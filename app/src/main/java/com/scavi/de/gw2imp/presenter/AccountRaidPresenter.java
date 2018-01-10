/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scavi.de.gw2imp.presenter;

import com.google.common.util.concurrent.FutureCallback;
import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.response.account.AccountRaids;
import com.scavi.de.gw2imp.communication.response.misc.RaidData;
import com.scavi.de.gw2imp.data.entity.raid.RaidEntity;
import com.scavi.de.gw2imp.model.AccountRaidModel;
import com.scavi.de.gw2imp.ui.view.IAccountRaidView;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

public class AccountRaidPresenter {
    private final IAccountRaidView mView;
    private final AccountRaidModel mModel;

    /**
     * Constructor
     *
     * @param view  the view for the account raid details
     * @param model the model for the account raid details
     */
    @Inject
    public AccountRaidPresenter(final IAccountRaidView view,
                                final AccountRaidModel model) {
        mView = view;
        mModel = model;
    }


    /**
     * This method verifies if the general raid data exists already. In case the raid data don't
     * exists (local), this method initiates a request to determine all general raid information.
     * Based on the raid information, another server side request will be initiated to determine the
     * account specific raid information to correlate both data inputs
     */
    public void loadRaidData() {
        FutureCallback<List<RaidEntity>> raidDataCallback = createRaidDataCallback();
        mView.onShowProgress();
        mModel.getRaidData(raidDataCallback);
    }


    /**
     * The purpose of this {@link FutureCallback} is to determine the general raid information
     * from the database. If the raid information are not within the database, they will be
     * requested from the server side and persisted into the database.
     * Also the account raid information will (all raids / wings that were completed during this
     * week) be requested from the server side.
     * The general raid data will be enriched with the account raid information and returned.
     * In case an error occurs, the view will be called.
     *
     * @return
     */
    private FutureCallback<List<RaidEntity>> createRaidDataCallback() {
        return new FutureCallback<List<RaidEntity>>() {
            @Override
            public void onSuccess(@Nullable final List<RaidEntity> result) {
                List<RaidEntity> generalRaidData = determineRaidData(result);
                // no error occurred
                if (generalRaidData != null) {
                    AccountRaids accountRaidData = determineAccountData();
                    if (accountRaidData != null) {
                        final List<RaidEntity> combinedRaidInfo = mModel.injectSections(
                                mModel.completedRaidData(generalRaidData, accountRaidData));
                        // show the raid information in the view
                        mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                            mView.onHideProgress();
                            mView.setupRaidView(combinedRaidInfo);
                        });
                    } else {
                        // show the raid information in the view
                        mModel.getExecutorAccess().getUiThreadExecutor().execute(
                                mView::onHideProgressAfterError);
                    }
                }
            }


            @Override
            public void onFailure(final Throwable t) {
                mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                    String errorInfo = mModel.getString(R.string.account_raid_data_access_error,
                            t.getLocalizedMessage());
                    mView.onHideProgressAfterError();
                    mView.showUserError(errorInfo);
                });
            }
        };
    }


    /**
     * This method determines which raid data in the app format have to be used.
     * In case the given raid data are not empty or null, they will be used. In case
     * the given data are empty or null, the data raid information will be requested
     * from the server side and persisted.
     *
     * @param knownRaidData the raid data from the database (possibly empty)
     * @return the persisted raid data to use (in the format of the app)
     */
    private List<RaidEntity> determineRaidData(final List<RaidEntity> knownRaidData) {
        if (knownRaidData != null && knownRaidData.size() > 0) {
            return knownRaidData;
        }

        List<RaidEntity> raidData = null;
        // no general raid data available yet, load the general raid information
        try {
            List<RaidData> generalRaidData = mModel.requestRaidData();
            raidData = mModel.persistRaidData(generalRaidData);
        } catch (final ResponseException ex) {
            mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                mView.onHideProgressAfterError();
                String errorInfo = mModel.determineAccountHttpError(ex.getResponseCode());
                mView.showUserError(errorInfo);
            });
        } catch (final IOException ex) {
            mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                String errorInfo = mModel.getString(R.string.account_raid_general_info,
                        ex.getLocalizedMessage());
                mView.onHideProgressAfterError();
                mView.showUserError(errorInfo);
            });
        }
        return raidData;
    }


    /**
     * Request the raid account data and processes errors that might occur
     *
     * @return the account raid data (all raids that were completed during this week)
     */
    private AccountRaids determineAccountData() {
        AccountRaids accountRaids = null;
        try {
            accountRaids = mModel.requestRaidAccountData();
        } catch (final ResponseException ex) {
            mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                mView.onHideProgressAfterError();
                String errorInfo = mModel.determineAccountHttpError(ex.getResponseCode());
                mView.showUserError(errorInfo);
            });
        } catch (final IOException ex) {
            mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                String errorInfo = mModel.getString(R.string.account_raid_account_data,
                        ex.getLocalizedMessage());
                mView.onHideProgressAfterError();
                mView.showUserError(errorInfo);
            });
        }
        return accountRaids;
    }
}
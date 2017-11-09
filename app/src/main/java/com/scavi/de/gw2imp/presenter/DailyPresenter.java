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

import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.response.achievement.DailyAchievements;
import com.scavi.de.gw2imp.data.so.Daily;
import com.scavi.de.gw2imp.model.DailyModel;
import com.scavi.de.gw2imp.ui.view.IDailyView;

import java.io.IOException;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

@ParametersAreNonnullByDefault
public class DailyPresenter {
    private final IDailyView mView;
    private final DailyModel mModel;

    /**
     * Constructor
     *
     * @param view  the view for the account dailies
     * @param model the model for the account dailies
     */
    @Inject
    public DailyPresenter(final IDailyView view,
                          final DailyModel model) {
        mView = view;
        mModel = model;
    }


    /**
     * Loads the current dailies and the achievements of the account to determine which dailies
     * are done and which dailies are open
     */
    public void loadDailiesToday() {
        mView.onShowProgress();
        mModel.executeDailyDataRequest(() -> {
            try {
                // request the information from the server side
                // FIXME: doesn't return the required info. A ticket is open to return the needed
                // info
                //List<AccountAchievement> requestedAccountDailies = mModel.requestAccountDailies();
                DailyAchievements requestedDailies = mModel.requestDailies();

                // merge and convert the requested data into the local app format
                List<Daily> dailies = Daily.from(requestedDailies);
                mModel.injectAchievementsData(dailies);

                mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                    mView.onHideProgress();
                    mView.setupDailyView(Daily.injectSections(dailies));
                });

            } catch (IOException ioEx) {
                mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                    mView.onHideProgressAfterError();
                    // TODO
                });
            } catch (final ResponseException ex) {
                mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                    mView.onHideProgressAfterError();
                    String errorInfo = mModel.determineAccountHttpError(ex.getResponseCode());
                    mView.showUserError(errorInfo);
                });
            }
        });
    }


    /**
     * Loads the current dailies and the achievements of the account to determine which dailies
     * are done and which dailies are open
     */
    public void loadDailiesTomorrow() {
        mView.onShowProgress();
        mModel.executeDailyDataRequest(() -> {
            try {
                // request the information from the server side
                DailyAchievements requestedDailiesTomorrow = mModel.requestDailiesTomorrow();
                // convert the requested data into the local app format
                List<Daily> dailiesTomorrow = Daily.from(requestedDailiesTomorrow);
                mModel.injectAchievementsData(dailiesTomorrow);
                mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                    // show in ui
                    mView.setupDailyView(Daily.injectSections(dailiesTomorrow));
                    mView.onHideProgress();
                });
            } catch (IOException ioEx) {
                mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                    mView.onHideProgressAfterError();
                    // TODO
                });
            } catch (final ResponseException ex) {
                mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                    mView.onHideProgressAfterError();
                    String errorInfo = mModel.determineAccountHttpError(ex.getResponseCode());
                    mView.showUserError(errorInfo);
                });
            }
        });
    }
}
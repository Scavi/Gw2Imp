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
package com.scavi.de.gw2imp.model;

import android.content.Context;
import android.support.annotation.AnyThread;
import android.support.annotation.WorkerThread;

import com.google.common.util.concurrent.FutureCallback;
import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.communication.access.IAccountAccess;
import com.scavi.de.gw2imp.communication.access.IMiscellaneousAccess;
import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.response.account.AccountRaids;
import com.scavi.de.gw2imp.communication.response.misc.RaidData;
import com.scavi.de.gw2imp.data.db.Gw2ImpDatabase;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.data.entity.raid.RaidEntity;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

@ParametersAreNonnullByDefault
public class AccountRaidModel extends AbstractModel {
    private static final String FORSAKEN_THICKET = "forsaken_thicket";
    private static final String BASTION_OF_THE_PERTINENT = "bastion_of_the_penitent";

    private final IAccountAccess mAccountAccess;
    private final IMiscellaneousAccess mMiscAccess;
    private final IDatabaseAccess mImpDatabase;
    private final IExecutorAccess mExecutorAccess;

    /**
     * Constructor
     *
     * @param context        the context to global information about the application environment
     * @param accountAccess  the server side access the account data
     * @param miscAccess     the server side access to the misc data
     * @param impDatabase    the database access of this application
     * @param executorAccess to access the main and background threads
     */
    @Inject
    public AccountRaidModel(final Context context,
                            final IAccountAccess accountAccess,
                            final IMiscellaneousAccess miscAccess,
                            final IDatabaseAccess impDatabase,
                            final IExecutorAccess executorAccess) {
        super(context);
        mAccountAccess = accountAccess;
        mMiscAccess = miscAccess;
        mImpDatabase = impDatabase;
        mExecutorAccess = executorAccess;
    }


    /**
     * Requests the general raid information from the server side
     *
     * @param callback the callback to process the raid result
     */
    @WorkerThread
    public void getRaidData(final FutureCallback<List<RaidEntity>> callback) {
        Callable<List<RaidEntity>> dbCall = () -> mImpDatabase.raidDAO().selectAll();
        mExecutorAccess.executeBackgroundTask(dbCall, callback);
    }

    /**
     * Requests the general raid information from the server side
     *
     * @return the general raid information.
     */
    @WorkerThread
    public List<RaidData> requestRaidData() throws IOException, ResponseException {
        return mMiscAccess.getRaid(FORSAKEN_THICKET,
                BASTION_OF_THE_PERTINENT);
    }


    /**
     * Requests the account raid information
     */
    @WorkerThread
    public AccountRaids requestRaidAccountData() throws IOException, ResponseException {
        return mAccountAccess.getRaids();
    }


    /**
     * Finds all raids that were passed by this account during the week.
     *
     * @param raidData        the known raid data
     * @param accountRaidData the raids that were solved during this week
     * @return the known raid data with the information which one was solved.
     */
    @AnyThread
    public List<RaidEntity> completedRaidData(final List<RaidEntity> raidData,
                                              final AccountRaids accountRaidData) {
        Set<String> raidsOfThisWeek = new HashSet<>(accountRaidData.getRaids());
        for (RaidEntity raid : raidData) {
            if (raidsOfThisWeek.contains(raid.getRaidContext())) {
                raid.setIsCompleted(true);
            }
        }
        return raidData;
    }


    /**
     * Persists the raid information into the data model of the application
     *
     * @param raidData the general raid data
     * @return the persisted raid data
     */
    @WorkerThread
    public List<RaidEntity> persistRaidData(final List<RaidData> raidData) {
        List<RaidEntity> raids = RaidEntity.from(raidData);
        mImpDatabase.raidDAO().insertAll(raids);
        return raids;
    }


    /**
     * Divide the raid information from the server side by sections. The section is defined by
     * it's raid. This is required for adapters.
     *
     * @param raids the known raids
     * @return the raids list with sections
     */
    public List<RaidEntity> injectSections(final List<RaidEntity> raids) {
        return RaidEntity.injectSections(raids);
    }


    /**
     * @return to access the main and background threads
     */
    public IExecutorAccess getExecutorAccess() {
        return mExecutorAccess;
    }
}

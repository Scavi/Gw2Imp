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

import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.communication.access.IAccountAccess;
import com.scavi.de.gw2imp.communication.access.IAchievementAccess;
import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.response.account.Achievement;
import com.scavi.de.gw2imp.communication.response.achievement.DailyAchievements;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

public class AccountDailyModel extends AbstractModel {
    private final IAccountAccess mAccountAccess;
    private final IAchievementAccess mAchievementAccess;
    private final IExecutorAccess mExecutorAccess;

    /**
     * Constructor
     *
     * @param context           the context to global information about the application environment
     * @param accountAccess     the server side access the account data
     * @param achievementAccess the server side access to the achievement data
     * @param executorAccess    to access the main and background threads
     */
    public AccountDailyModel(final Context context,
                             final IAccountAccess accountAccess,
                             final IAchievementAccess achievementAccess,
                             final IExecutorAccess executorAccess) {
        super(context);
        mAccountAccess = accountAccess;
        mAchievementAccess = achievementAccess;
        mExecutorAccess = executorAccess;
    }

    public void executeDailyDataRequest(final Runnable dailyRequestor) {
        mExecutorAccess.getBackgroundThreadExecutor().execute(dailyRequestor);
    }


    /**
     * @return
     */
    public List<Achievement> requestAccountDailies() throws IOException, ResponseException {
        return mAccountAccess.getAchievements();
    }


    /**
     * @return
     */
    public DailyAchievements requestDailies() throws IOException, ResponseException {
        return mAchievementAccess.getDaily();
    }


    /**
     * @return
     */
    public DailyAchievements requestDailiesTomorrow() throws IOException, ResponseException {
        return mAchievementAccess.getDailyTomorrow();
    }


    /**
     * @return to access the main and background threads
     */
    public IExecutorAccess getExecutorAccess() {
        return mExecutorAccess;
    }
}
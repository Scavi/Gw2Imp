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
import android.support.annotation.WorkerThread;

import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.communication.access.IAccountAccess;
import com.scavi.de.gw2imp.communication.access.IAchievementAccess;
import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.response.account.AccountAchievement;
import com.scavi.de.gw2imp.communication.response.achievement.Achievement;
import com.scavi.de.gw2imp.communication.response.achievement.DailyAchievements;
import com.scavi.de.gw2imp.data.dao.IAchievementDAO;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.data.entity.achievement.AchievementEntity;
import com.scavi.de.gw2imp.data.so.Daily;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class AccountDailyModel extends AbstractModel {
    private final IAccountAccess mAccountAccess;
    private final IAchievementAccess mAchievementAccess;
    private final IExecutorAccess mExecutorAccess;
    private final IDatabaseAccess mImpDatabase;

    /**
     * Constructor
     *
     * @param context           the context to global information about the application environment
     * @param accountAccess     the server side access the account data
     * @param achievementAccess the server side access to the achievement data
     * @param executorAccess    to access the main and background threads
     * @param impDatabase       the database access of this application
     */
    public AccountDailyModel(final Context context,
                             final IAccountAccess accountAccess,
                             final IAchievementAccess achievementAccess,
                             final IExecutorAccess executorAccess,
                             final IDatabaseAccess impDatabase) {
        super(context);
        mAccountAccess = accountAccess;
        mAchievementAccess = achievementAccess;
        mExecutorAccess = executorAccess;
        mImpDatabase = impDatabase;
    }


    /**
     * Executes the given runnable in the background thread
     *
     * @param dailyRequestor the daily accessor
     */
    public void executeDailyDataRequest(final Runnable dailyRequestor) {
        mExecutorAccess.getBackgroundThreadExecutor().execute(dailyRequestor);
    }


    /**
     * Determines and inject the achievement to the given dailies
     *
     * @param dailies the dailies
     */
    @WorkerThread
    public void injectAchievementsData(@Nullable final List<Daily> dailies)
            throws IOException, ResponseException {
        if (dailies == null || dailies.size() == 0) {
            return;
        }
        for (Daily daily : dailies) {
            AchievementEntity entity = getOrRequestAchievementData(daily.getId());
            daily.setAchievementData(entity);
        }
    }

    /**
     * Tries to get the {@link AchievementEntity} to the given id. Does a lookup in the database
     * first. In case the achievement is not known local, a request will be initiated to
     * determine the data. In case the achievement is known on server side, the entity will be
     * persisted into the local database of the application
     *
     * @param id the id of the requested achievement
     * @return the achievement (if it is known). Otherwise <code>null</code>
     */
    @WorkerThread
    private AchievementEntity getOrRequestAchievementData(final int id)
            throws IOException, ResponseException {
        AchievementEntity achievement = selectAchievement(id);
        if (achievement == null) {
            List<Achievement> requestedAchievement = mAchievementAccess.getAchievement(id);
            if (requestedAchievement != null && requestedAchievement.size() > 0) {
                achievement = AchievementEntity.from(requestedAchievement.get(0));
                insertAchievement(achievement);
            }
        }
        return achievement;
    }


    /**
     * Selects the achievement of the given id from the database
     *
     * @param id the id of the requested achievement
     * @return the achievement (if it is known). Otherwise <code>null</code>
     */
    @WorkerThread
    private AchievementEntity selectAchievement(final int id) {
        IAchievementDAO dao = mImpDatabase.achievementDAO();
        AchievementEntity achievement = dao.selectAchievement(id);
        if (achievement != null) {
            achievement.setTiers(dao.selectTiers(id));
            achievement.setFlags(dao.selectFlags(id));
            achievement.setRewards(dao.selectRewards(id));
        }
        return achievement;
    }


    /**
     * Inserts the achievement, the flags, the rewards and tiers into the database
     *
     * @param achievement the achievement to insert
     */
    @WorkerThread
    private void insertAchievement(final AchievementEntity achievement) {
        mImpDatabase.achievementDAO().insertAchievement(achievement);
        mImpDatabase.achievementDAO().insertFlags(achievement.getFlags());
        mImpDatabase.achievementDAO().insertRewards(achievement.getRewards());
        mImpDatabase.achievementDAO().insertTiers(achievement.getTiers());
    }

    /**
     * @return the achievements of the account with a synchronous request
     */
    @WorkerThread
    public List<AccountAchievement> requestAccountDailies() throws IOException, ResponseException {
        return mAccountAccess.getAchievements();
    }


    /**
     * @return the daily achievements today with a synchronous request
     */
    @WorkerThread
    public DailyAchievements requestDailies() throws IOException, ResponseException {
        return mAchievementAccess.getDaily();
    }


    /**
     * @return the daily achievements tomorrow with a synchronous request
     */
    @WorkerThread
    public DailyAchievements requestDailiesTomorrow() throws IOException, ResponseException {
        return mAchievementAccess.getDailyTomorrow();
    }


    /**
     * @return the achievement information (data) with a synchronous request
     */
    @WorkerThread
    public List<Achievement> requestAchievementsData() throws IOException, ResponseException {
        return mAchievementAccess.getAchievements();
    }


    /**
     * @return to access the main and background threads
     */
    public IExecutorAccess getExecutorAccess() {
        return mExecutorAccess;
    }
}
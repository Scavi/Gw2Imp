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
package com.scavi.de.gw2imp.data.so;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.helper.IDaily;
import com.scavi.de.gw2imp.communication.response.account.AccountAchievement;
import com.scavi.de.gw2imp.communication.response.achievement.DailyAchievements;
import com.scavi.de.gw2imp.data.entity.achievement.AchievementEntity;
import com.scavi.de.gw2imp.data.entity.raid.RaidEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Daily {
    private final static int NOT_COMPLETED = 0;
    private final String mType;
    private final int mId;
    private final int mCurrent;
    private final int mMax;
    private AchievementEntity mAchievementData;


    /**
     * Constructor
     *
     * @param type the type of the daily (e.g. pvp or wvw)
     * @param id   the id of the daily
     */
    public Daily(final String type,
                 final int id) {
        this(type, id, NOT_COMPLETED, NOT_COMPLETED);
    }


    /**
     * Constructor
     *
     * @param type    the type of the daily (e.g. pvp or wvw)
     * @param id      the id of the daily
     * @param current The player's current progress towards the achievement
     * @param max     The amount needed to complete the achievement. Most WvW achievements have
     *                this set to -1.
     */
    public Daily(final String type,
                 final int id,
                 final int current,
                 final int max) {
        mType = type;
        mId = id;
        mCurrent = current;
        mMax = max;
    }


    /**
     * @return the type of the daily (e.g. pvp or wvw)
     */
    public String getType() {
        return mType;
    }


    /**
     * @return the id of the daily
     */
    public int getId() {
        return mId;
    }


    /**
     * @return The player's current progress towards the achievement
     */
    public int getCurrent() {
        return mCurrent;
    }


    /**
     * @return The amount needed to complete the achievement. Most WvW achievements have
     * this set to -1.
     */
    public int getMax() {
        return mMax;
    }


    /**
     * @return <code>true</code> the daily is completed <br/>
     * <code>false</code> the daily is not completed
     */
    public boolean isCompleted() {
        return mCurrent == mMax && mMax != NOT_COMPLETED;
    }


    /**
     * @return <code>true</code> if the current data are only for information (e.g. section)
     * else <code>false</code>
     */
    public boolean isInfoData() {
        return mId < 0;
    }


    /**
     * Creates a list of {@link Daily} based on the possible dailies. The completed dailies of
     * the account will be marked
     *
     * @param dailies             the possible daily achievements
     * @return all converted dailies
     */
    public static List<Daily> from(final DailyAchievements dailies) {
        // FIXME - API doesn't provide it right now
//        if (accountAchievements == null || accountAchievements.size() == 0) {
//            return new ArrayList<>(0);
//        }
//        accountAchievements.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));

        List<Daily> dailyStatus = new ArrayList<>(32);
        from(dailies.getPve(), dailyStatus);
        from(dailies.getPvp(), dailyStatus);
        from(dailies.getWvw(), dailyStatus);
        from(dailies.getFractals(), dailyStatus);
        return dailyStatus;
    }


    /**
     * @return the achievement information to the current daily
     */
    public AchievementEntity getAchievementData() {
        return mAchievementData;
    }


    /**
     * @param achievementData the achievement information to the current daily
     */
    public void setAchievementData(final AchievementEntity achievementData) {
        mAchievementData = achievementData;
    }


    /**
     * Iterates through the given dailies (pvp, pve, wvw,...) and verify the current account data
     * with the dailies to mark them, if they are completed.
     * If they are not completed they will be added as uncompleted entry
     *
     * @param dailies             the current dailies by it's context (e.g. pvp, wvw)
     * @param dailyStatus         the list of all dailies
     */
    private static <T extends IDaily> void from(@Nullable final List<T> dailies,

                                                final List<Daily> dailyStatus) {
        if (dailies == null || dailies.size() == 0) {
            return;
        }
        for (IDaily daily : dailies) {
            // FIXME API doesn't provide an information, if a daily is done, yet
//            AccountAchievement achievement = new AccountAchievement(daily.getId());
//            int lookup =
//                    accountAchievements == null ? -1 : Collections.binarySearch
//                    (accountAchievements, achievement);
//            // verifies if the daily is done by the user
//            if (lookup >= 0) {
//                AccountAchievement accountAchievement = accountAchievements.get(lookup);
//                dailyStatus.add(new Daily(
//                        daily.getType(),
//                        daily.getId(),
//                        accountAchievement.getCurrent(),
//                        accountAchievement.getMax()));
//            } else {
//                dailyStatus.add(new Daily(daily.getType(), daily.getId()));
//            }
            dailyStatus.add(new Daily(daily.getType(), daily.getId()));
        }
    }


    /**
     * Divide the daily information from the server side by sections. The section is defined by
     * it's type. This is required for adapters.
     *
     * @param dailies the known dailies
     * @return the raids list with sections
     */
    @NonNull
    public static List<Daily> injectSections(final List<Daily> dailies) {
        String lastSectionName = null;
        for (int i = 0; i < dailies.size(); ++i) {
            Daily daily = dailies.get(i);
            if (i == 0 || !daily.getType().equals(lastSectionName)) {
                lastSectionName = daily.getType();
                dailies.add(i, new Daily(lastSectionName, -1));
            }
        }
        return dailies;
    }
}

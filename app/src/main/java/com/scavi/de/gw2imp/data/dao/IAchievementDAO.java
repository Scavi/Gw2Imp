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
package com.scavi.de.gw2imp.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scavi.de.gw2imp.data.entity.achievement.AchievementEntity;
import com.scavi.de.gw2imp.data.entity.achievement.FlagsEntity;
import com.scavi.de.gw2imp.data.entity.achievement.RewardEntity;
import com.scavi.de.gw2imp.data.entity.achievement.TierEntity;
import com.scavi.de.gw2imp.data.util.DbConst;

import java.util.List;

@Dao
public interface IAchievementDAO {
    /**
     * @return all achievements from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ACHIEVEMENT)
    List<AchievementEntity> selectAchievements();


    /**
     * @param id the id of the achievement
     * @return the achievement to the given id from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ACHIEVEMENT + " WHERE id = :id")
    AchievementEntity selectAchievement(final int id);


    /**
     * @param achievementId the foreign key to the achievement table
     * @return all achievements flags from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ACHIEVEMENT_FLAGS + " WHERE achievement_id = " +
            ":achievementId")
    List<FlagsEntity> selectFlags(final int achievementId);


    /**
     * @param achievementId the foreign key to the achievement table
     * @return all achievement rewards from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ACHIEVEMENT_REWARDS + " WHERE achievement_id = " +
            ":achievementId")
    List<RewardEntity> selectRewards(final int achievementId);


    /**
     * @param achievementId the foreign key to the achievement table
     * @return all achievements tiers from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ACHIEVEMENT_TIERS + " WHERE achievement_id = " +
            ":achievementId")
    List<TierEntity> selectTiers(final int achievementId);


    /**
     * Inserts all achievements
     *
     * @param raids the raids to insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAchievement(final AchievementEntity raids);


    /**
     * Insert the flags to the achievements
     *
     * @param flags the achievement flags
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFlags(final List<FlagsEntity> flags);


    /**
     * Insert the rewards to the achievements
     *
     * @param rewards the reward flags
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRewards(final List<RewardEntity> rewards);


    /**
     * Insert the tiers to the achievements
     *
     * @param rewards the tier flags
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTiers(final List<TierEntity> rewards);
}

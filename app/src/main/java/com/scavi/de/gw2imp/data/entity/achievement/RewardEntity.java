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
package com.scavi.de.gw2imp.data.entity.achievement;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

import com.scavi.de.gw2imp.data.util.DbConst;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@Entity(tableName = DbConst.TABLE_ACHIEVEMENT_REWARDS,
        primaryKeys = {"achievement_id", "id"},
        foreignKeys = @ForeignKey(
                entity = AchievementEntity.class,
                parentColumns = "id",
                childColumns = "achievement_id"))
public class RewardEntity {
    @NonNull
    @ColumnInfo(name = "achievement_id")
    private final Integer mAchievementId;
    @NonNull
    @ColumnInfo(name = "id")
    private final Integer mId;
    @ColumnInfo(name = "type")
    private final String mType;
    @ColumnInfo(name = "count")
    private final Integer mCount;


    /**
     * Constructor
     *
     * @param achievementId The id of the achievement this reward is referenced
     * @param id            If Item: The item ID to be rewarded. <br/>
     *                      If Mastery: The mastery point ID to be rewarded. <br/>
     *                      If Title: The title id.
     * @param type          The type of reward. Additional fields appear for different values
     *                      of type.
     * @param count         If Coins: The number of Coins to be rewarded. <br/>
     *                      If Item: The number of id to be rewarded. <br/>
     */
    public RewardEntity(@NonNull final Integer achievementId,
                        @NonNull final Integer id,
                        final String type,
                        final Integer count) {
        mAchievementId = achievementId;
        mId = id;
        mType = type;
        mCount = count;
    }



    /**
     * @return e id of the achievement this reward is referenced
     */
    @NonNull
    public Integer getAchievementId() {
        return mAchievementId;
    }


    /**
     * @return If Item: The item ID to be rewarded. <br/>
     * If Mastery: The mastery point ID to be rewarded. <br/>
     * If Title: The title id.
     */
    @NonNull
    public Integer getId() {
        return mId;
    }


    /**
     * @return The type of reward. Additional fields appear for different values
     * of type.
     */
    public String getType() {
        return mType;
    }


    /**
     * @return If Coins: The number of Coins to be rewarded. <br/>
     * If Item: The number of id to be rewarded. <br/>
     */
    public Integer getCount() {
        return mCount;
    }
}

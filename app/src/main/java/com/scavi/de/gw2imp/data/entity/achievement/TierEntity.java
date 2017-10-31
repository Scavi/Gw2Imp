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
@Entity(tableName = DbConst.TABLE_ACHIEVEMENT_TIERS,
        primaryKeys = {"achievement_id", "count", "points"},
        foreignKeys = @ForeignKey(
                entity = AchievementEntity.class,
                parentColumns = "id",
                childColumns = "achievement_id"))
public class TierEntity {
    @NonNull
    @ColumnInfo(name = "achievement_id")
    private final Integer mAchievementId;
    @NonNull
    @ColumnInfo(name = "count")
    private final Integer mCount;
    @NonNull
    @ColumnInfo(name = "points")
    private final Integer mPoints;

    /**
     * Constructor
     *
     * @param achievementId The id of the achievement this reward is referenced
     * @param count         The number of "things" (achievement-specific) that must be completed to
     *                      achieve this tier.
     * @param points        The amount of AP awarded for completing this tier.
     */
    public TierEntity(final Integer achievementId,
                      final Integer count,
                      final Integer points) {
        mAchievementId = achievementId;
        mCount = count;
        mPoints = points;
    }


    /**
     * @return The id of the achievement this reward is referenced
     */
    @NonNull
    public Integer getAchievementId() {
        return mAchievementId;
    }


    /**
     * @return The number of "things" (achievement-specific) that must be completed to achieve
     * this tier.
     */
    @NonNull
    public Integer getCount() {
        return mCount;
    }


    /**
     * @return The amount of AP awarded for completing this tier.
     */
    @NonNull
    public Integer getPoints() {
        return mPoints;
    }
}
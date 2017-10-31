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
@Entity(tableName = DbConst.TABLE_ACHIEVEMENT_FLAGS,
        primaryKeys = {"achievement_id", "flag"},
        foreignKeys = @ForeignKey(
                entity = AchievementEntity.class,
                parentColumns = "id",
                childColumns = "achievement_id"))
public class FlagsEntity {
    @NonNull
    @ColumnInfo(name = "achievement_id")
    private final Integer mAchievementId;
    @NonNull
    @ColumnInfo(name = "flag")
    private final String mFlag;


    /**
     * Constructor
     *
     * @param achievementId The id of the achievement this reward is referenced
     * @param flag          Achievement categories. Possible values:
     *                      Pvp - can only get progress in PvP or WvW
     *                      CategoryDisplay - is a meta achievement
     *                      MoveToTop - affects in-game UI collation
     *                      IgnoreNearlyComplete - doesn't appear in the "nearly complete" UI
     *                      Repeatable - can be repeated multiple times
     *                      Hidden - hidden achievement; must fulfil unlock requirements before
     *                      making progress or showing in the hero panel
     *                      RequiresUnlock - must fulfil unlock requirements before making
     *                      progress but will show in the hero panel before unlocking
     *                      RepairOnLogin - unknown
     *                      Daily - Flags an achievement as resetting daily.
     *                      Weekly - Flags an achievement as resetting weekly.
     *                      Monthly - Flags an achievement as resetting monthly.
     *                      Permanent - Flags an achievement as progress never reseting.
     */
    public FlagsEntity(final Integer achievementId,
                       final String flag) {
        mAchievementId = achievementId;
        mFlag = flag;
    }


    /**
     * @return The id of the achievement this reward is referenced
     */
    @NonNull
    public Integer getAchievementId() {
        return mAchievementId;
    }


    /**
     * @return Achievement categories. Possible values:
     * Pvp - can only get progress in PvP or WvW
     * CategoryDisplay - is a meta achievement
     * MoveToTop - affects in-game UI collation
     * IgnoreNearlyComplete - doesn't appear in the "nearly complete" UI
     * Repeatable - can be repeated multiple times
     * Hidden - hidden achievement; must fulfil unlock requirements before
     * making progress or showing in the hero panel
     * RequiresUnlock - must fulfil unlock requirements before making
     * progress but will show in the hero panel before unlocking
     * RepairOnLogin - unknown
     * Daily - Flags an achievement as resetting daily.
     * Weekly - Flags an achievement as resetting weekly.
     * Monthly - Flags an achievement as resetting monthly.
     * Permanent - Flags an achievement as progress never reseting.
     */
    @NonNull
    public String getFlag() {
        return mFlag;
    }
}

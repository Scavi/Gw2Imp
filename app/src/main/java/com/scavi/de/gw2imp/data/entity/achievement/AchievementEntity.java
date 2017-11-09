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
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.scavi.de.gw2imp.communication.response.achievement.Achievement;
import com.scavi.de.gw2imp.data.util.DbConst;

import java.util.List;

import javax.annotation.Nullable;

@Entity(tableName = DbConst.TABLE_ACHIEVEMENT)
public class AchievementEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final Integer mId;
    @ColumnInfo(name = "name")
    private final String mName;
    @ColumnInfo(name = "description")
    private final String mDescription;
    @ColumnInfo(name = "requirement")
    private final String mRequirement;
    @ColumnInfo(name = "locked_text")
    private final String mLockedText;
    @ColumnInfo(name = "type")
    private final String mType;
    @Ignore
    private List<RewardEntity> mRewards;
    @Ignore
    private List<FlagsEntity> mFlags;
    @Ignore
    private List<TierEntity> mTiers;


    /**
     * Constructor
     *
     * @param id          The achievement id.
     * @param name        The achievement name.
     * @param description The achievement description.
     * @param requirement The achievement requirement as listed in-game.
     * @param lockedText  The achievement description prior to unlocking it.
     * @param type        The achievement type. Possible values:
     *                    Default - A default achievement.
     *                    ItemSet - Achievement is linked to Collections
     */
    public AchievementEntity(final Integer id,
                             final String name,
                             final String description,
                             final String requirement,
                             final String lockedText,
                             final String type) {
        mId = id;
        mName = name;
        mDescription = description;
        mRequirement = requirement;
        mLockedText = lockedText;
        mType = type;
    }


    /**
     * @return The achievement id.
     */
    public Integer getId() {
        return mId;
    }


    /**
     * @return The achievement name.
     */
    public String getName() {
        return mName;
    }


    /**
     * @return The achievement description.
     */
    public String getDescription() {
        return mDescription;
    }


    /**
     * @return The achievement requirement as listed in-game.
     */
    public String getRequirement() {
        return mRequirement;
    }


    /**
     * @return The achievement description prior to unlocking it.
     */
    public String getLockedText() {
        return mLockedText;
    }


    /**
     * @return The achievement type. Possible values:
     * Default - A default achievement.
     * ItemSet - Achievement is linked to Collections
     */
    public String getType() {
        return mType;
    }


    /**
     * @return (array of strings) - Achievement categories. Possible values:
     * Pvp - can only get progress in PvP or WvW
     * CategoryDisplay - is a meta achievement
     * MoveToTop - affects in-game UI collation
     * IgnoreNearlyComplete - doesn't appear in the "nearly complete" UI
     * Repeatable - can be repeated multiple times
     * Hidden - hidden achievement; must fulfil unlock requirements before making progress or
     * showing in the hero panel
     * RequiresUnlock - must fulfil unlock requirements before making progress but will show in
     * the hero panel before unlocking
     * RepairOnLogin - unknown
     * Daily - Flags an achievement as resetting daily.
     * Weekly - Flags an achievement as resetting weekly.
     * Monthly - Flags an achievement as resetting monthly.
     * Permanent - Flags an achievement as progress never reseting.
     */
    public List<FlagsEntity> getFlags() {
        return mFlags;
    }


    /**
     * @return Describes the rewards given for the achievement. Each object contains:
     * type (string) - The type of reward. Additional fields appear for different values of type.
     * If Coins:
     * count (number) - The number of Coins to be rewarded.
     * If Item:
     * id (number) - The item ID to be rewarded.
     * count (number) - The number of id to be rewarded.
     * If Mastery:
     * id (number) - The mastery point ID to be rewarded.
     * region (string) - The region the Mastery Point applies to. Either Tyria or Maguuma.
     * If Title:
     * id (number) - The title id.
     */
    public List<RewardEntity> getRewards() {
        return mRewards;
    }


    /**
     * @param flags (array of strings) - Achievement categories. Possible values:
     *              Pvp - can only get progress in PvP or WvW
     *              CategoryDisplay - is a meta achievement
     *              MoveToTop - affects in-game UI collation
     *              IgnoreNearlyComplete - doesn't appear in the "nearly complete" UI
     *              Repeatable - can be repeated multiple times
     *              Hidden - hidden achievement; must fulfil unlock requirements before making
     *              progress or showing in the hero panel
     *              RequiresUnlock - must fulfil unlock requirements before making progress but
     *              will show in the hero panel before unlocking
     *              RepairOnLogin - unknown
     *              Daily - Flags an achievement as resetting daily.
     *              Weekly - Flags an achievement as resetting weekly.
     *              Monthly - Flags an achievement as resetting monthly.
     *              Permanent - Flags an achievement as progress never reseting.
     */
    public void setFlags(final List<FlagsEntity> flags) {
        mFlags = flags;
    }


    /**
     * @param rewards Describes the rewards given for the achievement. Each object contains:
     *                type (string) - The type of reward. Additional fields appear for different
     *                values of type.
     *                If Coins:
     *                count (number) - The number of Coins to be rewarded.
     *                If Item:
     *                id (number) - The item ID to be rewarded.
     *                count (number) - The number of id to be rewarded.
     *                If Mastery:
     *                id (number) - The mastery point ID to be rewarded.
     *                region (string) - The region the Mastery Point applies to. Either Tyria or
     *                Maguuma.
     *                If Title:
     *                id (number) - The title id.
     */
    public void setRewards(final List<RewardEntity> rewards) {
        mRewards = rewards;
    }


    /**
     * @return Describes the achievement's tiers. Each object contains:
     * count (number) - The number of "things" (achievement-specific) that must be completed to
     * achieve this tier.
     * points (number) The amount of AP awarded for completing this tier.
     */
    public List<TierEntity> getTiers() {
        return mTiers;
    }

    /**
     * @param tiers Describes the achievement's tiers. Each object contains:
     *              count (number) - The number of "things" (achievement-specific) that must be
     *              completed to achieve this tier.
     *              points (number) The amount of AP awarded for completing this tier.
     */
    public void setTiers(final List<TierEntity> tiers) {
        mTiers = tiers;
    }

    /**
     * @param achievement creates an {@link AchievementEntity} with it's child entities from the
     *                    given achievement
     * @return the achievement entity
     */
    public static AchievementEntity from(@Nullable final Achievement achievement) {
        if (achievement == null) {
            return null;
        }
        AchievementEntity achievementEntity = new AchievementEntity(
                achievement.getId(),
                achievement.getName(),
                achievement.getDescription(),
                achievement.getRequirement(),
                achievement.getLockedText(),
                achievement.getType());
        achievementEntity.setFlags(FlagsEntity.from(achievement, achievement.getFlags()));
        achievementEntity.setRewards(RewardEntity.from(achievement, achievement.getRewards()));
        achievementEntity.setTiers(TierEntity.from(achievement, achievement.getTiers()));
        return achievementEntity;
    }
}

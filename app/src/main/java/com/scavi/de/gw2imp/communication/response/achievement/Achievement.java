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

package com.scavi.de.gw2imp.communication.response.achievement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Information about achievements
 */
public class Achievement {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("requirement")
    @Expose
    private String requirement;
    @SerializedName("locked_text")
    @Expose
    private String lockedText;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("flags")
    @Expose
    private List<String> flags;
    @SerializedName("tiers")
    @Expose
    private List<Tier> tiers;
    @SerializedName("prerequisites")
    @Expose
    private List<Integer> prerequisites;
    @SerializedName("rewards")
    @Expose
    private List<Reward> rewards;

    /**
     * @return The achievement id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The achievement id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The achievement name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The achievement name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The achievement description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The achievement description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The achievement requirement as listed in-game.
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * @param requirement The achievement requirement as listed in-game.
     */
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    /**
     * @return The achievement description prior to unlocking it.
     */
    public String getLockedText() {
        return lockedText;
    }

    /**
     * @param lockedText The achievement description prior to unlocking it.
     */
    public void setLockedText(String lockedText) {
        this.lockedText = lockedText;
    }

    /**
     * @return The achievement type. Possible values:
     * Default - A default achievement.
     * ItemSet - Achievement is linked to Collections
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The achievement type. Possible values:
     *             Default - A default achievement.
     *             ItemSet - Achievement is linked to Collections
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return (array of strings) - Achievement categories. Possible values:
     * Pvp - can only get progress in PvP or WvW
     * CategoryDisplay - is a meta achievement
     * MoveToTop - affects in-game UI collation
     * IgnoreNearlyComplete - doesn't appear in the "nearly complete" UI
     * Repeatable - can be repeated multiple times
     * Hidden - hidden achievement; must fulfil unlock requirements before making progress or showing in the hero panel
     * RequiresUnlock - must fulfil unlock requirements before making progress but will show in the hero panel before unlocking
     * RepairOnLogin - unknown
     * Daily - Flags an achievement as resetting daily.
     * Weekly - Flags an achievement as resetting weekly.
     * Monthly - Flags an achievement as resetting monthly.
     * Permanent - Flags an achievement as progress never reseting.
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * @param flags (array of strings) - Achievement categories. Possible values:
     *              Pvp - can only get progress in PvP or WvW
     *              CategoryDisplay - is a meta achievement
     *              MoveToTop - affects in-game UI collation
     *              IgnoreNearlyComplete - doesn't appear in the "nearly complete" UI
     *              Repeatable - can be repeated multiple times
     *              Hidden - hidden achievement; must fulfil unlock requirements before making progress or showing in the hero panel
     *              RequiresUnlock - must fulfil unlock requirements before making progress but will show in the hero panel before unlocking
     *              RepairOnLogin - unknown
     *              Daily - Flags an achievement as resetting daily.
     *              Weekly - Flags an achievement as resetting weekly.
     *              Monthly - Flags an achievement as resetting monthly.
     *              Permanent - Flags an achievement as progress never reseting.
     */
    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    /**
     * @return Describes the achievement's tiers. Each object contains:
     * count (number) - The number of "things" (achievement-specific) that must be completed to achieve this tier.
     * points (number) The amount of AP awarded for completing this tier.
     */
    public List<Tier> getTiers() {
        return tiers;
    }

    /**
     * @param tiers Describes the achievement's tiers. Each object contains:
     *              count (number) - The number of "things" (achievement-specific) that must be completed to achieve this tier.
     *              points (number) The amount of AP awarded for completing this tier.
     */
    public void setTiers(List<Tier> tiers) {
        this.tiers = tiers;
    }

    /**
     * @return Contains an array of achievement ids required to progress the given achievement.
     */
    public List<Integer> getPrerequisites() {
        return prerequisites;
    }

    /**
     * @param prerequisites Contains an array of achievement ids required to progress the given achievement.
     */
    public void setPrerequisites(List<Integer> prerequisites) {
        this.prerequisites = prerequisites;
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
    public List<Reward> getRewards() {
        return rewards;
    }

    /**
     * @param rewards Describes the rewards given for the achievement. Each object contains:
     *                type (string) - The type of reward. Additional fields appear for different values of type.
     *                If Coins:
     *                count (number) - The number of Coins to be rewarded.
     *                If Item:
     *                id (number) - The item ID to be rewarded.
     *                count (number) - The number of id to be rewarded.
     *                If Mastery:
     *                id (number) - The mastery point ID to be rewarded.
     *                region (string) - The region the Mastery Point applies to. Either Tyria or Maguuma.
     *                If Title:
     *                id (number) - The title id.
     */
    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }
}

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

package com.scavi.de.gw2imp.communication.response.mechanics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Describes the skill's effect
 */
public class Fact {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("hit_count")
    @Expose
    private Integer hitCount;
    @SerializedName("dmg_multiplier")
    @Expose
    private Integer dmgMultiplier;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("apply_count")
    @Expose
    private Integer applyCount;
    @SerializedName("distance")
    @Expose
    private Integer distance;

    /**
     * @return An arbitrary localized string describing the fact.
     */
    public String getText() {
        return text;
    }

    /**
     * @param text An arbitrary localized string describing the fact.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return Defines what additional fields the object will contain, and what type of fact it is. Can be one of the following:
     * AttributeAdjust
     * Buff
     * ComboField
     * ComboFinisher
     * Damage
     * Distance
     * Duration
     * Heal
     * HealingAdjust
     * NoData
     * Number
     * Percent
     * PrefixedBuff
     * Radius
     * Range
     * Recharge
     * Time
     * Unblockable
     */
    public String getType() {
        return type;
    }

    /**
     * @param type Defines what additional fields the object will contain, and what type of fact it is. Can be one of the following:
     *             AttributeAdjust
     *             Buff
     *             ComboField
     *             ComboFinisher
     *             Damage
     *             Distance
     *             Duration
     *             Heal
     *             HealingAdjust
     *             NoData
     *             Number
     *             Percent
     *             PrefixedBuff
     *             Radius
     *             Range
     *             Recharge
     *             Time
     *             Unblockable
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return A URL to the icon shown with the fact. Not included with all facts.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon A URL to the icon shown with the fact. Not included with all facts.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return Damage: The amount of times the damage hits.<br/>
     * Heal: The number of times the heal is applied. <br/>
     */
    public Integer getHitCount() {
        return hitCount;
    }

    /**
     * @param hitCount Damage: The amount of times the damage hits.<br/>
     *                 Heal: The number of times the heal is applied. <br/>
     */
    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    /**
     * @return Damage: Indicates the damage multiplier value of that skill.
     */
    public Integer getDmgMultiplier() {
        return dmgMultiplier;
    }

    /**
     * @param dmgMultiplier Damage: Indicates the damage multiplier value of that skill.
     */
    public void setDmgMultiplier(Integer dmgMultiplier) {
        this.dmgMultiplier = dmgMultiplier;
    }

    /**
     * @return The duration in seconds.
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * @param duration The duration in seconds.
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * @return The boon, condition, or effect referred to by the fact.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The boon, condition, or effect referred to by the fact.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The description of the status effect.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description of the status effect.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The number of stacks applied.
     */
    public Integer getApplyCount() {
        return applyCount;
    }

    /**
     * @param applyCount The number of stacks applied.
     */
    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    /**
     * @return The distance value.
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * @param distance The distance value.
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}

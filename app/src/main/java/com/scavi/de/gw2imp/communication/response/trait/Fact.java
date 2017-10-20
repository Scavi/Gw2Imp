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

package com.scavi.de.gw2imp.communication.response.trait;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Facts information of the trait
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
    @SerializedName("percent")
    @Expose
    private Integer percent;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("target")
    @Expose
    private String target;

    /**
     * @return An arbitrary localized string describing the fact. Not included with all facts.
     */
    public String getText() {
        return text;
    }

    /**
     * @param text An arbitrary localized string describing the fact. Not included with all facts.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return Defines what additional fields the object will contain, and what type of fact it is. Can be one of the following:
     * AttributeAdjust
     * Buff
     * BuffConversion
     * ComboField
     * ComboFinisher
     * Damage
     * Distance
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
     *             BuffConversion
     *             ComboField
     *             ComboFinisher
     *             Damage
     *             Distance
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

    /**
     * @return BuffConversion: How much of the source attribute is added to target. <br/>
     * ComboFinisher: The percent chance that the finisher will trigger.<br/>
     * Percent: The percentage value as referenced by text.
     */
    public Integer getPercent() {
        return percent;
    }

    /**
     * @param percent BuffConversion: How much of the source attribute is added to target. <br/>
     *                ComboFinisher: The percent chance that the finisher will trigger.<br/>
     *                Percent: The percentage value as referenced by text.
     */
    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    /**
     * @return AttributeAdjust:  The amount target gets adjusted, based on a level 80 character at base stats.
     * Number: The number value as referenced by text.
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value AttributeAdjust:  The amount target gets adjusted, based on a level 80 character at base stats.
     *              Number: The number value as referenced by text.
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return AttributeAdjust: The attribute this fact adjusts. Note that a value of Healing indicates the fact is a heal, and Ferocity is encoded at CritDamage.
     * BuffConversion: The attribute that gets added to.
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target AttributeAdjust: The attribute this fact adjusts. Note that a value of Healing indicates the fact is a heal, and Ferocity is encoded at CritDamage.
     *               BuffConversion: The attribute that gets added to.
     */
    public void setTarget(String target) {
        this.target = target;
    }
}

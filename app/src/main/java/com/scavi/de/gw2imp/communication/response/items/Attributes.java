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

package com.scavi.de.gw2imp.communication.response.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Attributes this bonus applies to
 */
public class Attributes {

    @SerializedName("attribute")
    @Expose
    private String attribute;
    @SerializedName("modifier")
    @Expose
    private Integer modifier;

    /**
     * @return Attributes this bonus applies to. Possible values:
     * BoonDuration - Concentration
     * ConditionDamage – Condition Damage
     * ConditionDuration - Expertise
     * CritDamage – Ferocity
     * Healing – Healing Power
     * Power – Power
     * Precision – Precision
     * Toughness – Toughness
     * Vitality – Vitality
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * @param attribute Attributes this bonus applies to. Possible values:
     *                  BoonDuration - Concentration
     *                  ConditionDamage – Condition Damage
     *                  ConditionDuration - Expertise
     *                  CritDamage – Ferocity
     *                  Healing – Healing Power
     *                  Power – Power
     *                  Precision – Precision
     *                  Toughness – Toughness
     *                  Vitality – Vitality
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * @return The modifier value.
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * @param modifier The modifier value.
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }
}

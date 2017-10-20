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
 * Information about itemstats for items that are in the game.
 */
public class ItemStat {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;

    /**
     * @return The itemstat id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The itemstat id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The name of the set of stats.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name of the set of stats.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return (array of key/value pair object) (default/null value: {} empty array) – List of attribute bonuses. Each object may contain the following key/value pairs:
     * AgonyResistance (decimal) - Agony Resistance
     * BoonDuration (decimal) - Concentration
     * ConditionDamage (decimal) – Condition Damage
     * ConditionDuration (decimal) - Expertise
     * CritDamage (decimal) – Ferocity
     * Healing (decimal) – Healing Power
     * Power (decimal) – Power
     * Precision (decimal) – Precision
     * Toughness (decimal) – Toughness
     * Vitality (decimal) – Vitality
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     * @param attributes (array of key/value pair object) (default/null value: {} empty array) – List of attribute bonuses. Each object may contain the following key/value pairs:
     *                   AgonyResistance (decimal) - Agony Resistance
     *                   BoonDuration (decimal) - Concentration
     *                   ConditionDamage (decimal) – Condition Damage
     *                   ConditionDuration (decimal) - Expertise
     *                   CritDamage (decimal) – Ferocity
     *                   Healing (decimal) – Healing Power
     *                   Power (decimal) – Power
     *                   Precision (decimal) – Precision
     *                   Toughness (decimal) – Toughness
     *                   Vitality (decimal) – Vitality
     */
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}

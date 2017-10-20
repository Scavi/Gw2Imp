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

package com.scavi.de.gw2imp.communication.response.items.pvp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scavi.de.gw2imp.communication.response.items.Attributes;

/**
 * Information about the PvP amulets that are in the game.
 */
public class Amulet {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;

    /**
     * @return The id of the amulet.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id of the amulet.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The name of the amulet.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name of the amulet.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The icon uri for the amulet.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The icon uri for the amulet.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return (array of key/value pairs) (default/null value: {} empty array) - The list of stats provided by this PvP amulet. May include the following:
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
     * @param attributes (array of key/value pairs) (default/null value: {} empty array) - The list of stats provided by this PvP amulet. May include the following:
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
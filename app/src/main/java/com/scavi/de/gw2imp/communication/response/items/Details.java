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

import java.util.List;

/**
 * Additional item details if applicable, depending on the item type
 */
public class Details {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("damage_type")
    @Expose
    private String damageType;
    @SerializedName("min_power")
    @Expose
    private Integer minPower;
    @SerializedName("max_power")
    @Expose
    private Integer maxPower;
    @SerializedName("defense")
    @Expose
    private Integer defense;
    @SerializedName("infusion_slots")
    @Expose
    private List<Object> infusionSlots;
    @SerializedName("infix_upgrade")
    @Expose
    private InfixUpgrade infixUpgrade;
    @SerializedName("suffix_item_id")
    @Expose
    private Integer suffixItemId;
    @SerializedName("secondary_suffix_item_id")
    @Expose
    private String secondarySuffixItemId;

    /**
     * @return The weapon type.
     * One-handed main hand: Axe, Dagger, Mace, Pistol, Scepter, Sword
     * One-handed off hand: Focus, Shield, Torch, Warhorn
     * Two-handed: Greatsword, Hammer, LongBow, Rifle, ShortBow, Staff
     * Aquatic: Harpoon, Weapon, Trident
     * Other: LargeBundle, SmallBundle, Toy, TwoHandedToy
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The weapon type.
     *             One-handed main hand: Axe, Dagger, Mace, Pistol, Scepter, Sword
     *             One-handed off hand: Focus, Shield, Torch, Warhorn
     *             Two-handed: Greatsword, Hammer, LongBow, Rifle, ShortBow, Staff
     *             Aquatic: Harpoon, Weapon, Trident
     *             Other: LargeBundle, SmallBundle, Toy, TwoHandedToy
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The damage type.
     * Fire – Fire damage
     * Ice – Ice damage
     * Lightning – Lighting damage
     * Physical – Physical damage.
     * Choking – (not used)
     */
    public String getDamageType() {
        return damageType;
    }

    /**
     * @param damageType The damage type.
     *                   Fire – Fire damage
     *                   Ice – Ice damage
     *                   Lightning – Lighting damage
     *                   Physical – Physical damage.
     *                   Choking – (not used)
     */
    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    /**
     * @return Minimum weapon strength.
     */
    public Integer getMinPower() {
        return minPower;
    }

    /**
     * @param minPower Minimum weapon strength.
     */
    public void setMinPower(Integer minPower) {
        this.minPower = minPower;
    }

    /**
     * @return Maximum weapon strength.
     */
    public Integer getMaxPower() {
        return maxPower;
    }

    /**
     * @param maxPower Maximum weapon strength.
     */
    public void setMaxPower(Integer maxPower) {
        this.maxPower = maxPower;
    }

    /**
     * @return The defense value of the weapon (for shields).
     */
    public Integer getDefense() {
        return defense;
    }

    /**
     * @param defense The defense value of the weapon (for shields).
     */
    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    /**
     * @return Infusion slots of the weapon
     */
    public List<Object> getInfusionSlots() { // TODO object
        return infusionSlots;
    }

    /**
     * @param infusionSlots Infusion slots of the weapon
     */
    public void setInfusionSlots(List<Object> infusionSlots) { // TODO object
        this.infusionSlots = infusionSlots;
    }

    /**
     * @return The infix upgrade object
     */
    public InfixUpgrade getInfixUpgrade() {
        return infixUpgrade;
    }

    /**
     * @param infixUpgrade The infix upgrade object
     */
    public void setInfixUpgrade(InfixUpgrade infixUpgrade) {
        this.infixUpgrade = infixUpgrade;
    }

    /**
     * @return The suffix item id. This is usually a sigil.
     */
    public Integer getSuffixItemId() {
        return suffixItemId;
    }

    /**
     * @param suffixItemId The suffix item id. This is usually a sigil.
     */
    public void setSuffixItemId(Integer suffixItemId) {
        this.suffixItemId = suffixItemId;
    }

    /**
     * @return The secondary suffix item id. Equals to an empty string for all currently discovered items.
     */
    public String getSecondarySuffixItemId() {
        return secondarySuffixItemId;
    }

    /**
     * @param secondarySuffixItemId The secondary suffix item id. Equals to an empty string for all currently discovered items.
     */
    public void setSecondarySuffixItemId(String secondarySuffixItemId) {
        this.secondarySuffixItemId = secondarySuffixItemId;
    }

    // TODO stat_choices
}

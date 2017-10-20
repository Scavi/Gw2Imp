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

package com.scavi.de.gw2imp.communication.response.professions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scavi.de.gw2imp.communication.response.weapon.Weapons;

import java.util.List;

/**
 * Information about professions that are in the game.
 */
public class Profession {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("icon_big")
    @Expose
    private String iconBig;
    @SerializedName("specializations")
    @Expose
    private List<Integer> specializations = null;
    @SerializedName("weapons")
    @Expose
    private Weapons weapons;
    @SerializedName("flags")
    @Expose
    private List<String> flags = null;
    @SerializedName("skills")
    @Expose
    private List<Skill> skills = null;
    @SerializedName("training")
    @Expose
    private List<Training> training = null;

    /**
     * @return (default/null value: "") - The profession id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id (default/null value: "") - The profession id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return (default/null value: "") - The name of the profession.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name (default/null value: "") - The name of the profession.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return (default/null value: "") - The icon for the profession.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon (default/null value: "") - The icon for the profession.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return (default/null value: "") - The large icon for the profession.
     */
    public String getIconBig() {
        return iconBig;
    }

    /**
     * @param iconBig (default/null value: "") - The large icon for the profession.
     */
    public void setIconBig(String iconBig) {
        this.iconBig = iconBig;
    }

    /**
     * @return (array of integer) (default/null value: 0) - List of API:2/specializations ids.
     */
    public List<Integer> getSpecializations() {
        return specializations;
    }

    /**
     * @param specializations (array of integer) (default/null value: 0) - List of API:2/specializations ids.
     */
    public void setSpecializations(List<Integer> specializations) {
        this.specializations = specializations;
    }

    /**
     * @return (array of objects) (default/null value: {} empty array) - The list of weapons skills available for this profession. May include the following objects:
     * Axe - Axe
     * Dagger - Dagger
     * Mace - Mace
     * Pistol - Pistol
     * Sword - Sword
     * Scepter - Scepter
     * Focus - Focus
     * Shield - Shield
     * Torch - Torch
     * Warhorn - Warhorn
     * Greatsword - Greatsword
     * Hammer - Hammer
     * Longbow - Longbow
     * Rifle - Rifle
     * Shortbow - Shortbow
     * Staff - Staff
     * Speargun - Speargun
     * Spear - Spear
     * Trident - Trident
     */
    public Weapons getWeapons() {
        return weapons;
    }

    /**
     * @param weapons (array of objects) (default/null value: {} empty array) - The list of weapons skills available for this profession. May include the following objects:
     *                Axe - Axe
     *                Dagger - Dagger
     *                Mace - Mace
     *                Pistol - Pistol
     *                Sword - Sword
     *                Scepter - Scepter
     *                Focus - Focus
     *                Shield - Shield
     *                Torch - Torch
     *                Warhorn - Warhorn
     *                Greatsword - Greatsword
     *                Hammer - Hammer
     *                Longbow - Longbow
     *                Rifle - Rifle
     *                Shortbow - Shortbow
     *                Staff - Staff
     *                Speargun - Speargun
     *                Spear - Spear
     *                Trident - Trident
     */
    public void setWeapons(Weapons weapons) {
        this.weapons = weapons;
    }

    /**
     * @return Can contain the following values:
     * NoRacialSkills - This profession cannot equip racial skills.
     * NoWeaponSwap - This profession can only use one weapon set.
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * @param flags Can contain the following values:
     *              NoRacialSkills - This profession cannot equip racial skills.
     *              NoWeaponSwap - This profession can only use one weapon set.
     */
    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    /**
     * @return (default/null value: {} empty array) - The list of weapon skills objects.
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * @param skills (default/null value: {} empty array) - The list of weapon skills objects.
     */
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    /**
     * @return (array of objects) (default/null value: {} empty array)
     */
    public List<Training> getTraining() {
        return training;
    }

    /**
     * @param training (array of objects) (default/null value: {} empty array)
     */
    public void setTraining(List<Training> training) {
        this.training = training;
    }
}


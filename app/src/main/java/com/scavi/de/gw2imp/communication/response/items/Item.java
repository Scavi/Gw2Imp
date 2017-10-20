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
 * Information about items
 */
public class Item {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("rarity")
    @Expose
    private String rarity;
    @SerializedName("vendor_value")
    @Expose
    private Integer vendorValue;
    @SerializedName("default_skin")
    @Expose
    private String defaultSkin;
    @SerializedName("game_types")
    @Expose
    private List<String> gameTypes = null;
    @SerializedName("flags")
    @Expose
    private List<String> flags = null;
    @SerializedName("restrictions")
    @Expose
    private List<Object> restrictions = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("chat_link")
    @Expose
    private String chatLink;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("details")
    @Expose
    private Details details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The item description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The item description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The item type (see below). Possible values:
     * Armor – Armor
     * Back – Back item
     * Bag – Bags
     * Consumable – Consumables
     * Container – Containers
     * CraftingMaterial – Crafting materials
     * Gathering – Gathering tools
     * Gizmo – Gizmos
     * MiniPet – Miniatures
     * Tool – Salvage kits
     * Trait – Trait guides
     * Trinket – Trinkets
     * Trophy – Trophies
     * UpgradeComponent – Upgrade components
     * Weapon – Weapons
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The item type (see below). Possible values:
     *             Armor – Armor
     *             Back – Back item
     *             Bag – Bags
     *             Consumable – Consumables
     *             Container – Containers
     *             CraftingMaterial – Crafting materials
     *             Gathering – Gathering tools
     *             Gizmo – Gizmos
     *             MiniPet – Miniatures
     *             Tool – Salvage kits
     *             Trait – Trait guides
     *             Trinket – Trinkets
     *             Trophy – Trophies
     *             UpgradeComponent – Upgrade components
     *             Weapon – Weapons
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The required level.
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level The required level.
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return rarity The item rarity. Possible values:
     * Junk
     * Basic
     * Fine
     * Masterwork
     * Rare
     * Exotic
     * Ascended
     * Legendary
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * @param rarity The item rarity. Possible values:
     *               Junk
     *               Basic
     *               Fine
     *               Masterwork
     *               Rare
     *               Exotic
     *               Ascended
     *               Legendary
     */
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    /**
     * @return The value in coins when selling to a vendor. (Can be non-zero even when the item has the NoSell flag.)
     */
    public Integer getVendorValue() {
        return vendorValue;
    }

    /**
     * @param vendorValue The value in coins when selling to a vendor. (Can be non-zero even when the item has the NoSell flag.)
     */
    public void setVendorValue(Integer vendorValue) {
        this.vendorValue = vendorValue;
    }

    /**
     * @return The default skin id.
     */
    public String getDefaultSkin() {
        return defaultSkin;
    }

    /**
     * @param defaultSkin The default skin id.
     */
    public void setDefaultSkin(String defaultSkin) {
        this.defaultSkin = defaultSkin;
    }

    /**
     * @return The game types in which the item is usable. At least one game type is specified. Possible values:
     * Activity – Usable in activities
     * Dungeon – Usable in dungeons
     * Pve – Usable in general PvE
     * Pvp – Usable in PvP
     * PvpLobby – Usable in the Heart of the Mists
     * Wvw – Usable in World vs. World
     */
    public List<String> getGameTypes() {
        return gameTypes;
    }

    /**
     * @param gameTypes The game types in which the item is usable. At least one game type is specified. Possible values:
     *                  Activity – Usable in activities
     *                  Dungeon – Usable in dungeons
     *                  Pve – Usable in general PvE
     *                  Pvp – Usable in PvP
     *                  PvpLobby – Usable in the Heart of the Mists
     *                  Wvw – Usable in World vs. World
     */
    public void setGameTypes(List<String> gameTypes) {
        this.gameTypes = gameTypes;
    }

    /**
     * @return Flags applying to the item. Possible values:
     * AccountBindOnUse – Account bound on use
     * AccountBound – Account bound on acquire
     * Attuned - If the item is Attuned
     * BulkConsume - If the item can be bulk consumed
     * DeleteWarning - If the item will prompt the player with a warning when deleting
     * HideSuffix – Hide the suffix of the upgrade component
     * Infused - If the item is infused
     * MonsterOnly
     * NoMysticForge – Not usable in the Mystic Forge
     * NoSalvage – Not salvageable
     * NoSell – Not sellable
     * NotUpgradeable – Not upgradeable
     * NoUnderwater – Not available underwater
     * SoulbindOnAcquire – Soulbound on acquire
     * SoulBindOnUse – Soulbound on use
     * Tonic - If the item is a tonic
     * Unique – Unique
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * @param flags Flags applying to the item. Possible values:
     *              AccountBindOnUse – Account bound on use
     *              AccountBound – Account bound on acquire
     *              Attuned - If the item is Attuned
     *              BulkConsume - If the item can be bulk consumed
     *              DeleteWarning - If the item will prompt the player with a warning when deleting
     *              HideSuffix – Hide the suffix of the upgrade component
     *              Infused - If the item is infused
     *              MonsterOnly
     *              NoMysticForge – Not usable in the Mystic Forge
     *              NoSalvage – Not salvageable
     *              NoSell – Not sellable
     *              NotUpgradeable – Not upgradeable
     *              NoUnderwater – Not available underwater
     *              SoulbindOnAcquire – Soulbound on acquire
     *              SoulBindOnUse – Soulbound on use
     *              Tonic - If the item is a tonic
     *              Unique – Unique
     */
    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    /**
     * @return Restrictions applied to the item. Possible values:
     * Asura
     * Charr
     * Human
     * Norn
     * Sylvari
     * Elementalist
     * Engineer
     * Guardian
     * Mesmer
     * Necromancer
     * Ranger
     * Thief
     * Warrior
     * details (object, op
     */
    public List<Object> getRestrictions() {
        return restrictions;
    }

    /**
     * @param restrictions Restrictions applied to the item. Possible values:
     *                     Asura
     *                     Charr
     *                     Human
     *                     Norn
     *                     Sylvari
     *                     Elementalist
     *                     Engineer
     *                     Guardian
     *                     Mesmer
     *                     Necromancer
     *                     Ranger
     *                     Thief
     *                     Warrior
     *                     details (object, op
     */
    public void setRestrictions(List<Object> restrictions) {
        this.restrictions = restrictions;
    }

    /**
     * @return The item id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The item id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The chat link.
     */
    public String getChatLink() {
        return chatLink;
    }

    /**
     * @param chatLink The chat link.
     */
    public void setChatLink(String chatLink) {
        this.chatLink = chatLink;
    }

    /**
     * @return The full icon URL.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The full icon URL.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
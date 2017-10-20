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
 * Information about recipes that were discovered by players in the game.
 */
public class Recipe {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("output_item_id")
    @Expose
    private Integer outputItemId;
    @SerializedName("output_item_count")
    @Expose
    private Integer outputItemCount;
    @SerializedName("min_rating")
    @Expose
    private Integer minRating;
    @SerializedName("time_to_craft_ms")
    @Expose
    private Integer timeToCraftMs;
    @SerializedName("disciplines")
    @Expose
    private List<String> disciplines = null;
    @SerializedName("flags")
    @Expose
    private List<String> flags = null;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("chat_link")
    @Expose
    private String chatLink;

    /**
     * @return The recipe type. Possible values:
     * Weapon recipes: Axe, Dagger, Focus, Greatsword, Hammer, Harpoon, LongBow, Mace, Pistol, Rifle, Scepter, Shield, ShortBow, Weapon, Staff, Sword, Torch, Trident, Warhorn
     * Armor recipes: Boots, Coat, Gloves, Helm, Leggings, Shoulders
     * Trinket recipes: Amulet, Earring, Ring
     * Food recipes: Dessert, Feast, IngredientCooking, Meal, Seasoning, Snack, Soup, Food
     * Crafting component recipes: Component, Inscription, Insignia, LegendaryComponent
     * Refinement recipes: Refinement, RefinementEctoplasm, RefinementObsidian
     * Guild recipes: GuildConsumable, GuildDecoration, GuildConsumableWvw
     * Other recipes: Backpack, Bag, Bulk, Consumable, Dye, Potion, UpgradeComponent
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The recipe type. Possible values:
     *             Weapon recipes: Axe, Dagger, Focus, Greatsword, Hammer, Harpoon, LongBow, Mace, Pistol, Rifle, Scepter, Shield, ShortBow, Weapon, Staff, Sword, Torch, Trident, Warhorn
     *             Armor recipes: Boots, Coat, Gloves, Helm, Leggings, Shoulders
     *             Trinket recipes: Amulet, Earring, Ring
     *             Food recipes: Dessert, Feast, IngredientCooking, Meal, Seasoning, Snack, Soup, Food
     *             Crafting component recipes: Component, Inscription, Insignia, LegendaryComponent
     *             Refinement recipes: Refinement, RefinementEctoplasm, RefinementObsidian
     *             Guild recipes: GuildConsumable, GuildDecoration, GuildConsumableWvw
     *             Other recipes: Backpack, Bag, Bulk, Consumable, Dye, Potion, UpgradeComponent
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The item id of the produced item
     */
    public Integer getOutputItemId() {
        return outputItemId;
    }

    /**
     * @param outputItemId The item id of the produced item
     */
    public void setOutputItemId(Integer outputItemId) {
        this.outputItemId = outputItemId;
    }

    /**
     * @return The amount of items produced.
     */
    public Integer getOutputItemCount() {
        return outputItemCount;
    }

    /**
     * @param outputItemCount The amount of items produced.
     */
    public void setOutputItemCount(Integer outputItemCount) {
        this.outputItemCount = outputItemCount;
    }

    /**
     * @return The required rating to craft the recipe.
     */
    public Integer getMinRating() {
        return minRating;
    }

    /**
     * @param minRating The required rating to craft the recipe.
     */
    public void setMinRating(Integer minRating) {
        this.minRating = minRating;
    }

    /**
     * @return The time in milliseconds it takes to craft the item.
     */
    public Integer getTimeToCraftMs() {
        return timeToCraftMs;
    }

    /**
     * @param timeToCraftMs The time in milliseconds it takes to craft the item.
     */
    public void setTimeToCraftMs(Integer timeToCraftMs) {
        this.timeToCraftMs = timeToCraftMs;
    }

    /**
     * @return The crafting disciplines that can use the recipe. Possible values:
     * Artificer
     * Armorsmith
     * Chef
     * Huntsman
     * Jeweler
     * Leatherworker
     * Tailor
     * Weaponsmith
     * Scribe
     */
    public List<String> getDisciplines() {
        return disciplines;
    }

    /**
     * @param disciplines The crafting disciplines that can use the recipe. Possible values:
     *                    Artificer
     *                    Armorsmith
     *                    Chef
     *                    Huntsman
     *                    Jeweler
     *                    Leatherworker
     *                    Tailor
     *                    Weaponsmith
     *                    Scribe
     */
    public void setDisciplines(List<String> disciplines) {
        this.disciplines = disciplines;
    }

    /**
     * @return Flags applying to the recipe. Possible values:
     * AutoLearned – Indicates that a recipe automatically unlocks upon reaching the required discipline rating.
     * LearnedFromItem – Indicates that a recipe is unlocked by consuming a recipe sheet.
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * @param flags Flags applying to the recipe. Possible values:
     *              AutoLearned – Indicates that a recipe automatically unlocks upon reaching the required discipline rating.
     *              LearnedFromItem – Indicates that a recipe is unlocked by consuming a recipe sheet.
     */
    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    /**
     * @return List of recipe ingredients.
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients List of recipe ingredients.
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * @return The recipe id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The recipe id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The chat code for the recipe.
     */
    public String getChatLink() {
        return chatLink;
    }

    /**
     * @param chatLink The chat code for the recipe.
     */
    public void setChatLink(String chatLink) {
        this.chatLink = chatLink;
    }
}

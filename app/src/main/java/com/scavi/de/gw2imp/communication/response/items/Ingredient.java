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
 * Ingredients for Recipes
 */
public class Ingredient {
    @SerializedName("item_id")
    @Expose
    private Integer itemId;
    @SerializedName("count")
    @Expose
    private Integer count;

    /**
     * @return The ingredient's item id resolvable against v2/items.
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * @param itemId The ingredient's item id resolvable against v2/items.
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * @return The quantity of this ingredient.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The quantity of this ingredient.
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}

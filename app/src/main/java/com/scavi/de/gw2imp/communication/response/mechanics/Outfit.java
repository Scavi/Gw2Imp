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

import java.util.List;

/**
 * Information about outfits
 */
public class Outfit {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("unlock_items")
    @Expose
    private List<Integer> unlockItems = null;

    /**
     * @return (default/null value: 0) - The id of the outfit.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id (default/null value: 0) - The id of the outfit.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return (default/null value: "") - The name of the outfit (this is also the outfit displayed over a character in-game.)
     */
    public String getName() {
        return name;
    }

    /**
     * @param name (default/null value: "") - The name of the outfit (this is also the outfit displayed over a character in-game.)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return (default/null value: "") - The icon for the selected outfit. (url)
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon (default/null value: "") - The icon for the selected outfit. (url)
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return An array of item id which unlock this outfit; resolvable against v2/items.
     */
    public List<Integer> getUnlockItems() {
        return unlockItems;
    }


    /**
     * @param unlockItems An array of item id which unlock this outfit; resolvable against v2/items.
     */
    public void setUnlockItems(List<Integer> unlockItems) {
        this.unlockItems = unlockItems;
    }
}

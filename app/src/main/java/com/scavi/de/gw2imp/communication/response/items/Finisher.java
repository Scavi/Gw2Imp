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
 * Information about finishers that are available in-game.
 */
public class Finisher {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("unlock_details")
    @Expose
    private String unlockDetails;
    @SerializedName("unlock_items")
    @Expose
    private List<Object> unlockItems = null;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * @return (default/null value: 0) - The id of the finisher.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id (default/null value: 0) - The id of the finisher.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return (default/null value: "") - A description explaining how to acquire the finisher.
     */
    public String getUnlockDetails() {
        return unlockDetails;
    }

    /**
     * @param unlockDetails (default/null value: "") - A description explaining how to acquire the finisher.
     */
    public void setUnlockDetails(String unlockDetails) {
        this.unlockDetails = unlockDetails;
    }

    /**
     * @return (array of numbers) (optional) - An array of item ids used to unlock the finisher. Can be resolved against v2/items
     */
    public List<Object> getUnlockItems() { // TODO integer?
        return unlockItems;
    }

    /**
     * @param unlockItems (array of numbers) (optional) - An array of item ids used to unlock the finisher. Can be resolved against v2/items
     */
    public void setUnlockItems(List<Object> unlockItems) { // TODO integer?
        this.unlockItems = unlockItems;
    }

    /**
     * @return (default/null value: 0) - The order in which the finisher appears in a list.
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * @param order (default/null value: 0) - The order in which the finisher appears in a list.
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * @return (default/null value: "") - The icon uri for the finisher.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon (default/null value: "") - The icon uri for the finisher.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return (default/null value: "") - The name of the finisher as it appears in-game.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name (default/null value: "") - The name of the finisher as it appears in-game.
     */
    public void setName(String name) {
        this.name = name;
    }
}

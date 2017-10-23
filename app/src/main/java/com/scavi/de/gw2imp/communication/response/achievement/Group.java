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
package com.scavi.de.gw2imp.communication.response.achievement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Group {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("categories")
    @Expose
    private List<Integer> categories = null;

    /**
     * @return The category's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The category's ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The category name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The category name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The category description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The category description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return A number describing where to sort this category among other the other categories
     * in its group. Lowest numbers go first, highest numbers go last.
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * @param order A number describing where to sort this category among other the other
     *              categories in its group. Lowest numbers go first, highest numbers go last.
     */
    public void setOrder(Integer order) {
        this.order = order;
    }


    /**
     * @return A URL to an image for the icon of the category.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon A URL to an image for the icon of the category.
     */
    public void setIcon(final String icon) {
        this.icon = icon;
    }

    /**
     * @return An array containing a number of achievement IDs that this category contains
     */
    public List<Integer> getCategories() {
        return categories;
    }

    /**
     * @param categories An array containing a number of achievement IDs that this category contains
     */
    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }
}

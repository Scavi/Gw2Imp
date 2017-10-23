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
package com.scavi.de.gw2imp.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

//@Entity
public class Group {
    //@PrimaryKey
    private String id;
    private String name;
    private String description;
    private Integer order;
    private byte[] icon;
    private List<Integer> categories;


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
    public byte[] getIcon() {
        return icon;
    }

    /**
     * @param icon A URL to an image for the icon of the category.
     */
    public void setIcon(final byte[] icon) {
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

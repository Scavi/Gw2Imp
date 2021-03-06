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
 * Information about materials
 */
public class Material {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("items")
    @Expose
    private List<Integer> items = null;

    /**
     * @return The category id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The category id.
     */
    public void setId(Integer id) {
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
     * @return (array of numbers) – The ids of the items in this category.
     */
    public List<Integer> getItems() {
        return items;
    }

    /**
     * @param items (array of numbers) – The ids of the items in this category.
     */
    public void setItems(List<Integer> items) {
        this.items = items;
    }
}
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

package com.scavi.de.gw2imp.communication.response.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * is resource returns the materials stored in a player's vault.
 */
public class Materials {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("count")
    @Expose
    private Integer count;

    /**
     * @return The item ID of the material.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The item ID of the material.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The material category the item belongs to. (See /v2/materials.)
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * @param category The material category the item belongs to. (See /v2/materials.)
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * @return The number of the material that is stored in the account vault.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The number of the material that is stored in the account vault.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

}

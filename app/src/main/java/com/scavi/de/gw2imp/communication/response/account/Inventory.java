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

public class Inventory {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("binding")
    @Expose
    private String binding;

    /**
     * @return The item ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The item ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The number of this item in the stack.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The number of this item in the stack.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return The scope of the inventory slot.
     */
    public String getBinding() {
        return binding;
    }

    /**
     * @param binding The scope of the inventory slot.
     */
    public void setBinding(String binding) {
        this.binding = binding;
    }
}

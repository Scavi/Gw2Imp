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

package com.scavi.de.gw2imp.communication.response.commerce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Information about a transaction
 */
public class Transaction {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("item_id")
    @Expose
    private Integer itemId;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("purchased")
    @Expose
    private String purchased;

    /**
     * @return Id of the transaction.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id Id of the transaction.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The item id.
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * @param itemId The item id.
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * @return The price in coins.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price The price in coins.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return The quantity of the item.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity The quantity of the item.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return The date of creation, using ISO-8601 standard.
     */
    public String getCreated() {
        return created;
    }

    /**
     * @param created The date of creation, using ISO-8601 standard.
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * @return The date of purchase, using ISO-8601 standard. Not shown in current second-level endpoint.
     */
    public String getPurchased() {
        return purchased;
    }

    /**
     * @param purchased The date of purchase, using ISO-8601 standard. Not shown in current second-level endpoint.
     */
    public void setPurchased(String purchased) {
        this.purchased = purchased;
    }
}



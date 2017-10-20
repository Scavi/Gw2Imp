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

public class Buy {
    @SerializedName("listings")
    @Expose
    private Integer listings;
    @SerializedName("unit_price")
    @Expose
    private Integer unitPrice;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    /**
     * @return The number of individual listings this object refers to (e.g. two players selling at the same price will end up in the same listing)
     */
    public Integer getListings() {
        return listings;
    }

    /**
     * @param listings The number of individual listings this object refers to (e.g. two players selling at the same price will end up in the same listing)
     */
    public void setListings(Integer listings) {
        this.listings = listings;
    }

    /**
     * @return The sell offer or buy order price in coins.
     */
    public Integer getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice The sell offer or buy order price in coins.
     */
    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return The amount of items being sold/bought in this listing.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity The amount of items being sold/bought in this listing.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

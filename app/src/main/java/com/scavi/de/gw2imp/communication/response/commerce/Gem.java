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
 * The current gems to coins exchange rate.
 */
public class Gem {
    @SerializedName("coins_per_gem")
    @Expose
    private Integer coinsPerGem;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    /**
     * @return The number of coins you get for a single gem.
     */
    public Integer getCoinsPerGem() {
        return coinsPerGem;
    }

    /**
     * @param coinsPerGem The number of coins you get for a single gem.
     */
    public void setCoinsPerGem(Integer coinsPerGem) {
        this.coinsPerGem = coinsPerGem;
    }

    /**
     * @return The number of coins you get for the specified quantity of gems.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity The number of coins you get for the specified quantity of gems.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}

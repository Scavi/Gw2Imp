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
 * The current aggregated buy and sell listing information from the trading post.
 */
public class Price {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("whitelisted")
    @Expose
    private Boolean whitelisted;
    @SerializedName("buys")
    @Expose
    private Buy buys;
    @SerializedName("sells")
    @Expose
    private Sell sells;

    /**
     * @return The item id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The item id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Indicates whether or not a free to play account can purchase or sell this item on the trading post.
     */
    public Boolean getWhitelisted() {
        return whitelisted;
    }

    /**
     * @param whitelisted Indicates whether or not a free to play account can purchase or sell this item on the trading post.
     */
    public void setWhitelisted(Boolean whitelisted) {
        this.whitelisted = whitelisted;
    }

    /**
     * @return Buy information.
     */
    public Buy getBuys() {
        return buys;
    }

    /**
     * @param buys Buy information.
     */
    public void setBuys(Buy buys) {
        this.buys = buys;
    }

    /**
     * @return Sell information.
     */
    public Sell getSells() {
        return sells;
    }

    /**
     * @param sells Sell information.
     */
    public void setSells(Sell sells) {
        this.sells = sells;
    }
}

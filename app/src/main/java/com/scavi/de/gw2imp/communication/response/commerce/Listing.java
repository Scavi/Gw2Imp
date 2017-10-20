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

import java.util.List;

/**
 * The trading post listing
 */
public class Listing {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("buys")
    @Expose
    private List<Buy> buys = null;
    @SerializedName("sells")
    @Expose
    private List<Sell> sells = null;

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
     * @return A list of all buy listings, ascending from lowest buy order
     */
    public List<Buy> getBuys() {
        return buys;
    }

    /**
     * @param buys A list of all buy listings, ascending from lowest buy order
     */
    public void setBuys(List<Buy> buys) {
        this.buys = buys;
    }

    /**
     * @return A list of all sell listings, ascending from lowest sell offer.
     */
    public List<Sell> getSells() {
        return sells;
    }

    /**
     * @param sells A list of all sell listings, ascending from lowest sell offer.
     */
    public void setSells(List<Sell> sells) {
        this.sells = sells;
    }

}

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
 * This resource returns a list of accepted resources for the gem exchange.
 */
public class Exchange {
    @SerializedName("exchange")
    @Expose
    private List<String> exchange;

    /**
     * @return The response is an array containing "coins", and "gems", signalizing the two subresources
     */
    public List<String> getExchange() {
        return exchange;
    }

    /**
     * @param exchange an array of strings representing dungeon path names completed since daily dungeon reset
     */
    public void setExchange(List<String> exchange) {
        this.exchange = exchange;
    }
}

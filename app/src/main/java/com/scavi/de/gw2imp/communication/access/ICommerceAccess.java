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
package com.scavi.de.gw2imp.communication.access;

import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.response.commerce.Price;

import java.io.IOException;
import java.util.List;

public interface ICommerceAccess {
    /**
     * Determine all item ids with prices. A wifi connection is mandatory. In case, no wifi
     * connection exists, the method returns an empty list
     *
     * @return all item IDs that are used for commerce.
     */
    List<Integer> getAllCommerceItemsWithWifi() throws IOException, ResponseException;


    /**
     * Determine all prices to the ids. The ids must be "," separated. A wifi connection is
     * mandatory. In case, no wifi connection exists, the method returns an empty list
     *
     * @param ids all ids of prices to select
     * @return a list with all prices to the passed IDs
     */
    List<Price> getPricesWithWifi(final String ids) throws IOException, ResponseException;
}

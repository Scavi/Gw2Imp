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
import com.scavi.de.gw2imp.communication.response.account.AccountAchievement;
import com.scavi.de.gw2imp.communication.response.items.Finisher;
import com.scavi.de.gw2imp.communication.response.items.Item;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface IItemAccess {
    /**
     * The interface to receive the finisher data to a given id.
     *
     * @param id       the id of the finisher
     * @param callback the callback to process the response
     */
    void getFinisher(final int id,
                     final Callback<Finisher> callback);


    /**
     * Makes a synchronous call to get the item to the passed ID. A wifi connection is mandatory.
     * In case, no wifi connection exists, the method returns <code>null</code>
     *
     * @param id the ID of the item
     * @return The item to the ID or <code>null</code> in case no wifi connection exists
     * (or the ID is unknown)
     */
    Item getItemWithWifi(final int id) throws IOException, ResponseException;
}

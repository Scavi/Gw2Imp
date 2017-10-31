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
import com.scavi.de.gw2imp.communication.response.misc.RaidData;

import java.io.IOException;
import java.util.List;

import retrofit2.Callback;

public interface IMiscellaneousAccess {

    /**
     * The interface to receive the raids information to the given id and process the response
     * via the callback. This interface is used for asynchronous calls.
     *
     * @param id       the id of the raid (e.g. forsaken_thicket or bastion_of_the_penitent)
     * @param callback the callback to process the asynchronous result
     */
    void getRaid(final String id,
                 final Callback<RaidData> callback);


    /**
     * The interface to receive the raids information to the given id. This interface is used for
     * synchronous calls.
     *
     * @param ids the ids of the raid (e.g. forsaken_thicket or bastion_of_the_penitent)
     * @return the result of the call
     */
    List<RaidData> getRaid(final String... ids) throws IOException, ResponseException;
}

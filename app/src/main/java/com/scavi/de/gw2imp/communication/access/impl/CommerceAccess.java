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
 *
 */
package com.scavi.de.gw2imp.communication.access.impl;

import com.scavi.de.gw2imp.communication.access.ICommerceAccess;
import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.response.commerce.Price;
import com.scavi.de.gw2imp.communication.rest.GW2CommercePlugin;
import com.scavi.de.gw2imp.util.network.IConnectivityAccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ParametersAreNonnullByDefault
public class CommerceAccess implements ICommerceAccess {
    private final GW2CommercePlugin mPlugin;
    private final IConnectivityAccess mConnectivityAccess;

    /**
     * Constructor
     *
     * @param retrofit           the retrofit adapter
     * @param connectivityAccess the network connectivity access to determine information about
     *                           network connection
     */
    public CommerceAccess(final Retrofit retrofit,
                          final IConnectivityAccess connectivityAccess) {
        mPlugin = retrofit.create(GW2CommercePlugin.class);
        mConnectivityAccess = connectivityAccess;
    }


    /**
     * Calls the server side synchronous to determine all item ids with prices. A wifi connection
     * is mandatory. In case, no wifi connection exists, the method returns an empty list
     *
     * @return all item IDs that are used for commerce.
     */
    @Override
    public List<Integer> getAllCommerceItemsWithWifi() throws IOException, ResponseException {
        if (!mConnectivityAccess.hasWifiConnection()) {
            return new ArrayList<>();
        }
        Call<List<Integer>> call = mPlugin.getAllItemIdsWithPrices();
        Response<List<Integer>> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new ResponseException(response.code(), response.errorBody());
        }
    }


    /**
     * Calls the server side synchronous to determine all prices to the ids. The ids must be ","
     * separated. A wifi connection is mandatory. In case, no wifi connection exists, the method
     * returns an empty list
     *
     * @param ids all ids of prices to select
     * @return a list with all prices to the passed IDs
     */
    @Override
    public List<Price> getPricesWithWifi(final String ids) throws IOException, ResponseException {
        if (!mConnectivityAccess.hasWifiConnection()) {
            return new ArrayList<>();
        }
        Call<List<Price>> call = mPlugin.getPrices(ids);
        Response<List<Price>> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new ResponseException(response.code(), response.errorBody());
        }
    }
}



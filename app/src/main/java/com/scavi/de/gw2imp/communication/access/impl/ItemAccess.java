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
package com.scavi.de.gw2imp.communication.access.impl;

import com.scavi.androidimp.env.android.IConnectivityAccess;
import com.scavi.de.gw2imp.communication.access.IItemAccess;
import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.response.items.Finisher;
import com.scavi.de.gw2imp.communication.response.items.Item;
import com.scavi.de.gw2imp.communication.rest.Gw2ApiItemPlugin;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.annotation.ParametersAreNonnullByDefault;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@ParametersAreNonnullByDefault
public class ItemAccess implements IItemAccess {
    private final Gw2ApiItemPlugin mGw2Plugin;
    private final IConnectivityAccess mConnectivityAccess;

    /**
     * Constructor
     *
     * @param retrofit           the retrofit adapter
     * @param connectivityAccess the network connectivity access to determine information about
     *                           network connection
     */
    public ItemAccess(final Retrofit retrofit,
                      final IConnectivityAccess connectivityAccess) {
        this.mGw2Plugin = retrofit.create(Gw2ApiItemPlugin.class);
        this.mConnectivityAccess = connectivityAccess;
    }


    /**
     * Calls the server side asynchronous to determine the finisher to the given id.
     *
     * @param id       the id of the finisher
     * @param callback the callback to process the response
     */
    @Override
    public void getFinisher(final int id,
                            final Callback<Finisher> callback) {
        Call<Finisher> call = mGw2Plugin.getFinisher(id);
        call.enqueue(callback);
    }


    /**
     * Makes a synchronous call to get the item to the passed ID. A wifi connection is mandatory.
     * In case, no wifi connection exists, the method returns <code>null</code>
     *
     * @param id the ID of the item
     * @return The item to the ID or <code>null</code> in case no wifi connection exists
     * (or the ID is unknown) or if the item id doesn't exists on server side (inconsistency on
     * server side)
     */
    @Override
    public Item getItemWithWifi(final int id) throws IOException, ResponseException {
        if (!mConnectivityAccess.hasWifiConnection()) {
            return null;
        }
        Call<Item> call = mGw2Plugin.getItem(id);
        Response<Item> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else if (response.code() == HttpURLConnection.HTTP_NOT_FOUND) {
            return null;
        } else {
            throw new ResponseException(response.code(), response.errorBody());
        }
    }
}

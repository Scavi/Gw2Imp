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

import com.scavi.de.gw2imp.communication.access.IItemAccess;
import com.scavi.de.gw2imp.communication.response.items.Finisher;
import com.scavi.de.gw2imp.communication.rest.Gw2ApiV2AccountPlugin;
import com.scavi.de.gw2imp.communication.rest.Gw2ApiV2ItemPlugin;

import javax.annotation.ParametersAreNonnullByDefault;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

@ParametersAreNonnullByDefault
public class ItemAccess implements IItemAccess {
    private final Gw2ApiV2ItemPlugin mGw2Plugin;

    /**
     * Constructor
     *
     * @param retrofit the retrofit adapter
     */
    public ItemAccess(final Retrofit retrofit) {
        this.mGw2Plugin = retrofit.create(Gw2ApiV2ItemPlugin.class);
    }

    /**
     * Calls the server side asynchronous to determine the finisher to the given id.
     *
     * @param id       the id of the finisher
     * @param callback the callback to process the response
     */
    @Override
    public void callGetFinisher(final int id, final Callback<Finisher> callback) {
        Call<Finisher> call = mGw2Plugin.getFinisher(id);
        call.enqueue(callback);
    }
}

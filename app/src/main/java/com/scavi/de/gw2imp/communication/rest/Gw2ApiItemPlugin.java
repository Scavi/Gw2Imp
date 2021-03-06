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
package com.scavi.de.gw2imp.communication.rest;

import com.scavi.de.gw2imp.communication.response.items.Finisher;
import com.scavi.de.gw2imp.communication.response.items.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Gw2ApiItemPlugin {
    @GET("v2/finishers/{id}")
    Call<Finisher> getFinisher(@Path("id") final int id);


    @GET("v2/finishers/{ids}")
    Call<List<Finisher>> getFinishers(@Path("ids") final String ids);


    @GET("v2/items/{id}?lang=de")
    Call<Item> getItem(@Path("id") final int id);
}
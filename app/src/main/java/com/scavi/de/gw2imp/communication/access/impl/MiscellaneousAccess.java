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

import com.scavi.de.gw2imp.communication.access.IMiscellaneousAccess;
import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.response.misc.RaidData;
import com.scavi.de.gw2imp.communication.rest.Gw2ApiMiscellaneousPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@ParametersAreNonnullByDefault
public class MiscellaneousAccess implements IMiscellaneousAccess {
    private final Gw2ApiMiscellaneousPlugin mPlugin;

    /**
     * Constructor
     *
     * @param retrofit the retrofit adapter
     */
    public MiscellaneousAccess(final Retrofit retrofit) {
        mPlugin = retrofit.create(Gw2ApiMiscellaneousPlugin.class);
    }


    /**
     * Calls the server side asynchronous to determine the raid information to the given id.
     *
     * @param id       the id of the raid (e.g. forsaken_thicket or bastion_of_the_penitent)
     * @param callback the callback to process the asynchronous result
     */
    public void getRaid(final String id,
                        final Callback<RaidData> callback) {
        Call<RaidData> call = mPlugin.getRaids(id);
        call.enqueue(callback);
    }


    /**
     * Calls the server side synchronous to determine the raid information to the given id.
     *
     * @param ids the ids of the raid (e.g. forsaken_thicket or bastion_of_the_penitent)
     * @return the result of the call
     */
    public List<RaidData> getRaid(final String... ids) throws IOException, ResponseException {
        List<RaidData> result = new ArrayList<>(ids.length);
        for (int i = 0; i < ids.length; ++i) {
            Call<RaidData> call = mPlugin.getRaids(ids[i]);
            Response<RaidData> response = call.execute();
            if (response.isSuccessful()) {
                result.add(response.body());
            } else {
                throw new ResponseException(response.code(), response.errorBody());
            }
        }
        return result;
    }

//    /**
//     * Creates an {@link Observable} to access the raid data
//     *
//     * @param id the id of the raid (e.g. forsaken_thicket or bastion_of_the_penitent)
//     * @return the observable for the raids call
//     */
//    @Override
//    public Observable<RaidData> getRaidObservable(final String id) {
//        Observable<RaidData> observable = mPlugin.getRaidObserver(id);
//        observable.observeOn(Schedulers.newThread());
//        observable.subscribeOn(Schedulers.io());
//        return observable;
//    }
//
//
//    /**
//     * Calls the server side asynchronous to determine the raid information to the given id
// (level).
//     *
//     * @param onNext      the consumer to process the result (in case http code 4xx / 5xx count
//     *                    also as a result)
//     * @param onException the consumer to process an exception
//     */
//    @Override
//    public void getRaid(final Consumer<RaidData[]> onNext,
//                        final Consumer<? super Throwable> onException) {
//        Observable.zip(
//                getRaidObservable(FORSAKEN_THICKET),
//                getRaidObservable(BASTION_OF_THE_PERTINENT),
//                merge())
//                .subscribe(onNext, onException);
//    }
//
//
//    /**
//     * The merge function for both raid results
//     *
//     * @return an array of size 2 with both raids
//     */
//    @NonNull
//    private BiFunction<RaidData, RaidData, RaidData[]> merge() {
//        return new BiFunction<RaidData, RaidData, RaidData[]>() {
//            @Override
//            public RaidData[] apply(@NonNull final RaidData raidData1,
//                                    @NonNull final RaidData raidData2) throws Exception {
//                return new RaidData[]{raidData1, raidData2};
//            }
//        };
//    }
}

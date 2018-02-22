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
package com.scavi.de.gw2imp.dagger2.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.scavi.androidimp.env.android.IConnectivityAccess;
import com.scavi.androidimp.env.android.INotificationManagerAccess;
import com.scavi.androidimp.env.android.NetworkConnectivityAccess;
import com.scavi.androidimp.env.android.NotificationManagerAccessImpl;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.async.ExecutorAccess;
import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.communication.access.IAccountAccess;
import com.scavi.de.gw2imp.communication.access.IAchievementAccess;
import com.scavi.de.gw2imp.communication.access.ICommerceAccess;
import com.scavi.de.gw2imp.communication.access.IItemAccess;
import com.scavi.de.gw2imp.communication.access.IMiscellaneousAccess;
import com.scavi.de.gw2imp.communication.access.impl.AccountAccess;
import com.scavi.de.gw2imp.communication.access.impl.AchievementsAccess;
import com.scavi.de.gw2imp.communication.access.impl.CommerceAccess;
import com.scavi.de.gw2imp.communication.access.impl.ItemAccess;
import com.scavi.de.gw2imp.communication.access.impl.MiscellaneousAccess;
import com.scavi.de.gw2imp.communication.interceptor.ApiKeyInterceptor;
import com.scavi.de.gw2imp.data.db.Gw2ImpDatabase;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.preferences.IPreferenceAccess;
import com.scavi.de.gw2imp.preferences.PreferenceManager;
import com.scavi.de.gw2imp.util.Const;

import java.util.concurrent.TimeUnit;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@ParametersAreNonnullByDefault
public class ApplicationModule {
    private final IApplication mApplication;

    /**
     * Constructor
     *
     * @param application application the application
     */
    public ApplicationModule(final IApplication application) {
        this.mApplication = application;
    }

    /**
     * @return the application
     */
    @Provides
    @Singleton
    IApplication provideApplication() {
        return mApplication;
    }


    /**
     * @return the application context
     */
    @Provides
    @Singleton
    public Context provideContext() {
        return (Application) mApplication;
    }


    /**
     * @param retrofitAdapter the retrofit2 adapter to access the REST API
     * @return the server side access the account data
     */
    @Provides
    @Singleton
    IAccountAccess provideAccountAccess(final Retrofit retrofitAdapter) {
        return new AccountAccess(retrofitAdapter);
    }


    /**
     * @param retrofitAdapter the retrofit2 adapter to access the REST API
     * @return the server side access to the achievement data
     */
    @Provides
    @Singleton
    IAchievementAccess provideAchievementAccess(final Retrofit retrofitAdapter) {
        return new AchievementsAccess(retrofitAdapter);
    }


    /**
     * @param retrofitAdapter    the retrofit2 adapter to access the REST API
     * @param connectivityAccess the network connectivity access to determine information about
     *                           network connection
     * @return the server side access to the item data
     */
    @Provides
    @Singleton
    IItemAccess provideItemAccess(final Retrofit retrofitAdapter,
                                  final IConnectivityAccess connectivityAccess) {
        return new ItemAccess(retrofitAdapter, connectivityAccess);
    }


    /**
     * @param retrofitAdapter the retrofit2 adapter to access the REST API
     * @return the server side access to the misc information (e.g. raid)
     */
    @Provides
    @Singleton
    IMiscellaneousAccess provideMiscAccess(final Retrofit retrofitAdapter) {
        return new MiscellaneousAccess(retrofitAdapter);
    }


    /**
     * @param retrofitAdapter    the retrofit2 adapter to access the REST API
     * @param connectivityAccess the network connectivity access to determine information about
     *                           network connection
     * @return the server side access to the commerce information
     */
    @Provides
    @Singleton
    ICommerceAccess provideCommerceAccess(final Retrofit retrofitAdapter,
                                          final IConnectivityAccess connectivityAccess) {
        return new CommerceAccess(retrofitAdapter, connectivityAccess);
    }


    /**
     * @return the synchronized shared preferences of this application
     */
    @Provides
    @Singleton
    IPreferenceAccess providePreferences() {
        return PreferenceManager.getInstance();
    }


    /**
     * @param context          the context to global information about the application environment
     * @param preferenceAccess the synchronized shared preferences of this application
     * @return the retrofit2 adapter to access the REST API
     */
    @Provides
    @Singleton
    Retrofit provideRetrofitAdapter(final Context context,
                                    final IPreferenceAccess preferenceAccess) {
        OkHttpClient httpClient = createOkHttpClientBuilder(context, preferenceAccess);
        // set gson. Make sure that malformed JSON will be accepted
        Gson gson = new GsonBuilder().setLenient().create();
        // get the base url of the application and initiate the retrofit client
        return new Retrofit.Builder().baseUrl(Const.GW2_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient).build();
    }


    /**
     * @param context the context to global information about the application environment
     * @return the database of this application
     */
    @Provides
    @Singleton
    IDatabaseAccess provideDatabase(final Context context) {
        return Room.databaseBuilder(context, Gw2ImpDatabase.class, "gw2-imp-treasure")
                .fallbackToDestructiveMigration()
                .build();
    }


    /**
     * @return the access to the main / background executor
     */
    @Provides
    @Singleton
    IExecutorAccess provideExecutorAccess() {
        return new ExecutorAccess();
    }


    /**
     * @param context the context to global information about the application environment
     * @return the network connectivity access to determine information about network connection
     */
    @Provides
    @Singleton
    IConnectivityAccess provideNetworkConnectivityAccess(final Context context) {
        return new NetworkConnectivityAccess(context);
    }


    /**
     * @param context the context to global information about the application environment
     * @return the notification manager access
     */
    @Provides
    @Singleton
    INotificationManagerAccess provideNotificationManagerAccess(final Context context) {
        return new NotificationManagerAccessImpl(context);
    }


    /**
     * Creates the http client with all interceptors and logging components
     *
     * @param context          the context to global information about the application environment
     * @param preferenceAccess the synchronized shared preferences of this application
     * @return the {@link OkHttpClient}
     */
    private OkHttpClient createOkHttpClientBuilder(final Context context,
                                                   final IPreferenceAccess preferenceAccess) {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ApiKeyInterceptor(context, preferenceAccess));
        //builder.addInterceptor(logging);
        builder.readTimeout(Const.SERVER_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(Const.SERVER_READ_TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }
}

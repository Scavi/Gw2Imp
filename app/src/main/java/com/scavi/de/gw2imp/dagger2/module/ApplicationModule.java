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
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.communication.access.IAccountAccess;
import com.scavi.de.gw2imp.communication.access.impl.AccountAccess;
import com.scavi.de.gw2imp.communication.interceptor.ApiKeyInterceptor;
import com.scavi.de.gw2imp.data.preferences.IPreferenceAccess;
import com.scavi.de.gw2imp.data.preferences.PreferenceManager;
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
    public IApplication provideApplication() {
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
    public IAccountAccess provideAccountAccess(final Retrofit retrofitAdapter) {
        return new AccountAccess(retrofitAdapter);
    }

    /**
     * @return the synchronized shared preferences of this application
     */
    @Provides
    @Singleton
    public IPreferenceAccess providePreferences() {
        return PreferenceManager.getInstance();
    }


    /**
     * @param context          the context to global information about the application environment
     * @param preferenceAccess the synchronized shared preferences of this application
     * @return the retrofit2 adapter to access the REST API
     */
    @Provides
    @Singleton
    public Retrofit provideRetrofitAdapter(final Context context,
                                           final IPreferenceAccess preferenceAccess) {
        OkHttpClient httpClient = createOkHttpClientBuilder(context, preferenceAccess);
        // set gson. Make sure that malformed JSON will be accepted
        Gson gson = new GsonBuilder().setLenient().create();
        // get the base url of the application and initiate the retrofit client
        return new Retrofit.Builder().baseUrl(Const.GW2_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient).build();
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
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ApiKeyInterceptor(context, preferenceAccess));
        builder.readTimeout(Const.SERVER_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(Const.SERVER_READ_TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }
}

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
package com.scavi.de.gw2imp.dagger2.component;

import android.content.Context;

import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.communication.access.IAccountAccess;
import com.scavi.de.gw2imp.communication.access.impl.AccountAccess;
import com.scavi.de.gw2imp.dagger2.module.ApplicationModule;
import com.scavi.de.gw2imp.data.preferences.IPreferenceAccess;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    /**
     * @return the application
     */
    IApplication getApplication();


    /**
     * @return the application context
     */
    Context getContext();


    /**
     * @return the retrofit2 adapter to access the REST API
     */
    Retrofit getRetrofitAdapter();


    /**
     * @return the server side access the account data
     */
    IAccountAccess getAccountAccess();

    /**
     * @return the synchronized shared preferences of this application
     */
    IPreferenceAccess providePreferences();
}

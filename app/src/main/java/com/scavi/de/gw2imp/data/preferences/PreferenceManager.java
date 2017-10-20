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
package com.scavi.de.gw2imp.data.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.scavi.de.gw2imp.util.Const;
import com.scavi.de.gw2imp.util.RoutingState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class PreferenceManager implements IPreferenceAccess, IPreferences {
    private static final String SHARED_PREFERENCES_ACCESS = "com.scavi.de.gw2imp";
    private final Object mLock = new Object();

    private static class InstanceHandler {
        private static final PreferenceManager INSTANCE = new PreferenceManager();
    }


    /**
     * Singleton Constructor
     */
    private PreferenceManager() {
    }


    /**
     * @return the singleton instance
     */
    public static PreferenceManager getInstance() {
        return InstanceHandler.INSTANCE;
    }


    /**
     * Writes the api key synchronized into the shared preferences ({@link Activity#MODE_PRIVATE}
     *
     * @param context the context to global information about the application environment
     * @param apiKey  the api key to write
     */
    @Override
    public void writeApiKey(final Context context,
                            final String apiKey) {
        synchronized (mLock) {
            getPreferences(context).edit().putString(API_KEY, apiKey).apply();
        }
    }


    /**
     * Reads the api key synchronized from the shared preferences. If the key doesn't exist ""
     * will be returned
     *
     * @param context the context to global information about the application environment
     * @return the api key from the shared preferences or ""
     */
    @Override
    public String readApiKey(final Context context) {
        synchronized (mLock) {
            return getPreferences(context).getString(API_KEY, "");
        }
    }


    /**
     * Writes the routing state synchronized into the shared preferences
     * ({@link Activity#MODE_PRIVATE}
     *
     * @param context      the context to global information about the application environment
     * @param routingState the current routing state
     */
    @Override
    public void writeRoutingState(final Context context,
                                  final RoutingState routingState) {
        synchronized (mLock) {
            getPreferences(context).edit().putInt(ROUTING_STATE, routingState.getValue()).apply();
        }
    }

    /**
     * Reads the current routing state from the shared preferences.
     *
     * @param context the context to global information about the application environment
     * @return the current routing state. In case, the value is not  set,
     * {@link RoutingState#ApiKeyRegistration} will be returned
     */
    @Override
    public RoutingState readRoutingState(final Context context) {
        int state = getPreferences(context).getInt(ROUTING_STATE, RoutingState.ApiKeyRegistration
                .getValue());
        return RoutingState.valueOf(state);
    }


    /**
     * @param context the context to global information about the application environment
     * @return the shared preferences for this application
     */
    private SharedPreferences getPreferences(final Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_ACCESS, Activity.MODE_PRIVATE);
    }
}

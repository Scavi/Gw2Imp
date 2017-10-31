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
package com.scavi.de.gw2imp.model;

import android.content.Context;

import com.google.common.base.Strings;
import com.scavi.de.gw2imp.preferences.IPreferenceAccess;
import com.scavi.de.gw2imp.util.RoutingState;

public class MainModel {
    private final Context mContext;
    private final IPreferenceAccess mPreferenceAccess;

    /**
     * Constructor
     *
     * @param context          the context to global information about the application environment
     * @param preferenceAccess access to the shared preferences of this application
     */
    public MainModel(final Context context,
                     final IPreferenceAccess preferenceAccess) {
        mContext = context;
        mPreferenceAccess = preferenceAccess;
    }


    /**
     * Persists the routing state depending on the entered api key
     */
    public void persistRoutingState() {
        String apiKey = mPreferenceAccess.readApiKey(mContext);
        if (Strings.isNullOrEmpty(apiKey)) {
            return;
        }
        mPreferenceAccess.writeRoutingState(mContext, RoutingState.MainApplication);
    }
}

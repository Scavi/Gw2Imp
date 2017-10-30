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
package com.scavi.de.gw2imp.communication.interceptor;

import android.content.Context;

import com.scavi.de.gw2imp.preferences.IPreferenceAccess;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@ParametersAreNonnullByDefault
public class ApiKeyInterceptor extends AbstractTokenInterceptor implements Interceptor {
    private final Context mContext;
    private final IPreferenceAccess mPreferenceAccess;

    /**
     * Constructor
     *
     * @param context          the context to global information about the application environment
     * @param preferenceAccess the access to the preferences
     */
    public ApiKeyInterceptor(final Context context,
                             final IPreferenceAccess preferenceAccess) {
        this.mContext = context;
        mPreferenceAccess = preferenceAccess;
    }

    /**
     * Adds the api token (if available) as an authentication header.
     */
    @Nonnull
    @Override
    public Response intercept(final Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        String apiKey = mPreferenceAccess.readApiKey(mContext);
        if (!apiKey.isEmpty()) {
            setAuthenticationHeader(requestBuilder, apiKey);
        }
        return chain.proceed(requestBuilder.build());
    }
}
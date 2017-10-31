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
import android.support.annotation.StringRes;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.communication.util.HttpErrorMapping;

import java.net.HttpURLConnection;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class AbstractModel {
    final Context mContext;


    /**
     * Constructor
     *
     * @param context the context to global information about the application environment
     */
    AbstractModel(final Context context) {
        mContext = context;
    }


    /**
     * @return the error about the connection
     */
    public String getConnectionExceptionError() {
        return mContext.getString(R.string.connection_exception);
    }

    /**
     * Determines the information to the http error code. Calling the GW2 API with an invalid api
     * key caused a {@link HttpURLConnection#HTTP_BAD_REQUEST}
     *
     * @return the information to the error code
     */
    public String determineAccountHttpError(final int httpError) {
        return httpError == HttpURLConnection.HTTP_BAD_REQUEST ?
                mContext.getString(R.string.api_account_key_error) :
                HttpErrorMapping.map(mContext, httpError);
    }


    /**
     * Determines the string resource
     *
     * @param id the id of the string resource
     * @return the string
     */
    public String getString(@StringRes final int id) {
        return mContext.getString(id);
    }


    /**
     * Determines the string resource
     *
     * @param id     the id of the string resource
     * @param params the params for the string resource
     * @return the string
     */
    public String getString(@StringRes final int id,
                            final Object... params) {
        return mContext.getString(id, params);
    }
}

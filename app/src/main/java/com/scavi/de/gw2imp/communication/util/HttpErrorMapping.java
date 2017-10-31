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
package com.scavi.de.gw2imp.communication.util;

import android.content.Context;

import com.scavi.de.gw2imp.R;

import java.net.HttpURLConnection;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class HttpErrorMapping {
    /**
     * Maps the given http error code to the supported error information of this app
     *
     * @param context       the context to global information about the application environment
     * @param httpErrorCode the error code
     * @return the correlated error string
     */
    public static String map(final Context context,
                             final int httpErrorCode) {
        String message;
        switch (httpErrorCode) {
            case HttpURLConnection.HTTP_BAD_REQUEST:
                message = context.getString(R.string.http_400_bad_request);
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                message = context.getString(R.string.http_401_unauthorized);
                break;
            case HttpURLConnection.HTTP_FORBIDDEN:
                message = context.getString(R.string.http_403_forbidden);
                break;
            case HttpURLConnection.HTTP_NOT_FOUND:
                message = context.getString(R.string.http_404_not_found);
                break;
            case HttpURLConnection.HTTP_BAD_METHOD:
                message = context.getString(R.string.http_405_method_not_allowed);
                break;
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                message = context.getString(R.string.http_500_internal_server_error);
                break;
            case HttpURLConnection.HTTP_UNAVAILABLE:
                message = context.getString(R.string.http_503_service_unavailable);
                break;
            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                message = context.getString(R.string.http_504_connection_gateway_timeout);
                break;
            default:
                message = String.format(context.getString(R.string.http_code_unknown),
                        String.valueOf(httpErrorCode));
        }
        return message;
    }
}

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
package com.scavi.de.gw2imp.communication.error;

import android.support.annotation.Nullable;

import java.util.Locale;

import okhttp3.ResponseBody;

public class ResponseException extends Exception {
    private final ResponseBody mResponseBody;
    private final int mResponseCode;

    /**
     * Constructor
     *
     * @param responseCode the http response code
     * @param responseBody the response body of the call
     */
    public ResponseException(final int responseCode,
                             @Nullable final ResponseBody responseBody) {
        mResponseCode = responseCode;
        mResponseBody = responseBody;
    }


    /**
     * @return the response body
     */
    public ResponseBody getResponseBody() {
        return mResponseBody;
    }


    /**
     * @return the http response code
     */
    public int getResponseCode() {
        return mResponseCode;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "RC=%d, Body=%s", getResponseCode(), getResponseBody() != null ?
                        getResponseBody().toString() : "<null>");
    }
}

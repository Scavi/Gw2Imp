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
package com.scavi.de.gw2imp.util;

import android.support.annotation.NonNull;

public class RestHelper {
    /**
     * Private constructor to prevent access of the helper class (only static access)
     */
    private RestHelper() {

    }

    /**
     * Converts the given int-parameters to a "," separated list
     *
     * @param intParams the int params
     * @return the "," separated int param list
     */
    @NonNull
    public static String intToGetParamList(int... intParams) {
        StringBuilder paramList = new StringBuilder(intParams.length * 2);
        for (int i = 0; i < intParams.length; ++i) {
            if (i < intParams.length - 1) {
                paramList.append(intParams[i]).append(",");
            }
        }
        return paramList.toString();
    }
}

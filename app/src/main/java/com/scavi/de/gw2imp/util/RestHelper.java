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

import java.util.List;

import javax.annotation.Nonnull;

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
    public static String intToGetParamList(@Nonnull final int... intParams) {
        if (intParams.length == 0) {
            return "";
        }
        StringBuilder paramList = new StringBuilder(intParams.length * 2);
        for (final int intParam : intParams) {
            paramList.append(intParam);
            paramList.append(",");
        }
        return paramList.substring(0, paramList.length() - 1);
    }


    /**
     * Creates an int-parameter "," separated list from the given index with the given length
     *
     * @param list   the list to split
     * @param from   from the index of
     * @param length the length of the new temp array
     * @return the "," separated list of the split list
     */
    @Nonnull
    public static String splitIntToGetParamList(@Nonnull final List<Integer> list,
                                                final int from,
                                                final int length) {
        int[] temp = new int[Math.min(length, list.size() - from)];
        for (int i = 0, j = from; i < length && j < list.size(); ++i, ++j) {
            temp[i] = list.get(j);
        }
        return intToGetParamList(temp);
    }
}

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

import android.util.SparseArray;

public enum RoutingState {
    None(0), ApiKeyRegistration(10), MainApplication(100);
    private final int mValue;
    private static final SparseArray<RoutingState> mMap = new SparseArray<>();

    static {
        for (RoutingState state : RoutingState.values()) {
            mMap.append(state.mValue, state);
        }
    }

    /**
     * Constructor
     *
     * @param value the value of the routing target
     */
    RoutingState(final int value) {
        mValue = value;
    }

    /**
     * @return the value of the routing state
     */
    public int getValue() {
        return mValue;
    }

    /**
     * @param value the value of the routing state
     * @return the routing state or {@link RoutingState#None}
     */
    public static RoutingState valueOf(final int value) {
        return mMap.get(value, None);
    }
}

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

package com.scavi.de.gw2imp.communication.response.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountRaids {
    @Expose
    private List<String> raids;

    /**
     * Constructor
     */
    public AccountRaids() {
        this(null);
    }

    /**
     * Constructor
     *
     * @param raids an array of strings representing raid encounters completed since weekly raid
     *              reset. The names can be resolved against v2/raids for more information.
     */
    public AccountRaids(final List<String> raids) {
        this.raids = raids;
    }

    /**
     * @return an array of strings representing raid encounters completed since weekly raid reset
     * . The names can be resolved against v2/raids for more information.
     */
    public List<String> getRaids() {
        return raids;
    }

    /**
     * @param raids an array of strings representing raid encounters completed since weekly raid
     *              reset. The names can be resolved against v2/raids for more information.
     */
    public void setRaids(List<String> raids) {
        this.raids = raids;
    }
}

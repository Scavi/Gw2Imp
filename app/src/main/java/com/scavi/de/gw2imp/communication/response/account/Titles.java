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

public class Titles {
    @SerializedName("titles")
    @Expose
    private List<String> titles;

    /**
     * @return information about titles that are unlocked for an account.
     */
    public List<String> getTitles() {
        return titles;
    }

    /**
     * @param titles aninformation about titles that are unlocked for an account.
     */
    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
}

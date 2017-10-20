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

public class Minis {
    @SerializedName("minis")
    @Expose
    private List<Integer> minis;

    /**
     * @return each value being the ID of a miniature resolved via /v2/minis.
     */
    public List<Integer> getMinis() {
        return minis;
    }

    /**
     * @param minis each value being the ID of a miniature resolved via /v2/minis.
     */
    public void setDungeons(List<Integer> minis) {
        this.minis = minis;
    }
}

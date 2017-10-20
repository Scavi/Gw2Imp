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

/**
 * This resource has information about masteries that are unlocked for an account. A tallied up total of the account's mastery points can be found at v2/account/mastery/points.
 */
public class Masteries {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("level")
    @Expose
    private Integer level;

    /**
     * @return (default/null value: 0) - The id of the mastery resolvable against v2/masteries.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id (default/null value: 0) - The id of the mastery resolvable against v2/masteries.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return (default/null value: 0) - Indicates the level at which the mastery is on the account. Is a 0-indexed reference to the /v2/masteries.levels array indicating the maximum level unlocked by the user. If omitted, this mastery hasn't been started.
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level (default/null value: 0) - Indicates the level at which the mastery is on the account. Is a 0-indexed reference to the /v2/masteries.levels array indicating the maximum level unlocked by the user. If omitted, this mastery hasn't been started.
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

}

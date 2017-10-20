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

package com.scavi.de.gw2imp.communication.response.achievement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Describes the rewards given for the achievement.
 */
public class Reward {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("count")
    @Expose
    private Integer count;

    /**
     * @return The type of reward. Additional fields appear for different values of type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type of reward. Additional fields appear for different values of type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return If Item: The item ID to be rewarded. <br/>
     * If Mastery: The mastery point ID to be rewarded. <br/>
     * If Title: The title id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id If Item: The item ID to be rewarded. <br/>
     *           If Mastery: The mastery point ID to be rewarded. <br/>
     *           If Title: The title id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return If Coins: The number of Coins to be rewarded. <br/>
     * If Item: The number of id to be rewarded. <br/>
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count If Coins: The number of Coins to be rewarded. <br/>
     *              If Item: The number of id to be rewarded. <br/>
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}

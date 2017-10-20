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
 * Describes the achievement's tier.
 */
public class Tier {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("points")
    @Expose
    private Integer points;

    /**
     * @return The number of "things" (achievement-specific) that must be completed to achieve this tier.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The number of "things" (achievement-specific) that must be completed to achieve this tier.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return The amount of AP awarded for completing this tier.
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * @param points The amount of AP awarded for completing this tier.
     */
    public void setPoints(Integer points) {
        this.points = points;
    }
}
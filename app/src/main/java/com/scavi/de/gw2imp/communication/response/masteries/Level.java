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

package com.scavi.de.gw2imp.communication.response.masteries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Information about the level of the masteries
 */
public class Level {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("instruction")
    @Expose
    private String instruction;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("point_cost")
    @Expose
    private Integer pointCost;
    @SerializedName("exp_cost")
    @Expose
    private Integer expCost;

    /**
     * @return (default/null value: "") - The name for the given mastery.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name (default/null value: "") - The name for the given mastery.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return (default/null value: "") - The in game description for the given mastery.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description (default/null value: "") - The in game description for the given mastery.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return (default/null value: "") - The in game instructions for the given mastery.
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * @param instruction (default/null value: "") - The in game instructions for the given mastery.
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * @return (default/null value: "") - The icon uri for the mastery.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon (default/null value: "") - The icon uri for the mastery.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return (default/null value: 0) - The amount of mastery points required to unlock the mastery.
     */
    public Integer getPointCost() {
        return pointCost;
    }

    /**
     * @param pointCost (default/null value: 0) - The amount of mastery points required to unlock the mastery.
     */
    public void setPointCost(Integer pointCost) {
        this.pointCost = pointCost;
    }

    /**
     * @return (default/null value: 0) - The total amount of experience needed to train the given mastery level. This total is non-cumulative between levels.
     */
    public Integer getExpCost() {
        return expCost;
    }

    /**
     * @param expCost (default/null value: 0) - The total amount of experience needed to train the given mastery level. This total is non-cumulative between levels.
     */
    public void setExpCost(Integer expCost) {
        this.expCost = expCost;
    }
}

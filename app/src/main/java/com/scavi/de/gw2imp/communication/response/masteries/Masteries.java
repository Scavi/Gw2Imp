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

import java.util.List;

/**
 * information about masteries that are available in-game.
 */
public class Masteries {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("requirement")
    @Expose
    private String requirement;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("levels")
    @Expose
    private List<Level> levels = null;

    /**
     * @return (default/null value: 0) - The id of the mastery.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id (default/null value: 0) - The id of the mastery.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return (default/null value: "") - The name of the selected mastery.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name (default/null value: "") - The name of the selected mastery.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return (string) (default/null value: "") - The written out requirements to unlock the mastery track.
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * @param requirement (string) (default/null value: "") - The written out requirements to unlock the mastery track.
     */
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    /**
     * @return (default/null value: 0) - The order in which the mastery track appears in a list.
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * @param order (default/null value: 0) - The order in which the mastery track appears in a list.
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * @return (default/null value: "") - The background uri for the mastery track.
     */
    public String getBackground() {
        return background;
    }

    /**
     * @param background (default/null value: "") - The background uri for the mastery track.
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * @return (default/null value: "") - The in-game region in which the mastery track belongs.
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region (default/null value: "") - The in-game region in which the mastery track belongs.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return containing the information of each mastery level.
     * name (string) (default/null value: "") - The name for the given mastery.
     * description (string) (default/null value: "") - The in game description for the given mastery.
     * instruction (string) (default/null value: "") - The in game instructions for the given mastery.
     * icon (string) (default/null value: "") - The icon uri for the mastery.
     * point_cost (integer) (default/null value: 0) - The amount of mastery points required to unlock the mastery.
     * exp_cost (integer) (default/null value: 0) - The total amount of experience needed to train the given mastery level. This total is non-cumulative between levels.
     */
    public List<Level> getLevels() {
        return levels;
    }

    /**
     * @param levels containing the information of each mastery level.
     *               name (string) (default/null value: "") - The name for the given mastery.
     *               description (string) (default/null value: "") - The in game description for the given mastery.
     *               instruction (string) (default/null value: "") - The in game instructions for the given mastery.
     *               icon (string) (default/null value: "") - The icon uri for the mastery.
     *               point_cost (integer) (default/null value: 0) - The amount of mastery points required to unlock the mastery.
     *               exp_cost (integer) (default/null value: 0) - The total amount of experience needed to train the given mastery level. This total is non-cumulative between levels.
     */
    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }
}

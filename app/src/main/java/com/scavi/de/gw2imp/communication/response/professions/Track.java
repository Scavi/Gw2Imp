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

package com.scavi.de.gw2imp.communication.response.professions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * List of skills and traits training details tracks objects.
 */
public class Track {
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("skill_id")
    @Expose
    private Integer skillId;
    @SerializedName("trait_id")
    @Expose
    private Integer traitId;

    /**
     * @return (default/null value: 0) - The cost to train this skill or trait.
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * @param cost (default/null value: 0) - The cost to train this skill or trait.
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     * @return string) (default/null value: "") - Inidcates whether this is a skill or trait. May include the following values:
     * Trait
     * Skill
     */
    public String getType() {
        return type;
    }

    /**
     * @param type string) (default/null value: "") - Inidcates whether this is a skill or trait. May include the following values:
     *             Trait
     *             Skill
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return (default/null value: 0) - The API:2/skills id. This field is only present if type is Skill.
     */
    public Integer getSkillId() {
        return skillId;
    }

    /**
     * @param skillId (default/null value: 0) - The API:2/skills id. This field is only present if type is Skill.
     */
    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    /**
     * @return (default/null value: 0) - The API:2/traits id. This field is only present if type is Trait.
     */
    public Integer getTraitId() {
        return traitId;
    }

    /**
     * @param traitId (default/null value: 0) - The API:2/traits id. This field is only present if type is Trait.
     */
    public void setTraitId(Integer traitId) {
        this.traitId = traitId;
    }
}
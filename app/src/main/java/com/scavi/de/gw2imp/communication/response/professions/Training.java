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

import java.util.List;

/**
 * List of training details objects.
 */
public class Training {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("track")
    @Expose
    private List<Track> track = null;

    /**
     * @return (default/null value: 0) - The id of the API:2/skills or API:2/specializations inidcated by the category.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id (default/null value: 0) - The id of the API:2/skills or API:2/specializations inidcated by the category.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return (default/null value: "") - The category for the training object. May include the following values:
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category (default/null value: "") - The category for the training object. May include the following values:
     *                 Skills - API:2/skills.
     *                 Specializations - API:2/specializations.
     *                 EliteSpecializations - Elite Specializations API:2/specializations.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return (default/null value: "") - The name of the skill or specialization inidcated by the category and id.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name (default/null value: "") - The name of the skill or specialization inidcated by the category and id.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return efault/null value: {} empty array) - List of skills and traits training details tracks objects. Includes the following objects:
     * cost (integer) (default/null value: 0) - The cost to train this skill or trait.
     * type (string) (default/null value: "") - Inidcates whether this is a skill or trait. May include the following values:
     * Trait
     * Skill
     * skill_id (integer) (default/null value: 0) - This field is only present if type is Skill.
     * trait_id (integer) (default/null value: 0) - This field is only present if type is Trait.
     */
    public List<Track> getTrack() {
        return track;
    }

    /**
     * @param track efault/null value: {} empty array) - List of skills and traits training details tracks objects. Includes the following objects:
     *              cost (integer) (default/null value: 0) - The cost to train this skill or trait.
     *              type (string) (default/null value: "") - Inidcates whether this is a skill or trait. May include the following values:
     *              Trait
     *              Skill
     *              skill_id (integer) (default/null value: 0) - This field is only present if type is Skill.
     *              trait_id (integer) (default/null value: 0) - This field is only present if type is Trait.
     */
    public void setTrack(List<Track> track) {
        this.track = track;
    }
}

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

package com.scavi.de.gw2imp.communication.response.pets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This resource returns information about the Ranger pets that are in the game.
 */
public class Pet {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("skills")
    @Expose
    private List<Skill> skills;

    /**
     * @return The id of the pet.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id of the pet.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The name of the pet.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name of the pet.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return (default/null value: "") - The description of the pet.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description (default/null value: "") - The description of the pet.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The icon uri for the pet. (url)
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The icon uri for the pet. (url)
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return array of skills of the pet
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * @param skills array of skills of the pet
     */
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}

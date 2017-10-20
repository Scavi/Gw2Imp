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
 * The list of weapon skills objects.
 */
public class Skill {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("slot")
    @Expose
    private String slot;
    @SerializedName("offhand")
    @Expose
    private String offhand;
    @SerializedName("attunement")
    @Expose
    private String attunement;
    @SerializedName("source")
    @Expose
    private String source;

    /**
     * @return (default/null value: 0) - The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id (default/null value: 0) - The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return (default/null value: "") - The skill bar slot that this weapon skill can be used in. May include the following values:
     * Profession_1
     * Utility
     * Heal
     * Elite
     */
    public String getSlot() {
        return slot;
    }

    /**
     * @param slot (default/null value: "") - The skill bar slot that this weapon skill can be used in. May include the following values:
     *             Profession_1
     *             Utility
     *             Heal
     *             Elite
     */
    public void setSlot(String slot) {
        this.slot = slot;
    }

    /**
     * @return (default/null value: "") - The name of the offhand weapon this skill requires to be equipped. This field is usually only present for Thief skills.
     */
    public String getOffhand() {
        return offhand;
    }

    /**
     * @param offhand (default/null value: "") - The name of the offhand weapon this skill requires to be equipped. This field is usually only present for Thief skills.
     */
    public void setOffhand(String offhand) {
        this.offhand = offhand;
    }

    /**
     * @return (default/null value: "") - The Elementalist attunement that this skill requires. This field is usually only present for Elementalist skills.
     */
    public String getAttunement() {
        return attunement;
    }

    /**
     * @param attunement (default/null value: "") - The Elementalist attunement that this skill requires. This field is usually only present for Elementalist skills.
     */
    public void setAttunement(String attunement) {
        this.attunement = attunement;
    }

    /**
     * @return (default/null value: "") - The name of the class the skill was stolen from. This only applies to thief stolen skills.
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source (default/null value: "") - The name of the class the skill was stolen from. This only applies to thief stolen skills.
     */
    public void setSource(String source) {
        this.source = source;
    }
}
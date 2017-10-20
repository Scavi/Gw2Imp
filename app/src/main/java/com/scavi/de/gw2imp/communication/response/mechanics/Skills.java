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

package com.scavi.de.gw2imp.communication.response.mechanics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Information about sikills. A skill is an ability which is activated by the player to perform an action. It is through skills that a character is able to do damage, protect themselves and their allies, and perform actions with objects in the environment. Skill selection is one of the most significant ways a player can customize how their character plays.
 */
public class Skills {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("facts")
    @Expose
    private List<Fact> facts = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("weapon_type")
    @Expose
    private String weaponType;
    @SerializedName("professions")
    @Expose
    private List<String> professions = null;
    @SerializedName("slot")
    @Expose
    private String slot;
    @SerializedName("bundle_skills")
    @Expose
    private List<Integer> bundleSkills = null;
    @SerializedName("flags")
    @Expose
    private List<String> flags = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("chat_link")
    @Expose
    private String chatLink;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Fact> getFacts() {
        return facts;
    }

    public void setFacts(List<Fact> facts) {
        this.facts = facts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public List<String> getProfessions() {
        return professions;
    }

    public void setProfessions(List<String> professions) {
        this.professions = professions;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public List<Integer> getBundleSkills() {
        return bundleSkills;
    }

    public void setBundleSkills(List<Integer> bundleSkills) {
        this.bundleSkills = bundleSkills;
    }

    public List<String> getFlags() {
        return flags;
    }

    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChatLink() {
        return chatLink;
    }

    public void setChatLink(String chatLink) {
        this.chatLink = chatLink;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}

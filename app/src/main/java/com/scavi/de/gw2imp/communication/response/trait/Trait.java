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

package com.scavi.de.gw2imp.communication.response.trait;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scavi.de.gw2imp.communication.response.mechanics.Fact;

import java.util.List;

/**
 * Information about specific traits which are contained within specializations. Specializations are a progression mechanic available to professions, which allows characters to concentrate on different aspects of their build through various traits. Depending on the character's level, up to three specializations can be chosen, and each fully trained specialization provides 3 minor traits and the choice of 3 out of 9 major traits. Players can switch back and forth between specializations when out of combat.
 */
public class Trait {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tier")
    @Expose
    private Integer tier;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("slot")
    @Expose
    private String slot;
    @SerializedName("facts")
    @Expose
    private List<Fact> facts = null;
    @SerializedName("traited_facts")
    @Expose
    private List<TraitedFact> traitedFacts = null;
    @SerializedName("specialization")
    @Expose
    private Integer specialization;
    @SerializedName("icon")
    @Expose
    private String icon;

    /**
     * @return The trait id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The trait id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The trait's tier (Adept, Master, Grandmaster) as a value from 1-3. Elite specializations also contain a tier 0 minor trait, describing which weapon the elite specialization gains access to.
     */
    public Integer getTier() {
        return tier;
    }

    /**
     * @param tier The trait's tier (Adept, Master, Grandmaster) as a value from 1-3. Elite specializations also contain a tier 0 minor trait, describing which weapon the elite specialization gains access to.
     */
    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * @return The trait name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The trait name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The trait description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The trait description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Either Major or Minor depending on the trait's slot. Minor traits are the ones given immediately when choosing a specialization.
     */
    public String getSlot() {
        return slot;
    }

    /**
     * @param slot Either Major or Minor depending on the trait's slot. Minor traits are the ones given immediately when choosing a specialization.
     */
    public void setSlot(String slot) {
        this.slot = slot;
    }

    /**
     * @return A list of tooltip facts associated with the trait itsel
     */
    public List<Fact> getFacts() {
        return facts;
    }

    public void setFacts(List<Fact> facts) {
        this.facts = facts;
    }

    public List<TraitedFact> getTraitedFacts() {
        return traitedFacts;
    }

    public void setTraitedFacts(List<TraitedFact> traitedFacts) {
        this.traitedFacts = traitedFacts;
    }

    public Integer getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Integer specialization) {
        this.specialization = specialization;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}

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

/**
 * Additions or changes to tooltip facts where there is interplay between traits. (
 */
public class TraitedFact {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("requires_trait")
    @Expose
    private Integer requiresTrait;
    @SerializedName("target")
    @Expose
    private String target;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return Specifies which trait has to be selected in order for this fact to take effect.
     */
    public Integer getRequiresTrait() {
        return requiresTrait;
    }

    /**
     * @param requiresTrait Specifies which trait has to be selected in order for this fact to take effect.
     */
    public void setRequiresTrait(Integer requiresTrait) {
        this.requiresTrait = requiresTrait;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}

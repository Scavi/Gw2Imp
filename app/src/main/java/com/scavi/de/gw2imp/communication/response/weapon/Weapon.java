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

package com.scavi.de.gw2imp.communication.response.weapon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scavi.de.gw2imp.communication.response.professions.Skill;

import java.util.List;

/**
 *
 */
public class Weapon {

    @SerializedName("flags")
    @Expose
    private List<String> flags;
    @SerializedName("skills")
    @Expose
    private List<Skill> skills;

    public List<String> getFlags() {
        return flags;
    }

    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}

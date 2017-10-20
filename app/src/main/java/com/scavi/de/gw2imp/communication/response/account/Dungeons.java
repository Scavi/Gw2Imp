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

package com.scavi.de.gw2imp.communication.response.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * an array of strings representing dungeon path names completed since daily dungeon reset. The names can be resolved against v2/dungeons for more information.
 */
public class Dungeons {
    @SerializedName("dungeons")
    @Expose
    private List<String> dungeons;

    /**
     * @return a list of strings representing dungeon path names completed since daily dungeon reset
     */
    public List<String> getDungeons() {
        return dungeons;
    }

    /**
     * @param dungeons an array of strings representing dungeon path names completed since daily dungeon reset
     */
    public void setDungeons(List<String> dungeons) {
        this.dungeons = dungeons;
    }
}

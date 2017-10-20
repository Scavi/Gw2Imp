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

package com.scavi.de.gw2imp.communication.response.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The infix upgrade is an object
 */
public class InfixUpgrade {
    @SerializedName("attributes")
    @Expose
    private List<Attributes> attributes = null;

    /**
     * @return List of attribute bonuses.
     */
    public List<Attributes> getAttributes() {
        return attributes;
    }

    /**
     * @param attributes List of attribute bonuses.
     */
    public void setAttributes(List<Attributes> attributes) {
        this.attributes = attributes;
    }

    // TODO buff

}

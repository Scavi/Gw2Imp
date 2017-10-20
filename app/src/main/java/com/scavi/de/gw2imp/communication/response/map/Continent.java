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

package com.scavi.de.gw2imp.communication.response.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A list of continents and information about each continent
 */
public class Continent {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("continent_dims")
    @Expose
    private List<Integer> continentDims = null;
    @SerializedName("min_zoom")
    @Expose
    private Integer minZoom;
    @SerializedName("max_zoom")
    @Expose
    private Integer maxZoom;
    @SerializedName("floors")
    @Expose
    private List<Integer> floors = null;
    @SerializedName("id")
    @Expose
    private Integer id;

    /**
     * @return The name of the continent.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name of the continent.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return (array of two numbers) – The width and height dimensions of the continent.
     */
    public List<Integer> getContinentDims() {
        return continentDims;
    }

    /**
     * @param continentDims (array of two numbers) – The width and height dimensions of the continent.
     */
    public void setContinentDims(List<Integer> continentDims) {
        this.continentDims = continentDims;
    }

    /**
     * @return The minimal zoom level for use with the map tile service.
     */
    public Integer getMinZoom() {
        return minZoom;
    }

    /**
     * @param minZoom The minimal zoom level for use with the map tile service.
     */
    public void setMinZoom(Integer minZoom) {
        this.minZoom = minZoom;
    }

    /**
     * @return The maximum zoom level for use with the map tile service.
     */
    public Integer getMaxZoom() {
        return maxZoom;
    }

    /**
     * @param maxZoom The maximum zoom level for use with the map tile service.
     */
    public void setMaxZoom(Integer maxZoom) {
        this.maxZoom = maxZoom;
    }

    /**
     * @return (array of numbers) – A list of floors available for this continent.
     */
    public List<Integer> getFloors() {
        return floors;
    }

    /**
     * @param floors (array of numbers) – A list of floors available for this continent.
     */
    public void setFloors(List<Integer> floors) {
        this.floors = floors;
    }

    /**
     * @return The id of the continent.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id of the continent.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}

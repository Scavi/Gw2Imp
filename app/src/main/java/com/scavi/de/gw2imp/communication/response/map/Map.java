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
 * information about maps in the game
 */
public class Map {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("min_level")
    @Expose
    private Integer minLevel;
    @SerializedName("max_level")
    @Expose
    private Integer maxLevel;
    @SerializedName("default_floor")
    @Expose
    private Integer defaultFloor;
    @SerializedName("floors")
    @Expose
    private List<Integer> floors = null;
    @SerializedName("region_id")
    @Expose
    private Integer regionId;
    @SerializedName("region_name")
    @Expose
    private String regionName;
    @SerializedName("continent_id")
    @Expose
    private Integer continentId;
    @SerializedName("continent_name")
    @Expose
    private String continentName;
    @SerializedName("map_rect")
    @Expose
    private List<List<Integer>> mapRect = null;
    @SerializedName("continent_rect")
    @Expose
    private List<List<Integer>> continentRect = null;

    /**
     * @return The map id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The map id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The map name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The map name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The minimal level of this map.
     */
    public Integer getMinLevel() {
        return minLevel;
    }

    /**
     * @param minLevel The minimal level of this map.
     */
    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    /**
     * @return The maximum level of this map.
     */
    public Integer getMaxLevel() {
        return maxLevel;
    }

    /**
     * @param maxLevel The maximum level of this map.
     */
    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    /**
     * @return The default floor of this map.
     */
    public Integer getDefaultFloor() {
        return defaultFloor;
    }

    /**
     * @param defaultFloor The default floor of this map.
     */
    public void setDefaultFloor(Integer defaultFloor) {
        this.defaultFloor = defaultFloor;
    }

    /**
     * @return A list of available floors for this map.
     */
    public List<Integer> getFloors() {
        return floors;
    }

    /**
     * @param floors A list of available floors for this map.
     */
    public void setFloors(List<Integer> floors) {
        this.floors = floors;
    }

    /**
     * @return The id of the region this map belongs to.
     */
    public Integer getRegionId() {
        return regionId;
    }

    /**
     * @param regionId The id of the region this map belongs to.
     */
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    /**
     * @return The name of the region this map belongs to.
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @param regionName The name of the region this map belongs to.
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * @return The id of the continent this map belongs to.
     */
    public Integer getContinentId() {
        return continentId;
    }

    /**
     * @param continentId The id of the continent this map belongs to.
     */
    public void setContinentId(Integer continentId) {
        this.continentId = continentId;
    }

    /**
     * @return The name of the continent this map belongs to.
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     * @param continentName The name of the continent this map belongs to.
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    /**
     * @return The dimensions of the map, given as the coordinates of the lower-left (SW) and upper-right (NE) corners.
     */
    public List<List<Integer>> getMapRect() {
        return mapRect;
    }

    /**
     * @param mapRect The dimensions of the map, given as the coordinates of the lower-left (SW) and upper-right (NE) corners.
     */
    public void setMapRect(List<List<Integer>> mapRect) {
        this.mapRect = mapRect;
    }

    /**
     * @return The dimensions of the map within the continent coordinate system, given as the coordinates of the upper-left (NW) and lower-right (SE) corners.
     */
    public List<List<Integer>> getContinentRect() {
        return continentRect;
    }

    /**
     * @param continentRect The dimensions of the map within the continent coordinate system, given as the coordinates of the upper-left (NW) and lower-right (SE) corners.
     */
    public void setContinentRect(List<List<Integer>> continentRect) {
        this.continentRect = continentRect;
    }

}

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

/**
 * This resource returns an account's progress towards all their achievements.
 */

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountAchievement implements Comparable<AccountAchievement> {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("current")
    @Expose
    private Integer current;
    @SerializedName("max")
    @Expose
    private Integer max;
    @SerializedName("done")
    @Expose
    private Boolean done;
    @SerializedName("repeated")
    @Expose
    private Integer repeated;

    // TODO bits

    public AccountAchievement() {

    }

    /**
     * @param id The achievement id.
     */
    public AccountAchievement(final int id) {
        this.id = id;
    }


    /**
     * @return The achievement id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The achievement id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The player's current progress towards the achievement.
     */
    public Integer getCurrent() {
        return current;
    }

    /**
     * @param current The player's current progress towards the achievement.
     */
    public void setCurrent(Integer current) {
        this.current = current;
    }

    /**
     * @return The amount needed to complete the achievement. Most WvW achievements have this set
     * to -1.
     */
    public Integer getMax() {
        return max;
    }

    /**
     * @param max The amount needed to complete the achievement. Most WvW achievements have this
     *            set to -1.
     */
    public void setMax(Integer max) {
        this.max = max;
    }

    /**
     * @return Whether or not the achievement is done.
     */
    public Boolean getDone() {
        return done;
    }

    /**
     * @param done Whether or not the achievement is done.
     */
    public void setDone(Boolean done) {
        this.done = done;
    }

    /**
     * @return The number of times the achievement has been completed if the achievement is
     * repeatable.
     */
    public Integer getRepeated() {
        return repeated;
    }

    /**
     * @param repeated The number of times the achievement has been completed if the achievement
     *                 is repeatable.
     */
    public void setRepeated(Integer repeated) {
        this.repeated = repeated;
    }

    /**
     * Compares the current achievement by id with passed achievement
     *
     * @param toCompare the given id to compare
     * @return the result of the comparison of both ids
     */
    @Override
    public int compareTo(@NonNull final AccountAchievement toCompare) {
        return id.compareTo(toCompare.getId());
    }
}

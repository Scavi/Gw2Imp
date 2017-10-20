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


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This resource returns information about player accounts.
 */
public class Account {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("world")
    @Expose
    private Integer world;
    @SerializedName("guilds")
    @Expose
    private List<String> guilds;
    @SerializedName("guild_leader")
    @Expose
    private List<String> guildLeader;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("access")
    @Expose
    private List<String> access;
    @SerializedName("commander")
    @Expose
    private Boolean commander;
    @SerializedName("fractal_level")
    @Expose
    private Integer fractalLevel;
    @SerializedName("daily_ap")
    @Expose
    private Integer dailyAp;
    @SerializedName("monthly_ap")
    @Expose
    private Integer monthlyAp;
    @SerializedName("wvw_rank")
    @Expose
    private Integer wvwRank;

    /**
     * @return The unique persistent account GUID.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The unique persistent account GUID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The unique account name with numerical suffix. It is possible that the name change. Do not rely on the name, use id instead
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The unique account name with numerical suffix. It is possible that the name change. Do not rely on the name, use id instead
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The age of the account in seconds.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age The age of the account in seconds.
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return The id of the home world the account is assigned to. Can be resolved via v2/worlds.
     */
    public Integer getWorld() {
        return world;
    }

    /**
     * @param world The id of the home world the account is assigned to. Can be resolved via v2/worlds.
     */
    public void setWorld(Integer world) {
        this.world = world;
    }

    /**
     * @return A list of guilds assigned to the given account.
     */
    public List<String> getGuilds() {
        return guilds;
    }

    /**
     * @param guilds A list of guilds assigned to the given account.
     */
    public void setGuilds(List<String> guilds) {
        this.guilds = guilds;
    }

    /**
     * @return A list of guilds the account is leader of.
     */
    public List<String> getGuildLeader() {
        return guildLeader;
    }

    /**
     * @param guildLeader A list of guilds the account is leader of.
     */
    public void setGuildLeader(List<String> guildLeader) {
        this.guildLeader = guildLeader;
    }

    /**
     * @return An ISO-8601 standard timestamp of when the account was created.
     */
    public String getCreated() {
        return created;
    }

    /**
     * @param created An ISO-8601 standard timestamp of when the account was created.
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * @return A list of what content this account has access to. Possible values:
     * None – should probably never happen
     * PlayForFree – has not yet purchased the game
     * GuildWars2 – has purchased the base game
     * HeartOfThorns – has purchased Heart of Thorns
     * PathOfFire – has purchased Path of Fire
     */
    public List<String> getAccess() {
        return access;
    }

    /**
     * @param access A list of what content this account has access to. Possible values:
     *               None – should probably never happen
     *               PlayForFree – has not yet purchased the game
     *               GuildWars2 – has purchased the base game
     *               HeartOfThorns – has purchased Heart of Thorns
     *               PathOfFire – has purchased Path of Fire
     */
    public void setAccess(List<String> access) {
        this.access = access;
    }

    /**
     * @return True if the player has bought a commander tag.
     */
    public Boolean getCommander() {
        return commander;
    }

    /**
     * @param commander True if the player has bought a commander tag.
     */
    public void setCommander(Boolean commander) {
        this.commander = commander;
    }

    /**
     * @return The account's personal fractal reward level. Requires the additional progression scope.
     */
    public Integer getFractalLevel() {
        return fractalLevel;
    }

    /**
     * @param fractalLevel The account's personal fractal reward level. Requires the additional progression scope.
     */
    public void setFractalLevel(Integer fractalLevel) {
        this.fractalLevel = fractalLevel;
    }

    /**
     * @return The daily AP the account has. Requires the additional progression scope
     */
    public Integer getDailyAp() {
        return dailyAp;
    }

    /**
     * @param dailyAp The daily AP the account has. Requires the additional progression scope
     */
    public void setDailyAp(Integer dailyAp) {
        this.dailyAp = dailyAp;
    }

    /**
     * @return The monthly AP the account has. Requires the additional progression scope.
     */
    public Integer getMonthlyAp() {
        return monthlyAp;
    }

    /**
     * @param monthlyAp The monthly AP the account has. Requires the additional progression scope.
     */
    public void setMonthlyAp(Integer monthlyAp) {
        this.monthlyAp = monthlyAp;
    }

    /**
     * @return The account's personal wvw rank. Requires the additional progression scope.
     */
    public Integer getWvwRank() {
        return wvwRank;
    }

    /**
     * @param wvwRank The account's personal wvw rank. Requires the additional progression scope.
     */
    public void setWvwRank(Integer wvwRank) {
        this.wvwRank = wvwRank;
    }
}



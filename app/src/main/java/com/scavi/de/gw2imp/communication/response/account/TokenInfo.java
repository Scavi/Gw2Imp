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
 * This resource returns information about the supplied API key.
 */
public class TokenInfo {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("permissions")
    @Expose
    private List<String> permissions = null;

    /**
     * @return The API key that was requested.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The API key that was requested.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The name given to the API key by the account owner. Warning: The value of this field is not escaped and may contain valid HTML, JavaScript, other code. Handle with care.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name given to the API key by the account owner. Warning: The value of this field is not escaped and may contain valid HTML, JavaScript, other code. Handle with care.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Array of strings describing which permissions the API key has. The array can contain any of:
     * account - Grants access to the /v2/account endpoint (This permission is required for all API keys).
     * builds - Grants access to view each character's equipped specializations and gear.
     * characters - Grants access to the /v2/characters endpoint.
     * guilds - Grants access to guild info under the /v2/guild/:id/ sub-endpoints.
     * inventories - Grants access to inventories in the /v2/characters, /v2/account/bank, and /v2/account/materials endpoints.
     * progression - Grants access to achievements, dungeon unlock status, mastery point assignments, and general PvE progress.
     * pvp - Grants access to the /v2/pvp sub-endpoints. (i.e. /v2/pvp/games, /v2/pvp/stats)
     * tradingpost - Grants access to the /v2/commerce/transactions endpoint.
     * unlocks - Grants access to the /v2/account/skins and /v2/account/dyes endpoints.
     * wallet - Grants access to the /v2/account/wallet endpoint.
     */
    public List<String> getPermissions() {
        return permissions;
    }

    /**
     * @param permissions Array of strings describing which permissions the API key has. The array can contain any of:
     *                    account - Grants access to the /v2/account endpoint (This permission is required for all API keys).
     *                    builds - Grants access to view each character's equipped specializations and gear.
     *                    characters - Grants access to the /v2/characters endpoint.
     *                    guilds - Grants access to guild info under the /v2/guild/:id/ sub-endpoints.
     *                    inventories - Grants access to inventories in the /v2/characters, /v2/account/bank, and /v2/account/materials endpoints.
     *                    progression - Grants access to achievements, dungeon unlock status, mastery point assignments, and general PvE progress.
     *                    pvp - Grants access to the /v2/pvp sub-endpoints. (i.e. /v2/pvp/games, /v2/pvp/stats)
     *                    tradingpost - Grants access to the /v2/commerce/transactions endpoint.
     *                    unlocks - Grants access to the /v2/account/skins and /v2/account/dyes endpoints.
     *                    wallet - Grants access to the /v2/account/wallet endpoint.
     */
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}

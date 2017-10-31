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
package com.scavi.de.gw2imp.communication.rest;

import com.scavi.de.gw2imp.communication.response.account.Account;
import com.scavi.de.gw2imp.communication.response.account.Achievement;
import com.scavi.de.gw2imp.communication.response.account.Bank;
import com.scavi.de.gw2imp.communication.response.account.Cats;
import com.scavi.de.gw2imp.communication.response.account.Characters;
import com.scavi.de.gw2imp.communication.response.account.Dungeons;
import com.scavi.de.gw2imp.communication.response.account.Dyes;
import com.scavi.de.gw2imp.communication.response.account.Inventory;
import com.scavi.de.gw2imp.communication.response.account.Masteries;
import com.scavi.de.gw2imp.communication.response.account.Materials;
import com.scavi.de.gw2imp.communication.response.account.Minis;
import com.scavi.de.gw2imp.communication.response.account.Nodes;
import com.scavi.de.gw2imp.communication.response.account.Outfits;
import com.scavi.de.gw2imp.communication.response.account.Raids;
import com.scavi.de.gw2imp.communication.response.account.Recipts;
import com.scavi.de.gw2imp.communication.response.account.Skins;
import com.scavi.de.gw2imp.communication.response.account.Titles;
import com.scavi.de.gw2imp.communication.response.account.TokenInfo;
import com.scavi.de.gw2imp.communication.response.account.Wallet;
import com.scavi.de.gw2imp.communication.response.commerce.Transaction;
import com.scavi.de.gw2imp.communication.response.items.Finisher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Gw2ApiV2AccountPlugin {

    /**
     * @return Returns information about an account associated with an API key.
     */
    @GET("v2/account")
    Call<Account> getAccount();

    /**
     * @return Returns information about an account associated with an API key.
     */
    @GET("v2/account/achievements")
    Call<List<Achievement>> getAchievements();

    /**
     * @return Returns information about a bank associated with an API key.
     */
    @GET("v2/account/bank")
    Call<List<Bank>> getBank();

    /**
     * @return Returns information about the current daily cleared dungeons associated with an
     * API key.
     */
    @GET("v2/account/dungeons")
    Call<Dungeons> getDungeons();

    /**
     * @return Returns information about unlocked dyes associated with an API key.
     */
    @GET("v2/account/dyes")
    Call<Dyes> getDyes();

    /**
     * @return Returns information about unlocked finishers associated with an API key.
     */
    @GET("v2/account/finishers")
    Call<List<Finisher>> getFinishers();

    /**
     * @return Returns information about unlocked cats in the home instance associated with an
     * API key.
     */
    @GET("v2/account/home/cats")
    Call<Cats> getCats();

    /**
     * @return This endpoint returns an array of strings. Each string represents the name of a
     * particular node.
     */
    @GET("v2/account/home/nodes")
    Call<Nodes> getNodes();

    /**
     * @return This resource returns the shared inventory slots in an account. This endpoint is
     * only accessible with a valid API key.
     */
    @GET("v2/account/inventory")
    Call<List<Inventory>> getInventory();

    /**
     * @return This resource returns information about masteries that are unlocked for an account.
     */
    @GET("v2/account/masteries")
    Call<Masteries> getMasteries();

    /**
     * @return This resource returns the materials stored in a player's vault.
     */
    @GET("v2/account/materials")
    Call<Materials> getMaterials();

    /**
     * @return This resource returns the unlocked miniatures of the account. This endpoint is
     * only accessible with a valid API key.
     */
    @GET("v2/account/minis")
    Call<Minis> getMinis();

    /**
     * @return This resource returns information about outfits that are unlocked for an account.
     */
    @GET("v2/account/outfits")
    Call<Outfits> getOutfits();

    /**
     * @return Returns information about completed raid events between weekly resets associated
     * with an API key.
     */
    @GET("v2/account/raids")
    Call<Raids> getRaids();

    /**
     * @return This resource returns information about recipes that are unlocked for an account.
     */
    @GET("v2/account/recipes")
    Call<Recipts> getRecipes();

    /**
     * @return returns the unlocked skins of the account. This endpoint is only accessible with a
     * valid API key.
     */
    @GET("v2/account/skins")
    Call<Skins> getSkins();

    /**
     * @return This resource returns information about titles that are unlocked for an account.
     */
    @GET("v2/account/titles")
    Call<Titles> getTitles();

    /**
     * @return This resource returns the currencies of the account. This endpoint is only
     * accessible with a valid API key.
     */
    @GET("v2/account/wallet")
    Call<Wallet> getWallet();

    /**
     * @return If the endpoint is accessed without any parameters (/v2/characters), it will
     * return an array of characters by name.
     */
    @GET("v2/account/characters")
    Call<Characters> getCharacters();

    /**
     * Characters can be requested specifically via the ids parameter, or by specifying them
     * percent-encoded in the next URI component. (e.g. /v2/characters/My%20Character)
     *
     * @param name the name of the character
     * @return the character by name
     */
    @GET("v2/characters/{name}")
    Call<Character> getCharacter(@Path("name") final String name);

    /**
     * To retrieve all the characters on an account, pagination can be used. (/v2/characters?page=0)
     *
     * @param page the page number
     * @return the character by page
     */
    @GET("v2/characters?page={page}")
    Call<Character> getCharacter(@Path("page") final int page);

    /**
     * @return This resource provides access to the current buy-transactions of a player.
     */
    @GET("v2/account/commerce/transactions")
    Call<List<Transaction>> getCurrentBuyTransactions();

    /**
     * @return This resource provides access to the current sell-transactions of a player.
     */
    @GET("v2/account/commerce/transactions")
    Call<List<Transaction>> getCurrentSellTransactions();

    /**
     * @return This resource provides access to the history buy-transactions of a player.
     */
    @GET("v2/account/commerce/transactions")
    Call<List<Transaction>> getHistoryBuyTransactions();

    /**
     * @return This resource provides access to the history sell-transactions of a player.
     */
    @GET("v2/account/commerce/transactions")
    Call<List<Transaction>> getHistorySellTransactions();

    // TODO account -> pvp

    /**
     * @return This resource returns information about the supplied API key.
     */
    @GET("v2/tokeninfo")
    Call<TokenInfo> getTokenInfo();
}
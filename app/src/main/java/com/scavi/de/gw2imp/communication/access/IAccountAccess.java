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
package com.scavi.de.gw2imp.communication.access;

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

import java.io.IOException;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public interface IAccountAccess {
    /**
     * The interface to receive the account data asynchronous
     *
     * @param callback the callback to process the response
     */
    void getAccount(final Callback<Account> callback);

    /**
     * The interface to receive the account data synchronous
     *
     * @return the response
     */
    Response<Account> getAccount() throws IOException;

    /**
     * The interface to receive the account achievements information asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountAchievements(Callback<List<Achievement>> callback);


    /**
     * The interface to receive the account bank information asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountBank(Callback<List<Bank>> callback);


    /**
     * The interface to receive the account weekly done dungeons asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountDungeons(Callback<Dungeons> callback);


    /**
     * The interface to receive about information unlocked dyes asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountDyes(Callback<Dyes> callback);


    /**
     * The interface to receive information about unlocked finishers associated asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountFinishers(Callback<List<Finisher>> callback);


    /**
     * The interface to receive information asynchronous about unlocked cats in the home instance
     * associated
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountCats(Callback<Cats> callback);


    /**
     * The interface to receive information about the nodes of the account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountNodes(Callback<Nodes> callback);


    /**
     * The interface to receive information about the shared inventory slots in an account
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountInventory(Callback<List<Inventory>> callback);


    /**
     * The interface to receive information about the masteries that are unlocked for an account
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountMasteries(Callback<Masteries> callback);


    /**
     * The interface to receive information about the materials stored in a player's vault
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountMaterials(Callback<Materials> callback);


    /**
     * CThe interface to receive information about the unlocked miniatures of the account
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountMinis(Callback<Minis> callback);

    /**
     * The interface to receive information about outfits that are unlocked for an account
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountOutfits(Callback<Outfits> callback);


    /**
     * The interface to receive information about completed raid events between weekly resets
     * associated asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountRaids(Callback<Raids> callback);

    /**
     * CThe interface to receive information about recipes that are unlocked for an account
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountRecipes(Callback<Recipts> callback);


    /**
     * The interface to receive information about the unlocked skins of the account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountSkins(Callback<Skins> callback);


    /**
     * The interface to receive information about titles that are unlocked for an account
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountTitles(Callback<Titles> callback);


    /**
     * The interface to receive information about the currencies of the account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    public void getAccountWallet(Callback<Wallet> callback);


    /**
     * The interface to receive information about an array of characters by name asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    public void getAccountCharacters(Callback<Characters> callback);


    /**
     * CThe interface to receive information about a character by name asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @param name     the name of the character
     */
    public void getAccountCharacter(Callback<Character> callback,
                                    final String name);


    /**
     * The interface to receive information about all the characters on an account, pagination
     * can be used asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @param page     the page number
     */
    void getAccountCharacter(Callback<Character> callback,
                             final int page);


    /**
     * The interface to receive information about the current buy-transactions of a player
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountCurrentBuyTransactions(Callback<List<Transaction>> callback);


    /**
     * The interface to receive information about the current sell-transactions of a player
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountCurrentSellTransactions(Callback<List<Transaction>> callback);


    /**
     * The interface to receive information about the history buy-transactions of a player
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountHistoryBuyTransactions(Callback<List<Transaction>> callback);


    /**
     * The interface to receive information about the history sell-transactions of a player
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountHistorySellTransactions(Callback<List<Transaction>> callback);


    /**
     * The interface to receive information about the supplied API key asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAccountTokenInfo(Callback<TokenInfo> callback);
}

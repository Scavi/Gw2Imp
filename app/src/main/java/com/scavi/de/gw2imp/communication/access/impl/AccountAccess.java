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
package com.scavi.de.gw2imp.communication.access.impl;


import com.scavi.de.gw2imp.communication.access.IAccountAccess;
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
import com.scavi.de.gw2imp.communication.rest.Gw2ApiV2Plugin;

import java.io.IOException;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@ParametersAreNonnullByDefault
public class AccountAccess implements IAccountAccess {
    private final Gw2ApiV2Plugin mGw2Plugin;

    /**
     * Constructor
     *
     * @param retrofit the retrofit adapter
     */
    public AccountAccess(final Retrofit retrofit) {
        this.mGw2Plugin = retrofit.create(Gw2ApiV2Plugin.class);
    }

    /**
     * Calls the server side asynchronous to determine the account information asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return Returns information about an account associated
     */
    @Override
    public void getAccount(Callback<Account> callback) {
        Call<Account> call = mGw2Plugin.getAccount();
        call.enqueue(callback);
    }


    /**
     * The interface to receive the account data synchronous
     *
     * @return the response
     */
    @Override
    public Response<Account> getAccount() throws IOException {
        Call<Account> call = mGw2Plugin.getAccount();
        return call.execute();
    }

    /**
     * Calls the server side asynchronous to determine the account achievements information
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return Returns information about an account associated
     */
    @Override
    public void getAccountAchievements(Callback<List<Achievement>> callback) {
        Call<List<Achievement>> call = mGw2Plugin.getAccountAchievements();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine the account bank information asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return Returns information about a bank associated
     */
    @Override
    public void getAccountBank(Callback<List<Bank>> callback) {
        Call<List<Bank>> call = mGw2Plugin.getAccountBank();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine the account weekly done dungeons asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return Returns information about the current daily cleared dungeons associated
     */
    @Override
    public void getAccountDungeons(Callback<Dungeons> callback) {
        Call<Dungeons> call = mGw2Plugin.getAccountDungeons();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about unlocked dyes asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return Returns information about unlocked dyes associated
     */
    @Override
    public void getAccountDyes(Callback<Dyes> callback) {
        Call<Dyes> call = mGw2Plugin.getAccountDyes();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about unlocked finishers
     * associated asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return Returns information about unlocked finishers associated
     */
    @Override
    public void getAccountFinishers(Callback<List<Finisher>> callback) {
        Call<List<Finisher>> call = mGw2Plugin.getAccountFinishers();
        call.enqueue(callback);
    }

    /**
     * Calls the server side asynchronous to determine information about unlocked cats in the
     * home instance associated asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return Returns information about unlocked cats in the home instance associated
     */
    @Override
    public void getAccountCats(Callback<Cats> callback) {
        Call<Cats> call = mGw2Plugin.getAccountCats();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the nodes of the account
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This endpoint returns an array of strings. Each string represents the name of a
     * particular node.
     */
    @Override
    public void getAccountNodes(Callback<Nodes> callback) {
        Call<Nodes> call = mGw2Plugin.getAccountNodes();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the shared inventory
     * slots in an account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource returns the shared inventory slots in an account.
     */
    @Override
    public void getAccountInventory(Callback<List<Inventory>> callback) {
        Call<List<Inventory>> call = mGw2Plugin.getAccountInventory();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the masteries that are
     * unlocked for an account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource returns information about masteries that are unlocked for an account.
     */
    @Override
    public void getAccountMasteries(Callback<Masteries> callback) {
        Call<Masteries> call = mGw2Plugin.getAccountMasteries();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the materials stored in
     * a player's vault asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource returns the materials stored in a player's vault.
     */
    @Override
    public void getAccountMaterials(Callback<Materials> callback) {
        Call<Materials> call = mGw2Plugin.getAccountMaterials();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the unlocked miniatures
     * of the account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource returns the unlocked miniatures of the account.
     */
    @Override
    public void getAccountMinis(Callback<Minis> callback) {
        Call<Minis> call = mGw2Plugin.getAccountMinis();
        call.enqueue(callback);
    }

    /**
     * Calls the server side asynchronous to determine information about outfits that are
     * unlocked for an account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource returns information about outfits that are unlocked for an account.
     */
    @Override
    public void getAccountOutfits(Callback<Outfits> callback) {
        Call<Outfits> call = mGw2Plugin.getAccountOutfits();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about completed raid events
     * between weekly resets associated asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return Returns information about completed raid events between weekly resets associated
     */
    @Override
    public void getAccountRaids(Callback<Raids> callback) {
        Call<Raids> call = mGw2Plugin.getAccountRaids();
        call.enqueue(callback);
    }

    /**
     * Calls the server side asynchronous to determine information about recipes that are
     * unlocked for an account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource returns information about recipes that are unlocked for an account.
     */
    @Override
    public void getAccountRecipes(Callback<Recipts> callback) {
        Call<Recipts> call = mGw2Plugin.getAccountRecipes();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the unlocked skins of
     * the account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return the unlocked skins of the account.
     */
    @Override
    public void getAccountSkins(Callback<Skins> callback) {
        Call<Skins> call = mGw2Plugin.getAccountSkins();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about titles that are unlocked
     * for an account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource returns information about titles that are unlocked for an account.
     */
    @Override
    public void getAccountTitles(Callback<Titles> callback) {
        Call<Titles> call = mGw2Plugin.getAccountTitles();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the currencies of the
     * account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource returns the currencies of the account.
     */
    @Override
    public void getAccountWallet(Callback<Wallet> callback) {
        Call<Wallet> call = mGw2Plugin.getAccountWallet();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about an array of characters
     * by name asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return an array of characters by name.
     */
    @Override
    public void getAccountCharacters(Callback<Characters> callback) {
        Call<Characters> call = mGw2Plugin.getAccountCharacters();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about a character by name
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @param name     the name of the character
     * @return an array of characters by name.
     */
    @Override
    public void getAccountCharacter(Callback<Character> callback,
                                    final String name) {
        Call<Character> call = mGw2Plugin.getAccountCharacter(name);
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to to retrieve all the characters on an account,
     * pagination can be used asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @param page     the page number
     * @return This resource provides access to the history buy-transactions of a player.
     */
    @Override
    public void getAccountCharacter(Callback<Character> callback,
                                    final int page) {
        Call<Character> call = mGw2Plugin.getAccountCharacter(page);
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the current
     * buy-transactions of a player asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource provides access to the history buy-transactions of a player.
     */
    @Override
    public void getAccountCurrentBuyTransactions(Callback<List<Transaction>> callback) {
        Call<List<Transaction>> call = mGw2Plugin.getAccountCurrentBuyTransactions();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the current
     * sell-transactions of a player asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource provides access to the history sell-transactions of a player.
     */
    @Override
    public void getAccountCurrentSellTransactions(Callback<List<Transaction>> callback) {
        Call<List<Transaction>> call = mGw2Plugin.getAccountCurrentSellTransactions();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the history
     * buy-transactions of a player asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource provides access to the history buy-transactions of a player.
     */
    @Override
    public void getAccountHistoryBuyTransactions(Callback<List<Transaction>> callback) {
        Call<List<Transaction>> call = mGw2Plugin.getAccountHistoryBuyTransactions();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the history
     * sell-transactions of a player asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource provides access to the history sell-transactions of a player.
     */
    @Override
    public void getAccountHistorySellTransactions(Callback<List<Transaction>> callback) {
        Call<List<Transaction>> call = mGw2Plugin.getAccountHistorySellTransactions();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @return This resource returns information about the supplied API key.
     */
    @Override
    public void getAccountTokenInfo(Callback<TokenInfo> callback) {
        Call<TokenInfo> call = mGw2Plugin.getAccountTokenInfo();
        call.enqueue(callback);
    }
}

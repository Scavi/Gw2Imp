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
import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.response.account.Account;
import com.scavi.de.gw2imp.communication.response.account.AccountAchievement;
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
import com.scavi.de.gw2imp.communication.response.account.AccountRaids;
import com.scavi.de.gw2imp.communication.response.account.Recipts;
import com.scavi.de.gw2imp.communication.response.account.Skins;
import com.scavi.de.gw2imp.communication.response.account.Titles;
import com.scavi.de.gw2imp.communication.response.account.TokenInfo;
import com.scavi.de.gw2imp.communication.response.account.Wallet;
import com.scavi.de.gw2imp.communication.response.commerce.Transaction;
import com.scavi.de.gw2imp.communication.response.items.Finisher;
import com.scavi.de.gw2imp.communication.rest.Gw2ApiAccountPlugin;

import java.io.IOException;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@ParametersAreNonnullByDefault
public class AccountAccess implements IAccountAccess {
    private final Gw2ApiAccountPlugin mGw2Plugin;

    /**
     * Constructor
     *
     * @param retrofit the retrofit adapter
     */
    public AccountAccess(final Retrofit retrofit) {
        mGw2Plugin = retrofit.create(Gw2ApiAccountPlugin.class);
    }

    /**
     * Calls the server side asynchronous to determine the account information
     *
     * @param callback the callback to process the asynchronous result
     * @return Returns information about an account associated
     */
    @Override
    public void getAccount(final Callback<Account> callback) {
        Call<Account> call = mGw2Plugin.getAccount();
        call.enqueue(callback);
    }


    /**
     * The interface to receive the account data synchronous
     *
     * @return the account response
     */
    @Override
    public Response<Account> getAccount() throws IOException {
        Call<Account> call = mGw2Plugin.getAccount();
        return call.execute();
    }


    /**
     * Calls the server side asynchronous to determine the account achievements information
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getAchievements(final Callback<List<AccountAchievement>> callback) {
        Call<List<AccountAchievement>> call = mGw2Plugin.getAchievements();
        call.enqueue(callback);
    }


    /**
     * Calls the server side synchronous to determine the account achievements information
     * asynchronous
     *
     * @return the account achievement information
     */
    @Override
    public List<AccountAchievement> getAchievements() throws IOException, ResponseException {
        Call<List<AccountAchievement>> call = mGw2Plugin.getAchievements();
        Response<List<AccountAchievement>> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new ResponseException(response.code(), response.errorBody());
        }
    }

    /**
     * Calls the server side asynchronous to determine the account bank information
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getBank(final Callback<List<Bank>> callback) {
        Call<List<Bank>> call = mGw2Plugin.getBank();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine the account weekly done dungeons
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getDungeons(final Callback<Dungeons> callback) {
        Call<Dungeons> call = mGw2Plugin.getDungeons();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about unlocked dyes
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getDyes(final Callback<Dyes> callback) {
        Call<Dyes> call = mGw2Plugin.getDyes();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about unlocked finishers
     * associated asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getFinishers(final Callback<List<Finisher>> callback) {
        Call<List<Finisher>> call = mGw2Plugin.getFinishers();
        call.enqueue(callback);
    }

    /**
     * Calls the server side asynchronous to determine information about unlocked cats in the
     * home instance associated asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getCats(final Callback<Cats> callback) {
        Call<Cats> call = mGw2Plugin.getCats();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the nodes of the account
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getNodes(final Callback<Nodes> callback) {
        Call<Nodes> call = mGw2Plugin.getNodes();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the shared inventory
     * slots in an account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getInventory(final Callback<List<Inventory>> callback) {
        Call<List<Inventory>> call = mGw2Plugin.getInventory();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the masteries that are
     * unlocked for an account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getMasteries(final Callback<Masteries> callback) {
        Call<Masteries> call = mGw2Plugin.getMasteries();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the materials stored in
     * a player's vault asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getMaterials(final Callback<Materials> callback) {
        Call<Materials> call = mGw2Plugin.getMaterials();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the unlocked miniatures
     * of the account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getMinis(final Callback<Minis> callback) {
        Call<Minis> call = mGw2Plugin.getMinis();
        call.enqueue(callback);
    }

    /**
     * Calls the server side asynchronous to determine information about outfits that are
     * unlocked for an account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getOutfits(final Callback<Outfits> callback) {
        Call<Outfits> call = mGw2Plugin.getOutfits();
        call.enqueue(callback);
    }


    /**
     * The interface to receive information about completed raid events between weekly resets
     * associated synchronous
     *
     * @return the account raid data
     */
    @Override
    public AccountRaids getRaids() throws IOException, ResponseException {
        Call<List<String>> call = mGw2Plugin.getRaids();
        Response<List<String>> response = call.execute();
        if (response.isSuccessful()) {
            return new AccountRaids(response.body());
        } else {
            throw new ResponseException(response.code(), response.errorBody());
        }
    }


    /**
     * Calls the server side asynchronous to determine information about recipes that are
     * unlocked for an account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getRecipes(final Callback<Recipts> callback) {
        Call<Recipts> call = mGw2Plugin.getRecipes();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the unlocked skins of
     * the account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getSkins(final Callback<Skins> callback) {
        Call<Skins> call = mGw2Plugin.getSkins();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about titles that are unlocked
     * for an account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getTitles(final Callback<Titles> callback) {
        Call<Titles> call = mGw2Plugin.getTitles();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the currencies of the
     * account asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getWallet(final Callback<Wallet> callback) {
        Call<Wallet> call = mGw2Plugin.getWallet();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about an array of characters
     * by name asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getCharacters(Callback<Characters> callback) {
        Call<Characters> call = mGw2Plugin.getCharacters();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about a character by name
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @param name     the name of the character
     */
    @Override
    public void getCharacter(final Callback<Character> callback,
                             final String name) {
        Call<Character> call = mGw2Plugin.getCharacter(name);
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to to retrieve all the characters on an account,
     * pagination can be used asynchronous
     *
     * @param callback the callback to process the asynchronous result
     * @param page     the page number
     */
    @Override
    public void getCharacter(final Callback<Character> callback,
                             final int page) {
        Call<Character> call = mGw2Plugin.getCharacter(page);
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the current
     * buy-transactions of a player asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getCurrentBuyTransactions(final Callback<List<Transaction>> callback) {
        Call<List<Transaction>> call = mGw2Plugin.getCurrentBuyTransactions();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the current
     * sell-transactions of a player asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getCurrentSellTransactions(final Callback<List<Transaction>> callback) {
        Call<List<Transaction>> call = mGw2Plugin.getCurrentSellTransactions();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the history
     * buy-transactions of a player asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getHistoryBuyTransactions(final Callback<List<Transaction>> callback) {
        Call<List<Transaction>> call = mGw2Plugin.getHistoryBuyTransactions();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information about the history
     * sell-transactions of a player asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getHistorySellTransactions(final Callback<List<Transaction>> callback) {
        Call<List<Transaction>> call = mGw2Plugin.getHistorySellTransactions();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine information asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getTokenInfo(final Callback<TokenInfo> callback) {
        Call<TokenInfo> call = mGw2Plugin.getTokenInfo();
        call.enqueue(callback);
    }
}

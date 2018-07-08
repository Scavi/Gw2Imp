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
 *
 */
package com.scavi.de.gw2imp.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.data.entity.item.ItemEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceHistoryEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemSearchEntity;
import com.scavi.de.gw2imp.model.so.TradingItemData;
import com.scavi.de.gw2imp.preferences.IPreferenceAccess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nonnull;

public class TradingItemsModel extends AbstractModel {
    public static final int TRADING_ITEM_DELAY_MS = 500;
    private final IDatabaseAccess mImpDatabase;
    private final IExecutorAccess mExecutorAccess;
    private final IPreferenceAccess mPreferenceAccess;
    private final String WILD_CARD = "%";

    /**
     * Constructor
     *
     * @param context          the context to global information about the application environment
     * @param impDatabase      the database access of this application
     * @param executorAccess   to access the main and background threads
     * @param preferenceAccess access to the shared preferences of this application
     */
    public TradingItemsModel(final Context context,
                             final IDatabaseAccess impDatabase,
                             final IExecutorAccess executorAccess,
                             final IPreferenceAccess preferenceAccess) {
        super(context);
        mImpDatabase = impDatabase;
        mExecutorAccess = executorAccess;
        mPreferenceAccess = preferenceAccess;
    }


    /**
     * Determines the item prices and the item history prices and creates a new trading item object
     *
     * @param item the selected item
     * @return the object containing all data
     */
    @WorkerThread
    public TradingItemData determineTradingItemData(@Nonnull final ItemEntity item) {
        List<ItemPriceHistoryEntity> historyPrices = selectItemPriceHistory(item);
        List<ItemPriceEntity> prices = selectItemPrices(item);
        return new TradingItemData.Builder()
                .setItemHistoryPrices(historyPrices)
                .setItemPrices(prices)
                .setSelectedItem(item).build();
    }


    /**
     * Selects all items by name. First it tries to select by the exact name part. If no results
     * exists, it selects all part names to the pattern name%
     *
     * @param name the name of the item
     * @return all items to the given name
     */
    @WorkerThread
    public List<ItemEntity> selectItemsToName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return new ArrayList<>(0);
        }
        name = name.trim();
        List<ItemEntity> result = selectExactByNamePart(name);
        if (result == null || result.size() == 0) { // TODO nullorempty remove boilerpalte
            result = selectInaccurateByNamePart(name);
        }
        return result;
    }


    /**
     * Determines all items with the exact part name
     *
     * @param name the name of the item
     * @return all items to the name
     */
    @Nullable
    private List<ItemEntity> selectExactByNamePart(@NonNull final String name) {
        List<ItemSearchEntity> itemSearch = mImpDatabase.itemsDAO().selectAllItemsByNamePart(name);
        return mImpDatabase.itemsDAO().selectItems(ItemSearchEntity.allIdsFrom(itemSearch));
    }


    /**
     * Determines all items with the part name to the given pattern name%
     *
     * @param name the name of the item
     * @return all items to the name
     */
    private List<ItemEntity> selectInaccurateByNamePart(@NonNull String name) {
        name = name.endsWith(WILD_CARD) ? name : name + WILD_CARD;
        List<ItemSearchEntity> items = mImpDatabase.itemsDAO().selectAllItemsByNamePart(name);
        return mImpDatabase.itemsDAO().selectItems(ItemSearchEntity.allIdsFrom(items));
    }


    /**
     * @param item the item
     * @return all prices to the item
     */
    @WorkerThread
    List<ItemPriceEntity> selectItemPrices(@Nonnull final ItemEntity item) {
        return mImpDatabase.itemsDAO().selectItemPrices(item.getItemId());
    }


    /**
     * @param item the item
     * @return all history prices to the given item
     */
    @WorkerThread
    List<ItemPriceHistoryEntity> selectItemPriceHistory(@Nonnull final ItemEntity item) {
        return mImpDatabase.itemsDAO().selectItemPriceHistory(item.getItemId());
    }


    /**
     * @return <code>true</code> optimal search index to find all words
     * <code>false</code> still indexing
     */
    public boolean isSearchIndexOptimal() {
        return mPreferenceAccess.readIsWordIndexComplete(mContext);
    }

    /**
     * @return to access the main and background threads
     */
    public IExecutorAccess getExecutorAccess() {
        return mExecutorAccess;
    }
}

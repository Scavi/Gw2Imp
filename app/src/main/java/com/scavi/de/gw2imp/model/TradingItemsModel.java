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
import android.support.annotation.WorkerThread;

import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.data.entity.item.ItemEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceHistoryEntity;
import com.scavi.de.gw2imp.model.so.TradingItemData;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class TradingItemsModel extends AbstractModel {
    public static final int TRADING_ITEM_DELAY_MS = 400;
    private final IDatabaseAccess mImpDatabase;
    private final IExecutorAccess mExecutorAccess;
    private final String WILD_CARD = "%";

    /**
     * Constructor
     *
     * @param context        the context to global information about the application environment
     * @param impDatabase    the database access of this application
     * @param executorAccess to access the main and background threads
     */
    public TradingItemsModel(final Context context,
                             final IDatabaseAccess impDatabase,
                             final IExecutorAccess executorAccess) {
        super(context);
        mImpDatabase = impDatabase;
        mExecutorAccess = executorAccess;
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
     * Selects all items by name
     *
     * @param name the name
     * @return all items to the given name
     */
    @WorkerThread
    public List<ItemEntity> selectItemsToName(String name) {
        if (name == null || name.isEmpty()) {
            return new ArrayList<>(0);
        }
        name = name.trim();
        name = name.startsWith(WILD_CARD) ? name : WILD_CARD + name;
        name = name.endsWith(WILD_CARD) ? name : name + WILD_CARD;
        return mImpDatabase.itemsDAO().selectItems(name);
    }


    /**
     * @param item the item
     * @return all prices to the item
     */
    @WorkerThread
    public List<ItemPriceEntity> selectItemPrices(@Nonnull final ItemEntity item) {
        return mImpDatabase.itemsDAO().selectItemPrices(item.getItemId());
    }


    /**
     * @param item the item
     * @return all history prices to the given item
     */
    @WorkerThread
    public List<ItemPriceHistoryEntity> selectItemPriceHistory(@Nonnull final ItemEntity item) {
        return mImpDatabase.itemsDAO().selectItemPriceHistory(item.getItemId());
    }


    /**
     * @return to access the main and background threads
     */
    public IExecutorAccess getExecutorAccess() {
        return mExecutorAccess;
    }
}

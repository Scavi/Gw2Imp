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
package com.scavi.de.gw2imp.background.collector;

import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.scavi.androidimp.util.RestHelper;
import com.scavi.de.gw2imp.communication.access.ICommerceAccess;
import com.scavi.de.gw2imp.communication.access.IItemAccess;
import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.response.commerce.Price;
import com.scavi.de.gw2imp.communication.response.items.Item;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.data.entity.item.ItemEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceEntity;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemCollector extends Thread {
    private static final String TAG = "ItemPriceCollector";
    // the constant defines how many items will be processed at once (e.g. load 100 item prices)
    private static final int ITEM_PROCESS_SIZE = 100;
    // the constant defines the iteration until we wait before interact with the server again
    // while loading all prices / items
    private static final int ITEM_PROCESS_WAIT_COUNT = ITEM_PROCESS_SIZE * 10;
    private static final long ITERATION_MS = 60 * 60 * 1000;
    // the wait time (give the server time to breath ;-))
    private static final int TOO_MANY_REQUEST_DELAY_MS = 60 * 1000;
    private final IDatabaseAccess mDatabaseAccess;
    private final IItemAccess mItemAccess;
    private final ICommerceAccess mCommerceAccess;

    /**
     * Constructor
     *
     * @param databaseAccess the access to the database
     * @param itemAccess     the server side access to the general items
     * @param commerceAccess the server side access to the commerce items
     */
    public ItemCollector(final IDatabaseAccess databaseAccess,
                         final IItemAccess itemAccess,
                         final ICommerceAccess commerceAccess) {
        mDatabaseAccess = databaseAccess;
        mItemAccess = itemAccess;
        mCommerceAccess = commerceAccess;
    }


    /**
     * The purpose of this thread is to determine the price course of each item.
     * As long as the thread is not interrupted, this method initiates the loading of prices and
     * their items. Items will be loaded once. Items and prices will be stored into the local
     * database.
     * Prices of the last month will be stored in a history data set.
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            List<Integer> allCommerceItemIds = loadAllCommerceItemIds();
            loadItemsAndPrices(allCommerceItemIds);
            //updateHistoryItemPrices();
            waitMs(ITERATION_MS);
        }
    }


    /**
     * Loads the item IDs with a commerce item id context
     *
     * @return a list of all item IDs in a commerce context
     */
    private List<Integer> loadAllCommerceItemIds() {
        List<Integer> allCommerceItemIds = new LinkedList<>();
        try {
            allCommerceItemIds = mCommerceAccess.getAllCommerceItemsWithWifi();
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex); // TODO
        }
        return allCommerceItemIds;
    }


    /**
     * This method loads all prices (and their items) to the given item IDs that have a
     * commerce context. Currently there are around 25000 out of 88000 commerce items.
     * After that, the method initiates the processing of the loaded prices.
     *
     * @param allCommerceItemIds all item ids
     */
    private void loadItemsAndPrices(final List<Integer> allCommerceItemIds) {
        if (allCommerceItemIds.size() == 0) {
            return;
        }
        int pos = 0;
        // initiates the loading of items and their price via REST. Since each item must
        // be loaded in a single request, this loop loads the items in chunks (otherwise
        // we might have too many requests at once)
        while (pos < allCommerceItemIds.size()) {
            try {
                Log.i(TAG, "loaded items: " + mDatabaseAccess.itemsDAO().selectItemCount());
                Log.i(TAG, "loaded item prices: " +
                        mDatabaseAccess.itemsDAO().selectItemPriceCount());
                // creates a "," separated list of item IDs for the GET parameter
                String ids = RestHelper.splitIntToGetParamList(allCommerceItemIds,
                        pos, ITEM_PROCESS_SIZE);
                // all prices to the "," separated ID list.
                List<Price> prices = mCommerceAccess.getPricesWithWifi(ids);

                processPrices(prices);
                pos += ITEM_PROCESS_SIZE;

                // to prevent too many request at once, wait every 1000 processed items
                if (pos % ITEM_PROCESS_WAIT_COUNT == 0) {
                    waitMs(TOO_MANY_REQUEST_DELAY_MS);
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.getMessage(), ex); // TODO
            }
        }
    }

    /**
     * Verifies, if the general item information (id, name, icon) already exists. If not, the item
     * information will be loaded. After that, the price will be inserted into the database.
     * Since the item prices will be loaded via REST GET, the commercial item IDs must be split into
     * groups (<code>ITEM_PROCESS_SIZE</code>)
     *
     * @param prices the loaded prices to process.
     */
    private void processPrices(@Nullable final List<Price> prices)
            throws IOException, ResponseException {
        if (prices == null || prices.size() == 0) {
            return;
        }
        for (Price price : prices) {
            boolean isItemExisting = false;
            ItemEntity knownItem = mDatabaseAccess.itemsDAO().selectItem(price.getId());
            // item is not known yet. Load the item via the REST API
            if (knownItem == null) {
                Item newItem = mItemAccess.getItemWithWifi(price.getId());
                if (newItem != null) {
                    mDatabaseAccess.itemsDAO().insertItems(new ItemEntity(newItem));
                    isItemExisting = true;
                }
            } else {
                isItemExisting = true;
            }

            // add the price only if the item reference is known and exists
            if (isItemExisting) {
                ItemPriceEntity newPrice = new ItemPriceEntity(
                        price.getId(),
                        price.getBuys().getUnitPrice(),
                        price.getSells().getUnitPrice(),
                        System.currentTimeMillis());
                mDatabaseAccess.itemsDAO().insertItemPrice(newPrice);
            } else {
                Log.w(TAG, String.format(
                        "The Item to the price id '%d' doesn't exist and will be skipped",
                        price.getId()));
            }
        }
    }


    /**
     * This method will be used to update older prices.
     * To save some space on the smartphone, prices of the previous months will be merged together.
     */
    @VisibleForTesting
    protected void updateHistoryItemPrices() {
        try {
            // TODO

        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex); // TODO
        }
    }


    /**
     * Waits the given ms
     *
     * @param toWait the ms to wait
     */
    private void waitMs(final long toWait) {
        synchronized (this) {
            try {
                wait(toWait);
            } catch (InterruptedException ex) {
                Log.e(TAG, ex.getMessage(), ex); // TODO
            }
        }
    }
}

package com.scavi.de.gw2imp.background.collector.data;

import android.util.Log;

import com.scavi.androidimp.util.DateHelper;
import com.scavi.androidimp.util.RestHelper;
import com.scavi.de.gw2imp.communication.access.ICommerceAccess;
import com.scavi.de.gw2imp.communication.access.IItemAccess;
import com.scavi.de.gw2imp.communication.response.commerce.Price;
import com.scavi.de.gw2imp.communication.response.items.Item;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.data.entity.item.ItemEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceHistoryEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemSearchEntity;
import com.scavi.de.gw2imp.data.entity.item.TrendEntity;
import com.scavi.de.gw2imp.data.util.TrendType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemDataProcessor implements IDataProcessor {
    private static final String TAG = "ItemDataProcessor";

    // the constant defines how many items will be processed at once (e.g. load 100 item prices)
    private static final int ITEM_PROCESS_SIZE = 100;
    // the constant defines the iteration until we wait before interact with the server again
    // while loading all prices / items
    private static final int ITEM_PROCESS_WAIT_COUNT = ITEM_PROCESS_SIZE * 10;
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
    public ItemDataProcessor(final IDatabaseAccess databaseAccess,
                             final IItemAccess itemAccess,
                             final ICommerceAccess commerceAccess) {
        mDatabaseAccess = databaseAccess;
        mItemAccess = itemAccess;
        mCommerceAccess = commerceAccess;
    }


    /**
     * Loads the item IDs with a commerce item id context
     *
     * @return a list of all item IDs in a commerce context
     */
    @Override
    public List<Integer> loadAllIds() {
        List<Integer> allCommerceItemIds = new LinkedList<>();
        try {
            allCommerceItemIds = mCommerceAccess.getAllCommerceItemsWithWifi();
        } catch (Exception ex) {
            Log.e(TAG, "A general failure has occurred while trying to load all item IDs.",
                    ex);
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
    @Override
    public void loadData(final List<Integer> allCommerceItemIds) {
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
                Log.e(TAG, "A general failure has occurred while trying to load commerce data.",
                        ex);
            }
        }
    }

    /**
     * @param prices the loaded prices to process.
     */
    private void processPrices(@Nullable final List<Price> prices) {
        if (prices == null || prices.size() == 0) {
            return;
        }
        for (Price price : prices) {
            insertItemPrice(price);
            saveItemPriceTrend(price);
        }
    }


    /**
     * Verifies, if the general item information (id, name, icon) already exists. If not, the item
     * information will be loaded. After that, the price will be inserted into the database.
     * Since the item prices will be loaded via REST GET, the commercial item IDs must be split into
     * groups (<code>ITEM_PROCESS_SIZE</code>).
     * Catches all exceptions to make sure that an error won't affect processing the prices
     *
     * @param price the new price
     */
    private void insertItemPrice(final Price price) {
        try {
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
        } catch (Exception ex) {
            Log.e(TAG, "Failed to process the item price with the ID: " + price.getId(), ex);
        }
    }


    /**
     * This method saves the seller and buyer item trends of the current given price
     *
     * @param price the new price
     */
    private void saveItemPriceTrend(final Price price) {
        try {
            ItemPriceEntity itemPrice = mDatabaseAccess.itemsDAO().selectLastItemPrice(
                    price.getId());
            double sellPercentage = 0;
            double buyPercentage = 0;
            // calculates the sell and buy percentage if a price already exists
            if (itemPrice != null) {
                sellPercentage = 1 - (price.getSells().getUnitPrice() / itemPrice.getSellPrice());
                buyPercentage = 1 - (price.getBuys().getUnitPrice() / itemPrice.getBuyPrice());
            }
            // insert the data
            mDatabaseAccess.trendDAO().insertTrend(
                    new TrendEntity(price.getId(), TrendType.BuyItems, buyPercentage));
            mDatabaseAccess.trendDAO().insertTrend(
                    new TrendEntity(price.getId(), TrendType.SellItems, sellPercentage));
        } catch (Exception ex) {
            Log.e(TAG, "Failed to process the item trend with the ID: " + price.getId(), ex);
        }
    }


    /**
     * This method will be used to update older prices.
     * To save some space on the smart phone, prices of the previous months will be merged together.
     */
    @Override
    public void updateHistory() {
        List<Integer> allItemIds = determineItemIds();
        if (allItemIds == null || allItemIds.size() == 0) {
            Log.i(TAG, "No Item Ids exists to create history data sets.");
            return;
        }

        long processTs = System.currentTimeMillis();
        long startLastMonth;
        long endLastMonth;
        // determine the last month depending from the process timestamp. In the first iteration
        // the process timestamp is the current timestamp. With the second iteration,
        startLastMonth = DateHelper.startOfLastMonth(processTs);
        endLastMonth = DateHelper.endOfLastMonth(processTs);
        // iterate through all known IDs. This is required because we want to create a history entry
        // for each item
        for (int itemId : allItemIds) {
            updatePriceHistory(itemId, startLastMonth, endLastMonth);
        }
    }


    /**
     * Determine all known item ids
     *
     * @return all known item ids
     */
    private List<Integer> determineItemIds() {
        List<Integer> ids = null;
        try {
            ids = mDatabaseAccess.itemsDAO().selectAllItemIds();
        } catch (Exception ex) {
            Log.e(TAG, "A general failure has occurred while trying to determine all item IDs.",
                    ex);
        }
        return ids;
    }


    /**
     * Select all items in the given range from <code>from</code> until <code>till</code> and
     * create a history entry. The history entry contains the min / max values of the month.
     * It basically represents a merging of all price values in the time spam to save memory on
     * the phone. The new history entry will be inserted into the database and all price entries
     * will be removed
     *
     * @param itemId the current item id
     * @param from   the timestamp from
     * @param till   the timestamp to
     */
    private void updatePriceHistory(final int itemId,
                                    final long from,
                                    final long till) {
        try {
            // select all item prices to the given id in the range
            List<ItemPriceEntity> prices = mDatabaseAccess.itemsDAO().
                    selectItemPricesInRange(itemId, from, till);
            if (prices != null && prices.size() > 0) {
                //Log.v(TAG, "Update price history entry for the itemId " + itemId);
                // creates a history entry from all prices in the time range
                ItemPriceHistoryEntity history = ItemPriceHistoryEntity.from(itemId, from, prices);
                // insert the history entry
                mDatabaseAccess.itemsDAO().insertItemPriceHistory(history);
                // delete all prices in the time range
                mDatabaseAccess.itemsDAO().deleteItemPricesInRange(itemId, from, till);

                Log.v(TAG, "Remaining to delete: " + mDatabaseAccess.itemsDAO()
                        .selectPricesInRange(from, till));
            } else {
                //Log.d(TAG, "The history prices for the itemId " + itemId + " are up to date.");
            }
        } catch (Exception ex) {
            Log.e(TAG, "A general failure has occurred while trying to update the price history.",
                    ex);
        }
    }


    /**
     * This method determines all missing IDs for the search index that are known yet.
     */
    @Override
    public void updateItemSearchIndex() {
        try {
            // all item IDs that don't have a search index yet
            int[] allMissingIds = mDatabaseAccess.itemsDAO().selectMissingSearchIndexIds();
            // Selects the item to the id, extract all parts of the item name to create the
            // search index (all ItemSearchEntity's) and insert them into the database
            for (int missingId : allMissingIds) {
                try {
                    ItemEntity item = mDatabaseAccess.itemsDAO().selectItem(missingId);
                    List<ItemSearchEntity> searchEntities = createItemSearchIndex(item);
                    mDatabaseAccess.itemsDAO().insertItemSearchParts(searchEntities);
                } catch (Exception ex) {
                    Log.e(TAG, "An error occurred trying to create the search index to the id" +
                            missingId, ex);
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, "A general failure has occurred while trying to load and process all " +
                    "item IDs to generate a search index", ex);
        }
    }


    /**
     * Replaces all letters except A-Z, 0-1 and german special characters ÄÖÜß with " " (lower
     * and upper case). Then the remaining item name will be tokenized by " ".
     *
     * @param item the item for the search index
     * @return all parts of the name
     */
    private String[] determineItemNameParts(@Nonnull final ItemEntity item) {
        String cleanedName = item.getName().replaceAll("[^a-zA-Z1-9äÄöÖüÜß]", " ");
        String[] allParts = cleanedName.split("\\s+");
        List<String> cleanedParts = new ArrayList<>();
        for (String part : allParts) {
            // length of 2 to exclude one digit words. 2 is the lowest value, because we want to
            // support values like "Ei"
            if (part.length() >= 2) {
                cleanedParts.add(part);
            }
        }
        return cleanedParts.toArray(new String[cleanedParts.size()]);
    }


    /**
     * Creates a search item index to the given item with all parts
     *
     * @param item the item for the search index
     * @return all search index entities
     */
    private List<ItemSearchEntity> createItemSearchIndex(@Nonnull final ItemEntity item) {
        String[] nameParts = determineItemNameParts(item);
        List<ItemSearchEntity> itemSearch = new ArrayList<>(nameParts.length);
        for (final String part : nameParts) {
            itemSearch.add(new ItemSearchEntity(item.getItemId(), part));
        }
        return itemSearch;
    }


    /**
     * Waits the given ms
     *
     * @param toWait the ms to wait
     */
    private void waitMs(final long toWait) {
        synchronized (this) {
            try {
                if (toWait > 0) {
                    wait(toWait);
                }
            } catch (InterruptedException ex) {
                Log.e(TAG, ex.getMessage(), ex);
            }
        }
    }
}

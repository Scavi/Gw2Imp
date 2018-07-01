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
package com.scavi.de.gw2imp.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scavi.de.gw2imp.data.entity.item.ItemEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceHistoryEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemSearchEntity;
import com.scavi.de.gw2imp.data.util.DbConst;

import java.util.List;
import java.util.Set;

import static com.scavi.de.gw2imp.data.util.DbConst.TABLE_ITEM_PRICES;


@Dao
public interface IItemsDAO {
    /**
     * Inserts the given items into the table
     *
     * @param items the items and all the information
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertItems(final ItemEntity... items);


    /**
     * Selects the item to the given id
     *
     * @param id the id of the item
     * @return the item to the given id from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ITEMS + " WHERE id = :id")
    ItemEntity selectItem(final int id);


    /**
     * Selects the item with the next higher id while the data set is order from low to high
     *
     * @param id the id where the next higher id is required
     * @return the item or <code>null</code>
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ITEMS + " WHERE id > :id ORDER BY ID ASC LIMIT 1")
    ItemEntity selectNextItem(final int id);


    /**
     * @return the item to the given id from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ITEMS)
    List<ItemEntity> selectItems();


    /**
     * @param name the name of the item. Wildcard can be used
     * @return the item to the given name from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ITEMS + " WHERE name LIKE(:name)")
    List<ItemEntity> selectItems(final String name);


    /**
     * @param ids all item ids
     * @return the item to the given id from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ITEMS + " WHERE id IN(:ids)")
    List<ItemEntity> selectItems(final int[] ids);


    /**
     * Select all known item ids
     *
     * @return all item ids
     */
    @Query("SELECT id FROM " + DbConst.TABLE_ITEMS)
    List<Integer> selectAllItemIds();


    /**
     * @return the amount of distinct items in the database
     */
    @Query("SELECT COUNT(*) FROM " + DbConst.TABLE_ITEMS)
    int selectItemCount();


    /**
     * Inserts the given item price into the table
     *
     * @param item the item price entity
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertItemPrice(final ItemPriceEntity item);


    /**
     * @return the count of items in the item price table
     */
    @Query("SELECT COUNT(*) FROM " + TABLE_ITEM_PRICES)
    int selectItemPriceCount();


    /**
     * Selects all prices to a given item ID
     *
     * @param id all prices to the given ID
     * @return the item to the given id from the DAO
     */
    @Query("SELECT * FROM " + TABLE_ITEM_PRICES + " WHERE id = :id")
    List<ItemPriceEntity> selectItemPrices(final int id);


    /**
     * Selects all item prices that are in the given range to the given id
     *
     * @param start the start time of the interval to select
     * @param end   the end time of the interval to select
     * @return the count of items in that range
     */
    @Query("SELECT count(*) FROM " + TABLE_ITEM_PRICES +
            " WHERE time >= :start AND time <= :end LIMIT 10")
    int selectPricesInRange(final long start,
                            final long end);


    /**
     * Selects all item prices that are in the given range to the given id
     *
     * @param id    the id of the item which the prices belong to
     * @param start the start time of the interval to select
     * @param end   the end time of the interval to select
     * @return the items in the range
     */
    @Query("SELECT * FROM " + TABLE_ITEM_PRICES +
            " WHERE id = :id AND time >= :start AND time <= :end")
    List<ItemPriceEntity> selectItemPricesInRange(final int id,
                                                  final long start,
                                                  final long end);


    /**
     * Selects the last price to the given item ID
     *
     * @param id the id of the item which the prices belong to
     * @return the last item price
     */
    @Query("SELECT * FROM " + TABLE_ITEM_PRICES + " WHERE id = :id ORDER BY time DESC LIMIT 1")
    ItemPriceEntity selectLastItemPrice(final int id);


    /**
     * Delete all item prices that are in the given range to the given id
     *
     * @param id    the id of the item which the prices belong to
     * @param start the start time of the interval to select
     * @param end   the end time of the interval to select
     */
    @Query("DELETE FROM " + TABLE_ITEM_PRICES +
            " WHERE id = :id AND time >= :start AND time <= :end")
    void deleteItemPricesInRange(final int id,
                                 final long start,
                                 final long end);

    /**
     * Inserts the given item price history into the table
     *
     * @param item the item price entity
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertItemPriceHistory(final ItemPriceHistoryEntity item);


    /**
     * @return the count of items in the history item price table
     */
    @Query("SELECT COUNT(*) FROM " + DbConst.TABLE_ITEM_PRICE_HISTORY)
    int selectItemPriceHistoryCount();


    /**
     * @param id the id of the item
     * @return the item to the given id from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ITEM_PRICE_HISTORY + " WHERE id = :id")
    List<ItemPriceHistoryEntity> selectItemPriceHistory(final int id);


    /**
     * Inserts the list of all item search parts
     *
     * @param itemSearch all items to insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertItemSearchParts(final List<ItemSearchEntity> itemSearch);


    /**
     * Selects all IDs from the item table that are not yet in the item part search.
     *
     * @return all IDs from the item table that are not yet in the item part search.
     */
    @Query("SELECT id FROM " + DbConst.TABLE_ITEMS + " WHERE id NOT IN " +
            "(SELECT DISTINCT id FROM " + DbConst.TABLE_ITEM_PART_SEARCH + " )")
    int[] selectMissingSearchIndexIds();


    /**
     * Selects the amount of search item parts distinct by id because the correlation is ID to
     * all parts of the name to the correlated ID
     *
     * @return the amount of item parts
     */
    @Query("SELECT COUNT(id) FROM " + DbConst.TABLE_ITEM_PART_SEARCH)
    int selectItemSearchCount();


    /**
     * @return
     */
    @Query("SELECT distinct namePart FROM " + DbConst.TABLE_ITEM_PART_SEARCH +
            " GROUP BY namePart")
    List<String> selectSearchItemDictionary();


    /**
     * @return
     */
    @Query("SELECT distinct namePart, id FROM " + DbConst.TABLE_ITEM_PART_SEARCH +
            " WHERE id > :id GROUP BY namePart ORDER BY id ASC LIMIT 1")
    ItemSearchEntity selectNextSearchItemIndex(final int id);


    /**
     * Searches all items to the given name part
     *
     * @param name the name of the item. Wildcard can be used
     * @return all items to the name part
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ITEM_PART_SEARCH + " WHERE namePart LIKE(:name) LIMIT" +
            " 100")
    List<ItemSearchEntity> selectAllItemsByNamePart(final String name);


    @Query("DELETE FROM " + DbConst.TABLE_ITEM_PART_SEARCH)
    void deleteSearchIndex();
}
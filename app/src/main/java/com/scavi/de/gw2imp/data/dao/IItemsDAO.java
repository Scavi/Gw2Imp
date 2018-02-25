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
import com.scavi.de.gw2imp.data.util.DbConst;

import java.util.List;


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
     * @param id the id of the item
     * @return the item to the given id from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ITEMS + " WHERE id = :id")
    ItemEntity selectItem(final int id);


    /**
     * @return the item to the given id from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ITEMS)
    List<ItemEntity> selectItems();


    /**
     * @param name the name of the item. Wildcard can be used
     * @return the item to the given id from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ITEMS + " WHERE name LIKE(:name)")
    List<ItemEntity> selectItems(final String name);


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
     * @return the item to the given id from the DAO
     */
    @Query("SELECT COUNT(*) FROM " + DbConst.TABLE_ITEM_PRICES)
    int selectItemPriceCount();


    /**
     * @param id all prices to the given ID
     * @return the item to the given id from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ITEM_PRICES + " WHERE id = :id")
    List<ItemPriceEntity> selectItemPrices(final int id);


    /**
     * Inserts the given item price history into the table
     *
     * @param item the item price entity
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertItemPriceHistory(final ItemPriceHistoryEntity item);


    /**
     * @param id the id of the item
     * @return the item to the given id from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_ITEM_PRICE_HISTORY + " WHERE id = :id")
    List<ItemPriceHistoryEntity> selectItemPriceHistory(final int id);
}

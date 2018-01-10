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
package com.scavi.de.gw2imp.data.entity.item;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.scavi.de.gw2imp.data.util.DbConst;

import javax.annotation.ParametersAreNonnullByDefault;


@Entity(tableName = DbConst.TABLE_ITEM_PRICE_HISTORY,
        foreignKeys = @ForeignKey(
                entity = ItemEntity.class,
                parentColumns = "id",
                childColumns = "id"))
@ParametersAreNonnullByDefault
public class ItemPriceHistoryEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final int mItemId;
    @ColumnInfo(name = "month")
    private final int mMonth;
    @ColumnInfo(name = "min")
    private final int mMinPrice;
    @ColumnInfo(name = "max")
    private final int mMaxPrice;


    /**
     * Constructor
     *
     * @param itemId   the unique id of the item
     * @param month    the month that shows the min / max price
     * @param minPrice the min price of this item during the month
     * @param maxPrice the max price of this item during the month
     */
    public ItemPriceHistoryEntity(final int itemId,
                                  final int month,
                                  final int minPrice,
                                  final int maxPrice) {
        mItemId = itemId;
        mMonth = month;
        mMinPrice = minPrice;
        mMaxPrice = maxPrice;
    }


    /**
     * @return the unique id of the item
     */
    public int getItemId() {
        return mItemId;
    }


    /**
     * @return the month that shows the min / max price
     */
    public int getMonth() {
        return mMonth;
    }


    /**
     * @return the min price of this item during the month
     */
    public int getMinPrice() {
        return mMinPrice;
    }


    /**
     * @return the max price of this item during the month
     */
    public int getMaxPrice() {
        return mMaxPrice;
    }
}

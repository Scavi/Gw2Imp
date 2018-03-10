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
package com.scavi.de.gw2imp.data.entity.item;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.scavi.de.gw2imp.data.util.DbConst;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;


@Entity(tableName = DbConst.TABLE_ITEM_PRICE_HISTORY,
        primaryKeys = {"id", "startOfMonth"},
        foreignKeys = @ForeignKey(
                entity = ItemEntity.class,
                parentColumns = "id",
                childColumns = "id"))
@ParametersAreNonnullByDefault
public class ItemPriceHistoryEntity {
    @ColumnInfo(name = "id")
    private final int mItemId;
    @ColumnInfo(name = "startOfMonth")
    private final long mTsStartOfMonth;
    @ColumnInfo(name = "avgStartBuy")
    private final int mAvgStartBuy;
    @ColumnInfo(name = "avgEndBuy")
    private final int mAvgEndBuy;
    @ColumnInfo(name = "avgStartSell")
    private final int mAvgStartSell;
    @ColumnInfo(name = "avgEndSell")
    private final int mAvgEndSell;

    /**
     * Constructor
     *
     * @param itemId         the unique id of the item
     * @param tsStartOfMonth the timestamp of the start of the month
     * @param avgStartBuy    the average buy-price of the start of the month (calculate the average
     *                       of the first half of all prices)
     * @param avgEndBuy      the average buy-price of the end of the month (calculate the average of
     *                       the last half of all prices)
     * @param avgStartSell   the average sell-price of the start of the month (calculate the average
     *                       of the first half of all prices)
     * @param avgEndSell     the average sell-price of the end of the month (calculate the
     *                       average of the last half of all prices)
     */
    public ItemPriceHistoryEntity(final int itemId,
                                  final long tsStartOfMonth,
                                  final int avgStartBuy,
                                  final int avgEndBuy,
                                  final int avgStartSell,
                                  final int avgEndSell) {
        mItemId = itemId;
        mTsStartOfMonth = tsStartOfMonth;
        mAvgStartBuy = avgStartBuy;
        mAvgEndBuy = avgEndBuy;
        mAvgStartSell = avgStartSell;
        mAvgEndSell = avgEndSell;
    }


    /**
     * @return the unique id of the item
     */
    public int getItemId() {
        return mItemId;
    }

    /**
     * @return the timestamp of the start of the month
     */
    public long getTsStartOfMonth() {
        return mTsStartOfMonth;
    }

    /**
     * @return the average buy price of the start of the month (calculate the average
     * of the first half of all prices)
     */
    public int getAvgStartBuy() {
        return mAvgStartBuy;
    }

    /**
     * @return the average buy price of the end of the month (calculate the average of
     * the last half of all prices)
     */
    public int getAvgEndBuy() {
        return mAvgEndBuy;
    }

    /**
     * @return the average sell-price of the start of the month (calculate the average
     * of the first half of all prices)
     */
    public int getAvgStartSell() {
        return mAvgStartSell;
    }

    /**
     * @return the average sell-price of the end of the month (calculate the
     * average of the last half of all prices)
     */
    public int getAvgEndSell() {
        return mAvgEndSell;
    }


    /**
     * @param itemId         the unique id of the item
     * @param tsStartOfMonth the timestamp of the start of the month
     * @param pricesInRange  all prices to the given item in the range
     * @return the item price history
     */
    public static ItemPriceHistoryEntity from(final int itemId,
                                              final long tsStartOfMonth,
                                              @Nonnull final List<ItemPriceEntity> pricesInRange) {
        int avgStartBuy = 0;
        int avgStartSell = 0;
        int avgEndBuy = 0;
        int avgEndSell = 0;

        int split = pricesInRange.size() / 2;
        for (int i = 0; i < split; ++i) {
            ItemPriceEntity price = pricesInRange.get(i);
            avgStartBuy += price.getBuyPrice();
            avgStartSell += price.getSellPrice();
        }
        for (int i = split; i < pricesInRange.size(); ++i) {
            ItemPriceEntity price = pricesInRange.get(i);
            avgEndBuy += price.getBuyPrice();
            avgEndSell += price.getSellPrice();
        }
        return new ItemPriceHistoryEntity(
                itemId,
                tsStartOfMonth,
                avgStartBuy / split,
                avgEndBuy / split,
                avgStartSell / split,
                avgEndSell / split);
    }
}

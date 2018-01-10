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
import android.arch.persistence.room.Index;

import com.scavi.de.gw2imp.data.entity.achievement.AchievementEntity;
import com.scavi.de.gw2imp.data.util.DbConst;

import javax.annotation.ParametersAreNonnullByDefault;

@Entity(tableName = DbConst.TABLE_ITEM_PRICES, indices = {@Index(value = "id")},
        primaryKeys = {"id", "time"},
        foreignKeys = @ForeignKey(
                entity = ItemEntity.class,
                parentColumns = "id",
                childColumns = "id"))
@ParametersAreNonnullByDefault
public class ItemPriceEntity {
    @ColumnInfo(name = "id")
    private final int mItemId;
    @ColumnInfo(name = "time")
    private final long mTime;
    @ColumnInfo(name = "buy_price")
    private final int mBuyPrice;
    @ColumnInfo(name = "sell_price")
    private final int mSellPrice;


    /**
     * Constructor
     *
     * @param itemId    the unique id of the item
     * @param buyPrice  the buy price of this item at the current timestamp
     * @param sellPrice the sell price of this item at the current timestamp
     * @param time      the current timestamp / moment of the current price
     */
    public ItemPriceEntity(final int itemId,
                           final int buyPrice,
                           final int sellPrice,
                           final long time) {
        mItemId = itemId;
        mBuyPrice = buyPrice;
        mSellPrice = sellPrice;
        mTime = time;
    }


    /**
     * @return the unique id of the item
     */
    public int getItemId() {
        return mItemId;
    }


    /**
     * @return the buy price of this item at the current timestamp
     */
    public int getBuyPrice() {
        return mBuyPrice;
    }


    /**
     * @return the sell price of this item at the current timestamp
     */
    public int getSellPrice() {
        return mSellPrice;
    }


    /**
     * @return the timestamp / moment of the current price
     */
    public long getTime() {
        return mTime;
    }
}

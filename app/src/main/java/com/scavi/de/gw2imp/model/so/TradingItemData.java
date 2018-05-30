package com.scavi.de.gw2imp.model.so;

import android.content.Context;

import com.scavi.de.gw2imp.data.entity.item.ItemEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceHistoryEntity;
import com.scavi.de.gw2imp.ui.graph.Gw2CurrencyFormatter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;

public class TradingItemData {
    private final SimpleDateFormat DEFAULT_ITEM_DATE_PATTERN = new SimpleDateFormat(
            "HH:mm:ss, dd.MMM yy",
            Locale.getDefault());
    private Builder mBuilder;

    private TradingItemData(final Builder builder) {
        mBuilder = builder;
    }

    public int count() {
        return mBuilder.mItemHistoryPrices.size() + mBuilder.mItemPrices.size();
    }

    public List<ItemPriceHistoryEntity> getItemHistoryPrices() {
        return mBuilder.mItemHistoryPrices;
    }

    public List<ItemPriceEntity> getItemPrices() {
        return mBuilder.mItemPrices;
    }

    public ItemEntity getSelectedItem() {
        return mBuilder.selectedItem;
    }

    public String getMinSellPriceFormatted(@Nonnull final Context context) {
        return Gw2CurrencyFormatter.createCurrencyString(context, mBuilder.mMinSellPrice);
    }

    public String getMaxSellPriceFormatted(@Nonnull final Context context) {
        return Gw2CurrencyFormatter.createCurrencyString(context, mBuilder.mMaxSellPrice);
    }

    public String getMinBuyPriceFormatted(@Nonnull final Context context) {
        return Gw2CurrencyFormatter.createCurrencyString(context, mBuilder.mMinBuyPrice);
    }

    public String getMaxBuyPriceFormatted(@Nonnull final Context context) {
        return Gw2CurrencyFormatter.createCurrencyString(context, mBuilder.mMaxBuyPrice);
    }

    public int getMinSellPrice() {
        return mBuilder.mMinSellPrice;
    }

    public int getMaxBuyPrice() {
        return mBuilder.mMaxBuyPrice;
    }

    public String getLatestUpdate() {
        return DEFAULT_ITEM_DATE_PATTERN.format(mBuilder.mLatestUpdate);
    }

    public static class Builder {
        private List<ItemPriceHistoryEntity> mItemHistoryPrices;
        private List<ItemPriceEntity> mItemPrices;
        private ItemEntity selectedItem;
        private int mMinSellPrice = Integer.MAX_VALUE;
        private int mMaxSellPrice = 0;
        private int mMinBuyPrice = Integer.MAX_VALUE;
        private int mMaxBuyPrice = 0;
        private long mLatestUpdate = 0;


        public Builder setItemHistoryPrices(@Nonnull final List<ItemPriceHistoryEntity>
                                                    itemHistoryPrices) {
            mItemHistoryPrices = itemHistoryPrices;
            return this;
        }

        public Builder setItemPrices(@Nonnull final List<ItemPriceEntity> itemPrices) {
            mItemPrices = itemPrices;
            return this;
        }

        public Builder setSelectedItem(@Nonnull final ItemEntity selectedItem) {
            this.selectedItem = selectedItem;
            return this;
        }

        public TradingItemData build() {
            for (ItemPriceHistoryEntity history : mItemHistoryPrices) {
                mMinSellPrice = Math.min(history.getAvgStartSell(), mMinSellPrice);
                mMinSellPrice = Math.min(history.getAvgEndSell(), mMinSellPrice);
                mMaxSellPrice = Math.max(history.getAvgStartSell(), mMaxSellPrice);
                mMaxSellPrice = Math.max(history.getAvgEndSell(), mMaxSellPrice);
                mMinBuyPrice = Math.min(history.getAvgStartSell(), mMinBuyPrice);
                mMinBuyPrice = Math.min(history.getAvgEndSell(), mMinBuyPrice);
                mMaxBuyPrice = Math.max(history.getAvgStartSell(), mMaxBuyPrice);
                mMaxBuyPrice = Math.max(history.getAvgEndSell(), mMaxBuyPrice);
            }
            for (ItemPriceEntity price : mItemPrices) {
                mMinSellPrice = Math.min(price.getSellPrice(), mMinSellPrice);
                mMaxSellPrice = Math.max(price.getSellPrice(), mMaxSellPrice);
                mMinBuyPrice = Math.min(price.getBuyPrice(), mMinBuyPrice);
                mMaxBuyPrice = Math.max(price.getBuyPrice(), mMaxBuyPrice);
                mLatestUpdate = Math.max(mLatestUpdate, price.getTime());
            }
            return new TradingItemData(this);
        }

    }
}

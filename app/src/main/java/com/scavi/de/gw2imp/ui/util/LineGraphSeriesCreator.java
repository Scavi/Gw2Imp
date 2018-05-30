package com.scavi.de.gw2imp.ui.util;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceHistoryEntity;
import com.scavi.de.gw2imp.model.so.TradingItemData;
import com.scavi.de.gw2imp.ui.graph.Gw2CurrencyFormatter;

import java.util.List;

import javax.annotation.Nonnull;

public class LineGraphSeriesCreator {
    @Nonnull
    private final List<ItemPriceHistoryEntity> mItemHistoryPrices;
    @Nonnull
    private final List<ItemPriceEntity> mItemPrices;


    /**
     * Constructor
     *
     * @param tradingItemData the trading item data containing the history, the current price and
     *                        the item information
     */
    public LineGraphSeriesCreator(@Nonnull final TradingItemData tradingItemData) {
        this(tradingItemData.getItemHistoryPrices(), tradingItemData.getItemPrices());
    }


    /**
     * Constructor
     *
     * @param itemHistoryPrices all history prices to the current item
     * @param itemPrices        all current prices to the current item
     */
    public LineGraphSeriesCreator(@Nonnull final List<ItemPriceHistoryEntity> itemHistoryPrices,
                                  @Nonnull final List<ItemPriceEntity> itemPrices) {
        mItemHistoryPrices = itemHistoryPrices;
        mItemPrices = itemPrices;
    }

    /**
     * Creates a graph series for buyer depending on the given flag. It
     * combines history and current item prices
     *
     * @return the price graph series  for the current item
     */
    public LineGraphSeries<DataPoint> createBuyGraphSeries() {
        return createGraphSeries(true);
    }


    /**
     * Creates a graph series for seller-prices depending on the given flag. It
     * combines history and current item prices
     *
     * @return the price graph series for the current item
     */
    public LineGraphSeries<DataPoint> createSellGraphSeries() {
        return createGraphSeries(false);
    }

    /**
     * Creates a graph series for buyer and seller-prices depending on the given flag. It
     * combines history and current item prices
     *
     * @param isBuyPrice <code>true</code> create a graph for the buy-context
     *                   <code>false</code> create a graph for the sell-context
     * @return the price graph series  for the current item
     */
    private LineGraphSeries<DataPoint> createGraphSeries(final boolean isBuyPrice) {

        // x2 because history prices have a start and end date within the period
        DataPoint[] graphPoints = new DataPoint[(mItemHistoryPrices.size() * 2) + mItemPrices
                .size()];
        createHistoryData(isBuyPrice, graphPoints);
        createCurrentData(isBuyPrice, graphPoints);
        return new LineGraphSeries<>(graphPoints);
    }


    /**
     * Creates a graph series for buyer and seller-prices depending on the given flag. It
     * creates and sets data points for the history prices
     *
     * @param isBuyPrice  <code>true</code> create a graph for the buy-context
     *                    <code>false</code> create a graph for the sell-context
     * @param graphPoints the data point array to set for the graph
     */
    private void createHistoryData(final boolean isBuyPrice,
                                   final DataPoint[] graphPoints) {
        int pos = 0;
        int offset = 0;
        double price;
        for (ItemPriceHistoryEntity itemHistoryPrice : mItemHistoryPrices) {
            // avg start price of the period
            price = isBuyPrice ? itemHistoryPrice.getAvgStartBuy() :
                    itemHistoryPrice.getAvgStartSell();
            graphPoints[pos] = new DataPoint(pos + offset, price);
            pos++;
            offset += Gw2CurrencyFormatter.ONE_GRID_LENGTH;
            // avg end price of the period
            price = isBuyPrice ? itemHistoryPrice.getAvgEndBuy() :
                    itemHistoryPrice.getAvgEndSell();
            graphPoints[pos] = new DataPoint(pos + offset, price);
            pos++;
        }
    }


    /**
     * Creates a graph series for buyer and seller-prices depending on the given flag. It
     * creates and sets data points for the current prices
     *
     * @param isBuyPrice  <code>true</code> create a graph for the buy-context
     *                    <code>false</code> create a graph for the sell-context
     * @param graphPoints the data point array to set for the graph
     */
    private void createCurrentData(final boolean isBuyPrice,
                                   final DataPoint[] graphPoints) {
        double price;
        // the position within the graph point array can be determined by the length of the array
        // minus the available item prices (e.g. if we have two item prices and no history entries
        // the length of the graph points will be 2 (-2 of item prices so the position will be 0)
        int pos = graphPoints.length - mItemPrices.size();
        // In case we have history entries we must calculate the grid length. This can be determined
        // by the position divided by two because for each history entry we will have two data
        // points
        int offset = (pos / 2) * Gw2CurrencyFormatter.ONE_GRID_LENGTH;
        for (ItemPriceEntity itemPrice : mItemPrices) {
            price = isBuyPrice ?
                    itemPrice.getBuyPrice() :
                    itemPrice.getSellPrice();
            graphPoints[pos] = new DataPoint(pos + offset, price);
            pos++;
        }
    }
}

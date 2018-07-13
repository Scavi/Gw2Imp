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
package com.scavi.de.gw2imp.ui.view;

import com.scavi.de.gw2imp.data.entity.item.ItemEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceHistoryEntity;
import com.scavi.de.gw2imp.model.so.TradingItemData;

import java.util.List;

import javax.annotation.Nonnull;

public interface ITradingItemsView extends IStatusView {

    /**
     * @return the current name of the item we want to find
     */
    String getItemSearchName();


    /**
     * @param foundItems the list of items that were found
     */
    void updatePossibleItems(@Nonnull final List<ItemEntity> foundItems);


    /**
     * Closes the current search
     */
    void closeSearch();


    /**
     * Shows the UI components that will contain the data (set the components to visible)
     */
    void showComponents();

    /**
     * Updates the graph with the prices
     *
     * @param tradingItemData the trading item data containing the history, the current price and
     *                        the item information
     */
    void updateGraph(@Nonnull final TradingItemData tradingItemData);


    /**
     * Updates the details of the presented trading item
     *
     * @param tradingItemData the trading item data containing the history, the current price and
     *                        the item information
     */
    void updateItemDetails(@Nonnull final TradingItemData tradingItemData);

    /**
     * This method resets the screen (e.g. item graph, data) from previous searches
     */
    void resetScreen();


    /**
     * In case no prices exists, a message will be presented to the user
     */
    void showNoItemPricesFound();


    /**
     *Updates the clear button (e.g. visibility)
     */
    void updateClearButtonView(final String text);
}

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
package com.scavi.de.gw2imp.presenter;

import android.text.TextWatcher;

import com.scavi.de.gw2imp.data.entity.item.ItemEntity;
import com.scavi.de.gw2imp.model.TradingItemsModel;
import com.scavi.de.gw2imp.model.so.TradingItemData;
import com.scavi.de.gw2imp.ui.util.DelayedTextFieldWatcher;
import com.scavi.de.gw2imp.ui.view.ITradingItemsView;

import java.util.List;
import java.util.TimerTask;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

@ParametersAreNonnullByDefault
public class TradingItemsPresenter {
    private static final int MIN_SEARCH_LENGTH = 3;
    private final ITradingItemsView mView;
    private final TradingItemsModel mModel;

    /**
     * Constructor
     *
     * @param view  the view for the trading items
     * @param model the model for the trading items
     */
    @Inject
    public TradingItemsPresenter(final ITradingItemsView view,
                                 final TradingItemsModel model) {
        mView = view;
        mModel = model;
    }


    /**
     * @return <code>true</code> optimal search index to find all words
     * <code>false</code> still indexing
     */
    public boolean verifySearchIndexOptimal() {
        return mModel.isSearchIndexOptimal();
    }


    /**
     * This method creates a text field watcher with a small delay. After the delay an item search
     * will be initiated against the database will be initiated to determine all items to the
     * entered name
     *
     * @return the text watcher
     */
    public TextWatcher createTradingItemTextDelay() {
        return new DelayedTextFieldWatcher(
                new TimerTask() {
                    @Override
                    public void run() {
                        Runnable itemSearch = createItemSearchProcessor();
                        // make sure that the search will be started in the UI thread because UI
                        // components will be updated (e.g. show progress)
                        mModel.getExecutorAccess().getUiThreadExecutor().execute(itemSearch);
                    }
                }, TradingItemsModel.TRADING_ITEM_DELAY_MS);
    }


    /**
     * Creates a runnable that determines the item name, shows the progress and initiate the
     * item search in a background thread.
     *
     * @return the runnable to initiate the item search
     */
    private Runnable createItemSearchProcessor() {
        return () -> {
            // the item name for the search
            String itemName = mView.getItemSearchName();
            mView.updateClearButtonView(itemName);

            // must be at least 3 characters long
            if (itemName != null && itemName.length() >= MIN_SEARCH_LENGTH) {
                mView.onShowProgress();
                Runnable itemSearchProcess = createItemSearchProcessor(itemName);
                // uses a background thread to execute the item search
                mModel.getExecutorAccess().getBackgroundThreadExecutor().execute(itemSearchProcess);
            } else {
                // reset the screen of there are not enough characters
                mView.resetScreen();
            }
        };
    }


    /**
     * Creates a runnable that uses the model to select all items to the given name. After the
     * search, the progress will be hidden and the UI will be updated.
     *
     * @param itemName the name of the item
     * @return the runnable.
     */
    private Runnable createItemSearchProcessor(final String itemName) {
        return () -> {
            // all items that match to the name (no prices yet!)
            List<ItemEntity> foundItems = mModel.selectItemsToName(itemName);
            mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                mView.updatePossibleItems(foundItems);
                // exactly one entry
                if (foundItems.size() == 1) {
                    onItemSelected(foundItems.get(0));
                } else {
                    mView.onHideProgress();
                }
            });
        };
    }


    /**
     * This method will be called in case the user selects an item that he searched before.
     *
     * @param selectedItem the selected item.
     */
    public void onItemSelected(@Nonnull final ItemEntity selectedItem) {
        mView.onShowProgress();
        Runnable itemPriceProcessor = createItemPriceProcessor(selectedItem);
        mModel.getExecutorAccess().getBackgroundThreadExecutor().execute(itemPriceProcessor);
    }


    /**
     * This method creates a runnable who does:
     * 1.) Determines all trading item details (prices, history prices, ...) in the current process
     * 2.) reset the UI (old graphs) in the UI process
     * 3.) Updates graph and item details in the UI process
     *
     * @param item the selected item.
     * @return the runnable to execute the steps
     */
    private Runnable createItemPriceProcessor(@Nonnull final ItemEntity item) {
        return () -> {
            // all prices and history prices of the item
            TradingItemData tradingItemData = mModel.determineTradingItemData(item);

            mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                mView.resetScreen();
                mView.closeSearch();

                // not enough items to show prices (from - to)
                if (tradingItemData.count() < 2) {
                    mView.showNoItemPricesFound();
                } else {
                    mView.showComponents();
                    mView.updateGraph(tradingItemData);
                    mView.updateItemDetails(tradingItemData);
                }
                mView.onHideProgress();
            });
        };
    }
}

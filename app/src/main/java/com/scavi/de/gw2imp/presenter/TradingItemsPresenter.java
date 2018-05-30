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
import com.scavi.de.gw2imp.data.entity.item.ItemPriceEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceHistoryEntity;
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

            // at least 3 characters long.
            if (itemName != null && itemName.length() >= 3) {
                mView.onShowProgress();
                Runnable itemSearchProcess = createItemSearchProcessor(itemName);
                // uses a background thread to execute the item search
                mModel.getExecutorAccess().getBackgroundThreadExecutor().execute(itemSearchProcess);

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


    public void onItemSelected(@Nonnull final ItemEntity selectedItem) {
        mView.onShowProgress();
        Runnable itemPriceProcessor = createItemPriceProcessor(selectedItem);
        mModel.getExecutorAccess().getBackgroundThreadExecutor().execute(itemPriceProcessor);
    }


    private Runnable createItemPriceProcessor(@Nonnull final ItemEntity item) {
        return () -> {
            TradingItemData tradingItemData = mModel.determineTradingItemData(item);
            mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                mView.resetScreen();
                mView.closeSearch();

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

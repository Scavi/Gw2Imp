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
import com.scavi.de.gw2imp.ui.util.DelayedTextFieldWatcher;
import com.scavi.de.gw2imp.ui.view.ITradingItemsView;

import java.util.List;
import java.util.TimerTask;

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
     * @return
     */
    public TextWatcher createTradingItemTextDelay() {
        return new DelayedTextFieldWatcher(
                new TimerTask() {
                    @Override
                    public void run() {
                        Runnable itemSearch = createItemSearchProcessor();
                        mModel.getExecutorAccess().getUiThreadExecutor().execute(itemSearch);
                    }
                }, TradingItemsModel.TRADING_ITEM_DELAY_MS);
    }


    /**
     *
     */
    private Runnable createItemSearchProcessor() {
        return () -> {
            String itemName = mView.getItemSearchName();
            mView.onShowProgress();
            Runnable itemSearchProcess = createItemSearchProcessor(itemName);
            mModel.getExecutorAccess().getBackgroundThreadExecutor().execute(itemSearchProcess);
        };
    }


    /**
     * @param itemName
     * @return
     */
    private Runnable createItemSearchProcessor(final String itemName) {
        return () -> {
            List<ItemEntity> foundItems = mModel.selectItemsToName(itemName);
            mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                mView.onHideProgress();
                mView.updateFoundItems(foundItems);
            });
        };
    }


}

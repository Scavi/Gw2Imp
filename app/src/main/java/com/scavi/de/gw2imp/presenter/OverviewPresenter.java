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
package com.scavi.de.gw2imp.presenter;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.model.OverviewModel;
import com.scavi.de.gw2imp.ui.view.IOverviewView;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

@ParametersAreNonnullByDefault
public class OverviewPresenter {
    private final IOverviewView mView;
    private final OverviewModel mModel;

    /**
     * Constructor
     *
     * @param view  the view for the application overview
     * @param model the model for the application overview
     */
    @Inject
    public OverviewPresenter(final IOverviewView view,
                             final OverviewModel model) {
        mView = view;
        mModel = model;
    }


    /**
     * Loads and updates the status data
     */
    public void loadStatusData() {
        Runnable statusLoadAndProcessor = createLoadProcessor();
        mModel.getExecutorAccess().getBackgroundThreadExecutor().execute(statusLoadAndProcessor);
    }


    /**
     * Creates a runnable that loads all status information in the background (not UI process).
     * Based on the loaded information in the background a runnable will be created to update the
     * view. This runnable will be added to a UI thread updater.
     *
     * @return the status runnable
     */
    private Runnable createLoadProcessor() {
        return () -> {
            int itemCount = mModel.selectItemCount();
            int itemPriceCount = mModel.selectItemPriceCount();
            int itemHistoryCount = mModel.selectItemPriceHistoryCount();
            boolean isSearchIndexComplete = mModel.selectIsSearchIndexComplete();

            Runnable informationUiUpdater = createInformationUiUpdater(itemCount, itemPriceCount,
                    itemHistoryCount);
            Runnable statusUiUpdater = createStatusUiUpdater(isSearchIndexComplete);
            mModel.getExecutorAccess().getUiThreadExecutor().execute(informationUiUpdater);
            mModel.getExecutorAccess().getUiThreadExecutor().execute(statusUiUpdater);
        };
    }


    /**
     * Creates a runnable to update the UI with the information
     *
     * @param itemCount        the amount of distinct items in the database
     * @param itemPriceCount   the count of items in the item price table
     * @param itemHistoryCount the count of items in the history item price table
     * @return the runnable to update the UI with the information
     */
    private Runnable createInformationUiUpdater(final int itemCount,
                                                final int itemPriceCount,
                                                final int itemHistoryCount) {
        return () -> {
            mView.updateItemCount(itemCount);
            mView.updateItemPriceCount(itemPriceCount);
            mView.updateHistoryCount(itemHistoryCount);
        };
    }


    /**
     * Creates a runnable to update the UI with the status
     *
     * @param isSearchIndexComplete <code>true</code> if the search index is complete <br>
     *                              <code>false</code> if the update of the search index is still
     *                              not complete
     * @return the runnable to update the  status
     */
    private Runnable createStatusUiUpdater(final boolean isSearchIndexComplete) {
        return () -> {
            int resourceId;
            int color;
            // determine text & color depending on the search index flag
            if (isSearchIndexComplete) {
                resourceId = R.string.overview_index_sync_complete;
                color = R.color.core_highlight_3;
            } else {
                resourceId = R.string.overview_index_sync_incomplete;
                color = R.color.core_highlight_1;
            }
            mView.updateSearchIndex(resourceId, color);
        };
    }
}

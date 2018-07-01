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
package com.scavi.de.gw2imp.background.collector;

import android.util.Log;

import com.scavi.de.gw2imp.background.collector.data.IDataProcessor;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemCollector extends Thread {
    private static final String TAG = "ItemPriceCollector";

    // item collector iteration timer
    private static final long ITERATION_MS = 120 * 60 * 1000;
    private final IDataProcessor mItemDataProcessor;

    /**
     * Constructor
     *
     * @param itemDataProcessor the item data processor that will determine and process all data
     */
    public ItemCollector(final IDataProcessor itemDataProcessor) {
        mItemDataProcessor = itemDataProcessor;
    }


    /**
     * The purpose of this thread is to determine the price course of each item.
     * As long as the thread is not interrupted, this method initiates the loading of prices and
     * their items. Items will be loaded once. Items and prices will be stored into the local
     * database.
     * Prices of the last month will be stored in a history data set.
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            List<Integer> allCommerceItemIds = mItemDataProcessor.loadAllIds();
            mItemDataProcessor.loadData(allCommerceItemIds);
            waitMs(ITERATION_MS);
        }
    }


    /**
     * Waits the given ms
     *
     * @param toWait the ms to wait
     */
    private void waitMs(final long toWait) {
        synchronized (this) {
            try {
                wait(toWait);
            } catch (InterruptedException ex) {
                Log.e(TAG, "Interrupted: " + ex.getMessage(), ex);
            }
        }
    }
}

package com.scavi.de.gw2imp.background.collector;

import android.util.Log;

import com.scavi.androidimp.util.DateHelper;
import com.scavi.de.gw2imp.background.collector.data.IDataProcessor;

public class ItemHistoryCollector extends Thread {
    private static final String TAG = "ItemHistoryCollector";
    private static final long BUFFER = 1000 * 60 * 60 * 3; // hour
    private final IDataProcessor mItemDataProcessor;

    /**
     * Constructor
     *
     * @param itemDataProcessor the item data processor that will determine and process all data
     */
    public ItemHistoryCollector(final IDataProcessor itemDataProcessor) {
        mItemDataProcessor = itemDataProcessor;
    }


    @Override
    public void run() {
        while (!isInterrupted()) {
            mItemDataProcessor.updateHistory();
            long timeToWait = DateHelper.timeToNextMonth(System.currentTimeMillis());
            waitMs(timeToWait + BUFFER);
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
                if (toWait > 0) {
                    wait(toWait);
                }
            } catch (InterruptedException ex) {
                Log.e(TAG, ex.getMessage(), ex); // TODO
            }
        }
    }
}

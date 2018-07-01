package com.scavi.de.gw2imp.background.collector;

import android.content.Context;
import android.util.Log;

import com.google.common.base.MoreObjects;
import com.scavi.de.gw2imp.background.collector.data.IDataProcessor;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.data.db.routine.ItemRoutines;
import com.scavi.de.gw2imp.data.entity.item.ItemEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemSearchEntity;
import com.scavi.de.gw2imp.preferences.IPreferenceAccess;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class SearchIndexUpdater extends Thread {
    private static final String TAG = "SearchIndexUpdater";

    // item collector iteration timer
    private static final long ITERATION_MS = 60 * 60 * 1000;
    private static final int MAX_DICTIONARY_ITERATIONS = 3;
    private final IDataProcessor mItemDataProcessor;
    private final IDatabaseAccess mDatabaseAccess;
    private final IPreferenceAccess mPreferences;
    private final Context mContext;

    /**
     * Constructor
     *
     * @param context           the context to global information about the application environment
     * @param itemDataProcessor the item data processor that will determine and process all data
     * @param databaseAccess    the access to the database
     * @param preferences       the access to the preferences of the application
     */
    public SearchIndexUpdater(final Context context,
                              final IDataProcessor itemDataProcessor,
                              final IDatabaseAccess databaseAccess,
                              final IPreferenceAccess preferences) {
        mContext = context;
        mItemDataProcessor = itemDataProcessor;
        mDatabaseAccess = databaseAccess;
        mPreferences = preferences;
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
            //mDatabaseAccess.itemsDAO().deleteSearchIndex();
            if (isDictionaryToUpdate()) {
                updateDictionary();
            }

            while (!ItemRoutines.isSearchIndexComplete(mDatabaseAccess)) {
                mItemDataProcessor.updateItemSearchIndex();
            }
            waitMs(ITERATION_MS);
        }
    }

    private void updateDictionary() {
        //int id = mPreferences.readWordIndex(mContext);
        //Set<String> searchDictionary = null;

        Set<String> searchDictionary = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        searchDictionary.addAll(mDatabaseAccess.itemsDAO().selectSearchItemDictionary());

        for (int i = 0; i < 5; ++i) {
            int id = 0;
            ItemSearchEntity nextItem;

            while ((nextItem = mDatabaseAccess.itemsDAO().selectNextSearchItemIndex(id)) != null) {
                // lazy initialization of the search dictionary with the first iteration
//                if (searchDictionary == null) {
//                    searchDictionary = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
//                    searchDictionary.addAll(mDatabaseAccess.itemsDAO()
// .selectSearchItemDictionary());
//                }

                Set<String> foundWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
                // search for all possible known words for the current search item with the given
                // dictionary
                searchWords(nextItem, searchDictionary, new StringBuilder(), foundWords, 0);
                if (foundWords.size() > 0) {
                    // updates the dictionary with the recent found words
                    searchDictionary.addAll(foundWords);

                    // TODO insert the found search words for all search items with the current
                    // search item part name
                } else {
                    Log.v(TAG, "No dictionary update necessary from " + nextItem.getNamePart());
                }

                // item was processed, update the id / index
                id = nextItem.getId();
                mPreferences.writeWordIndex(mContext, id);
            }
        }
        Log.v(TAG, "foo");
    }

    private boolean isDictionaryToUpdate() {
        return mPreferences.readDictionaryIteration(mContext) < MAX_DICTIONARY_ITERATIONS;
    }


    private void updateDictionaryIteration() {
        int currentIteration = mPreferences.readDictionaryIteration(mContext);
        if (currentIteration < MAX_DICTIONARY_ITERATIONS) {
            mPreferences.writeDictionaryIteration(mContext, currentIteration + 1);
        }
    }

    public void searchWords(final ItemSearchEntity searchItem,
                            final Set<String> searchDictionary,
                            final StringBuilder currentWord,
                            final Set<String> foundWords,
                            int pos) {
        if (pos == searchItem.getNamePart().length()) {
            return;
        }

        for (int i = pos; i < searchItem.getNamePart().length(); ++i) {
            currentWord.append(searchItem.getNamePart().charAt(i));
            boolean isExisting = searchDictionary.contains(currentWord.toString());
            // found a matching word, continue to look for further words from this point
            if (isExisting && !searchItem.getNamePart().equalsIgnoreCase(currentWord.toString())) {
                foundWords.add(currentWord.toString());
                searchWords(searchItem, searchDictionary, new StringBuilder(), foundWords, i + 1);
            }
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
                Log.e(TAG, "Interrupted: " + ex.getMessage(), ex);
            }
        }
    }
}


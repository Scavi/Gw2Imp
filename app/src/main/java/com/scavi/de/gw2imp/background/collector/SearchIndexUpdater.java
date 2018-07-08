package com.scavi.de.gw2imp.background.collector;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.google.common.base.Strings;
import com.scavi.de.gw2imp.background.collector.data.IDataProcessor;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.data.db.routine.ItemRoutines;
import com.scavi.de.gw2imp.data.entity.item.ItemSearchEntity;
import com.scavi.de.gw2imp.preferences.IPreferenceAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class SearchIndexUpdater extends Thread {
    private static final String TAG = "SearchIndexUpdater";

    // item collector iteration timer
    private static final long ITERATION_MS = 60 * 60 * 1000;
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
            updateDictionary(); // TODO remove and use it after the updateItemSearchIndex (currently only for test)
            while (!ItemRoutines.isSearchIndexComplete(mDatabaseAccess)) {
                mItemDataProcessor.updateItemSearchIndex();
            }
            updateDictionary();
            waitMs(ITERATION_MS);
        }
    }

    private void updateDictionary() {
        if (mPreferences.readIsWordIndexComplete(mContext)) {
            return;
        }

        int countBefore = mDatabaseAccess.itemsDAO().selectItemSearchCount();
        Set<String> searchDictionary = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        searchDictionary.addAll(mDatabaseAccess.itemsDAO().selectSearchItemDictionary());

        int id = 0;
        ItemSearchEntity nextItem;

        while ((nextItem = mDatabaseAccess.itemsDAO().selectNextSearchItemIndex(id)) != null) {
            Set<String> foundWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
            // search for all possible known words for the current search item with the given dic
            searchWords(nextItem, searchDictionary, foundWords);
            if (foundWords.size() > 0) {
                List<ItemSearchEntity> toUpdate = ItemSearchEntity.from(nextItem, foundWords);
                mDatabaseAccess.itemsDAO().insertItemSearchParts(toUpdate);
            }
            // item was processed, update the id / index
            id = nextItem.getId();
        }
        int countAfter = mDatabaseAccess.itemsDAO().selectItemSearchCount();
        boolean isUnchanged = countBefore == countAfter && countBefore > 0;
        mPreferences.writeIsWordIndexComplete(mContext, isUnchanged);
    }


    /**
     * This method searches recursively through the current search item and tries to find
     * matching part names in the dictionary
     *
     * @param searchItem       the item part to search
     * @param searchDictionary the dictionary with all known words
     * @param foundWords       all words found so far that match a part of the given item part
     *                         and dictionary entries
     * @return <code>true</code> the item part completely translated into the dictionary<br>
     * <code>false</code> the item part can not be translated into the dictionary
     */

    @VisibleForTesting
    protected boolean searchWords(@NonNull final ItemSearchEntity searchItem,
                                  @NonNull final Set<String> searchDictionary,
                                  @NonNull Set<String> foundWords) {
        if (Strings.isNullOrEmpty(searchItem.getNamePart())) {
            return false;
        }
        searchWords(searchItem, searchDictionary, new StringBuilder(), foundWords, 0);
        // TODO: lets start without this first to get more words into the dictionary and see how
        // it goes
        return true;
    }


    /**
     * This method searches recursively through the current search item and tries to find
     * matching part names in the dictionary
     *
     * @param searchItem       the item part to search
     * @param searchDictionary the dictionary with all known words
     * @param currentWord      the current word during the lookup process
     * @param foundWords       all words found so far that match a part of the given item part
     *                         and dictionary entries
     * @param pos              the current position of teh search item part
     * @return <code>true</code> the item part completely translated into the dictionary<br>
     * <code>false</code> the item part can not be translated into the dictionary
     */
    private void searchWords(@NonNull final ItemSearchEntity searchItem,
                             @NonNull final Set<String> searchDictionary,
                             @NonNull final StringBuilder currentWord,
                             @NonNull final Set<String> foundWords,
                             int pos) {
        for (int i = pos; i < searchItem.getNamePart().length(); ++i) {
            currentWord.append(searchItem.getNamePart().charAt(i));
            boolean isExisting = searchDictionary.contains(currentWord.toString());
            // found a matching word, continue to look for further words from this point
            if (isExisting && !searchItem.getNamePart().equalsIgnoreCase(currentWord.toString())) {
                foundWords.add(currentWord.toString());
                searchWords(searchItem, searchDictionary, new StringBuilder(),
                        foundWords, i + 1);
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


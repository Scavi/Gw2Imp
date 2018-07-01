package com.scavi.de.gw2imp.background.collector;

import com.scavi.de.gw2imp.background.collector.SearchIndexUpdater;
import com.scavi.de.gw2imp.data.entity.item.ItemSearchEntity;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class SearchIndexUpdaterTest {

    /**
     * Tests that 4 items will be found including a word that begins at the first character and
     * another word that ends with the last character of the entity
     */
    @Test
    public void testDictionarySearchHasEntries() {
        // setup
        ItemSearchEntity searchItem = new ItemSearchEntity(1, "Eisenerz");
        Set<String> searchDictionary = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        searchDictionary.addAll(Arrays.asList("Eis", "en", "Erz", "Eisen"));
        Set<String> foundWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        // execute
        SearchIndexUpdater searchIndexer = new SearchIndexUpdater(null, null, null, null);
        boolean hasEntries = searchIndexer.searchWords(searchItem, searchDictionary, foundWords);

        // verify
        Assert.assertTrue(hasEntries);
        Assert.assertEquals(4, foundWords.size());
        Assert.assertTrue(foundWords.contains("eis"));
        Assert.assertTrue(foundWords.contains("en"));
        Assert.assertTrue(foundWords.contains("erz"));
        Assert.assertTrue(foundWords.contains("eisen"));
    }

    /**
     * Tests that no item will be added if the the word is equal to the dictionary
     */
    @Test
    public void testDictionarySearchHasNoNewEntry() {
        // setup
        ItemSearchEntity searchItem = new ItemSearchEntity(1, "Eisenerz");
        Set<String> searchDictionary = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        searchDictionary.add("Eisenerz");
        Set<String> foundWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        // execute
        SearchIndexUpdater searchIndexer = new SearchIndexUpdater(null, null, null, null);
        boolean hasEntries = searchIndexer.searchWords(searchItem, searchDictionary, foundWords);

        // verify
        Assert.assertFalse(hasEntries);
        Assert.assertEquals(0, foundWords.size());
    }

// TODO: currently we will add all words
//    /**
//     * In this test, Eisener can't get completed. Due to this, no entries will be returned
//     * because the given search item can't get completely translated into dictionary words
//     */
//    @Test
//    public void testDictionarySearchHasIncompleteEntry() {
//        // setup
//        ItemSearchEntity searchItem = new ItemSearchEntity(1, "Eisener");
//        Set<String> searchDictionary = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
//        searchDictionary.addAll(Arrays.asList("Eis", "en", "Erz", "Eisen"));
//        Set<String> foundWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
//
//        // execute
//        SearchIndexUpdater searchIndexer = new SearchIndexUpdater(null, null, null, null);
//        boolean hasEntries = searchIndexer.searchWords(searchItem, searchDictionary, foundWords);
//
//        // verify
//        Assert.assertFalse(hasEntries);
//        Assert.assertEquals(0, foundWords.size());
//    }
}

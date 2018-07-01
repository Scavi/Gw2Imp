package com.scavi.util;

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
    public void testDictionarySearch() {
        // setup
        ItemSearchEntity searchItem = new ItemSearchEntity(1, "Eisenerz");
        Set<String> searchDictionary = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        searchDictionary.addAll(Arrays.asList("Eis", "en", "Erz", "Eisen"));
        Set<String> foundWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        // execute
        SearchIndexUpdater searchIndexer = new SearchIndexUpdater(null, null, null, null);
        searchIndexer.searchWords(searchItem, searchDictionary, new StringBuilder(), foundWords, 0);

        // verify
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
    public void testDictionarySearch2() {
        // setup
        ItemSearchEntity searchItem = new ItemSearchEntity(1, "Eisenerz");
        Set<String> searchDictionary = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        searchDictionary.add("Eisenerz");
        Set<String> foundWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        // execute
        SearchIndexUpdater searchIndexer = new SearchIndexUpdater(null, null, null, null);
        searchIndexer.searchWords(searchItem, searchDictionary, new StringBuilder(), foundWords, 0);

        // verify
        Assert.assertEquals(0, foundWords.size());
    }
}

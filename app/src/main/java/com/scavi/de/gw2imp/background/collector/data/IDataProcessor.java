package com.scavi.de.gw2imp.background.collector.data;

import java.util.List;

public interface IDataProcessor {
    /**
     * Loads the item IDs that needs to be processed
     *
     * @return a list of all item IDs in the current context
     */
    List<Integer> loadAllIds();

    /**
     * This method loads the data to the given item IDs that have a commerce context. Currently
     * there are around 25000 out of 88000 commerce items.
     * After that, the method initiates the processing.
     *
     * @param allIds all item ids
     */
    void loadData(final List<Integer> allIds);


    /**
     * This method will be used to update older items
     */
    void updateHistory();
}

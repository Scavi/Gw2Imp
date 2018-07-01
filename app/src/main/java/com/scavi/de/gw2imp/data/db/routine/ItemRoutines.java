package com.scavi.de.gw2imp.data.db.routine;

import android.support.annotation.NonNull;

import com.scavi.de.gw2imp.data.db.IDatabaseAccess;

public class ItemRoutines {

    /**
     * This method determines the count of all items and the count of the indexed search items.
     * Both needs to be equal to show that the search index is current
     *
     * @param databaseAccess the database access of this application
     * @return <code>true</code> if the search index is complete <br>
     * <code>false</code> if the update of the search index is still not complete
     */
    public static boolean isSearchIndexComplete(@NonNull final IDatabaseAccess databaseAccess) {
        int itemCount = databaseAccess.itemsDAO().selectItemCount();
        int indexCount = databaseAccess.itemsDAO().selectItemSearchCount();
        return itemCount == indexCount;
    }
}

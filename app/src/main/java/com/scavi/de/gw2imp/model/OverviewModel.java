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
package com.scavi.de.gw2imp.model;

import android.content.Context;

import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.data.db.routine.ItemRoutines;
import com.scavi.de.gw2imp.preferences.IPreferenceAccess;
import com.scavi.de.gw2imp.util.RoutingState;

public class OverviewModel extends AbstractModel {
    private final IDatabaseAccess mDatabaseAccess;
    private final IExecutorAccess mExecutorAccess;

    /**
     * Constructor
     *
     * @param context        the context to global information about the application environment
     * @param databaseAccess the database access of this application
     * @param executorAccess to access the main and background threads
     */
    public OverviewModel(final Context context,
                         final IDatabaseAccess databaseAccess,
                         final IExecutorAccess executorAccess) {
        super(context);
        mDatabaseAccess = databaseAccess;
        mExecutorAccess = executorAccess;
    }


    /**
     * @return the amount of distinct items in the database
     */
    public int selectItemCount() {
        return mDatabaseAccess.itemsDAO().selectItemCount();
    }


    /**
     * @return the count of items in the item price table
     */
    public int selectItemPriceCount() {
        return mDatabaseAccess.itemsDAO().selectItemPriceCount();
    }


    /**
     * @return the count of items in the history item price table
     */
    public int selectItemPriceHistoryCount() {
        return mDatabaseAccess.itemsDAO().selectItemPriceHistoryCount();
    }


    /**
     * This method determines the count of all items and the count of the indexed search items.
     * Both needs to be equal to show that the search index is current
     *
     * @return <code>true</code> if the search index is complete <br>
     * <code>false</code> if the update of the search index is still not complete
     */
    public boolean selectIsSearchIndexComplete() {
        return ItemRoutines.isSearchIndexComplete(mDatabaseAccess);
    }


    /**
     * @return to access the main and background threads
     */
    public IExecutorAccess getExecutorAccess() {
        return mExecutorAccess;
    }
}

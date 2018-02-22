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
package com.scavi.de.gw2imp.model;

import android.content.Context;
import android.support.annotation.WorkerThread;
import android.text.TextWatcher;

import com.google.common.util.concurrent.FutureCallback;
import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.data.entity.item.ItemEntity;
import com.scavi.de.gw2imp.data.entity.raid.RaidEntity;
import com.scavi.de.gw2imp.ui.util.DelayedTextFieldWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class TradingItemsModel {
    public static final int TRADING_ITEM_DELAY_MS = 2000;
    private final Context mContext;
    private final IDatabaseAccess mImpDatabase;
    private final IExecutorAccess mExecutorAccess;

    /**
     * Constructor
     *
     * @param context        the context to global information about the application environment
     * @param impDatabase    the database access of this application
     * @param executorAccess to access the main and background threads
     */
    public TradingItemsModel(final Context context,
                             final IDatabaseAccess impDatabase,
                             final IExecutorAccess executorAccess) {
        mContext = context;
        mImpDatabase = impDatabase;
        mExecutorAccess = executorAccess;
    }


    @WorkerThread
    public List<ItemEntity> selectItemsToName(String name) {
        if (name == null || name.isEmpty()) {
            return new ArrayList<>(0);
        }
        name = name.endsWith("%") ? name : name + "%";
        return mImpDatabase.itemsDAO().selectItems(name);
    }


    /**
     * @return to access the main and background threads
     */
    public IExecutorAccess getExecutorAccess() {
        return mExecutorAccess;
    }



}

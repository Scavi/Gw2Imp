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
package com.scavi.de.gw2imp.dagger2.module;

import android.content.Context;

import com.scavi.de.gw2imp.background.collector.ItemCollector;
import com.scavi.de.gw2imp.communication.access.ICommerceAccess;
import com.scavi.de.gw2imp.communication.access.IItemAccess;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.preferences.IPreferenceAccess;

import dagger.Module;
import dagger.Provides;

@Module
public class DataCollectionServiceModule {
    /**
     * @param databaseAccess the access to the database
     * @param itemAccess     the server side access to the general
     *                       items
     * @param commerceAccess the server side access to the commerce
     *                       items
     * @return the item price collector is responsible to select items, their prices and updates
     * item price history information
     */
    @Provides
    public ItemCollector provideItemPriceCollector(final IDatabaseAccess databaseAccess,
                                                   final IItemAccess itemAccess,
                                                   final ICommerceAccess commerceAccess) {
        return new ItemCollector(databaseAccess, itemAccess, commerceAccess);
    }
}

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
package com.scavi.de.gw2imp.dagger2.component;

import com.scavi.de.gw2imp.background.DataCollectionService;
import com.scavi.de.gw2imp.background.collector.ItemCollector;
import com.scavi.de.gw2imp.dagger2.module.DataCollectionServiceModule;
import com.scavi.de.gw2imp.util.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = DataCollectionServiceModule.class)
public interface DataCollectionServiceComponent {

    /**
     * The inject method of the activity for the dagger component
     *
     * @param backgroundService the background service of the application
     */
    void inject(final DataCollectionService backgroundService);


    /**
     * @return the item price collector is responsible to select items, their prices and updates
     * item price history information
     */
    ItemCollector getItemPriceCollector();
}

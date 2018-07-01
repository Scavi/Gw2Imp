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
package com.scavi.de.gw2imp.data.db;

import com.scavi.de.gw2imp.data.dao.IAchievementDAO;
import com.scavi.de.gw2imp.data.dao.IItemsDAO;
import com.scavi.de.gw2imp.data.dao.IRaidDAO;
import com.scavi.de.gw2imp.data.dao.ITimedEventsDAO;
import com.scavi.de.gw2imp.data.dao.ITrendDAO;

public interface IDatabaseAccess {
    /**
     * @return the access to the raid table
     */
    IRaidDAO raidDAO();


    /**
     * @return the access to the achievement table and it's embedded tables
     */
    IAchievementDAO achievementDAO();


    /**
     * @return the access to the timed events (e.g. world bosses)
     */
    ITimedEventsDAO timedEventsDAO();


    /**
     * @return the access to the items
     */
    IItemsDAO itemsDAO();


    /**
     * @return the access to the trends (e.g. increasing and decreasing item prices)
     */
    ITrendDAO trendDAO();
}

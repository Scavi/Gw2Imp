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
package com.scavi.de.gw2imp.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.scavi.de.gw2imp.data.dao.IAchievementDAO;
import com.scavi.de.gw2imp.data.dao.IItemsDAO;
import com.scavi.de.gw2imp.data.dao.IRaidDAO;
import com.scavi.de.gw2imp.data.dao.ITimedEventsDAO;
import com.scavi.de.gw2imp.data.entity.achievement.AchievementEntity;
import com.scavi.de.gw2imp.data.entity.achievement.FlagsEntity;
import com.scavi.de.gw2imp.data.entity.achievement.RewardEntity;
import com.scavi.de.gw2imp.data.entity.achievement.TierEntity;
import com.scavi.de.gw2imp.data.entity.event.WorldBossEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceHistoryEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemSearchEntity;
import com.scavi.de.gw2imp.data.entity.item.TrendEntity;
import com.scavi.de.gw2imp.data.entity.raid.RaidEntity;

@Database(entities =
        {RaidEntity.class, AchievementEntity.class, FlagsEntity.class, RewardEntity.class,
                TierEntity.class, WorldBossEntity.class, ItemEntity.class, ItemPriceEntity.class,
                ItemPriceHistoryEntity.class, TrendEntity.class, ItemSearchEntity.class},
        version = 3)
public abstract class Gw2ImpDatabase extends RoomDatabase implements IDatabaseAccess {

    /**
     * @return the access to the raid table
     */
    public abstract IRaidDAO raidDAO();


    /**
     * @return the access to the achievement table and it's embedded tables
     */
    public abstract IAchievementDAO achievementDAO();


    /**
     * @return the access to the timed events (e.g. world bosses)
     */
    public abstract ITimedEventsDAO timedEventsDAO();


    /**
     * @return the access to all item information (e.g. id, name, url, icon)
     */
    public abstract IItemsDAO itemsDAO();
}

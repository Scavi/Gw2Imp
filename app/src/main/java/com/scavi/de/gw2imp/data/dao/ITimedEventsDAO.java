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
package com.scavi.de.gw2imp.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scavi.de.gw2imp.data.entity.event.WorldBossEntity;
import com.scavi.de.gw2imp.data.util.DbConst;

import java.util.List;

@Dao
public interface ITimedEventsDAO {

    /**
     * Selects all world bosses within the given interval
     *
     * @param from minutes of the day from
     * @param till minutes of the day till
     * @return all world boss in the given from / to interval
     */
    @Query("SELECT * FROM " + DbConst.TABLE_WORLD_BOSSES +
            " WHERE startMinutes >= :from AND startMinutes <= :till ORDER BY startMinutes ASC")
    List<WorldBossEntity> selectWorldBossesInInterval(final int from,
                                                      final int till);

    /**
     * Inserts the given world bosses into the table
     *
     * @param worldBoss the world bosses
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWorldBosses(final List<WorldBossEntity> worldBoss);
}

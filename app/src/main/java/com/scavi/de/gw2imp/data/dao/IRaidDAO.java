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
package com.scavi.de.gw2imp.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scavi.de.gw2imp.data.entity.raid.RaidEntity;
import com.scavi.de.gw2imp.data.util.DbConst;

import java.util.List;


@Dao
public interface IRaidDAO {
    /**
     * @return all raids from the DAO
     */
    @Query("SELECT * FROM " + DbConst.TABLE_RAID)
    List<RaidEntity> selectAll();

    /**
     * Inserts all raids
     *
     * @param raids the raids to insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(final List<RaidEntity> raids);
}

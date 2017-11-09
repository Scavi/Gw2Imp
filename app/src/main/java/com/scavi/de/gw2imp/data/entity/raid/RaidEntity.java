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
package com.scavi.de.gw2imp.data.entity.raid;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scavi.de.gw2imp.communication.response.misc.Event;
import com.scavi.de.gw2imp.communication.response.misc.RaidData;
import com.scavi.de.gw2imp.communication.response.misc.Wing;
import com.scavi.de.gw2imp.data.util.DbConst;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@Entity(tableName = DbConst.TABLE_RAID)
@ParametersAreNonnullByDefault
public class RaidEntity {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "raid_context")
    private final String mRaidContext;
    @NonNull
    @ColumnInfo(name = "raid")
    private final String mRaid;
    @NonNull
    @ColumnInfo(name = "type")
    private final String mType;
    @Ignore
    private boolean mIsCompleted;


    /**
     * Constructor
     *
     * @param raid        the raid (e.g. spirit vale)
     * @param raidContext the raid id (e.g. vale guardian or spirit woods)
     * @param type        the type (e.g. Boss or Checkpoint)
     */
    public RaidEntity(final String raid,
                      final String raidContext,
                      final String type) {
        mRaid = raid;
        mRaidContext = raidContext;
        mType = type;
    }


    /**
     * @return the raid (e.g. spirit vale)
     */
    @NonNull
    public String getRaid() {
        return mRaid;
    }


    /**
     * @return the raid id (e.g. vale guardian or spirit woods)
     */
    @NonNull
    public String getRaidContext() {
        return mRaidContext;
    }


    /**
     * @return the type (e.g. Boss or Checkpoint)
     */
    @NonNull
    public String getType() {
        return mType;
    }


    /**
     * @return <c>true</c> the raid was already done this week <br/>
     * <c>false</c> the raid was not already done this week.
     */
    public boolean isCompleted() {
        return mIsCompleted;
    }


    /**
     * @param isCompleted <c>true</c> the raid was already done this week <br/>
     *                    <c>false</c> the raid was not already done this week.
     */
    public void setIsCompleted(final boolean isCompleted) {
        mIsCompleted = isCompleted;
    }


    /**
     * Helpe method to convert the {@link RaidData} objects, that are received from the server side
     * into the local {@link RaidEntity} object
     *
     * @param raids the data model of the backend / server side
     * @return the local data model
     */
    @NonNull
    public static List<RaidEntity> from(@Nullable final List<RaidData> raids) {
        if (raids == null || raids.size() == 0) {
            return new ArrayList<>(0);
        }
        List<RaidEntity> result = new ArrayList<>();
        for (RaidData raid : raids) {
            for (Wing wing : raid.getWings()) {
                for (Event event : wing.getEvents()) {
                    result.add(new RaidEntity(wing.getId(), event.getId(), event.getType()));
                }
            }
        }
        return result;
    }


    /**
     * Divide the raid information from the server side by sections. The section is defined by
     * it's raid. This is required for adapters.
     *
     * @param raids the known raids
     * @return the raids list with sections
     */
    @NonNull
    public static List<RaidEntity> injectSections(final List<RaidEntity> raids) {
        String lastSectionName = null;
        for (int i = 0; i < raids.size(); ++i) {
            RaidEntity raid = raids.get(i);
            if (i == 0 || !raid.getRaid().equals(lastSectionName)) {
                lastSectionName = raid.getRaid();
                raids.add(i, new RaidEntity(lastSectionName, "", ""));
            }
        }
        return raids;
    }
}

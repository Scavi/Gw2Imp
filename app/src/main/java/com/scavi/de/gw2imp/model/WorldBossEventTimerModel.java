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
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.google.common.util.concurrent.FutureCallback;
import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.data.dao.ITimedEventsDAO;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.data.entity.event.WorldBossEntity;
import com.scavi.de.gw2imp.ui.adapter.WorldBossesAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Callable;

public class WorldBossEventTimerModel extends AbstractModel {
    private final int DAY_MAX_MINUTES = 1439;
    private final int PREV_MINUTES = 10;
    private final int INTERVAL = 120; // 2 hours
    private final IDatabaseAccess mImpDatabase;
    private final IExecutorAccess mExecutorAccess;


    /**
     * Constructor
     *
     * @param context        the context to global information about the application environment
     * @param executorAccess to access the main and background threads
     * @param impDatabase    the database access of this application
     */
    public WorldBossEventTimerModel(final Context context,
                                    final IDatabaseAccess impDatabase,
                                    final IExecutorAccess executorAccess) {
        super(context);
        mImpDatabase = impDatabase;
        mExecutorAccess = executorAccess;
    }


    /**
     * This method determines the world bosses between that from currently active until the next
     * two hours. In case, the bosses are not yet persisted, this method initiates the insertion
     *
     * @param callBack the callback to process the result
     */
    @WorkerThread
    public void determineUpcomingWorldBosses(final FutureCallback<List<WorldBossEntity>> callBack) {
        Callable<List<WorldBossEntity>> callable = () -> {
            // TODO ugly hack
            int daylightSaving = TimeZone.getDefault().inDaylightTime(new Date()) ? 60 : 0;
            int from = getMinutesOfDay() + daylightSaving;
            int till = from + INTERVAL;

            // max 7 boss for each hour
            List<WorldBossEntity> bosses;
            // the interval of the bosses is within the current day
            if (till <= DAY_MAX_MINUTES) {
                bosses = selectAndInsertIfNotExists(Math.max(0, from - PREV_MINUTES), till);
            }
            // the interval is also for the next day
            else {
                bosses = selectAndInsertIfNotExists(Math.max(0, from - PREV_MINUTES),
                        DAY_MAX_MINUTES);
                bosses.addAll(selectAndInsertIfNotExists(0, till % DAY_MAX_MINUTES));
            }
            return adaptToSummerTime(bosses);
        };
        mExecutorAccess.executeBackgroundTask(callable, callBack);
    }

    // TODO ugly hack
    private List<WorldBossEntity> adaptToSummerTime(@NonNull final List<WorldBossEntity> bosses) {
        if (!TimeZone.getDefault().inDaylightTime(new Date())) {
            return bosses;
        }
        for (WorldBossEntity worldBoss : bosses) {
            worldBoss.changeToDailightSaving();
        }
        return bosses;
    }


    /**
     * @return Determines the minutes of the day
     */
    private int getMinutesOfDay() {
        Calendar now = Calendar.getInstance();
        int hours = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
        return (hours * 60) + minutes;
    }


    /**
     * This method tries to select all world bosses in the given minute interval between from and
     * till. In case no entries exists all possible world bosses will be inserted into the database
     *
     * @param from interval from
     * @param till interval to
     * @return the list with world bosses during that interval
     */
    private List<WorldBossEntity> selectAndInsertIfNotExists(final int from,
                                                             final int till) {
        if (from > till || from < 0 || till > DAY_MAX_MINUTES || from > DAY_MAX_MINUTES) {
            return new ArrayList<>(0);
        }
        ITimedEventsDAO dao = mImpDatabase.timedEventsDAO();
        List<WorldBossEntity> worldBosses = dao.selectWorldBossesInInterval(from, till);
        if (toRefreshRequired(worldBosses)) {
            // get all hard coded world bosses due to the missing functionality in the API
            List<WorldBossEntity> allBosses = WorldBossEntity.createWorldBossEventTimer();
            mImpDatabase.timedEventsDAO().insertWorldBosses(allBosses);
            worldBosses = dao.selectWorldBossesInInterval(from, till);
        }
        return worldBosses;
    }


    /**
     * Determines if the world boss table needs to get refreshed in the database table
     *
     * @param worldBosses the selected world bosses
     * @return <code>true</code> needs to get refreshed<br/>
     * <code>false</code> doesn't need to get refreshed
     */
    private boolean toRefreshRequired(final List<WorldBossEntity> worldBosses) {
        return worldBosses.size() == 0;
    }


    /**
     * @return to access the main and background threads
     */
    public IExecutorAccess getExecutorAccess() {
        return mExecutorAccess;
    }
}

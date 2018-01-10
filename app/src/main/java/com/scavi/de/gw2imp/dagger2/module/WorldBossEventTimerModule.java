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
import android.support.annotation.NonNull;

import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.model.WorldBossEventTimerModel;
import com.scavi.de.gw2imp.presenter.WorldBossEventTimerPresenter;
import com.scavi.de.gw2imp.ui.view.IWorldBossEventTimerView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class WorldBossEventTimerModule {
    private final IWorldBossEventTimerView mView;

    /**
     * Constructor
     *
     * @param view the view for the world boss event timer
     */
    public WorldBossEventTimerModule(final IWorldBossEventTimerView view) {
        this.mView = view;
    }

    /**
     * @return the view for the world boss event timer
     */
    @Provides
    @Nonnull
    public IWorldBossEventTimerView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of the world boss event timer
     * @return the presenter of the MVP pattern in the context of the world boss event timer
     */
    @Provides
    @NonNull
    public WorldBossEventTimerPresenter providePresenter(final WorldBossEventTimerModel model) {
        return new WorldBossEventTimerPresenter(mView, model);
    }


    /**
     * @param context        the context to global information about the application environment
     * @param executorAccess to access the main and background threads
     * @param impDatabase    the database access of this application
     * @return the model of the MVP pattern in the context of the world boss event timer
     */
    @Provides
    @NonNull
    public WorldBossEventTimerModel provideModel(final Context context,
                                                 final IDatabaseAccess impDatabase,
                                                 final IExecutorAccess executorAccess) {
        return new WorldBossEventTimerModel(context, impDatabase, executorAccess);
    }
}
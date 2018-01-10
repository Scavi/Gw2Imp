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
package com.scavi.de.gw2imp.presenter;


import com.google.common.util.concurrent.FutureCallback;
import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.data.entity.event.WorldBossEntity;
import com.scavi.de.gw2imp.model.WorldBossEventTimerModel;
import com.scavi.de.gw2imp.ui.view.IWorldBossEventTimerView;

import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

@ParametersAreNonnullByDefault
public class WorldBossEventTimerPresenter {
    private final IWorldBossEventTimerView mView;
    private final WorldBossEventTimerModel mModel;

    /**
     * Constructor
     *
     * @param view  the view for the world boss events
     * @param model the model for the world boss events
     */
    @Inject
    public WorldBossEventTimerPresenter(final IWorldBossEventTimerView view,
                                        final WorldBossEventTimerModel model) {
        mView = view;
        mModel = model;
    }

    /**
     * Shows the progress and initiates the asynchronous loading of the current and next world
     * boss events
     */
    public void loadWorldBoss() {
        mView.onShowProgress();
        FutureCallback<List<WorldBossEntity>> callback = createWorldBossCallback();
        mModel.determineUpcomingWorldBosses(callback);
    }


    /**
     * Creates the callback to process the world boss result to the view and hide the processing
     * window
     *
     * @return the callback
     */
    private FutureCallback<List<WorldBossEntity>> createWorldBossCallback() {
        return new FutureCallback<List<WorldBossEntity>>() {
            @Override
            public void onSuccess(@Nullable final List<WorldBossEntity> result) {
                mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                    mView.setupWorldBossesView(result);
                    mView.onHideProgress();
                });
            }

            @Override
            public void onFailure(final Throwable t) {
                mModel.getExecutorAccess().getUiThreadExecutor().execute(() -> {
                    String errorInfo = mModel.getString(R.string.world_boss_data_access_error,
                            t.getLocalizedMessage());
                    mView.onHideProgressAfterError();
                    mView.showUserError(errorInfo);
                });
            }
        };
    }
}

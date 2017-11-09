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
package com.scavi.de.gw2imp.dagger2.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.communication.access.IAccountAccess;
import com.scavi.de.gw2imp.communication.access.IAchievementAccess;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.model.DailyModel;
import com.scavi.de.gw2imp.presenter.DailyPresenter;
import com.scavi.de.gw2imp.ui.view.IDailyView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class DailyModule {
    private final IDailyView mView;

    /**
     * Constructor
     *
     * @param view the view for the account
     */
    public DailyModule(final IDailyView view) {
        this.mView = view;
    }

    /**
     * @return the view for the account
     */
    @Provides
    @Nonnull
    public IDailyView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of the account
     * @return the presenter of the MVP pattern in the context of the account
     */
    @Provides
    @NonNull
    public DailyPresenter providePresenter(final DailyModel model) {
        return new DailyPresenter(mView, model);
    }


    /**
     * @param context           the context to global information about the application environment
     * @param accountAccess     the server side access the account data
     * @param achievementAccess the server side access to the achievement data
     * @param executorAccess    to access the main and background threads
     * @param impDatabase       the database access of this application
     * @return the model of the MVP pattern in the context of the account
     */
    @Provides
    @NonNull
    public DailyModel provideModel(final Context context,
                                   final IAccountAccess accountAccess,
                                   final IAchievementAccess achievementAccess,
                                   final IExecutorAccess executorAccess,
                                   final IDatabaseAccess impDatabase) {
        return new DailyModel(context, accountAccess, achievementAccess, executorAccess,
                impDatabase);
    }
}

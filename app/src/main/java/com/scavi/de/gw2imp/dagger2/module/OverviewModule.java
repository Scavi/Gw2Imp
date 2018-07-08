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
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.model.TradingItemsModel;
import com.scavi.de.gw2imp.preferences.IPreferenceAccess;
import com.scavi.de.gw2imp.model.OverviewModel;
import com.scavi.de.gw2imp.presenter.OverviewPresenter;
import com.scavi.de.gw2imp.ui.view.IOverviewView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;


@Module
@ParametersAreNonnullByDefault
public class OverviewModule {
    private final IOverviewView mView;

    /**
     * Constructor
     *
     * @param view the view of application overview
     */
    public OverviewModule(final IOverviewView view) {
        this.mView = view;
    }

    /**
     * @return the view of the application overview
     */
    @Provides
    @Nonnull
    public IOverviewView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of the application overview
     * @return the presenter of the MVP pattern in the context of the application overview
     */
    @Provides
    @NonNull
    public OverviewPresenter providePresenter(final OverviewModel model) {
        return new OverviewPresenter(mView, model);
    }

    /**
     * @param context          the context to global information about the application environment
     * @param impDatabase      the database access of this application
     * @param executorAccess   to access the main and background threads
     * @param preferenceAccess to the shared preferences of this application
     * @return the model of the MVP pattern in the context of the application overview
     */
    @Provides
    @NonNull
    public OverviewModel provideModel(final Context context,
                                      final IDatabaseAccess impDatabase,
                                      final IExecutorAccess executorAccess,
                                      final IPreferenceAccess preferenceAccess) {
        return new OverviewModel(context, impDatabase, executorAccess, preferenceAccess);
    }
}
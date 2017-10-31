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

import com.scavi.de.gw2imp.model.MainModel;
import com.scavi.de.gw2imp.model.OverviewModel;
import com.scavi.de.gw2imp.preferences.IPreferenceAccess;
import com.scavi.de.gw2imp.presenter.MainPresenter;
import com.scavi.de.gw2imp.ui.view.IMainView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class MainModule {
    private final IMainView mView;

    /**
     * Constructor
     *
     * @param view the main view of the application
     */
    public MainModule(final IMainView view) {
        this.mView = view;
    }


    /**
     * @return the main view of the application
     */
    @Provides
    @Nonnull
    public IMainView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of the main activity
     * @return the presenter of the MVP pattern in the context of the main application
     */
    @Provides
    @Nonnull
    public MainPresenter providePresenter(final MainModel model) {
        return new MainPresenter(mView, model);
    }


    /**
     * @param context          the context to global information about the application environment
     * @param preferenceAccess access to the shared preferences of this application
     * @return the model of the MVP pattern in the context of the main activity
     */
    @Provides
    @NonNull
    public MainModel provideModel(final Context context,
                                  final IPreferenceAccess preferenceAccess) {
        return new MainModel(context, preferenceAccess);
    }
}

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

import com.scavi.de.gw2imp.data.preferences.IPreferenceAccess;
import com.scavi.de.gw2imp.model.SplashModel;
import com.scavi.de.gw2imp.presenter.SplashPresenter;
import com.scavi.de.gw2imp.ui.view.ISplashView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class SplashModule {
    private final ISplashView mView;

    /**
     * Constructor
     *
     * @param view the view for the splash screen
     */
    public SplashModule(final ISplashView view) {
        this.mView = view;
    }

    /**
     * @return the view of the splash screen
     */
    @Provides
    @Nonnull
    public ISplashView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of the splash screen
     * @return the presenter of the MVP pattern in the context of the splash screen
     */
    @Provides
    @NonNull
    public SplashPresenter providePresenter(final SplashModel model) {
        return new SplashPresenter(mView, model);
    }

    /**
     * @param context          the context to global information about the application environment
     * @param preferenceAccess access to the shared preferences of this application
     * @return the model of the MVP pattern in the context of the splash screen
     */
    @Provides
    @NonNull
    public SplashModel provideModel(final Context context,
                                    final IPreferenceAccess preferenceAccess) {
        return new SplashModel(context, preferenceAccess);
    }
}

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

import com.scavi.de.gw2imp.communication.access.IAccountAccess;
import com.scavi.de.gw2imp.preferences.IPreferenceAccess;
import com.scavi.de.gw2imp.model.ApiAccountKeyModel;
import com.scavi.de.gw2imp.presenter.ApiAccountKeyPresenter;
import com.scavi.de.gw2imp.ui.view.IApiAccountKeyView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class ApiAccountKeyModule {
    private final IApiAccountKeyView mView;

    /**
     * Constructor
     *
     * @param mView the view for the api account details
     */
    public ApiAccountKeyModule(final IApiAccountKeyView mView) {
        this.mView = mView;
    }

    /**
     * @return the view for the api account details
     */
    @Provides
    @Nonnull
    public IApiAccountKeyView provideView() {
        return mView;
    }

    /**
     * @param model the model of the MVP pattern in the context of the api account key
     * @return the presenter of the MVP pattern in the context of the api account key
     */
    @Provides
    @NonNull
    public ApiAccountKeyPresenter providePresenter(final ApiAccountKeyModel model) {
        return new ApiAccountKeyPresenter(mView, model);
    }

    /**
     * @param context          the context to global information about the application environment
     * @param accountAccess    the server side access the account data
     * @param preferenceAccess the synchronized shared preferences of this application
     * @return the model of the MVP pattern in the context of the api account key
     */
    @Provides
    @NonNull
    public ApiAccountKeyModel provideModel(final Context context,
                                           final IAccountAccess accountAccess,
                                           final IPreferenceAccess preferenceAccess) {
        return new ApiAccountKeyModel(context, accountAccess, preferenceAccess);
    }
}



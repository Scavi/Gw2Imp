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

import com.scavi.de.gw2imp.model.AccountCharacterModel;
import com.scavi.de.gw2imp.presenter.AccountCharacterPresenter;
import com.scavi.de.gw2imp.ui.view.IAccountCharacterView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class AccountCharacterModule {
    private final IAccountCharacterView mView;

    /**
     * Constructor
     *
     * @param view the view for the account characters
     */
    public AccountCharacterModule(final IAccountCharacterView view) {
        this.mView = view;
    }

    /**
     * @return the view for the account characters
     */
    @Provides
    @Nonnull
    public IAccountCharacterView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of the account characters
     * @return the presenter of the MVP pattern in the context of the account characters
     */
    @Provides
    @NonNull
    public AccountCharacterPresenter providePresenter(final AccountCharacterModel model) {
        return new AccountCharacterPresenter(mView, model);
    }

    /**
     * @param context the context to global information about the application environment
     * @return the model of the MVP pattern in the context of the account character screen
     */
    @Provides
    @NonNull
    public AccountCharacterModel provideModel(final Context context) {
        return new AccountCharacterModel(context);
    }
}

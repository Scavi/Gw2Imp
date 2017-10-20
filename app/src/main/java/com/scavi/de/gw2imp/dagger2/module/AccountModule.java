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

import android.support.annotation.NonNull;

import com.scavi.de.gw2imp.model.AccountModel;
import com.scavi.de.gw2imp.presenter.AccountPresenter;
import com.scavi.de.gw2imp.ui.view.IAccountView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class AccountModule {
    private final IAccountView mView;

    /**
     * Constructor
     *
     * @param view the view for the account
     */
    public AccountModule(final IAccountView view) {
        this.mView = view;
    }

    /**
     * @return the view for the account
     */
    @Provides
    @Nonnull
    public IAccountView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of the account
     * @return the presenter of the MVP pattern in the context of the account
     */
    @Provides
    @NonNull
    public AccountPresenter providePresenter(final AccountModel model) {
        return new AccountPresenter(mView, model);
    }

    /**
     * @return the model of the MVP pattern in the context of the account
     */
    @Provides
    @NonNull
    public AccountModel provideModel() {
        return new AccountModel();
    }
}

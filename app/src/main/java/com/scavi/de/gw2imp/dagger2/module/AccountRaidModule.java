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
import com.scavi.de.gw2imp.communication.access.IMiscellaneousAccess;
import com.scavi.de.gw2imp.data.db.Gw2ImpDatabase;
import com.scavi.de.gw2imp.model.AccountRaidModel;
import com.scavi.de.gw2imp.presenter.AccountRaidPresenter;
import com.scavi.de.gw2imp.ui.view.IAccountRaidView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class AccountRaidModule {
    private final IAccountRaidView mView;

    /**
     * Constructor
     *
     * @param view the view for the account raid
     */
    public AccountRaidModule(final IAccountRaidView view) {
        this.mView = view;
    }


    /**
     * @return the view for the account raid
     */
    @Provides
    @Nonnull
    public IAccountRaidView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of the account raid
     * @return the presenter of the MVP pattern in the context of the account raid
     */
    @Provides
    @NonNull
    public AccountRaidPresenter providePresenter(final AccountRaidModel model) {
        return new AccountRaidPresenter(mView, model);
    }


    /**
     * @param context           the context to global information about the application environment
     * @param accountAccess     the server side access the account data
     * @param miscAccess        the server side access to the misc data
     * @param impDatabaseAccess the imp database access
     * @param executorAccess    to access the main and background threads
     * @return the model of the MVP pattern in the context of the account raid
     */
    @Provides
    @NonNull
    public AccountRaidModel provideModel(final Context context,
                                         final IAccountAccess accountAccess,
                                         final IMiscellaneousAccess miscAccess,
                                         final Gw2ImpDatabase impDatabaseAccess,
                                         final IExecutorAccess executorAccess) {
        return new AccountRaidModel(context, accountAccess, miscAccess, impDatabaseAccess,
                executorAccess);
    }
}
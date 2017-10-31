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
import com.scavi.de.gw2imp.model.AccountDailyModel;
import com.scavi.de.gw2imp.presenter.AccountDailyPresenter;
import com.scavi.de.gw2imp.ui.view.IAccountDailyView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class AccountDailyModule {
    private final IAccountDailyView mView;

    /**
     * Constructor
     *
     * @param view the view for the account
     */
    public AccountDailyModule(final IAccountDailyView view) {
        this.mView = view;
    }

    /**
     * @return the view for the account
     */
    @Provides
    @Nonnull
    public IAccountDailyView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of the account
     * @return the presenter of the MVP pattern in the context of the account
     */
    @Provides
    @NonNull
    public AccountDailyPresenter providePresenter(final AccountDailyModel model) {
        return new AccountDailyPresenter(mView, model);
    }


    /**
     * @param context           the context to global information about the application environment
     * @param accountAccess     the server side access the account data
     * @param achievementAccess the server side access to the achievement data
     * @param executorAccess    to access the main and background threads
     * @return the model of the MVP pattern in the context of the account
     */
    @Provides
    @NonNull
    public AccountDailyModel provideModel(final Context context,
                                          final IAccountAccess accountAccess,
                                          final IAchievementAccess achievementAccess,
                                          final IExecutorAccess executorAccess) {
        return new AccountDailyModel(context, accountAccess, achievementAccess, executorAccess);
    }
}

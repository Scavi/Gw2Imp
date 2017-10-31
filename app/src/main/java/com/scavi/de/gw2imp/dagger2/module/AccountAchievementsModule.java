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
import com.scavi.de.gw2imp.communication.access.IAchievementAccess;
import com.scavi.de.gw2imp.model.AccountAchievementsModel;
import com.scavi.de.gw2imp.presenter.AccountAchievementsPresenter;
import com.scavi.de.gw2imp.ui.view.IAccountAchievementsView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class AccountAchievementsModule {
    private final IAccountAchievementsView mView;

    /**
     * Constructor
     *
     * @param view the view for the account achievements
     */
    public AccountAchievementsModule(final IAccountAchievementsView view) {
        this.mView = view;
    }


    /**
     * @return the view for the account achievements
     */
    @Provides
    @Nonnull
    public IAccountAchievementsView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of the account achievements
     * @return the presenter of the MVP pattern in the context of the account achievements
     */
    @Provides
    @NonNull
    public AccountAchievementsPresenter providePresenter(final AccountAchievementsModel model) {
        return new AccountAchievementsPresenter(mView, model);
    }


    /**
     * @param context           the context to global information about the application environment
     * @param accountAccess     the server side access the account data
     * @param achievementAccess the server side access to the account achievement data
     * @return the model of the MVP pattern in the context of the account achievements
     */
    @Provides
    @NonNull
    public AccountAchievementsModel provideModel(final Context context,
                                                 final IAccountAccess accountAccess,
                                                 final IAchievementAccess achievementAccess) {
        return new AccountAchievementsModel(context, accountAccess, achievementAccess);
    }
}
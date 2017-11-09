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
package com.scavi.de.gw2imp.presenter;

import com.scavi.de.gw2imp.communication.response.account.AccountAchievement;
import com.scavi.de.gw2imp.model.AccountAchievementsModel;
import com.scavi.de.gw2imp.ui.view.IAccountAchievementsView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountAchievementsPresenter {
    private final IAccountAchievementsView mView;
    private final AccountAchievementsModel mModel;

    /**
     * Constructor
     *
     * @param view  the view for the account details
     * @param model the model for the account details
     */
    @Inject
    public AccountAchievementsPresenter(final IAccountAchievementsView view,
                                        final AccountAchievementsModel model) {
        mView = view;
        mModel = model;
    }


    public void determineAchievements() {
        mModel.requestAchievements(new Callback<List<AccountAchievement>>() {
            @Override
            public void onResponse(final Call<List<AccountAchievement>> call,
                                   final Response<List<AccountAchievement>> response) {
                System.out.print("");
            }

            @Override
            public void onFailure(final Call<List<AccountAchievement>> call,
                                  final Throwable t) {
                System.out.print("");
            }
        });
    }
}

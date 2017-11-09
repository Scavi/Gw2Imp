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
package com.scavi.de.gw2imp.model;

import android.content.Context;

import retrofit2.Callback;

import com.scavi.de.gw2imp.communication.access.IAccountAccess;
import com.scavi.de.gw2imp.communication.access.IAchievementAccess;
import com.scavi.de.gw2imp.communication.response.account.AccountAchievement;

import java.util.List;

import javax.inject.Inject;

public class AccountAchievementsModel {
    private final Context mContext;
    private final IAccountAccess mAccountAccess;
    private final IAchievementAccess mAchievementAccess;

    /**
     * Constructor
     *
     * @param context           the context to global information about the application environment
     * @param accountAccess     the server side access the account data
     * @param achievementAccess the server side access to the account achievement data
     */
    @Inject
    public AccountAchievementsModel(final Context context,
                                    final IAccountAccess accountAccess,
                                    final IAchievementAccess achievementAccess) {
        mContext = context;
        mAccountAccess = accountAccess;
        mAchievementAccess = achievementAccess;
    }


    /**
     * Requests the achievements from the account
     *
     * @param callback the callback to process the account achievement result
     */
    public void requestAchievements(final Callback<List<AccountAchievement>> callback) {
        mAccountAccess.getAchievements(callback);
    }
}

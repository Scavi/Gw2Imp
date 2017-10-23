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
package com.scavi.de.gw2imp.communication.access.impl;

import com.scavi.de.gw2imp.communication.access.IAchievementAccess;
import com.scavi.de.gw2imp.communication.response.achievement.Category;
import com.scavi.de.gw2imp.communication.rest.Gw2ApiV2AchievementPlugin;
import com.scavi.de.gw2imp.data.entity.Group;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class AchievementsAccess implements IAchievementAccess {
    private final Gw2ApiV2AchievementPlugin mGw2Plugin;

    /**
     * Constructor
     *
     * @param retrofit the retrofit adapter
     */
    public AchievementsAccess(final Retrofit retrofit) {
        mGw2Plugin = retrofit.create(Gw2ApiV2AchievementPlugin.class);
    }


    /**
     * Calls the server side asynchronous to determine the top-level groups for achievements
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getAchievementGroups(final Callback<List<Group>> callback) {
        Call<List<Group>> call = mGw2Plugin.getAchievementGroups();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine the top-level group for achievement to the
     * given groupid asynchronous
     *
     * @param callback the callback to process the top-level group for achievements to the
     *                 requested groupid
     * @param groupId  the id of the group
     */
    @Override
    public void getAchievementGroup(final Callback<Group> callback,
                                    final String groupId) {
        Call<Group> call = mGw2Plugin.getAchievementGroup(groupId);
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine all the ids of the categories for
     * achievements.
     *
     * @param callback the callback to process the asynchronous result
     */
    @Override
    public void getAchievementCategories(final Callback<List<Category>> callback) {
        Call<List<Category>> call = mGw2Plugin.getAchievementCategories();
        call.enqueue(callback);
    }


    /**
     * Calls the server side asynchronous to determine the category to the given id
     *
     * @param callback the callback to process the asynchronous result
     * @param id       the id of teh category
     */
    @Override
    public void getAchievementCategories(final Callback<List<Category>> callback,
                                         final int id) {
        Call<List<Category>> call = mGw2Plugin.getAchievementCategories();
        call.enqueue(callback);
    }
}
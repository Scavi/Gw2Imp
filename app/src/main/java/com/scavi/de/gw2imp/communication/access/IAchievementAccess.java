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
package com.scavi.de.gw2imp.communication.access;

import com.scavi.de.gw2imp.communication.error.ResponseException;
import com.scavi.de.gw2imp.communication.response.achievement.Achievement;
import com.scavi.de.gw2imp.communication.response.achievement.Category;
import com.scavi.de.gw2imp.communication.response.achievement.DailyAchievements;
import com.scavi.de.gw2imp.communication.response.achievement.Group;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface IAchievementAccess {
    /**
     * Calls the server side asynchronous to determine the top-level groups for achievements
     * asynchronous
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAchievementGroups(final Callback<List<Group>> callback);


    /**
     * Calls the server side asynchronous to determine the top-level group for achievement to the
     * given groupid asynchronous
     *
     * @param groupId  the id of the group
     * @param callback the callback to process the asynchronous result
     */
    void getAchievementGroup(final Callback<Group> callback,
                             final String groupId);


    /**
     * Calls the server side asynchronous to determine all the ids of the categories for
     * achievements.
     *
     * @param callback the callback to process the asynchronous result
     */
    void getAchievementCategories(final Callback<List<Category>> callback);


    /**
     * Calls the server side asynchronous to determine the category to the given id
     *
     * @param callback the callback to process the asynchronous result
     * @param id       the id of teh category
     */
    void getAchievementCategories(final Callback<List<Category>> callback,
                                  final int id);


    /**
     * Calls the server side asynchronous to determine the dailies today
     *
     * @param callback the callback to process the asynchronous result
     */
    void getDaily(final Callback<DailyAchievements> callback);


    /**
     * Calls the server side synchronous to determine the dailies today
     *
     * @rreturn the dailies today
     */
    DailyAchievements getDaily() throws IOException, ResponseException;


    /**
     * Calls the server side asynchronous to determine the dailies tomorrow
     *
     * @param callback the callback to process the asynchronous result
     */
    void getDailyTomorrow(final Callback<DailyAchievements> callback);


    /**
     * Calls the server side synchronous to determine the dailies today
     *
     * @rreturn the dailies today
     */
    DailyAchievements getDailyTomorrow() throws IOException, ResponseException;
}

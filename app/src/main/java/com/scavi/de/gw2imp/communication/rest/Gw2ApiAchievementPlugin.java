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
package com.scavi.de.gw2imp.communication.rest;

import com.scavi.de.gw2imp.communication.response.achievement.Achievement;
import com.scavi.de.gw2imp.communication.response.achievement.Category;
import com.scavi.de.gw2imp.communication.response.achievement.DailyAchievements;
import com.scavi.de.gw2imp.communication.response.achievement.Group;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Gw2ApiAchievementPlugin {
    /**
     * @return All the top-level groups for achievements.
     */
    @GET("v2/achievements/groups?lang=de")
    Call<List<Group>> getAchievementGroups();


    /**
     * @param groupId the id of the group
     * @return the top-level group for achievements to the requested groupid
     */
    @GET("v2/achievements/groups/{groupId}&lang=de")
    Call<Group> getAchievementGroup(@Path("groupId") final String groupId);


    /**
     * @return all the ids of the categories for achievements.
     */
    @GET("v2/achievements/categories?lang=de")
    Call<List<Category>> getAchievementCategories();


    /**
     * @param id the id of the category
     * @return all the categories for achievements.
     */
    @GET("v2/achievements/categories/{id}&lang=de")
    Call<Category> getAchievementCategory(@Path("id") final int id);


    /**
     * @return the current set of daily achievements.
     */
    @GET("v2/achievements/daily?lang=de")
    Call<DailyAchievements> getDaily();


    /**
     * @return the next set of daily achievements.
     */
    @GET("v2/achievements/daily/tomorrow?lang=de")
    Call<DailyAchievements> getDailyTomorrow();


    /**
     * @return all achievements
     */
    @GET("/v2/achievements?lang=de")
    Call<List<Achievement>> getAchievements();


    /**
     * @param ids "," separated achievement ids
     * @return all achievements to the given id
     */
    @GET("/v2/achievements?lang=de")
    Call<List<Achievement>> getAchievementToIds(@Query(value = "ids") final String ids);
}

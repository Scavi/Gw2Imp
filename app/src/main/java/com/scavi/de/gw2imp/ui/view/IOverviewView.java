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
package com.scavi.de.gw2imp.ui.view;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

public interface IOverviewView {

    /**
     * Updates the loaded item count in the view
     *
     * @param itemCount the item count
     */
    void updateItemCount(final int itemCount);


    /**
     * Updates the count of loaded prices in the view
     *
     * @param priceCount the price count
     */
    void updateItemPriceCount(final int priceCount);


    /**
     * Updates the view to set the history count
     *
     * @param historyCount the history count
     */
    void updateHistoryCount(final int historyCount);


    /**
     * Updates the search index depending on the status
     *
     * @param resourceId the resource id
     * @param color      the color of the field
     */
    void updateSearchIndex(@StringRes final int resourceId,
                           @IdRes final int color);
}

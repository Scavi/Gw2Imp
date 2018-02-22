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
 *
 */
package com.scavi.de.gw2imp.ui.view;

import com.scavi.de.gw2imp.data.entity.item.ItemEntity;

import java.util.List;

import javax.annotation.Nonnull;

public interface ITradingItemsView extends IStatusView {

    /**
     * @return the current name of the item we want to find
     */
    String getItemSearchName();


    void updateFoundItems(@Nonnull final List<ItemEntity> foundItems);
}

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
package com.scavi.de.gw2imp.ui.listener;

import android.support.annotation.Nullable;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.scavi.de.gw2imp.ui.view.IMainNavigation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class NavigationClickListener implements Drawer.OnDrawerItemClickListener {
    public static final int OVERVIEW_ID = 10;
    public static final int CHARACTER_ID = 11;
    public static final int RAID_ID = 12;
    public static final int TRANSACTION_BUY_ID = 15;
    public static final int TRANSACTION_SELL_ID = 16;
    public static final int TRADING_UP = 20;
    public static final int TRADING_DOWN = 21;
    private final IMainNavigation mMainNavigator;

    /**
     * Constructor
     *
     * @param mainNavigator the interface of the main navigation to react to the navigation
     *                      clicks of the listener
     */
    public NavigationClickListener(final IMainNavigation mainNavigator) {
        mMainNavigator = mainNavigator;
    }

    /**
     * Reacts to the item click of the main navigation
     */
    @Override
    public boolean onItemClick(final View view,
                               final int position,
                               @Nullable final IDrawerItem drawerItem) {
        if (drawerItem == null) {
            return false;
        }

        switch ((int) drawerItem.getIdentifier()) {
            case OVERVIEW_ID:
                mMainNavigator.onClickAccountOverView();
                break;
            case CHARACTER_ID:
                mMainNavigator.onClickAccountCharacterView();
                break;
            case RAID_ID:
                mMainNavigator.onClickAccountRaidView();
                break;
            case TRANSACTION_BUY_ID:
                mMainNavigator.onClickAccountTransactionBuy();
                break;
            case TRANSACTION_SELL_ID:
                mMainNavigator.onClickAccountTransactionSell();
                break;
            case TRADING_UP:
                mMainNavigator.onClickTradingUp();
                break;
            case TRADING_DOWN:
                mMainNavigator.onClickTradingDown();
                break;
            default:
                return false;
        }
        return true;
    }
}

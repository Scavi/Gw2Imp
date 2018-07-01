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
import com.scavi.de.gw2imp.presenter.MainPresenter;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class NavigationClickListener implements Drawer.OnDrawerItemClickListener {
    public static final int OVERVIEW_ID = 10;
    public static final int CHARACTER_ID = 11;
    public static final int ACHIEVEMENTS_ID = 13;
    public static final int RAID_ID = 14;
    public static final int TRANSACTION_BUY_ID = 20;
    public static final int TRANSACTION_SELL_ID = 21;
    public static final int TRADING_ITEMS = 30;
    public static final int PRICE_DEVELOPMENT = 31;
    public static final int WORLD_BOSS_EVENT_TIMER_ID = 40;
    public static final int DAILIES_TODAY_ID = 50;
    public static final int DAILIES_TOMORROW_ID = 51;
    public static final int LICENSE_ID = 90;
    private final MainPresenter mMainNavigator;

    /**
     * Constructor
     *
     * @param mainNavigator the access of the main navigation to react to the navigation
     *                      clicks of the listener (presenter)
     */
    public NavigationClickListener(final MainPresenter mainNavigator) {
        mMainNavigator = mainNavigator;
    }

    /**
     * Reacts to the item click of the main navigation
     */
    @Override
    public boolean onItemClick(@Nullable final View view,
                               final int position,
                               @Nullable final IDrawerItem drawerItem) {
        if (view == null || drawerItem == null) {
            return false;
        }

        switch ((int) drawerItem.getIdentifier()) {
            case OVERVIEW_ID:
                mMainNavigator.onClickAccountOverview();
                break;
            case CHARACTER_ID:
                mMainNavigator.onClickAccountCharacter();
                break;
            case RAID_ID:
                mMainNavigator.onClickAccountRaid();
                break;
            case ACHIEVEMENTS_ID:
                mMainNavigator.onClickAccountAchievements();
                break;
            case TRANSACTION_BUY_ID:
                mMainNavigator.onClickAccountTransactionBuy();
                break;
            case TRANSACTION_SELL_ID:
                mMainNavigator.onClickAccountTransactionSell();
                break;
            case TRADING_ITEMS:
                mMainNavigator.onClickTradingItems();
                break;
            case PRICE_DEVELOPMENT:
                mMainNavigator.onClickWatchPriceDevelopment();
                break;
            case WORLD_BOSS_EVENT_TIMER_ID:
                mMainNavigator.onClickWorldBossEventTimer();
                break;
            case DAILIES_TODAY_ID:
                mMainNavigator.onClickDailiesToday();
                break;
            case DAILIES_TOMORROW_ID:
                mMainNavigator.onClickDailiesTomorrow();
                break;
            case LICENSE_ID:
                mMainNavigator.onClickLicense();
                break;
            default:
                return false;
        }
        return true;
    }
}

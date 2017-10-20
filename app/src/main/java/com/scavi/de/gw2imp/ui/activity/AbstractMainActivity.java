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
package com.scavi.de.gw2imp.ui.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.ui.listener.NavigationClickListener;
import com.scavi.de.gw2imp.ui.view.IMainNavigation;

import static com.scavi.de.gw2imp.ui.listener.NavigationClickListener.OVERVIEW_ID;

public abstract class AbstractMainActivity extends AppCompatActivity implements IMainNavigation {


    /**
     * Creates the navigation drawer menu
     *
     * @return the created drawer of the menu
     */
    protected Drawer createDrawerMenu() {
        setupActionBar(getSupportActionBar());
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.logo_main)
                .build();
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(getNavigationDrawerItems())
                .withOnDrawerItemClickListener(new NavigationClickListener(this))
                .build();
        return drawer;
    }


    /**
     * Setups the actionbar for the activity
     *
     * @param actionBar the actionbar to setup
     */
    protected void setupActionBar(@Nullable final ActionBar actionBar) {
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    /**
     * @return all navigation drawer items for the menu
     */
    private IDrawerItem[] getNavigationDrawerItems() {
        return new IDrawerItem[]{
                new SectionDrawerItem().withDivider(false)
                        .withName(getString(R.string.core_navigation_account)),
                new SecondaryDrawerItem()
                        .withIdentifier(OVERVIEW_ID)
                        .withName(getString(R.string.core_navigation_account_overview))
                        .withIcon(R.drawable.ic_home_black_24dp),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.CHARACTER_ID)
                        .withName(getString(R.string.core_navigation_account_character))
                        .withIcon(R.drawable.ic_account_circle_black_24dp),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.RAID_ID)
                        .withName(getString(R.string.core_navigation_account_raid))
                        .withIcon(R.drawable.ic_spa_black_24dp),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.TRANSACTION_BUY_ID)
                        .withName(getString(R.string.core_navigation_account_buy))
                        .withIcon(R.drawable.ic_shopping_cart_black_24dp),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.TRANSACTION_SELL_ID)
                        .withName(getString(R.string.core_navigation_account_sell))
                        .withIcon(R.drawable.ic_remove_shopping_cart_black_24dp),
                new SectionDrawerItem().withDivider(true)
                        .withName(getString(R.string.core_navigation_account_trading_market)),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.TRADING_UP)
                        .withName(getString(R.string.core_navigation_account_trading_up))
                        .withIcon(R.drawable.ic_trending_up_black_24dp),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.TRADING_DOWN)
                        .withName(getString(R.string.core_navigation_account_trading_down))
                        .withIcon(R.drawable.ic_trending_down_black_24dp)
        };
    }


    /**
     * The user clicked on the account overview view / information
     */
    @Override
    public void onClickAccountOverView() {
        // TODO
    }

    /**
     * The user clicked on the account character view / information
     */
    @Override
    public void onClickAccountCharacterView() {
        // TODO
    }

    /**
     * The user clicked on the account raid view / information
     */
    @Override
    public void onClickAccountRaidView() {
        // TODO
    }

    /**
     * The user clicked on the account buying transactions (history / current)
     */
    @Override
    public void onClickAccountTransactionBuy() {
        // TODO
    }

    /**
     * The user clicked on the account selling transactions (history / current)
     */
    @Override
    public void onClickAccountTransactionSell() {
        // TODO
    }

    /**
     * The user clicked on the trading market information (up)
     */
    @Override
    public void onClickTradingUp() {
        // TODO
    }

    /**
     * The user clicked on the trading market information (down)
     */
    @Override
    public void onClickTradingDown() {
        // TODO
    }
}

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

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerMainComponent;
import com.scavi.de.gw2imp.dagger2.module.MainModule;
import com.scavi.de.gw2imp.presenter.MainPresenter;
import com.scavi.de.gw2imp.ui.adapter.WorldBossesAdapter;
import com.scavi.de.gw2imp.ui.fragment.AccountAchievementsFragment;
import com.scavi.de.gw2imp.ui.fragment.AccountCharacterFragment;
import com.scavi.de.gw2imp.ui.fragment.DailyFragment;
import com.scavi.de.gw2imp.ui.fragment.AccountRaidFragment;
import com.scavi.de.gw2imp.ui.fragment.DailyTomorrowFragment;
import com.scavi.de.gw2imp.ui.fragment.LicenseFragment;
import com.scavi.de.gw2imp.ui.fragment.OverviewFragment;
import com.scavi.de.gw2imp.ui.fragment.TradingItemsFragment;
import com.scavi.de.gw2imp.ui.fragment.WorldBossEventTimerFragment;
import com.scavi.de.gw2imp.ui.listener.NavigationClickListener;
import com.scavi.de.gw2imp.ui.view.IMainView;

import java.util.Random;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

import static com.scavi.de.gw2imp.ui.listener.NavigationClickListener.OVERVIEW_ID;

@ParametersAreNonnullByDefault
public class MainActivity extends AppCompatActivity implements IMainView {
    @Inject
    MainPresenter mPresenter;
    private Drawer mNavigationDrawer;

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut
     *                           down then this Bundle contains the data it most recently supplied
     *                           in this method. Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectComponent(((IApplication) getApplication()).getComponent());
        setupUiComponents();
        routeAccountOverview();
        mPresenter.onCreate();
    }


    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    private void injectComponent(final ApplicationComponent applicationComponent) {
        MainModule mainModule = new MainModule(this);
        DaggerMainComponent.builder()
                .applicationComponent(applicationComponent)
                .mainModule(mainModule)
                .build()
                .inject(this);
    }


    /**
     * Create the navigation drawer and set the selection for the overview
     */
    protected void setupUiComponents() {
        setupActionBar(getSupportActionBar());
        setupNavigationDrawer();
    }

    private void setupNavigationDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        mNavigationDrawer = createDrawerMenu(toolbar);
        mNavigationDrawer.setSelection(NavigationClickListener.OVERVIEW_ID);
    }


    /**
     * Creates the navigation drawer menu
     *
     * @param toolbar the toolbar
     * @return the created drawer of the menu
     */
    private Drawer createDrawerMenu(final Toolbar toolbar) {
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(background())
                .build();
        IDrawerItem[] drawerItems = getNavigationDrawerItems();
        DrawerBuilder drawerBuilder = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(false)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(drawerItems)
                .withOnDrawerItemClickListener(new NavigationClickListener(mPresenter));
        return drawerBuilder.buildForFragment();
    }


    /**
     * Determines a random background
     *
     * @return the background to use
     */
    @DrawableRes
    private int background() {
        int id;
        Random random = new Random();
        int pos = random.nextInt() % 2;
        switch (pos) {
            case 0:
                id = R.drawable.logo_main_1;
                break;
            case 1:
                id = R.drawable.logo_main_2;
                break;
            default:
                id = R.drawable.logo_main_2;
        }
        return id;
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
     * Reacts to the {@link KeyEvent#KEYCODE_BACK} event and opens the navigation drawer if it is
     * not open yet
     */
    @Override
    public boolean onKeyUp(final int keyCode,
                           final KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && !mNavigationDrawer.isDrawerOpen()) {
            mNavigationDrawer.openDrawer();
            return false;
        } else {
            return super.onKeyUp(keyCode, event);
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
                /*
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.CHARACTER_ID)
                        .withName(getString(R.string.core_navigation_account_character))
                        .withIcon(R.drawable.ic_account_circle_black_24dp),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.ACHIEVEMENTS_ID)
                        .withName(getString(R.string.core_navigation_account_achievements))
                        .withIcon(R.drawable.ic_explore_black_24dp), */
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.RAID_ID)
                        .withName(getString(R.string.core_navigation_account_raid))
                        .withIcon(R.drawable.ic_spa_black_24dp),
                /*
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.TRANSACTION_BUY_ID)
                        .withName(getString(R.string.core_navigation_account_buy))
                        .withIcon(R.drawable.ic_shopping_cart_black_24dp),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.TRANSACTION_SELL_ID)
                        .withName(getString(R.string.core_navigation_account_sell))
                        .withIcon(R.drawable.ic_remove_shopping_cart_black_24dp),*/
                new SectionDrawerItem().withDivider(true)
                        .withName(getString(R.string.core_navigation_account_trading_market)),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.TRADING_ITEMS)
                        .withName(getString(R.string.core_navigation_account_trading))
                        .withIcon(R.drawable.ic_trending_up_black_24dp),
                new SectionDrawerItem().withDivider(true)
                        .withName(getString(R.string.core_navigation_event_timer)),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.WORLD_BOSS_EVENT_TIMER_ID)
                        .withName(getString(R.string.core_navigation_world_boss_event_timer))
                        .withIcon(R.drawable.ic_explore_black_24dp),
                new SectionDrawerItem().withDivider(true)
                        .withName(getString(R.string.core_navigation_account_common)),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.DAILIES_TODAY_ID)
                        .withName(getString(R.string.core_navigation_dailies_today))
                        .withIcon(R.drawable.ic_explore_black_24dp),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.DAILIES_TOMORROW_ID)
                        .withName(getString(R.string.core_navigation_dailies_tomorrow))
                        .withIcon(R.drawable.ic_explore_black_24dp),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.LICENSE_ID)
                        .withName(getString(R.string.core_license))
                        .withIcon(R.drawable.ic_info_black_24dp),

        };
    }


    /**
     * Switch the fragment to show the account overview view / information
     */
    @Override
    public void routeAccountOverview() {
        OverviewFragment fragment = new OverviewFragment();
        show(fragment);
    }


    /**
     * Switch the fragment to show the account character view / information
     */
    @Override
    public void routeAccountCharacter() {
        AccountCharacterFragment fragment = new AccountCharacterFragment();
        show(fragment);
    }


    /**
     * Switch the fragment to show the account raid view / information
     */
    @Override
    public void routeAccountRaid() {
        AccountRaidFragment fragment = new AccountRaidFragment();
        show(fragment);
    }


    /**
     * Switch the fragment to show  the account achievements view / information
     */
    @Override
    public void routeAccountAchievements() {
        AccountAchievementsFragment fragment = new AccountAchievementsFragment();
        show(fragment);
    }


    /**
     * Switch the fragment to show  the account buying transactions (history / current)
     */
    @Override
    public void routeAccountTransactionBuy() {
        // TODO
    }


    /**
     * Switch the fragment to show the account selling transactions (history / current)
     */
    @Override
    public void routeAccountTransactionSell() {
        // TODO
    }


    /**
     * Switch the fragment to show the trading market information
     */
    @Override
    public void routeTradingItems() {
        TradingItemsFragment fragment = new TradingItemsFragment();
        show(fragment);
    }


    /**
     * Switch the fragment to show the world boss event timer
     */
    @Override
    public void routeWorldBossEventTimer() {
        WorldBossEventTimerFragment fragment = new WorldBossEventTimerFragment();
        show(fragment);
    }

    /**
     * Switch the fragment to show the account daily view / information
     */
    @Override
    public void routeDailiesToday() {
        DailyFragment fragment = new DailyFragment();
        show(fragment);
    }


    /**
     * Switch the fragment to show the dailies of tomorrow without any account information
     */
    @Override
    public void routeDailiesTomorrow() {
        DailyTomorrowFragment fragment = new DailyTomorrowFragment();
        show(fragment);
    }


    /**
     * Switch the fragment to show the license view / information
     */
    @Override
    public void routeToLicense() {
        LicenseFragment fragment = new LicenseFragment();
        show(fragment);
    }


    /**
     * Shows the given fragment and closes the navigation drawer if open
     *
     * @param fragment the fragment to show
     */
    private void show(final Fragment fragment) {
        mNavigationDrawer.closeDrawer();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit();
    }
}

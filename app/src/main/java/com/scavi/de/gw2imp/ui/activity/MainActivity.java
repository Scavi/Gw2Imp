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
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

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
import com.scavi.de.gw2imp.ui.fragment.AccountAchievementsFragment;
import com.scavi.de.gw2imp.ui.fragment.AccountCharacterFragment;
import com.scavi.de.gw2imp.ui.fragment.AccountDailyFragment;
import com.scavi.de.gw2imp.ui.fragment.AccountRaidFragment;
import com.scavi.de.gw2imp.ui.fragment.OverviewFragment;
import com.scavi.de.gw2imp.ui.listener.NavigationClickListener;
import com.scavi.de.gw2imp.ui.view.IMainView;

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
        mNavigationDrawer = createDrawerMenu();
        mNavigationDrawer.setSelection(NavigationClickListener.OVERVIEW_ID);
    }


    /**
     * Creates the navigation drawer menu
     *
     * @return the created drawer of the menu
     */
    private Drawer createDrawerMenu() {
        setupActionBar(getSupportActionBar());
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.logo_main)
                .build();
        DrawerBuilder drawerBuilder = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(getNavigationDrawerItems())
                .withOnDrawerItemClickListener(new NavigationClickListener(mPresenter));
        return drawerBuilder.buildForFragment();
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
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.CHARACTER_ID)
                        .withName(getString(R.string.core_navigation_account_character))
                        .withIcon(R.drawable.ic_account_circle_black_24dp),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.ACCOUNT_DAILIES_ID)
                        .withName(getString(R.string.core_navigation_account_dailies))
                        .withIcon(R.drawable.ic_explore_black_24dp),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.ACHIEVEMENTS_ID)
                        .withName(getString(R.string.core_navigation_account_achievements))
                        .withIcon(R.drawable.ic_explore_black_24dp),
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
                        .withName(getString(R.string.core_navigation_account_trading_market))/*,
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.TRADING_UP)
                        .withName(getString(R.string.core_navigation_account_trading_up))
                        .withIcon(R.drawable.ic_trending_up_black_24dp),
                new SecondaryDrawerItem()
                        .withIdentifier(NavigationClickListener.TRADING_DOWN)
                        .withName(getString(R.string.core_navigation_account_trading_down))
                        .withIcon(R.drawable.ic_trending_down_black_24dp)*/
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
     * Switch teh fragment to show the account daily view / information
     */
    @Override
    public void routeAccountDailies() {
        AccountDailyFragment fragment = new AccountDailyFragment();
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
     * Switch the fragment to show  the trading market information (up)
     */
    @Override
    public void routeTradingUp() {
        // TODO
    }


    /**
     * Switch the fragment to show  the trading market information (down)
     */
    @Override
    public void routeTradingDown() {
        // TODO
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

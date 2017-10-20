package com.scavi.de.gw2imp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerOverviewComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerSplashComponent;
import com.scavi.de.gw2imp.dagger2.module.OverviewModule;
import com.scavi.de.gw2imp.dagger2.module.SplashModule;
import com.scavi.de.gw2imp.presenter.OverviewPresenter;
import com.scavi.de.gw2imp.ui.view.IOverviewView;

import javax.inject.Inject;

public class OverviewActivity extends AppCompatActivity implements IOverviewView {

    @Inject
    OverviewPresenter mPresenter;

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut
     *                           down then this Bundle contains the data it most recently supplied
     *                           in this method. Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        injectComponent(((IApplication) getApplication()).getComponent());
        createDrawerMenu();
        mPresenter.onCreate();
    }

    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    private void injectComponent(final ApplicationComponent applicationComponent) {
        OverviewModule module = new OverviewModule(this);
        DaggerOverviewComponent.builder()
                .applicationComponent(applicationComponent)
                .overviewModule(module)
                .build()
                .inject(this);
    }


    // TODO
    private void createDrawerMenu() {
        setupActionBar(getSupportActionBar());


        //if you want to update the items at a later time it is recommended to keep it in a variable

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.logo_main)
//                .addProfiles(
//                        new ProfileDrawerItem().withName("Cookie Monster")/*.withIcon
//                        (getResources().getDrawable(R.drawable.profile))*/
//                )
//                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
//                    @Override
//                    public boolean onProfileChanged(View view,
//                                                    IProfile profile,
//                                                    boolean currentProfile) {
//                        return false;
//                    }
//                })
                .build();


//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                //.withRootView(R.id.drawer_layout)
                .addDrawerItems(
                        new SecondaryDrawerItem().withName("Übersicht"),
                        new SectionDrawerItem().withDivider(true)
                                .withIdentifier(1).withName("Account"),
                        new SecondaryDrawerItem().withName("Charaktere"),
                        new SecondaryDrawerItem().withName("Raid"),

                        new PrimaryDrawerItem().withName("Irgendwas"),
                        new PrimaryDrawerItem().withName("Anderes"),

                        new SectionDrawerItem().withDivider(true)
                                .withIdentifier(1).withName("Handelsmarkt"),
                        new SecondaryDrawerItem().withName("Transaktionen")

                        )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view,
                                               int position,
                                               IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        return true;
                    }
                })
                .build();

//
//        new DrawerBuilder()
//                .withActivity(this)
//                .addDrawerItems(
//                        item2,
//                        new SecondaryDrawerItem().withName("cookies")
//                )
//                .withDrawerGravity(Gravity.END)
//                .append(result);
    }


    /**
     * Setups the actionbar for the activity
     *
     * @param actionBar the actionbar to setup
     */
    protected void setupActionBar(final ActionBar actionBar) {
        if (actionBar != null) {
            if (true) { // TODO
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            } else {
                actionBar.hide();
            }
        }
    }
}

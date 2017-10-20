package com.scavi.de.gw2imp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.scavi.de.gw2imp.ui.listener.NavigationClickListener;
import com.scavi.de.gw2imp.ui.view.IOverviewView;

import javax.inject.Inject;

import static com.scavi.de.gw2imp.ui.listener.NavigationClickListener.*;

public class OverviewActivity extends AbstractMainActivity implements IOverviewView {

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
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        injectComponent(((IApplication) getApplication()).getComponent());
        setupUiComponents();
        mPresenter.onCreate();
    }


    /**
     * Setups the UI component. Create the navigation drawer and set the selection for the overview
     */
    private void setupUiComponents() {
        Drawer drawer = createDrawerMenu();
        drawer.setSelection(NavigationClickListener.OVERVIEW_ID);
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
}
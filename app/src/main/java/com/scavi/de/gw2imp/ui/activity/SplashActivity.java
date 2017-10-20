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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerSplashComponent;
import com.scavi.de.gw2imp.dagger2.module.SplashModule;
import com.scavi.de.gw2imp.presenter.SplashPresenter;
import com.scavi.de.gw2imp.ui.view.ISplashView;

import javax.inject.Inject;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SplashActivity extends AppCompatActivity implements ISplashView {
    @Inject
    SplashPresenter mPresenter;

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
        injectComponent(((IApplication) getApplication()).getComponent());
        mPresenter.onCreate();
    }


    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    private void injectComponent(final ApplicationComponent applicationComponent) {
        SplashModule module = new SplashModule(this);
        DaggerSplashComponent.builder()
                .applicationComponent(applicationComponent)
                .splashModule(module)
                .build()
                .inject(this);
    }


    /**
     * Routes to the next activity depending on the given class
     *
     * @param targetRoutingClazz the class to route to
     */
    @Override
    public void routeNext(final Class targetRoutingClazz) {
        Intent routingTarget = new Intent(getApplicationContext(), targetRoutingClazz);
        routingTarget.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(routingTarget);
        finish();
    }
}

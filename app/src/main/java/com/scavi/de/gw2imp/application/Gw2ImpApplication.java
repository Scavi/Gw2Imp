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
package com.scavi.de.gw2imp.application;

import android.app.Application;
import android.content.Intent;

import com.scavi.de.gw2imp.background.DataCollectionService;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerApplicationComponent;
import com.scavi.de.gw2imp.dagger2.module.ApplicationModule;

public class Gw2ImpApplication extends Application implements IApplication {
    private ApplicationComponent mGw2ImpApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupComponent();
        startService(new Intent(this, DataCollectionService.class));
    }

    /**
     * Setup the main component for the gw2 imp application
     */
    private void setupComponent() {
        mGw2ImpApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new
                ApplicationModule(this)).build();
    }

    /**
     * @return the gw2 imp dagger component
     */
    @Override
    public ApplicationComponent getComponent() {
        return mGw2ImpApplicationComponent;
    }
}

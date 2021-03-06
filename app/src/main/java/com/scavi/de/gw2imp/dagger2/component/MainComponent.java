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
package com.scavi.de.gw2imp.dagger2.component;

import com.scavi.androidimp.util.ActivityScope;
import com.scavi.de.gw2imp.dagger2.module.MainModule;
import com.scavi.de.gw2imp.model.MainModel;
import com.scavi.de.gw2imp.presenter.MainPresenter;
import com.scavi.de.gw2imp.ui.activity.MainActivity;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = MainModule.class)
public interface MainComponent {

    /**
     * The inject method of the activity for the dagger component
     *
     * @param activity the activity to inject
     */
    void inject(final MainActivity activity);


    /**
     * @return the presenter of the MVP pattern in the context of the main application
     */
    MainPresenter getPresenter();


    /**
     * @return the model of the MVP pattern in the context of the main activity
     */
    MainModel getModel();
}

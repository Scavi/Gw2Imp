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
package com.scavi.de.gw2imp.presenter;

import android.content.Context;
import android.content.Intent;

import com.scavi.de.gw2imp.model.SplashModel;
import com.scavi.de.gw2imp.ui.activity.AccountActivity;
import com.scavi.de.gw2imp.ui.view.ISplashView;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

@ParametersAreNonnullByDefault
public class SplashPresenter {
    private final ISplashView mView;
    private final SplashModel mModel;

    /**
     * Constructor
     *
     * @param view  the view for the splash screen
     * @param model the model for the splash screen
     */
    @Inject
    public SplashPresenter(final ISplashView view,
                           final SplashModel model) {
        mView = view;
        mModel = model;
    }


    /**
     * Tests about the application state (e.g. obfuscation) and routes to the next activity,
     * depending on the application state
     */
    public void onCreate() {
        mModel.testObfuscation();
        Class targetRoutingClazz = mModel.getRoutingTarget();
        mView.routeNext(targetRoutingClazz);
    }
}

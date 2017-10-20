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

import com.scavi.de.gw2imp.model.OverviewModel;
import com.scavi.de.gw2imp.ui.view.IOverviewView;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

@ParametersAreNonnullByDefault
public class OverviewPresenter {
    private final IOverviewView mView;
    private final OverviewModel mModel;

    /**
     * Constructor
     *
     * @param view  the view for the application overview
     * @param model the model for the application overview
     */
    @Inject
    public OverviewPresenter(final IOverviewView view,
                             final OverviewModel model) {
        mView = view;
        mModel = model;
    }

    /**
     * Process the information about the creation of the view to the module to update the status
     */
    public void onCreate() {
        mModel.persistRoutingState();
    }
}

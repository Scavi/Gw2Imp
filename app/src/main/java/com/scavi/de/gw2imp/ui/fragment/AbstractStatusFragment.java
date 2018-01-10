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
 *
 */
package com.scavi.de.gw2imp.ui.fragment;

import android.view.View;

import com.scavi.de.gw2imp.R;

public abstract class AbstractStatusFragment extends AbstractFragment {
    private View mInfoContainer;
    private View mGettingThingsDoneContainer;
    private View mErrorContainer;


    /**
     * Determine the status views
     */
    protected void setupUiComponents(final View fragmentView) {
        mInfoContainer = fragmentView.findViewById(getInfoContainerId());
        mErrorContainer = fragmentView.findViewById(getErrorContainerId());
        mGettingThingsDoneContainer = fragmentView.findViewById(getGettingThingsDoneContainerId());
    }

    /**
     * Informs the user about the loading progress
     */
    @Override
    public void onShowProgress() {
        if (isRemoving()) {
            return;
        }
        mInfoContainer.setVisibility(View.GONE);
        mErrorContainer.setVisibility(View.GONE);
        mGettingThingsDoneContainer.setVisibility(View.VISIBLE);
    }


    /**
     * The loading of the data is done.
     */
    @Override
    public void onHideProgress() {
        if (isRemoving()) {
            return;
        }
        mInfoContainer.setVisibility(View.VISIBLE);
        mGettingThingsDoneContainer.setVisibility(View.GONE);
        mErrorContainer.setVisibility(View.GONE);
    }


    /**
     * The loading of the data is done but an error has occurred
     */
    @Override
    public void onHideProgressAfterError() {
        if (isRemoving()) {
            return;
        }
        mErrorContainer.setVisibility(View.VISIBLE);
        mInfoContainer.setVisibility(View.GONE);
        mGettingThingsDoneContainer.setVisibility(View.GONE);
    }


    /**
     * @return the resource id of the info / data container
     */
    protected abstract int getInfoContainerId();


    /**
     * @return the resource id of the error container
     */
    protected abstract int getErrorContainerId();


    /**
     * @return the resource id of the view
     */
    protected abstract int getGettingThingsDoneContainerId();
}

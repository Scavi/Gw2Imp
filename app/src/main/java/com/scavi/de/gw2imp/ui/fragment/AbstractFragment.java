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
package com.scavi.de.gw2imp.ui.fragment;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.scavi.de.gw2imp.ui.util.ActivityHelper;
import com.scavi.de.gw2imp.ui.view.IStatusView;

public abstract class AbstractFragment extends Fragment implements IStatusView {


    /**
     * Notifies the user about the occurred error. The error can have different characteristics
     * (e.g. an invalid api key format, no internet connection, technical errors)
     *
     * @param errorMessage the error message for the user
     */
    @Override
    public void showUserError(final String errorMessage) {
        if (isRemoving()) {
            return;
        }
        AlertDialog errorDialog = ActivityHelper.createSimpleOkDialog(getActivity(), errorMessage);
        errorDialog.show();
    }


    /**
     * @return the layout adapter controller to fade in the adapter items
     */
    protected LayoutAnimationController createLayoutAdapterController() {
        return new LayoutAnimationController(
                AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in), 0.7f);
    }
}

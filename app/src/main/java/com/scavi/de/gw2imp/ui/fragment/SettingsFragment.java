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

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerSettingsComponent;
import com.scavi.de.gw2imp.dagger2.module.SettingsModule;
import com.scavi.de.gw2imp.presenter.SettingsPresenter;
import com.scavi.de.gw2imp.ui.view.ISettingsView;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class SettingsFragment extends Fragment implements ISettingsView {
    @Inject
    SettingsPresenter mPresenter;

    /**
     * Called to have the fragment instantiate its user interface view. This is optional, and
     * non-graphical fragments can return null (which is the default implementation). This will
     * be called between onCreate(Bundle) and onActivityCreated(Bundle).
     *
     * @param inflater           LayoutInflater: The LayoutInflater object that can be used to
     *                           inflate any views in the fragment,
     * @param container          ViewGroup: If non-null, this is the parent view that the
     *                           fragment's UI should be attached to. The fragment should not add
     *                           the view itself, but this can be used to generate the
     *                           LayoutParams of the view.
     * @param savedInstanceState Bundle: If non-null, this fragment is being re-constructed from
     *                           a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(@Nonnull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        Context context = getActivity() != null ? getActivity().getApplicationContext() : null;
        if (context == null) {
            return null;
        }
        injectComponent(((IApplication) context).getComponent());
        return inflater.inflate(R.layout.fragment_trading_item, container, false);
    }

    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    private void injectComponent(final ApplicationComponent applicationComponent) {
        SettingsModule module = new SettingsModule(this);
        DaggerSettingsComponent.builder()
                .applicationComponent(applicationComponent)
                .settingsModule(module)
                .build()
                .inject(this);
    }
}

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

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerAccountRaidComponent;
import com.scavi.de.gw2imp.dagger2.module.AccountRaidModule;
import com.scavi.de.gw2imp.data.entity.raid.RaidEntity;
import com.scavi.de.gw2imp.presenter.AccountRaidPresenter;
import com.scavi.de.gw2imp.ui.adapter.RaidAdapter;
import com.scavi.de.gw2imp.ui.view.IAccountRaidView;

import java.util.List;

import javax.inject.Inject;

public class AccountRaidFragment extends AbstractStatusFragment implements IAccountRaidView {
    @Inject
    AccountRaidPresenter mPresenter;
    private ListView mRaidListView;


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
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        injectComponent(((IApplication) getContext().getApplicationContext()).getComponent());
        View fragmentView = inflater.inflate(R.layout.fragment_account_raid, container, false);
        setupUiComponents(fragmentView);
        return fragmentView;
    }


    /**
     * @see AbstractFragment#onStart()
     */
    @Override
    public void onStart() {
        super.onStart();
        mPresenter.loadRaidData();
    }


    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    private void injectComponent(@NonNull final ApplicationComponent applicationComponent) {
        AccountRaidModule module = new AccountRaidModule(this);
        DaggerAccountRaidComponent.builder()
                .applicationComponent(applicationComponent)
                .accountRaidModule(module)
                .build()
                .inject(this);
    }


    /**
     * Determine the frequent accessed views
     */
    protected void setupUiComponents(final View fragmentView) {
        super.setupUiComponents(fragmentView);
        mRaidListView = fragmentView.findViewById(android.R.id.list);
    }


    /**
     * @param raids the objects containing the raid for the view
     */
    @Override
    public void setupRaidView(final List<RaidEntity> raids) {
        Context context = getActivity() != null ? getActivity().getApplicationContext() : null;
        if (isRemoving() || context == null) {
            return;
        }
        RaidAdapter adapter = new RaidAdapter(
                context,
                android.R.id.list,
                raids.toArray(new RaidEntity[raids.size()]));
        mRaidListView.setLayoutAnimation(createLayoutAdapterController());
        mRaidListView.setAdapter(adapter);
        mRaidListView.startLayoutAnimation();
    }


    /**
     * @return the resource id of the info / data container
     */
    @Override
    protected int getInfoContainerId() {
        return R.id.account_raid_information;
    }


    /**
     * @return the resource id of the error container
     */
    @Override
    protected int getErrorContainerId() {
        return R.id.account_raid_error_container;
    }


    /**
     * @return the resource id of the view
     */
    @Override
    protected int getGettingThingsDoneContainerId() {
        return R.id.account_raid_getting_things_done;
    }
}
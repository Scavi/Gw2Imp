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

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerWorldBossEventTimerComponent;
import com.scavi.de.gw2imp.dagger2.module.WorldBossEventTimerModule;
import com.scavi.de.gw2imp.data.entity.event.WorldBossEntity;
import com.scavi.de.gw2imp.presenter.WorldBossEventTimerPresenter;
import com.scavi.de.gw2imp.ui.adapter.WorldBossesAdapter;
import com.scavi.de.gw2imp.ui.view.IWorldBossEventTimerView;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class WorldBossEventTimerFragment extends AbstractStatusFragment implements
        IWorldBossEventTimerView {
    @Inject
    WorldBossEventTimerPresenter mPresenter;
    private ListView mEventListView;


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
        View view = inflater.inflate(R.layout.fragment_world_boss_event_timer, container, false);
        setupUiComponents(view);
        return view;
    }


    /**
     * Determine the frequent accessed views
     */
    @Override
    protected void setupUiComponents(final View fragmentView) {
        super.setupUiComponents(fragmentView);
        mEventListView = fragmentView.findViewById(android.R.id.list);
    }


    /**
     * @see AbstractFragment#onStart()
     */
    @Override
    public void onStart() {
        super.onStart();
        mPresenter.loadWorldBoss();
    }


    /**
     * @param worldBosses the objects containing the upcoming world bosses
     */
    @Override
    public void setupWorldBossesView(final List<WorldBossEntity> worldBosses) {
        Context context = getActivity() != null ? getActivity().getApplicationContext() : null;
        if (isRemoving() || context == null) {
            return;
        }
        WorldBossesAdapter adapter = new WorldBossesAdapter(
                context,
                android.R.id.list,
                worldBosses.toArray(new WorldBossEntity[worldBosses.size()]));
        mEventListView.setLayoutAnimation(createLayoutAdapterController());
        mEventListView.setAdapter(adapter);
        mEventListView.startLayoutAnimation();
    }


    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    protected void injectComponent(final ApplicationComponent applicationComponent) {
        WorldBossEventTimerModule module = new WorldBossEventTimerModule(this);
        DaggerWorldBossEventTimerComponent.builder()
                .applicationComponent(applicationComponent)
                .worldBossEventTimerModule(module)
                .build()
                .inject(this);
    }


    /**
     * @return the resource id of the info / data container
     */
    @Override
    protected int getInfoContainerId() {
        return R.id.world_boss_information;
    }


    /**
     * @return the resource id of the error container
     */
    @Override
    protected int getErrorContainerId() {
        return R.id.world_boss_error_container;
    }


    /**
     * @return the resource id of the view
     */
    @Override
    protected int getGettingThingsDoneContainerId() {
        return R.id.world_boss_getting_things_done;
    }
}

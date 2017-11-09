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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.data.so.Daily;
import com.scavi.de.gw2imp.presenter.AccountDailyPresenter;
import com.scavi.de.gw2imp.ui.adapter.DailyAdapter;
import com.scavi.de.gw2imp.ui.view.IAccountDailyView;

import java.util.List;

import javax.inject.Inject;

public abstract class AbstractDailyFragment extends AbstractFragment implements IAccountDailyView {
    @Inject
    AccountDailyPresenter mPresenter;
    private ListView mDailyListView;
    private View mDailyInfoContainer;
    private View mGettingThingsDoneContainer;
    private View mDailyErrorContainer;


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
        View view = inflater.inflate(R.layout.fragment_account_daily, container, false);
        setupUiComponents(view);
        return view;
    }


    /**
     * Determine the frequent accessed views
     */
    private void setupUiComponents(final View fragmentView) {
        mDailyListView = fragmentView.findViewById(android.R.id.list);
        mDailyInfoContainer = fragmentView.findViewById(R.id.account_daily_information);
        mDailyErrorContainer = fragmentView.findViewById(R.id.account_daily_error_container);
        mGettingThingsDoneContainer = fragmentView.findViewById(R.id
                .account_daily_getting_things_done);
    }


    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    protected abstract void injectComponent(final ApplicationComponent applicationComponent);


    /**
     * @param dailies the list of dailies as achievements with all information
     */
    @Override
    public void setupDailyView(final List<Daily> dailies) {
        DailyAdapter adapter = new DailyAdapter(
                getContext(),
                android.R.id.list,
                dailies.toArray(new Daily[dailies.size()]));
        mDailyListView.setLayoutAnimation(createLayoutAdapterController());
        mDailyListView.setAdapter(adapter);
        mDailyListView.startLayoutAnimation();
    }

    /**
     * Informs the user about the loading progress
     */
    @Override
    public void onShowProgress() {
        if (isRemoving()) {
            return;
        }
        mDailyInfoContainer.setVisibility(View.GONE);
        mDailyErrorContainer.setVisibility(View.GONE);
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
        mDailyInfoContainer.setVisibility(View.VISIBLE);
        mGettingThingsDoneContainer.setVisibility(View.GONE);
        mDailyErrorContainer.setVisibility(View.GONE);
    }


    /**
     * The loading of the data is done but an error has occurred
     */
    @Override
    public void onHideProgressAfterError() {
        if (isRemoving()) {
            return;
        }
        mDailyErrorContainer.setVisibility(View.VISIBLE);
        mDailyInfoContainer.setVisibility(View.GONE);
        mGettingThingsDoneContainer.setVisibility(View.GONE);
    }
}

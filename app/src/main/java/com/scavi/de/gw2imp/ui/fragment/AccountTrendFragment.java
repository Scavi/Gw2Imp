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
import com.scavi.de.gw2imp.dagger2.component.DaggerAccountTrendComponent;
import com.scavi.de.gw2imp.dagger2.module.AccountTrendModule;
import com.scavi.de.gw2imp.data.so.Trend;
import com.scavi.de.gw2imp.presenter.AccountTrendPresenter;
import com.scavi.de.gw2imp.ui.adapter.TrendAdapter;
import com.scavi.de.gw2imp.ui.view.IAccountTrendView;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class AccountTrendFragment extends AbstractStatusFragment implements
        IAccountTrendView {

    @Inject
    AccountTrendPresenter mPresenter;
    private ListView mTrendListView;

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
        View fragmentView = inflater.inflate(R.layout.fragment_trading_item, container, false);
        setupUiComponents(fragmentView);
        return fragmentView;
    }

    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    private void injectComponent(final ApplicationComponent applicationComponent) {
        AccountTrendModule module = new AccountTrendModule(this);
        DaggerAccountTrendComponent.builder()
                .applicationComponent(applicationComponent)
                .accountTrendModule(module)
                .build()
                .inject(this);
    }


    /**
     * Determine the frequent accessed views
     */
    protected void setupUiComponents(final View fragmentView) {
        super.setupUiComponents(fragmentView);
        mTrendListView = fragmentView.findViewById(android.R.id.list);
    }


    /**
     * Updates the view with the trends
     *
     * @param allTrends all trends
     */
    @Override
    public void updateTrendView(final List<Trend> allTrends) {
        Context context = getActivity() != null ? getActivity().getApplicationContext() : null;
        if (isRemoving() || context == null) {
            return;
        }
        TrendAdapter adapter = new TrendAdapter(
                context,
                android.R.id.list,
                allTrends.toArray(new Trend[allTrends.size()]));
        mTrendListView.setLayoutAnimation(createLayoutAdapterController());
        mTrendListView.setAdapter(adapter);
        mTrendListView.startLayoutAnimation();

    }

    /**
     * @return the resource id of the info / data container
     */
    @Override
    protected int getInfoContainerId() {
        return R.id.account_trend_information;
    }


    /**
     * @return the resource id of the error container
     */
    @Override
    protected int getErrorContainerId() {
        return R.id.account_trend_error_container;
    }


    /**
     * @return the resource id of the view
     */
    @Override
    protected int getGettingThingsDoneContainerId() {
        return R.id.account_trend_getting_things_done;
    }
}

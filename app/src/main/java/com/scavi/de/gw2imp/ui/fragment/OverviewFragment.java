package com.scavi.de.gw2imp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerOverviewComponent;
import com.scavi.de.gw2imp.dagger2.module.OverviewModule;
import com.scavi.de.gw2imp.presenter.OverviewPresenter;
import com.scavi.de.gw2imp.ui.util.ActivityHelper;
import com.scavi.de.gw2imp.ui.view.IOverviewView;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class OverviewFragment extends Fragment implements IOverviewView {
    @Inject
    OverviewPresenter mPresenter;


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
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }


    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    private void injectComponent(final ApplicationComponent applicationComponent) {
        OverviewModule module = new OverviewModule(this);
        DaggerOverviewComponent.builder()
                .applicationComponent(applicationComponent)
                .overviewModule(module)
                .build()
                .inject(this);
    }


    /**
     * Currently, the status information are only updated during on resume.
     * TODO: publish and subscribe for the information (currently not required)
     */
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadStatusData();
    }


    /**
     * Updates the loaded item count in the view
     *
     * @param itemCount the item count
     */
    @Override
    public void updateItemCount(final int itemCount) {
        ActivityHelper.setTextOnTextView(getActivity(), R.id.overview_loaded_item_count, itemCount);
    }


    /**
     * Updates the count of loaded prices in the view
     *
     * @param priceCount the price count
     */
    @Override
    public void updateItemPriceCount(final int priceCount) {
        ActivityHelper.setTextOnTextView(getActivity(), R.id.overview_loaded_price_count,
                priceCount);
    }


    /**
     * Updates the view to set the history count
     *
     * @param historyCount the history count
     */
    @Override
    public void updateHistoryCount(final int historyCount) {
        ActivityHelper.setTextOnTextView(getActivity(), R.id.overview_history_count, historyCount);
    }


    /**
     * Updates the search index depending on the status
     *
     * @param resourceId the resource id
     * @param color      the color of the field
     */
    @Override
    public void updateSearchIndexStatus(@StringRes final int resourceId,
                                        @IdRes final int color) {
        String text = getString(resourceId);
        ActivityHelper.setTextOnTextView(getActivity(), R.id.overview_search_index, text);
        ActivityHelper.setColorOnTextView(getActivity(), R.id.overview_search_index, color);
    }


    /**
     * Updates the word index depending on the status
     *
     * @param resourceId the resource id
     * @param color      the color of the field
     */
    @Override
    public void updateWordIndexStatus(final int resourceId,
                                      final int color) {
        String text = getString(resourceId);
        ActivityHelper.setTextOnTextView(getActivity(), R.id.overview_word_index, text);
        ActivityHelper.setColorOnTextView(getActivity(), R.id.overview_word_index, color);
    }
}
package com.scavi.de.gw2imp.presenter;

import com.scavi.de.gw2imp.data.so.Trend;
import com.scavi.de.gw2imp.model.AccountTrendModel;
import com.scavi.de.gw2imp.ui.view.IAccountTrendView;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;


@ParametersAreNonnullByDefault
public class AccountTrendPresenter {
    private final IAccountTrendView mView;
    private final AccountTrendModel mModel;

    /**
     * Constructor
     *
     * @param view  the view for the application item watch
     * @param model the model for the application item watch
     */
    @Inject
    public AccountTrendPresenter(final IAccountTrendView view,
                                 final AccountTrendModel model) {
        mView = view;
        mModel = model;
    }


    /**
     * Determines all trends and initiate the UI update
     */
    public void onCreate() {
        Runnable trendUpdater = createTrendUpdater();
        mModel.getExecutorAccess().getBackgroundThreadExecutor().execute(trendUpdater);
    }


    /**
     * Selects all trends from the UI and updates them in the view.
     *
     * @return the runnable
     */
    private Runnable createTrendUpdater() {
        return () -> {
            List<Trend> trends = mModel.determineAllTrends();
            mModel.getExecutorAccess().getUiThreadExecutor().execute(() ->
                    mView.updateTrendView(trends));
        };
    }
}

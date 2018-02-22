package com.scavi.de.gw2imp.dagger2.module;


import android.content.Context;
import android.support.annotation.NonNull;

import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.model.TradingItemsModel;
import com.scavi.de.gw2imp.presenter.TradingItemsPresenter;
import com.scavi.de.gw2imp.ui.view.ITradingItemsView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class TradingItemsModule {
    private final ITradingItemsView mView;

    /**
     * Constructor
     *
     * @param view the view of all trading items and their prices
     */
    public TradingItemsModule(final ITradingItemsView view) {
        this.mView = view;
    }

    /**
     * @return the view for the world boss event timer
     */
    @Provides
    @Nonnull
    public ITradingItemsView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of all trading items and their
     *              prices
     * @return the presenter of the MVP pattern in the context of all trading items and their prices
     */
    @Provides
    @NonNull
    public TradingItemsPresenter providePresenter(final TradingItemsModel model) {
        return new TradingItemsPresenter(mView, model);
    }


    /**
     * @param context        the context to global information about the application environment
     * @param impDatabase    the database access of this application
     * @param executorAccess to access the main and background threads
     * @return the model of the MVP pattern in the context  of all trading items and their prices
     */
    @Provides
    @NonNull
    public TradingItemsModel provideModel(final Context context,
                                          final IDatabaseAccess impDatabase,
                                          final IExecutorAccess executorAccess) {
        return new TradingItemsModel(context, impDatabase, executorAccess);
    }
}

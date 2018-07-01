package com.scavi.de.gw2imp.dagger2.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.model.AccountTrendModel;
import com.scavi.de.gw2imp.presenter.AccountTrendPresenter;
import com.scavi.de.gw2imp.ui.view.IAccountTrendView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class AccountTrendModule {
    private final IAccountTrendView mView;

    /**
     * Constructor
     *
     * @param view the view of application item price watch
     */
    public AccountTrendModule(final IAccountTrendView view) {
        this.mView = view;
    }

    /**
     * @return the view of the application watch
     */
    @Provides
    @Nonnull
    public IAccountTrendView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of the application watch
     * @return the presenter of the MVP pattern in the context of the application watch
     */
    @Provides
    @NonNull
    public AccountTrendPresenter providePresenter(final AccountTrendModel model) {
        return new AccountTrendPresenter(mView, model);
    }

    /**
     * @param context           the context to global information about the application environment
     * @param impDatabaseAccess the imp database access
     * @param executorAccess    to access the main and background threads
     * @return the model of the MVP pattern in the context of the application watch
     */
    @Provides
    @NonNull
    public AccountTrendModel provideModel(final Context context,
                                          final IDatabaseAccess impDatabaseAccess,
                                          final IExecutorAccess executorAccess) {
        return new AccountTrendModel(context, impDatabaseAccess, executorAccess);
    }
}

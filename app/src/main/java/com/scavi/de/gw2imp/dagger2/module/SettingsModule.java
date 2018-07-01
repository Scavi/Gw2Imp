package com.scavi.de.gw2imp.dagger2.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.scavi.de.gw2imp.model.SettingsModel;
import com.scavi.de.gw2imp.preferences.IPreferenceAccess;
import com.scavi.de.gw2imp.presenter.SettingsPresenter;
import com.scavi.de.gw2imp.ui.view.ISettingsView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;

@Module
@ParametersAreNonnullByDefault
public class SettingsModule {
    private final ISettingsView mView;

    /**
     * Constructor
     *
     * @param view the view of application settings
     */
    public SettingsModule(final ISettingsView view) {
        this.mView = view;
    }

    /**
     * @return the view of the application settings
     */
    @Provides
    @Nonnull
    public ISettingsView provideView() {
        return mView;
    }


    /**
     * @param model the model of the MVP pattern in the context of the application settings
     * @return the presenter of the MVP pattern in the context of the application settings
     */
    @Provides
    @NonNull
    public SettingsPresenter providePresenter(final SettingsModel model) {
        return new SettingsPresenter(mView, model);
    }

    /**
     * @param context          the context to global information about the application environment
     * @param preferenceAccess access to the shared preferences of this application
     * @return the model of the MVP pattern in the context of the application settings
     */
    @Provides
    @NonNull
    public SettingsModel provideModel(final Context context,
                                      final IPreferenceAccess preferenceAccess) {
        return new SettingsModel(context, preferenceAccess);
    }
}

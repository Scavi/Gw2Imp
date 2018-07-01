package com.scavi.de.gw2imp.presenter;

import com.scavi.de.gw2imp.model.SettingsModel;
import com.scavi.de.gw2imp.ui.view.ISettingsView;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

@ParametersAreNonnullByDefault
public class SettingsPresenter {
    private final ISettingsView mView;
    private final SettingsModel mModel;

    /**
     * Constructor
     *
     * @param view  the view for the application settings
     * @param model the model for the application settings
     */
    @Inject
    public SettingsPresenter(final ISettingsView view,
                             final SettingsModel model) {
        mView = view;
        mModel = model;
    }
}

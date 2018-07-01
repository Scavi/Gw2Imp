package com.scavi.de.gw2imp.dagger2.component;


import com.scavi.androidimp.util.ActivityScope;
import com.scavi.de.gw2imp.dagger2.module.SettingsModule;
import com.scavi.de.gw2imp.model.SettingsModel;
import com.scavi.de.gw2imp.presenter.SettingsPresenter;
import com.scavi.de.gw2imp.ui.fragment.SettingsFragment;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = SettingsModule.class)
public interface SettingsComponent {
    /**
     * The inject method of the fragment for the dagger component
     *
     * @param fragment the fragment to inject
     */
    void inject(final SettingsFragment fragment);


    /**
     * @return the presenter of the MVP pattern in the context of the application settings
     */
    SettingsPresenter getPresenter();


    /**
     * @return the model of the MVP pattern in the context of the application settings
     */
    SettingsModel getModel();
}

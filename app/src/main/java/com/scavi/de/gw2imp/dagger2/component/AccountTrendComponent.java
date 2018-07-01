package com.scavi.de.gw2imp.dagger2.component;

import com.scavi.androidimp.util.ActivityScope;
import com.scavi.de.gw2imp.dagger2.module.AccountTrendModule;
import com.scavi.de.gw2imp.model.AccountTrendModel;
import com.scavi.de.gw2imp.presenter.AccountTrendPresenter;
import com.scavi.de.gw2imp.ui.fragment.AccountTrendFragment;

import dagger.Component;


@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = AccountTrendModule.class)
public interface AccountTrendComponent {
    /**
     * The inject method of the fragment for the dagger component
     *
     * @param fragment the fragment to inject
     */
    void inject(final AccountTrendFragment fragment);


    /**
     * @return the presenter of the MVP pattern in the context of the application watch
     */
    AccountTrendPresenter getPresenter();


    /**
     * @return the model of the MVP pattern in the context of the application watch
     */
    AccountTrendModel getModel();
}

package com.scavi.de.gw2imp.dagger2.component;


import com.scavi.de.gw2imp.dagger2.module.TradingItemsModule;
import com.scavi.de.gw2imp.model.TradingItemsModel;
import com.scavi.de.gw2imp.presenter.TradingItemsPresenter;
import com.scavi.de.gw2imp.ui.fragment.TradingItemsFragment;
import com.scavi.de.gw2imp.util.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = TradingItemsModule.class)
public interface TradingItemsComponent {
    /**
     * The inject method of the activity for the dagger component
     *
     * @param fragment the fragment to inject
     */
    void inject(final TradingItemsFragment fragment);


    /**
     * @return the presenter of the MVP pattern in the context of all trading items and their prices
     */
    TradingItemsPresenter getPresenter();


    /**
     * @return the model of the MVP pattern in the context of all trading items and their prices
     */
    TradingItemsModel getModel();
}

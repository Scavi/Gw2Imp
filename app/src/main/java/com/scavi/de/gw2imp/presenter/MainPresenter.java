/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scavi.de.gw2imp.presenter;

import com.scavi.de.gw2imp.model.MainModel;
import com.scavi.de.gw2imp.ui.view.IMainView;

public class MainPresenter {
    private final IMainView mView;
    private final MainModel mModel;


    /**
     * Constructor
     *
     * @param view  the main view representing also the navigation view for the main area of the
     *              application
     * @param model the model of the MVP pattern in the context of the main activity
     */
    public MainPresenter(final IMainView view,
                         final MainModel model) {
        mView = view;
        mModel = model;
    }


    /**
     * Process the information about the creation of the view to the module to update the status
     */
    public void onCreate() {
        mModel.persistRoutingState();
    }


    /**
     * The user clicked on the account overview view / information
     */
    public void onClickAccountOverview() {
        mView.routeAccountOverview();
    }


    /**
     * The user clicked on the account character view / information
     */
    public void onClickAccountCharacter() {
        mView.routeAccountCharacter();
    }


    /**
     * The user clicked on the account dailies view / information
     */
    public void onClickDailiesToday() {
        mView.routeDailiesToday();
    }


    /**
     * The user clicked on the account raid view / information
     */
    public void onClickAccountRaid() {
        mView.routeAccountRaid();
    }


    /**
     * The user clicked on the account achievements view / information
     */
    public void onClickAccountAchievements() {
        mView.routeAccountAchievements();
    }


    /**
     * The user clicked on the account buying transactions (history / current)
     */
    public void onClickAccountTransactionBuy() {
        mView.routeAccountTransactionBuy();
    }


    /**
     * The user clicked on the account selling transactions (history / current)
     */
    public void onClickAccountTransactionSell() {
        mView.routeAccountTransactionSell();
    }


    /**
     * The user clicked on the trading market information
     */
    public void onClickTradingItems() {
        mView.routeTradingItems();
    }


    /**
     * The user clicked to show the development of prices (e.g. prices, gems, ...)
     */
    public void onClickWatchPriceDevelopment() {
        mView.routeWatchPriceDevelopment();
    }

    /**
     * The user clicked on the world boss event timer
     */
    public void onClickWorldBossEventTimer() {
        mView.routeWorldBossEventTimer();
    }


    /**
     * The user clicked on the dailies tomorrow view / information
     */
    public void onClickDailiesTomorrow() {
        mView.routeDailiesTomorrow();
    }


    /**
     * The user clicked on the license information
     */
    public void onClickLicense() {
        mView.routeToLicense();
    }
}

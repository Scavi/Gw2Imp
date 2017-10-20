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
package com.scavi.de.gw2imp.ui.view;

public interface IMainNavigation {

    /**
     * The user clicked on the account overview view / information
     */
    void onClickAccountOverView();

    /**
     * The user clicked on the account character view / information
     */
    void onClickAccountCharacterView();

    /**
     * The user clicked on the account raid view / information
     */
    void onClickAccountRaidView();

    /**
     * The user clicked on the account buying transactions (history / current)
     */
    void onClickAccountTransactionBuy();

    /**
     * The user clicked on the account selling transactions (history / current)
     */
    void onClickAccountTransactionSell();

    /**
     * The user clicked on the trading market information (up)
     */
    void onClickTradingUp();

    /**
     * The user clicked on the trading market information (down)
     */
    void onClickTradingDown();
}

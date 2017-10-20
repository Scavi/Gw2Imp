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

public interface IApiAccountKeyView {


    /**
     * Opens the {@link android.app.Activity to scan the QR code}
     */
    void openScanQrCode();


    /**
     * Shows the progress of the registration request
     */
    void onShowProgress();


    /**
     * Hides the progress of the registration request
     */
    void onHideProgress();


    /**
     * @return the current entered api account key
     */
    String getApiAccountKey();

    /**
     * Invalidates the api key field due to the incorrect api key
     *
     * @param errorMessage the error message for the
     */
    void invalidateApiKeyField(final String errorMessage);

    /**
     * Notifies the user about the occurred error. The error can have different characteristics
     * (e.g. an invalid api key format, no internet connection, technical errors)
     *
     * @param errorMessage the error message for the user
     */
    void showUserError(final String errorMessage);

    /**
     * Routes to the main application
     */
    void routeToMain();
}

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

import com.scavi.de.gw2imp.communication.handler.DefaultCallback;
import com.scavi.de.gw2imp.communication.response.account.Account;
import com.scavi.de.gw2imp.model.ApiAccountKeyModel;
import com.scavi.de.gw2imp.ui.view.IApiAccountKeyView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class ApiAccountKeyPresenter {
    private static final String TAG = "ApiAccountKeyPresenter";
    private final IApiAccountKeyView mView;
    private final ApiAccountKeyModel mModel;

    /**
     * Constructor
     *
     * @param view  the view for the api account details
     * @param model the model for the api account details
     */
    @Inject
    public ApiAccountKeyPresenter(final IApiAccountKeyView view,
                                  final ApiAccountKeyModel model) {
        mView = view;
        mModel = model;
    }

    /**
     * Opens the activity to scan the api key as QR code
     */
    public void onScanApiKey() {
        mView.openScanQrCode();
    }


    /**
     * Tries to register the api key. Verifies the API key. If the API has a valid format,
     * tries to send a request to the server side with the api key
     */
    public void onRegisterApiKey() {
        String apiKey = mView.getApiAccountKey();
        if (!mModel.isValidApiKeyFormat(apiKey)) {
            String apiKeyError = mModel.analyseApiKeyError(apiKey);
            String apiKeyFormat = mModel.getApiKeyFormatInfo();
            mView.showUserError(apiKeyError);
            mView.invalidateApiKeyField(apiKeyFormat);
            return;
        }
        // the api key has a valid format...
        mView.onShowProgress();
        mModel.storeApiKey(apiKey);
        // initiates the call to determine account information and verifies the given api key
        mModel.requestAccount(new DefaultCallback<Account>(mView, mModel) {
            @Override
            protected void processResponse(final Call<Account> call,
                                           final Response<Account> response) {
                // the account request was valid - route to the next activity
                if (response.isSuccessful()) {
                    mView.routeToMain();
                } else {
                    String errorInformation = mModel.determineAccountHttpError(response.code());
                    mView.showUserError(errorInformation);
                }
            }
        });
    }


    /**
     * Skips to enter the API key - just move to the main section of the app
     */
    public void onSkipApiKey() {
        mView.routeToMain();
    }
}

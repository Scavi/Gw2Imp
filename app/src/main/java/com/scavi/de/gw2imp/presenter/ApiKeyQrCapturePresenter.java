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

import android.support.annotation.NonNull;

import com.scavi.de.gw2imp.preferences.qr.QrResult;
import com.scavi.de.gw2imp.model.ApiKeyQrCaptureModel;
import com.scavi.de.gw2imp.ui.view.IApiKeyQrCaptureView;

import java.util.Observable;
import java.util.Observer;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

@ParametersAreNonnullByDefault
public class ApiKeyQrCapturePresenter implements Observer {
    private final IApiKeyQrCaptureView mView;
    private final ApiKeyQrCaptureModel mModel;

    /**
     * Constructor
     *
     * @param view  the view for the api key account QR code view
     * @param model the model of the MVP pattern in the context of the api key account QR code
     */
    @Inject
    public ApiKeyQrCapturePresenter(final IApiKeyQrCaptureView view,
                                    final ApiKeyQrCaptureModel model) {
        mView = view;
        mModel = model;
        mModel.addObserver(this);
    }

    /**
     * @return the barcode receiver module
     */
    @NonNull
    public BarcodeRetriever determineBarcodeReceiver() {
        return mModel;
    }

    /**
     * Processes the update from the observable component
     *
     * @param observable the {@link ApiKeyQrCaptureModel}
     * @param arg        the {@link QrResult}
     */
    @Override
    public void update(final Observable observable,
                       final Object arg) {
        if (!(observable instanceof ApiKeyQrCaptureModel)) {
            return;
        }
        if (!(arg instanceof QrResult)) {
            return;
        }
        mView.onClose((QrResult) arg);
    }
}

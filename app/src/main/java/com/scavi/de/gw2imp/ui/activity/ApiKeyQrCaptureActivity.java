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
package com.scavi.de.gw2imp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerApiKeyQrCaptureComponent;
import com.scavi.de.gw2imp.dagger2.module.ApiKeyQrCaptureModule;
import com.scavi.de.gw2imp.preferences.qr.QrResult;
import com.scavi.de.gw2imp.model.ApiKeyQrCaptureModel;
import com.scavi.de.gw2imp.presenter.ApiKeyQrCapturePresenter;
import com.scavi.de.gw2imp.ui.view.IApiKeyQrCaptureView;

import javax.inject.Inject;

import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

public class ApiKeyQrCaptureActivity extends AppCompatActivity implements IApiKeyQrCaptureView {
    public static final String QR_CODE = "QRCode";
    @Inject
    ApiKeyQrCapturePresenter mPresenter;

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut
     *                           down then this Bundle contains the data it most recently supplied
     *                           in this method. Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_key_qr_capture);
        injectComponent(((IApplication) getApplication()).getComponent());
        setupUiComponents();
    }


    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    private void injectComponent(@NonNull final ApplicationComponent applicationComponent) {
        ApiKeyQrCaptureModule module = new ApiKeyQrCaptureModule(this);
        DaggerApiKeyQrCaptureComponent.builder()
                .applicationComponent(applicationComponent)
                .apiKeyQrCaptureModule(module)
                .build()
                .inject(this);
    }


    /**
     * Setups / registers the UI components of this activity
     */
    private void setupUiComponents() {
        BarcodeCapture barcodeCapture = (BarcodeCapture) getSupportFragmentManager()
                .findFragmentById(R.id.api_key_qr);
        BarcodeRetriever bcRetriever = mPresenter.determineBarcodeReceiver();
        barcodeCapture.setRetrieval(bcRetriever);
        setupActionBar(getSupportActionBar());
    }


    /**
     * Setups the actionbar for the activity
     *
     * @param actionBar the actionbar to setup
     */
    protected void setupActionBar(@Nullable final ActionBar actionBar) {
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(false);
        }
    }



    /**
     * Sets the result based on the received {@link QrResult}  and closes the activity
     *
     * @param qrResult the qr result containing the return code and the qr code (in case of success)
     */
    @Override
    public void onClose(final QrResult qrResult) {
        int returnCode = qrResult.getReturnCode() == ApiKeyQrCaptureModel.RC_VALID ?
                Activity.RESULT_OK : Activity.RESULT_CANCELED;
        Intent resultIntent = new Intent();
        resultIntent.putExtra(QR_CODE, qrResult.getQrCode());
        setResult(returnCode, resultIntent);
        finish();
    }
}

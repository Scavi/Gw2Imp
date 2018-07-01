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

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerApiAccountKeyComponent;
import com.scavi.de.gw2imp.dagger2.module.ApiAccountKeyModule;
import com.scavi.de.gw2imp.presenter.ApiAccountKeyPresenter;
import com.scavi.de.gw2imp.ui.util.ActivityHelper;
import com.scavi.de.gw2imp.ui.view.IApiAccountKeyView;

import javax.inject.Inject;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ApiAccountKeyActivity extends AppCompatActivity implements IApiAccountKeyView,
        View.OnClickListener {
    private static final int QR_CODE_ACTIVITY_CALL = 1;

    @Inject
    ApiAccountKeyPresenter mPresenter;
    private TextInputLayout mApiKeyLayout;
    private TextView mApiKeyTextView;
    private View mApiKeyInfoContainer;
    private View mApiKeyValidatingContainer;
    private View mApiKeyButtonContainer;

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
        setContentView(R.layout.activity_account_api_key);
        injectComponent(((IApplication) getApplication()).getComponent());
        setupUiComponents();
    }


    /**
     * Setups and registers the UI components of this activity
     */
    private void setupUiComponents() {
        ActivityHelper.setupClickableComponent(this, R.id.skip_api_account_key);
        ActivityHelper.setupClickableComponent(this, R.id.verify_api_account_key);
        ActivityHelper.setupClickableComponent(this, R.id.account_api_key_scan);
        mApiKeyLayout = findViewById(R.id.account_api_key_container);
        mApiKeyTextView = findViewById(R.id.account_api_key_input_field);
        mApiKeyInfoContainer = findViewById(R.id.account_api_key_information_container);
        mApiKeyValidatingContainer = findViewById(R.id.account_api_key_validating_container);
        mApiKeyButtonContainer = findViewById(R.id.account_api_key_button_container);
        setupActionBar(getSupportActionBar());
    }

    /**
     * Setups the actionbar for the activity
     *
     * @param actionBar the actionbar to setup
     */
    protected void setupActionBar(@Nullable final ActionBar actionBar) {
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    private void injectComponent(@NonNull final ApplicationComponent applicationComponent) {
        ApiAccountKeyModule module = new ApiAccountKeyModule(this);
        DaggerApiAccountKeyComponent.builder()
                .applicationComponent(applicationComponent)
                .apiAccountKeyModule(module)
                .build()
                .inject(this);
    }


    /**
     * Called when a registered view has been clicked.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(@NonNull final View view) {
        if (view.getId() == R.id.skip_api_account_key) {
            mPresenter.onSkipApiKey();
        } else if (view.getId() == R.id.verify_api_account_key) {
            mPresenter.onRegisterApiKey();
        } else if (view.getId() == R.id.account_api_key_scan) {
            mPresenter.onScanApiKey();
        }
    }

    /**
     * Called after the finish of the {@link android.app.Activity} that was called via
     * {{@link android.app.Activity}#startActivityForResult} is closed
     */
    @Override
    protected void onActivityResult(final int requestCode,
                                    final int resultCode,
                                    final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QR_CODE_ACTIVITY_CALL) {
            if (resultCode == RESULT_OK) {
                String qrCode = data.getStringExtra(ApiKeyQrCaptureActivity.QR_CODE);
                mApiKeyTextView.setText(qrCode);
            }
        }
    }


    /**
     * Starts the activity to capture the QR code
     */
    @Override
    public void openScanQrCode() {
        Intent intent = new Intent(this, ApiKeyQrCaptureActivity.class);
        startActivityForResult(intent, QR_CODE_ACTIVITY_CALL);
    }

    /**
     * Shows the progress dialog if the dialog not already is showing
     */
    @Override
    public void onShowProgress() {
        if (isFinishing()) {
            return;
        }
        mApiKeyInfoContainer.setVisibility(View.GONE);
        mApiKeyButtonContainer.setVisibility(View.GONE);
        mApiKeyValidatingContainer.setVisibility(View.VISIBLE);
    }


    /**
     * Hides the progress dialog if the dialog is showing
     */
    @Override
    public void onHideProgress() {
        if (isFinishing()) {
            return;
        }
        mApiKeyInfoContainer.setVisibility(View.VISIBLE);
        mApiKeyButtonContainer.setVisibility(View.VISIBLE);
        mApiKeyValidatingContainer.setVisibility(View.GONE);
    }


    @Override
    public void onHideProgressAfterError() {
        throw new UnsupportedOperationException("Not implemented!");
    }

    /**
     * @return the current entered api account key
     */
    @Override
    public String getApiAccountKey() {
        return mApiKeyTextView.getText().toString();
    }


    /**
     * Invalidates the api key field due to the incorrect api key
     *
     * @param errorMessage the error message for the field
     */
    @Override
    public void invalidateApiKeyField(@NonNull final String errorMessage) {
        mApiKeyLayout.setError(errorMessage);
    }


    /**
     * Notifies the user about the occurred error. The error can have different characteristics
     * (e.g. an invalid api key format, no internet connection, technical errors)
     *
     * @param errorMessage the error message for the user
     */
    @Override
    public void showUserError(@NonNull final String errorMessage) {
        if (isFinishing()) {
            return;
        }
        AlertDialog errorDialog = ActivityHelper.createSimpleOkDialog(this, errorMessage);
        errorDialog.show();
    }


    /**
     * Routes to the main section of the app
     */
    @Override
    public void routeToMain() {
        Intent routingTarget = new Intent(getApplicationContext(), MainActivity.class);
        routingTarget.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(routingTarget);
        finish();
    }
}
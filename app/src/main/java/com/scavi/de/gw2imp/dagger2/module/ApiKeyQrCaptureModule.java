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
package com.scavi.de.gw2imp.dagger2.module;

import android.support.annotation.NonNull;

import com.scavi.de.gw2imp.model.ApiKeyQrCaptureModel;
import com.scavi.de.gw2imp.presenter.ApiKeyQrCapturePresenter;
import com.scavi.de.gw2imp.ui.view.IApiKeyQrCaptureView;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import dagger.Module;
import dagger.Provides;


@Module
@ParametersAreNonnullByDefault
public class ApiKeyQrCaptureModule {
    private final IApiKeyQrCaptureView mView;

    /**
     * Constructor
     *
     * @param mView the view for the api key account QR code view
     */
    public ApiKeyQrCaptureModule(final IApiKeyQrCaptureView mView) {
        this.mView = mView;
    }

    /**
     * @return the view for the api key account QR code view
     */
    @Provides
    @Nonnull
    public IApiKeyQrCaptureView provideView() {
        return mView;
    }

    /**
     * @param model the model of the MVP pattern in the context of the api key account QR code
     * @return the presenter of the MVP pattern in the context of the api key account QR code
     */
    @Provides
    @NonNull
    public ApiKeyQrCapturePresenter providePresenter(final ApiKeyQrCaptureModel model) {
        return new ApiKeyQrCapturePresenter(mView, model);
    }

    /**
     * @return the model of the MVP pattern in the context of the api key account QR code
     */
    @Provides
    @NonNull
    public ApiKeyQrCaptureModel provideModel() {
        return new ApiKeyQrCaptureModel();
    }
}

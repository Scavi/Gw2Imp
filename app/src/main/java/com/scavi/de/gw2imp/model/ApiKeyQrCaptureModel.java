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
package com.scavi.de.gw2imp.model;

import android.util.SparseArray;

import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.barcode.Barcode;
import com.scavi.de.gw2imp.preferences.qr.QrResult;

import java.util.List;
import java.util.Observable;

import javax.annotation.ParametersAreNonnullByDefault;

import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

@ParametersAreNonnullByDefault
public class ApiKeyQrCaptureModel extends Observable implements BarcodeRetriever {
    public static final int RC_VALID = 0;
    public static final int RC_RETRIEVE_FAILED = -10;
    public static final int RC_MISSING_PERMISSION = -20;


    /**
     * A barcode was recognized. Notifies the observers about it
     *
     * @param barcode the received barcode
     */
    @Override
    public void onRetrieved(final Barcode barcode) {
        // TODO validate the barcode format
        QrResult qrResult = new QrResult(RC_VALID, barcode.rawValue);
        setChanged();
        notifyObservers(qrResult);
    }

    @Override
    public void onRetrievedMultiple(final Barcode closetToClick,
                                    final List<BarcodeGraphic> barcode) {
        // TODO
    }

    @Override
    public void onBitmapScanned(final SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onRetrievedFailed(final String reason) {
        QrResult qrResult = new QrResult(RC_RETRIEVE_FAILED);
        notifyObservers(qrResult);
    }

    @Override
    public void onPermissionRequestDenied() {
        QrResult qrResult = new QrResult(RC_MISSING_PERMISSION);
        notifyObservers(qrResult);
    }
}

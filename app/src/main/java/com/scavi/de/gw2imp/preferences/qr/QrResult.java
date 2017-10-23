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
package com.scavi.de.gw2imp.preferences.qr;

import android.support.annotation.Nullable;

public class QrResult {
    private final int mReturnCode;
    private final String mQrCode;


    /**
     * Constructor
     *
     * @param returnCode the code for the activity
     */
    public QrResult(final int returnCode) {
        this(returnCode, null);
    }


    /**
     * Constructor
     *
     * @param returnCode the code for the activity
     * @param qrCode     the result of the qr code
     */
    public QrResult(final int returnCode,
                    @Nullable final String qrCode) {
        mReturnCode = returnCode;
        mQrCode = qrCode;
    }

    /**
     * @return the code for the activity
     */
    public String getQrCode() {
        return mQrCode;
    }


    /**
     * @return the result of the qr code
     */
    public int getReturnCode() {
        return mReturnCode;
    }
}

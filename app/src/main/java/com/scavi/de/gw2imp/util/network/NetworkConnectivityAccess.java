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
 *
 */
package com.scavi.de.gw2imp.util.network;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class NetworkConnectivityAccess implements IConnectivityAccess {
    private final Context mContext;

    /**
     * Constructor
     *
     * @param context the context to global information about the application environment
     */
    public NetworkConnectivityAccess(final Context context) {
        mContext = context;
    }

    /**
     * This method verifies if the wifi manager is enabled and has a connection
     *
     * @return <code>true</code> has a wifi connection
     * <code>false</code> has not a wifi connection
     */
    @Override
    public boolean hasWifiConnection() {
        WifiManager wifiManager = (WifiManager)
                mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null && wifiManager.isWifiEnabled()) {
            WifiInfo info = wifiManager.getConnectionInfo();
            if (info != null && info.getNetworkId() != -1) {
                return true;
            }
        }
        return false;
    }
}

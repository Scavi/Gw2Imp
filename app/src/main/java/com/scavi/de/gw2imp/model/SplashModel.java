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

import android.content.Context;
import android.widget.Toast;

import com.scavi.de.gw2imp.preferences.IPreferenceAccess;
import com.scavi.de.gw2imp.ui.activity.ApiAccountKeyActivity;
import com.scavi.de.gw2imp.ui.activity.MainActivity;
import com.scavi.de.gw2imp.ui.util.ActivityHelper;
import com.scavi.de.gw2imp.util.RoutingState;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

@ParametersAreNonnullByDefault
public class SplashModel extends AbstractModel {
    private static final String OBFUSCATION_TEST_NAME = "com.scavi.de.gw2imp.util.ActivityHelper";
    private final IPreferenceAccess mPreferenceAccess;

    /**
     * Constructor
     *
     * @param context          the context to global information about the application environment
     * @param preferenceAccess access to the shared preferences of this application
     */
    @Inject
    public SplashModel(final Context context,
                       final IPreferenceAccess preferenceAccess) {
        super(context);
        mPreferenceAccess = preferenceAccess;
    }

    /**
     * Verifies that the app is obfuscated
     */
    public void testObfuscation() {
        if (OBFUSCATION_TEST_NAME.equals(ActivityHelper.class.getName())) {
            Toast.makeText(mContext,
                    "Code not obfuscated! Don't use it as a release version!",
                    Toast.LENGTH_LONG).show();
        }
    }


    /**
     * @return the target routing {@link android.app.Activity} based on the current
     * {@link RoutingState}
     */
    public Class getRoutingTarget() {
        RoutingState state = mPreferenceAccess.readRoutingState(mContext);
        return state == RoutingState.MainApplication ? MainActivity.class :
                ApiAccountKeyActivity.class;
    }
}

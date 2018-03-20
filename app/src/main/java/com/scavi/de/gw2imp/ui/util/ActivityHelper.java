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
package com.scavi.de.gw2imp.ui.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.scavi.de.gw2imp.R;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ActivityHelper {

    /**
     * Private constructor for the helper
     */
    private ActivityHelper() {
    }

    /**
     * Determines the id {@link View} to the given viewId and sets the passed {@link Activity} as
     * click listener
     *
     * @param activity the source activity and click listener
     * @param viewId   the id of the view
     */
    public static <T extends Activity & View.OnClickListener> void setupClickableComponent(
            final T activity,
            @IdRes final int viewId) {
        final View targetView = activity.findViewById(viewId);
        targetView.setOnClickListener(activity);
    }


    /**
     * Shows the message in the given dialog
     *
     * @param activity the hosting activity
     * @param message  the message
     */
    @Nonnull
    public static void showMessageInDialog(@Nonnull final Activity activity,
                                  @Nonnull final String message) {
        AlertDialog dialog = createSimpleOkDialog(activity, message);
        dialog.show();
    }

    /**
     * Creates a simple ok dialog to the given message (without a title)
     *
     * @param activity the hosting activity
     * @param message  the message
     * @return the {@link AlertDialog} or <code>null</code> if no host activity is passed
     */
    @NonNull
    public static AlertDialog createSimpleOkDialog(@Nonnull final Activity activity,
                                                   @Nonnull final String message) {
        AlertDialog.Builder dialogCreator = new AlertDialog.Builder(activity);
        dialogCreator.setMessage(message);
        dialogCreator.setPositiveButton(android.R.string.ok,
                (paramDialogInterface, paramInt) -> {
                    // nothing to do
                });
        AlertDialog alertDialog = dialogCreator.create();
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().getAttributes().windowAnimations =
                    R.style.DefaultDialogAnimation;
        }
        return alertDialog;
    }
}

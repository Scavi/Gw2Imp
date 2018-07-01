package com.scavi.de.gw2imp.model;

import android.content.Context;

import com.scavi.de.gw2imp.preferences.IPreferenceAccess;

import javax.annotation.ParametersAreNonnullByDefault;

public class SettingsModel extends AbstractModel {
    private final IPreferenceAccess mPreferenceAccess;


    /**
     * Constructor
     *
     * @param context          the context to global information about the application environment
     * @param preferenceAccess access to the shared preferences of this application
     */
    public SettingsModel(final Context context,
                         final IPreferenceAccess preferenceAccess) {
        super(context);
        mPreferenceAccess = preferenceAccess;
    }
}

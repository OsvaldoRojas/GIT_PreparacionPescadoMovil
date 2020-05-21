package com.grupo.pinsa.libraries;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

public abstract class GrupoPinsaAplicacion extends Application {
    public static Application GRUPO_PINSA;

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor;

    public GrupoPinsaAplicacion() {
        super();
        GRUPO_PINSA = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferencesEditor = sharedPreferences.edit();
    }

    /**
     * SharedPreferences
     */
    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static SharedPreferences.Editor getSharedPreferencesEditor() {
        return sharedPreferencesEditor;
    }
}

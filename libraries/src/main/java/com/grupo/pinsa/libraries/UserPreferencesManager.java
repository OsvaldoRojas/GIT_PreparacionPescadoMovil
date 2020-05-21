package com.grupo.pinsa.libraries;

import com.grupo.pinsa.libraries.dialogos.Dialogo;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

public class UserPreferencesManager {
    /**
     * Constantes Privadas
     */
    private static final int MSG_ERROR_ACTUALIZAR = R.string.grupopinsa_preferences_error_put;
    private static final int MSG_ERROR_OBTENER = R.string.grupopinsa_preferences_error_get;

    /**
     * Exists Preferences
     */
    public static boolean existsPreference(String key) {
        return GrupoPinsaAplicacion.getSharedPreferences().contains(key);
    }

    /**
     * Put Preferences
     */
    public static boolean putBooleanPreference(String key, boolean value) {
        try {
            GrupoPinsaAplicacion.getSharedPreferencesEditor().putBoolean(key, value);
            GrupoPinsaAplicacion.getSharedPreferencesEditor().commit();
            return true;
        } catch (Exception e) {
            Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_ACTUALIZAR, Dialogo.ACEPTAR, true);
            return false;
        }
    }

    public static boolean putStringPreference(String key, String value) {
        try {
            GrupoPinsaAplicacion.getSharedPreferencesEditor().putString(key, value);
            GrupoPinsaAplicacion.getSharedPreferencesEditor().commit();
            return true;
        } catch (Exception e) {
            Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_ACTUALIZAR, Dialogo.ACEPTAR, true);
            return false;
        }
    }

    public static boolean putIntegerPreference(String key, int value) {
        try {
            GrupoPinsaAplicacion.getSharedPreferencesEditor().putInt(key, value);
            GrupoPinsaAplicacion.getSharedPreferencesEditor().commit();
            return true;
        } catch (Exception e) {
            Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_ACTUALIZAR, Dialogo.ACEPTAR, true);
            return false;
        }
    }

    public static boolean putFloatPreference(String key, float value) {
        try {
            GrupoPinsaAplicacion.getSharedPreferencesEditor().putFloat(key, value);
            GrupoPinsaAplicacion.getSharedPreferencesEditor().commit();
            return true;
        } catch (Exception e) {
            Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_ACTUALIZAR, Dialogo.ACEPTAR, true);
            return false;
        }
    }

    public static boolean putLongPreference(String key, long value) {
        try {
            GrupoPinsaAplicacion.getSharedPreferencesEditor().putLong(key, value);
            GrupoPinsaAplicacion.getSharedPreferencesEditor().commit();
            return true;
        } catch (Exception e) {
            Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_ACTUALIZAR, Dialogo.ACEPTAR, true);
            return false;
        }
    }

    /**
     * Get Preferences
     */
    public static boolean getBooleanPreference(String key) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getBoolean(key, false);

        return false;
    }

    public static boolean getBooleanPreference(String key, boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getBoolean(key, false);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_OBTENER, Dialogo.ACEPTAR, true);

        return false;
    }

    public static boolean getBooleanPreference(String key, boolean defaultValue, boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getBoolean(key, defaultValue);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_OBTENER, Dialogo.ACEPTAR, true);

        return defaultValue;
    }

    public static boolean getBooleanPreference(String key, boolean defaultValue, String errorMessage,  boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getBoolean(key, defaultValue);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, errorMessage, Dialogo.ACEPTAR, true);

        return defaultValue;
    }

    public static String getStringPreference(String key) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getString(key, "");

        return "";
    }

    public static String getStringPreference(String key, boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getString(key, "");
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_OBTENER, Dialogo.ACEPTAR, true);

        return "";
    }

    public static String getStringPreference(String key, String defaultValue, boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getString(key, defaultValue);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_OBTENER, Dialogo.ACEPTAR, true);

        return defaultValue;
    }

    public static String getStringPreference(String key, String defaultValue, String errorMessage,  boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getString(key, defaultValue);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, errorMessage, Dialogo.ACEPTAR, true);

        return defaultValue;
    }

    public static int getIntegerPreference(String key) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getInt(key, -1);

        return -1;
    }

    public static int getIntegerPreference(String key, boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getInt(key, -1);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_OBTENER, Dialogo.ACEPTAR, true);

        return -1;
    }

    public static int getIntegerPreference(String key, int defaultValue, boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getInt(key, defaultValue);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_OBTENER, Dialogo.ACEPTAR, true);

        return defaultValue;
    }

    public static int getIntegerPreference(String key, int defaultValue, String errorMessage,  boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getInt(key, defaultValue);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, errorMessage, Dialogo.ACEPTAR, true);

        return defaultValue;
    }

    public static float getFloatPreference(String key) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getInt(key, -1);

        return -1;
    }

    public static float getFloatPreference(String key, boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getInt(key, -1);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_OBTENER, Dialogo.ACEPTAR, true);

        return -1;
    }

    public static float getFloatPreference(String key, float defaultValue, boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getFloat(key, defaultValue);
        else
        if (displayError)
            Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_OBTENER, Dialogo.ACEPTAR, true);

        return defaultValue;
    }

    public static float getFloatPreference(String key, float defaultValue, String errorMessage,  boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getFloat(key, defaultValue);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, errorMessage, Dialogo.ACEPTAR, true);

        return defaultValue;
    }

    public static long getLongPreference(String key) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getInt(key, -1);

        return -1;
    }

    public static long getLongPreference(String key, boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getInt(key, -1);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_OBTENER, Dialogo.ACEPTAR, true);

        return -1;
    }

    public static long getLongPreference(String key, long defaultValue, boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getLong(key, defaultValue);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, MSG_ERROR_OBTENER, Dialogo.ACEPTAR, true);

        return defaultValue;
    }

    public static long getLongPreference(String key, long defaultValue, String errorMessage,  boolean displayError) {
        if (existsPreference(key))
            return GrupoPinsaAplicacion.getSharedPreferences().getLong(key, defaultValue);
        else
            if (displayError)
                Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, errorMessage, Dialogo.ACEPTAR, true);

        return defaultValue;
    }

    /**
     * Remove Preferences
     */
    public static void removePreference(String key) {
        if (existsPreference(key)) {
            GrupoPinsaAplicacion.getSharedPreferencesEditor().remove(key);
            GrupoPinsaAplicacion.getSharedPreferencesEditor().commit();
        }
    }
}

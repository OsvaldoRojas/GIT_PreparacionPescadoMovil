package com.grupo.pinsa.libraries.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.grupo.pinsa.libraries.R;
import com.grupo.pinsa.libraries.dialogos.Dialogo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

public class Utilerias {
    private static Toast toast;
    private static Snackbar snackbar;

    /**
     * Constantes Privadas
     */
    private static final int ICONO = R.drawable.drw_grupo_pinsa_pescado_blanco_icono;

    /**
     * Constantes Publicas
     */
    public static final int DURACION_LARGA = Toast.LENGTH_LONG;
    public static final int DURACION_CORTA = Toast.LENGTH_SHORT;

    /**
     * Toolbar
     */
    public static Toolbar createToolbar(Activity activity, @IdRes int toolbar, @StringRes int mensaje) {
        return createToolbar(activity, toolbar, Conversion.resourceToString(mensaje), ICONO);
    }

    public static Toolbar createToolbar(Activity activity, @IdRes int toolbar, String mensaje) {
        return createToolbar(activity, toolbar, mensaje, ICONO);
    }

    public static Toolbar createToolbar(Activity activity, @IdRes int toolbar, @StringRes int mensaje, @DrawableRes int icono) {
        return createToolbar(activity, toolbar, Conversion.resourceToString(mensaje), icono);
    }

    public static Toolbar createToolbar(Activity activity, @IdRes int toolbar, String mensaje, @DrawableRes int icono) {
        Toolbar tb = activity.findViewById(toolbar);
        tb.setTitle(mensaje);
        tb.setNavigationIcon(icono);
        return tb;
    }

    /**
     * Toast Message
     */
    public static void showToast(Context context, @StringRes int mensaje) {
        showToast(context, Conversion.resourceToString(mensaje), DURACION_LARGA);
    }

    public static void showToast(Context context, String mensaje) {
        showToast(context, mensaje, DURACION_LARGA);
    }

    public static void showToast(Context context, @StringRes int mensaje, int duracion) {
        showToast(context, Conversion.resourceToString(mensaje), duracion);
    }

    public static void showToast(Context context, String mensaje, int duracion) {
        if (toast != null)
            toast.cancel();

        toast = Toast.makeText(context, mensaje, duracion);
        toast.show();
    }

    /**
     * Snackbar Message
     */
    public static void showSnackbar(Activity activity, View view, @StringRes int mensaje) {
        showSnackbar(activity, view, Conversion.resourceToString(mensaje), DURACION_LARGA);
    }

    public static void showSnackbar(Activity activity, View view, String mensaje) {
        showSnackbar(activity, view, mensaje, DURACION_LARGA);
    }

    public static void showSnackbar(Activity activity, View view, @StringRes int mensaje, int duracion) {
        showSnackbar(activity, view, Conversion.resourceToString(mensaje), duracion);
    }

    public static void showSnackbar(Activity activity, View view, String mensaje, int duracion) {
        if (snackbar != null)
            if (snackbar.isShown())
                snackbar.dismiss();

        hideKeyboard(activity);
        snackbar = Snackbar.make(view, mensaje, duracion);
        snackbar.show();
    }

    /**
     * Activity
     */
    public static void newActivity(Context context, Class clase) {
        newActivity(context, clase, null);
    }

    public static void newActivity(Context context, Class clase, Bundle bundle) {
        try {
            Intent intent = new Intent(context, clase);

            if (bundle != null)
                intent.putExtra(Constantes.C_SHARED_PARAMETERS_ACTIVITY, bundle);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            Dialogo.showDialogoError(context, e.getMessage(), Dialogo.ACEPTAR, true);
        }
    }

    /**
     * Fragment
     */
    public static void newFragment(Fragment fragment, int layoutContenedor, Context context) {
        newFragment(fragment, layoutContenedor, context, null);
    }

    public static void newFragment(Fragment fragment, int layoutContenedor, Context context, Bundle bundle) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;

        if (bundle != null)
            fragment.setArguments(bundle);

        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_animation, R.anim.exit_animation);
        fragmentTransaction.replace(layoutContenedor, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Keyboard
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null)
            view = new View(activity);

        try {
            Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void showKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();

        if (view == null)
            view = new View(activity);

        try {
            Objects.requireNonNull(inputMethodManager).showSoftInput(view, 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delay
     */
    public static void pause(Context context, int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ie) {
            Toast.makeText(context, ie.getMessage(), Toast.LENGTH_LONG);
        }
    }

    /**
     * Obtener una clase
     */
    public static Class getClass(String path) {
        try {
            return Class.forName(path);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Obtener Fecha Actual
     */
    public static Date getDate() {
        return Calendar.getInstance().getTime();
    }

    public static String getShortDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }

    public static String getLongDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}

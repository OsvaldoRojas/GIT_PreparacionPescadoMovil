package com.grupo.pinsa.libraries.dialogos;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.grupo.pinsa.libraries.R;
import com.grupo.pinsa.libraries.common.Conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

public class Dialogo {
    private static DialogoProgreso dialogoProgreso;
    private static DialogoInput dialogoInputSIP;
    private static DialogoInfo dialogoInfo;
    private static DialogoEstandar dialogoEstandar;
    private static DialogoCalendar dialogoCalendar;
    private static DialogoCustom dialogoCustom;

    /**
     * Constantes Privadas
     */
    private static final @DrawableRes int ICONO = R.drawable.drw_grupo_pinsa_pescado_blanco_icono;
    private static final @ColorRes int COLOR_ENCABEZADO = R.color.grupopinsa_colorBlue;
    private static final @ColorRes int COLOR_BOTON = R.color.grupopinsa_colorBlue;

    /**
     * Constantes publicas
     */
    public static final @StringRes int SI = R.string.grupopinsa_dialogo_respuesta_si;
    public static final @StringRes int NO = R.string.grupopinsa_dialogo_respuesta_no;
    public static final @StringRes int ACEPTAR = R.string.grupopinsa_dialogo_respuesta_aceptar;
    public static final @StringRes int CANCELAR = R.string.grupopinsa_dialogo_respuesta_cancelar;
    public static final @StringRes int ESPERAR = R.string.grupopinsa_dialogo_respuesta_esperar;
    public static final @StringRes int CONTINUAR = R.string.grupopinsa_dialogo_respuesta_continuar;
    public static final @StringRes int GUARDAR = R.string.grupopinsa_dialogo_respuesta_guardar;
    public static final @StringRes int BORRAR = R.string.grupopinsa_dialogo_respuesta_borrar;
    public static final @StringRes int SALIR = R.string.grupopinsa_dialogo_respuesta_salir;

    public static final @StringRes int CONECTANDO = R.string.grupopinsa_dialogo_mensaje_conectando;
    public static final @StringRes int BUSCANDO = R.string.grupopinsa_dialogo_mensaje_buscando;
    public static final @StringRes int CARGANDO = R.string.grupopinsa_dialogo_mensaje_cargando;
    public static final @StringRes int ESPERE = R.string.grupopinsa_dialogo_mensaje_espere;
    public static final @StringRes int PROCESANDO = R.string.grupopinsa_dialogo_mensaje_procesando;

    public static final int NO_DATES_DISABLED = 0;
    public static final int NEXTDATES_DISABLED = 1;
    public static final int BACKDATES_DISABLED = 2;

    /**
     * Dialogo Progreso
     */
    public static DialogoProgreso createDialogoProgreso(Context context, String message) {
        return new DialogoProgreso(context)
                .setMessage(message)
                .setCancelable(false);
    }

    public static void showDialogoProgreso(Context context, int message) {
        showDialogoProgreso(context, Conversion.resourceToString(message));
    }

    public static void showDialogoProgreso(final Context context, final String message) {
        hideDialogoProgreso();
        dialogoProgreso = createDialogoProgreso(context, message);
        dialogoProgreso.show();
    }

    public static void hideDialogoProgreso() {
        if (dialogoProgreso != null)
            if (dialogoProgreso.isShowing())
                dialogoProgreso.dismiss();
    }

    /**
     * Dialogo Input
     */
    public static void showDialogoInput(Context context, @StringRes final int message, int inputType, @StringRes int positiveMessage, @StringRes int negativeMessage, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, ICONO, COLOR_ENCABEZADO, Conversion.resourceToString(message), "", inputType, Conversion.resourceToString(positiveMessage), COLOR_BOTON, Conversion.resourceToString(negativeMessage), COLOR_BOTON, true, dialogoAccion);
    }

    public static void showDialogoInput(Context context, @StringRes final int message, int inputType, @StringRes int positiveMessage, @StringRes int negativeMessage, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, ICONO, COLOR_ENCABEZADO, Conversion.resourceToString(message), "", inputType, Conversion.resourceToString(positiveMessage), COLOR_BOTON, Conversion.resourceToString(negativeMessage), COLOR_BOTON, cancelable, dialogoAccion);
    }

    public static void showDialogoInput(Context context, final String message, int inputType, @StringRes int positiveMessage, @StringRes int negativeMessage, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, ICONO, COLOR_ENCABEZADO, message, "", inputType, Conversion.resourceToString(positiveMessage), COLOR_BOTON, Conversion.resourceToString(negativeMessage), COLOR_BOTON, cancelable, dialogoAccion);
    }

    public static void showDialogoInput(Context context, @StringRes int message, String startValue, int inputType, @StringRes int positiveMessage, @StringRes int negativeMessage, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, ICONO, COLOR_ENCABEZADO, Conversion.resourceToString(message), startValue, inputType, Conversion.resourceToString(positiveMessage), COLOR_BOTON, Conversion.resourceToString(negativeMessage), COLOR_BOTON, cancelable, dialogoAccion);
    }

    public static void showDialogoInput(Context context, final String message, int inputType, String positiveMessage, String negativeMessage, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, ICONO, COLOR_ENCABEZADO, message, "", inputType, positiveMessage, COLOR_BOTON, negativeMessage, COLOR_BOTON, true, dialogoAccion);
    }

    public static void showDialogoInput(Context context, final String message, int inputType, String positiveMessage, String negativeMessage, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, ICONO, COLOR_ENCABEZADO, message, "", inputType, positiveMessage, COLOR_BOTON, negativeMessage, COLOR_BOTON, cancelable, dialogoAccion);
    }

    public static void showDialogoInput(Context context, @StringRes final int message, int inputType, @StringRes int positiveMessage, @StringRes int negativeMessage, @ColorRes int buttonsColors, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, ICONO, COLOR_ENCABEZADO, Conversion.resourceToString(message), "", inputType, Conversion.resourceToString(positiveMessage), buttonsColors, Conversion.resourceToString(negativeMessage), buttonsColors, true, dialogoAccion);
    }

    public static void showDialogoInput(Context context, @StringRes final int message, int inputType, @StringRes int positiveMessage, @StringRes int negativeMessage, @ColorRes int buttonsColors, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, ICONO, COLOR_ENCABEZADO, Conversion.resourceToString(message), "", inputType, Conversion.resourceToString(positiveMessage), buttonsColors, Conversion.resourceToString(negativeMessage), buttonsColors, cancelable, dialogoAccion);
    }

    public static void showDialogoInput(Context context, final String message, int inputType, String positiveMessage, String negativeMessage, @ColorRes int buttonsColors, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, ICONO, COLOR_ENCABEZADO, message, "", inputType, positiveMessage, buttonsColors, negativeMessage, buttonsColors, true, dialogoAccion);
    }

    public static void showDialogoInput(Context context, final String message, int inputType, String positiveMessage, String negativeMessage, @ColorRes int buttonsColors, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, ICONO, COLOR_ENCABEZADO, message, "", inputType, positiveMessage, buttonsColors, negativeMessage, buttonsColors, cancelable, dialogoAccion);
    }
    
    public static void showDialogoInput(Context context, @DrawableRes int icon, @StringRes final int message, int inputType, @StringRes int positiveMessage, @StringRes int negativeMessage, @ColorRes int buttonsColors, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, icon, COLOR_ENCABEZADO, Conversion.resourceToString(message), "", inputType, Conversion.resourceToString(positiveMessage), buttonsColors, Conversion.resourceToString(negativeMessage), buttonsColors, cancelable, dialogoAccion);
    }
    
    public static void showDialogoInput(Context context, @DrawableRes int icon, final String message, int inputType, String positiveMessage, String negativeMessage, @ColorRes int buttonsColors, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, icon, COLOR_ENCABEZADO, message, "", inputType, positiveMessage, buttonsColors, negativeMessage, buttonsColors, cancelable, dialogoAccion);
    }
        
    public static void showDialogoInput(Context context, @DrawableRes int icon, @ColorRes int headerColor, @StringRes final int message, int inputType, @StringRes int positiveMessage, @StringRes int negativeMessage, @ColorRes int buttonsColors, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, icon, headerColor, Conversion.resourceToString(message), "", inputType, Conversion.resourceToString(positiveMessage), buttonsColors, Conversion.resourceToString(negativeMessage), buttonsColors, cancelable, dialogoAccion);
    }
    
    public static void showDialogoInput(Context context, @DrawableRes int icon, @ColorRes int headerColor, final String message, int inputType, String positiveMessage, String negativeMessage, @ColorRes int buttonsColors, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, icon, headerColor, message, "", inputType, positiveMessage, buttonsColors, negativeMessage, buttonsColors, cancelable, dialogoAccion);
    }

    public static void showDialogoInput(Context context, @DrawableRes int icon, @ColorRes int headerColor, final String message, String startValue, int inputType, String positiveMessage, String negativeMessage, @ColorRes int buttonsColors, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, icon, headerColor, message, startValue, inputType, positiveMessage, buttonsColors, negativeMessage, buttonsColors, cancelable, dialogoAccion);
    }

    public static void showDialogoInput(Context context, @DrawableRes int icon, @ColorRes int headerColor, @StringRes final int inputText, int inputType, @StringRes int positiveMessage, @ColorRes int positiveButtonColor, @StringRes int negativeMessage, @ColorRes int negativeButtonColor, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, icon, headerColor, Conversion.resourceToString(inputText), "", inputType, Conversion.resourceToString(positiveMessage), positiveButtonColor, Conversion.resourceToString(negativeMessage), negativeButtonColor, cancelable, dialogoAccion);
    }

    public static void showDialogoInput(Context context, @DrawableRes int icon, @ColorRes int headerColor, @StringRes final int message, @StringRes int startValue, int inputType, @StringRes int positiveMessage, @ColorRes int positiveButtonColor, @StringRes int negativeMessage, @ColorRes int negativeButtonColor, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoInput(context, icon, headerColor, Conversion.resourceToString(message), Conversion.resourceToString(startValue), inputType, Conversion.resourceToString(positiveMessage), positiveButtonColor, Conversion.resourceToString(negativeMessage), negativeButtonColor, cancelable, dialogoAccion);
    }
    
    public static void showDialogoInput(Context context, @DrawableRes int icon, @ColorRes int headerColor, final String message, String startValue, int inputType, String positiveMessage, @ColorRes int positiveButtonColor, String negativeMessage, @ColorRes int negativeButtonColor, boolean cancelable, final DialogoAccion dialogoAccion) {
        hideDialogoInput();
        dialogoInputSIP = createDialogoInput(context, icon, headerColor, message, startValue, inputType, positiveMessage, positiveButtonColor, negativeMessage, negativeButtonColor, cancelable, dialogoAccion);
        dialogoInputSIP.show();
    }

    public static DialogoInput createDialogoInput(Context context, @DrawableRes int icon, @ColorRes int headerColor, final String message, String startValue, int inputType, String positiveMessage, @ColorRes int positiveButtonColor, String negativeMessage, @ColorRes int negativeButtonColor, boolean cancelable, final DialogoAccion dialogoAccion) {
        final DialogoInput dialogoInputSIP = new DialogoInput(context)
                .setIcon(icon)
                .setTopColorRes(headerColor)
                .setPositiveButtonColorRes(positiveButtonColor)
                .setNegativeButtonColorRes(negativeButtonColor)
                .setInputValue(startValue)
                .setHint(message)
                .setInputMethod(inputType)
                .setCancelable(cancelable);

        dialogoInputSIP.setPositiveButton(positiveMessage, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoAccion.onPositive(dialogoInputSIP.getInputValue());
            }
        }).setNegativeButton(negativeMessage, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoAccion.onNegative();
            }
        });

        return dialogoInputSIP;
    }
    
    public static void hideDialogoInput() {
        if (dialogoInputSIP != null)
            if (dialogoInputSIP.isShowing())
                dialogoInputSIP.dismiss();
    }

    /**
     * Dialogo Info
     */
    public static void showDialogoNotify(Context context, @StringRes int message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoNotify(context, ICONO, Conversion.resourceToString(message), Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoNotify(Context context, @StringRes int message, String confirmMessage, boolean cancelable) {
        showDialogoNotify(context, ICONO, Conversion.resourceToString(message), confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoNotify(Context context, String message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoNotify(context, ICONO, message, Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }
    
    public static void showDialogoNotify(Context context, String message, String confirmMessage, boolean cancelable) {
        showDialogoNotify(context, ICONO, message, confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoNotify(Context context, @DrawableRes int icon, String message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoNotify(context, icon, message, Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoNotify(Context context, @DrawableRes int icon, @StringRes int message, String confirmMessage, boolean cancelable) {
        showDialogoNotify(context, icon, Conversion.resourceToString(message), confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoNotify(Context context, @DrawableRes int icon, @StringRes int message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoNotify(context, icon, Conversion.resourceToString(message), Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }
    
    public static void showDialogoNotify(Context context, @DrawableRes int icon, String message, String confirmMessage, boolean cancelable) {
        showDialogoNotify(context, icon, message, confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoNotify(Context context, @DrawableRes int icon, String message, @StringRes int confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        showDialogoNotify(context, icon, message, Conversion.resourceToString(confirmMessage), buttonColor, cancelable);
    }

    public static void showDialogoNotify(Context context, @DrawableRes int icon, @StringRes int message, String confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        showDialogoNotify(context, icon, Conversion.resourceToString(message), confirmMessage, buttonColor, cancelable);
    }
    
    public static void showDialogoNotify(Context context, @DrawableRes int icon, @StringRes int message, @StringRes int confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        showDialogoNotify(context, icon, Conversion.resourceToString(message), Conversion.resourceToString(confirmMessage), buttonColor, cancelable);
    }
    
    public static void showDialogoNotify(Context context, @DrawableRes int icon, String message, String confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        hideDialogoInformativo();
        dialogoInfo = createDialogoInformativo(context, icon, R.color.grupopinsa_colorNotify, message, confirmMessage, buttonColor, cancelable);
        dialogoInfo.show();
    }

    /**
     * Dialogo Success
     */
    public static void showDialogoSuccess(Context context, String message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoSuccess(context, ICONO, message, Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoSuccess(Context context, @StringRes int message, String confirmMessage, boolean cancelable) {
        showDialogoSuccess(context, ICONO, Conversion.resourceToString(message), confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoSuccess(Context context, @StringRes int message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoSuccess(context, ICONO, Conversion.resourceToString(message), Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoSuccess(Context context, String message, String confirmMessage, boolean cancelable) {
        showDialogoSuccess(context, ICONO, message, confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoSuccess(Context context, @DrawableRes int icon, String message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoSuccess(context, icon, message, Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoSuccess(Context context, @DrawableRes int icon, @StringRes int message, String confirmMessage, boolean cancelable) {
        showDialogoSuccess(context, icon, Conversion.resourceToString(message), confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoSuccess(Context context, @DrawableRes int icon, @StringRes int message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoSuccess(context, icon, Conversion.resourceToString(message), Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoSuccess(Context context, @DrawableRes int icon, String message, String confirmMessage, boolean cancelable) {
        showDialogoSuccess(context, icon, message, confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoSuccess(Context context, @DrawableRes int icon, String message, @StringRes int confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        showDialogoSuccess(context, icon, message, Conversion.resourceToString(confirmMessage), buttonColor, cancelable);
    }

    public static void showDialogoSuccess(Context context, @DrawableRes int icon, @StringRes int message, String confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        showDialogoSuccess(context, icon, Conversion.resourceToString(message), confirmMessage, buttonColor, cancelable);
    }

    public static void showDialogoSuccess(Context context, @DrawableRes int icon, @StringRes int message, @StringRes int confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        showDialogoSuccess(context, icon, Conversion.resourceToString(message), Conversion.resourceToString(confirmMessage), buttonColor, cancelable);
    }

    public static void showDialogoSuccess(Context context, @DrawableRes int icon, String message, String confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        hideDialogoInformativo();
        dialogoInfo = createDialogoInformativo(context, icon, R.color.grupopinsa_colorSuccess, message, confirmMessage, buttonColor, cancelable);
        dialogoInfo.show();
    }

    /**
     * Dialogo Warning
     */
    public static void showDialogoWarning(Context context, String message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoWarning(context, ICONO, message, Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoWarning(Context context, @StringRes int message, String confirmMessage, boolean cancelable) {
        showDialogoWarning(context, ICONO, Conversion.resourceToString(message), confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoWarning(Context context, @StringRes int message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoWarning(context, ICONO, Conversion.resourceToString(message), Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoWarning(Context context, String message, String confirmMessage, boolean cancelable) {
        showDialogoWarning(context, ICONO, message, confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoWarning(Context context, @DrawableRes int icon, String message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoWarning(context, icon, message, Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoWarning(Context context, @DrawableRes int icon, @StringRes int message, String confirmMessage, boolean cancelable) {
        showDialogoWarning(context, icon, Conversion.resourceToString(message), confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoWarning(Context context, @DrawableRes int icon, @StringRes int message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoWarning(context, icon, Conversion.resourceToString(message), Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoWarning(Context context, @DrawableRes int icon, String message, String confirmMessage, boolean cancelable) {
        showDialogoWarning(context, icon, message, confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoWarning(Context context, @DrawableRes int icon, String message, @StringRes int confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        showDialogoWarning(context, icon, message, Conversion.resourceToString(confirmMessage), buttonColor, cancelable);
    }

    public static void showDialogoWarning(Context context, @DrawableRes int icon, @StringRes int message, String confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        showDialogoWarning(context, icon, Conversion.resourceToString(message), confirmMessage, buttonColor, cancelable);
    }

    public static void showDialogoWarning(Context context, @DrawableRes int icon, @StringRes int message, @StringRes int confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        showDialogoWarning(context, icon, Conversion.resourceToString(message), Conversion.resourceToString(confirmMessage), buttonColor, cancelable);
    }

    public static void showDialogoWarning(Context context, @DrawableRes int icon, String message, String confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        hideDialogoInformativo();
        dialogoInfo = createDialogoInformativo(context, icon, R.color.grupopinsa_colorWarning, message, confirmMessage, buttonColor, cancelable);
        dialogoInfo.show();
    }

    /**
     * Dialogo Error
     */
    public static void showDialogoError(Context context, @StringRes int message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoError(context, ICONO, Conversion.resourceToString(message), Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoError(Context context, @StringRes int message, String confirmMessage, boolean cancelable) {
        showDialogoError(context, ICONO, Conversion.resourceToString(message), confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoError(Context context, String message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoError(context, ICONO, message, Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoError(Context context, String message, String confirmMessage, boolean cancelable) {
        showDialogoError(context, ICONO, message, confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoError(Context context, @DrawableRes int icon, String message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoError(context, icon, message, Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoError(Context context, @DrawableRes int icon, @StringRes int message, String confirmMessage, boolean cancelable) {
        showDialogoError(context, icon, Conversion.resourceToString(message), confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoError(Context context, @DrawableRes int icon, @StringRes int message, @StringRes int confirmMessage, boolean cancelable) {
        showDialogoError(context, icon, Conversion.resourceToString(message), Conversion.resourceToString(confirmMessage), COLOR_BOTON, cancelable);
    }

    public static void showDialogoError(Context context, @DrawableRes int icon, String message, String confirmMessage, boolean cancelable) {
        showDialogoError(context, icon, message, confirmMessage, COLOR_BOTON, cancelable);
    }

    public static void showDialogoError(Context context, @DrawableRes int icon, String message, @StringRes int confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        showDialogoError(context, icon, message, Conversion.resourceToString(confirmMessage), buttonColor, cancelable);
    }

    public static void showDialogoError(Context context, @DrawableRes int icon, @StringRes int message, String confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        showDialogoError(context, icon, Conversion.resourceToString(message), confirmMessage, buttonColor, cancelable);
    }

    public static void showDialogoError(Context context, @DrawableRes int icon, @StringRes int message, @StringRes int confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        showDialogoError(context, icon, Conversion.resourceToString(message), Conversion.resourceToString(confirmMessage), buttonColor, cancelable);
    }

    public static void showDialogoError(Context context, @DrawableRes int icon, String message, String confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        hideDialogoInformativo();
        dialogoInfo = createDialogoInformativo(context, icon, R.color.grupopinsa_colorError, message, confirmMessage, buttonColor, cancelable);
        dialogoInfo.show();
    }

    /**
     * Dialogo Informativo
     */
    public static DialogoInfo createDialogoInformativo(Context context, @DrawableRes int icon, @ColorRes int headerColor, String message, String confirmMessage, @ColorRes int buttonColor, boolean cancelable) {
        return new DialogoInfo(context)
                .setIcon(icon)
                .setTopColorRes(headerColor)
                .setConfirmButtonText(confirmMessage)
                .setConfirmButtonColor(buttonColor)
                .setMessage(message)
                .setCancelable(cancelable);
    }

    public static void hideDialogoInformativo() {
        if (dialogoInfo != null)
            if (dialogoInfo.isShowing())
                dialogoInfo.dismiss();
    }

    /**
     * Dialogo Estandar
     */
    public static void showDialogoEstandar(Context context, @StringRes int message,  @StringRes int positiveMessage,  @StringRes int negativeMessage, final DialogoAccion dialogoAccion) {
        showDialogoEstandar(context, ICONO, COLOR_ENCABEZADO, Conversion.resourceToString(message), Conversion.resourceToString(positiveMessage), COLOR_BOTON, Conversion.resourceToString(negativeMessage), COLOR_BOTON, true, dialogoAccion);
    }

    public static void showDialogoEstandar(Context context, String message,  @StringRes int positiveMessage,  @StringRes int negativeMessage, final DialogoAccion dialogoAccion) {
        showDialogoEstandar(context, ICONO, COLOR_ENCABEZADO, message, Conversion.resourceToString(positiveMessage), COLOR_BOTON, Conversion.resourceToString(negativeMessage), COLOR_BOTON, true, dialogoAccion);
    }

    public static void showDialogoEstandar(Context context, String message, String positiveMessage, String negativeMessage, final DialogoAccion dialogoAccion) {
        showDialogoEstandar(context, ICONO, COLOR_ENCABEZADO, message, positiveMessage, COLOR_BOTON, negativeMessage, COLOR_BOTON, true, dialogoAccion);
    }

    public static void showDialogoEstandar(Context context, @StringRes int message,  @StringRes int positiveMessage,  @StringRes int negativeMessage, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoEstandar(context, ICONO, COLOR_ENCABEZADO, Conversion.resourceToString(message), Conversion.resourceToString(positiveMessage), COLOR_BOTON, Conversion.resourceToString(negativeMessage), COLOR_BOTON, cancelable, dialogoAccion);
    }

    public static void showDialogoEstandar(Context context, String message, String positiveMessage, String negativeMessage, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoEstandar(context, ICONO, COLOR_ENCABEZADO, message, positiveMessage, COLOR_BOTON, negativeMessage, COLOR_BOTON, cancelable, dialogoAccion);
    }

    public static void showDialogoEstandar(Context context, @DrawableRes int icon, @StringRes int message,  @StringRes int positiveMessage,  @StringRes int negativeMessage, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoEstandar(context, icon, COLOR_ENCABEZADO, Conversion.resourceToString(message), Conversion.resourceToString(positiveMessage), COLOR_BOTON, Conversion.resourceToString(negativeMessage), COLOR_BOTON, cancelable, dialogoAccion);
    }

    public static void showDialogoEstandar(Context context, @DrawableRes int icon, String message, String positiveMessage, String negativeMessage, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoEstandar(context, icon, COLOR_ENCABEZADO, message, positiveMessage, COLOR_BOTON, negativeMessage, COLOR_BOTON, cancelable, dialogoAccion);
    }

    public static void showDialogoEstandar(Context context, @DrawableRes int icon, @StringRes int message,  @StringRes int positiveMessage,  @StringRes int negativeMessage, @ColorRes int buttonsColors, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoEstandar(context, icon, COLOR_ENCABEZADO, Conversion.resourceToString(message), Conversion.resourceToString(positiveMessage), buttonsColors, Conversion.resourceToString(negativeMessage), buttonsColors, cancelable, dialogoAccion);
    }

    public static void showDialogoEstandar(Context context, @DrawableRes int icon, String message, String positiveMessage, String negativeMessage, @ColorRes int buttonsColors, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoEstandar(context, icon, COLOR_ENCABEZADO, message, positiveMessage, buttonsColors, negativeMessage, buttonsColors, cancelable, dialogoAccion);
    }

    public static void showDialogoEstandar(Context context, @DrawableRes int icon, @ColorRes int headerColor, @StringRes int message,  @StringRes int positiveMessage,  @StringRes int negativeMessage, @ColorRes int buttonsColors, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoEstandar(context, icon, headerColor, Conversion.resourceToString(message), Conversion.resourceToString(positiveMessage), buttonsColors, Conversion.resourceToString(negativeMessage), buttonsColors, cancelable, dialogoAccion);
    }

    public static void showDialogoEstandar(Context context, @DrawableRes int icon, @ColorRes int headerColor, String message, String positiveMessage, String negativeMessage, @ColorRes int buttonsColors, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoEstandar(context, icon, headerColor, message, positiveMessage, buttonsColors, negativeMessage, buttonsColors, cancelable, dialogoAccion);
    }

    public static void showDialogoEstandar(Context context, @DrawableRes int icon, @ColorRes int headerColor, @StringRes int message,  @StringRes int positiveMessage, @ColorRes int positiveButtonColor,  @StringRes int negativeMessage, @ColorRes int negativeButtonColor, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoEstandar(context, icon, headerColor, Conversion.resourceToString(message), Conversion.resourceToString(positiveMessage), positiveButtonColor, Conversion.resourceToString(negativeMessage), negativeButtonColor, cancelable, dialogoAccion);
    }

    public static void showDialogoEstandar(Context context, @DrawableRes int icon, @ColorRes int headerColor, String message, String positiveMessage, @ColorRes int positiveButtonColor, String negativeMessage, @ColorRes int negativeButtonColor, boolean cancelable, final DialogoAccion dialogoAccion) {
        hideDialogoEstandar();
        dialogoEstandar = createDialogoEstandar(context, icon, headerColor, message, positiveMessage, positiveButtonColor, negativeMessage, negativeButtonColor, cancelable, dialogoAccion);
        dialogoEstandar.show();
    }

    public static DialogoEstandar createDialogoEstandar(Context context, @DrawableRes int icon, @ColorRes int headerColor, String message, String positiveMessage, @ColorRes int positiveButtonColor, String negativeMessage, @ColorRes int negativeButtonColor, boolean cancelable, final DialogoAccion dialogoAccion) {
        return new DialogoEstandar(context)
                .setIcon(icon)
                .setTopColorRes(headerColor)
                .setMessage(message)
                .setPositiveButton(positiveMessage, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogoAccion.onPositive();
                    }
                })
                .setPositiveButtonColorRes(positiveButtonColor)
                .setNegativeButton(negativeMessage, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogoAccion.onNegative();
                    }
                }).setNegativeButtonColorRes(negativeButtonColor)
                .setCancelable(cancelable);
    }

    public static void hideDialogoEstandar() {
        if (dialogoEstandar != null)
            if (dialogoEstandar.isShowing())
                dialogoEstandar.dismiss();
    }

    /**
     * Dialogo Date
     */
    public static void showDialogoDate(Context context, DialogoAccion dialogoAccion) {
        showDialogoDate(context, Calendar.getInstance(), true, dialogoAccion);
    }

    public static void showDialogoDate(Context context, boolean cancelable, DialogoAccion dialogoAccion) {
        showDialogoDate(context, Calendar.getInstance(), cancelable, dialogoAccion);
    }

    public static void showDialogoDate(Context context, String fecha, DialogoAccion dialogoAccion) {
        showDialogoDate(context, fecha, true, NO_DATES_DISABLED, dialogoAccion);
    }

    public static void showDialogoDate(Context context, String fecha, boolean cancelable, DialogoAccion dialogoAccion) {
        showDialogoDate(context, fecha, cancelable, NO_DATES_DISABLED, dialogoAccion);
    }

    public static void showDialogoDate(Context context, String fecha, String formatoRespuesta, boolean cancelable, DialogoAccion dialogoAccion) {
        showDialogoDate(context, fecha, formatoRespuesta, cancelable, NO_DATES_DISABLED, dialogoAccion);
    }

    public static void showDialogoDate(Context context, String fecha, String formatoRespuesta, boolean cancelable, int datesDisabled, DialogoAccion dialogoAccion) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatoRespuesta);
            Date date = sdf.parse(fecha);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            showDialogoDate(context, calendar, formatoRespuesta, cancelable, datesDisabled, dialogoAccion);
        } catch (ParseException pe) {
            showDialogoError(context, R.string.grupopinsa_dialogo_error_parse, R.string.grupopinsa_dialogo_respuesta_aceptar, cancelable);
        }
    }

    public static void showDialogoDate(Context context, String fecha, boolean cancelable, int datesDisabled, DialogoAccion dialogoAccion) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse(fecha);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            showDialogoDate(context, calendar, cancelable, datesDisabled, dialogoAccion);
        } catch (ParseException pe) {
            showDialogoError(context, R.string.grupopinsa_dialogo_error_parse, R.string.grupopinsa_dialogo_respuesta_aceptar, cancelable);
        }
    }

    public static void showDialogoDate(Context context, int anno, int mes, int diaMes, DialogoAccion dialogoAccion) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(anno, mes, diaMes);

        showDialogoDate(context, calendar, true, dialogoAccion);
    }

    public static void showDialogoDate(Context context, int anno, int mes, int diaMes, boolean cancelable, DialogoAccion dialogoAccion) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(anno, mes, diaMes);

        showDialogoDate(context, calendar, cancelable, dialogoAccion);
    }

    public static void showDialogoDate(Context context, Calendar calendar, DialogoAccion dialogoAccion) {
        showDialogoDate(context, calendar, true, dialogoAccion);
    }

    public static void showDialogoDate(Context context, Calendar calendar, String formatoRespuesta, boolean cancelable, DialogoAccion dialogoAccion) {
        hideDialogoDate();
        dialogoCalendar = createDialogoDate(context, calendar, formatoRespuesta, cancelable, NO_DATES_DISABLED, dialogoAccion);
        dialogoCalendar.show();
    }

    public static void showDialogoDate(Context context, Calendar calendar, boolean cancelable, DialogoAccion dialogoAccion) {
        hideDialogoDate();
        dialogoCalendar = createDialogoDate(context, calendar, "dd-MM-yyyy", cancelable, NO_DATES_DISABLED, dialogoAccion);
        dialogoCalendar.show();
    }

    public static void showDialogoDate(Context context, Calendar calendar, String formatoRespuesta, boolean cancelable, int dateDisabled, DialogoAccion dialogoAccion) {
        hideDialogoDate();
        dialogoCalendar = createDialogoDate(context, calendar,formatoRespuesta, cancelable, dateDisabled, dialogoAccion);
        dialogoCalendar.show();
    }

    public static void showDialogoDate(Context context, Calendar calendar, boolean cancelable, int dateDisabled, DialogoAccion dialogoAccion) {
        hideDialogoDate();
        dialogoCalendar = createDialogoDate(context, calendar,"dd-MM-yyyy", cancelable, dateDisabled, dialogoAccion);
        dialogoCalendar.show();
    }

    public static DialogoCalendar createDialogoDate(Context context, Calendar calendar, final String formatoRespuesta, boolean cancelable, int dateDisabled, final DialogoAccion dialogoAccion) {
        final DialogoCalendar dialogoCalendar = new DialogoCalendar(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat sdf = new SimpleDateFormat(formatoRespuesta);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        String fecha = sdf.format(calendar.getTime());
                        dialogoAccion.onDate(fecha);
                    }
                })
                .setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialogoCalendar.setCancelable(cancelable);

        switch (dateDisabled) {
            case NO_DATES_DISABLED:
                break;
            case NEXTDATES_DISABLED:
                dialogoCalendar.setMaxDate(System.currentTimeMillis() + 1000);
                break;
            case BACKDATES_DISABLED:
                dialogoCalendar.setMinDate(System.currentTimeMillis() - 1000);
                break;
        }

        return dialogoCalendar;
    }

    public static void hideDialogoDate() {
        if (dialogoCalendar != null)
            if (dialogoCalendar.isShowing())
                dialogoCalendar.dismiss();
    }

    /**
     * Dialogo Custom
     */
    public static void showDialogoCustom(Context context, View view, Integer viewPositive, Integer viewNegative, final DialogoAccion dialogoAccion) {
        showDialogoCustom(context, ICONO, COLOR_ENCABEZADO,view, viewPositive, viewNegative, true, dialogoAccion);
    }

    public static void showDialogoCustom(Context context, View view, Integer viewPositive, Integer viewNegative, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoCustom(context, ICONO, COLOR_ENCABEZADO, view, viewPositive, viewNegative, cancelable, dialogoAccion);
    }

    public static void showDialogoCustom(Context context, @DrawableRes int icon, View view, Integer viewPositive, Integer viewNegative, boolean cancelable, final DialogoAccion dialogoAccion) {
        showDialogoCustom(context, icon, COLOR_ENCABEZADO, view, viewPositive, viewNegative, cancelable, dialogoAccion);
    }

    public static void showDialogoCustom(Context context, @DrawableRes int icon, @ColorRes int headerColor, View view, Integer viewPositive, Integer viewNegative, boolean cancelable, final DialogoAccion dialogoAccion) {
        hideDialogoCustom();
        dialogoCustom = createDialogoCustom(context, icon, headerColor, view, viewPositive, viewNegative, cancelable, dialogoAccion);
        dialogoCustom.show();
    }

    public static DialogoCustom createDialogoCustom(Context context, @DrawableRes int icon, @ColorRes int headerColor, View view, Integer viewPositive, Integer viewNegative, boolean cancelable, final DialogoAccion dialogoAccion) {
        DialogoCustom custom = new DialogoCustom(context)
                .setIcon(icon)
                .setTopColorRes(headerColor)
                .setCancelable(cancelable)
                .setView(view);

        if (dialogoAccion != null) {
            if (viewPositive != null)
                custom.setListener(viewPositive, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogoAccion.onPositive();
                    }
                });

            if (viewNegative != null)
                custom.setListener(viewNegative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogoAccion.onNegative();
                    }
                });
        }

        return custom;
    }

    public static void hideDialogoCustom() {
        if (dialogoCustom != null)
            if (dialogoCustom.isShowing())
                dialogoCustom.dismiss();
    }

    /**
     * Utilerias
     */
    public static boolean isShowing() {
        return dialogoProgreso.isShowing() || dialogoInputSIP.isShowing() || dialogoInfo.isShowing() || dialogoEstandar.isShowing() || dialogoCalendar.isShowing() || dialogoCustom.isShowing();
    }
}

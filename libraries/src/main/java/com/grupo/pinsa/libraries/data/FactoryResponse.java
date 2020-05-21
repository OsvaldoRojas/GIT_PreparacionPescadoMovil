package com.grupo.pinsa.libraries.data;

import android.app.Activity;

import com.grupo.pinsa.libraries.dialogos.Dialogo;

/**
 * Created by Juan J. Palomera on 25/09/2018
 */

public abstract class FactoryResponse implements FactoryResponseInterface {
    private final Activity activity;

    public FactoryResponse(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onSuccess(String json) {
        Dialogo.hideDialogoProgreso();
    }

    @Override
    public void onNotify(String message) {
        Dialogo.showDialogoNotify(activity, message, Dialogo.ACEPTAR, true);
        Dialogo.hideDialogoProgreso();
    }

    @Override
    public void onWarning(String message) {
        Dialogo.showDialogoWarning(activity, message, Dialogo.ACEPTAR, true);
        Dialogo.hideDialogoProgreso();
    }

    @Override
    public void onFailure(String message) {
        Dialogo.showDialogoError(activity, message, Dialogo.ACEPTAR, true);
        Dialogo.hideDialogoProgreso();
    }
}

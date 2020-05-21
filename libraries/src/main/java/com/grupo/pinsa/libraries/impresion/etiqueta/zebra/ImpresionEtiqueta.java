package com.grupo.pinsa.libraries.impresion.etiqueta.zebra;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;

import java.util.HashMap;

/**
 * Created by Juan J. Palomera on 23/03/2019
 */

public class ImpresionEtiqueta {
    HashMap<String, String> datos;
    Activity activity;

    public ImpresionEtiqueta(Activity activity) {
        this.activity = activity;
        this.datos = new HashMap<>();
    }

    public ImpresionEtiqueta(HashMap<String, String> datos) {
        this.datos = datos;
    }

    public ImpresionEtiqueta addData(String campo, String valor) {
        datos.put("%" + campo + "%", valor);
        return this;
    }

    public ImpresionEtiqueta setData(HashMap<String, String> datos) {
        this.datos = datos;
        return this;
    }

    public ImpresionEtiqueta print(String archivo, final ImpresionEtiquetaCallback impresionEtiquetaCallback) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.zebra.printconnect", "com.zebra.printconnect.print.TemplatePrintService"));
        intent.putExtra("com.zebra.printconnect.PrintService.TEMPLATE_FILE_NAME", archivo + ".prn");
        intent.putExtra("com.zebra.printconnect.PrintService.VARIABLE_DATA", datos);
        intent.putExtra("com.zebra.printconnect.PrintService.RESULT_RECEIVER", buildPCSageReceiver(new ResultReceiver(null) {
            @Override
            protected void onReceiveResult(final int resultCode, final Bundle resultData) {
                if (resultCode == 0)
                    impresionEtiquetaCallback.onSuccess();
                else
                    impresionEtiquetaCallback.onFailure(resultData.getString("com.zebra.printconnect.PrintService.ERROR_MESSAGE"));
            }
        }));

        activity.startService(intent);

        return this;
    }

    private ResultReceiver buildPCSageReceiver(ResultReceiver actualReceiver) {
        Parcel parcel = Parcel.obtain();
        actualReceiver.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ResultReceiver receiverForSending = ResultReceiver.CREATOR.createFromParcel(parcel);
        parcel.recycle();

        return receiverForSending;
    }
}

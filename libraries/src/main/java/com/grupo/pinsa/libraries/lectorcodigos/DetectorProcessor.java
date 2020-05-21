package com.grupo.pinsa.libraries.lectorcodigos;

import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by Juan J. Palomera on 10/03/2019
 */

public class DetectorProcessor implements Detector.Processor<Barcode> {
    private final LectorCodigosFragment fragment;
    private final int tipo_lectura;
    private final LectorCodigosCallback lectorCodigosCallback;
    private String token = "";
    private String tokenAnterior = "";

    public DetectorProcessor(LectorCodigosFragment fragment, int tipo_lectura, LectorCodigosCallback lectorCodigosCallback) {
        this.fragment = fragment;
        this.tipo_lectura = tipo_lectura;
        this.lectorCodigosCallback = lectorCodigosCallback;
    }

    @Override
    public void release() {
        lectorCodigosCallback.Leer(fragment, token);

        if (tipo_lectura == LectorCodigos.LECTURA_UNICA)
            fragment.close();
    }

    @Override
    public void receiveDetections(Detector.Detections<Barcode> detections) {
        final SparseArray<Barcode> barcodeSparseArray = detections.getDetectedItems();

        if (barcodeSparseArray.size() > 0) {
            token = barcodeSparseArray.valueAt(0).displayValue;

            if (!token.equals(tokenAnterior)) {
                tokenAnterior = token;

                release();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            synchronized (this) {
                                wait(5000);
                                tokenAnterior = "";
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }
}

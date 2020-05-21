package com.grupo.pinsa.libraries.lectorcodigos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.SurfaceView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.grupo.pinsa.libraries.R;

import java.util.Objects;

/**
 * Created by Juan J. Palomera on 10/03/2019
 * Modify by Juan J. Palomera on 16/11/2019 - Se agrega validación de permisos para acceso a la camara y solicitud en caso de no ternerlos.
 */

public class LectorCodigos {
    public static final int LECTURA_UNICA = 0;
    public static final int LECTURA_CONTINUA = 1;

    private static LectorCodigosFragment fragment;
    private final int tipoLectura;
    private final LectorCodigosCallback lectorCodigosCallback;
    private final SurfaceView cameraView;

    public LectorCodigos(LectorCodigosFragment fragment, int tipo_lectura, LectorCodigosCallback lectorCodigosCallback) {
        LectorCodigos.fragment = fragment;
        this.tipoLectura = tipo_lectura;
        this.lectorCodigosCallback = lectorCodigosCallback;
        this.cameraView = Objects.requireNonNull(fragment.getActivity()).findViewById(R.id.id_lector_codigos);
    }

    public void Iniciar() {
        if (ContextCompat.checkSelfPermission(fragment.getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(fragment.getActivity(), new String[] { Manifest.permission.CAMERA }, 1);
        } else {
            final BarcodeDetector barcodeDetector = new BarcodeDetector
                    .Builder(fragment.getActivity())
                    .setBarcodeFormats(Barcode.ALL_FORMATS)
                    .build();

            CameraSource cameraSource = new CameraSource
                    .Builder(Objects.requireNonNull(fragment.getActivity()), barcodeDetector)
                    .setRequestedPreviewSize(1600, 1024)
                    .setAutoFocusEnabled(true)
                    .build();

            cameraView.getHolder().addCallback(new CameraViewCallback(fragment, cameraSource, cameraView));

            barcodeDetector.setProcessor(new DetectorProcessor(fragment, tipoLectura, lectorCodigosCallback));
        }
    }
}

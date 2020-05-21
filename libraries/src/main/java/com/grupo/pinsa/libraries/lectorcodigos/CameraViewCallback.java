package com.grupo.pinsa.libraries.lectorcodigos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.grupo.pinsa.libraries.R;
import com.grupo.pinsa.libraries.common.Constantes;
import com.grupo.pinsa.libraries.dialogos.Dialogo;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Juan J. Palomera on 10/03/2019
 */

public class CameraViewCallback implements SurfaceHolder.Callback {
    private final LectorCodigosFragment fragment;
    private final CameraSource cameraSource;
    private final SurfaceView cameraView;

    public CameraViewCallback(LectorCodigosFragment fragment, CameraSource cameraSource, SurfaceView cameraView) {
        this.fragment = fragment;
        this.cameraSource = cameraSource;
        this.cameraView = cameraView;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(fragment.getActivity()), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (fragment.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA))
                    fragment.requestPermissions(new String[] { Manifest.permission.CAMERA }, Constantes.PERMISO_REQUEST_CAMARA);
            }
        } else {
            try {
                cameraSource.start(cameraView.getHolder());
            } catch (IOException ie) {
                Dialogo.showDialogoError(fragment.getActivity(), R.string.grupopinsa_lector_codigos_error_camera, Dialogo.ACEPTAR, true);
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        cameraSource.stop();
    }
}

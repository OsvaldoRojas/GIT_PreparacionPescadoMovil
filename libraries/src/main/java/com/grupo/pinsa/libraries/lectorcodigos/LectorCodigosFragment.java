package com.grupo.pinsa.libraries.lectorcodigos;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grupo.pinsa.libraries.Fragment;
import com.grupo.pinsa.libraries.R;
import com.grupo.pinsa.libraries.common.Utilerias;
import com.grupo.pinsa.libraries.dialogos.Dialogo;

import java.util.Objects;

import androidx.annotation.NonNull;

/**
 * Created by Juan J. Palomera on 10/03/2019
 */

public class LectorCodigosFragment extends Fragment {
    private int tipoLectura;
    private LectorCodigosCallback lectorCodigosCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_lector_codigos, container, false);
    }

    @Override
    public void onResume() {
        new LectorCodigos(this, tipoLectura, lectorCodigosCallback).Iniciar();
        //cameraView = getActivity().findViewById(R.id.id_lector_codigos);
        //initScanner();
        super.onResume();
    }

    public LectorCodigosFragment setTipoLectura(int tipoLectura) {
        this.tipoLectura = tipoLectura;
        return this;
    }

    public LectorCodigosFragment setLectorCodigosCallback(LectorCodigosCallback lectorCodigosCallback) {
        this.lectorCodigosCallback = lectorCodigosCallback;
        return this;
    }

    public void close() {
        Objects.requireNonNull(this.getActivity()).getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}

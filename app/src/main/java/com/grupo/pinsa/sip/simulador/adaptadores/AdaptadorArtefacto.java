package com.grupo.pinsa.sip.simulador.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.vista.Refaccion;

import java.util.List;

public class AdaptadorArtefacto extends ArrayAdapter<Refaccion> {

    private Context contexto;
    private List<Refaccion> listaRefacciones;

    public AdaptadorArtefacto(Context contexto, List<Refaccion> listaRefacciones){
        super(contexto, 0, listaRefacciones);
        this.contexto = contexto;
        this.listaRefacciones = listaRefacciones;
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        if(vista == null){
            vista = ((LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.item_spinner, null);
        }

        ( (TextView) vista.findViewById(R.id.opcionSpinner) )
                .setText( this.listaRefacciones.get(posicion).getDescripcion() );

        return vista;
    }

    @Override
    public View getDropDownView(int posicion, @Nullable View vista, @NonNull ViewGroup padre) {
        View fila = vista;
        if (fila == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            fila = layoutInflater.inflate(R.layout.item_spinner, padre, false);
        }

        if (fila.getTag() == null) {
            ArtefactoHolder artefactoHolder = new ArtefactoHolder();
            artefactoHolder.setOpcioneSpinner( (TextView) fila.findViewById(R.id.opcionSpinner) );
            fila.setTag(artefactoHolder);
        }

        Refaccion refaccion = this.listaRefacciones.get(posicion);
        ( (ArtefactoHolder) fila.getTag() ).getOpcioneSpinner().setText( refaccion.getDescripcion() );

        return fila;
    }

    private static class ArtefactoHolder{
        private TextView opcioneSpinner;

        public TextView getOpcioneSpinner() {
            return opcioneSpinner;
        }

        public void setOpcioneSpinner(TextView opcioneSpinner) {
            this.opcioneSpinner = opcioneSpinner;
        }
    }
}

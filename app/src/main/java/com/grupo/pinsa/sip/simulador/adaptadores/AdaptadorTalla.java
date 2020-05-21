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
import com.grupo.pinsa.sip.simulador.modelo.Talla;

import java.util.List;

public class AdaptadorTalla extends ArrayAdapter<Talla> {

    private Context contexto;
    private List<Talla> listaTallas;

    public AdaptadorTalla(Context contexto, List<Talla> listaTallas){
        super(contexto, R.layout.item_spinner, listaTallas);
        this.contexto = contexto;
        this.listaTallas = listaTallas;
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        if(vista == null){
            vista = ((LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.item_spinner, null);
        }

        ( (TextView) vista.findViewById(R.id.opcionSpinner) )
                .setText( this.listaTallas.get(posicion).getDescripcion() );

        return vista;
    }

    @Override
    public View getDropDownView(int posicion, @Nullable View vista, @NonNull ViewGroup padre) {
        View fila = vista;
        if(fila == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            fila = layoutInflater.inflate(R.layout.item_spinner, padre, false);
        }

        if (fila.getTag() == null) {
            TallaHolder tallaHolder = new TallaHolder();
            tallaHolder.setOpcioneSpinner( (TextView) fila.findViewById(R.id.opcionSpinner) );
            fila.setTag(tallaHolder);
        }

        Talla talla = this.listaTallas.get(posicion);
        ( (TallaHolder) fila.getTag() ).getOpcioneSpinner().setText( talla.getDescripcion() );

        return fila;
    }

    private static class TallaHolder{
        private TextView opcioneSpinner;

        public TextView getOpcioneSpinner() {
            return opcioneSpinner;
        }

        public void setOpcioneSpinner(TextView opcioneSpinner) {
            this.opcioneSpinner = opcioneSpinner;
        }
    }
}

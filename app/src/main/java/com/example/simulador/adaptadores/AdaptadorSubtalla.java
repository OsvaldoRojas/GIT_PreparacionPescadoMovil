package com.example.simulador.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.simulador.R;
import com.example.simulador.vista.Subtalla;

import java.util.List;

public class AdaptadorSubtalla extends ArrayAdapter<Subtalla> {

    private Context contexto;
    private List<Subtalla> listaSubtalla;

    public AdaptadorSubtalla(Context contexto, List<Subtalla> listaSubtalla){
        super(contexto, R.layout.item_spinner, listaSubtalla);
        this.contexto = contexto;
        this.listaSubtalla = listaSubtalla;
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        if(vista == null){
            vista = ((LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.item_spinner, null);
        }

        ( (TextView) vista.findViewById(R.id.opcionSpinner) )
                .setText( this.listaSubtalla.get(posicion).getDescripcion() );

        return vista;
    }

    @Override
    public View getDropDownView(int posicion, @Nullable View vista, @NonNull ViewGroup padre) {
        View fila = vista;

        if(fila == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            fila = layoutInflater.inflate(R.layout.item_spinner, null, false);
        }

        if (fila.getTag() == null) {
            SubtallaHolder subtallaHolder = new SubtallaHolder();
            subtallaHolder.setOpcioneSpinner( (TextView) fila.findViewById(R.id.opcionSpinner) );
            fila.setTag(subtallaHolder);
        }

        Subtalla subtalla = this.listaSubtalla.get(posicion);
        ( (SubtallaHolder) fila.getTag() ).getOpcioneSpinner().setText( subtalla.getDescripcion() );

        return fila;
    }

    private static class SubtallaHolder{
        private TextView opcioneSpinner;

        public TextView getOpcioneSpinner() {
            return opcioneSpinner;
        }

        public void setOpcioneSpinner(TextView opcioneSpinner) {
            this.opcioneSpinner = opcioneSpinner;
        }
    }
}

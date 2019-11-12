package com.example.simulador_pescado.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.vista.Maquinaria;

import java.util.List;

public class AdaptadorMaquinaria extends ArrayAdapter<Maquinaria> {

    private List<Maquinaria> listaMaquinaria;
    private Context contexto;

    public AdaptadorMaquinaria(List<Maquinaria> listaMaquinaria, Context contexto) {
        super(contexto, R.layout.item_spinner, listaMaquinaria);
        this.contexto = contexto;
        this.listaMaquinaria = listaMaquinaria;
    }

    @NonNull
    @Override
    public View getView(int posicion, @Nullable View vista, @NonNull ViewGroup parent) {
        if(vista == null){
            vista = ( (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) )
                    .inflate(R.layout.item_spinner, null);
        }

        ( (TextView) vista.findViewById(R.id.opcionSpinner) )
                .setText( this.listaMaquinaria.get(posicion).getDescripcion() );

        return vista;
    }

    @Override
    public View getDropDownView(int posicion, @Nullable View vista, @NonNull ViewGroup padre) {
        View fila = vista;
        if(fila == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            fila = layoutInflater.inflate(R.layout.item_spinner, padre, false);
        }

        if(fila.getTag() == null){
            MaquinariaHolder maquinariaHolder = new MaquinariaHolder();
            maquinariaHolder.setOpcioneSpinner( (TextView) fila.findViewById(R.id.opcionSpinner) );
            fila.setTag(maquinariaHolder);
        }

        Maquinaria maquinaria = this.listaMaquinaria.get(posicion);
        ( (MaquinariaHolder) fila.getTag() ).getOpcioneSpinner().setText( maquinaria.getDescripcion() );

        return fila;
    }

    private static class MaquinariaHolder {
        private TextView opcioneSpinner;

        public TextView getOpcioneSpinner() {
            return opcioneSpinner;
        }

        public void setOpcioneSpinner(TextView opcioneSpinner) {
            this.opcioneSpinner = opcioneSpinner;
        }
    }
}

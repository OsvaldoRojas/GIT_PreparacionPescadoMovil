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
import com.grupo.pinsa.sip.simulador.modelo.Bascula;

import java.util.List;

public class AdaptadorBasculaCatalogo extends ArrayAdapter<Bascula> {

    private List<Bascula> listaBasculas;
    private Context contexto;

    public AdaptadorBasculaCatalogo(Context contexto, List<Bascula> listaBasculas) {
        super(contexto, R.layout.item_spinner, listaBasculas);
        this.contexto = contexto;
        this.listaBasculas = listaBasculas;
    }

    @NonNull
    @Override
    public View getView(int posicion, @Nullable View vista, @NonNull ViewGroup parent) {
        if(vista == null){
            vista = ((LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.item_spinner, null);
        }

        ( (TextView) vista.findViewById(R.id.opcionSpinner) )
                .setText( this.listaBasculas.get(posicion).getDescripcion() );

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
            BasculaCatalogoHolder basculaCatalogoHolder = new BasculaCatalogoHolder();
            basculaCatalogoHolder.setOpcioneSpinner( (TextView) fila.findViewById(R.id.opcionSpinner) );
            fila.setTag(basculaCatalogoHolder);
        }

        Bascula bascula = this.listaBasculas.get(posicion);
        ( (BasculaCatalogoHolder) fila.getTag() ).getOpcioneSpinner().setText( bascula.getDescripcion() );

        return fila;
    }

    private static class BasculaCatalogoHolder{
        private TextView opcioneSpinner;

        public TextView getOpcioneSpinner() {
            return opcioneSpinner;
        }

        public void setOpcioneSpinner(TextView opcioneSpinner) {
            this.opcioneSpinner = opcioneSpinner;
        }
    }
}

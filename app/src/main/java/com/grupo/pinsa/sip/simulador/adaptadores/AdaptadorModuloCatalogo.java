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
import com.grupo.pinsa.sip.simulador.modelo.Modulo;

import java.util.List;

public class AdaptadorModuloCatalogo extends ArrayAdapter<Modulo> {

    private List<Modulo> listaModulos;
    private Context contexto;

    public AdaptadorModuloCatalogo(Context contexto, List<Modulo> listaModulos) {
        super(contexto, R.layout.item_spinner, listaModulos);
        this.contexto = contexto;
        this.listaModulos = listaModulos;
    }

    @NonNull
    @Override
    public View getView(int posicion, @Nullable View vista, @NonNull ViewGroup parent) {
        if(vista == null){
            vista = ((LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.item_spinner, null);
        }

        ( (TextView) vista.findViewById(R.id.opcionSpinner) )
                .setText( this.listaModulos.get(posicion).getDescripcion() );

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
            ModuloCatalogoHolder moduloCatalogoHolder = new ModuloCatalogoHolder();
            moduloCatalogoHolder.setOpcioneSpinner( (TextView) fila.findViewById(R.id.opcionSpinner) );
            fila.setTag(moduloCatalogoHolder);
        }

        Modulo modulo = this.listaModulos.get(posicion);
        ( (ModuloCatalogoHolder) fila.getTag() ).getOpcioneSpinner().setText( modulo.getDescripcion() );

        return fila;
    }

    private static class ModuloCatalogoHolder{
        private TextView opcioneSpinner;

        public TextView getOpcioneSpinner() {
            return opcioneSpinner;
        }

        public void setOpcioneSpinner(TextView opcioneSpinner) {
            this.opcioneSpinner = opcioneSpinner;
        }
    }
}
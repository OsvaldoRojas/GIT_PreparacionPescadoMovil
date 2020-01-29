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
import com.example.simulador.vista.GrupoEspecie;

import java.util.List;

public class AdaptadorGrupoEspecie extends ArrayAdapter<GrupoEspecie> {

    private Context contexto;
    private List<GrupoEspecie> listaEspecie;

    public AdaptadorGrupoEspecie(Context contexto, List<GrupoEspecie> listaEspecie){
        super(contexto, R.layout.item_spinner, listaEspecie);
        this.contexto = contexto;
        this.listaEspecie = listaEspecie;
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        if(vista == null){
            vista = ((LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.item_spinner, null);
        }

        ( (TextView) vista.findViewById(R.id.opcionSpinner) )
                .setText( this.listaEspecie.get(posicion).getDescripcion() );

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
            EspecieHolder especieHolder = new EspecieHolder();
            especieHolder.setOpcioneSpinner( (TextView) fila.findViewById(R.id.opcionSpinner) );
            fila.setTag(especieHolder);
        }

        GrupoEspecie especie = this.listaEspecie.get(posicion);
        ( (EspecieHolder) fila.getTag() ).getOpcioneSpinner().setText( especie.getDescripcion() );

        return fila;
    }

    private static class EspecieHolder{
        private TextView opcioneSpinner;

        public TextView getOpcioneSpinner() {
            return opcioneSpinner;
        }

        public void setOpcioneSpinner(TextView opcioneSpinner) {
            this.opcioneSpinner = opcioneSpinner;
        }
    }
}

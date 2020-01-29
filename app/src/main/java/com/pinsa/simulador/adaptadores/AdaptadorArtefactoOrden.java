package com.pinsa.simulador.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pinsa.simulador.R;
import com.pinsa.simulador.vista.Artefacto;

import java.util.List;

public class AdaptadorArtefactoOrden extends ArrayAdapter<Artefacto> {

    public AdaptadorArtefactoOrden(Context contexto, List<Artefacto> listaArtefacto){
        super(contexto, 0, listaArtefacto);
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        Artefacto artefacto = getItem(posicion);
        if(vista == null){
            vista = LayoutInflater.from( getContext() ).inflate(R.layout.item_artefacto_orden, padre, false);
        }

        TextView etiquetaDescripcion = vista.findViewById(R.id.etiquetaDescripcion);
        etiquetaDescripcion.setText( artefacto.getDescripcion() );

        if( (posicion % 2) == 0 ){
            etiquetaDescripcion.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion_dos) );
        }else{
            etiquetaDescripcion.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion) );
        }

        return vista;
    }
}

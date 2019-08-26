package com.example.simulador_pescado.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.vista.RefaccionLista;

import java.util.List;

public class AdaptadorArtefactoLista extends ArrayAdapter<RefaccionLista> {

    public AdaptadorArtefactoLista(Context contexto, List<RefaccionLista> listaArtefactos){
        super(contexto, 0, listaArtefactos);
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        final RefaccionLista refaccionLista = getItem(posicion);
        if(vista == null){
            vista = LayoutInflater.from( getContext() ).inflate(R.layout.item_artefacto, padre, false);
        }

        TextView campoCantidad = vista.findViewById(R.id.etiquetaCantidad);
        TextView campoCodigo = vista.findViewById(R.id.etiquetaCodigo);
        TextView campoArtefacto = vista.findViewById(R.id.etiquetaArtefacto);

        campoCantidad.setText( String.valueOf( refaccionLista.getCantidad() ) );
        campoCodigo.setText( refaccionLista.getCodigo() );
        campoArtefacto.setText( refaccionLista.getRefaccion().getDescripcion() );

        if( (posicion % 2) == 0 ){
            campoCantidad.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion_dos) );
            campoCodigo.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion_dos) );
            campoArtefacto.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion_dos) );
        }else{
            campoCantidad.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion) );
            campoCodigo.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion) );
            campoArtefacto.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion) );
        }

        return vista;
    }
}

package com.example.simulador_pescado.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.vista.Tina;

import java.util.List;

public class AdaptadorMezclarSubtallas extends ArrayAdapter<Tina> {

    public AdaptadorMezclarSubtallas(Context contexto, List<Tina> listaSubtallas){
        super(contexto, 0, listaSubtallas);
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        Tina posicionSubtalla = getItem(posicion);
        if(vista == null){
            vista = LayoutInflater.from( getContext() ).inflate(R.layout.item_mezclar_subtalla, padre, false);
        }

        TextView posicionTina = vista.findViewById(R.id.etiquetaPosicionTina);
        TextView subtalla = vista.findViewById(R.id.etiquetaSubtalla);

        posicionTina.setText( posicionSubtalla.getPosicion() );
        subtalla.setText( posicionSubtalla.getSubtalla().getDescripcion() );

        return vista;
    }
}

package com.grupo.pinsa.sip.simulador.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.vista.Tina;

import java.util.List;

public class AdaptadorTinasLiberadas extends ArrayAdapter<Tina> {

    public AdaptadorTinasLiberadas(Context contexto, List<Tina> listaTinas){
        super(contexto, 0, listaTinas);
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        Tina tina = getItem(posicion);
        if(vista == null){
            vista = LayoutInflater.from( getContext() ).inflate(R.layout.item_tinas_liberadas, padre, false);
        }

        TextView posicionTina = vista.findViewById(R.id.etiquetaPosicionTina);
        posicionTina.setText( tina.getPosicion() );

        return vista;
    }
}

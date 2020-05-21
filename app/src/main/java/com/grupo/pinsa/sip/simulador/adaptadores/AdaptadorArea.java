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
import com.grupo.pinsa.sip.simulador.modelo.Area;

import java.util.List;

public class AdaptadorArea extends ArrayAdapter<Area> {

    private Context contexto;
    private List<Area> listaAreas;

    public AdaptadorArea(Context contexto, List<Area> listaAreas){
        super(contexto, R.layout.item_spinner, listaAreas);
        this.contexto = contexto;
        this.listaAreas = listaAreas;
    }

    @NonNull
    @Override
    public View getView(int posicion, @Nullable View vista, @NonNull ViewGroup padre) {
        if(vista == null){
            vista = ( (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) )
                    .inflate(R.layout.item_spinner, null);
        }

        ( (TextView) vista.findViewById(R.id.opcionSpinner) )
                .setText( this.listaAreas.get(posicion).getDescripcion() );

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
            AreaHolder areaHolder = new AreaHolder();
            areaHolder.setOpcionSpinner( (TextView) fila.findViewById(R.id.opcionSpinner) );
            fila.setTag(areaHolder);
        }

        Area area = this.listaAreas.get(posicion);
        ( (AreaHolder) fila.getTag() ).getOpcionSpinner().setText( area.getDescripcion() );

        return fila;
    }

    private static class AreaHolder{
        private TextView opcionSpinner;

        public TextView getOpcionSpinner() {
            return opcionSpinner;
        }

        public void setOpcionSpinner(TextView opcionSpinner) {
            this.opcionSpinner = opcionSpinner;
        }
    }
}

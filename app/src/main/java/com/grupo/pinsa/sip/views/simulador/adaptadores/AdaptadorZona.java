package com.grupo.pinsa.sip.views.simulador.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.modelo.Zona;

import java.util.List;

public class AdaptadorZona extends ArrayAdapter<Zona> {

    private Context contexto;
    private List<Zona> listaZonas;

    public AdaptadorZona(Context contexto, List<Zona> listaZonas){
        super(contexto, R.layout.item_spinner, listaZonas);
        this.contexto = contexto;
        this.listaZonas = listaZonas;
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        if(vista == null){
            vista = ( (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) )
                    .inflate(R.layout.item_spinner, null);
        }

        ( (TextView) vista.findViewById(R.id.opcionSpinner) )
                .setText( this.listaZonas.get(posicion).getDescripcion() );

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
            ZonaHolder zonaHolder = new ZonaHolder();
            zonaHolder.setOpcioneSpinner( (TextView) fila.findViewById(R.id.opcionSpinner) );
            fila.setTag(zonaHolder);
        }

        Zona zona = this.listaZonas.get(posicion);
        ( (ZonaHolder) fila.getTag() ).getOpcioneSpinner().setText( zona.getDescripcion() );

        return fila;
    }

    private static class ZonaHolder{
        private TextView opcioneSpinner;

        public TextView getOpcioneSpinner() {
            return opcioneSpinner;
        }

        public void setOpcioneSpinner(TextView opcioneSpinner) {
            this.opcioneSpinner = opcioneSpinner;
        }
    }

}

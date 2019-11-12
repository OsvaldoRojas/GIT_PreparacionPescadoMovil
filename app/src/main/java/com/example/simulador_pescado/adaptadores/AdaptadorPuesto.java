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
import com.example.simulador_pescado.vista.Puesto;

import java.util.List;

public class AdaptadorPuesto extends ArrayAdapter<Puesto> {

    private Context contexto;
    private List<Puesto> listaPuestos;

    public AdaptadorPuesto(Context contexto, List<Puesto> listaPuestos){
        super(contexto, R.layout.item_spinner, listaPuestos);
        this.contexto = contexto;
        this.listaPuestos = listaPuestos;
    }

    @NonNull
    @Override
    public View getView(int posicion, @Nullable View vista, @NonNull ViewGroup padre) {
        if(vista == null){
            vista = ( (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) )
                    .inflate(R.layout.item_spinner, null);
        }

        ( (TextView) vista.findViewById(R.id.opcionSpinner) )
                .setText( this.listaPuestos.get(posicion).getDescripcion() );

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
            PuestoHolder puestoHolder = new PuestoHolder();
            puestoHolder.setOpcionSpinner( (TextView) fila.findViewById(R.id.opcionSpinner) );
            fila.setTag(puestoHolder);
        }

        Puesto puesto = this.listaPuestos.get(posicion);
        ( (PuestoHolder) fila.getTag() ).getOpcionSpinner().setText( puesto.getDescripcion() );

        return fila;
    }

    private static class PuestoHolder{
        private TextView opcionSpinner;

        public TextView getOpcionSpinner() {
            return opcionSpinner;
        }

        public void setOpcionSpinner(TextView opcionSpinner) {
            this.opcionSpinner = opcionSpinner;
        }
    }
}

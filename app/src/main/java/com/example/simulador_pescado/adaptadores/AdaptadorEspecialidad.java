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
import com.example.simulador_pescado.vista.Especialidad;

import java.util.List;

public class AdaptadorEspecialidad extends ArrayAdapter<Especialidad> {

    private Context contexto;
    private List<Especialidad> listaEspecialidad;

    public AdaptadorEspecialidad(Context contexto, List<Especialidad> listaEspecialidad){
        super(contexto, R.layout.item_spinner, listaEspecialidad);
        this.contexto = contexto;
        this.listaEspecialidad = listaEspecialidad;
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        if(vista == null){
            vista = ( (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) )
                    .inflate(R.layout.item_spinner, null);
        }

        ( (TextView) vista.findViewById(R.id.opcionSpinner) )
                .setText( this.listaEspecialidad.get(posicion).getDescripcion() );

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
            EspecialidadHolder especialidadHolder = new EspecialidadHolder();
            especialidadHolder.setOpcioneSpinner( (TextView) fila.findViewById(R.id.opcionSpinner) );
            fila.setTag(especialidadHolder);
        }

        Especialidad especialidad = this.listaEspecialidad.get(posicion);
        ( (EspecialidadHolder) fila.getTag() ).getOpcioneSpinner().setText( especialidad.getDescripcion() );

        return fila;
    }

    private static class EspecialidadHolder{
        private TextView opcioneSpinner;

        public TextView getOpcioneSpinner() {
            return opcioneSpinner;
        }

        public void setOpcioneSpinner(TextView opcioneSpinner) {
            this.opcioneSpinner = opcioneSpinner;
        }
    }
}

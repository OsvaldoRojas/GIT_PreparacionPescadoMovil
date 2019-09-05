package com.example.simulador_pescado.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.vista.ListaOrdenMantenimientoServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdaptadorOrdenMantenimiento extends ArrayAdapter<ListaOrdenMantenimientoServicio> {

    private List<ListaOrdenMantenimientoServicio> listaOrden;
    private List<ListaOrdenMantenimientoServicio> listaRespaldo = new ArrayList<>();

    public AdaptadorOrdenMantenimiento(Context contexto, List<ListaOrdenMantenimientoServicio> listaOrden){
        super(contexto, 0, listaOrden);
        this.listaOrden = listaOrden;
        this.listaRespaldo.addAll(listaOrden);
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        ListaOrdenMantenimientoServicio ordenMantenimiento = getItem(posicion);
        if(vista == null){
            vista = LayoutInflater.from( getContext() ).inflate(R.layout.item_orden_mantenimiento, padre, false);
        }

        TextView etiquetaFolio = vista.findViewById(R.id.etiquetaFolio);
        TextView etiquetaFecha = vista.findViewById(R.id.etiquetaFecha);
        TextView etiquetaEquipo = vista.findViewById(R.id.etiquetaEquipo);
        TextView etiquetaMecanico = vista.findViewById(R.id.etiquetaMecanico);

        etiquetaFolio.setText( ordenMantenimiento.getFolio() );
        etiquetaFecha.setText( ordenMantenimiento.getFecha() );
        etiquetaEquipo.setText( ordenMantenimiento.getMaquinaria() );
        etiquetaMecanico.setText( ordenMantenimiento.getNombreEmpleado().concat(" ")
                .concat( ordenMantenimiento.getaPaternoEmpleado() ) );

        if( (posicion % 2) == 0 ){
            etiquetaFolio.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion_dos) );
            etiquetaFecha.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion_dos) );
            etiquetaEquipo.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion_dos) );
            etiquetaMecanico.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion_dos) );
        }else{
            etiquetaFolio.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion) );
            etiquetaFecha.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion) );
            etiquetaEquipo.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion) );
            etiquetaMecanico.setBackgroundColor( getContext().getResources().getColor(R.color.no_selecion) );
        }

        return vista;
    }

    public void filtro(String textoBusqueda){
        textoBusqueda = textoBusqueda.toLowerCase( Locale.getDefault() );
        this.listaOrden.clear();

        if( textoBusqueda.length() == 0 ){
            this.listaOrden.addAll(this.listaRespaldo);
        }else{
            for( ListaOrdenMantenimientoServicio orden : this.listaRespaldo ){
                if( String.valueOf( orden.getFolio() ).contains(textoBusqueda)
                        || orden.getFecha().substring(0, 10).contains(textoBusqueda)
                        || orden.getMaquinaria().toLowerCase( Locale.getDefault() ).contains(textoBusqueda)
                        || orden.getNombreEmpleado().toLowerCase( Locale.getDefault() ).contains(textoBusqueda)
                        || orden.getaPaternoEmpleado().toLowerCase( Locale.getDefault() ).contains(textoBusqueda) ){
                    this.listaOrden.add(orden);
                }
            }
        }
        notifyDataSetChanged();
    }
}

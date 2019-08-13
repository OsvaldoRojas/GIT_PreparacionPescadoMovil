package com.example.simulador_pescado.adaptadores;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.vista.Artefacto;
import com.example.simulador_pescado.vista.ArtefactoLista;

import java.util.List;

public class AdaptadorArtefactoLista extends ArrayAdapter<ArtefactoLista> {

    private List<Artefacto> listaSpinner;

    public AdaptadorArtefactoLista(Context contexto, List<ArtefactoLista> listaArtefactos, List<Artefacto> listaSpinner){
        super(contexto, 0, listaArtefactos);
        this.listaSpinner = listaSpinner;
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        final ArtefactoLista artefactoLista = getItem(posicion);
        if(vista == null){
            vista = LayoutInflater.from( getContext() ).inflate(R.layout.item_artefacto, padre, false);
        }

        EditText campoCantidad = vista.findViewById(R.id.campoCantidad);
        Spinner campoArtefacto = vista.findViewById(R.id.campoArtefacto);

        campoCantidad.setText( String.valueOf( artefactoLista.getCantidad() ) );
        campoCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if( editable.toString().equals("") ){
                    artefactoLista.setCantidad( 0 );
                }else{
                    artefactoLista.setCantidad( Integer.valueOf( editable.toString() ) );
                }
            }
        });

        campoArtefacto.setAdapter( new AdaptadorArtefacto( getContext(), this.listaSpinner ) );
        campoArtefacto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adaptadorVista, View vista, int posicion, long id) {
                artefactoLista.setArtefacto( (Artefacto) adaptadorVista.getItemAtPosition(posicion) );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        campoArtefacto.setSelection( obtenerPosicionItem( campoArtefacto, artefactoLista.getArtefacto().getDescripcion() ) );

        return vista;
    }

    public static int obtenerPosicionItem(Spinner spinner, String descripcion) {
        int posicion = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if ( ( (Artefacto) spinner.getItemAtPosition(i) ).getDescripcion().equalsIgnoreCase(descripcion) ) {
                posicion = i;
            }
        }
        return posicion;
    }
}

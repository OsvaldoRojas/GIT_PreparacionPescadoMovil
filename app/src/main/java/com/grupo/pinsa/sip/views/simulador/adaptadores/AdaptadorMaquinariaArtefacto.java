package com.grupo.pinsa.sip.views.simulador.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.modelo.Artefacto;

import java.util.List;

public class AdaptadorMaquinariaArtefacto extends ArrayAdapter<Artefacto> {

    private List<Artefacto> listaArtefactos;

    public AdaptadorMaquinariaArtefacto(Context contexto, List<Artefacto> listaArtefactos){
        super(contexto, 0, listaArtefactos);
        this.listaArtefactos = listaArtefactos;
    }

    @NonNull
    @Override
    public View getView(int posicion, @Nullable View vista, @NonNull ViewGroup padre) {
        final Artefacto artefacto = getItem(posicion);
        if( vista == null ){
            vista = LayoutInflater.from( getContext() ).inflate(R.layout.item_maquinaria_artefactos, padre, false);
        }

        TextView descripcion = vista.findViewById(R.id.descripcion);
        CheckBox checkBox = vista.findViewById(R.id.checkBox);

        descripcion.setText( artefacto.getDescripcion() );
        checkBox.setTag(posicion);
        checkBox.setChecked( artefacto.getSelecionado() );
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                int position = (Integer) view.getTag();
                getItem(position).setSelecionado( checkBox.isChecked() );
            }
        });

        vista.setClickable(true);
        return vista;
    }

    public List<Artefacto> getListaArtefactos() {
        return listaArtefactos;
    }
}

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
import com.grupo.pinsa.sip.simulador.modelo.Cocedor;

import java.util.List;

public class AdaptadorCocedorCatalogo extends ArrayAdapter<Cocedor> {

    private List<Cocedor> listaCocedores;
    private Context contexto;

    public AdaptadorCocedorCatalogo(Context contexto, List<Cocedor> listaCocedores) {
        super(contexto, R.layout.item_spinner, listaCocedores);
        this.contexto = contexto;
        this.listaCocedores = listaCocedores;
    }

    @NonNull
    @Override
    public View getView(int posicion, @Nullable View vista, @NonNull ViewGroup parent) {
        if(vista == null){
            vista = ((LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.item_spinner, null);
        }

        ( (TextView) vista.findViewById(R.id.opcionSpinner) )
                .setText( this.listaCocedores.get(posicion).getTamano() );

        return vista;
    }

    @Override
    public View getDropDownView(int posicion, @Nullable View vista, @NonNull ViewGroup padre) {
        View fila = vista;
        if(fila == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            fila = layoutInflater.inflate(R.layout.item_spinner, padre, false);
        }

        if (fila.getTag() == null) {
            CocedorCatalogoHolder cocedorCatalogoHolder = new CocedorCatalogoHolder();
            cocedorCatalogoHolder.setOpcioneSpinner( (TextView) fila.findViewById(R.id.opcionSpinner) );
            fila.setTag(cocedorCatalogoHolder);
        }

        Cocedor cocedor = this.listaCocedores.get(posicion);
        ( (CocedorCatalogoHolder) fila.getTag() ).getOpcioneSpinner().setText( cocedor.getTamano() );

        return fila;
    }

    private static class CocedorCatalogoHolder{
        private TextView opcioneSpinner;

        public TextView getOpcioneSpinner() {
            return opcioneSpinner;
        }

        public void setOpcioneSpinner(TextView opcioneSpinner) {
            this.opcioneSpinner = opcioneSpinner;
        }
    }
}

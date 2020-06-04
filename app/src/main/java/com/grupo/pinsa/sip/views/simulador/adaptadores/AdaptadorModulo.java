package com.grupo.pinsa.sip.views.simulador.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.modulos.Fragment_Modulos_Plan;
import com.grupo.pinsa.sip.views.simulador.modelo.Modulo;

import java.util.List;

public class AdaptadorModulo extends RecyclerView.Adapter<AdaptadorModulo.ModuloViewHolder>{

    private List<Modulo> listaModulos;
    private Fragment pantalla;

    public AdaptadorModulo(List<Modulo> listaModulos, Fragment pantalla){
        this.listaModulos = listaModulos;
        this.pantalla = pantalla;
    }

    @NonNull
    @Override
    public ModuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.card_modulos, parent, false);
        return new ModuloViewHolder(vista, this.pantalla);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuloViewHolder holder, int position) {
        holder.descripcion.setText( this.listaModulos.get(position).getDescripcion() );
    }

    @Override
    public int getItemCount() {
        return this.listaModulos.size();
    }

    public static class ModuloViewHolder extends RecyclerView.ViewHolder{

        public TextView descripcion;

        public ModuloViewHolder(View vista, final Fragment pantalla){
            super(vista);
            this.descripcion = vista.findViewById(R.id.descripcion);

            vista.setClickable(true);
            vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ( (Fragment_Modulos_Plan) pantalla).vistaModulo( getLayoutPosition() );
                }
            });
        }
    }
}

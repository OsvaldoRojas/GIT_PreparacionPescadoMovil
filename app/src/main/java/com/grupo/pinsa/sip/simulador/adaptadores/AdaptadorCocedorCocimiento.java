package com.grupo.pinsa.sip.simulador.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.cocimiento.Fragment_Cocimiento_Plan;
import com.grupo.pinsa.sip.simulador.vista.Cocedor;

import java.util.List;

public class AdaptadorCocedorCocimiento extends RecyclerView.Adapter<AdaptadorCocedorCocimiento.CocedorCocimientoViewHolder>{

    private List<Cocedor> listaCocedores;
    private Fragment pantalla;

    public AdaptadorCocedorCocimiento(List<Cocedor> listaCocedores, Fragment pantalla){
        this.listaCocedores = listaCocedores;
        this.pantalla = pantalla;
    }

    @NonNull
    @Override
    public CocedorCocimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.card_cocimiento_cocedor, parent, false);
        return new CocedorCocimientoViewHolder(vista, this.pantalla);
    }

    @Override
    public void onBindViewHolder(@NonNull CocedorCocimientoViewHolder holder, int position) {
        holder.tamano.setText( this.listaCocedores.get(position).getTamano() );
        holder.capacidad.setText( String.valueOf( this.listaCocedores.get(position).getCapacidad() ).concat(" Carritos") );
        holder.estatus.setText( this.listaCocedores.get(position).getEstatus() );

        //CAMBIAR IMAGEN
    }

    @Override
    public int getItemCount() {
        return this.listaCocedores.size();
    }

    public static class CocedorCocimientoViewHolder extends RecyclerView.ViewHolder{

        public TextView tamano;
        public TextView capacidad;
        public TextView estatus;
        public ImageView imagenCocedor;

        public CocedorCocimientoViewHolder(View vista, final Fragment pantalla){
            super(vista);
            this.tamano = vista.findViewById(R.id.tamano);
            this.capacidad = vista.findViewById(R.id.capacidad);
            this.estatus = vista.findViewById(R.id.estatus);
            this.imagenCocedor = vista.findViewById(R.id.imagenCocedor);

            vista.setClickable(true);
            vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ( (Fragment_Cocimiento_Plan) pantalla).cargaCarritos( getLayoutPosition() );
                }
            });
        }
    }
}

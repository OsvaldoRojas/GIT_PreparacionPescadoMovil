package com.grupo.pinsa.sip.simulador.adaptadores;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.cocimiento.Fragment_Cocimiento_Plan;
import com.grupo.pinsa.sip.simulador.estatusCocedor.Fragment_Estatus;
import com.grupo.pinsa.sip.simulador.modelo.Cocedor;

import java.util.List;

public class AdaptadorCocedor extends RecyclerView.Adapter<AdaptadorCocedor.CocedorCocimientoViewHolder>{

    private List<Cocedor> listaCocedores;
    private Fragment pantalla;

    public AdaptadorCocedor(List<Cocedor> listaCocedores, Fragment pantalla){
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
        Cocedor cocedor = this.listaCocedores.get(position);

        if( !cocedor.getTamano().equals("") ){
            holder.contenedorTamano.setVisibility(View.VISIBLE);
            holder.tamano.setText( cocedor.getTamano() );
        }

        if( !String.valueOf( cocedor.getCapacidad() ).equals("") ){
            holder.contenedorCapacidad.setVisibility(View.VISIBLE);
            holder.capacidad.setText( String.valueOf( cocedor.getCapacidad() ).concat(" carritos") );
        }

        if( !cocedor.getEstatus().equals("") ){
            holder.contenedorEstatus.setVisibility(View.VISIBLE);
            holder.estatus.setText( cocedor.getEstatus() );
        }

        if( !cocedor.getEspecie().equals("") ){
            holder.contenedorEspecie.setVisibility(View.VISIBLE);
            holder.especie.setText( cocedor.getEspecie() );
        }

        if( !cocedor.getEspecialidad().equals("") ){
            holder.contenedorEspecialidad.setVisibility(View.VISIBLE);
            holder.especialidad.setText( cocedor.getEspecialidad() );
        }

        if( !cocedor.getTiempoRestante().equals("") ){
            holder.contenedorTiempoRestante.setVisibility(View.VISIBLE);
            holder.tiempoRestante.setText( cocedor.getTiempoRestante() );
        }

        if( !cocedor.getTotalCarritos().equals("") ){
            holder.contenedorCarritos.setVisibility(View.VISIBLE);
            holder.carritos.setText( cocedor.getTotalCarritos() );
        }

        holder.imagenCocedor.setColorFilter(Color.parseColor( cocedor.getColor() ), PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public int getItemCount() {
        return this.listaCocedores.size();
    }

    public static class CocedorCocimientoViewHolder extends RecyclerView.ViewHolder{

        public TextView tamano;
        public TextView capacidad;
        public TextView estatus;
        public TextView especie;
        public TextView especialidad;
        public TextView tiempoRestante;
        public TextView carritos;
        public LinearLayout contenedorTamano;
        public LinearLayout contenedorCapacidad;
        public LinearLayout contenedorEstatus;
        public LinearLayout contenedorEspecie;
        public LinearLayout contenedorEspecialidad;
        public LinearLayout contenedorTiempoRestante;
        public LinearLayout contenedorCarritos;
        public ImageView imagenCocedor;

        public CocedorCocimientoViewHolder(View vista, final Fragment pantalla){
            super(vista);
            this.tamano = vista.findViewById(R.id.tamano);
            this.capacidad = vista.findViewById(R.id.capacidad);
            this.estatus = vista.findViewById(R.id.estatus);
            this.especie = vista.findViewById(R.id.especie);
            this.especialidad = vista.findViewById(R.id.especialidad);
            this.tiempoRestante = vista.findViewById(R.id.tiempoRestante);
            this.carritos = vista.findViewById(R.id.carritos);
            this.contenedorTamano = vista.findViewById(R.id.contenedorTamano);
            this.contenedorCapacidad = vista.findViewById(R.id.contenedorCapacidad);
            this.contenedorEstatus = vista.findViewById(R.id.contenedorEstatus);
            this.contenedorEspecie = vista.findViewById(R.id.contenedorEspecie);
            this.contenedorEspecialidad = vista.findViewById(R.id.contenedorEspecialidad);
            this.contenedorTiempoRestante = vista.findViewById(R.id.contenedorTiempoRestante);
            this.contenedorCarritos = vista.findViewById(R.id.contenedorCarritos);
            this.imagenCocedor = vista.findViewById(R.id.imagenCocedor);

            vista.setClickable(true);
            vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( pantalla instanceof Fragment_Cocimiento_Plan ){
                        ( (Fragment_Cocimiento_Plan) pantalla).cargaCarritos( getLayoutPosition() );
                    }else{
                        if( pantalla instanceof Fragment_Estatus ){
                            ( (Fragment_Estatus) pantalla).detalleCocida( getLayoutPosition() );
                        }
                    }
                }
            });
        }
    }
}

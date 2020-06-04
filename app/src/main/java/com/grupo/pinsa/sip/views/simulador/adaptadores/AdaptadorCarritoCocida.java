package com.grupo.pinsa.sip.views.simulador.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.modelo.Carrito;

import java.util.List;

public class AdaptadorCarritoCocida extends RecyclerView.Adapter<AdaptadorCarritoCocida.CarritoCocidaViewHolder>{

    private List<Carrito> listaCarritos;

    public AdaptadorCarritoCocida(List<Carrito> listaCarritos){
        this.listaCarritos = listaCarritos;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public CarritoCocidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.item_carrito_cocida, parent, false);
        return new CarritoCocidaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoCocidaViewHolder holder, int position) {
        holder.carrito.setText( this.listaCarritos.get(position).getDescripcion() );
        holder.especie.setText( this.listaCarritos.get(position).getEspecie() );
        holder.subtalla.setText( this.listaCarritos.get(position).getSubtalla() );
        holder.temperatura.setText( String.valueOf( this.listaCarritos.get(position).getTemperatura() ).concat("°") );
        holder.barco.setText("");
        holder.calidad.setText("");
        if( this.listaCarritos.get(position).getMovimientos() != null && !this.listaCarritos.get(position).getMovimientos().isEmpty() ){
            if( this.listaCarritos.get(position).getMovimientos().size() == 1 ){
                holder.barco.setText( this.listaCarritos.get(position).getMovimientos().get(0).getBarco() );
                holder.calidad.setText( this.listaCarritos.get(position).getMovimientos().get(0).getCalidad() );
            }else{
                holder.barco.setText( this.listaCarritos.get(position).getMovimientos().get(0).getBarco().concat("...") );
                holder.calidad.setText( this.listaCarritos.get(position).getMovimientos().get(0).getCalidad().concat("...") );
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.listaCarritos.size();
    }

    public static class CarritoCocidaViewHolder extends RecyclerView.ViewHolder{

        public TextView carrito;
        public TextView especie;
        public TextView subtalla;
        public TextView barco;
        public TextView calidad;
        public TextView temperatura;

        public CarritoCocidaViewHolder(View vista){
            super(vista);
            this.carrito = vista.findViewById(R.id.carrito);
            this.especie = vista.findViewById(R.id.especie);
            this.subtalla = vista.findViewById(R.id.subtalla);
            this.barco = vista.findViewById(R.id.barco);
            this.calidad = vista.findViewById(R.id.calidad);
            this.temperatura = vista.findViewById(R.id.temperatura);

            vista.setClickable(true);
        }
    }
}

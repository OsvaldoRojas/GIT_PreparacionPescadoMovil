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

public class AdaptadorDetalleModulo extends RecyclerView.Adapter<AdaptadorDetalleModulo.CarritoModuloViewHolder> {

    private List<Carrito> listaCarritos;

    public AdaptadorDetalleModulo(List<Carrito> listaCarritos){
        this.listaCarritos = listaCarritos;
    }

    @NonNull
    @Override
    public CarritoModuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.item_detalle_modulo, parent, false);
        return new CarritoModuloViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoModuloViewHolder holder, int position) {
        holder.turno.setText( String.valueOf( this.listaCarritos.get(position).getTurnoMP() == 0 ? 1 : 2 ) );
        holder.carrito.setText( this.listaCarritos.get(position).getDescripcion() );
        holder.especie.setText( this.listaCarritos.get(position).getEspecie() );
        holder.subtalla.setText( this.listaCarritos.get(position).getSubtalla() );
        holder.cocedor.setText( this.listaCarritos.get(position).getCocedor() );
        holder.fecha.setText( this.listaCarritos.get(position).getFecha().substring(0, 10) );
        holder.hora.setText( this.listaCarritos.get(position).getFecha().substring(11, 16) );
        holder.temperatura.setText( String.valueOf( this.listaCarritos.get(position).getTemperatura() ).concat("°") );
        holder.barco.setText("");
        if( this.listaCarritos.get(position).getMovimientos() != null && !this.listaCarritos.get(position).getMovimientos().isEmpty() ){
            if( this.listaCarritos.get(position).getMovimientos().size() == 1 ){
                holder.barco.setText( this.listaCarritos.get(position).getMovimientos().get(0).getBarco() );
            }else{
                holder.barco.setText( this.listaCarritos.get(position).getMovimientos().get(0).getBarco().concat("...") );
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.listaCarritos.size();
    }

    public static class CarritoModuloViewHolder extends RecyclerView.ViewHolder{

        public TextView turno;
        public TextView carrito;
        public TextView especie;
        public TextView subtalla;
        public TextView barco;
        public TextView cocedor;
        public TextView temperatura;
        public TextView fecha;
        public TextView hora;

        public CarritoModuloViewHolder(View vista){
            super(vista);
            this.turno = vista.findViewById(R.id.turno);
            this.carrito = vista.findViewById(R.id.carrito);
            this.especie = vista.findViewById(R.id.especie);
            this.subtalla = vista.findViewById(R.id.subtalla);
            this.barco = vista.findViewById(R.id.barco);
            this.cocedor = vista.findViewById(R.id.cocedor);
            this.temperatura = vista.findViewById(R.id.temperatura);
            this.fecha = vista.findViewById(R.id.fecha);
            this.hora = vista.findViewById(R.id.hora);

            vista.setClickable(true);
        }
    }
}

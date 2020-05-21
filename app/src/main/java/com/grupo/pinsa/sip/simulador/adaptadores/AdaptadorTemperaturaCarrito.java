package com.grupo.pinsa.sip.simulador.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.temperatura.Fragment_Temperatura_Carrito;
import com.grupo.pinsa.sip.simulador.modelo.Carrito;

import java.util.List;

public class AdaptadorTemperaturaCarrito extends RecyclerView.Adapter<AdaptadorTemperaturaCarrito.TemperaturaViewHolder>{

    private List<Carrito> listaCarritos;
    private Fragment pantalla;

    public AdaptadorTemperaturaCarrito(List<Carrito> listaCarritos, Fragment pantalla){
        this.listaCarritos = listaCarritos;
        this.pantalla = pantalla;
    }

    @NonNull
    @Override
    public TemperaturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.card_temperatura_carrito, parent, false);
        return new TemperaturaViewHolder(vista, this.pantalla, this.listaCarritos);
    }

    @Override
    public void onBindViewHolder(@NonNull TemperaturaViewHolder holder, int position) {
        Carrito carrito = this.listaCarritos.get(position);
        holder.temperatura.setText( String.valueOf( carrito.getTemperatura() ) );
        holder.carrito.setText( carrito.getDescripcion() );
        holder.especie.setText( carrito.getEspecie() );
        holder.subtalla.setText( carrito.getSubtalla() );
        if( !carrito.isSeleccionado() ){
            holder.temperatura.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.carrito.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaCarrito.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.especie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.subtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorAccent) );
        }else{
            holder.temperatura.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.carrito.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaCarrito.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.especie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.subtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorPrimary) );
        }
        carrito.setSeleccionadoGeneral( ( (Fragment_Temperatura_Carrito) pantalla).getSeleccionar() );
    }

    @Override
    public int getItemCount() {
        return this.listaCarritos.size();
    }

    public static class TemperaturaViewHolder extends RecyclerView.ViewHolder{

        public TextView temperatura;
        public TextView carrito;
        public TextView especie;
        public TextView subtalla;
        public TextView etiquetaCarrito;
        public TextView etiquetaEspecie;
        public TextView etiquetaSubtalla;
        public RelativeLayout fondo;

        public TemperaturaViewHolder(View vista, final Fragment pantalla, final List<Carrito> listaCarritos){
            super(vista);
            this.temperatura = vista.findViewById(R.id.temperatura);
            this.carrito = vista.findViewById(R.id.carrito);
            this.etiquetaCarrito = vista.findViewById(R.id.etiquetaCarrito);
            this.especie = vista.findViewById(R.id.especie);
            this.etiquetaEspecie = vista.findViewById(R.id.etiquetaEspecie);
            this.subtalla = vista.findViewById(R.id.subtalla);
            this.etiquetaSubtalla = vista.findViewById(R.id.etiquetaSubtalla);
            this.fondo = vista.findViewById(R.id.fondo);

            vista.setClickable(true);
            vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( ( (Fragment_Temperatura_Carrito) pantalla).libre ){
                        Carrito carritoSeleccionado = listaCarritos.get( getLayoutPosition() );
                        if( carritoSeleccionado.isSeleccionado() ){
                            temperatura.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            carrito.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            etiquetaCarrito.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            especie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            subtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorAccent) );
                            carritoSeleccionado.setSeleccionado(false);
                            carritoSeleccionado.setSeleccionadoSuma(false);
                        }else{
                            temperatura.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            carrito.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaCarrito.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            especie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            subtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            carritoSeleccionado.setSeleccionado(true);
                            carritoSeleccionado.setSeleccionadoSuma(true);
                        }
                    }
                }
            });
        }
    }
}

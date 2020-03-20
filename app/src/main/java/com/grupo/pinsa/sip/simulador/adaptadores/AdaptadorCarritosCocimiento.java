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
import com.grupo.pinsa.sip.simulador.cocimiento.Fragment_Carga_Carritos;
import com.grupo.pinsa.sip.simulador.vista.Carrito;

import java.util.List;

public class AdaptadorCarritosCocimiento extends RecyclerView.Adapter<AdaptadorCarritosCocimiento.CarritosCocimientoViewHolder>{

    private List<Carrito> listaCarritos;
    private Fragment pantalla;

    public AdaptadorCarritosCocimiento(List<Carrito> listaCarritos, Fragment pantalla){
        this.listaCarritos = listaCarritos;
        this.pantalla = pantalla;
    }

    @NonNull
    @Override
    public CarritosCocimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.card_cocimiento_carrito, parent, false);
        return new CarritosCocimientoViewHolder(vista, this.pantalla, this.listaCarritos);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritosCocimientoViewHolder holder, int position) {
        Carrito carrito = this.listaCarritos.get(position);
        holder.carrito.setText( carrito.getDescripcion() );
        holder.especie.setText( carrito.getEspecie() );
        holder.subtalla.setText( carrito.getSubtalla() );
        if( !carrito.isSeleccionado() ){
            holder.carrito.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaCarrito.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.especie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.subtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorAccent) );
        }else{
            holder.carrito.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaCarrito.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.especie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.subtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorPrimary) );
        }
        carrito.setSeleccionadoGeneral( ( (Fragment_Carga_Carritos) pantalla).getSeleccionar() );
    }

    @Override
    public int getItemCount() {
        return this.listaCarritos.size();
    }

    public static class CarritosCocimientoViewHolder extends RecyclerView.ViewHolder{

        public TextView carrito;
        public TextView etiquetaCarrito;
        public TextView especie;
        public TextView etiquetaEspecie;
        public TextView subtalla;
        public TextView etiquetaSubtalla;
        public RelativeLayout fondo;

        public CarritosCocimientoViewHolder(View vista, final Fragment pantalla, final List<Carrito> listaCarritos){
            super(vista);
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
                    Carrito carritoSeleccionado = listaCarritos.get( getLayoutPosition() );
                    if( carritoSeleccionado.isSeleccionado() ){
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
                    ( (Fragment_Carga_Carritos) pantalla).actualizaTotal();
                }
            });
        }
    }
}

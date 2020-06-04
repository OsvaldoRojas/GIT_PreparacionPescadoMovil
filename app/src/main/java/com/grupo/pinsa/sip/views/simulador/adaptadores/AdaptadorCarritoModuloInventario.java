package com.grupo.pinsa.sip.views.simulador.adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.modelo.Carrito;
import com.grupo.pinsa.sip.views.simulador.modulos.ActividadCarritosInventario;

import java.util.List;

public class AdaptadorCarritoModuloInventario extends RecyclerView.Adapter<AdaptadorCarritoModuloInventario.CarritoModuloViewHolder> {

    private List<Carrito> listaCarritos;
    private Activity pantalla;

    public AdaptadorCarritoModuloInventario(List<Carrito> listaCarritos, Activity pantalla){
        this.listaCarritos = listaCarritos;
        this.pantalla = pantalla;
    }

    @NonNull
    @Override
    public CarritoModuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.card_modulo_carrito, parent, false);
        return new CarritoModuloViewHolder(vista, this.pantalla, this.listaCarritos);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoModuloViewHolder holder, int position) {
        Carrito carrito = this.listaCarritos.get(position);
        holder.carrito.setText( carrito.getDescripcion() );
        holder.peso.setText( String.valueOf( carrito.getPeso() ).concat("kg") );
        holder.especie.setText( carrito.getEspecie() );
        holder.subtalla.setText( carrito.getSubtalla() );
        holder.hora.setText( carrito.getFecha().substring(11, 16) );
        holder.especialidad.setText( carrito.getEspecialidad() );
        holder.temperatura.setText( String.valueOf( carrito.getTemperatura() ).concat("°C") );
        holder.barco.setText("");
        if( carrito.getMovimientos() != null && !carrito.getMovimientos().isEmpty() ){
            if( carrito.getMovimientos().size() == 1 ){
                holder.barco.setText( carrito.getMovimientos().get(0).getBarco() );
            }else{
                holder.barco.setText( carrito.getMovimientos().get(0).getBarco().concat("...") );
            }
        }
        if( !carrito.isSeleccionado() ){
            holder.carrito.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaCarrito.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaPeso.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.peso.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.especie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.subtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaBarco.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.barco.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaHora.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.hora.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaEspecialidad.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.especialidad.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaTemperatura.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.temperatura.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorAccent) );
        }else{
            holder.carrito.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaCarrito.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaPeso.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.peso.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.especie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.subtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaBarco.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.barco.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaHora.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.hora.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaEspecialidad.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.especialidad.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaTemperatura.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.temperatura.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorPrimary) );
        }
    }

    @Override
    public int getItemCount() {
        return this.listaCarritos.size();
    }

    public static class CarritoModuloViewHolder extends RecyclerView.ViewHolder{

        public TextView carrito;
        public TextView etiquetaCarrito;
        public TextView etiquetaPeso;
        public TextView peso;
        public TextView especie;
        public TextView etiquetaEspecie;
        public TextView subtalla;
        public TextView etiquetaSubtalla;
        public TextView etiquetaBarco;
        public TextView barco;
        public TextView etiquetaHora;
        public TextView hora;
        public TextView etiquetaEspecialidad;
        public TextView especialidad;
        public TextView etiquetaTemperatura;
        public TextView temperatura;
        public RelativeLayout fondo;

        public CarritoModuloViewHolder(View vista, final Activity pantalla, final List<Carrito> listaCarritos){
            super(vista);
            this.carrito = vista.findViewById(R.id.carrito);
            this.etiquetaCarrito = vista.findViewById(R.id.etiquetaCarrito);
            this.etiquetaPeso = vista.findViewById(R.id.etiquetaPeso);
            this.peso = vista.findViewById(R.id.peso);
            this.especie = vista.findViewById(R.id.especie);
            this.etiquetaEspecie = vista.findViewById(R.id.etiquetaEspecie);
            this.subtalla = vista.findViewById(R.id.subtalla);
            this.etiquetaSubtalla = vista.findViewById(R.id.etiquetaSubtalla);
            this.etiquetaBarco = vista.findViewById(R.id.etiquetaBarco);
            this.barco = vista.findViewById(R.id.barco);
            this.etiquetaHora = vista.findViewById(R.id.etiquetaHora);
            this.hora = vista.findViewById(R.id.hora);
            this.etiquetaEspecialidad = vista.findViewById(R.id.etiquetaEspecialidad);
            this.especialidad = vista.findViewById(R.id.especialidad);
            this.etiquetaTemperatura = vista.findViewById(R.id.etiquetaTemperatura);
            this.temperatura = vista.findViewById(R.id.temperatura);
            this.fondo = vista.findViewById(R.id.fondo);

            vista.setClickable(true);
            vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Carrito carritoSeleccionado = listaCarritos.get( getLayoutPosition() );
                    if( carritoSeleccionado.isSeleccionado() ){
                        carrito.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        etiquetaCarrito.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        etiquetaPeso.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        peso.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        especie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        subtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        etiquetaBarco.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        barco.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        etiquetaHora.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        hora.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        etiquetaEspecialidad.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        especialidad.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        etiquetaTemperatura.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        temperatura.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                        fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorAccent) );
                        carritoSeleccionado.setSeleccionado(false);
                        carritoSeleccionado.setSeleccionadoSuma(false);
                    }else{
                        if( ( (ActividadCarritosInventario) pantalla).totalCarritos < ( (ActividadCarritosInventario) pantalla).capacidadTotal ){
                            carrito.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaCarrito.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaPeso.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            peso.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            especie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            subtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaBarco.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            barco.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaHora.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            hora.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaEspecialidad.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            especialidad.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaTemperatura.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            temperatura.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            carritoSeleccionado.setSeleccionado(true);
                            carritoSeleccionado.setSeleccionadoSuma(true);
                        }
                    }
                    ( (ActividadCarritosInventario) pantalla).actualizaTotal();
                }
            });
        }
    }
}

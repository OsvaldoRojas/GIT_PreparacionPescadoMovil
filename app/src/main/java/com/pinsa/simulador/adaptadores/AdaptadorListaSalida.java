package com.pinsa.simulador.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pinsa.simulador.R;
import com.pinsa.simulador.descongelado.Fragment_Descongelado_SalidaTinas;
import com.pinsa.simulador.vista.SalidaTina;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdaptadorListaSalida extends RecyclerView.Adapter<AdaptadorListaSalida.SalidaTinaViewHolder>{

    private List<SalidaTina> listaTinas;
    private List<SalidaTina> listaRespaldo = new ArrayList<>();
    private Fragment pantalla;

    public static class SalidaTinaViewHolder extends RecyclerView.ViewHolder{
        public TextView tina;
        public TextView posicion;
        public TextView subtalla;
        public TextView hora;
        public ImageView accion;

        public SalidaTinaViewHolder(View vista){
            super(vista);
            this.tina = vista.findViewById(R.id.tina);
            this.posicion = vista.findViewById(R.id.posicion);
            this.hora = vista.findViewById(R.id.hora);
            this.subtalla = vista.findViewById(R.id.subtalla);
            this.accion = vista.findViewById(R.id.accion);
        }
    }

    public AdaptadorListaSalida(List<SalidaTina> listaTinas, Fragment pantalla){
        this.pantalla = pantalla;
        this.listaTinas = listaTinas;
        this.listaRespaldo.addAll(this.listaTinas);
    }

    @NonNull
    @Override
    public SalidaTinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.card_salida_tina, parent, false);
        return new SalidaTinaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull SalidaTinaViewHolder holder, final int position) {
        holder.tina.setText( this.listaTinas.get(position).getTina() );
        holder.posicion.setText( this.listaTinas.get(position).getPosicion() );
        holder.subtalla.setText( this.listaTinas.get(position).getSubtalla() );
        holder.hora.setText( this.listaTinas.get(position).getHora() );
        if( this.listaTinas.get(position).getMarcado() ){
            holder.accion.setImageDrawable( this.pantalla.getContext().getDrawable(R.drawable.ic_eliminar) );
        }else{
            holder.accion.setImageDrawable( this.pantalla.getContext().getDrawable(R.drawable.ic_salida) );
        }
        holder.accion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( listaTinas.get(position).getMarcado() ){
                    ( (Fragment_Descongelado_SalidaTinas) pantalla).guardaCambio(
                            listaTinas.get(position).getTina(),
                            false,
                            true
                    );
                }else{
                    ( (Fragment_Descongelado_SalidaTinas) pantalla).guardaCambio(
                            listaTinas.get(position).getTina(),
                            true,
                            true
                    );
                }
            }
        });
    }

    public void filtro(String textoBusqueda){
        textoBusqueda = textoBusqueda.toLowerCase( Locale.getDefault() );
        this.listaTinas.clear();

        if( textoBusqueda.length() == 0 ){
            this.listaTinas.addAll(this.listaRespaldo);
        }else{
            for( SalidaTina tina : this.listaRespaldo ){
                if( tina.getTina() != null && tina.getTina().contains(textoBusqueda) ){
                    this.listaTinas.add(tina);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.listaTinas.size();
    }
}

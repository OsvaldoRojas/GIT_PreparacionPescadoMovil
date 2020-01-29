package com.example.simulador.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simulador.R;
import com.example.simulador.vista.SalidaTina;

import java.util.List;

public class AdaptadorListaSalida extends RecyclerView.Adapter<AdaptadorListaSalida.SalidaTinaViewHolder>{

    private List<SalidaTina> listaTinas;

    public static class SalidaTinaViewHolder extends RecyclerView.ViewHolder{
        public TextView tina;
        public TextView posicion;
        public TextView hora;
        public ImageView eliminar;

        public SalidaTinaViewHolder(View vista){
            super(vista);
            this.tina = vista.findViewById(R.id.tina);
            this.posicion = vista.findViewById(R.id.posicion);
            this.hora = vista.findViewById(R.id.hora);
            this.eliminar = vista.findViewById(R.id.eliminar);
        }
    }

    public AdaptadorListaSalida(List<SalidaTina> listaTinas){
        this.listaTinas = listaTinas;
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
        holder.hora.setText( this.listaTinas.get(position).getHora() );
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println( "Se elimino: " + listaTinas.get(position).getTina() );
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listaTinas.size();
    }
}

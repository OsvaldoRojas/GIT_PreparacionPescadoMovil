package com.grupo.pinsa.sip.simulador.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.modelo.DetalleSimple;

import java.util.List;

public class AdaptadorDetalleSimple extends RecyclerView.Adapter<AdaptadorDetalleSimple.DetalleSimpleViewHolder> {

    private List<DetalleSimple> listaDetalle;

    public AdaptadorDetalleSimple(List<DetalleSimple> listaDetalle){
        this.listaDetalle = listaDetalle;
    }

    @NonNull
    @Override
    public DetalleSimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.item_detalle_simple, parent, false);
        return new DetalleSimpleViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull DetalleSimpleViewHolder holder, int position) {
        holder.llave.setText( this.listaDetalle.get(position).getLlave() );
        holder.valor.setText( this.listaDetalle.get(position).getValor() );
    }

    @Override
    public int getItemCount() {
        return this.listaDetalle.size();
    }

    public static class DetalleSimpleViewHolder extends RecyclerView.ViewHolder{

        public TextView llave;
        public TextView valor;

        public DetalleSimpleViewHolder(View vista){
            super(vista);
            this.llave = vista.findViewById(R.id.llave);
            this.valor = vista.findViewById(R.id.valor);

            vista.setClickable(true);
        }
    }
}

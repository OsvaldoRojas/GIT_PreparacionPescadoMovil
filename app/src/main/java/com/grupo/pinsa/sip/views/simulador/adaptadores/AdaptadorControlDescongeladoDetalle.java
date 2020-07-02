package com.grupo.pinsa.sip.views.simulador.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.modelo.ControlDescongelado;

import java.util.List;

public class AdaptadorControlDescongeladoDetalle extends RecyclerView.Adapter<AdaptadorControlDescongeladoDetalle.ControlDescongeladoDetalleViewHolder> {

    private List<ControlDescongelado> listaControl;

    public AdaptadorControlDescongeladoDetalle(List<ControlDescongelado> listaControl){
        this.listaControl = listaControl;
    }

    @NonNull
    @Override
    public ControlDescongeladoDetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.item_detalle_control, parent, false);
        return new AdaptadorControlDescongeladoDetalle.ControlDescongeladoDetalleViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ControlDescongeladoDetalleViewHolder holder, int position) {
        holder.hora.setText( this.listaControl.get(position).getHora().substring(0, 5) );
        holder.concentradoCloro.setText( String.valueOf( this.listaControl.get(position).getConcentrado() ) );
        holder.temperaturaAgua.setText( String.valueOf( this.listaControl.get(position).getTemperatura() ) );
        holder.consumoCloro.setText( String.valueOf( this.listaControl.get(position).getConsumoCloro() ) );
        holder.antiespumante.setText( String.valueOf( this.listaControl.get(position).getAntiespumante() ) );
    }

    @Override
    public int getItemCount() {
        return this.listaControl.size();
    }

    public static class ControlDescongeladoDetalleViewHolder extends RecyclerView.ViewHolder{

        public TextView hora;
        public TextView concentradoCloro;
        public TextView temperaturaAgua;
        public TextView consumoCloro;
        public TextView antiespumante;

        public ControlDescongeladoDetalleViewHolder(View vista){
            super(vista);
            this.hora = vista.findViewById(R.id.hora);
            this.concentradoCloro = vista.findViewById(R.id.concentradoCloro);
            this.temperaturaAgua = vista.findViewById(R.id.temperaturaAgua);
            this.consumoCloro = vista.findViewById(R.id.consumoCloro);
            this.antiespumante = vista.findViewById(R.id.antiespumante);

            vista.setClickable(true);
        }
    }
}

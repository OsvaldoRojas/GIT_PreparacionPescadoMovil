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
import com.grupo.pinsa.sip.simulador.temperatura.Fragment_Temperatura_Tina;
import com.grupo.pinsa.sip.simulador.modelo.TemperaturaTina;

import java.util.List;

public class AdaptadorTemperaturaTina extends RecyclerView.Adapter<AdaptadorTemperaturaTina.TemperaturaViewHolder>{

    private List<TemperaturaTina> listaTinas;
    private Fragment pantalla;

    public AdaptadorTemperaturaTina(List<TemperaturaTina> listaTinas, Fragment pantalla){
        this.listaTinas = listaTinas;
        this.pantalla = pantalla;
    }

    @NonNull
    @Override
    public TemperaturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.card_temperatura_tina, parent, false);
        return new TemperaturaViewHolder(vista, this.pantalla, this.listaTinas);
    }

    @Override
    public void onBindViewHolder(@NonNull TemperaturaViewHolder holder, int position) {
        TemperaturaTina tina = this.listaTinas.get(position);
        holder.temperatura.setText( String.valueOf( tina.getTemperatura() ) );
        holder.tina.setText( tina.getDescripcion() );
        holder.especie.setText( tina.getEspecie() );
        holder.subtalla.setText( tina.getSubtalla() );
        if( !tina.isSeleccionado() ){
            holder.temperatura.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.tina.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaTina.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.especie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.subtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
            holder.fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorAccent) );
        }else{
            holder.temperatura.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.tina.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaTina.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.especie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.subtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
            holder.fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorPrimary) );
        }
        tina.setSeleccionadoGeneral( ( (Fragment_Temperatura_Tina) pantalla).getSeleccionar() );
    }

    @Override
    public int getItemCount() {
        return this.listaTinas.size();
    }

    public static class TemperaturaViewHolder extends RecyclerView.ViewHolder{

        public TextView temperatura;
        public TextView tina;
        public TextView especie;
        public TextView subtalla;
        public TextView etiquetaTina;
        public TextView etiquetaEspecie;
        public TextView etiquetaSubtalla;
        public RelativeLayout fondo;

        public TemperaturaViewHolder(View vista, final Fragment pantalla, final List<TemperaturaTina> listaTinas){
            super(vista);
            this.temperatura = vista.findViewById(R.id.temperatura);
            this.tina = vista.findViewById(R.id.tina);
            this.etiquetaTina = vista.findViewById(R.id.etiquetaTina);
            this.especie = vista.findViewById(R.id.especie);
            this.etiquetaEspecie = vista.findViewById(R.id.etiquetaEspecie);
            this.subtalla = vista.findViewById(R.id.subtalla);
            this.etiquetaSubtalla = vista.findViewById(R.id.etiquetaSubtalla);
            this.fondo = vista.findViewById(R.id.fondo);

            vista.setClickable(true);
            vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( ( (Fragment_Temperatura_Tina) pantalla).libre ){
                        TemperaturaTina tinaSeleccionada = listaTinas.get( getLayoutPosition() );
                        if( tinaSeleccionada.isSeleccionado() ){
                            temperatura.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            tina.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            etiquetaTina.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            especie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            subtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorAccent) );
                            tinaSeleccionada.setSeleccionado(false);
                            tinaSeleccionada.setSeleccionadoSuma(false);
                        }else{
                            temperatura.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            tina.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaTina.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            especie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaEspecie.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            subtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            etiquetaSubtalla.setTextColor( pantalla.getResources().getColor(R.color.blanco) );
                            fondo.setBackgroundColor( pantalla.getResources().getColor(R.color.colorPrimary) );
                            tinaSeleccionada.setSeleccionado(true);
                            tinaSeleccionada.setSeleccionadoSuma(true);
                        }
                    }
                }
            });
        }
    }
}

package com.grupo.pinsa.sip.views.simulador.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.modelo.Cocida;
import com.grupo.pinsa.sip.views.simulador.preselecion.Fragment_Asigna_Tina_Cocida;

import java.util.List;

public class AdaptadorCocida extends RecyclerView.Adapter<AdaptadorCocida.CocidaViewHolder> {

    private List<Cocida> listaCocida;
    private Fragment fragment;
    public static CocidaViewHolder anterior;
    public static Cocida cocidaAnterior;

    public AdaptadorCocida(List<Cocida> listaCocida, Fragment fragment){
        this.listaCocida = listaCocida;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public CocidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.card_asignar_cocida, parent, false);
        return new CocidaViewHolder(vista, listaCocida, fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull CocidaViewHolder holder, int position) {
        holder.talla.setText( this.listaCocida.get(position).getTalla().getDescripcion() );
        holder.subTalla.setText( this.listaCocida.get(position).getSubtalla().getDescripcion() );
        holder.especie.setText( this.listaCocida.get(position).getEspecie().getDescripcion() );
        holder.especialidad.setText( this.listaCocida.get(position).getEspecialiad().getDescripcion() );
        holder.turno.setText( this.listaCocida.get(position).isTurno() ? "Turno 2" : "Turno 1" );
        String cantidad = this.listaCocida.get(position).getTinasPreseleccionadas() + "/" +
                this.listaCocida.get(position).getTinasSimulador();
        holder.cantidadTina.setText(cantidad);
    }

    @Override
    public int getItemCount() {
        return this.listaCocida.size();
    }

    public static class CocidaViewHolder extends RecyclerView.ViewHolder{
        public TextView talla;
        public TextView subTalla;
        public TextView especie;
        public TextView especialidad;
        public TextView turno;
        public TextView cantidadTina;
        public RelativeLayout fondo;

        public CocidaViewHolder(final View vista, final List<Cocida> listaCocida, final Fragment fragment){
            super(vista);
            this.talla = vista.findViewById(R.id.talla);
            this.subTalla = vista.findViewById(R.id.subtalla);
            this.especie = vista.findViewById(R.id.especie);
            this.especialidad = vista.findViewById(R.id.especialidad);
            this.turno = vista.findViewById(R.id.turno);
            this.cantidadTina = vista.findViewById(R.id.cantidadTinas);
            this.fondo = vista.findViewById(R.id.fondo);

            vista.setClickable(true);
            vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( ( (Fragment_Asigna_Tina_Cocida) fragment ).getNuevo() ){
                        Cocida cocida = listaCocida.get( getLayoutPosition() );
                        if( cocida.isSeleccionado() ){
                            cocida.setSeleccionado(false);
                            talla.setTextColor( fragment.getResources().getColor(R.color.colorPrimary) );
                            subTalla.setTextColor( fragment.getResources().getColor(R.color.colorPrimary) );
                            especie.setTextColor( fragment.getResources().getColor(R.color.colorPrimary) );
                            especialidad.setTextColor( fragment.getResources().getColor(R.color.colorPrimary) );
                            turno.setTextColor( fragment.getResources().getColor(R.color.colorPrimary) );
                            cantidadTina.setTextColor( fragment.getResources().getColor(R.color.colorPrimary) );
                            fondo.setBackgroundColor( fragment.getResources().getColor(R.color.colorAccent) );
                            anterior = null;
                            cocidaAnterior = null;
                        }else{
                            if( !(anterior == null) ) {
                                anterior.talla.setTextColor( fragment.getResources().getColor(R.color.colorPrimary) );
                                anterior.subTalla.setTextColor( fragment.getResources().getColor(R.color.colorPrimary) );
                                anterior.especie.setTextColor( fragment.getResources().getColor(R.color.colorPrimary) );
                                anterior.especialidad.setTextColor( fragment.getResources().getColor(R.color.colorPrimary) );
                                anterior.turno.setTextColor( fragment.getResources().getColor(R.color.colorPrimary) );
                                anterior.cantidadTina.setTextColor( fragment.getResources().getColor(R.color.colorPrimary) );
                                anterior.fondo.setBackgroundColor( fragment.getResources().getColor(R.color.colorAccent) );
                            }

                            if( !(cocidaAnterior == null) ){
                                cocidaAnterior.setSeleccionado(false);
                            }

                            cocida.setSeleccionado(true);
                            talla.setTextColor( fragment.getResources().getColor(R.color.blanco) );
                            subTalla.setTextColor( fragment.getResources().getColor(R.color.blanco) );
                            especie.setTextColor( fragment.getResources().getColor(R.color.blanco) );
                            especialidad.setTextColor( fragment.getResources().getColor(R.color.blanco) );
                            turno.setTextColor( fragment.getResources().getColor(R.color.blanco) );
                            cantidadTina.setTextColor( fragment.getResources().getColor(R.color.blanco) );
                            fondo.setBackgroundColor( fragment.getResources().getColor(R.color.colorPrimary) );
                            anterior = CocidaViewHolder.this;
                            cocidaAnterior = cocida;
                        }
                    }
                }
            });
        }
    }
}

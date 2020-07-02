package com.grupo.pinsa.sip.views.simulador.adaptadores;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.descongelado.Fragment_Descongelado_Control;
import com.grupo.pinsa.sip.views.simulador.modelo.ControlDescongelado;

import java.util.List;

public class AdaptadorControlDescongelado extends RecyclerView.Adapter<AdaptadorControlDescongelado.ControlDescongeladoViewHolder> {

    private List<ControlDescongelado> listaControl;
    private Fragment pantalla;

    public AdaptadorControlDescongelado(List<ControlDescongelado> listaControl, Fragment pantalla){
        this.pantalla = pantalla;
        this.listaControl = listaControl;
    }

    @NonNull
    @Override
    public ControlDescongeladoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.card_descongelado_control, parent, false);
        return new ControlDescongeladoViewHolder(vista, this.pantalla);
    }

    @Override
    public void onBindViewHolder(@NonNull ControlDescongeladoViewHolder holder, int position) {
        holder.fecha.setText( this.listaControl.get(position).getFecha().substring(0, 10) );
        holder.hora.setText( this.listaControl.get(position).getHora().substring(0, 5) );
        holder.clave.setText( this.listaControl.get(position).getClave() );
    }

    @Override
    public int getItemCount() {
        return this.listaControl.size();
    }

    public static class ControlDescongeladoViewHolder extends RecyclerView.ViewHolder{

        public TextView fecha;
        public TextView hora;
        public TextView clave;

        public ControlDescongeladoViewHolder(View vista, final Fragment pantalla){
            super(vista);
            this.fecha = vista.findViewById(R.id.fecha);
            this.hora = vista.findViewById(R.id.hora);
            this.clave = vista.findViewById(R.id.claveProceso);

            vista.setClickable(true);
            vista.setLongClickable(true);
            vista.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    PopupMenu menu = new PopupMenu(pantalla.getContext(), view);
                    menu.getMenuInflater().inflate( R.menu.menu_lista_control, menu.getMenu() );
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch ( item.getItemId() ){
                                case R.id.editar:
                                    ( (Fragment_Descongelado_Control) pantalla).edita( getLayoutPosition() );
                                    return true;
                                case R.id.eliminar:
                                    ( (Fragment_Descongelado_Control) pantalla).elimina( getLayoutPosition() );
                                    return true;
                                case R.id.detalle:
                                    ( (Fragment_Descongelado_Control) pantalla).detalle( getLayoutPosition() );
                                    return true;
                            }
                            return false;
                        }
                    });
                    if( ( (Fragment_Descongelado_Control) pantalla).validaFechaCaptura() ){
                        menu.getMenu().getItem(2).setVisible(false);
                    }else{
                        menu.getMenu().getItem(0).setVisible(false);
                        menu.getMenu().getItem(1).setVisible(false);
                    }
                    menu.show();
                    return true;
                }
            });
        }
    }
}

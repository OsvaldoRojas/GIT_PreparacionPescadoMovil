package com.example.simulador_pescado.adaptadores;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.clases.ParametrosRecycler;
import com.example.simulador_pescado.clases.Utilidades;

import java.util.ArrayList;

public class AdaptadorRecycler
        extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolderAdaptadorRecycler>implements View.OnClickListener {

    ArrayList<ParametrosRecycler> listaPersonajes;
    View.OnClickListener listener;


    public AdaptadorRecycler(ArrayList<ParametrosRecycler> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

    @Override
    public ViewHolderAdaptadorRecycler onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout=0;
        layout= R.layout.recicler_view_plantilla;
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,null,false);
            view.setOnClickListener(this);

        return new ViewHolderAdaptadorRecycler(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAdaptadorRecycler holder, int position) {
        holder.folio.setText(listaPersonajes.get(position).getFolio());
        holder.fecha.setText(listaPersonajes.get(position).getFecha());
        holder.artefacto.setText(listaPersonajes.get(position).getArterfacto());
        holder.mecanico.setText(listaPersonajes.get(position).getMecanico());

        //nuevo codigo


    }

    @Override
    public int getItemCount() {
        return listaPersonajes.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!= null){
            listener.onClick(view);
        }
    }

    public class ViewHolderAdaptadorRecycler extends RecyclerView.ViewHolder {
        TextView folio,fecha,artefacto,mecanico;
        public ViewHolderAdaptadorRecycler(@NonNull View itemView) {
            super(itemView);
            folio=itemView.findViewById(R.id.recycler_Folio);
            fecha=itemView.findViewById(R.id.recycler_Fecha);
            artefacto=itemView.findViewById(R.id.recycler_Artefacto);
            mecanico=itemView.findViewById(R.id.recycler_Mecanico);

        }
    }

/*
    public class ViewHolderAdaptadorRecycler extends RecyclerView.ViewHolder {

        TextView etiNombre,etiInformacion;
        ImageView foto;

        public ViewHolderAdaptadorRecycler(View itemView) {
            super(itemView);
            etiNombre=itemView.findViewById(R.id.nombre);
            if (Utilidades.posision==Utilidades.Layout) {
                etiInformacion= itemView.findViewById(R.id.infoPersonaje);
            }
            foto= itemView.findViewById(R.id.idImagen);
        }
    }*/

    @Override
    public void onViewRecycled(@NonNull ViewHolderAdaptadorRecycler holder) {
        super.onViewRecycled(holder);
    }
}

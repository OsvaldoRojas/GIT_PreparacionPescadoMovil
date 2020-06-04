package com.grupo.pinsa.sip.views.simulador.modulos;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.TooltipCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.contenedores.Contenedor_Modulos;
import com.grupo.pinsa.sip.views.simulador.modelo.Carrito;
import com.grupo.pinsa.sip.views.simulador.modelo.Modulo;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Vista_Modulo extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private View vista;
    private AlertDialog ventanaError;
    private ProgressBar barraProgreso;
    private SwipeRefreshLayout actualizar;

    private Modulo moduloSeleccionado;

    public Modulo getModuloSeleccionado() {
        return moduloSeleccionado;
    }

    public void setModuloSeleccionado(Modulo moduloSeleccionado) {
        this.moduloSeleccionado = moduloSeleccionado;
    }

    private OnFragmentInteractionListener mListener;

    public Fragment_Vista_Modulo() {
    }

    public static Fragment_Vista_Modulo newInstance(Serializable param1) {
        Fragment_Vista_Modulo fragment = new Fragment_Vista_Modulo();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setModuloSeleccionado( (Modulo) getArguments().getSerializable(ARG_PARAM1) );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_vista_modulo, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

        this.actualizar = this.vista.findViewById(R.id.actualizar);
        this.actualizar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actualizar.setRefreshing(true);
                getCarritos();
            }
        });

        TextView etiquetaModulo = this.vista.findViewById(R.id.modulo);
        etiquetaModulo.setText( getModuloSeleccionado().getDescripcion() );

        Button botonCancelar = this.vista.findViewById(R.id.boton2);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor_Modulos().newInstance(0);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        Button botonSalidaCarritos = this.vista.findViewById(R.id.boton1);
        botonSalidaCarritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment_Salida_Carritos().newInstance( getModuloSeleccionado() );
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        FloatingActionButton botonEntradaManual = this.vista.findViewById(R.id.entradaManual);
        TooltipCompat.setTooltipText(botonEntradaManual, "Entrada manual");
        botonEntradaManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment_Entrada_Manual().newInstance( getModuloSeleccionado() );
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        FloatingActionButton botonEntradaInventario = this.vista.findViewById(R.id.entradaInventario);
        TooltipCompat.setTooltipText(botonEntradaInventario, "Entrada inventario");
        botonEntradaInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment_Entrada_Inventario().newInstance( getModuloSeleccionado() );
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        FloatingActionButton botonDetalle = this.vista.findViewById(R.id.detalle);
        TooltipCompat.setTooltipText(botonDetalle, "Detalle");
        botonDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment_Detalle_Modulo().newInstance( getModuloSeleccionado() );
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        getCarritos();
    }

    private void dibujaModulo(){
        int posicionesInhabilitadas = getModuloSeleccionado().getCapacidadMaxima() - getModuloSeleccionado().getCapacidadActual();
        int posicionesDibujadas = 0;
        int posicionesTurno1 = posicionesInhabilitadas;
        int posicionesTurno2 = getModuloSeleccionado().getCarritos().size() + posicionesInhabilitadas;
        for( Carrito carrito : getModuloSeleccionado().getCarritos() ){
            if( carrito.getTurno() == 1 ){
                posicionesTurno1 = posicionesTurno1+1;
            }
        }
        LinearLayout posiciones = this.vista.findViewById(R.id.posiciones);
        posiciones.removeAllViews();
        for( int columna = 0; columna < getModuloSeleccionado().getColumna(); columna++  ){
            LinearLayout linearFila = new LinearLayout( getContext() );
            LinearLayout.LayoutParams layoutParams = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearFila.setLayoutParams(layoutParams);
            linearFila.setOrientation(LinearLayout.VERTICAL);
            posiciones.addView(linearFila);
            for( int fila = 0; fila < getModuloSeleccionado().getFila(); fila++ ){
                if( posicionesDibujadas == getModuloSeleccionado().getCapacidadMaxima() ){
                    terminaProcesando();
                    return;
                }
                ImageView imagen = new ImageView( getContext() );
                LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(70, 70);
                imagenParams.setMargins(1, 1, 1, 1 );
                imagen.setLayoutParams(imagenParams);
                imagen.setPadding(10, 10, 10, 10);

                if(posicionesDibujadas < posicionesInhabilitadas){
                    imagen.setImageResource(R.drawable.ic_block_modulo);
                    imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio ) );
                }else{
                    if(posicionesDibujadas < posicionesTurno1){
                        imagen.setImageResource(R.drawable.ic_pinsa_carritos);
                        imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_turno1 ) );
                    }else{
                        if(posicionesDibujadas < posicionesTurno2){
                            imagen.setImageResource(R.drawable.ic_pinsa_carrito_turno2);
                            imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_turno2 ) );
                        }else{
                            imagen.setImageResource(R.drawable.ic_pinsa_carrito_vacio);
                            imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio ) );
                        }
                    }
                }
                linearFila.addView(imagen);
                posicionesDibujadas = posicionesDibujadas+1;
            }
        }

        //
        float[] arregloTemperaturas = new float[]{35.6f,23.3f,40.5f};
        getModuloSeleccionado().setTemperaturas(arregloTemperaturas);
        //
        if( getModuloSeleccionado().getTemperaturas() != null ){
            LinearLayout temperaturas = this.vista.findViewById(R.id.temperaturas);
            temperaturas.removeAllViews();
            for(int posicion = 0; posicion < getModuloSeleccionado().getTemperaturas().length; posicion++){
                LinearLayout linearFila = new LinearLayout( getContext() );
                LinearLayout.LayoutParams layoutParams = new LinearLayout
                        .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                linearFila.setLayoutParams(layoutParams);
                linearFila.setGravity(Gravity.CENTER_VERTICAL);
                linearFila.setOrientation(LinearLayout.HORIZONTAL);
                temperaturas.addView(linearFila);

                TextView etiquetaTemperatura = new TextView( getContext() );
                etiquetaTemperatura.setText(
                        String.valueOf( getModuloSeleccionado().getTemperaturas()[posicion] ).concat("°C")
                );
                etiquetaTemperatura.setTextSize(12);
                etiquetaTemperatura.setPadding(0, 0, 5, 0);
                linearFila.addView(etiquetaTemperatura);

                ImageView imagen = new ImageView( getContext() );
                LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(50, 50);
                imagenParams.setMargins(1, 1, 1, 1 );
                imagen.setLayoutParams(imagenParams);
                imagen.setPadding(10, 10, 10, 10);
                imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio ) );
                if(getModuloSeleccionado().getTemperaturas()[posicion] <= 35){
                    imagen.setImageResource(R.drawable.ic_temperatura_modulo2);
                }else{
                    imagen.setImageResource(R.drawable.ic_temperatura_modulo1);
                }
                linearFila.addView(imagen);
            }
        }

        terminaProcesando();
    }

    private void getCarritos(){
        Call<List<Carrito>> llamadaServicio = APIServicios.getConexionAPPWEB().getCarritosModulo( getModuloSeleccionado().getId() );
        llamadaServicio.enqueue(new Callback<List<Carrito>>() {
            @Override
            public void onResponse(Call<List<Carrito>> call, Response<List<Carrito>> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        getModuloSeleccionado().setCarritos( response.body() );
                        dibujaModulo();
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al obtener los carritos del módulo" );
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Carrito>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    public void errorServicio(final String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_mensaje_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaError = builder.create();
        this.ventanaError.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaError.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText(mensaje);

                Button botonAceptar = ventanaError.findViewById(R.id.boton1);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ventanaError.dismiss();
                    }
                });
            }
        });
        this.ventanaError.show();
    }

    public void iniciaProcesando(){
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        this.barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        if( this.actualizar.isRefreshing() ){
            this.actualizar.setRefreshing(false);
        }
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        this.barraProgreso.setVisibility(View.GONE);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

package com.grupo.pinsa.sip.views.simulador.modulos;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorDetalleModulo;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.modelo.Carrito;
import com.grupo.pinsa.sip.views.simulador.modelo.Modulo;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Detalle_Modulo extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View vista;
    private AlertDialog ventanaError;
    private ProgressBar barraProgreso;
    private SwipeRefreshLayout actualizar;

    private boolean vistaPanoramica = false;

    private Modulo moduloSeleccionado;

    public Modulo getModuloSeleccionado() {
        return moduloSeleccionado;
    }

    public void setModuloSeleccionado(Modulo moduloSeleccionado) {
        this.moduloSeleccionado = moduloSeleccionado;
    }

    private OnFragmentInteractionListener mListener;

    public Fragment_Detalle_Modulo() {
    }

    public static Fragment_Detalle_Modulo newInstance(Serializable param1, boolean vistaPanoramica) {
        Fragment_Detalle_Modulo fragment = new Fragment_Detalle_Modulo();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putBoolean("vistaPanoramica", vistaPanoramica);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setModuloSeleccionado( (Modulo) getArguments().getSerializable(ARG_PARAM1) );
            this.vistaPanoramica = getArguments().getBoolean("vistaPanoramica");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_detalle_modulo, container, false);
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

        Button botonCancelar = this.vista.findViewById(R.id.botonVolver);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vistaPanoramica){
                    Fragment fragment = new Fragment_Vista_Panoramica();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
                }else{
                    Fragment fragment = new Fragment_Vista_Modulo().newInstance( getModuloSeleccionado() );
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
                }
            }
        });

        muestraCarritos();
    }

    private void muestraCarritos(){
        RecyclerView vistaCarritos = this.vista.findViewById(R.id.listaCarritos);
        vistaCarritos.setHasFixedSize(true);

        LinearLayoutManager layoutManagerCarritos = new LinearLayoutManager( getContext() );
        vistaCarritos.setLayoutManager(layoutManagerCarritos);

        AdaptadorDetalleModulo AdaptadorDetalleModulo = new AdaptadorDetalleModulo( getModuloSeleccionado().getCarritos() );
        vistaCarritos.setAdapter(AdaptadorDetalleModulo);

        TextView campoCarritos = this.vista.findViewById(R.id.campoCarritos);
        campoCarritos.setText( getModuloSeleccionado().getCarritos().size() + " carritos" );

        int canastillas = 0;
        for( Carrito carrito : getModuloSeleccionado().getCarritos() ){
            canastillas = canastillas + carrito.getCanastillas();
        }

        TextView campoCanastillas = this.vista.findViewById(R.id.campoCanastillas);
        campoCanastillas.setText(canastillas + " canastillas");
        terminaProcesando();
    }

    private void getCarritos(){
        Call<List<Carrito>> llamadaServicio = APIServicios.getConexionAPPWEB().getCarritosModulo( getModuloSeleccionado().getId() );
        llamadaServicio.enqueue(new Callback<List<Carrito>>() {
            @Override
            public void onResponse(Call<List<Carrito>> call, Response<List<Carrito>> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        if( !response.body().isEmpty() ){
                            getModuloSeleccionado().setCarritos( response.body() );
                        }
                        muestraCarritos();
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

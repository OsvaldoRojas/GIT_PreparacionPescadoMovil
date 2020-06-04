package com.grupo.pinsa.sip.views.simulador.estatusCocedor;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorCocedor;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.contenedores.Contenedor_Cocida;
import com.grupo.pinsa.sip.views.simulador.modelo.Cocedor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Estatus extends Fragment {

    private View vista;

    private List<Cocedor> listaCocedores = new ArrayList<>();

    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private ProgressBar barraProgreso;
    private SwipeRefreshLayout actualizar;

    private OnFragmentInteractionListener mListener;

    public Fragment_Estatus() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_estatus, container, false);
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
                getCocedores();
            }
        });

        getCocedores();
    }

    private void getCocedores(){
        Call<List<Cocedor>> llamadaServicio = APIServicios.getConexion().getCocedoresCocimiento();
        llamadaServicio.enqueue(new Callback<List<Cocedor>>() {
            @Override
            public void onResponse(Call<List<Cocedor>> call, Response<List<Cocedor>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        muestraCocedores( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener los cocedores");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Cocedor>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void muestraCocedores(List<Cocedor> cocedores){
        this.listaCocedores = new ArrayList<>();
        for(Cocedor cocedor : cocedores){
            if( cocedor.getCarritos() != null && !cocedor.getCarritos().isEmpty() ){
                cocedor.setEstatus("");
                cocedor.setTotalCarritos(
                        String.valueOf( cocedor.getCarritos().size() )
                        .concat("/")
                        .concat( String.valueOf( cocedor.getCapacidad() ) )
                );
                this.listaCocedores.add(cocedor);
            }
        }

        RecyclerView vistaLista = this.vista.findViewById(R.id.listaCocedores);
        vistaLista.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
        vistaLista.setLayoutManager(layoutManager);

        AdaptadorCocedor adaptador = new AdaptadorCocedor(this.listaCocedores, this);
        vistaLista.setAdapter(adaptador);
        terminaProcesando();
    }

    public void detalleCocida(final int posicion){
        iniciaProcesando();
        Call<List<Cocedor>> llamadaServicio = APIServicios.getConexionAPPWEB()
                .getDetalleCocidasCocedor( this.listaCocedores.get(posicion).getIdCocida() );
        llamadaServicio.enqueue(new Callback<List<Cocedor>>() {
            @Override
            public void onResponse(Call<List<Cocedor>> call, Response<List<Cocedor>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        if( !response.body().isEmpty() ){
                            for( Cocedor cocedor : response.body() ){
                                cocedor.setIdCocida( listaCocedores.get(posicion).getIdCocida() );
                            }
                            terminaProcesando();
                            Fragment fragment = new Contenedor_Cocida().newInstance( 0, (Serializable) response.body() );
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
                        }else{
                            terminaProcesando();
                            errorServicio("No se encontró información de detalle estatus del cocedor");
                        }
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener el detalle estatus del cocedor");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Cocedor>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    public void ventanaMensaje(final String mensaje){
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
        this.barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        if( this.actualizar.isRefreshing() ){
            this.actualizar.setRefreshing(false);
        }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

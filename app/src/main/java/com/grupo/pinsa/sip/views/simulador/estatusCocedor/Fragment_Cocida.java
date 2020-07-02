package com.grupo.pinsa.sip.views.simulador.estatusCocedor;

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

import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorCarritoCocida;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorDetalleSimple;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.contenedores.Contenedor_Cocida;
import com.grupo.pinsa.sip.views.simulador.utilerias.Constantes;
import com.grupo.pinsa.sip.views.simulador.modelo.Carrito;
import com.grupo.pinsa.sip.views.simulador.modelo.Cocedor;
import com.grupo.pinsa.sip.views.simulador.modelo.DetalleSimple;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Cocida extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private View vista;

    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private ProgressBar barraProgreso;
    private Button boton1;

    private Cocedor cocedorSeleccionado;

    private OnFragmentInteractionListener mListener;

    public Cocedor getCocedorSeleccionado() {
        return cocedorSeleccionado;
    }

    public void setCocedorSeleccionado(Cocedor cocedorSeleccionado) {
        this.cocedorSeleccionado = cocedorSeleccionado;
    }

    public Fragment_Cocida() {
    }

    public static Fragment_Cocida newInstance(Serializable param1) {
        Fragment_Cocida fragment = new Fragment_Cocida();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setCocedorSeleccionado( (Cocedor) getArguments().getSerializable(ARG_PARAM1) );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_cocida, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        iniciaProcesando();

        Button botonVolver = this.vista.findViewById(R.id.botonVolver);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment_Estatus();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        this.boton1 = this.vista.findViewById(R.id.boton1);
        if( !getCocedorSeleccionado().getCompensacion() ){
            if( getCocedorSeleccionado().getTotalCocidas() > 1 ){
                this.boton1.setVisibility(View.GONE);
            }else{
                this.boton1.setEnabled(false);
                if( getCocedorSeleccionado().getEstatus().equalsIgnoreCase( Constantes.ESTATUS_COCEDOR.procesoDescarga.getDescripcion() ) ){
                    String subtalla = getCocedorSeleccionado().getCarritos().get(0).getSubtalla();
                    for(Carrito carrito : getCocedorSeleccionado().getCarritos()){
                        if( !carrito.getSubtalla().equalsIgnoreCase(subtalla) ){
                            this.boton1.setEnabled(true);
                            break;
                        }
                    }
                }
                this.boton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iniciaProcesando();
                        generaCompensacion();
                    }
                });
            }
        }else{
            if( getCocedorSeleccionado().getIniciado() ){
                this.boton1.setVisibility(View.GONE);
            }else{
                this.boton1.setText( getResources().getString(R.string.iniciar) );
                this.boton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iniciaProcesando();

                        iniciaCocida();
                    }
                });
            }
        }

        List<DetalleSimple> listaCocedor = new ArrayList<>();
        DetalleSimple tamano = new DetalleSimple();
        tamano.setLlave("Descripción");
        tamano.setValor( getCocedorSeleccionado().getDescripcion() );
        listaCocedor.add(tamano);

        DetalleSimple capacidad = new DetalleSimple();
        capacidad.setLlave("Capacidad");
        capacidad.setValor( String.valueOf(getCocedorSeleccionado().getCapacidad()).concat(" carritos") );
        listaCocedor.add(capacidad);

        DetalleSimple numeroCocida = new DetalleSimple();
        numeroCocida.setLlave("Número de cocida");
        numeroCocida.setValor( String.valueOf(getCocedorSeleccionado().getNumeroCocida()) );
        listaCocedor.add(numeroCocida);

        RecyclerView vistaCocedor = this.vista.findViewById(R.id.datosCocedor);
        vistaCocedor.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
        vistaCocedor.setLayoutManager(layoutManager);

        AdaptadorDetalleSimple adaptadorCocedor = new AdaptadorDetalleSimple(listaCocedor);
        vistaCocedor.setAdapter(adaptadorCocedor);

        List<DetalleSimple> listaCocida = new ArrayList<>();
        DetalleSimple registro = new DetalleSimple();
        registro.setLlave("Número de registro");
        registro.setValor( getCocedorSeleccionado().getNumeroRegistro() );
        listaCocida.add(registro);

        DetalleSimple especie = new DetalleSimple();
        especie.setLlave("Especie");
        especie.setValor( getCocedorSeleccionado().getEspecie() );
        listaCocida.add(especie);

        DetalleSimple especialidad = new DetalleSimple();
        especialidad.setLlave("Especialidad");
        especialidad.setValor(getCocedorSeleccionado().getEspecialidad());
        listaCocida.add(especialidad);

        DetalleSimple fechaInicio = new DetalleSimple();
        fechaInicio.setLlave("Fecha de inicio");
        fechaInicio.setValor(getCocedorSeleccionado().getFechaInicio());
        listaCocida.add(fechaInicio);

        DetalleSimple horaInicio = new DetalleSimple();
        horaInicio.setLlave("Hora de inicio");
        horaInicio.setValor(getCocedorSeleccionado().getHoraInicio());
        listaCocida.add(horaInicio);

        DetalleSimple tiempoRestante = new DetalleSimple();
        tiempoRestante.setLlave("Tiempo restante");
        tiempoRestante.setValor(getCocedorSeleccionado().getTiempoRestante().substring(0, 5));
        listaCocida.add(tiempoRestante);

        DetalleSimple totalCarritos = new DetalleSimple();
        totalCarritos.setLlave("Carritos");
        totalCarritos.setValor(
                String.valueOf( getCocedorSeleccionado().getCarritos().size() )
                        .concat("/")
                        .concat( String.valueOf( getCocedorSeleccionado().getCapacidad() ) )
        );
        listaCocida.add(totalCarritos);

        DetalleSimple receta = new DetalleSimple();
        receta.setLlave("Receta");
        receta.setValor(getCocedorSeleccionado().getReceta());
        listaCocida.add(receta);

        RecyclerView vistaCocida = this.vista.findViewById(R.id.datosCocida);
        vistaCocida.setHasFixedSize(true);

        LinearLayoutManager layoutManagerCocida = new LinearLayoutManager( getContext() );
        vistaCocida.setLayoutManager(layoutManagerCocida);

        AdaptadorDetalleSimple adaptadorCocida = new AdaptadorDetalleSimple(listaCocida);
        vistaCocida.setAdapter(adaptadorCocida);

        RecyclerView vistaCarritos = this.vista.findViewById(R.id.listaCarritos);
        vistaCarritos.setHasFixedSize(true);

        LinearLayoutManager layoutManagerCarritos = new LinearLayoutManager( getContext() );
        vistaCarritos.setLayoutManager(layoutManagerCarritos);

        AdaptadorCarritoCocida adaptadorCarritoCocida = new AdaptadorCarritoCocida( getCocedorSeleccionado().getCarritos() );
        vistaCarritos.setAdapter(adaptadorCarritoCocida);

        ViewGroup.LayoutParams params = vistaCarritos.getLayoutParams();
        params.height = 102*getCocedorSeleccionado().getCarritos().size();
        vistaCarritos.setLayoutParams(params);
        vistaCarritos.requestLayout();

        terminaProcesando();
    }

    private void generaCompensacion(){
        JsonObject json = new JsonObject();
        json.addProperty("idCocida", getCocedorSeleccionado().getIdCocida() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().generaCompensacion(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        boton1.setVisibility(View.GONE);
                        detalleCocida();
                    }else{
                        terminaProcesando();
                        errorServicio("Error al iniciar la cocida");
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaServicio> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    public void detalleCocida(){
        Call<List<Cocedor>> llamadaServicio = APIServicios.getConexionAPPWEB()
                .getDetalleCocidasCocedor( getCocedorSeleccionado().getIdCocida() );
        llamadaServicio.enqueue(new Callback<List<Cocedor>>() {
            @Override
            public void onResponse(Call<List<Cocedor>> call, Response<List<Cocedor>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        if( !response.body().isEmpty() ){
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

    private void iniciaCocida(){
        JsonObject json = new JsonObject();
        json.addProperty("idCocida", getCocedorSeleccionado().getIdCocida() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().iniciaCocida(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    terminaProcesando();
                    if(response.code() == 200){
                        boton1.setVisibility(View.GONE);
                    }else{
                        errorServicio("Error al iniciar la cocida");
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaServicio> call, Throwable t) {
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
        ProgressBar barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        ProgressBar barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        barraProgreso.setVisibility(View.GONE);
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

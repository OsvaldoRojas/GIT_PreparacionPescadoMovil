package com.grupo.pinsa.sip.views.simulador.modulos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorCarritoModulo;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorCocedorCatalogo;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.modelo.Carrito;
import com.grupo.pinsa.sip.views.simulador.modelo.Cocedor;
import com.grupo.pinsa.sip.views.simulador.modelo.Modulo;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.ModuloCarritosAsignados;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;
import com.grupo.pinsa.sip.views.simulador.utilerias.Constantes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class Fragment_Entrada_Inventario extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private View vista;

    private List<Carrito> listaCarritos = new ArrayList<>();
    private List<Cocedor> cocedores;
    private AdaptadorCarritoModulo adaptador;

    public int totalCarritos = 0;
    public int capacidadTotal;

    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private ProgressBar barraProgreso;
    private RecyclerView vistaLista;
    private Spinner seleccionaCocedor;
    private Button registrar;

    private Modulo moduloSeleccionado;

    private OnFragmentInteractionListener mListener;

    public Fragment_Entrada_Inventario() {
    }

    public static Fragment_Entrada_Inventario newInstance(Serializable param1) {
        Fragment_Entrada_Inventario fragment = new Fragment_Entrada_Inventario();
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
        this.vista = inflater.inflate(R.layout.fragment_entrada_inventario, container, false);
        iniciaComponentes();
        return this.vista;
    }

    public Modulo getModuloSeleccionado() {
        return moduloSeleccionado;
    }

    public void setModuloSeleccionado(Modulo moduloSeleccionado) {
        this.moduloSeleccionado = moduloSeleccionado;
    }

    private void iniciaComponentes(){
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

        this.capacidadTotal = getModuloSeleccionado().getCapacidadActual();
        this.totalCarritos = getModuloSeleccionado().getCarritos().size();
        TextView etiquetaTotalCarritos = this.vista.findViewById(R.id.totalCarritos);
        etiquetaTotalCarritos.setText( String.valueOf(this.totalCarritos).concat("/")
                .concat( String.valueOf(this.capacidadTotal) ) );

        this.registrar = this.vista.findViewById(R.id.botonRegistrar);
        this.registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaGuardado();
            }
        });

        Button ver = this.vista.findViewById(R.id.botonVer);
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( ( (Cocedor) seleccionaCocedor.getSelectedItem() ).getId() > 0 ){
                    navega();
                }else{
                    errorServicio("Es necesario seleccionar un cocedor");
                }
            }
        });

        Button volver = this.vista.findViewById(R.id.botonVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment_Vista_Modulo().newInstance( getModuloSeleccionado() );
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        TextView descripcion = this.vista.findViewById(R.id.descripcion);
        descripcion.setText( getModuloSeleccionado().getDescripcion() );

        TextView capacidad = this.vista.findViewById(R.id.capacidad);
        capacidad.setText( String.valueOf( getModuloSeleccionado().getCapacidadMaxima() ) );

        this.vistaLista = this.vista.findViewById(R.id.listaCarritos);
        this.seleccionaCocedor = this.vista.findViewById(R.id.seleccionCocedor);

        getCatalogoCocedores();
    }

    private void getCatalogoCocedores(){
        Call<List<Cocedor>> llamadaServicio = APIServicios.getConexionAPPWEB().getCocedoresCocimiento();
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
        //this.cocedores = cocedores;
        this.cocedores = new ArrayList<>();
        for( Cocedor cocedor : cocedores ){
            if( cocedor.getEstatus().equalsIgnoreCase( Constantes.ESTATUS_COCEDOR.procesoDescarga.getDescripcion() ) ){
                this.cocedores.add(cocedor);
            }
        }
        Cocedor cocedorVacio = new Cocedor();
        cocedorVacio.setId(0);
        cocedorVacio.setDescripcion("Seleccionar cocedor");
        this.cocedores.add(0, cocedorVacio);
        AdaptadorCocedorCatalogo adaptadorCocedorCatalogo = new AdaptadorCocedorCatalogo(getContext(), this.cocedores);
        this.seleccionaCocedor.setAdapter(adaptadorCocedorCatalogo);
        terminaProcesando();
    }

    private void validaGuardado(){
        if( this.totalCarritos == getModuloSeleccionado().getCarritos().size() ){
            errorServicio("Es necesario seleccionar al menos un carrito");
        }else{
            iniciaProcesando();
            guarda();
        }
    }

    private void navega(){
        Intent contenedores = new Intent(getContext(), ActividadCarritosInventario.class);
        contenedores.putExtra("cocedor", (Serializable) this.seleccionaCocedor.getSelectedItem() );
        contenedores.putExtra("listaCarritosAgregados", (Serializable) this.listaCarritos);
        contenedores.putExtra("capacidadTotal", this.capacidadTotal);
        contenedores.putExtra("totalCarritos", this.totalCarritos);
        contenedores.putExtra("bascula",
                ( (Cocedor) this.seleccionaCocedor.getSelectedItem() ).getBascula() != null
                        ? ( (Cocedor) this.seleccionaCocedor.getSelectedItem() ).getBascula().getDescripcion()
                        : ""
        );
        startActivityForResult(contenedores, 0);
    }

    private void guarda(){
        int consecutivo = 1;
        for(Carrito carrito : this.listaCarritos){
            carrito.setPosicion( String.valueOf(consecutivo) );
            consecutivo = consecutivo + 1;
        }
        ModuloCarritosAsignados carritosAsignados = new ModuloCarritosAsignados();
        carritosAsignados.setIdModulo( getModuloSeleccionado().getId() );
        carritosAsignados.setTurno( UsuarioLogueado.getUsuarioLogueado().getTurno() );
        carritosAsignados.setUsuario( UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        carritosAsignados.setCarritos(this.listaCarritos);

        Call<List<RespuestaServicio>> llamadaServicio = APIServicios.getConexion().asignaCarritosModulo(carritosAsignados);
        llamadaServicio.enqueue(new Callback<List<RespuestaServicio>>() {
            @Override
            public void onResponse(Call<List<RespuestaServicio>> call, Response<List<RespuestaServicio>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        validaResultado( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al asignar los carritos al módulo");
                    }
                }
            }


            @Override
            public void onFailure(Call<List<RespuestaServicio>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void validaResultado(List<RespuestaServicio> respuestas){
        List<Carrito> carritosRemover = new ArrayList<>();
        for(RespuestaServicio respuestaServicio : respuestas){
            if( respuestaServicio.getCodigo() == 0 ){
                for(Carrito carrito : this.listaCarritos){
                    if( respuestaServicio.getId().equals( String.valueOf( carrito.getIdCocidaCarrito() ) ) ){
                        carritosRemover.add(carrito);
                    }
                }
            }
        }
        this.listaCarritos.removeAll(carritosRemover);

        if( !this.listaCarritos.isEmpty() ){
            muestraCarritos(this.listaCarritos);
            errorServicio("Ocurrió un error al asignar alguno de los carritos");
        }else{
            Fragment fragment = new Fragment_Vista_Modulo().newInstance( getModuloSeleccionado() );
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        }
    }

    private void muestraCarritos(List<Carrito> carritos){
        this.listaCarritos = carritos;
        for(Carrito carrito : this.listaCarritos){
            carrito.setSeleccionado(false);
            carrito.setSeleccionadoSuma(false);
        }
        this.vistaLista.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
        this.vistaLista.setLayoutManager(layoutManager);

        this.adaptador = new AdaptadorCarritoModulo(this.listaCarritos, this);
        this.vistaLista.setAdapter(this.adaptador);

        actualizaTotal();
        terminaProcesando();
    }

    public void actualizaTotal(){
        this.totalCarritos = getModuloSeleccionado().getCarritos().size() + this.listaCarritos.size();
        TextView etiquetaTotalCarritos = this.vista.findViewById(R.id.totalCarritos);
        etiquetaTotalCarritos.setText( String.valueOf(this.totalCarritos).concat("/")
                .concat( String.valueOf(this.capacidadTotal) ) );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if( resultCode == RESULT_OK ){
            iniciaProcesando();
            muestraCarritos( (List<Carrito>) data.getSerializableExtra("listaCarritosAgregados") );
        }
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

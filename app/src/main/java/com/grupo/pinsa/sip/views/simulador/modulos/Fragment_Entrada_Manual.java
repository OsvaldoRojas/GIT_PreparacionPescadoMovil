package com.grupo.pinsa.sip.views.simulador.modulos;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorBasculaCatalogo;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorCarritoModulo;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorCocedorCatalogo;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.modelo.Bascula;
import com.grupo.pinsa.sip.views.simulador.modelo.Carrito;
import com.grupo.pinsa.sip.views.simulador.modelo.Cocedor;
import com.grupo.pinsa.sip.views.simulador.modelo.Modulo;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.ModuloCarritosAsignados;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;
import com.grupo.pinsa.sip.views.simulador.utilerias.Constantes;
import com.grupo.pinsa.sip.views.simulador.utilerias.Utilerias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Entrada_Manual extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private View vista;

    private List<Carrito> listaCarritos = new ArrayList<>();
    private List<Cocedor> cocedores;
    private List<Bascula> basculas;
    private AdaptadorCarritoModulo adaptador;

    public int totalCarritos = 0;
    public int capacidadTotal;
    private boolean cargando = false;

    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private ProgressBar barraProgreso;
    private SwipeRefreshLayout actualizar;
    private RecyclerView vistaLista;
    private Spinner seleccionaCocedor;
    private Spinner seleccionaBascula;
    private Button registrar;

    private Modulo moduloSeleccionado;

    private OnFragmentInteractionListener mListener;

    public Fragment_Entrada_Manual() {
    }

    public static Fragment_Entrada_Manual newInstance(Serializable param1) {
        Fragment_Entrada_Manual fragment = new Fragment_Entrada_Manual();
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
        this.vista = inflater.inflate(R.layout.fragment_entrada_manual, container, false);
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

        this.actualizar = this.vista.findViewById(R.id.actualizar);
        this.actualizar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actualizar.setRefreshing(true);
                if( ( (Cocedor) seleccionaCocedor.getSelectedItem() ).getId() > 0 &&
                        ( (Bascula) seleccionaBascula.getSelectedItem() ).getIdBascula() > 0 ){
                    getCarritosSinAsignar();
                }else{
                    actualizar.setRefreshing(false);
                }
            }
        });

        this.registrar = this.vista.findViewById(R.id.botonRegistrar);
        this.registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaGuardado();
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
        this.seleccionaCocedor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if( cocedores.get(i).getId() > 0 ){
                    if(!cargando){
                        cargando = true;
                        for(Bascula bascula : basculas){
                            if( cocedores.get(i).getBascula() != null &&
                                    bascula.getIdBascula() == cocedores.get(i).getBascula().getIdBascula() ){
                                seleccionaBascula.setSelection(
                                        Utilerias.obtenerPosicionBascula( seleccionaBascula, bascula.getDescripcion() )
                                );
                                break;
                            }
                        }
                        if( ( (Bascula) seleccionaBascula.getSelectedItem() ).getIdBascula() > 0 ){
                            iniciaProcesando();
                            getCarritosSinAsignar();
                        }else{
                            cargando = false;
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.seleccionaBascula = this.vista.findViewById(R.id.seleccionBascula);
        this.seleccionaBascula.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if( basculas.get(i).getIdBascula() > 0 ){
                    if( ( (Cocedor) seleccionaCocedor.getSelectedItem() ).getId() > 0 ){
                        if(!cargando){
                            cargando = true;
                            iniciaProcesando();
                            getCarritosSinAsignar();
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getCatalogoCocedores();
        getCatalogoBasculas();
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

    private void getCatalogoBasculas(){
        Call<List<Bascula>> llamadaServicio = APIServicios.getConexionAPPWEB().getBasculas("coc");
        llamadaServicio.enqueue(new Callback<List<Bascula>>() {
            @Override
            public void onResponse(Call<List<Bascula>> call, Response<List<Bascula>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        muestraBasculas( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener las básculas");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Bascula>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void muestraBasculas(List<Bascula> basculas){
        this.basculas = basculas;
        Bascula basculaVacia = new Bascula();
        basculaVacia.setIdBascula(0);
        basculaVacia.setDescripcion("Seleccionar báscula");
        this.basculas.add(0, basculaVacia);
        AdaptadorBasculaCatalogo adaptadorBasculaCatalogo = new AdaptadorBasculaCatalogo(getContext(), this.basculas);
        this.seleccionaBascula.setAdapter(adaptadorBasculaCatalogo);
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

    private void guarda(){
        List<Carrito> listaCarritosAsignados = new ArrayList<>();
        int consecutivo = 1;
        for(Carrito carrito : this.listaCarritos){
            if( carrito.isSeleccionado() ){
                carrito.setPosicion( String.valueOf(consecutivo) );
                carrito.setIdBascula( ( (Bascula) this.seleccionaBascula.getSelectedItem() ).getIdBascula() );
                listaCarritosAsignados.add(carrito);
                consecutivo = consecutivo + 1;
            }
        }

        ModuloCarritosAsignados carritosAsignados = new ModuloCarritosAsignados();
        carritosAsignados.setIdModulo( getModuloSeleccionado().getId() );
        carritosAsignados.setTurno( UsuarioLogueado.getUsuarioLogueado().getTurno() );
        carritosAsignados.setUsuario( UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        carritosAsignados.setCarritos(listaCarritosAsignados);

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
        boolean error = false;
        for(RespuestaServicio respuestaServicio : respuestas){
            if( respuestaServicio.getCodigo() > 0 ){
                error = true;
                break;
            }
        }

        if(error){
            getCarritosSinAsignar();
            errorServicio("Ocurrió un error al asignar alguno de los carritos");
        }else{
            Fragment fragment = new Fragment_Vista_Modulo().newInstance( getModuloSeleccionado() );
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        }
    }

    private void getCarritosSinAsignar(){
        long idCocedor = ( (Cocedor) this.seleccionaCocedor.getSelectedItem() ).getId();
        int idBascula = ( (Bascula) this.seleccionaBascula.getSelectedItem() ).getIdBascula();
        Call<List<Carrito>> llamadaServicio = APIServicios.getConexion()
                .getCarritosSinAsignarModulo(idCocedor, idBascula);
        llamadaServicio.enqueue(new Callback<List<Carrito>>() {
            @Override
            public void onResponse(Call<List<Carrito>> call, Response<List<Carrito>> response) {
                if( isAdded() ){
                    cargando = false;
                    if(response.code() == 200){
                        muestraCarritos( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener los carritos sin asignar módulo");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Carrito>> call, Throwable t) {
                if( isAdded() ){
                    cargando = false;
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void muestraCarritos(List<Carrito> carritos){
        List<Carrito> listaCarritosPesados = new ArrayList<>();
        for(Carrito carrito : carritos){
            if( carrito.getPeso() > 0.0 ){
                listaCarritosPesados.add(carrito);
            }
        }

        for(Carrito carritoNuevo : listaCarritosPesados){
            for(Carrito carritoAnterior : this.listaCarritos){
                if( carritoNuevo.getDescripcion().equalsIgnoreCase( carritoAnterior.getDescripcion() ) ){
                    carritoNuevo.setSeleccionado( carritoAnterior.isSeleccionado() );
                    carritoNuevo.setSeleccionadoSuma( carritoAnterior.isSeleccionadoSuma() );
                    break;
                }
            }
        }
        this.listaCarritos = listaCarritosPesados;
        TextView sinResultado = this.vista.findViewById(R.id.sinResultados);
        if( !this.listaCarritos.isEmpty() ) {
            sinResultado.setVisibility(View.GONE);
            this.vistaLista.setVisibility(View.VISIBLE);

            this.vistaLista.setHasFixedSize(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
            this.vistaLista.setLayoutManager(layoutManager);

            this.adaptador = new AdaptadorCarritoModulo(this.listaCarritos, this);
            this.vistaLista.setAdapter(this.adaptador);

        }else{
            this.vistaLista.setVisibility(View.GONE);
            sinResultado.setVisibility(View.VISIBLE);
        }

        actualizaTotal();
        terminaProcesando();
    }

    public void actualizaTotal(){
        this.totalCarritos = getModuloSeleccionado().getCarritos().size();
        for(Carrito carrito : this.listaCarritos){
            if( carrito.isSeleccionadoSuma() ){
                this.totalCarritos = this.totalCarritos + 1;
            }
        }
        TextView etiquetaTotalCarritos = this.vista.findViewById(R.id.totalCarritos);
        etiquetaTotalCarritos.setText( String.valueOf(this.totalCarritos).concat("/")
                .concat( String.valueOf(this.capacidadTotal) ) );
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
}

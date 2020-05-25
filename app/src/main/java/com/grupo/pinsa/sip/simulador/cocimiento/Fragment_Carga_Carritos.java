package com.grupo.pinsa.sip.simulador.cocimiento;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorCarritosCocimiento;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorCocedorCatalogo;
import com.grupo.pinsa.sip.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Cocimiento;
import com.grupo.pinsa.sip.simulador.utilerias.Constantes;
import com.grupo.pinsa.sip.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.simulador.modelo.Carrito;
import com.grupo.pinsa.sip.simulador.modelo.Cocedor;
import com.grupo.pinsa.sip.simulador.modelo.CocedorActualSiguiente;
import com.grupo.pinsa.sip.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.simulador.modelo.servicio.CocedorCarritosAsignados;
import com.grupo.pinsa.sip.simulador.modelo.servicio.RespuestaServicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Carga_Carritos extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View vista;

    private List<Carrito> listaCarritos = new ArrayList<>();
    private List<Cocedor> cocedores;
    private AdaptadorCarritosCocimiento adaptador;

    public int totalCarritos = 0;
    public boolean libre;
    public int capacidadTotal;

    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private ProgressBar barraProgreso;
    private SwipeRefreshLayout actualizar;
    private CheckBox seleccionarTodo;
    private RecyclerView vistaLista;
    private Spinner seleccionaCocedor;
    private Button completo;

    private Cocedor cocedorSeleccionado;

    private OnFragmentInteractionListener mListener;

    public Fragment_Carga_Carritos() {
    }

    public static Fragment_Carga_Carritos newInstance(Serializable param1, Serializable param2) {
        Fragment_Carga_Carritos fragment = new Fragment_Carga_Carritos();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setCocedorSeleccionado( (Cocedor) getArguments().getSerializable(ARG_PARAM1) );
            setCocedores( (List<Cocedor>) getArguments().getSerializable(ARG_PARAM2) );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_carga_carritos, container, false);
        iniciaComponentes();
        return this.vista;
    }

    public Cocedor getCocedorSeleccionado() {
        return cocedorSeleccionado;
    }

    public void setCocedorSeleccionado(Cocedor cocedorSeleccionado) {
        this.cocedorSeleccionado = cocedorSeleccionado;
    }

    public List<Cocedor> getCocedores() {
        return cocedores;
    }

    public void setCocedores(List<Cocedor> cocedores) {
        this.cocedores = cocedores;
    }

    public boolean getSeleccionar(){
        return this.seleccionarTodo.isChecked();
    }

    private void iniciaComponentes(){
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

        this.capacidadTotal = getCocedorSeleccionado().getCapacidad();
        this.totalCarritos = getCocedorSeleccionado().getCarritos().size();
        TextView etiquetaTotalCarritos = this.vista.findViewById(R.id.totalCarritos);
        etiquetaTotalCarritos.setText( String.valueOf(this.totalCarritos).concat("/")
                .concat( String.valueOf( getCocedorSeleccionado().getCapacidad() ) ) );

        this.actualizar = this.vista.findViewById(R.id.actualizar);
        this.actualizar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actualizar.setRefreshing(true);
                if( !getCocedorSeleccionado().getEstatus().equalsIgnoreCase( Constantes.ESTATUS_COCEDOR.disponible.getDescripcion() ) ){
                    muestraCarritos( getCocedorSeleccionado().getCarritos() );
                }else{
                    getCarritosSinAsignar();
                }
                getCocedorActualSiguiente();
            }
        });

        this.completo = this.vista.findViewById(R.id.botonCompleto);
        this.completo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaGuardado();
            }
        });

        Button volver = this.vista.findViewById(R.id.botonVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(libre){
                    cambiaEstatus( Constantes.ESTATUS_COCEDOR.disponible.getId() );
                }
                Fragment fragment = new Contenedor_Cocimiento().newInstance(0);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        this.vistaLista = this.vista.findViewById(R.id.listaCarritos);

        this.seleccionaCocedor = this.vista.findViewById(R.id.seleccionCocedor);
        Cocedor cocedorVacio = new Cocedor();
        cocedorVacio.setId(0);
        cocedorVacio.setTamano("Seleccionar cocedor");
        getCocedores().add(0, cocedorVacio);
        //TODO: Quitar los cocedores deshabilitados
        AdaptadorCocedorCatalogo adaptadorCocedorCatalogo = new AdaptadorCocedorCatalogo(getContext(), getCocedores());
        this.seleccionaCocedor.setAdapter(adaptadorCocedorCatalogo);

        this.seleccionarTodo = this.vista.findViewById(R.id.seleccionarTodo);
        this.seleccionarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( seleccionarTodo.isChecked() ){
                    seleccionarTodo.setText("Deseleccionar todo");
                    int totalTemporal = totalCarritos;
                    for(int i = 0; i < listaCarritos.size(); i++){
                        if( totalTemporal < capacidadTotal ){
                            if( !listaCarritos.get(i).isSeleccionado() ){
                                totalTemporal = totalTemporal+1;
                            }
                            listaCarritos.get(i).setSeleccionado( seleccionarTodo.isChecked() );
                            listaCarritos.get(i).setSeleccionadoGeneral( !seleccionarTodo.isChecked() );
                            listaCarritos.get(i).setSeleccionadoSuma( seleccionarTodo.isChecked() );
                        }else{
                            break;
                        }
                    }
                }else{
                    seleccionarTodo.setText("Seleccionar todo");
                    for(int i = 0; i < listaCarritos.size(); i++){
                        listaCarritos.get(i).setSeleccionado( seleccionarTodo.isChecked() );
                        listaCarritos.get(i).setSeleccionadoGeneral( !seleccionarTodo.isChecked() );
                        listaCarritos.get(i).setSeleccionadoSuma( seleccionarTodo.isChecked() );
                    }
                }

                adaptador.notifyDataSetChanged();
                actualizaTotal();
            }
        });

        if( !getCocedorSeleccionado().getEstatus().equalsIgnoreCase( Constantes.ESTATUS_COCEDOR.disponible.getDescripcion() ) ){
            this.seleccionarTodo.setVisibility(View.GONE);
            this.seleccionaCocedor.setVisibility(View.GONE);
            this.completo.setVisibility(View.GONE);
            this.libre = false;
            muestraCarritos( getCocedorSeleccionado().getCarritos() );
        }else{
            this.libre = true;
            getCarritosSinAsignar();
            cambiaEstatus( Constantes.ESTATUS_COCEDOR.procesoCarga.getId() );
        }
        getCocedorActualSiguiente();
    }

    private void cambiaEstatus(int idEstado){
        JsonObject json = new JsonObject();
        json.addProperty("idCocedor", getCocedorSeleccionado().getId() );
        json.addProperty("idEstado", idEstado);
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().cambiaEstatusCocedor(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {

            }

            @Override
            public void onFailure(Call<RespuestaServicio> call, Throwable t) {

            }
        });
    }

    private void validaGuardado(){
        if( this.totalCarritos == 0 ){
            errorServicio("Es necesario seleccionar al menos un carrito");
        }else{
            String subtalla = "";
            List<String> listaSubtallas = new ArrayList<>();
            for(Carrito carrito : this.listaCarritos){
                if( carrito.isSeleccionado() ){
                    if( !carrito.getSubtalla().equalsIgnoreCase(subtalla) ){
                        listaSubtallas.add( carrito.getSubtalla() );
                        subtalla = carrito.getSubtalla();
                    }
                }
            }

            Set<String> hashSet = new HashSet<>(listaSubtallas);
            listaSubtallas.clear();
            listaSubtallas.addAll(hashSet);

            if( listaSubtallas.size() > 1 ){
                String[] arregloSubtallas = new String[listaSubtallas.size()];
                int contador = 0;
                for(String subtallaDiferente : listaSubtallas){
                    arregloSubtallas[contador] = subtallaDiferente;
                    contador = contador+1;
                }
                aceptaMezclarSubtallas(arregloSubtallas);
            }else{
                iniciaProcesando();
                guarda();
            }
        }
    }

    private void aceptaMezclarSubtallas(final String[] subtallas){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_decision_carritos, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaEmergente = builder.create();
        this.ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button botonCancelar = ventanaEmergente.findViewById(R.id.boton1);
                botonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ventanaEmergente.dismiss();
                    }
                });

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton2);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iniciaProcesando();
                        guarda();
                        ventanaEmergente.dismiss();
                    }
                });

                TextView etiquetaMensaje = ventanaEmergente.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText( "¿Desea realizar la mezcla de subtallas?" );

                ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_spinner_item,
                        subtallas
                );
                adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ListView listaPosicionSubtalla = ventanaEmergente.findViewById(R.id.listaSubtallas);
                listaPosicionSubtalla.setAdapter(adaptador);
                Utilerias.setAlturaLista(listaPosicionSubtalla, 349);
            }
        });
        this.ventanaEmergente.show();
    }

    private void guarda(){
        List<Long> listaEmparrilladosCarritos = new ArrayList<>();
        for(Carrito carrito : this.listaCarritos){
            if( carrito.isSeleccionado() ){
                listaEmparrilladosCarritos.add( carrito.getIdEmparrillado() );
            }
        }

        long[] emparrillados = new long[listaEmparrilladosCarritos.size()];
        for(int posicion = 0; posicion < listaEmparrilladosCarritos.size(); posicion++){
            emparrillados[posicion] = listaEmparrilladosCarritos.get(posicion);
        }

        CocedorCarritosAsignados carritosAsignados = new CocedorCarritosAsignados();
        carritosAsignados.setId( getCocedorSeleccionado().getId() );
        if( ( (Cocedor) this.seleccionaCocedor.getSelectedItem() ).getId() > 0 ){
            cambiaEstatus( Constantes.ESTATUS_COCEDOR.disponible.getId() );
            carritosAsignados.setId( ( (Cocedor) this.seleccionaCocedor.getSelectedItem() ).getId() );
        }
        carritosAsignados.setCarritos(emparrillados);
        carritosAsignados.setUsuario( UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().asignaCarritosCocedor(carritosAsignados);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        terminaProcesando();
                        if( response.body().getCodigo() >= 700 ){
                            errorServicio(response.body().getMensaje());
                        }else{
                            Fragment fragment = new Contenedor_Cocimiento().newInstance(0);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
                        }
                    }else{
                        terminaProcesando();
                        errorServicio("Error al asignar carritos al cocedor");
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

    private void getCarritosSinAsignar(){
        Call<List<Carrito>> llamadaServicio = APIServicios.getConexion().getCarritosSinAsignar();
        llamadaServicio.enqueue(new Callback<List<Carrito>>() {
            @Override
            public void onResponse(Call<List<Carrito>> call, Response<List<Carrito>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        muestraCarritos( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener los carritos sin asignar");
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

    private void muestraCarritos(List<Carrito> carritos){
        for(Carrito carritoNuevo : carritos){
            for(Carrito carritoAnterior : this.listaCarritos){
                if( carritoNuevo.getId() == carritoAnterior.getId() ){
                    carritoNuevo.setSeleccionado( carritoAnterior.isSeleccionado() );
                    carritoNuevo.setSeleccionadoSuma( carritoAnterior.isSeleccionadoSuma() );
                    carritoNuevo.setSeleccionadoGeneral( carritoAnterior.isSeleccionadoGeneral() );
                    break;
                }
            }
        }
        this.listaCarritos = carritos;
        TextView sinResultado = this.vista.findViewById(R.id.sinResultados);
        if( !this.listaCarritos.isEmpty() ) {
            sinResultado.setVisibility(View.GONE);
            this.vistaLista.setVisibility(View.VISIBLE);

            this.vistaLista.setHasFixedSize(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
            this.vistaLista.setLayoutManager(layoutManager);

            this.adaptador = new AdaptadorCarritosCocimiento(this.listaCarritos, this);
            this.vistaLista.setAdapter(this.adaptador);
        }else{
            this.vistaLista.setVisibility(View.GONE);
            sinResultado.setVisibility(View.VISIBLE);
        }

        actualizaTotal();
        terminaProcesando();
    }

    private void getCocedorActualSiguiente(){
        Call<CocedorActualSiguiente> llamadaServicio = APIServicios.getConexion().getCocedorActualSiguiente();
        llamadaServicio.enqueue(new Callback<CocedorActualSiguiente>() {
            @Override
            public void onResponse(Call<CocedorActualSiguiente> call, Response<CocedorActualSiguiente> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        muestraInformacion( response.body() );
                        terminaProcesando();
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener cocedor actual y siguiente");
                    }
                }
            }

            @Override
            public void onFailure(Call<CocedorActualSiguiente> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void muestraInformacion(CocedorActualSiguiente actualSiguiente){
        TextView actual = this.vista.findViewById(R.id.actual);
        actual.setText( actualSiguiente.getActual().getDescripcion() );

        TextView subtallaActual = this.vista.findViewById(R.id.subtallaActual);
        subtallaActual.setText( actualSiguiente.getActual().getSubtalla() );

        TextView especieActual = this.vista.findViewById(R.id.especieActual);
        especieActual.setText( actualSiguiente.getActual().getEspecie() );

        TextView capacidadActual = this.vista.findViewById(R.id.capacidadActual);
        capacidadActual.setText( String.valueOf( actualSiguiente.getActual().getCapacidad() ) );

        TextView horaActual = this.vista.findViewById(R.id.horaActual);
        horaActual.setText( actualSiguiente.getActual().getHoraInicio() );

        TextView siguiente = this.vista.findViewById(R.id.siguiente);
        siguiente.setText( actualSiguiente.getSiguiente().getDescripcion() );

        TextView subtallaSiguiente = this.vista.findViewById(R.id.subtallaSiguiente);
        subtallaSiguiente.setText( actualSiguiente.getSiguiente().getSubtalla() );

        TextView especieSiguiente = this.vista.findViewById(R.id.especieSiguiente);
        especieSiguiente.setText( actualSiguiente.getSiguiente().getEspecie() );

        TextView capacidadSiguiente = this.vista.findViewById(R.id.capacidadSiguiente);
        capacidadSiguiente.setText( String.valueOf( actualSiguiente.getSiguiente().getCapacidad() ) );

        TextView horaSiguiente = this.vista.findViewById(R.id.horaSiguiente);
        horaSiguiente.setText( actualSiguiente.getSiguiente().getHoraInicio() );
    }

    public void actualizaTotal(){
        this.totalCarritos = getCocedorSeleccionado().getCarritos().size();
        for(Carrito carrito : this.listaCarritos){
            if( carrito.isSeleccionadoSuma() ){
                this.totalCarritos = this.totalCarritos + 1;
            }
        }
        TextView etiquetaTotalCarritos = this.vista.findViewById(R.id.totalCarritos);
        etiquetaTotalCarritos.setText( String.valueOf(this.totalCarritos).concat("/")
                .concat( String.valueOf( getCocedorSeleccionado().getCapacidad() ) ) );
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
}

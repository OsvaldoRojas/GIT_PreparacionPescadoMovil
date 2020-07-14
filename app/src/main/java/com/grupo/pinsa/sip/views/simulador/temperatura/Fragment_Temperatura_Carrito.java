package com.grupo.pinsa.sip.views.simulador.temperatura;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorCocedorCatalogo;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorModuloCatalogo;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorTemperaturaCarrito;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.views.simulador.modelo.Carrito;
import com.grupo.pinsa.sip.views.simulador.modelo.Cocedor;
import com.grupo.pinsa.sip.views.simulador.modelo.Modulo;
import com.grupo.pinsa.sip.views.simulador.modelo.TemperaturaCarrito;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Temperatura_Carrito extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private View vista;

    private int etapa;
    public boolean libre;

    private AlertDialog ventanaError;
    private ProgressBar barraProgreso;
    private SwipeRefreshLayout actualizar;
    private CheckBox seleccionarTodo;
    private RecyclerView vistaCarritos;
    private EditText temperatura;
    private TextView numeroCocida;
    private Spinner seleccionador;
    private List<Cocedor> cocedores;
    private List<Modulo> modulos;
    private List<Carrito> carritos;
    private AdaptadorTemperaturaCarrito adaptador;

    private OnFragmentInteractionListener mListener;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public boolean getSeleccionar(){
        return this.seleccionarTodo.isChecked();
    }

    public Fragment_Temperatura_Carrito() {
    }

    public static Fragment_Temperatura_Carrito newInstance(int param1) {
        Fragment_Temperatura_Carrito fragment = new Fragment_Temperatura_Carrito();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setEtapa( getArguments().getInt(ARG_PARAM1) );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_temperatura_carrito, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        this.temperatura = this.vista.findViewById(R.id.temperatura);
        this.seleccionador = this.vista.findViewById(R.id.seleccionador);
        this.numeroCocida = this.vista.findViewById(R.id.campoCocida);
        this.vistaCarritos = this.vista.findViewById(R.id.listaCarritos);

        this.actualizar = this.vista.findViewById(R.id.actualizar);
        this.actualizar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actualizar.setRefreshing(true);
                if(etapa == 1){
                    getCocedores();
                }else{
                    getModulos();
                }
            }
        });

        Button volver = this.vista.findViewById(R.id.botonVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeTemperatura();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        Button guardar = this.vista.findViewById(R.id.botonGuardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciaProcesando();
                guarda();
            }
        });

        Button resgitrar = this.vista.findViewById(R.id.botonRegistrar);
        resgitrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !temperatura.getText().toString().equals("") && !temperatura.getText().toString().equals(".") ){
                    for(Carrito carrito : carritos){
                        if( carrito.isSeleccionado() ){
                            carrito.setTemperatura( Float.valueOf( temperatura.getText().toString() ) );
                        }
                    }
                    temperatura.setText("");
                    if( seleccionarTodo.isChecked() ){
                        seleccionarTodo.callOnClick();
                    }else{
                        for(int i = 0; i < carritos.size(); i++){
                            carritos.get(i).setSeleccionado( seleccionarTodo.isChecked() );
                            carritos.get(i).setSeleccionadoGeneral( !seleccionarTodo.isChecked() );
                            carritos.get(i).setSeleccionadoSuma( seleccionarTodo.isChecked() );
                        }
                        adaptador.notifyDataSetChanged();
                    }
                }else{
                    errorServicio("Es necesario ingresar una temperatura valida");
                }
            }
        });

        this.seleccionarTodo = this.vista.findViewById(R.id.seleccionarTodo);
        this.seleccionarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < carritos.size(); i++){
                    carritos.get(i).setSeleccionado( seleccionarTodo.isChecked() );
                    carritos.get(i).setSeleccionadoGeneral( !seleccionarTodo.isChecked() );
                    carritos.get(i).setSeleccionadoSuma( seleccionarTodo.isChecked() );
                }

                if( seleccionarTodo.isChecked() ){
                    seleccionarTodo.setText("Deseleccionar todo");
                }else{
                    seleccionarTodo.setText("Seleccionar todo");
                }
                if(adaptador != null){
                    adaptador.notifyDataSetChanged();
                }
            }
        });

        TextView etiquetaTitulo = this.vista.findViewById(R.id.etiquetaTitulo);
        if(this.etapa == 1){
            etiquetaTitulo.setText("Cocimiento");
            getCocedores();
        }else{
            etiquetaTitulo.setText("Módulos");
            this.numeroCocida.setVisibility(View.GONE);
            getModulos();
        }
    }

    private void getCocedores(){
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

    private void muestraCocedores(List<Cocedor> listaCocedores){
        this.cocedores = listaCocedores;
        Cocedor cocedorVacio = new Cocedor();
        cocedorVacio.setId(0);
        cocedorVacio.setDescripcion("Seleccionar");
        this.cocedores.add(0, cocedorVacio);
        AdaptadorCocedorCatalogo adaptadorCocedorCatalogo = new AdaptadorCocedorCatalogo(getContext(), this.cocedores);
        this.seleccionador.setAdapter(adaptadorCocedorCatalogo);
        this.seleccionador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if( cocedores.get(i).getId() == 0 ){
                    numeroCocida.setText("");
                    muestraCarritos(new ArrayList<Carrito>());
                }else{
                    numeroCocida.setText( cocedores.get(i).getNumeroCocida() );
                    muestraCarritos( cocedores.get(i).getCarritos() );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        terminaProcesando();
    }

    private void muestraCarritos(List<Carrito> listaCarritos){
        this.carritos = listaCarritos;
        TextView sinResultado = this.vista.findViewById(R.id.sinResultados);
        Button guardar = this.vista.findViewById(R.id.botonGuardar);
        if( !this.carritos.isEmpty() ) {
            guardar.setEnabled(true);
            this.vistaCarritos.setVisibility(View.VISIBLE);
            sinResultado.setVisibility(View.GONE);
            this.vistaCarritos.setHasFixedSize(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
            this.vistaCarritos.setLayoutManager(layoutManager);

            this.adaptador = new AdaptadorTemperaturaCarrito(this.carritos, this);
            this.vistaCarritos.setAdapter(this.adaptador);
        }else{
            guardar.setEnabled(false);
            this.vistaCarritos.setVisibility(View.GONE);
            sinResultado.setVisibility(View.VISIBLE);
        }
        terminaProcesando();
    }

    private void getModulos(){
        Call<List<Modulo>> llamadaServicio = APIServicios.getConexionAPPWEB().getModulos();
        llamadaServicio.enqueue(new Callback<List<Modulo>>() {
            @Override
            public void onResponse(Call<List<Modulo>> call, Response<List<Modulo>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        muestraModulos( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener los módulos");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Modulo>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void muestraModulos(List<Modulo> listaModulos){
        this.modulos = listaModulos;
        Modulo moduloVacio = new Modulo();
        moduloVacio.setId(0);
        moduloVacio.setDescripcion("Seleccionar módulo");
        this.modulos.add(0, moduloVacio);
        AdaptadorModuloCatalogo adaptadorModuloCatalogo = new AdaptadorModuloCatalogo(getContext(), this.modulos);
        this.seleccionador.setAdapter(adaptadorModuloCatalogo);
        this.seleccionador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if( modulos.get(i).getId() == 0 ){
                    muestraCarritos(new ArrayList<Carrito>());
                }else{
                    iniciaProcesando();
                    getCarritos( modulos.get(i).getId() );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        terminaProcesando();
    }

    private void getCarritos(long idModulo){
        Call<List<Carrito>> llamadaServicio = APIServicios.getConexionAPPWEB().getCarritosModulo(idModulo);
        llamadaServicio.enqueue(new Callback<List<Carrito>>() {
            @Override
            public void onResponse(Call<List<Carrito>> call, Response<List<Carrito>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        muestraCarritos( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener los carritos del módulo");
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

    private void guarda(){
        List<TemperaturaCarrito> listaTemperaturasCarrito = new ArrayList<>();
        for(Carrito carrito : this.carritos){
            TemperaturaCarrito temperaturaCarrito = new TemperaturaCarrito();
            temperaturaCarrito.setEtapa(this.etapa);
            temperaturaCarrito.setTemperatura( carrito.getTemperatura() );
            temperaturaCarrito.setUsuario( UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
            temperaturaCarrito.setIdCocidaCarrito( carrito.getIdCocidaCarrito() );
            temperaturaCarrito.setIdModuloEspacio( carrito.getIdModuloEspacio() );
            listaTemperaturasCarrito.add(temperaturaCarrito);
        }
        Call<List<RespuestaServicio>> llamadaServicio = APIServicios.getConexion().guardaTemperaturaCarrito(listaTemperaturasCarrito);
        llamadaServicio.enqueue(new Callback<List<RespuestaServicio>>() {
            @Override
            public void onResponse(Call<List<RespuestaServicio>> call, Response<List<RespuestaServicio>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        validaResultado( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al guardar temperatura de los carritos");
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
                for(Carrito carrito : this.carritos){
                    if( ( this.etapa == 1 && respuestaServicio.getId() == String.valueOf( carrito.getIdCocidaCarrito() ) )
                            || ( this.etapa == 2 && respuestaServicio.getId() == String.valueOf( carrito.getIdModuloEspacio() ) ) ){
                        carrito.setTemperatura(0);
                        break;
                    }
                }
                error = true;
            }
        }

        terminaProcesando();
        if(error){
            this.adaptador.notifyDataSetChanged();
            errorServicio("Ocurrió un error al guardar algunas temperaturas");
        }else{
            Fragment fragment = new HomeTemperatura();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
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

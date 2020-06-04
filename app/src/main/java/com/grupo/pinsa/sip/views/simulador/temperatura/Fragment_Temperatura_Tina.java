package com.grupo.pinsa.sip.views.simulador.temperatura;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorTemperaturaTina;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.views.simulador.modelo.TemperaturaTina;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Temperatura_Tina extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private View vista;

    private int idEtapa;
    public boolean libre;

    private AlertDialog ventanaError;
    private ProgressBar barraProgreso;
    private SwipeRefreshLayout actualizar;
    private CheckBox seleccionarTodo;
    private RecyclerView vistaTinas;
    private EditText temperatura;
    private List<TemperaturaTina> listaTinas;
    private AdaptadorTemperaturaTina adaptador;

    private OnFragmentInteractionListener mListener;

    public int getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(int idEtapa) {
        this.idEtapa = idEtapa;
    }

    public boolean getSeleccionar(){
        return this.seleccionarTodo.isChecked();
    }

    public Fragment_Temperatura_Tina() {
    }

    public static Fragment_Temperatura_Tina newInstance(int param1) {
        Fragment_Temperatura_Tina fragment = new Fragment_Temperatura_Tina();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setIdEtapa( getArguments().getInt(ARG_PARAM1) );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_temperatura_tina, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        this.temperatura = this.vista.findViewById(R.id.temperatura);
        this.vistaTinas = this.vista.findViewById(R.id.listaTinas);

        this.actualizar = this.vista.findViewById(R.id.actualizar);
        this.actualizar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actualizar.setRefreshing(true);
                getTinas();
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
                for(TemperaturaTina tina : listaTinas){
                    if( tina.isSeleccionado() ){
                        tina.setTemperatura( Float.valueOf( temperatura.getText().toString() ) );
                    }
                }
                temperatura.setText("");
                if( seleccionarTodo.isChecked() ){
                    seleccionarTodo.callOnClick();
                }else{
                    for(int i = 0; i < listaTinas.size(); i++){
                        listaTinas.get(i).setSeleccionado( seleccionarTodo.isChecked() );
                        listaTinas.get(i).setSeleccionadoGeneral( !seleccionarTodo.isChecked() );
                        listaTinas.get(i).setSeleccionadoSuma( seleccionarTodo.isChecked() );
                    }
                    adaptador.notifyDataSetChanged();
                }
            }
        });

        this.seleccionarTodo = this.vista.findViewById(R.id.seleccionarTodo);
        this.seleccionarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < listaTinas.size(); i++){
                    listaTinas.get(i).setSeleccionado( seleccionarTodo.isChecked() );
                    listaTinas.get(i).setSeleccionadoGeneral( !seleccionarTodo.isChecked() );
                    listaTinas.get(i).setSeleccionadoSuma( seleccionarTodo.isChecked() );
                }

                if( seleccionarTodo.isChecked() ){
                    seleccionarTodo.setText("Deseleccionar todo");
                }else{
                    seleccionarTodo.setText("Seleccionar todo");
                }
                adaptador.notifyDataSetChanged();
            }
        });

        getTinas();
    }

    private void getTinas(){
        Call<List<TemperaturaTina>> llamadaServicio = APIServicios.getConexion().getTinasTemperatura( getIdEtapa() );
        llamadaServicio.enqueue(new Callback<List<TemperaturaTina>>() {
            @Override
            public void onResponse(Call<List<TemperaturaTina>> call, Response<List<TemperaturaTina>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        muestraTinas( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener el listado de tinas");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TemperaturaTina>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void muestraTinas(List<TemperaturaTina> tinas){
        this.listaTinas = tinas;
        TextView sinResultado = this.vista.findViewById(R.id.sinResultados);
        if( !this.listaTinas.isEmpty() ) {
            this.vistaTinas.setVisibility(View.VISIBLE);
            sinResultado.setVisibility(View.GONE);
            this.vistaTinas.setHasFixedSize(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
            this.vistaTinas.setLayoutManager(layoutManager);

            this.adaptador = new AdaptadorTemperaturaTina(this.listaTinas, this);
            this.vistaTinas.setAdapter(this.adaptador);
        }else{
            this.vistaTinas.setVisibility(View.GONE);
            sinResultado.setVisibility(View.VISIBLE);
        }
        terminaProcesando();
    }

    private void guarda(){
        for(TemperaturaTina tina : this.listaTinas){
            tina.setIdEtapa(this.idEtapa);
            tina.setUsuario( UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        }
        Call<List<RespuestaServicio>> llamadaServicio = APIServicios.getConexion().guardaTemperaturaTina(this.listaTinas);
        llamadaServicio.enqueue(new Callback<List<RespuestaServicio>>() {
            @Override
            public void onResponse(Call<List<RespuestaServicio>> call, Response<List<RespuestaServicio>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                       validaResultado( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al guardar temperatura de las tinas");
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
                for(TemperaturaTina tina : this.listaTinas){
                    if( respuestaServicio.getId() == String.valueOf( tina.getIdHistoricoTina() ) ){
                        tina.setTemperatura(0);
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

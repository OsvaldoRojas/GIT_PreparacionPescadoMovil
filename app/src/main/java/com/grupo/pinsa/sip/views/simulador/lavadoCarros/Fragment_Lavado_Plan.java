package com.grupo.pinsa.sip.views.simulador.lavadoCarros;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.grupo.pinsa.libraries.Fragment;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.utilerias.Constantes;
import com.grupo.pinsa.sip.views.simulador.modelo.Operador;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Lavado_Plan extends Fragment {

    private View vista;

    private ImageView operador1;
    private ImageView operador2;
    private ImageView operador3;
    private ImageView operador4;
    private ImageView operador5;
    private ImageView operador6;
    private ImageView operador7;
    private ImageView operador8;

    private Operador operadorSeleccionado;

    private List<Operador> listaOperadores = new ArrayList<>();

    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private ProgressBar barraProgreso;
    private SwipeRefreshLayout actualizar;

    private LinearLayout contenedorBotones;
    private LinearLayout contenedorTurno;

    private Button boton1;
    private Button boton2;
    private Button botonTurno;

    private OnFragmentInteractionListener mListener;

    public Fragment_Lavado_Plan() {
    }

    public Operador getOperadorSeleccionado() {
        return operadorSeleccionado;
    }

    public void setOperadorSeleccionado(Operador operadorSeleccionado) {
        this.operadorSeleccionado = operadorSeleccionado;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_lavado_plan, container, false);
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
                getAsignados();
            }
        });

        this.contenedorBotones = this.vista.findViewById(R.id.contenedorBotones);
        this.boton1 = this.vista.findViewById(R.id.boton1);
        this.boton2 = this.vista.findViewById(R.id.boton2);

        this.contenedorTurno = this.vista.findViewById(R.id.contenedorTurno);
        this.botonTurno = this.vista.findViewById(R.id.botonTurno);
        this.botonTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liberaTurno();
            }
        });

        this.operador1 = this.vista.findViewById(R.id.operador1);
        this.operador1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(1);
            }
        });

        this.operador2 = this.vista.findViewById(R.id.operador2);
        this.operador2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(2);
            }
        });

        this.operador3 = this.vista.findViewById(R.id.operador3);
        this.operador3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(3);
            }
        });

        this.operador4 = this.vista.findViewById(R.id.operador4);
        this.operador4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(4);
            }
        });

        this.operador5 = this.vista.findViewById(R.id.operador5);
        this.operador5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(5);
            }
        });

        this.operador6 = this.vista.findViewById(R.id.operador6);
        this.operador6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(6);
            }
        });

        this.operador7 = this.vista.findViewById(R.id.operador7);
        this.operador7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(7);
            }
        });

        this.operador8 = this.vista.findViewById(R.id.operador8);
        this.operador8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(8);
            }
        });

        getAsignados();
    }

    private void getAsignados(){
        if( getOperadorSeleccionado() != null ){
            accionIconoOperador( getOperadorSeleccionado().getIdEstacion() );
        }

        getOperadoresAsignados();
    }

    private void getOperadoresAsignados(){
        Call<List<Operador>> llamadaServicio = APIServicios.getConexion().getOperadoresLavado();
        llamadaServicio.enqueue(new Callback<List<Operador>>() {
            @Override
            public void onResponse(Call<List<Operador>> call, Response<List<Operador>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        ordenaOperadores( response.body() );
                        terminaProcesando();
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener los operadores asignados");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Operador>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    public void ordenaOperadores(List<Operador> listaOperadores){
        if( isAdded() ){
            this.listaOperadores = new ArrayList<>();
            for(Operador operador : listaOperadores){
                if(operador.getIdEstacion() <= 8){
                    this.listaOperadores.add(operador);
                }
            }
            for( Operador recursoOperador : this.listaOperadores ){
                recursoOperador.setEstado(Constantes.ESTADO.seleccionado);
                accionIconoOperador( recursoOperador.getIdEstacion() );
            }
        }
    }

    private void accionIconoOperador(int posicion){
        for( Operador operador : this.listaOperadores ){
            if( operador.getIdEstacion() == posicion ){
                if( operador.getEstado() == Constantes.ESTADO.inicial ){
                    setOperadorSeleccionado(operador);
                    deshabilitaRecursos();
                    getIconoOperador( operador.getIdEstacion() )
                            .setImageResource(R.drawable.ic_operador_blanco);
                    getIconoOperador( operador.getIdEstacion() )
                            .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                    operador.setEstado( Constantes.ESTADO.seleccionado );
                    this.boton1.setText(R.string.liberarUsuario);
                    this.boton1.setEnabled(false);
                    this.boton1.setOnClickListener(null);
                    this.boton2.setText(R.string.asignarUsuario);
                    this.boton2.setEnabled(true);
                    this.boton2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            asignaOperador();
                        }
                    });
                    this.contenedorTurno.setVisibility(View.GONE);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( operador.getEstado() == Constantes.ESTADO.seleccionado ){
                        setOperadorSeleccionado(null);
                        habilitaRecursos();
                        if( operador.isLibre() ){
                            getIconoOperador( operador.getIdEstacion() )
                                    .setImageResource(R.drawable.ic_operador_gris);
                            getIconoOperador( operador.getIdEstacion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
                            operador.setEstado( Constantes.ESTADO.inicial );
                        }else{
                            getIconoOperador( operador.getIdEstacion() )
                                    .setImageResource(R.drawable.ic_operador_negro);
                            getIconoOperador( operador.getIdEstacion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_asignado ) );
                            operador.setEstado( Constantes.ESTADO.asignado );
                        }
                        if( validaMuestraTurno() ){
                            this.contenedorBotones.setVisibility(View.GONE);
                            this.contenedorTurno.setVisibility(View.VISIBLE);
                        }else{
                            this.contenedorBotones.setVisibility(View.INVISIBLE);
                            this.contenedorTurno.setVisibility(View.GONE);
                        }
                    }else{
                        setOperadorSeleccionado(operador);
                        deshabilitaRecursos();
                        getIconoOperador( operador.getIdEstacion() )
                                .setImageResource(R.drawable.ic_operador_blanco);
                        getIconoOperador( operador.getIdEstacion() )
                                .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                        operador.setEstado( Constantes.ESTADO.seleccionado );
                        this.boton1.setText(R.string.liberarUsuario);
                        this.boton1.setEnabled(true);
                        this.boton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                liberaOperador();
                            }
                        });
                        this.boton2.setText(R.string.asignarUsuario);
                        this.boton2.setEnabled(false);
                        this.boton2.setOnClickListener(null);
                        this.contenedorTurno.setVisibility(View.GONE);
                        this.contenedorBotones.setVisibility(View.VISIBLE);
                    }
                }
                break;
            }
        }
    }

    private void asignaOperador(){
        androidx.fragment.app.Fragment fragment = new Fragment_Asigna_Operador().newInstance( getOperadorSeleccionado() );
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private void liberaOperador(){
        iniciaProcesando();
        getOperadorSeleccionado().setIdEmpleado(0);
        getOperadorSeleccionado().setLibre(true);
        getOperadorSeleccionado().setTurno(true);
        if( UsuarioLogueado.getUsuarioLogueado().getTurno() == 1 ){
            getOperadorSeleccionado().setTurno(false);
        }
        guardaOperador();
    }

    private void guardaOperador(){
        JsonObject json = new JsonObject();
        json.addProperty("idEstacion", getOperadorSeleccionado().getIdEstacion() );
        json.addProperty("libre", getOperadorSeleccionado().isLibre() );
        json.addProperty("idEmpleado", String.valueOf( getOperadorSeleccionado().getIdEmpleado() ) );
        json.addProperty("turno", getOperadorSeleccionado().isTurno() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaOperadorLavado(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        ventanaMensaje("El usuario fue liberado exitosamente");
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al liberar al operador" );
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
                        getAsignados();
                    }
                });
            }
        });
        this.ventanaError.show();
    }

    private void liberaTurno(){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_decision_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaEmergente = builder.create();
        this.ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaEmergente.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText("¿Está seguro de liberar todos los operadores?");

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton2);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iniciaProcesando();
                        liberaTurnoServicio();
                        ventanaEmergente.dismiss();
                    }
                });

                Button botonCancelar = ventanaEmergente.findViewById(R.id.boton1);
                botonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ventanaEmergente.dismiss();
                    }
                });
            }
        });
        this.ventanaEmergente.show();
    }

    private void liberaTurnoServicio(){
        JsonObject json = new JsonObject();
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().liberaTurnoLavado(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        getAsignados();
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al liberar el turno" );
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

    private void deshabilitaRecursos(){
        for(Operador operador : this.listaOperadores){
            getIconoOperador( operador.getIdEstacion() ).setEnabled(false);
        }

        if( getOperadorSeleccionado() != null ){
            getIconoOperador( getOperadorSeleccionado().getIdEstacion() ).setEnabled(true);
        }
    }

    private void habilitaRecursos(){
        for(Operador operador : this.listaOperadores) {
            getIconoOperador(operador.getIdEstacion()).setEnabled(true);
        }
    }

    private ImageView getIconoOperador(int posicion){
        switch (posicion){
            case 1: return this.operador1;
            case 2: return this.operador2;
            case 3: return this.operador3;
            case 4: return this.operador4;
            case 5: return this.operador5;
            case 6: return this.operador6;
            case 7: return this.operador7;
            case 8: return this.operador8;
        }
        return null;
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

    private boolean validaMuestraTurno(){
        for( Operador operadorBascula : this.listaOperadores ){
            if( !operadorBascula.isLibre() ){
                return true;
            }
        }
        return false;
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

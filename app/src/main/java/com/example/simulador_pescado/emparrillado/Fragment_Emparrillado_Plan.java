package com.example.simulador_pescado.emparrillado;

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
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.conexion.APIServicios;
import com.example.simulador_pescado.utilerias.Constantes;
import com.example.simulador_pescado.vista.OperadorBascula;
import com.example.simulador_pescado.vista.UsuarioLogueado;
import com.example.simulador_pescado.vista.servicio.LiberarTodos;
import com.example.simulador_pescado.vista.servicio.RespuestaServicio;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Emparrillado_Plan extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View vista;

    private ImageView operador1;
    private ImageView operador2;
    private ImageView operador3;
    private ImageView operador4;
    private ImageView operador5;
    private ImageView operador6;
    private ImageView operador7;
    private ImageView operador8;

    private OperadorBascula operadorSeleccionado;

    private List<OperadorBascula> listaOperadores = new ArrayList<>();

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

    public Fragment_Emparrillado_Plan() {
    }

    public OperadorBascula getOperadorSeleccionado() {
        return operadorSeleccionado;
    }

    public void setOperadorSeleccionado(OperadorBascula operadorSeleccionado) {
        this.operadorSeleccionado = operadorSeleccionado;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Descongelado_Plan.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Emparrillado_Plan newInstance(String param1, String param2) {
        Fragment_Emparrillado_Plan fragment = new Fragment_Emparrillado_Plan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_emparrillado_plan, container, false);
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
            accionIconoOperador( getOperadorSeleccionado().getIdPreseleccionEstacion() );
        }

        getOperadoresAsignados();
    }

    private void getOperadoresAsignados(){
        Call<List<OperadorBascula>> operadoresAsignados = APIServicios.getConexion().getOperadoresAsignados();
        operadoresAsignados.enqueue(new Callback<List<OperadorBascula>>() {
            @Override
            public void onResponse(Call<List<OperadorBascula>> call, Response<List<OperadorBascula>> response) {
                if(response.code() == 200){
                    ordenaOperadores( response.body() );
                    terminaProcesando();
                }else{
                    terminaProcesando();
                    errorServicio("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<List<OperadorBascula>> call, Throwable t) {
                terminaProcesando();
                errorServicio("Error al conectar con el servidor");
            }
        });
    }

    public void ordenaOperadores(List<OperadorBascula> listaOperadores){
        if( isAdded() ){
            //this.listaOperadores = listaOperadores;
            this.listaOperadores = new ArrayList<>();
            for(OperadorBascula operadorBascula : listaOperadores){
                if(operadorBascula.getIdPreseleccionEstacion() <= 8){
                    this.listaOperadores.add(operadorBascula);
                }
            }
            for( final OperadorBascula recursoOperador : this.listaOperadores ){
                recursoOperador.setEstado(Constantes.ESTADO.seleccionado);
                accionIconoOperador( recursoOperador.getIdPreseleccionEstacion() );
            }
        }
    }

    private void accionIconoOperador(int posicion){
        for( OperadorBascula operador : this.listaOperadores ){
            if( operador.getIdPreseleccionEstacion() == posicion ){
                if( operador.getEstado() == Constantes.ESTADO.inicial ){
                    setOperadorSeleccionado(operador);
                    deshabilitaRecursos();
                    getIconoOperador( operador.getIdPreseleccionEstacion() )
                            .setImageResource(R.drawable.ic_operador_blanco);
                    getIconoOperador( operador.getIdPreseleccionEstacion() )
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
                        if( operador.getLibre() ){
                            getIconoOperador( operador.getIdPreseleccionEstacion() )
                                    .setImageResource(R.drawable.ic_operador_gris);
                            getIconoOperador( operador.getIdPreseleccionEstacion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
                            operador.setEstado( Constantes.ESTADO.inicial );
                        }else{
                            getIconoOperador( operador.getIdPreseleccionEstacion() )
                                    .setImageResource(R.drawable.ic_operador_negro);
                            getIconoOperador( operador.getIdPreseleccionEstacion() )
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
                        getIconoOperador( operador.getIdPreseleccionEstacion() )
                                .setImageResource(R.drawable.ic_operador_blanco);
                        getIconoOperador( operador.getIdPreseleccionEstacion() )
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
        Fragment fragment = new Fragment_Asigna_Operador().newInstance( getOperadorSeleccionado() );
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private void liberaOperador(){
        iniciaProcesando();
        getOperadorSeleccionado().setIdEmpleado(0);
        getOperadorSeleccionado().setLibre(true);
        guardaOperador();
    }

    private void guardaOperador(){
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaOperador( getOperadorSeleccionado() );
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                RespuestaServicio respuesta = response.body();
                if( response.code() == 200 && respuesta.getCodigo() == 0 ){
                    ventanaMensaje("El usuario fue liberado exitosamente");
                }else{
                    terminaProcesando();
                    errorServicio("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<RespuestaServicio> call, Throwable t) {
                terminaProcesando();
                errorServicio("Error al conectar con el servidor");
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
        LiberarTodos liberarTodos = new LiberarTodos( UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().liberaTurno(liberarTodos);

        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                RespuestaServicio respuesta = response.body();
                if( response.code() == 200 && respuesta.getCodigo() == 0 ){
                    getAsignados();
                }else{
                    terminaProcesando();
                    errorServicio("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<RespuestaServicio> call, Throwable t) {
                terminaProcesando();
                errorServicio("Error al conectar con el servidor");
            }
        });
    }

    private void deshabilitaRecursos(){
        for(OperadorBascula operador : this.listaOperadores){
            getIconoOperador( operador.getIdPreseleccionEstacion() ).setEnabled(false);
        }

        if( getOperadorSeleccionado() != null ){
            getIconoOperador( getOperadorSeleccionado().getIdPreseleccionEstacion() ).setEnabled(true);
        }
    }

    private void habilitaRecursos(){
        for(OperadorBascula operador : this.listaOperadores) {
            getIconoOperador(operador.getIdPreseleccionEstacion()).setEnabled(true);
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
        for( OperadorBascula operadorBascula : this.listaOperadores ){
            if( !operadorBascula.getLibre() ){
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

    // TODO: Rename method, update argument and hook method into UI event
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

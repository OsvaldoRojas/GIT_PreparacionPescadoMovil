package com.grupo.pinsa.sip.views.simulador.modulos;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorZona;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.contenedores.Contenedor_Modulos;
import com.grupo.pinsa.sip.views.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.views.simulador.modelo.Operador;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.Zona;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.Gafete;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Asigna_Operador extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private Serializable mParam1;
    private View vista;
    private Spinner seleccionZona;

    private AlertDialog ventanaError;

    private Operador operadorSeleccionado;

    private OnFragmentInteractionListener mListener;

    public Fragment_Asigna_Operador() {
    }

    public static Fragment_Asigna_Operador newInstance(Serializable param1) {
        Fragment_Asigna_Operador fragment = new Fragment_Asigna_Operador();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_asignar_operador_modulo, container, false);
        iniciaComponentes();
        return this.vista;
    }

    public Operador getOperadorSeleccionado() {
        return operadorSeleccionado;
    }

    public void setOperadorSeleccionado(Operador operadorSeleccionado) {
        this.operadorSeleccionado = operadorSeleccionado;
    }

    private void iniciaComponentes(){
        setOperadorSeleccionado( (Operador) this.mParam1);
        iniciaProcesando();

        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor_Modulos().newInstance(0);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        Button botonAceptar = this.vista.findViewById(R.id.boton2);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaAsignacion();
            }
        });

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        campoEscaner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validaGafete( editable.toString() );
            }
        });

        getZonas();
    }

    private void getZonas(){
        Call<List<Zona>> llamadaServicio = APIServicios.getConexion().getZonas();
        llamadaServicio.enqueue(new Callback<List<Zona>>() {
            @Override
            public void onResponse(Call<List<Zona>> call, Response<List<Zona>> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        muestraZonas( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al obtener las zonas" );
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Zona>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void muestraZonas(List<Zona> zonas){
        Zona zonaVacia = new Zona();
        zonaVacia.setId(0);
        zonaVacia.setDescripcion("Seleccionar Zona");
        zonas.add(0, zonaVacia);
        this.seleccionZona = this.vista.findViewById(R.id.seleccionZona);
        AdaptadorZona adaptadorZona = new AdaptadorZona(getContext(), zonas);
        this.seleccionZona.setAdapter(adaptadorZona);
        terminaProcesando();
    }

    private void validaAsignacion(){
        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        TextView campoNombre = this.vista.findViewById(R.id.campoNombre);

        if( !campoEscaner.getText().equals("") &&
                !campoNombre.getText().equals( getResources().getString(R.string.mensajeErrorEscaneo) ) &&
                !campoNombre.getText().equals("") &&
                ( (Zona) this.seleccionZona.getSelectedItem() ).getId() != 0 ){
            iniciaProcesando();
            guarda();
        }else{
            errorValidacion();
        }
    }

    private void guarda(){
        JsonObject json = new JsonObject();
        json.addProperty("idEstacion", getOperadorSeleccionado().getIdEstacion() );
        json.addProperty("libre", false );
        json.addProperty("idEmpleado", String.valueOf( getOperadorSeleccionado().getIdEmpleado() ) );
        json.addProperty("turno", UsuarioLogueado.getUsuarioLogueado().getTurno() );
        json.addProperty("idZona", ( (Zona) this.seleccionZona.getSelectedItem() ).getId() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaOperadorModulo(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        resultadoAsignacion();
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al asignar al operador" );
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

    public void resultadoAsignacion(){
        terminaProcesando();
        Fragment fragment = new Contenedor_Modulos().newInstance(0);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
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

    private void validaGafete(String codigo){
        if( codigo.length() >= 7 ){
            iniciaProcesando();
            Call<Gafete> llamadaServicio = APIServicios.getConexionPINSA().getGafeteUsuario(codigo);
            llamadaServicio.enqueue(new Callback<Gafete>() {
                @Override
                public void onResponse(Call<Gafete> call, Response<Gafete> response) {
                    if( isAdded() ){
                        if(response.code() == 200){
                            resultadoEscaneoGafete( response.body() );
                        }else{
                            terminaProcesando();
                            errorServicio("Error al obtener operador");
                        }
                    }
                }

                @Override
                public void onFailure(Call<Gafete> call, Throwable t) {
                    if( isAdded() ){
                        terminaProcesando();
                        errorServicio("Error al conectar con el servidor");
                    }
                }
            });
        }else{
            TextView campoNombre = this.vista.findViewById(R.id.campoNombre);
            campoNombre.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoNombre.setTextColor( getResources().getColor(R.color.noValido) );
        }
    }

    public void resultadoEscaneoGafete(Gafete resultadoGafete){
        TextView campoNombre = this.vista.findViewById(R.id.campoNombre);

        if( resultadoGafete.getResultado().equalsIgnoreCase("YES") ){
            campoNombre.setText( resultadoGafete.getEmpleado().getNom_trab().concat(" ")
                    .concat( resultadoGafete.getEmpleado().getAp_paterno() ) );
            campoNombre.setTextColor( getResources().getColor(R.color.siValido) );

            getOperadorSeleccionado().setIdEmpleado( resultadoGafete.getEmpleado().getCla_trab() );
        }else{
            campoNombre.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoNombre.setTextColor( getResources().getColor(R.color.noValido) );
        }

        terminaProcesando();
    }

    public void errorValidacion(){
        final AlertDialog ventanaEmergente;
        AlertDialog.Builder builder = new AlertDialog.Builder( getContext() );
        View vistaAsignar = getLayoutInflater().inflate(R.layout.dialog_mensaje_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        ventanaEmergente = builder.create();
        ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaEmergente.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText( "Es necesario capturar todos los campos" );

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton1);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ventanaEmergente.dismiss();
                    }
                });
            }
        });
        ventanaEmergente.show();
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
}

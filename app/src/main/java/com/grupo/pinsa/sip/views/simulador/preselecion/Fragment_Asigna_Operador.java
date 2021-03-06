package com.grupo.pinsa.sip.views.simulador.preselecion;

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
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.contenedores.Contenedor;
import com.grupo.pinsa.sip.views.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.views.simulador.modelo.ErrorServicio;
import com.grupo.pinsa.sip.views.simulador.modelo.OperadorBascula;
import com.grupo.pinsa.sip.views.simulador.modelo.Tina;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.Gafete;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Asigna_Operador extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Serializable mParam1;
    private Serializable mParam2;

    private View vista;

    private AlertDialog ventanaError;

    private OperadorBascula operadorSeleccionado;
    private List<Tina> listaTinas;

    private OnFragmentInteractionListener mListener;

    public Fragment_Asigna_Operador() {
    }

    public static Fragment_Asigna_Operador newInstance(Serializable param1, Serializable param2) {
        Fragment_Asigna_Operador fragment = new Fragment_Asigna_Operador();
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
            mParam1 = getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista=inflater.inflate(R.layout.fragment_asignar_operador, container, false);

        iniciaComponentes();
        return this.vista;
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

    public OperadorBascula getOperadorSeleccionado() {
        return operadorSeleccionado;
    }

    public void setOperadorSeleccionado(OperadorBascula operadorSeleccionado) {
        this.operadorSeleccionado = operadorSeleccionado;
    }

    private void iniciaComponentes(){
        setOperadorSeleccionado( (OperadorBascula) this.mParam1);
        this.listaTinas = (List<Tina>) this.mParam2;

        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor().newInstance(0);
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

        TextView etiquetaPosicion = this.vista.findViewById(R.id.etiquetaPosicion);
        etiquetaPosicion.setText( getOperadorSeleccionado().getEstacion() );

        TextView etiquetaBascula = this.vista.findViewById(R.id.etiquetaBascula);
        etiquetaBascula.setText( getEtiquetaBasculaOperador( getOperadorSeleccionado().getIdPreseleccionEstacion() ) );

        TextView etiquetaTinaPrincipal = this.vista.findViewById(R.id.etiquetaTinaPrincipal);
        etiquetaTinaPrincipal.setText( getTinaPrincipalOperador( getOperadorSeleccionado().getIdPosicionPrincipal() ) );

        TextView etiquetaSubtallaPrincipal = this.vista.findViewById(R.id.etiquetaSubtallaPrincipal);
        etiquetaSubtallaPrincipal.setText( getSubtallaPrincipalOperador( getOperadorSeleccionado().getIdPosicionPrincipal() ) );

        TextView etiquetaTinaSecundaria = this.vista.findViewById(R.id.etiquetaTinaSecundaria);
        etiquetaTinaSecundaria.setText( getTinaSecundariaOperador( getOperadorSeleccionado().getIdPosicionAlterna() ) );

        TextView etiquetaSubtallaSecundaria = this.vista.findViewById(R.id.etiquetaSubtallaSecundaria);
        etiquetaSubtallaSecundaria.setText( getSubtallaSecundariaOperador( getOperadorSeleccionado().getIdPosicionAlterna() ) );

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
    }

    private void validaAsignacion(){
        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        TextView campoNombre = this.vista.findViewById(R.id.campoNombre);

        if( !campoEscaner.getText().equals("") &&
                !campoNombre.getText().equals( getResources().getString(R.string.mensajeErrorEscaneo) ) &&
                !campoNombre.getText().equals("")){
            iniciaProcesando();
            getOperadorSeleccionado().setLibre(false);
            getOperadorSeleccionado().setTurno(true);
            if( UsuarioLogueado.getUsuarioLogueado().getTurno() == 1 ){
                getOperadorSeleccionado().setTurno(false);
            }

            guarda();
        }else{
            errorValidacion();
        }
    }

    private void guarda(){
        JsonObject json = new JsonObject();
        json.addProperty("idPreseleccionEstacion", getOperadorSeleccionado().getIdPreseleccionEstacion() );
        json.addProperty("estacion", getOperadorSeleccionado().getEstacion() );
        json.addProperty("idPosicionPrincipal", getOperadorSeleccionado().getIdPosicionPrincipal() );
        json.addProperty("idPosicionAlterna", getOperadorSeleccionado().getIdPosicionAlterna() );
        json.addProperty("idEmpleado", getOperadorSeleccionado().getIdEmpleado() );
        json.addProperty("turno", getOperadorSeleccionado().getTurno() );
        json.addProperty("libre", getOperadorSeleccionado().getLibre() );
        //json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaOperador(json);
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
        Fragment fragment = new Contenedor().newInstance(0);
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
                            errorServicio("Error al obtener el operador");
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

    public void errorServicio(ErrorServicio errorMensaje){
        String mensajeMostrar = errorMensaje.getMessage();
        if( errorMensaje.getMensaje() != null &&
                !errorMensaje.getMensaje().equalsIgnoreCase("") ){
            mensajeMostrar = errorMensaje.getMensaje();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_mensaje_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaError = builder.create();
        final String finalMensajeMostrar = mensajeMostrar;
        this.ventanaError.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaError.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText(finalMensajeMostrar);

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
                etiquetaMensaje.setText( "Es necesario capturar un operador" );

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

    private String getEtiquetaBasculaOperador(int posicion){
        switch (posicion){
            case 1: return "A9";
            case 2: return "A7";
            case 3: return "A5";
            case 4: return "A3";
            case 5: return "A1";
            case 6: return "B2";
            case 7: return "B4";
            case 8: return "B6";
            case 9: return "B8";
            case 10: return "B10";
        }
        return "";
    }

    private String getTinaPrincipalOperador(int posicionTina){
        for( Tina tina : this.listaTinas ){
            if( tina.getIdPreseleccionPosicionTina() == posicionTina ){
                return tina.getPosicion();
            }
        }
        return "";
    }

    private String getSubtallaPrincipalOperador(int posicionTina){
        for( Tina tina : this.listaTinas ){
            if( tina.getIdPreseleccionPosicionTina() == posicionTina ){
                if( !tina.getLibre() ){
                    return tina.getSubtalla().getDescripcion();
                }
                break;
            }
        }
        return "";
    }

    private String getTinaSecundariaOperador(int posicionTina){
        for( Tina tina : this.listaTinas ){
            if( tina.getIdPreseleccionPosicionTina() == posicionTina ){
                return tina.getPosicion();
            }
        }
        return "";
    }

    private String getSubtallaSecundariaOperador(int posicionTina){
        for( Tina tina : this.listaTinas ){
            if( tina.getIdPreseleccionPosicionTina() == posicionTina ){
                if( !tina.getLibre() ){
                    return tina.getSubtalla().getDescripcion();
                }
                break;
            }
        }
        return "";
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
}

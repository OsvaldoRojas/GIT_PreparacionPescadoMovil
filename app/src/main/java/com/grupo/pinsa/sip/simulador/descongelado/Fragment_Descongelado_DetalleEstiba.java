package com.grupo.pinsa.sip.simulador.descongelado;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Descongelado;
import com.grupo.pinsa.sip.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.simulador.modelo.PosicionEstibaDescongelado;
import com.grupo.pinsa.sip.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.simulador.modelo.servicio.RespuestaServicio;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Descongelado_DetalleEstiba extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private Serializable mParam1;

    private View vista;

    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private PosicionEstibaDescongelado posicionSeleccionada;

    private OnFragmentInteractionListener mListener;
    private CountDownTimer contadorMinutos;

    public Fragment_Descongelado_DetalleEstiba() {
    }

    public static Fragment_Descongelado_DetalleEstiba newInstance(Serializable param1) {
        Fragment_Descongelado_DetalleEstiba fragment = new Fragment_Descongelado_DetalleEstiba();
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
        this.vista=inflater.inflate(R.layout.fragment_descongelado_detalle_estiba, container, false);

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

    public PosicionEstibaDescongelado getPosicionSeleccionada() {
        return posicionSeleccionada;
    }

    public void setPosicionSeleccionada(PosicionEstibaDescongelado posicionSeleccionada) {
        this.posicionSeleccionada = posicionSeleccionada;
    }

    private void iniciaComponentes(){
        setPosicionSeleccionada( (PosicionEstibaDescongelado) this.mParam1 );

        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor_Descongelado().newInstance(0);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        Button botonCompleta = this.vista.findViewById(R.id.boton2);
        if( getPosicionSeleccionada().getCompleta() ){
            botonCompleta.setEnabled(false);
        }
        botonCompleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirma("¿Está seguro que desea completar la estiba?");
            }
        });

        final TextView temporizador = this.vista.findViewById(R.id.temporizador);
        String muestraTemporizador = "00:00";
        int tiempo = 0;
        if( getPosicionSeleccionada().getMinutos() > 0 ){
            muestraTemporizador = completaNumero( getPosicionSeleccionada().getMinutos() ).concat(":00");
            tiempo = ( getPosicionSeleccionada().getMinutos() * 60 ) * 1000;
        }
        temporizador.setText(muestraTemporizador);

        if( temporizador.getText().toString().equals("00:00") && getPosicionSeleccionada().getValvulaEncendida() ){
            temporizador.setTextColor( getResources().getColor(R.color.noValido) );
        }else{
            temporizador.setTextColor( getResources().getColor(R.color.siValido) );
        }

        this.contadorMinutos = new CountDownTimer(tiempo,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int tamano = temporizador.getText().length();
                int segundos = Integer.valueOf( temporizador.getText().toString().substring(tamano-2) );
                int minutos = Integer.valueOf( temporizador.getText().toString().substring(0,tamano-3) );
                if( segundos == 0 ){
                    segundos = 59;
                    minutos = minutos - 1;
                }else{
                    segundos = segundos - 1;
                }

                String muestraTemporizador = completaNumero(minutos).concat(":").concat( completaNumero(segundos) );
                temporizador.setText(muestraTemporizador);
            }
            @Override
            public void onFinish() {
                temporizador.setText("00:00");
                if( getPosicionSeleccionada().getValvulaEncendida() ){
                    temporizador.setTextColor( getResources().getColor(R.color.noValido) );
                }else{
                    temporizador.setTextColor( getResources().getColor(R.color.siValido) );
                }
            }
        }.start();

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        iniciaPosiciones();
    }

    private String completaNumero(int numero){
        String resultado = String.valueOf(numero);
        if(numero < 10){
            resultado = "0".concat(resultado);
        }
        return resultado;
    }

    @Override
    public void onPause() {
        this.contadorMinutos.cancel();
        super.onPause();
    }

    private void iniciaPosiciones(){
        final TextView etiquetaPosicion1 = this.vista.findViewById(R.id.etiquetaPosicion1);
        final TextView etiquetaPosicion2 = this.vista.findViewById(R.id.etiquetaPosicion2);
        final TextView etiquetaPosicion3 = this.vista.findViewById(R.id.etiquetaPosicion3);
        final TextView etiquetaPosicion4 = this.vista.findViewById(R.id.etiquetaPosicion4);
        final TextView etiquetaPosicion5 = this.vista.findViewById(R.id.etiquetaPosicion5);
        switch ( getPosicionSeleccionada().getConteoNivel() ){
            case 0:
                etiquetaPosicion1.setText("Posición 1 desocupada");
                etiquetaPosicion2.setText("Posición 2 desocupada");
                etiquetaPosicion3.setText("Posición 3 desocupada");
                etiquetaPosicion4.setText("Posición 4 desocupada");
                etiquetaPosicion5.setText("Posición 5 desocupada");

                etiquetaPosicion1.setOnClickListener(null);
                etiquetaPosicion2.setOnClickListener(null);
                etiquetaPosicion3.setOnClickListener(null);
                etiquetaPosicion4.setOnClickListener(null);
                etiquetaPosicion5.setOnClickListener(null);

                etiquetaPosicion1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                etiquetaPosicion2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                etiquetaPosicion3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                etiquetaPosicion4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                etiquetaPosicion5.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                break;
            case 1:
                etiquetaPosicion1.setText("Posición 1 ocupada");
                etiquetaPosicion2.setText("Posición 2 desocupada");
                etiquetaPosicion3.setText("Posición 3 desocupada");
                etiquetaPosicion4.setText("Posición 4 desocupada");
                etiquetaPosicion5.setText("Posición 5 desocupada");

                etiquetaPosicion1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirmaCambio();
                    }
                });
                etiquetaPosicion2.setOnClickListener(null);
                etiquetaPosicion3.setOnClickListener(null);
                etiquetaPosicion4.setOnClickListener(null);
                etiquetaPosicion5.setOnClickListener(null);

                etiquetaPosicion1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_done),null, null, null);
                etiquetaPosicion2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                etiquetaPosicion3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                etiquetaPosicion4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                etiquetaPosicion5.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                break;
            case 2:
                etiquetaPosicion1.setText("Posición 1 ocupada");
                etiquetaPosicion2.setText("Posición 2 ocupada");
                etiquetaPosicion3.setText("Posición 3 desocupada");
                etiquetaPosicion4.setText("Posición 4 desocupada");
                etiquetaPosicion5.setText("Posición 5 desocupada");

                etiquetaPosicion1.setOnClickListener(null);
                etiquetaPosicion2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirmaCambio();
                    }
                });
                etiquetaPosicion3.setOnClickListener(null);
                etiquetaPosicion4.setOnClickListener(null);
                etiquetaPosicion5.setOnClickListener(null);

                etiquetaPosicion1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_block),null, null, null);
                etiquetaPosicion2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_done),null, null, null);
                etiquetaPosicion3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                etiquetaPosicion4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                etiquetaPosicion5.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                break;
            case 3:
                etiquetaPosicion1.setText("Posición 1 ocupada");
                etiquetaPosicion2.setText("Posición 2 ocupada");
                etiquetaPosicion3.setText("Posición 3 ocupada");
                etiquetaPosicion4.setText("Posición 4 desocupada");
                etiquetaPosicion5.setText("Posición 5 desocupada");

                etiquetaPosicion1.setOnClickListener(null);
                etiquetaPosicion2.setOnClickListener(null);
                etiquetaPosicion3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirmaCambio();
                    }
                });
                etiquetaPosicion4.setOnClickListener(null);
                etiquetaPosicion5.setOnClickListener(null);

                etiquetaPosicion1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_block),null, null, null);
                etiquetaPosicion2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_block),null, null, null);
                etiquetaPosicion3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_done),null, null, null);
                etiquetaPosicion4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                etiquetaPosicion5.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                break;
            case 4:
                etiquetaPosicion1.setText("Posición 1 ocupada");
                etiquetaPosicion2.setText("Posición 2 ocupada");
                etiquetaPosicion3.setText("Posición 3 ocupada");
                etiquetaPosicion4.setText("Posición 4 ocupada");
                etiquetaPosicion5.setText("Posición 5 desocupada");

                etiquetaPosicion1.setOnClickListener(null);
                etiquetaPosicion2.setOnClickListener(null);
                etiquetaPosicion3.setOnClickListener(null);
                etiquetaPosicion4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirmaCambio();
                    }
                });
                etiquetaPosicion5.setOnClickListener(null);

                etiquetaPosicion1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_block),null, null, null);
                etiquetaPosicion2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_block),null, null, null);
                etiquetaPosicion3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_block),null, null, null);
                etiquetaPosicion4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_done),null, null, null);
                etiquetaPosicion5.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_radio),null, null, null);
                break;
            case 5:
                etiquetaPosicion1.setText("Posición 1 ocupada");
                etiquetaPosicion2.setText("Posición 2 ocupada");
                etiquetaPosicion3.setText("Posición 3 ocupada");
                etiquetaPosicion4.setText("Posición 4 ocupada");
                etiquetaPosicion5.setText("Posición 5 ocupada");

                etiquetaPosicion1.setOnClickListener(null);
                etiquetaPosicion2.setOnClickListener(null);
                etiquetaPosicion3.setOnClickListener(null);
                etiquetaPosicion4.setOnClickListener(null);
                etiquetaPosicion5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirmaCambio();
                    }
                });

                etiquetaPosicion1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_block),null, null, null);
                etiquetaPosicion2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_block),null, null, null);
                etiquetaPosicion3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_block),null, null, null);
                etiquetaPosicion4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_block),null, null, null);
                etiquetaPosicion5.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_done),null, null, null);
                break;
        }
    }

    private void confirmaCambio(){
        if( UsuarioLogueado.getUsuarioLogueado().isMostrarMensaje() ){
            AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View vistaAsignar = inflater.inflate(R.layout.dialog_decision_opcional, null);
            builder.setCancelable(false);
            builder.setView(vistaAsignar);

            this.ventanaEmergente = builder.create();
            this.ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    TextView etiquetaMensaje = ventanaEmergente.findViewById(R.id.etiquetaMensaje);
                    etiquetaMensaje.setText("¿Cambiar estatus a desocupada?");

                    Button botonAceptar = ventanaEmergente.findViewById(R.id.boton2);
                    botonAceptar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CheckBox validarMensaje = ventanaEmergente.findViewById(R.id.desicionValidacion);
                            if( validarMensaje.isChecked() ){
                                UsuarioLogueado.getUsuarioLogueado().setMostrarMensaje(false);
                            }
                            iniciaProcesando();
                            liberaTina();
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
        }else{
            iniciaProcesando();
            liberaTina();
        }
    }

    private void confirma(final String mensaje){
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
                etiquetaMensaje.setText(mensaje);

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton2);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iniciaProcesando();
                        completaPosicion();
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

    private void completaPosicion(){
        JsonObject json = new JsonObject();
        json.addProperty("idDescongeladoPosicionTina", getPosicionSeleccionada().getIdPosicion() );
        //json.addProperty("bloqueado", true );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().completaPosicion(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        resultadoPosicionCompleta();
                        terminaProcesando();
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al completar la posición" );
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

    private void liberaTina(){
        JsonObject json = new JsonObject();
        json.addProperty("idDescongeladoPosicionTina", getPosicionSeleccionada().getIdPosicion() );
        json.addProperty("nivel", getPosicionSeleccionada().getConteoNivel() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().liberaTinaPosicion(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 && response.body().getCodigo() == 0 ){
                        if( response.body().getMensaje().equalsIgnoreCase("La tina no ha terminado su proceso de descongelado") ){
                            terminaProcesando();
                            errorServicio( response.body().getMensaje() );
                        }else{
                            resultadoLiberaTina();
                            terminaProcesando();
                        }
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al liberar la tina" );
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

    private void resultadoPosicionCompleta(){
        Button botonCompleta = this.vista.findViewById(R.id.boton2);
        botonCompleta.setEnabled(false);
    }

    private void resultadoLiberaTina(){
        getPosicionSeleccionada().setConteoNivel( getPosicionSeleccionada().getConteoNivel()-1 );
        iniciaPosiciones();
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
}

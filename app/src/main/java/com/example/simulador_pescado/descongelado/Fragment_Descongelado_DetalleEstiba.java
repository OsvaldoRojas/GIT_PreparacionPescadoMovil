package com.example.simulador_pescado.descongelado;

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

import com.example.simulador_pescado.conexion.APIServicios;
import com.example.simulador_pescado.contenedores.Contenedor_Descongelado;
import com.example.simulador_pescado.R;
import com.example.simulador_pescado.utilerias.Utilerias;
import com.example.simulador_pescado.vista.PosicionEstibaAtemperado;
import com.example.simulador_pescado.vista.PosicionEstibaDescongelado;
import com.example.simulador_pescado.vista.UsuarioLogueado;
import com.example.simulador_pescado.vista.servicio.LiberaTinaPosicion;
import com.example.simulador_pescado.vista.servicio.PosicionCompleta;
import com.example.simulador_pescado.vista.servicio.RespuestaServicio;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Descongelado_DetalleEstiba extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Serializable mParam1;

    private View vista;

    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private PosicionEstibaDescongelado posicionSeleccionada;

    private OnFragmentInteractionListener mListener;

    public Fragment_Descongelado_DetalleEstiba() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment Fragment_Preselecion_Tinas.
     */
    // TODO: Rename and change types and number of parameters
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
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
                iniciaProcesando();
                completaPosicion();
            }
        });

        final TextView temporizador = this.vista.findViewById(R.id.temporizador);
        String muestraTemporizador = completaNumero( getPosicionSeleccionada().getMinutos() ).concat(":00");
        temporizador.setText(muestraTemporizador);

        int tiempo = ( getPosicionSeleccionada().getMinutos() * 60 ) * 1000;
        new CountDownTimer(tiempo,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int segundos = Integer.valueOf( temporizador.getText().toString().substring(3) );
                int minutos = Integer.valueOf( temporizador.getText().toString().substring(0,2) );
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
        if( UsuarioLogueado.getUsuarioLogueado(null).isMostrarMensaje() ){
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
                                UsuarioLogueado.getUsuarioLogueado(null).setMostrarMensaje(false);
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

    private void completaPosicion(){
        PosicionCompleta posicionCompleta = new PosicionCompleta();
        posicionCompleta.setIdPosicion( getPosicionSeleccionada().getIdPosicion() );
        posicionCompleta.setCompleta(true);
        posicionCompleta.setUsuario( UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().completaPosicion(posicionCompleta);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                RespuestaServicio respuesta = response.body();
                if( response.code() == 200 && respuesta.getCodigo() == 0 ){
                    resultadoPosicionCompleta();
                    terminaProcesando();
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

    private void liberaTina(){
        LiberaTinaPosicion tinaLiberada = new LiberaTinaPosicion();
        tinaLiberada.setIdPosicion( getPosicionSeleccionada().getIdPosicion() );
        tinaLiberada.setNivel( getPosicionSeleccionada().getConteoNivel() );
        tinaLiberada.setUsuario( UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().liberaTinaPosicion(tinaLiberada);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                RespuestaServicio respuesta = response.body();
                if( response.code() == 200 && respuesta.getCodigo() == 0 ){
                    if( respuesta.getMensaje().equalsIgnoreCase("La tina no ha terminado su proceso de descongelado") ){
                        terminaProcesando();
                        errorServicio( respuesta.getMensaje() );
                    }else{
                        resultadoLiberaTina();
                        terminaProcesando();
                    }
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

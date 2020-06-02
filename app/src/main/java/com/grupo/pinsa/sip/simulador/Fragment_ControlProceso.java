package com.grupo.pinsa.sip.simulador;

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

import com.grupo.pinsa.sip.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.simulador.modelo.TinaProceso;
import com.grupo.pinsa.sip.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.simulador.modelo.servicio.RespuestaServicio;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ControlProceso extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View vista;

    private AlertDialog ventanaError;
    private AlertDialog ventanaMensaje;

    private OnFragmentInteractionListener mListener;

    public Fragment_ControlProceso() {
    }

    public static Fragment_ControlProceso newInstance(String param1, String param2) {
        Fragment_ControlProceso fragment = new Fragment_ControlProceso();
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
        this.vista = inflater.inflate(R.layout.fragment_control_proceso, container, false);
        iniciaComponente();
        return this.vista;
    }

    private void iniciaComponente(){
        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaComponentes();
            }
        });

        Button botonAceptar = this.vista.findViewById(R.id.boton2);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaGuardado();
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
                validaTina( editable.toString() );
            }
        });
    }

    private void validaGuardado(){
        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        TextView campoEtapaActual = this.vista.findViewById(R.id.campoEtapaActual);
        EditText campoPeso = this.vista.findViewById(R.id.campoPeso);

        if( campoEscaner.getText().toString().equalsIgnoreCase("") ||
                campoEtapaActual.getText().toString().equalsIgnoreCase("") ){
            errorServicio("Es necesario capturar una tina valida");
        }else{
            if( campoPeso.getText().toString().equalsIgnoreCase("") ){
                errorServicio("Es necesario capturar un peso");
            }else{
                iniciaProcesando();
                guarda();
            }
        }
    }

    private void guarda(){
        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        TextView campoPosicion = this.vista.findViewById(R.id.campoPosicion);
        TextView campoEtapaActual = this.vista.findViewById(R.id.campoEtapaActual);
        TextView campoEtapaSiguiente = this.vista.findViewById(R.id.campoEtapaSiguiente);
        EditText campoPeso = this.vista.findViewById(R.id.campoPeso);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("idTina", campoEscaner.getText().toString() );
        jsonObject.addProperty("peso", campoPeso.getText().toString() );
        jsonObject.addProperty("posicion", campoPosicion.getText().toString() );
        jsonObject.addProperty("etapaActual", campoEtapaActual.getText().toString() );
        jsonObject.addProperty("etapaSiguiente", campoEtapaSiguiente.getText().toString() );
        jsonObject.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaPeso(jsonObject);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    terminaProcesando();
                    if(response.code() == 200){
                        if(response.body() != null){
                            RespuestaServicio resultado = response.body();
                            if(resultado.getColumna() != null && !resultado.getColumna().equals("")){
                                muestraMensaje(
                                        resultado.getFila().concat( resultado.getColumna() )
                                                .concat("-").concat( resultado.getNivel() )
                                );
                            }else{
                                muestraMensaje("Guardado exitosamente");
                            }
                        }else{
                            errorServicio( "Error al guardar peso" );
                        }
                    }else{
                        errorServicio( "Error al guardar peso" );
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

    private void muestraCampos(RespuestaServicio respuestaServicio){
        TextView campoPosicion = this.vista.findViewById(R.id.campoPosicion);
        campoPosicion.setText(
                respuestaServicio.getFila().concat( respuestaServicio.getColumna() ).concat( respuestaServicio.getNivel() )
        );
    }

    private void limpiaComponentes(){
        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        TextView campoPosicion = this.vista.findViewById(R.id.campoPosicion);
        TextView campoEtapaActual = this.vista.findViewById(R.id.campoEtapaActual);
        TextView campoEtapaSiguiente = this.vista.findViewById(R.id.campoEtapaSiguiente);
        EditText campoPeso = this.vista.findViewById(R.id.campoPeso);

        campoEscaner.setText("");
        campoPosicion.setText("");
        campoEtapaActual.setText("");
        campoEtapaSiguiente.setText("");
        campoPeso.setText("");
    }

    private void validaTina(String codigo){
        if( codigo.length() >= 3 ){
            iniciaProcesando();
            Call<TinaProceso> llamadaServicio = APIServicios.getConexion().getTinaProceso(codigo);
            llamadaServicio.enqueue(new Callback<TinaProceso>() {
                @Override
                public void onResponse(Call<TinaProceso> call, Response<TinaProceso> response) {
                    if( isAdded() ){
                        if(response.code() == 200){
                            resultadoTinaProceso( response.body() );
                        }else{
                            terminaProcesando();
                            errorServicio("Error al validar la tina");
                        }
                    }
                }

                @Override
                public void onFailure(Call<TinaProceso> call, Throwable t) {
                    if( isAdded() ){
                        terminaProcesando();
                        errorServicio("Error al conectar con el servidor");
                    }
                }
            });
        }else{
            TextView campoEscaner = this.vista.findViewById(R.id.campoEscaner);
            campoEscaner.setTextColor( getResources().getColor(R.color.noValido) );
        }
    }

    private void resultadoTinaProceso(TinaProceso tina){
        TextView campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        TextView campoPosicion = this.vista.findViewById(R.id.campoPosicion);
        TextView campoEtapaActual = this.vista.findViewById(R.id.campoEtapaActual);
        TextView campoEtapaSiguiente = this.vista.findViewById(R.id.campoEtapaSiguiente);
        TextView campoPeso = this.vista.findViewById(R.id.campoPeso);
        if( tina.getId() == null ){
            campoEscaner.setTextColor( getResources().getColor(R.color.noValido) );

            campoPosicion.setText("");
            campoEtapaActual.setText("");
            campoEtapaSiguiente.setText("");
            campoPeso.setText("");
        }else{
            campoEscaner.setTextColor( getResources().getColor(R.color.siValido) );

            campoPosicion.setText( tina.getPosicion() );
            campoEtapaActual.setText( tina.getEtapaActual() );
            campoEtapaSiguiente.setText( tina.getEtapaSiguiente() );
            campoPeso.setText( String.valueOf( tina.getPeso() ) );
        }

        terminaProcesando();
    }

    public void muestraMensaje(final String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_mensaje_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaMensaje = builder.create();
        this.ventanaMensaje.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaMensaje.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText(mensaje);

                Button botonAceptar = ventanaMensaje.findViewById(R.id.boton1);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        limpiaComponentes();
                        ventanaMensaje.dismiss();
                    }
                });
            }
        });
        this.ventanaMensaje.show();
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

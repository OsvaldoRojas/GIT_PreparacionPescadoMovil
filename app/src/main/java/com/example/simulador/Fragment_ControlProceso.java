package com.example.simulador;

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

import com.example.simulador.conexion.APIServicios;
import com.example.simulador.utilerias.Utilerias;
import com.example.simulador.vista.TinaProceso;
import com.example.simulador.vista.UsuarioLogueado;
import com.example.simulador.vista.servicio.RespuestaServicio;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ControlProceso extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View vista;

    private AlertDialog ventanaError;

    private OnFragmentInteractionListener mListener;

    public Fragment_ControlProceso() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Descongelado_TiempoMuerto.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
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
        jsonObject.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaPeso(jsonObject);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                terminaProcesando();
                if(response.code() == 200){
                    limpiaComponentes();
                }else{
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
        if( codigo.length() >= 15 ){
            iniciaProcesando();
            Call<TinaProceso> llamadaServicio = APIServicios.getConexion().getTinaProceso( Long.valueOf(codigo) );
            llamadaServicio.enqueue(new Callback<TinaProceso>() {
                @Override
                public void onResponse(Call<TinaProceso> call, Response<TinaProceso> response) {
                    if(response.code() == 200){
                        resultadoTinaProceso( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error interno del servidor");
                    }
                }

                @Override
                public void onFailure(Call<TinaProceso> call, Throwable t) {
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
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
        if( tina.getId() == null ){
            campoEscaner.setTextColor( getResources().getColor(R.color.noValido) );

            campoPosicion.setText("");
            campoEtapaActual.setText("");
            campoEtapaSiguiente.setText("");
        }else{
            campoEscaner.setTextColor( getResources().getColor(R.color.siValido) );

            campoPosicion.setText( tina.getPosicion() );
            campoEtapaActual.setText( tina.getEtapaActual() );
            campoEtapaSiguiente.setText( tina.getEtapaSiguiente() );
        }

        terminaProcesando();
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
}

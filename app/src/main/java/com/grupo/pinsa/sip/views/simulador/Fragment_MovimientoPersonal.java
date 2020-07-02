package com.grupo.pinsa.sip.views.simulador;

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
import com.grupo.pinsa.sip.views.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.views.simulador.modelo.EmpleadoEstacion;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.Gafete;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_MovimientoPersonal extends Fragment {

    private View vista;

    private AlertDialog ventanaError;
    private EmpleadoEstacion empleadoEstacion;

    private OnFragmentInteractionListener mListener;

    public Fragment_MovimientoPersonal() {
    }

    public EmpleadoEstacion getEmpleadoEstacion() {
        return empleadoEstacion;
    }

    public void setEmpleadoEstacion(EmpleadoEstacion empleadoEstacion) {
        this.empleadoEstacion = empleadoEstacion;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_movimiento_personal, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        Button botonLiberar = this.vista.findViewById(R.id.liberar);
        botonLiberar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liberar();
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
            TextView campoArea = this.vista.findViewById(R.id.campoAreaPertenece);
            TextView campoPuesto = this.vista.findViewById(R.id.campoPuestoPertenece);
            TextView campoEstacion = this.vista.findViewById(R.id.campoEstacion);
            campoNombre.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoNombre.setTextColor( getResources().getColor(R.color.noValido) );
            campoArea.setText("");
            campoPuesto.setText("");
            campoEstacion.setText("");
        }
    }

    public void resultadoEscaneoGafete(Gafete resultadoGafete){
        TextView campoNombre = this.vista.findViewById(R.id.campoNombre);
        TextView campoArea = this.vista.findViewById(R.id.campoAreaPertenece);
        TextView campoPuesto = this.vista.findViewById(R.id.campoPuestoPertenece);

        if( resultadoGafete.getResultado().equalsIgnoreCase("YES") ){
            campoNombre.setText( resultadoGafete.getEmpleado().getNom_trab().concat(" ")
                    .concat( resultadoGafete.getEmpleado().getAp_paterno() ) );
            campoNombre.setTextColor( getResources().getColor(R.color.siValido) );

            campoArea.setText( resultadoGafete.getEmpleado().getAreaPertenece() );
            campoPuesto.setText( resultadoGafete.getEmpleado().getPuestoPertenece() );

            getEstacion();
        }else{
            campoNombre.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoNombre.setTextColor( getResources().getColor(R.color.noValido) );
            campoArea.setText("");
            campoPuesto.setText("");
            terminaProcesando();
        }
    }

    private void getEstacion(){
        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        Call<EmpleadoEstacion> llamadaServicio = APIServicios.getConexion()
                .getEmpleadoEstacion( campoEscaner.getText().toString() );
        llamadaServicio.enqueue(new Callback<EmpleadoEstacion>() {
            @Override
            public void onResponse(Call<EmpleadoEstacion> call, Response<EmpleadoEstacion> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        setEmpleadoEstacion( response.body() );
                        muestraResultado();
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener la estación del operador");
                    }
                }
            }

            @Override
            public void onFailure(Call<EmpleadoEstacion> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }


    private void muestraResultado(){
        TextView campoEstacion = this.vista.findViewById(R.id.campoEstacion);
        campoEstacion.setText( getEmpleadoEstacion().getEtapa() );

        Button botonLiberar = this.vista.findViewById(R.id.liberar);
        if( getEmpleadoEstacion().getIdEstacion() == 0 ){
            botonLiberar.setVisibility(View.INVISIBLE);
            campoEstacion.setTextColor( getResources().getColor(R.color.noValido) );
        }else{
            botonLiberar.setVisibility(View.VISIBLE);
            campoEstacion.setTextColor( getResources().getColor(R.color.siValido) );
        }
        terminaProcesando();
    }

    private void liberar(){
        iniciaProcesando();
        switch ( getEmpleadoEstacion().getEtapa() ){
            case "PRESELECCION":
                if( !getEmpleadoEstacion().getPosicionPrincipal().equalsIgnoreCase("") ){
                    liberaOperadorPreseleccion();
                }else{
                    liberaMontacargasPreseleccion();
                }
                break;
            case "EVISCERADO":
                liberaOperadorEviscerado();
                break;
            case "EMPARRILLADO":
                liberaOperadorEmparrillado();
                break;
            case "COCIMIENTO":
                liberaOperadorCocimiento();
                break;
            case "MODULOS":
                liberaOperadorModulos();
                break;
            case "LAVADO DE CARROS":
                liberaOperadorLavadoCarros();
                break;
        }
    }

    private void liberaOperadorPreseleccion(){
        JsonObject json = new JsonObject();
        json.addProperty("idPreseleccionEstacion", getEmpleadoEstacion().getIdEstacion() );
        json.addProperty("estacion", getEmpleadoEstacion().getEstacion() );
        json.addProperty("idPosicionPrincipal", getEmpleadoEstacion().getPosicionPrincipal() );
        json.addProperty("idPosicionAlterna", getEmpleadoEstacion().getPosicionAlterna() );
        json.addProperty("idEmpleado", 0 );
        json.addProperty("turno", getEmpleadoEstacion().getTurno() );
        json.addProperty("libre", true );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaOperador(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    terminaProcesando();
                    if( response.code() == 200 ){
                        ventanaMensaje("El operador fue liberado exitosamente");
                    }else{
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

    private void liberaMontacargasPreseleccion(){
        JsonObject json = new JsonObject();
        json.addProperty("idPreseleccionMontacarga", getEmpleadoEstacion().getIdEstacion() );
        json.addProperty("idEmpleado", 0 );
        json.addProperty("turno", getEmpleadoEstacion().getTurno() );
        json.addProperty("libre", true );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaMontacargas(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    terminaProcesando();
                    if( response.code() == 200 ){
                        ventanaMensaje("El operador fue liberado exitosamente");
                    }else{
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

    private void liberaOperadorEviscerado(){
        JsonObject json = new JsonObject();
        json.addProperty("idEstacion", getEmpleadoEstacion().getIdEstacion() );
        json.addProperty("libre", true );
        json.addProperty("idEmpleado", "0" );
        json.addProperty("turno", getEmpleadoEstacion().getTurno() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaOperadorEviscerado(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    terminaProcesando();
                    if( response.code() == 200 ){
                        ventanaMensaje("El operador fue liberado exitosamente");
                    }else{
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

    private void liberaOperadorEmparrillado(){
        JsonObject json = new JsonObject();
        json.addProperty("idEstacion", getEmpleadoEstacion().getIdEstacion() );
        json.addProperty("libre", true );
        json.addProperty("idEmpleado", "0" );
        json.addProperty("turno", getEmpleadoEstacion().getTurno() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaOperadorEmparrillado(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    terminaProcesando();
                    if( response.code() == 200 ){
                        ventanaMensaje("El operador fue liberado exitosamente");
                    }else{
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

    private void liberaOperadorCocimiento(){
        JsonObject json = new JsonObject();
        json.addProperty("idEstacion", getEmpleadoEstacion().getIdEstacion() );
        json.addProperty("libre", true );
        json.addProperty("idEmpleado", "0" );
        json.addProperty("turno", getEmpleadoEstacion().getTurno() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaOperadorCocimiento(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        ventanaMensaje("El operador fue liberado exitosamente");
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

    private void liberaOperadorModulos(){
        JsonObject json = new JsonObject();
        json.addProperty("idEstacion", getEmpleadoEstacion().getIdEstacion() );
        json.addProperty("libre", true );
        json.addProperty("idEmpleado", "0" );
        json.addProperty("turno", UsuarioLogueado.getUsuarioLogueado().getTurno() );
        json.addProperty("idZona", 1 );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaOperadorModulo(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        ventanaMensaje("El operador fue liberado exitosamente");
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

    private void liberaOperadorLavadoCarros(){
        JsonObject json = new JsonObject();
        json.addProperty("idEstacion", getEmpleadoEstacion().getIdEstacion() );
        json.addProperty("libre", true );
        json.addProperty("idEmpleado", "0" );
        json.addProperty("turno", getEmpleadoEstacion().getTurno() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaOperadorLavado(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        ventanaMensaje("El operador fue liberado exitosamente");
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
                        EditText campoEscaner = vista.findViewById(R.id.campoEscaner);
                        validaGafete( campoEscaner.getText().toString() );
                    }
                });
            }
        });
        this.ventanaError.show();
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

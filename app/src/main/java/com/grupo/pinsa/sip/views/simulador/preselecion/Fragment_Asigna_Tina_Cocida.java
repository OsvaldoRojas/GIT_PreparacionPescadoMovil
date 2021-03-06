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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorCocida;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.contenedores.Contenedor;
import com.grupo.pinsa.sip.views.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.views.simulador.modelo.Cocida;
import com.grupo.pinsa.sip.views.simulador.modelo.Tina;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.TinaEscaneo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Asigna_Tina_Cocida extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private boolean tinaValida = false;
    private Boolean nuevo;

    private View vista;

    private AlertDialog ventanaError;
    private RecyclerView vistaLista;

    private Tina tinaSeleccionada;

    private List<Cocida> cocidas;

    private OnFragmentInteractionListener mListener;

    public Fragment_Asigna_Tina_Cocida() {
    }

    public Boolean getNuevo() {
        return nuevo;
    }

    public void setNuevo(Boolean nuevo) {
        this.nuevo = nuevo;
    }

    public static Fragment_Asigna_Tina_Cocida newInstance(Serializable param1, Boolean param2) {
        Fragment_Asigna_Tina_Cocida fragment = new Fragment_Asigna_Tina_Cocida();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putBoolean(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setTinaSeleccionada( (Tina) getArguments().getSerializable(ARG_PARAM1) );
            setNuevo( getArguments().getBoolean(ARG_PARAM2) );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista=inflater.inflate(R.layout.fragment_asignar_tina_cocida, container, false);

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

    public Tina getTinaSeleccionada() {
        return tinaSeleccionada;
    }

    public void setTinaSeleccionada(Tina tinaSeleccionada) {
        this.tinaSeleccionada = tinaSeleccionada;
    }

    private void iniciaComponentes() {
        iniciaProcesando();

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

        TextView etiquetaPosicion = this.vista.findViewById(R.id.etiquetaPosicion);
        etiquetaPosicion.setText(
                etiquetaPosicion.getText().toString().concat(" ")
                        .concat( getTinaSeleccionada().getPosicion() )
        );

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

        if( !getNuevo() ){
            botonCancelar.setText(R.string.volver);
            botonAceptar.setVisibility(View.GONE);
            etiquetaPosicion.setText( "Tina ".concat( getTinaSeleccionada().getPosicion() ) );
            campoEscaner.setEnabled(false);
            campoEscaner.setText( getTinaSeleccionada().getTina().getIdTina() );
        }

        getCocidas();
    }

    private void validaAsignacion(){
        if(this.tinaValida){
                Cocida cocidaSeleccionada = getAsignacionCocida();
                if( cocidaSeleccionada == null ){
                    errorValidacion("Es necesario seleccionar una asignación cocida");
                }else{
                    iniciaProcesando();
                    getTinaSeleccionada().setLibre(false);
                    getTinaSeleccionada().setTurno(true);
                    if( UsuarioLogueado.getUsuarioLogueado().getTurno() == 1 ){
                        getTinaSeleccionada().setTurno(false);
                    }
                    getTinaSeleccionada().setSubtalla( cocidaSeleccionada.getSubtalla() );
                    getTinaSeleccionada().setTalla( cocidaSeleccionada.getTalla() );
                    getTinaSeleccionada().setGrupoEspecie( cocidaSeleccionada.getEspecie() );
                    getTinaSeleccionada().setEspecialidad( cocidaSeleccionada.getEspecialiad() );

                    guarda( cocidaSeleccionada.getId() );
                }
        }else{
            errorValidacion("Es necesario capturar una tina");
        }
    }

    private void guarda(int idCocida){
        JsonObject json = new JsonObject();
        json.addProperty("idPreseleccionPosicionTina", getTinaSeleccionada().getIdPreseleccionPosicionTina() );
        json.addProperty("idAsignacionCocida", idCocida);
        json.addProperty("idTina", getTinaSeleccionada().getTina().getDescripcion() );
        json.addProperty("idEspecie", getTinaSeleccionada().getGrupoEspecie().getIdEspecie() );
        json.addProperty("idTalla", getTinaSeleccionada().getTalla().getIdTalla() );
        json.addProperty("idSubtalla", getTinaSeleccionada().getSubtalla().getIdSubtalla() );
        json.addProperty("idEspecialidad", getTinaSeleccionada().getEspecialidad().getIdEspecialidad() );
        json.addProperty("npiezas", getTinaSeleccionada().getNpiezas() );
        json.addProperty("peso", getTinaSeleccionada().getPeso() );
        json.addProperty("libre", getTinaSeleccionada().getLibre() );
        json.addProperty("turno", getTinaSeleccionada().getTurno() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().asignaTina(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        resultadoAsignacion();
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al asignar la tina" );
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

    private Cocida getAsignacionCocida(){
        for(Cocida cocida : this.cocidas){
            if( cocida.isSeleccionado() ){
                return cocida;
            }
        }
        return null;
    }

    public void errorValidacion(final String mensaje){
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
                etiquetaMensaje.setText(mensaje);

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

    private void getCocidas(){
        Call<List<Cocida>> llamadaServicio = APIServicios.getConexion().getAsignacionCocida();
        llamadaServicio.enqueue(new Callback<List<Cocida>>() {
            @Override
            public void onResponse(Call<List<Cocida>> call, Response<List<Cocida>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        resultadoCocidas( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener las cocidas");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Cocida>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void resultadoCocidas(List<Cocida> cocidas){
        if( isAdded() ){
            this.cocidas = new ArrayList<>();
            if( !getNuevo() ){
                for( Cocida cocida : cocidas ){
                    if( cocida.getId() == getTinaSeleccionada().getIdAsignacionCocida() ){
                        this.cocidas.add(cocida);
                        break;
                    }
                }
            }else{
                this.cocidas.addAll(cocidas);
            }

            TextView sinResultado = this.vista.findViewById(R.id.sinResultados);
            if( !this.cocidas.isEmpty() ) {
                sinResultado.setVisibility(View.GONE);

                this.vistaLista = this.vista.findViewById(R.id.listaTinas);
                this.vistaLista.setHasFixedSize(true);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                this.vistaLista.setLayoutManager(layoutManager);

                AdaptadorCocida adaptador = new AdaptadorCocida(this.cocidas, this);
                this.vistaLista.setAdapter(adaptador);
            }else{
                sinResultado.setVisibility(View.VISIBLE);
            }

            terminaProcesando();
        }
    }

    private void validaTina(String codigo){
        this.tinaValida = false;
        if( codigo.length() >= 3 ){
            iniciaProcesando();
            Call<TinaEscaneo> llamadaServicio = APIServicios.getConexion().getTina(codigo);
            llamadaServicio.enqueue(new Callback<TinaEscaneo>() {
                @Override
                public void onResponse(Call<TinaEscaneo> call, Response<TinaEscaneo> response) {
                    if( isAdded() ){
                        if(response.code() == 200){
                            resultadoEscaneoTina( response.body() );
                        }else{
                            terminaProcesando();
                            errorServicio("Error al obtener la tina");
                        }
                    }
                }

                @Override
                public void onFailure(Call<TinaEscaneo> call, Throwable t) {
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

    public void resultadoEscaneoTina(TinaEscaneo resultadoTina){
        TextView campoEscaner = this.vista.findViewById(R.id.campoEscaner);

        if( resultadoTina.getIdTinaDes() != null ){
            this.tinaValida = true;
            campoEscaner.setTextColor( getResources().getColor(R.color.siValido) );

            getTinaSeleccionada().getTina().setIdTina( resultadoTina.getIdTinaDes() );
            getTinaSeleccionada().getTina().setDescripcion( resultadoTina.getTinaDes() );
        } else{
            campoEscaner.setTextColor( getResources().getColor(R.color.noValido) );
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
}

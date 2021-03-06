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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorEspecialidad;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorGrupoEspecie;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorSubtalla;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorTalla;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.contenedores.Contenedor;
import com.grupo.pinsa.sip.views.simulador.modelo.Bascula;
import com.grupo.pinsa.sip.views.simulador.utilerias.Catalogos;
import com.grupo.pinsa.sip.views.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.views.simulador.modelo.ErrorServicio;
import com.grupo.pinsa.sip.views.simulador.modelo.Especialidad;
import com.grupo.pinsa.sip.views.simulador.modelo.GrupoEspecie;
import com.grupo.pinsa.sip.views.simulador.modelo.Subtalla;
import com.grupo.pinsa.sip.views.simulador.modelo.Talla;
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

public class Fragment_Asigna_Tina extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View vista;
    private AlertDialog ventanaError;
    private Spinner seleccionTalla;
    private Spinner seleccionSubtalla;
    private Spinner seleccionEspecie;
    private Spinner seleccionEspecialidad;

    private boolean tinaValida = false;
    private boolean precarga = false;
    private Boolean nuevo;

    private Tina tinaSeleccionada;
    private AdaptadorTalla adaptadorTalla;
    private AdaptadorSubtalla adaptadorSubtalla;

    private List<Talla> catalogoTalla;
    private List<Subtalla> catalogoSubtalla;

    private OnFragmentInteractionListener mListener;

    public Fragment_Asigna_Tina() {
    }

    public Boolean getNuevo() {
        return nuevo;
    }

    public void setNuevo(Boolean nuevo) {
        this.nuevo = nuevo;
    }

    public boolean isPrecarga() {
        return precarga;
    }

    public void setPrecarga(boolean precarga) {
        this.precarga = precarga;
    }

    public static Fragment_Asigna_Tina newInstance(Serializable param1, Boolean param2) {
        Fragment_Asigna_Tina fragment = new Fragment_Asigna_Tina();
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
        this.vista=inflater.inflate(R.layout.fragment_asignar_tina, container, false);

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

    private void iniciaComponentes(){
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

        Button botonPrecargar = this.vista.findViewById(R.id.botonPrecargar);
        botonPrecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargaValoresIniciales();
            }
        });

        this.seleccionTalla = vista.findViewById(R.id.seleccionTalla);
        this.seleccionTalla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                iniciaProcesando();
                Spinner seleccionSubtalla = vista.findViewById(R.id.seleccionSubtalla);
                seleccionSubtalla.setSelection(0);
                if( catalogoTalla.get(i).getIdTalla() > 0 ){
                    getSubtallas( catalogoTalla.get(i).getIdTalla() );
                }else{
                    catalogoSubtalla.removeAll( Catalogos.getInstancia().getCatalogoSubtalla() );
                    Catalogos.getInstancia().setCatalogoSubtalla(new ArrayList<Subtalla>());
                    catalogoSubtalla.addAll( Catalogos.getInstancia().getCatalogoSubtalla() );
                    adaptadorSubtalla.notifyDataSetChanged();
                    terminaProcesando();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        this.catalogoTalla = Catalogos.getInstancia().getCatalogoTalla();
        this.adaptadorTalla = new AdaptadorTalla( getContext(), this.catalogoTalla );
        this.seleccionTalla.setAdapter(this.adaptadorTalla);

        this.seleccionSubtalla = this.vista.findViewById(R.id.seleccionSubtalla);
        this.catalogoSubtalla = Catalogos.getInstancia().getCatalogoSubtalla();
        this.adaptadorSubtalla = new AdaptadorSubtalla( getContext(), this.catalogoSubtalla );
        this.seleccionSubtalla.setAdapter(this.adaptadorSubtalla);

        this.seleccionEspecie = this.vista.findViewById(R.id.seleccionEspecie);
        this.seleccionEspecie.setAdapter( new AdaptadorGrupoEspecie( getContext(), Catalogos.getInstancia().getCatalogoGrupoEspecie() ) );

        this.seleccionEspecialidad = this.vista.findViewById(R.id.seleccionEspecialidad);
        this.seleccionEspecialidad.setAdapter( new AdaptadorEspecialidad( getContext(), Catalogos.getInstancia().getCatalogoEspecialidad() ) );

        TextView etiquetaPosicion = this.vista.findViewById(R.id.etiquetaPosicion);
        etiquetaPosicion.setText( String.valueOf( getTinaSeleccionada().getPosicion() ) );

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
            botonPrecargar.setVisibility(View.GONE);
            etiquetaPosicion.setVisibility(View.GONE);
            TextView etiquetaTitulo = this.vista.findViewById(R.id.etiquetaTitulo);
            etiquetaTitulo.setText( "Tina ".concat( getTinaSeleccionada().getPosicion() ) );
            campoEscaner.setEnabled(false);
            campoEscaner.setText( getTinaSeleccionada().getTina().getIdTina() );

            this.seleccionTalla.setEnabled(false);
            this.seleccionSubtalla.setEnabled(false);
            this.seleccionEspecie.setEnabled(false);
            this.seleccionEspecialidad.setEnabled(false);

            valorInicialEspecie();
            valorInicialEspecialidad();
        }

        getTallas();
    }

    private void getTallas(){
        Call<List<Talla>> llamadaServicio = APIServicios.getConexionWeb()
                .getTallasFiltrado("PRESELECCION", true);
        llamadaServicio.enqueue(new Callback<List<Talla>>() {
            @Override
            public void onResponse(Call<List<Talla>> call, Response<List<Talla>> response) {
                if( isAdded() ){
                    terminaProcesando();
                    if( response.code() == 200 ){
                        catalogoTalla.removeAll( Catalogos.getInstancia().getCatalogoTalla() );
                        Catalogos.getInstancia().setCatalogoTalla( response.body() );
                        catalogoTalla.addAll( Catalogos.getInstancia().getCatalogoTalla() );
                        adaptadorTalla.notifyDataSetChanged();

                        if( !getNuevo() ){
                            valorInicialTalla();
                        }
                    }else{
                        errorServicio("Error al obtener las tallas");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Talla>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void getSubtallas(int idTalla){
        Call<List<Subtalla>> llamadaServicio = APIServicios.getConexionWeb()
                .getSubtallasFiltrado(idTalla, true);
        llamadaServicio.enqueue(new Callback<List<Subtalla>>() {
            @Override
            public void onResponse(Call<List<Subtalla>> call, Response<List<Subtalla>> response) {
                if( isAdded() ){
                    terminaProcesando();
                    if( response.code() == 200 ){
                        catalogoSubtalla.removeAll( Catalogos.getInstancia().getCatalogoSubtalla() );
                        Catalogos.getInstancia().setCatalogoSubtalla( response.body() );
                        catalogoSubtalla.addAll( Catalogos.getInstancia().getCatalogoSubtalla() );
                        adaptadorSubtalla.notifyDataSetChanged();

                        if( !getNuevo() || isPrecarga() ){
                            valorInicialSubtalla();
                            setPrecarga(false);
                        }
                    }else{
                        errorServicio("Error al obtener las subtallas");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Subtalla>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void validaAsignacion(){
        Spinner especie = this.vista.findViewById(R.id.seleccionEspecie);
        Spinner especialidad = this.vista.findViewById(R.id.seleccionEspecialidad);
        Spinner talla = this.vista.findViewById(R.id.seleccionTalla);
        Spinner subtalla = this.vista.findViewById(R.id.seleccionSubtalla);

        if(this.tinaValida){
            if( ( (GrupoEspecie) especie.getSelectedItem() ).getIdEspecie() > 0 ){
                if( ( (Talla) talla.getSelectedItem() ).getIdTalla() > 0 ){
                    if( ( (Subtalla) subtalla.getSelectedItem() ).getIdSubtalla() > 0 ){
                        iniciaProcesando();
                        getTinaSeleccionada().setLibre(false);
                        getTinaSeleccionada().setTurno(true);
                        if( UsuarioLogueado.getUsuarioLogueado().getTurno() == 1 ){
                            getTinaSeleccionada().setTurno(false);
                        }
                        getTinaSeleccionada().setSubtalla( (Subtalla) subtalla.getSelectedItem() );
                        getTinaSeleccionada().setTalla( (Talla) talla.getSelectedItem() );
                        getTinaSeleccionada().setGrupoEspecie( (GrupoEspecie) especie.getSelectedItem() );
                        getTinaSeleccionada().setEspecialidad( (Especialidad) especialidad.getSelectedItem() );

                        guarda();
                    }else{
                        errorValidacion("El campo Subtalla es obligatorio");
                    }
                }else{
                    errorValidacion("El campo Talla es obligatorio");
                }
            }else {
                errorValidacion("El campo Especie es obligatorio");
            }
        }else{
            errorValidacion("Es necesario capturar una tina");
        }
    }

    private void guarda(){
        JsonObject json = new JsonObject();
        json.addProperty("idPreseleccionPosicionTina", getTinaSeleccionada().getIdPreseleccionPosicionTina() );
        json.addProperty("idAsignacionCocida", 0);
        json.addProperty("idTina", getTinaSeleccionada().getTina().getDescripcion() );
        json.addProperty("idEspecie", getTinaSeleccionada().getGrupoEspecie().getIdEspecie() );
        json.addProperty("idTalla", getTinaSeleccionada().getTalla().getIdTalla() );
        json.addProperty("idSubtalla", getTinaSeleccionada().getSubtalla().getIdSubtalla() );
        if( getTinaSeleccionada().getEspecialidad().getIdEspecialidad() == 0 ){
            json.addProperty("idEspecialidad", 13);
        }else{
            json.addProperty("idEspecialidad", getTinaSeleccionada().getEspecialidad().getIdEspecialidad() );
        }
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

    private void cargaValoresIniciales(){
        boolean turno = false;
        if( UsuarioLogueado.getUsuarioLogueado().getTurno() == 2 ){
            turno = true;
        }

        if( getTinaSeleccionada().getTurno() == turno ){
            valorInicialSubtalla();
            valorInicialTalla();
            valorInicialEspecie();
            valorInicialEspecialidad();
            setPrecarga(true);
        }
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

    public void valorInicialTalla() {
        for (int i = 0; i < this.seleccionTalla.getCount(); i++) {
            if ( ( (Talla) this.seleccionTalla.getItemAtPosition(i) ).getDescripcion()
                    .equalsIgnoreCase( getTinaSeleccionada().getTalla().getDescripcion() ) ) {
                this.seleccionTalla.setSelection(i);
                break;
            }
        }
    }

    public void valorInicialSubtalla() {
        for (int i = 0; i < this.seleccionSubtalla.getCount(); i++) {
            if ( ( (Subtalla) this.seleccionSubtalla.getItemAtPosition(i) ).getDescripcion()
                    .equalsIgnoreCase( getTinaSeleccionada().getSubtalla().getDescripcion() ) ) {
                this.seleccionSubtalla.setSelection(i);
                break;
            }
        }
    }

    public void valorInicialEspecie() {
        for (int i = 0; i < this.seleccionEspecie.getCount(); i++) {
            if ( ( (GrupoEspecie) this.seleccionEspecie.getItemAtPosition(i) ).getDescripcion()
                    .equalsIgnoreCase( getTinaSeleccionada().getGrupoEspecie().getDescripcion() ) ) {
                this.seleccionEspecie.setSelection(i);
                break;
            }
        }
    }

    public void valorInicialEspecialidad() {
        for (int i = 0; i < this.seleccionEspecialidad.getCount(); i++) {
            if ( ( (Especialidad) this.seleccionEspecialidad.getItemAtPosition(i) ).getDescripcion()
                    .equalsIgnoreCase( getTinaSeleccionada().getEspecialidad().getDescripcion() ) ) {
                this.seleccionEspecialidad.setSelection(i);
                break;
            }
        }
    }
}

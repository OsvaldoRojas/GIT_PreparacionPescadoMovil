package com.grupo.pinsa.sip.simulador.preselecion;

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
import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorEspecialidad;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorGrupoEspecie;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorSubtalla;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorTalla;
import com.grupo.pinsa.sip.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor;
import com.grupo.pinsa.sip.simulador.utilerias.Catalogos;
import com.grupo.pinsa.sip.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.simulador.modelo.ErrorServicio;
import com.grupo.pinsa.sip.simulador.modelo.Especialidad;
import com.grupo.pinsa.sip.simulador.modelo.GrupoEspecie;
import com.grupo.pinsa.sip.simulador.modelo.Subtalla;
import com.grupo.pinsa.sip.simulador.modelo.Talla;
import com.grupo.pinsa.sip.simulador.modelo.Tina;
import com.grupo.pinsa.sip.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.simulador.modelo.servicio.RespuestaServicio;
import com.grupo.pinsa.sip.simulador.modelo.servicio.TinaEscaneo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Asigna_Tina extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private Serializable mParam1;

    private View vista;
    private AlertDialog ventanaError;

    private boolean tinaValida = false;

    private Tina tinaSeleccionada;
    private AdaptadorTalla adaptadorTalla;
    private AdaptadorSubtalla adaptadorSubtalla;

    private List<Talla> catalogoTalla;
    private List<Subtalla> catalogoSubtalla;

    private OnFragmentInteractionListener mListener;

    public Fragment_Asigna_Tina() {
    }

    public static Fragment_Asigna_Tina newInstance(Serializable param1) {
        Fragment_Asigna_Tina fragment = new Fragment_Asigna_Tina();
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
        setTinaSeleccionada( (Tina) mParam1 );

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

        Spinner seleccionTalla = vista.findViewById(R.id.seleccionTalla);
        seleccionTalla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        seleccionTalla.setAdapter(this.adaptadorTalla);

        Spinner seleccionSubtalla = this.vista.findViewById(R.id.seleccionSubtalla);
        this.catalogoSubtalla = Catalogos.getInstancia().getCatalogoSubtalla();
        this.adaptadorSubtalla = new AdaptadorSubtalla( getContext(), this.catalogoSubtalla );
        seleccionSubtalla.setAdapter(this.adaptadorSubtalla);

        Spinner seleccionEspecie = this.vista.findViewById(R.id.seleccionEspecie);
        seleccionEspecie.setAdapter( new AdaptadorGrupoEspecie( getContext(), Catalogos.getInstancia().getCatalogoGrupoEspecie() ) );

        Spinner seleccionEspecialidad = this.vista.findViewById(R.id.seleccionEspecialidad);
        seleccionEspecialidad.setAdapter( new AdaptadorEspecialidad( getContext(), Catalogos.getInstancia().getCatalogoEspecialidad() ) );

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

        iniciaProcesando();
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

            getTinaSeleccionada().getTina().setIdTina( Long.valueOf( resultadoTina.getIdTinaDes() ) );
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
            Spinner seleccionEspecie = this.vista.findViewById(R.id.seleccionEspecie);
            Spinner seleccionTalla = this.vista.findViewById(R.id.seleccionTalla);
            Spinner seleccionSubtalla = this.vista.findViewById(R.id.seleccionSubtalla);
            Spinner seleccionEspecialidad = this.vista.findViewById(R.id.seleccionEspecialidad);

            seleccionEspecie.setSelection(
                    obtenerPosicionItem(
                            seleccionEspecie,
                            getTinaSeleccionada().getGrupoEspecie().getIdEspecie(),
                            "Especie"
                    )
            );

            seleccionTalla.setSelection(
                    obtenerPosicionItem(
                            seleccionTalla,
                            getTinaSeleccionada().getTalla().getIdTalla(),
                            "Talla"
                    )
            );

            seleccionSubtalla.setSelection(
                    obtenerPosicionItem(
                            seleccionSubtalla,
                            getTinaSeleccionada().getSubtalla().getIdSubtalla(),
                            "Subtalla"
                    )
            );

            seleccionEspecialidad.setSelection(
                    obtenerPosicionItem(
                            seleccionEspecialidad,
                            getTinaSeleccionada().getEspecialidad().getIdEspecialidad(),
                            "Especialidad"
                    )
            );
        }
    }

    public static int obtenerPosicionItem(Spinner spinner, int id, String tipo) {
        int posicion = 0;
        switch (tipo){
            case "Especie":
                for (int i = 0; i < spinner.getCount(); i++) {
                    if ( ( (GrupoEspecie) spinner.getItemAtPosition(i) ).getIdEspecie() == id ) {
                        posicion = i;
                        break;
                    }
                }
                break;
            case "Talla":
                for (int i = 0; i < spinner.getCount(); i++) {
                    if ( ( (Talla) spinner.getItemAtPosition(i) ).getIdTalla() == id ) {
                        posicion = i;
                        break;
                    }
                }
                break;
            case "Subtalla":
                for (int i = 0; i < spinner.getCount(); i++) {
                    if ( ( (Subtalla) spinner.getItemAtPosition(i) ).getIdSubtalla() == id ) {
                        posicion = i;
                        break;
                    }
                }
                break;
            case "Especialidad":
                for (int i = 0; i < spinner.getCount(); i++) {
                    if ( ( (Especialidad) spinner.getItemAtPosition(i) ).getIdEspecialidad() == id ) {
                        posicion = i;
                        break;
                    }
                }
                break;
        }
        return posicion;
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

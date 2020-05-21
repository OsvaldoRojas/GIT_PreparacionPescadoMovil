package com.grupo.pinsa.sip.simulador.orden;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorArtefacto;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorArtefactoOrden;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorRefaccionLista;
import com.grupo.pinsa.sip.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.simulador.utilerias.Catalogos;
import com.grupo.pinsa.sip.simulador.utilerias.Constantes;
import com.grupo.pinsa.sip.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.simulador.modelo.ErrorServicio;
import com.grupo.pinsa.sip.simulador.modelo.OrdenMantenimiento;
import com.grupo.pinsa.sip.simulador.modelo.Refaccion;
import com.grupo.pinsa.sip.simulador.modelo.RefaccionOrden;
import com.grupo.pinsa.sip.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.simulador.modelo.servicio.RespuestaServicio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_DetalleOrden extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private long mParam1;

    private View vista;

    private AlertDialog ventanaError;
    private TextView etiquetaHoraInicio;
    private TextView etiquetaHoraFin;
    private EditText campoCantidad;
    private TextView campoCodigo;
    private Spinner campoRefaccion;
    private ListView listaRefaccionVista;
    private ScrollView vistaScroll;
    private LinearLayout contenedorEncabezados;

    private AdaptadorRefaccionLista adaptadorRefaccionLista;
    private RefaccionOrden refaccionCapturada;

    private boolean cambioFechaInicio = false;
    private boolean cambioFechaFin = false;

    private OrdenMantenimiento ordenSeleccionada;

    private OnFragmentInteractionListener mListener;

    public Fragment_DetalleOrden() {
        // Required empty public constructor
    }

    public static Fragment_DetalleOrden newInstance(long param1) {
        Fragment_DetalleOrden fragment = new Fragment_DetalleOrden();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getLong(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista=inflater.inflate(R.layout.fragment_detalle_orden, container, false);

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

    public OrdenMantenimiento getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setOrdenSeleccionada(OrdenMantenimiento ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    private void iniciaComponentes(){
        iniciaProcesando();
        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navega();
            }
        });

        Call<OrdenMantenimiento> llamadaServicio = APIServicios.getConexion().getDetalleOrden(this.mParam1);
        llamadaServicio.enqueue(new Callback<OrdenMantenimiento>() {
            @Override
            public void onResponse(Call<OrdenMantenimiento> call, Response<OrdenMantenimiento> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        resultadoDetalleOrden( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener el detalle de la orden");
                    }
                }
            }

            @Override
            public void onFailure(Call<OrdenMantenimiento> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    public void resultadoDetalleOrden(OrdenMantenimiento ordenMantenimiento){
        setOrdenSeleccionada(ordenMantenimiento);

        Button botonAceptar = this.vista.findViewById(R.id.boton2);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaGuardado();
            }
        });

        TextView etiquetaFolio = this.vista.findViewById(R.id.etiquetaFolio);
        etiquetaFolio.setText( String.valueOf( getOrdenSeleccionada().getFolio() ) );

        TextView etiquetaEquipo = this.vista.findViewById(R.id.etiquetaEquipo);
        etiquetaEquipo.setText( getOrdenSeleccionada().getMaquinaria().getDescripcion() );

        this.etiquetaHoraInicio = this.vista.findViewById(R.id.etiquetaHoraInicio);
        this.etiquetaHoraInicio.setText(
                getOrdenSeleccionada().getFechaInicio().substring(11, 13).concat(":")
                        .concat( getOrdenSeleccionada().getFechaInicio().substring(14, 16) )
        );
        this.etiquetaHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId() ){
                    obtenHoraInicio();
                }
            }
        });

        this.etiquetaHoraFin = this.vista.findViewById(R.id.etiquetaHoraFin);
        if( getOrdenSeleccionada().getFechaFin() != null ){
            this.etiquetaHoraFin.setText(
                    getOrdenSeleccionada().getFechaFin().substring(11, 13).concat(":")
                            .concat( getOrdenSeleccionada().getFechaFin().substring(14, 16) )
            );
        }else{
            this.etiquetaHoraFin.setText("00:00");
        }
        this.etiquetaHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId() ){
                    obtenHoraFin();
                }
            }
        });

        if( getOrdenSeleccionada().getArtefactos() != null &&
                getOrdenSeleccionada().getArtefactos().size() > 0 ){
            AdaptadorArtefactoOrden adaptadorArtefactos = new AdaptadorArtefactoOrden(
                    getContext(),
                    getOrdenSeleccionada().getArtefactos()
            );
            ListView listaArtefactos = this.vista.findViewById(R.id.listaArtefactos);
            listaArtefactos.setAdapter(adaptadorArtefactos);
            Utilerias.setAlturaLista(listaArtefactos, 0);
            listaArtefactos.setVisibility(View.VISIBLE);
        }

        TextView etiquetaDescripcion = this.vista.findViewById(R.id.etiquetaDescripcion);
        etiquetaDescripcion.setText( getOrdenSeleccionada().getDescripcion() );

        TextView campoSolucion = this.vista.findViewById(R.id.campoSolucion);
        campoSolucion.setText( getOrdenSeleccionada().getSolucion() );

        this.campoCantidad = this.vista.findViewById(R.id.campoCantidad);
        this.campoCodigo = this.vista.findViewById(R.id.campoCodigo);

        this.campoRefaccion = this.vista.findViewById(R.id.campoRefaccion);
        this.campoRefaccion.setAdapter( new AdaptadorArtefacto( getContext(), Catalogos.getInstancia().getCatalogoRefaccion() ) );
        this.campoRefaccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if( ( (Refaccion) adapterView.getItemAtPosition(i) ).getIdRefaccion() == 0 ){
                    campoCodigo.setText("");
                }else{
                    campoCodigo.setText( ( (Refaccion) adapterView.getItemAtPosition(i) ).getCodigo() );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.adaptadorRefaccionLista = new AdaptadorRefaccionLista(
                getContext(),
                getOrdenSeleccionada().getRefacciones()
        );
        this.listaRefaccionVista = this.vista.findViewById(R.id.listaRefacciones);
        this.listaRefaccionVista.setAdapter(this.adaptadorRefaccionLista);
        Utilerias.setAlturaLista(this.listaRefaccionVista, 0);

        this.contenedorEncabezados = this.vista.findViewById(R.id.contenedorEncabezados);
        if( getOrdenSeleccionada().getRefacciones().size() > 0 ){
            this.contenedorEncabezados.setVisibility(View.VISIBLE);
        }

        this.vistaScroll = this.vista.findViewById(R.id.vistaGeneral);

        FloatingActionButton botonAgregar = this.vista.findViewById(R.id.botonAgregar);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !campoCodigo.getText().toString().equalsIgnoreCase("") &&
                        !campoCantidad.getText().toString().equalsIgnoreCase("") &&
                        ( (Refaccion) campoRefaccion.getSelectedItem() ).getIdRefaccion() != 0 ){
                    iniciaProcesando();
                    refaccionCapturada = new RefaccionOrden(
                            ( (Refaccion) campoRefaccion.getSelectedItem() ).getIdRefaccion(),
                            Integer.valueOf( campoCantidad.getText().toString() ),
                            campoCodigo.getText().toString(),
                            ( (Refaccion) campoRefaccion.getSelectedItem() ).getDescripcion()
                    );

                    guardaRefaccion();
                }else{
                    errorValidacion("Es necesario capturar todos los campos");
                }
            }
        });

        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() != Constantes.ROL.mecanico.getId() ){
            campoSolucion.setEnabled(false);
            LinearLayout contenedorCapturaRefaccion = this.vista.findViewById(R.id.contenedorCapturaRefaccion);
            contenedorCapturaRefaccion.setVisibility(View.GONE);
            botonAgregar.hide();
        }

        terminaProcesando();
    }

    private void guardaRefaccion(){
        iniciaProcesando();
        JsonObject json = new JsonObject();
        json.addProperty("idOrdenMantenimiento", getOrdenSeleccionada().getIdOrdenMantenimiento() );
        json.addProperty("idRefaccion", this.refaccionCapturada.getIdRefaccion() );
        json.addProperty("cantidad", this.refaccionCapturada.getCantidad() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaRefaccion(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        resultadoGuardadoRefaccion();
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al guardar la refacción" );
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

    public void resultadoGuardadoRefaccion(){
        this.contenedorEncabezados.setVisibility(View.VISIBLE);
        getOrdenSeleccionada().getRefacciones().add(this.refaccionCapturada);
        this.adaptadorRefaccionLista.notifyDataSetChanged();
        Utilerias.setAlturaLista(this.listaRefaccionVista, 0);

        this.vistaScroll.post(new Runnable() {
            public void run() {
                vistaScroll.fullScroll(vistaScroll.FOCUS_DOWN);
            }
        });

        this.campoCantidad.setText("");
        this.campoRefaccion.setSelection(0);
        this.campoCodigo.setText("");
        terminaProcesando();
    }

    private void validaGuardado(){
        EditText campoSolucion = this.vista.findViewById(R.id.campoSolucion);

        if( ( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.mecanico.getId() &&
                !campoSolucion.getText().toString().equals("") ) ||
                UsuarioLogueado.getUsuarioLogueado().getId_rol() != Constantes.ROL.mecanico.getId() ){
            iniciaProcesando();
            getOrdenSeleccionada().setSolucion( campoSolucion.getText().toString() );

            if(this.cambioFechaInicio){
                String fecha = getOrdenSeleccionada().getFechaInicio().substring(0, 10);
                String fechaHora = fecha.concat(" ")
                        .concat( this.etiquetaHoraInicio.getText().toString() )
                        .concat(":00");
                getOrdenSeleccionada().setFechaInicio(fechaHora);
            }

            if(this.cambioFechaFin){
                String fecha;
                if( getOrdenSeleccionada().getFechaFin() != null ){
                    fecha = getOrdenSeleccionada().getFechaFin().substring(0, 10);
                }else{
                    fecha = Utilerias.fechaActual("yyyy-MM-dd");
                }
                String fechaHora = fecha.concat(" ")
                        .concat( this.etiquetaHoraFin.getText().toString() )
                        .concat(":00");

                actualizaTiempo(fechaHora);
            }
            guarda();
        }else{
            errorValidacion("Es necesario capturar una solución");
        }
    }

    private void actualizaTiempo(String fecha){
        JsonObject json = new JsonObject();
        json.addProperty("idOrdenMantenimiento", getOrdenSeleccionada().getIdOrdenMantenimiento() );
        json.addProperty("fechaFin", fecha);
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().cierraTiempoOrdenMantenimiento(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() != 200 && response.body().getCodigo() != 0 ){
                        terminaProcesando();
                        errorServicio( "Error al actualizar el tiempo" );
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

    private void guarda(){
        JsonObject json = new JsonObject();
        json.addProperty("idOrdenMantenimiento", getOrdenSeleccionada().getIdOrdenMantenimiento() );
        json.addProperty("idEmpleado", getOrdenSeleccionada().getIdEmpleado() );
        json.addProperty("nombreEmpleado", getOrdenSeleccionada().getNombreEmpleado() );
        json.addProperty("aPaternoEmpleado", getOrdenSeleccionada().getaPaternoEmpleado() );
        json.addProperty("aMaternoEmpleado", getOrdenSeleccionada().getaMaternoEmpleado() );
        json.addProperty("fechaInicio", getOrdenSeleccionada().getFechaInicio() );
        json.addProperty("solucion", getOrdenSeleccionada().getSolucion() );
        json.addProperty("finalizada", getOrdenSeleccionada().getFinalizada() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().actualizaOrdenMantenimiento(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        resultadoActualizaOrden();
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al guardar la orden de mantenimiento" );
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

    public void resultadoActualizaOrden(){
        terminaProcesando();
        navega();
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

    private void navega(){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, Utilerias.navega(2)).commit();
    }

    private void obtenHoraFin(){
        int hora = Integer.valueOf( this.etiquetaHoraFin.getText().toString().substring(0, 2) );
        int minuto = Integer.valueOf( this.etiquetaHoraFin.getText().toString().substring(3) );
        TimePickerDialog recogerHora = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String horaFormateada = (hourOfDay < 10) ? ("0" + hourOfDay) : String.valueOf(hourOfDay);
                String minutoFormateado = (minute < 10) ? ("0" + minute) : String.valueOf(minute);

                etiquetaHoraFin.setText(horaFormateada + ":" + minutoFormateado);
                etiquetaHoraFin.setTextColor( getResources().getColor(R.color.negro) );
                cambioFechaFin = true;
            }
        }, hora, minuto, true);
        recogerHora.show();
    }

    private void obtenHoraInicio(){
        int hora = Integer.valueOf( this.etiquetaHoraInicio.getText().toString().substring(0, 2) );
        int minuto = Integer.valueOf( this.etiquetaHoraInicio.getText().toString().substring(3) );
        TimePickerDialog recogerHora = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String horaFormateada = (hourOfDay < 10) ? ("0" + hourOfDay) : String.valueOf(hourOfDay);
                String minutoFormateado = (minute < 10) ? ("0" + minute) : String.valueOf(minute);

                etiquetaHoraInicio.setText(horaFormateada + ":" + minutoFormateado);
                etiquetaHoraInicio.setTextColor( getResources().getColor(R.color.negro) );
                cambioFechaInicio = true;
            }
        }, hora, minuto, true);
        recogerHora.show();
    }
}

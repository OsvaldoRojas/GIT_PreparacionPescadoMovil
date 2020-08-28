package com.grupo.pinsa.sip.views.simulador.descongelado;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorControlDescongelado;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.modelo.ControlDescongelado;
import com.grupo.pinsa.sip.views.simulador.modelo.ControlDescongeladoDetalle;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;
import com.grupo.pinsa.sip.views.simulador.utilerias.Utilerias;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Descongelado_Control extends Fragment {

    private View vista;

    private RecyclerView vistaLista;
    private SwipeRefreshLayout actualizar;
    private ProgressBar barraProgreso;
    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private TextView campoFecha;
    private FloatingActionButton agregar;

    private List<ControlDescongelado> listaControlDescongelado = new ArrayList<>();

    private Calendar fecha = Calendar.getInstance();
    private int mesCampo = this.fecha.get(Calendar.MONTH);
    private int diaCampo = this.fecha.get(Calendar.DAY_OF_MONTH);
    private int periodoCampo = this.fecha.get(Calendar.YEAR);

    public Fragment_Descongelado_Control() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_descongelado_control, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

        this.actualizar = this.vista.findViewById(R.id.actualizar);
        this.actualizar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actualizar.setRefreshing(true);
                getControlDescongelado();
            }
        });

        this.campoFecha = this.vista.findViewById(R.id.campoFecha);
        int mesActual = this.mesCampo + 1;
        String diaFormateado = (this.diaCampo < 10)? 0 + String.valueOf(this.diaCampo):String.valueOf(this.diaCampo);
        String mesFormateado = (mesActual < 10)? 0 + String.valueOf(mesActual):String.valueOf(mesActual);
        this.campoFecha.setText(
                diaFormateado.concat("/")
                        .concat(mesFormateado).concat("/")
                        .concat( String.valueOf(periodoCampo) )
        );
        this.campoFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenFecha();
            }
        });

        ImageView detalleGeneral = this.vista.findViewById(R.id.detalleGeneral);
        detalleGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( listaControlDescongelado.size() > 0 ){
                    iniciaProcesando();
                    getDetalleGeneral();
                }else{
                    errorServicio("La fecha seleccionada no cuenta con información");
                }
            }
        });

        this.agregar = this.vista.findViewById(R.id.agregar);
        this.agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlDescongelado controlDescongelado = new ControlDescongelado();
                controlDescongelado.setId(0);
                controlDescongelado.setFecha( Utilerias.fechaHoraActual() );
                controlDescongelado.setHora( Utilerias.fechaHoraActual().substring(11) );
                controlDescongelado.setClave("---");
                controlDescongelado.setBorrado(false);
                navega(controlDescongelado, false);
            }
        });
        if( validaFechaCaptura() ){
            this.agregar.show();
        }else{
            this.agregar.hide();
        }

        this.vistaLista = this.vista.findViewById(R.id.listaControl);

        getControlDescongelado();
    }

    private void getDetalleGeneral(){
        Call<ControlDescongeladoDetalle> llamadaServicio = APIServicios.getConexionAPPWEB()
                .getControlDetalle( getFecha() );
        llamadaServicio.enqueue(new Callback<ControlDescongeladoDetalle>() {
            @Override
            public void onResponse(Call<ControlDescongeladoDetalle> call, Response<ControlDescongeladoDetalle> response) {
                if( isAdded() ){
                    terminaProcesando();
                    if(response.code() == 200){
                        ControlDescongeladoDetalle controlDescongeladoDetalle = response.body();
                        controlDescongeladoDetalle.setFecha( campoFecha.getText().toString() );
                        controlDescongeladoDetalle.setClaveProceso( listaControlDescongelado.get(0).getClave() );
                        Fragment fragment = new Fragment_Detalle_General().newInstance(controlDescongeladoDetalle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
                    }else{
                        errorServicio("Error al obtener detalle de control de descongelado");
                    }
                }
            }

            @Override
            public void onFailure(Call<ControlDescongeladoDetalle> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void navega(ControlDescongelado controlDescongelado, boolean detalle){
        Fragment fragment = new Fragment_Control_Proceso().newInstance(controlDescongelado, detalle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private void getControlDescongelado(){
        Call<List<ControlDescongelado>> llamadaServicio = APIServicios.getConexionAPPWEB().getControlDescongelado( getFecha() );
        llamadaServicio.enqueue(new Callback<List<ControlDescongelado>>() {
            @Override
            public void onResponse(Call<List<ControlDescongelado>> call, Response<List<ControlDescongelado>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        resultadoControlDescongelado( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener registros de control de descongelado");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ControlDescongelado>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void resultadoControlDescongelado(List<ControlDescongelado> listaControl){
        TextView sinResultado = this.vista.findViewById(R.id.sinResultados);
        this.listaControlDescongelado = listaControl;
        if( !this.listaControlDescongelado.isEmpty() ){
            this.vistaLista.setVisibility(View.VISIBLE);
            sinResultado.setVisibility(View.GONE);

            this.vistaLista.setHasFixedSize(true);
            this.vistaLista.setClickable(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
            this.vistaLista.setLayoutManager(layoutManager);

            for(ControlDescongelado controlDescongelado : this.listaControlDescongelado){
                String fechaServicio = controlDescongelado.getFecha();
                controlDescongelado.setFecha( fechaServicio.replace("-", "/") );
            }

            AdaptadorControlDescongelado adaptadorControlDescongelado = new AdaptadorControlDescongelado(
                    this.listaControlDescongelado,
                    this
            );
            this.vistaLista.setAdapter(adaptadorControlDescongelado);
        }else{
            this.vistaLista.setVisibility(View.GONE);
            sinResultado.setVisibility(View.VISIBLE);
        }
        terminaProcesando();
    }

    public void edita(int posicion){
        ControlDescongelado controlDescongelado = this.listaControlDescongelado.get(posicion);
        controlDescongelado.setBorrado(false);
        navega(controlDescongelado, false);
    }

    public void elimina(final int posicion){
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
                etiquetaMensaje.setText("¿Está seguro de eliminar el registro?");

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton2);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iniciaProcesando();
                        ControlDescongelado controlDescongelado = listaControlDescongelado.get(posicion);
                        controlDescongelado.setBorrado(true);
                        controlDescongelado.setUsuario( UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
                        elimina(controlDescongelado);
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

    public void detalle(int posicion){
        ControlDescongelado controlDescongelado = this.listaControlDescongelado.get(posicion);
        controlDescongelado.setBorrado(false);
        navega(controlDescongelado, true);
    }

    private void elimina(ControlDescongelado controlDescongelado){
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexionAPPWEB().guardaControlDescongelado(controlDescongelado);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        getControlDescongelado();
                    }else{
                        terminaProcesando();
                        errorServicio("Error al eliminar control proceso de decongelado");
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

    private void obtenFecha(){
        DatePickerDialog capturaFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int periodo, int mes, int dia) {
                int mesActual = mes + 1;
                String diaFormateado = (dia < 10)? 0 + String.valueOf(dia):String.valueOf(dia);
                String mesFormateado = (mesActual < 10)? 0 + String.valueOf(mesActual):String.valueOf(mesActual);

                periodoCampo = periodo;
                mesCampo = mes;
                diaCampo = dia;
                campoFecha.setText(
                        diaFormateado.concat("/")
                                .concat(mesFormateado).concat("/")
                                .concat( String.valueOf(periodo) )
                );
                iniciaProcesando();
                if( validaFechaCaptura() ){
                    agregar.show();
                }else{
                    agregar.hide();
                }
                getControlDescongelado();
            }
        }, this.periodoCampo, this.mesCampo, this.diaCampo);
        Calendar diaActual = Calendar.getInstance();
        capturaFecha.getDatePicker().setMaxDate( diaActual.getTimeInMillis() );
        capturaFecha.show();
    }

    public boolean validaFechaCaptura(){
        String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format( new Date() );
        if( this.campoFecha.getText().toString().equals(fechaActual) ){
            //this.agregar.show();
            return true;
        }else{
            //this.agregar.hide();
            return false;
        }
    }

    private String getFecha(){
        String fecha = this.campoFecha.getText().toString();
        String dia = fecha.substring(0,2);
        String mes = fecha.substring(3,5);
        String periodo = fecha.substring(6);
        return periodo.concat("-").concat(mes).concat("-").concat(dia);
    }

    public void iniciaProcesando(){
        this.barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        if( this.actualizar.isRefreshing() ){
            this.actualizar.setRefreshing(false);
        }
        this.barraProgreso.setVisibility(View.GONE);
    }
}

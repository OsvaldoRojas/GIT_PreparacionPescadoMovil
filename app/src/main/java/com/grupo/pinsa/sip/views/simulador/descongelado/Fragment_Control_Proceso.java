package com.grupo.pinsa.sip.views.simulador.descongelado;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.contenedores.Contenedor_Descongelado;
import com.grupo.pinsa.sip.views.simulador.modelo.ControlDescongelado;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;

import java.io.Serializable;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Control_Proceso extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View vista;
    private AlertDialog ventanaError;
    private TextView etiquetaFecha;
    private TextView etiquetaHora;
    private TextView clave;
    private TextView hora;
    private EditText temperatura;
    private EditText consumoAgua;
    private EditText loteHipoclorito;
    private EditText concentradoCloro;
    private EditText consumoCloro;
    private EditText loteAntiespumante;
    private EditText antiespumante;

    private ControlDescongelado controlDescongelado;

    private Calendar fecha = Calendar.getInstance();
    private int horaCampo = this.fecha.get(Calendar.HOUR_OF_DAY);
    private int minutoCampo = this.fecha.get(Calendar.MINUTE);
    private boolean detalle;

    public Fragment_Control_Proceso() {
    }

    public ControlDescongelado getControlDescongelado() {
        return controlDescongelado;
    }

    public void setControlDescongelado(ControlDescongelado controlDescongelado) {
        this.controlDescongelado = controlDescongelado;
    }

    public boolean isDetalle() {
        return detalle;
    }

    public void setDetalle(boolean detalle) {
        this.detalle = detalle;
    }

    public static Fragment_Control_Proceso newInstance(Serializable param1, Boolean param2) {
        Fragment_Control_Proceso fragment = new Fragment_Control_Proceso();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setControlDescongelado( (ControlDescongelado) getArguments().getSerializable(ARG_PARAM1) );
            setDetalle( getArguments().getBoolean(ARG_PARAM2) );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_control_proceso_descongelado, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor_Descongelado().newInstance(4);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        Button botonAceptar = this.vista.findViewById(R.id.boton2);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaGuardado();
            }
        });

        this.etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        this.etiquetaFecha.setText( getControlDescongelado().getFecha().substring(0, 10) );
        this.etiquetaHora = this.vista.findViewById(R.id.etiquetaHora);
        this.etiquetaHora.setText( getControlDescongelado().getFecha().substring(11) );
        this.clave = this.vista.findViewById(R.id.etiquetaClave);
        this.clave.setText( getControlDescongelado().getClave() );

        this.hora = this.vista.findViewById(R.id.campoHora);
        this.horaCampo = Integer.valueOf( getControlDescongelado().getHora().substring(0, 2) );
        this.minutoCampo = Integer.valueOf( getControlDescongelado().getHora().substring(3, 5) );
        //String horaFormateada = (this.horaCampo < 10)? 0 + String.valueOf(this.horaCampo) : String.valueOf(this.horaCampo);
        //String minutoFormateado = (this.minutoCampo < 10)? 0 + String.valueOf(this.minutoCampo) : String.valueOf(this.minutoCampo);
        this.hora.setText( getControlDescongelado().getHora().substring(0, 5) );
        this.hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenHora();
            }
        });

        this.temperatura = this.vista.findViewById(R.id.campoTemperatura);
        this.consumoAgua = this.vista.findViewById(R.id.campoConsumoAgua);
        this.loteHipoclorito = this.vista.findViewById(R.id.campoHipoclorito);
        this.concentradoCloro = this.vista.findViewById(R.id.campoConcentrado);
        this.consumoCloro = this.vista.findViewById(R.id.campoConsumoCloro);
        this.loteAntiespumante = this.vista.findViewById(R.id.campoLoteAntiespumante);
        this.antiespumante = this.vista.findViewById(R.id.campoAntiespumante);

        if( getControlDescongelado().getId() > 0 ){
            this.temperatura.setText( String.valueOf( getControlDescongelado().getTemperatura() ) );
            this.consumoAgua.setText( String.valueOf( getControlDescongelado().getConsumoAgua() ) );
            this.loteHipoclorito.setText( String.valueOf( getControlDescongelado().getLoteHipoclorito() ) );
            this.concentradoCloro.setText( String.valueOf( getControlDescongelado().getConcentrado() ) );
            this.consumoCloro.setText( String.valueOf( getControlDescongelado().getConsumoCloro() ) );
            this.loteAntiespumante.setText( String.valueOf( getControlDescongelado().getLoteAntiespumante() ) );
            this.antiespumante.setText( String.valueOf( getControlDescongelado().getAntiespumante() ) );
        }

        if( isDetalle() ){
            this.hora.setEnabled(false);
            this.temperatura.setEnabled(false);
            this.consumoAgua.setEnabled(false);
            this.loteHipoclorito.setEnabled(false);
            this.concentradoCloro.setEnabled(false);
            this.consumoCloro.setEnabled(false);
            this.loteAntiespumante.setEnabled(false);
            this.antiespumante.setEnabled(false);
            botonAceptar.setVisibility(View.GONE);
            botonCancelar.setText(R.string.volver);
        }
    }

    private void validaGuardado(){
        if( temperatura.getText().toString().equals("")
                || consumoAgua.getText().toString().equals("")
                || loteHipoclorito.getText().toString().equals("")
                || concentradoCloro.getText().toString().equals("")
                || consumoCloro.getText().toString().equals("")
                || loteAntiespumante.getText().toString().equals("")
                || antiespumante.getText().toString().equals("") ){
            errorValidacion("Es necesario capturar todos los campos");
        }else{
            iniciaProcesando();
            guarda();
        }
    }

    private void guarda(){
        getControlDescongelado().setFecha(
                this.etiquetaFecha.getText().toString().concat(" ")
                        .concat( this.etiquetaHora.getText().toString() )
        );
        getControlDescongelado().setHora( this.hora.getText().toString() );
        getControlDescongelado().setTemperatura( Float.valueOf( this.temperatura.getText().toString() ) );
        getControlDescongelado().setConsumoAgua( Float.valueOf( this.consumoAgua.getText().toString() ) );
        getControlDescongelado().setLoteHipoclorito( Integer.valueOf( this.loteHipoclorito.getText().toString() ) );
        getControlDescongelado().setConcentrado( Float.valueOf( this.concentradoCloro.getText().toString() ) );
        getControlDescongelado().setConsumoCloro( Float.valueOf( this.consumoCloro.getText().toString() ) );
        getControlDescongelado().setLoteAntiespumante( Integer.valueOf( this.loteAntiespumante.getText().toString() ) );
        getControlDescongelado().setAntiespumante( Float.valueOf( this.antiespumante.getText().toString() ) );
        getControlDescongelado().setUsuario( UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexionAPPWEB().guardaControlDescongelado( getControlDescongelado() );
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        terminaProcesando();
                        Fragment fragment = new Contenedor_Descongelado().newInstance(4);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
                    }else{
                        terminaProcesando();
                        errorServicio("Error al guardar control proceso de decongelado");
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

    private void obtenHora(){
        TimePickerDialog capturaHora = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int horaModificada, int minuto) {
                String horaFormateada =  (horaModificada < 10)? 0 + String.valueOf(horaModificada) : String.valueOf(horaModificada);
                String minutoFormateado = (minuto < 10)? 0 + String.valueOf(minuto) : String.valueOf(minuto);

                horaCampo = horaModificada;
                minutoCampo = minuto;

                hora.setText( horaFormateada.concat(":").concat(minutoFormateado) );
            }
        }, this.horaCampo, this.minutoCampo, true);
        capturaHora.show();
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

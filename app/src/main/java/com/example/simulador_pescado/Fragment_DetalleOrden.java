package com.example.simulador_pescado;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
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

import com.example.simulador_pescado.Contenedores.Contenedor;
import com.example.simulador_pescado.Contenedores.Contenedor_Atemperado;
import com.example.simulador_pescado.Contenedores.Contenedor_Descongelado;
import com.example.simulador_pescado.Utilerias.Catalogos;
import com.example.simulador_pescado.Utilerias.Utilerias;
import com.example.simulador_pescado.adaptadores.AdaptadorArtefacto;
import com.example.simulador_pescado.adaptadores.AdaptadorArtefactoOrden;
import com.example.simulador_pescado.adaptadores.AdaptadorRefaccionLista;
import com.example.simulador_pescado.conexion.ActualizaOrdenMantenimiento;
import com.example.simulador_pescado.conexion.CerrarTiempoOrden;
import com.example.simulador_pescado.conexion.GuardaRefaccion;
import com.example.simulador_pescado.conexion.ObtenDetalleOrden;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.ListaOrdenMantenimientoServicio;
import com.example.simulador_pescado.vista.Refaccion;
import com.example.simulador_pescado.vista.RefaccionOrden;
import com.example.simulador_pescado.vista.OrdenMantenimiento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fragment_DetalleOrden extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment Fragment_Preselecion_Tinas.
     */
    // TODO: Rename and change types and number of parameters
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

        ObtenDetalleOrden detalleOrden = new ObtenDetalleOrden( this, this.mParam1);
        detalleOrden.execute();
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
                obtenHoraInicio();
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
                obtenHoraFin();
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

                    GuardaRefaccion guardaRefaccion = new GuardaRefaccion(
                            Fragment_DetalleOrden.this,
                            getOrdenSeleccionada().getIdOrdenMantenimiento(),
                            refaccionCapturada
                    );
                    guardaRefaccion.execute();
                }else{
                    errorValidacion("Es necesario capturar todos los campos");
                }
            }
        });

        terminaProcesando();
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

        if( !campoSolucion.getText().toString().equals("") ){
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

                CerrarTiempoOrden cerrarTiempoOrden = new CerrarTiempoOrden(
                        this,
                        fechaHora,
                        getOrdenSeleccionada().getIdOrdenMantenimiento()
                );
                cerrarTiempoOrden.execute();
            }

            ActualizaOrdenMantenimiento actualiza = new ActualizaOrdenMantenimiento(this, getOrdenSeleccionada() );
            actualiza.execute();
        }else{
            errorValidacion("Es necesario capturar una solución");
        }
    }

    public void resultadoActualizaOrden(){
        terminaProcesando();
        navega();
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
        Fragment fragment = null;
        switch ( Catalogos.getInstancia().getEtapaActual() ){
            case 1:
                fragment = new Contenedor().newInstance(2);
                break;
            case 2:
                fragment = new Contenedor_Atemperado().newInstance(2);
                break;
            case 3:
                fragment = new Contenedor_Descongelado().newInstance(2);
                break;
        }
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
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

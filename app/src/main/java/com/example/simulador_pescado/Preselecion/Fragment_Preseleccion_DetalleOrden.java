package com.example.simulador_pescado.Preselecion;

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

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Contenedores.Contenedor;
import com.example.simulador_pescado.R;
import com.example.simulador_pescado.Utilerias.Catalogos;
import com.example.simulador_pescado.Utilerias.Utilerias;
import com.example.simulador_pescado.adaptadores.AdaptadorArtefacto;
import com.example.simulador_pescado.adaptadores.AdaptadorRefaccionLista;
import com.example.simulador_pescado.conexion.ObtenDetalleOrden;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.ListaOrdenMantenimientoServicio;
import com.example.simulador_pescado.vista.Refaccion;
import com.example.simulador_pescado.vista.RefaccionLista;
import com.example.simulador_pescado.vista.OrdenMantenimiento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Preseleccion_DetalleOrden extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Serializable mParam1;

    private View vista;

    private AlertDialog ventanaError;

    private OrdenMantenimiento ordenSeleccionada;

    private OnFragmentInteractionListener mListener;

    public Fragment_Preseleccion_DetalleOrden() {
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
    public static Fragment_Preseleccion_DetalleOrden newInstance(Serializable param1) {
        Fragment_Preseleccion_DetalleOrden fragment = new Fragment_Preseleccion_DetalleOrden();
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
        ListaOrdenMantenimientoServicio orden = (ListaOrdenMantenimientoServicio) this.mParam1;

        ObtenDetalleOrden detalleOrden = new ObtenDetalleOrden( this, orden.getIdOrdenMantenimiento() );
        detalleOrden.execute();
    }

    public void resultadoDetalleOrden(OrdenMantenimiento ordenMantenimiento){
        setOrdenSeleccionada(ordenMantenimiento);

        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor().newInstance(2);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        Button botonAceptar = this.vista.findViewById(R.id.boton2);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        TextView etiquetaFolio = this.vista.findViewById(R.id.etiquetaFolio);
        etiquetaFolio.setText( String.valueOf( getOrdenSeleccionada().getFolio() ) );

        TextView etiquetaEquipo = this.vista.findViewById(R.id.etiquetaEquipo);
        etiquetaEquipo.setText( getOrdenSeleccionada().getEquipo() );

        TextView etiquetaDescripcion = this.vista.findViewById(R.id.etiquetaDescripcion);
        etiquetaDescripcion.setText( getOrdenSeleccionada().getDescripcion() );

        final EditText campoCantidad = this.vista.findViewById(R.id.campoCantidad);

        final TextView campoCodigo = this.vista.findViewById(R.id.campoCodigo);

        final Spinner campoRefaccion = this.vista.findViewById(R.id.campoRefaccion);
        campoRefaccion.setAdapter( new AdaptadorArtefacto( getContext(), Catalogos.getInstancia().getCatalogoRefaccion() ) );
        campoRefaccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        final AdaptadorRefaccionLista adaptadorRefaccionLista = new AdaptadorRefaccionLista(
                getContext(),
                getOrdenSeleccionada().getListaRefacciones()
        );
        final ListView listaRefaccionVista = this.vista.findViewById(R.id.listaRefacciones);
        listaRefaccionVista.setAdapter(adaptadorRefaccionLista);
        Utilerias.setAlturaLista(listaRefaccionVista, 0);

        final LinearLayout contenedorEncabezados = this.vista.findViewById(R.id.contenedorEncabezados);
        if( !getOrdenSeleccionada().getListaArtefactos().isEmpty() ){
            contenedorEncabezados.setVisibility(View.VISIBLE);
        }

        final ScrollView vistaScroll = this.vista.findViewById(R.id.vistaGeneral);

        FloatingActionButton botonAgregar = this.vista.findViewById(R.id.botonAgregar);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !campoCodigo.getText().toString().equalsIgnoreCase("") &&
                        !campoCantidad.getText().toString().equalsIgnoreCase("") &&
                        ( (Refaccion) campoRefaccion.getSelectedItem() ).getIdRefaccion() != 0 ){
                    contenedorEncabezados.setVisibility(View.VISIBLE);
                    getOrdenSeleccionada().getListaRefacciones().add(
                            new RefaccionLista(
                                    (Refaccion) campoRefaccion.getSelectedItem(),
                                    Integer.valueOf( campoCantidad.getText().toString() ),
                                    campoCodigo.getText().toString()
                            )
                    );
                    adaptadorRefaccionLista.notifyDataSetChanged();
                    Utilerias.setAlturaLista(listaRefaccionVista, 0);

                    vistaScroll.post(new Runnable() {
                        public void run() {
                            vistaScroll.fullScroll(vistaScroll.FOCUS_DOWN);
                        }
                    });

                    campoCantidad.setText("");
                    campoRefaccion.setSelection(0);
                    campoCodigo.setText("");
                }else{
                    errorValidacion("Es necesario capturar todos los campos");
                }
            }
        });

        terminaProcesando();
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
}

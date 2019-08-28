package com.example.simulador_pescado.Atemperado;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Contenedores.Contenedor_Atemperado;
import com.example.simulador_pescado.R;
import com.example.simulador_pescado.Utilerias.Utilerias;
import com.example.simulador_pescado.adaptadores.AdaptadorArtefacto;
import com.example.simulador_pescado.adaptadores.AdaptadorArtefactoLista;
import com.example.simulador_pescado.vista.Refaccion;
import com.example.simulador_pescado.vista.RefaccionLista;
import com.example.simulador_pescado.vista.OrdenMantenimiento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Atemperado_DetalleOrden extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Serializable mParam1;

    private View vista;

    private AlertDialog ventanaError;

    private OrdenMantenimiento ordenSeleccionada;

    private OnFragmentInteractionListener mListener;

    public Fragment_Atemperado_DetalleOrden() {
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
    public static Fragment_Atemperado_DetalleOrden newInstance(Serializable param1) {
        Fragment_Atemperado_DetalleOrden fragment = new Fragment_Atemperado_DetalleOrden();
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
        List<Refaccion> listaRefacciones = new ArrayList<>();
        listaRefacciones.add( new Refaccion(0, "Refaccion") );
        listaRefacciones.add( new Refaccion(1, "Refaccion 1") );
        listaRefacciones.add( new Refaccion(2, "Refaccion 2") );
        listaRefacciones.add( new Refaccion(3, "Refaccion 3") );

        String[] listaCodigos = {"Código","001","002","003"};

        setOrdenSeleccionada( (OrdenMantenimiento) this.mParam1 );

        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor_Atemperado().newInstance(2);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        Button botonAceptar = this.vista.findViewById(R.id.boton2);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //limpiarCampos();
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

        final Spinner campoCodigo = this.vista.findViewById(R.id.campoCodigo);
        campoCodigo.setAdapter( new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listaCodigos) );

        final Spinner campoArtefacto = this.vista.findViewById(R.id.campoArtefacto);
        campoArtefacto.setAdapter( new AdaptadorArtefacto( getContext(), listaRefacciones) );

        final AdaptadorArtefactoLista adaptadorArtefactoLista = new AdaptadorArtefactoLista(
                getContext(),
                getOrdenSeleccionada().getListaRefacciones()
        );
        final ListView listaArtefactosVista = this.vista.findViewById(R.id.listaRefacciones);
        listaArtefactosVista.setAdapter(adaptadorArtefactoLista);
        Utilerias.setAlturaLista(listaArtefactosVista, 0);

        final LinearLayout contenedorEncabezados = this.vista.findViewById(R.id.contenedorEncabezados);
        if( !getOrdenSeleccionada().getListaArtefactos().isEmpty() ){
            contenedorEncabezados.setVisibility(View.VISIBLE);
        }

        final ScrollView vistaScroll = this.vista.findViewById(R.id.vistaGeneral);

        FloatingActionButton botonAgregar = this.vista.findViewById(R.id.botonAgregar);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contenedorEncabezados.setVisibility(View.VISIBLE);
                getOrdenSeleccionada().getListaRefacciones().add(
                        new RefaccionLista(
                                (Refaccion) campoArtefacto.getSelectedItem(),
                                Integer.valueOf( campoCantidad.getText().toString() ),
                                campoCodigo.getSelectedItem().toString()
                        )
                );
                adaptadorArtefactoLista.notifyDataSetChanged();
                Utilerias.setAlturaLista(listaArtefactosVista, 0);

                vistaScroll.post(new Runnable() {
                    public void run() {
                        vistaScroll.fullScroll(vistaScroll.FOCUS_DOWN);
                    }
                });

                campoCantidad.setText("");
                campoArtefacto.setSelection(0);
                campoCodigo.setSelection(0);
            }
        });
    }

    private void limpiarCampos(){
        OrdenMantenimiento orden = getOrdenSeleccionada();
        List<RefaccionLista> lista = new ArrayList<>();
        for( RefaccionLista refaccionLista : orden.getListaRefacciones() ){
            if( refaccionLista.getRefaccion().getDescripcion().equalsIgnoreCase("Refaccion") ){
                lista.add(refaccionLista);
            }
        }

        for(RefaccionLista refaccionLista : lista){
            orden.getListaArtefactos().remove(refaccionLista);
        }
    }
}


package com.example.simulador_pescado.Atemperado;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Contenedores.Contenedor_Atemperado;
import com.example.simulador_pescado.R;
import com.example.simulador_pescado.Utilerias.Utilerias;
import com.example.simulador_pescado.vista.PosicionEstiba;

import java.io.Serializable;

public class Fragment_Atemperado_DetalleEstiba extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Serializable mParam1;

    private View vista;

    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private PosicionEstiba posicionSeleccionada;

    private OnFragmentInteractionListener mListener;

    public Fragment_Atemperado_DetalleEstiba() {
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
    public static Fragment_Atemperado_DetalleEstiba newInstance(Serializable param1) {
        Fragment_Atemperado_DetalleEstiba fragment = new Fragment_Atemperado_DetalleEstiba();
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
        this.vista=inflater.inflate(R.layout.fragment_atemperado_detalle_estiba, container, false);

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

    public PosicionEstiba getPosicionSeleccionada() {
        return posicionSeleccionada;
    }

    public void setPosicionSeleccionada(PosicionEstiba posicionSeleccionada) {
        this.posicionSeleccionada = posicionSeleccionada;
    }

    private void iniciaComponentes(){
        setPosicionSeleccionada( (PosicionEstiba) this.mParam1 );

        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor_Atemperado().newInstance(0);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        final TextView etiquetaPosicion1 = this.vista.findViewById(R.id.etiquetaPosicion1);
        if( getPosicionSeleccionada().getConteoNivel() >= 1 ){
            etiquetaPosicion1.setText("Posición 1 ocupada");
        }
        etiquetaPosicion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaCambio( (TextView) vista.findViewById(R.id.etiquetaPosicion1) );
            }
        });

        final TextView etiquetaPosicion2 = this.vista.findViewById(R.id.etiquetaPosicion2);
        if( getPosicionSeleccionada().getConteoNivel() >= 2 ){
            etiquetaPosicion2.setText("Posición 2 ocupada");
        }
        etiquetaPosicion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaCambio( (TextView) vista.findViewById(R.id.etiquetaPosicion2) );
            }
        });

        final TextView etiquetaPosicion3 = this.vista.findViewById(R.id.etiquetaPosicion3);
        if( getPosicionSeleccionada().getConteoNivel() >= 3 ){
            etiquetaPosicion3.setText("Posición 3 ocupada");
        }
        etiquetaPosicion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaCambio( (TextView) vista.findViewById(R.id.etiquetaPosicion3) );
            }
        });

        final TextView etiquetaPosicion4 = this.vista.findViewById(R.id.etiquetaPosicion4);
        if( getPosicionSeleccionada().getConteoNivel() >= 4 ){
            etiquetaPosicion4.setText("Posición 4 ocupada");
        }
        etiquetaPosicion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaCambio( (TextView) vista.findViewById(R.id.etiquetaPosicion4) );
            }
        });

        final TextView etiquetaPosicion5 = this.vista.findViewById(R.id.etiquetaPosicion5);
        if( getPosicionSeleccionada().getConteoNivel() >= 5 ){
            etiquetaPosicion5.setText("Posición 5 ocupada");
        }
        etiquetaPosicion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaCambio( (TextView) vista.findViewById(R.id.etiquetaPosicion5) );
            }
        });
    }

    private void confirmaCambio(final TextView estiba){
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
                etiquetaMensaje.setText("¿Cambiar estatus a desocupada?");

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton2);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        estiba.setText( estiba.getText().toString().replace("ocupada", "desocupada") );
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
}

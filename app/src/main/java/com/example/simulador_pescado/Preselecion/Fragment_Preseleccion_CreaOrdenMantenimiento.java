package com.example.simulador_pescado.Preselecion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Contenedores.Contenedor;
import com.example.simulador_pescado.R;
import com.example.simulador_pescado.Utilerias.Utilerias;
import com.example.simulador_pescado.vista.Bascula;
import com.example.simulador_pescado.vista.OperadorBascula;
import com.example.simulador_pescado.vista.OperadorMontacargas;
import com.example.simulador_pescado.vista.Tina;

import java.io.Serializable;

public class CreaOrdenMantenimientoPreseleccion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Serializable mParam1;

    private View vista;

    private Tina tinaSeleccionada;
    private OperadorBascula operadorSeleccionado;
    private OperadorMontacargas montacargasSeleccionado;
    private Bascula basculaSeleccionada;

    private OnFragmentInteractionListener mListener;

    public CreaOrdenMantenimientoPreseleccion() {
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
    public static CreaOrdenMantenimientoPreseleccion newInstance(Serializable param1) {
        CreaOrdenMantenimientoPreseleccion fragment = new CreaOrdenMantenimientoPreseleccion();
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
        this.vista=inflater.inflate(R.layout.fragment_orden_mantenimiento, container, false);

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

    public Tina getTinaSeleccionada() {
        return tinaSeleccionada;
    }

    public void setTinaSeleccionada(Tina tinaSeleccionada) {
        this.tinaSeleccionada = tinaSeleccionada;
    }

    public OperadorBascula getOperadorSeleccionado() {
        return operadorSeleccionado;
    }

    public void setOperadorSeleccionado(OperadorBascula operadorSeleccionado) {
        this.operadorSeleccionado = operadorSeleccionado;
    }

    public OperadorMontacargas getMontacargasSeleccionado() {
        return montacargasSeleccionado;
    }

    public void setMontacargasSeleccionado(OperadorMontacargas montacargasSeleccionado) {
        this.montacargasSeleccionado = montacargasSeleccionado;
    }

    public Bascula getBasculaSeleccionada() {
        return basculaSeleccionada;
    }

    public void setBasculaSeleccionada(Bascula basculaSeleccionada) {
        this.basculaSeleccionada = basculaSeleccionada;
    }

    private void iniciaComponentes(){
        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor().newInstance(1);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        TextView etiquetaEquipo = this.vista.findViewById(R.id.etiquetaEquipo);

        if( this.mParam1 instanceof Tina ){
            setTinaSeleccionada( (Tina) this.mParam1 );
            etiquetaEquipo.setText( "Tina ".concat( getTinaSeleccionada().getPosicion() ) );
        }else{
            if( this.mParam1 instanceof OperadorBascula ){
                setOperadorSeleccionado( (OperadorBascula) this.mParam1 );
                etiquetaEquipo.setText( "Operador ".concat( getOperadorSeleccionado().getEstacion() ) );
            }else{
                if( this.mParam1 instanceof OperadorMontacargas ){
                    setMontacargasSeleccionado( (OperadorMontacargas) this.mParam1 );
                    etiquetaEquipo.setText( "Montacargas ".concat( getEtiquetaMontacargas( getMontacargasSeleccionado().getIdPreseleccionMontacarga() ) ) );
                }else{
                    if( this.mParam1 instanceof Bascula ){
                        setBasculaSeleccionada( (Bascula) this.mParam1 );
                        etiquetaEquipo.setText( "Báscula ".concat( getEtiquetaBascula( getBasculaSeleccionada().getIdBascula() ) ) );
                    }
                }
            }
        }
    }

    private String getEtiquetaMontacargas(int posicion){
        switch (posicion){
            case 1: return "A1";
            case 2: return "A2";
            case 3: return "B1";
            case 4: return "B2";
        }
        return "";
    }

    private String getEtiquetaBascula(int posicion){
        switch (posicion){
            case 1: return "A9";
            case 2: return "A7";
            case 3: return "A5";
            case 4: return "A3";
            case 5: return "A1";
            case 6: return "B2";
            case 7: return "B4";
            case 8: return "B6";
            case 9: return "B8";
            case 10: return "B10";
        }
        return "";
    }
}

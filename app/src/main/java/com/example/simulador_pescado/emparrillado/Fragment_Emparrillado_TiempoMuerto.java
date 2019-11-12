package com.example.simulador_pescado.emparrillado;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Fragment_CreaOrdenMantenimiento;
import com.example.simulador_pescado.R;
import com.example.simulador_pescado.utilerias.Catalogos;
import com.example.simulador_pescado.utilerias.Constantes;
import com.example.simulador_pescado.vista.Maquinaria;
import com.example.simulador_pescado.vista.OperadorBascula;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Emparrillado_TiempoMuerto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View vista;

    private LinearLayout contenedorBotones;

    private ImageView operador1;
    private ImageView operador2;
    private ImageView operador3;
    private ImageView operador4;
    private ImageView operador5;
    private ImageView operador6;
    private ImageView operador7;
    private ImageView operador8;

    private OperadorBascula operadorSeleccionado;
    private List<OperadorBascula> listaOperadores = new ArrayList<>();

    private AlertDialog ventanaError;
    private ProgressBar barraProgreso;
    private Button botonCrear;

    private OnFragmentInteractionListener mListener;

    public Fragment_Emparrillado_TiempoMuerto() {

    }

    public OperadorBascula getOperadorSeleccionado() {
        return operadorSeleccionado;
    }

    public void setOperadorSeleccionado(OperadorBascula operadorSeleccionado) {
        this.operadorSeleccionado = operadorSeleccionado;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Descongelado_TiempoMuerto.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Emparrillado_TiempoMuerto newInstance(String param1, String param2) {
        Fragment_Emparrillado_TiempoMuerto fragment = new Fragment_Emparrillado_TiempoMuerto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_emparrillado_tiempo_muerto, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

        this.contenedorBotones = this.vista.findViewById(R.id.botonera);
        this.botonCrear = this.vista.findViewById(R.id.botonCrearOrden);

        this.operador1 = this.vista.findViewById(R.id.operador1);
        this.operador1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(1);
            }
        });

        this.operador2 = this.vista.findViewById(R.id.operador2);
        this.operador2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(2);
            }
        });

        this.operador3 = this.vista.findViewById(R.id.operador3);
        this.operador3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(3);
            }
        });

        this.operador4 = this.vista.findViewById(R.id.operador4);
        this.operador4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(4);
            }
        });

        this.operador5 = this.vista.findViewById(R.id.operador5);
        this.operador5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(5);
            }
        });

        this.operador6 = this.vista.findViewById(R.id.operador6);
        this.operador6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(6);
            }
        });

        this.operador7 = this.vista.findViewById(R.id.operador7);
        this.operador7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(7);
            }
        });

        this.operador8 = this.vista.findViewById(R.id.operador8);
        this.operador8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(8);
            }
        });

        creaObjetosVacios();
    }

    private void creaObjetosVacios(){
        if( this.listaOperadores.isEmpty() ){
            for( int posicion = 1; posicion <= 8; posicion++ ){
                OperadorBascula recursoOperador = new OperadorBascula();
                recursoOperador.setIdPreseleccionEstacion(posicion);
                recursoOperador.setEstacion( getEtiquetaOperador(posicion) );
                recursoOperador.setClaveMaquina("MAQ-OPE-" + posicion);
                recursoOperador.setEstado(Constantes.ESTADO.inicial);
                this.listaOperadores.add(recursoOperador);
            }
        }
        terminaProcesando();
    }

    private void accionIconoOperador(int posicion){
        for( OperadorBascula operador : this.listaOperadores ){
            if( operador.getIdPreseleccionEstacion() == posicion ){
                if( operador.getEstado() == Constantes.ESTADO.inicial ){
                    setOperadorSeleccionado(operador);
                    deshabilitaRecursos();
                    getIconoOperador( operador.getIdPreseleccionEstacion() )
                            .setImageResource(R.drawable.ic_operador_blanco);
                    getIconoOperador( operador.getIdPreseleccionEstacion() )
                            .setBackground( getResources().getDrawable(R.drawable.contenedor_icono_seleccionado) );
                    operador.setEstado(Constantes.ESTADO.seleccionado);
                    this.botonCrear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            creaOrdenOperador();
                        }
                    });
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( operador.getEstado() == Constantes.ESTADO.seleccionado ){
                        setOperadorSeleccionado(null);
                        habilitaRecursos();
                        getIconoOperador( operador.getIdPreseleccionEstacion() )
                                .setImageResource(R.drawable.ic_operador_gris);
                        getIconoOperador( operador.getIdPreseleccionEstacion() )
                                .setBackground( getResources().getDrawable(R.drawable.contenedor_icono) );
                        operador.setEstado(Constantes.ESTADO.inicial);
                        this.botonCrear.setOnClickListener(null);
                        this.contenedorBotones.setVisibility(View.INVISIBLE);
                    }
                }
                break;
            }
        }
    }

    private void creaOrdenOperador(){
        if( validaMaquinaria( getOperadorSeleccionado().getClaveMaquina() ) ){
            Fragment fragment = new Fragment_CreaOrdenMantenimiento().newInstance( getOperadorSeleccionado().getClaveMaquina() );
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        }else{
            errorMaquinaria();
        }
    }

    private boolean validaMaquinaria(String clave){
        for( Maquinaria maquinariaLista : Catalogos.getInstancia().getCatalogoMaquinaria() ){
            if( maquinariaLista.getClave().equalsIgnoreCase(clave) ){
                return true;
            }
        }
        return false;
    }

    public void errorMaquinaria(){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_mensaje_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaError = builder.create();
        this.ventanaError.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaError.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText("No se encontró Maquinaria para el recurso seleccionado");

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

    private String getEtiquetaOperador(int posicion){
        switch (posicion){
            case 1: return "A5";
            case 2: return "A4";
            case 3: return "A3";
            case 4: return "A2";
            case 5: return "A1";
            case 6: return "B1";
            case 7: return "B2";
            case 8: return "B3";
        }
        return "";
    }

    private void habilitaRecursos(){
        for(OperadorBascula operador : this.listaOperadores){
            getIconoOperador( operador.getIdPreseleccionEstacion() ).setEnabled(true);
        }
    }

    private void deshabilitaRecursos(){
        for(OperadorBascula operador : this.listaOperadores){
            getIconoOperador( operador.getIdPreseleccionEstacion() ).setEnabled(false);
        }

        if( getOperadorSeleccionado() != null ){
            getIconoOperador( getOperadorSeleccionado().getIdPreseleccionEstacion() ).setEnabled(true);
        }
    }

    private ImageView getIconoOperador(int posicion){
        switch (posicion){
            case 1: return this.operador1;
            case 2: return this.operador2;
            case 3: return this.operador3;
            case 4: return this.operador4;
            case 5: return this.operador5;
            case 6: return this.operador6;
            case 7: return this.operador7;
            case 8: return this.operador8;
        }
        return null;
    }

    public void iniciaProcesando(){
        this.barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        this.barraProgreso.setVisibility(View.GONE);
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
}

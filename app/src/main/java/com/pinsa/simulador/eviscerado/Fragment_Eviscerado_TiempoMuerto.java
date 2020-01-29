package com.pinsa.simulador.eviscerado;

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

import com.pinsa.simulador.Fragment_CreaOrdenMantenimiento;
import com.pinsa.simulador.R;
import com.pinsa.simulador.utilerias.Catalogos;
import com.pinsa.simulador.utilerias.Constantes;
import com.pinsa.simulador.vista.Maquinaria;
import com.pinsa.simulador.vista.OperadorBascula;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Eviscerado_TiempoMuerto extends Fragment {

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
    private ImageView operador9;
    private ImageView operador10;
    private ImageView operador11;
    private ImageView operador12;
    private ImageView operador13;
    private ImageView operador14;
    private ImageView operador15;
    private ImageView operador16;
    private ImageView operador17;
    private ImageView operador18;
    private ImageView operador19;
    private ImageView operador20;

    private OperadorBascula operadorSeleccionado;
    private List<OperadorBascula> listaOperadores = new ArrayList<>();

    private AlertDialog ventanaError;
    private ProgressBar barraProgreso;
    private Button botonCrear;

    private OnFragmentInteractionListener mListener;

    public Fragment_Eviscerado_TiempoMuerto() {

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
    public static Fragment_Eviscerado_TiempoMuerto newInstance(String param1, String param2) {
        Fragment_Eviscerado_TiempoMuerto fragment = new Fragment_Eviscerado_TiempoMuerto();
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
        this.vista = inflater.inflate(R.layout.fragment_eviscerado_tiempo_muerto, container, false);
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

        this.operador9 = this.vista.findViewById(R.id.operador9);
        this.operador9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(9);
            }
        });

        this.operador10 = this.vista.findViewById(R.id.operador10);
        this.operador10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(10);
            }
        });

        this.operador11 = this.vista.findViewById(R.id.operador11);
        this.operador11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(11);
            }
        });

        this.operador12 = this.vista.findViewById(R.id.operador12);
        this.operador12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(12);
            }
        });

        this.operador13 = this.vista.findViewById(R.id.operador13);
        this.operador13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(13);
            }
        });

        this.operador14 = this.vista.findViewById(R.id.operador14);
        this.operador14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(14);
            }
        });

        this.operador15 = this.vista.findViewById(R.id.operador15);
        this.operador15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(15);
            }
        });

        this.operador16 = this.vista.findViewById(R.id.operador16);
        this.operador16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(16);
            }
        });

        this.operador17 = this.vista.findViewById(R.id.operador17);
        this.operador17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(17);
            }
        });

        this.operador18 = this.vista.findViewById(R.id.operador18);
        this.operador18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(18);
            }
        });

        this.operador19 = this.vista.findViewById(R.id.operador19);
        this.operador19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(19);
            }
        });

        this.operador20 = this.vista.findViewById(R.id.operador20);
        this.operador20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(20);
            }
        });

        creaObjetosVacios();
    }

    private void creaObjetosVacios(){
        if( this.listaOperadores.isEmpty() ){
            for( int posicion = 1; posicion <= 20; posicion++ ){
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
            case 1: return "A1";
            case 2: return "A2";
            case 3: return "A3";
            case 4: return "A4";
            case 5: return "A5";
            case 6: return "A6";
            case 7: return "A7";
            case 8: return "A8";
            case 9: return "A9";
            case 10: return "A10";
            case 11: return "B1";
            case 12: return "B2";
            case 13: return "B3";
            case 14: return "B4";
            case 15: return "B5";
            case 16: return "B6";
            case 17: return "B7";
            case 18: return "B8";
            case 19: return "B9";
            case 20: return "B10";
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
            case 9: return this.operador9;
            case 10: return this.operador10;
            case 11: return this.operador11;
            case 12: return this.operador12;
            case 13: return this.operador13;
            case 14: return this.operador14;
            case 15: return this.operador15;
            case 16: return this.operador16;
            case 17: return this.operador17;
            case 18: return this.operador18;
            case 19: return this.operador19;
            case 20: return this.operador20;
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

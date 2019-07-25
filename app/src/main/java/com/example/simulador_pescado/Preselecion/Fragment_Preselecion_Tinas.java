package com.example.simulador_pescado.Preselecion;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.Utilerias.Constantes;
import com.example.simulador_pescado.clases.Metodos_Retonables;
import com.example.simulador_pescado.clases.Utilidades;
import com.example.simulador_pescado.conexion.ObtenAsignados;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.OperadorBascula;
import com.example.simulador_pescado.vista.OperadorMontacargas;
import com.example.simulador_pescado.vista.Tina;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Preselecion_Tinas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Preselecion_Tinas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Preselecion_Tinas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;

    private LinearLayout contenedorBotones;

    private Button boton1;
    private Button boton2;

    private ImageView tina1;
    private ImageView tina2;
    private ImageView tina3;
    private ImageView tina4;
    private ImageView tina5;
    private ImageView tina6;
    private ImageView tina7;
    private ImageView tina8;
    private ImageView tina9;
    private ImageView tina10;
    private ImageView tina11;
    private ImageView tina12;

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

    private ImageView montacargas1;
    private ImageView montacargas2;
    private ImageView montacargas3;
    private ImageView montacargas4;

    private Tina tinaSeleccionada;
    private OperadorBascula operadorSeleccionado;
    private OperadorMontacargas montacargasSeleccionado;

    private List<Tina> listaTinas = new ArrayList<>();
    private List<OperadorBascula> listaOperadores = new ArrayList<>();
    private List<OperadorMontacargas> listaMontacargas = new ArrayList<>();

    private AlertDialog ventanaError;
    private ProgressBar barraProgreso;
    private SwipeRefreshLayout actualizar;
    private Fragment fragment;

    private OnFragmentInteractionListener mListener;

    public Fragment_Preselecion_Tinas() {
        // Required empty public constructor
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Preselecion_Tinas.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Preselecion_Tinas newInstance(String param1, String param2) {
        Fragment_Preselecion_Tinas fragment = new Fragment_Preselecion_Tinas();
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
        vista=inflater.inflate(R.layout.fragment_fragment__preselecion__tinas, container, false);

        iniciaComponentes();
        return vista;
    }

    private ImageView getIconoTina(int posicion){
        switch (posicion){
            case 1: return this.tina1;
            case 2: return this.tina2;
            case 3: return this.tina3;
            case 4: return this.tina4;
            case 5: return this.tina5;
            case 6: return this.tina6;
            case 7: return this.tina7;
            case 8: return this.tina8;
            case 9: return this.tina9;
            case 10: return this.tina10;
            case 11: return this.tina11;
            case 12: return this.tina12;
        }
        return null;
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
        }
        return null;
    }

    private ImageView getIconoMontacargas(int posicion){
        return null;
    }

    public void ordenaTinas(List<Tina> listaTinas){
        this.listaTinas = listaTinas;
        for( final Tina recursoTina : this.listaTinas ){
            if( recursoTina.getLibre() ){
                recursoTina.setEstado(Constantes.ESTADO.inicial);
            }else{
                recursoTina.setEstado(Constantes.ESTADO.seleccionado);
                accionIconoTina( recursoTina.getIdPosicion() );
            }
        }
    }

    public void ordenaOperadoresBascula(List<OperadorBascula> listaOperadores){
        this.listaOperadores = listaOperadores;
        for( final OperadorBascula recursoOperador : this.listaOperadores ){
            if( recursoOperador.getLibre() ){
                recursoOperador.setEstado(Constantes.ESTADO.inicial);
            }else{
                recursoOperador.setEstado(Constantes.ESTADO.seleccionado);
                accionIconoOperador( recursoOperador.getIdEstacion() );
            }
        }
    }

    public void ordenaOperadoresMontacargas(List<OperadorMontacargas> listaMontacargas){
        this.listaMontacargas = listaMontacargas;
        for( OperadorMontacargas recursoMontacargas : this.listaMontacargas ){

        }
    }

    public void errorServicio(ErrorServicio errorMensaje){
        String mensajeMostrar = errorMensaje.getMessage();
        if( errorMensaje.getMensaje() != null &&
                !errorMensaje.getMensaje().equalsIgnoreCase("") ){
            mensajeMostrar = errorMensaje.getMensaje();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        builder.setMessage(mensajeMostrar)
                .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ventanaError.dismiss();
                        creaObjetosVacios();
                    }
                });
        this.ventanaError = builder.create();
        this.ventanaError.show();
    }

    private void creaObjetosVacios(){
        iniciaProcesando();

        if( this.listaTinas.isEmpty() ){
            for( int posicion = 1; posicion <= 12; posicion++ ){
                final Tina recursoTina = new Tina();
                recursoTina.setIdPosicion(posicion);
                recursoTina.setLibre(true);
                recursoTina.setEstado(Constantes.ESTADO.inicial);
                this.listaTinas.add(recursoTina);
            }
        }

        if( this.listaOperadores.isEmpty() ){
            for( int posicion = 1; posicion <= 10; posicion++ ){
                final OperadorBascula recursoOperador = new OperadorBascula();
                recursoOperador.setIdEstacion(posicion);
                recursoOperador.setLibre(true);
                recursoOperador.setEstado(Constantes.ESTADO.inicial);
                this.listaOperadores.add(recursoOperador);
            }
        }

        if( this.listaMontacargas.isEmpty() ){

        }

        terminaProcesando();
    }

    private void accionIconoOperador(int posicion){
        for( OperadorBascula operador : this.listaOperadores ){
            if( operador.getIdEstacion() == posicion ){
                if( operador.getEstado() == Constantes.ESTADO.inicial ){
                    setOperadorSeleccionado(operador);
                    deshabilitaRecursos();
                    getIconoOperador( operador.getIdEstacion() ).
                            setImageResource(R.drawable.ic_operador1);
                    getIconoOperador( operador.getIdEstacion() ).
                            setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                    operador.setEstado( Constantes.ESTADO.seleccionado );
                    this.boton1.setText(R.string.liberarUsuario);
                    this.boton1.setEnabled(false);
                    this.boton2.setText(R.string.asignarUsuario);
                    this.boton2.setEnabled(true);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( operador.getEstado() == Constantes.ESTADO.seleccionado ){
                        setOperadorSeleccionado(null);
                        habilitaRecursos();
                        getIconoOperador( operador.getIdEstacion() ).
                                setImageResource(R.drawable.ic_operador2);
                        if( operador.getLibre() ){
                            getIconoOperador( operador.getIdEstacion() ).
                                    setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
                            operador.setEstado( Constantes.ESTADO.inicial );
                        }else{
                            getIconoOperador( operador.getIdEstacion() ).
                                    setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                            operador.setEstado( Constantes.ESTADO.asignado );
                        }
                        this.contenedorBotones.setVisibility(View.GONE);
                    }else{
                        setOperadorSeleccionado(operador);
                        deshabilitaRecursos();
                        getIconoOperador( operador.getIdEstacion() ).
                                setImageResource(R.drawable.ic_operador1);
                        getIconoOperador( operador.getIdEstacion() ).
                                setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                        operador.setEstado( Constantes.ESTADO.seleccionado );
                        this.boton1.setText(R.string.liberarUsuario);
                        this.boton1.setEnabled(true);
                        this.boton2.setText(R.string.asignarUsuario);
                        this.boton2.setEnabled(false);
                        this.contenedorBotones.setVisibility(View.VISIBLE);
                    }
                }
                break;
            }
        }
    }

    private void accionIconoTina(int posicion){
        for( Tina tina : this.listaTinas ){
            if( tina.getIdPosicion() == posicion ){
                if( tina.getEstado() == Constantes.ESTADO.inicial ){
                    setTinaSeleccionada(tina);
                    deshabilitaRecursos();
                    getIconoTina( tina.getIdPosicion() ).
                            setImageResource(R.drawable.ic_tina1);
                    getIconoTina( tina.getIdPosicion() ).
                            setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                    tina.setEstado( Constantes.ESTADO.seleccionado );
                    this.boton1.setText(R.string.liberarTina);
                    this.boton1.setEnabled(false);
                    this.boton2.setText(R.string.asignarTina);
                    this.boton2.setEnabled(true);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( tina.getEstado() == Constantes.ESTADO.seleccionado ){
                        setTinaSeleccionada(null);
                        habilitaRecursos();
                        getIconoTina( tina.getIdPosicion() ).
                                setImageResource(R.drawable.ic_tina2);
                        if( tina.getLibre() ){
                            getIconoTina( tina.getIdPosicion() ).
                                    setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
                            tina.setEstado( Constantes.ESTADO.inicial );
                        }else{
                            getIconoTina( tina.getIdPosicion() ).
                                    setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                            tina.setEstado( Constantes.ESTADO.asignado );
                        }
                        this.contenedorBotones.setVisibility(View.GONE);
                    }else{
                        setTinaSeleccionada(tina);
                        deshabilitaRecursos();
                        getIconoTina( tina.getIdPosicion() ).
                                setImageResource(R.drawable.ic_tina1);
                        getIconoTina( tina.getIdPosicion() ).
                                setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                        tina.setEstado( Constantes.ESTADO.seleccionado );
                        this.boton1.setText(R.string.liberarTina);
                        this.boton1.setEnabled(true);
                        this.boton2.setText(R.string.mezclarTina);
                        this.boton2.setEnabled(true);
                        this.contenedorBotones.setVisibility(View.VISIBLE);
                    }
                }
                break;
            }
        }
    }

    /*private void accionIconoMontacargas(Recurso recurso){
        if( recurso.getEstado() == Recurso.ESTADO.inicial ){
            setRecursoSeleccionado(recurso);
            deshabilitaRecursos();
            recurso.getIcono().setImageResource(R.drawable.ic_montacargas1);
            recurso.getIcono().setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
            recurso.setEstado( Recurso.ESTADO.seleccionado );
            this.boton1.setText(R.string.liberarUsuario);
            this.boton1.setEnabled(false);
            this.boton2.setText(R.string.asignarUsuario);
            this.boton2.setEnabled(true);
            this.contenedorBotones.setVisibility(View.VISIBLE);
        }else{
            setRecursoSeleccionado(null);
            habilitaRecursos();
            recurso.getIcono().setImageResource(R.drawable.ic_montacargas2);
            recurso.getIcono().setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
            recurso.setEstado( Recurso.ESTADO.inicial );
            this.contenedorBotones.setVisibility(View.GONE);
        }
    }*/

    public Fragment getFragment() {
        return fragment;
    }

    private void getAsignados(){
        if( getTinaSeleccionada() != null ){
            accionIconoTina( getTinaSeleccionada().getIdPosicion() );
        }

        if( getOperadorSeleccionado() != null ){
            accionIconoOperador( getOperadorSeleccionado().getIdEstacion() );
        }

        if( getMontacargasSeleccionado() != null ){

        }

        ObtenAsignados obtenAsignados = new ObtenAsignados( getFragment() );
        obtenAsignados.execute();
    }

    private void iniciaComponentes(){
        this.fragment = this;
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

        this.actualizar = this.vista.findViewById(R.id.actualizar);
        this.actualizar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actualizar.setRefreshing(true);
                getAsignados();
            }
        });

        this.contenedorBotones = this.vista.findViewById(R.id.contenedorBotones);
        this.boton1 = this.vista.findViewById(R.id.boton1);
        this.boton2 = this.vista.findViewById(R.id.boton2);

        this.tina1 = this.vista.findViewById(R.id.tina1);
        this.tina1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(1);
            }
        });

        this.tina2 = this.vista.findViewById(R.id.tina2);
        this.tina2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(2);
            }
        });

        this.tina3 = this.vista.findViewById(R.id.tina3);
        this.tina3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(3);
            }
        });

        this.tina4 = this.vista.findViewById(R.id.tina4);
        this.tina4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(4);
            }
        });

        this.tina5 = this.vista.findViewById(R.id.tina5);
        this.tina5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(5);
            }
        });

        this.tina6 = this.vista.findViewById(R.id.tina6);
        this.tina6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(6);
            }
        });

        this.tina7 = this.vista.findViewById(R.id.tina7);
        this.tina7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(8);
            }
        });

        this.tina8 = this.vista.findViewById(R.id.tina8);
        this.tina8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(9);
            }
        });

        this.tina9 = this.vista.findViewById(R.id.tina9);
        this.tina9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(9);
            }
        });

        this.tina10 = this.vista.findViewById(R.id.tina10);
        this.tina10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(10);
            }
        });

        this.tina11 = this.vista.findViewById(R.id.tina11);
        this.tina11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(11);
            }
        });

        this.tina12 = this.vista.findViewById(R.id.tina12);
        this.tina12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(12);
            }
        });

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

        getAsignados();
    }

    public void iniciaProcesando(){
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        this.barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        if( this.actualizar.isRefreshing() ){
            this.actualizar.setRefreshing(false);
        }
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        this.barraProgreso.setVisibility(View.GONE);
    }

    private void deshabilitaRecursos(){
        for(Tina tina : this.listaTinas){
            getIconoTina( tina.getIdPosicion() ).setEnabled(false);
        }

        for(OperadorBascula operador : this.listaOperadores){
            getIconoOperador( operador.getIdEstacion() ).setEnabled(false);
        }

        for(OperadorMontacargas montacargas : this.listaMontacargas){
            //montacargas.getIcono().setEnabled(false);
        }

        if( getTinaSeleccionada() != null ){
            getIconoTina( getTinaSeleccionada().getIdPosicion() ).setEnabled(true);
        }else{
            if( getOperadorSeleccionado() != null ){
                getIconoOperador( getOperadorSeleccionado().getIdEstacion() ).setEnabled(true);
            }else{
                if( getMontacargasSeleccionado() != null ){
                    //HABILITAR MONTACARGAS SELECCIONADO
                }
            }
        }
    }

    private void habilitaRecursos(){
        for(Tina tina : this.listaTinas){
            getIconoTina( tina.getIdPosicion() ).setEnabled(true);
        }

        for(OperadorBascula operador : this.listaOperadores){
            getIconoOperador( operador.getIdEstacion() ).setEnabled(true);
        }

        for(OperadorMontacargas montacargas : this.listaMontacargas){
            //montacargas.getIcono().setEnabled(true);
        }
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
    //mis cambios-----------------------------------------------------------------------------------------------------------------

}

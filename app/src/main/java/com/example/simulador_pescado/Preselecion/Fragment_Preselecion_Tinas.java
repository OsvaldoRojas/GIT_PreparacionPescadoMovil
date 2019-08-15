package com.example.simulador_pescado.Preselecion;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.Utilerias.Constantes;
import com.example.simulador_pescado.Utilerias.Utilerias;
import com.example.simulador_pescado.adaptadores.AdaptadorGrupoEspecie;
import com.example.simulador_pescado.adaptadores.AdaptadorMezclarSubtallas;
import com.example.simulador_pescado.adaptadores.AdaptadorSubtalla;
import com.example.simulador_pescado.adaptadores.AdaptadorTalla;
import com.example.simulador_pescado.conexion.CargaCatalogos;
import com.example.simulador_pescado.conexion.ObtenAsignados;
import com.example.simulador_pescado.conexion.ValidaGafete;
import com.example.simulador_pescado.conexion.ValidaTina;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.Gafete;
import com.example.simulador_pescado.vista.GrupoEspecie;
import com.example.simulador_pescado.vista.OperadorBascula;
import com.example.simulador_pescado.vista.OperadorMontacargas;
import com.example.simulador_pescado.vista.Subtalla;
import com.example.simulador_pescado.vista.Talla;
import com.example.simulador_pescado.vista.Tina;
import com.example.simulador_pescado.vista.TinaEscaneo;
import com.example.simulador_pescado.vista.TinaPosicion;
import com.example.simulador_pescado.vista.UsuarioLogueado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private LinearLayout contenedorMensaje;

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

    private TextView iconoTurno;

    private Tina tinaSeleccionada;
    private OperadorBascula operadorSeleccionado;
    private OperadorMontacargas montacargasSeleccionado;

    private List<Tina> listaMezclarTinas = new ArrayList<>();
    private List<Tina> listaTinas = new ArrayList<>();
    private List<OperadorBascula> listaOperadores = new ArrayList<>();
    private List<OperadorMontacargas> listaMontacargas = new ArrayList<>();
    private List<Subtalla> listaSubtalla = new ArrayList<>();
    private List<Talla> listaTalla = new ArrayList<>();
    private List<GrupoEspecie> listaGrupoEspecie = new ArrayList<>();

    private View.OnClickListener eventoLiberarTina;
    private View.OnClickListener eventoAsignarTina;
    private View.OnClickListener eventoMezclarTina;
    private View.OnClickListener eventoLiberarOperador;
    private View.OnClickListener eventoAsignarOperador;
    private View.OnClickListener eventoLiberarMontacargas;
    private View.OnClickListener eventoAsignarMontacargas;
    private View.OnClickListener eventoCancelarMezclar;
    private View.OnClickListener eventoAceptarMezclar;

    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private ProgressBar barraProgreso;
    private SwipeRefreshLayout actualizar;
    private Fragment fragment;

    private String fechaActual;
    private boolean esMontacargas;
    private boolean esMezclar;
    private boolean esInicio;

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
        switch (posicion){
            case 1: return this.montacargas1;
            case 2: return this.montacargas2;
            case 3: return this.montacargas3;
            case 4: return this.montacargas4;
        }
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
            if( recursoMontacargas.getLibre() ){
                recursoMontacargas.setEstado(Constantes.ESTADO.inicial);
            }else{
                recursoMontacargas.setEstado(Constantes.ESTADO.seleccionado);
                accionIconoMontacargas( recursoMontacargas.getIdMontacargaPreseleccion() );
            }
        }
    }

    public void marcaTurno(){
        if(UsuarioLogueado.getUsuarioLogueado(null).getTurno() == 1){
            this.iconoTurno.setText("T1");
            this.iconoTurno.setVisibility(View.VISIBLE);
        }else{
            this.iconoTurno.setText("T2");
            this.iconoTurno.setVisibility(View.VISIBLE);
        }
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
                        creaObjetosVacios();
                    }
                });
            }
        });
        this.ventanaError.show();
    }

    public void errorServicioAsignados(ErrorServicio errorMensaje){
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

    private String getEtiquetaMovil(int posicion){
        switch (posicion){
            case 1: return "A6";
            case 2: return "A5";
            case 3: return "A4";
            case 4: return "A3";
            case 5: return "A2";
            case 6: return "A1";
            case 7: return "B1";
            case 8: return "B2";
            case 9: return "B3";
            case 10: return "B4";
            case 11: return "B5";
            case 12: return "B6";
        }
        return "";
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
            case 9: return "B4";
            case 10: return "B5";
        }
        return "";
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

    private String getEtiquetaBasculaOperador(int posicion){
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

    private void creaObjetosVacios(){
        iniciaProcesando();

        if( this.listaTinas.isEmpty() ){
            for( int posicion = 1; posicion <= 12; posicion++ ){
                final Tina recursoTina = new Tina();
                recursoTina.setIdPosicion(posicion);
                //recursoTina.setLibre(true);
                recursoTina.setLibre(false);
                recursoTina.setEtiquetaMovil( getEtiquetaMovil(posicion) );
                //recursoTina.setEstado(Constantes.ESTADO.inicial);
                recursoTina.setEstado(Constantes.ESTADO.seleccionado);
                this.listaTinas.add(recursoTina);
                //quitar despues de pruebas
                TinaPosicion tp = new TinaPosicion();
                tp.setDescripcion("Tina Descripcion " + posicion);
                Subtalla st = new Subtalla();
                st.setDescripcion("Subtalla Descripcion " + posicion);
                recursoTina.setTina(tp);
                recursoTina.setSubtalla(st);
                accionIconoTina(posicion);
                //quitar despues de pruebas
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
            for( int posicion = 1; posicion <= 4; posicion++ ){
                OperadorMontacargas recursoMontacargas = new OperadorMontacargas();
                recursoMontacargas.setIdMontacargaPreseleccion(posicion);
                recursoMontacargas.setLibre(true);
                recursoMontacargas.setEstado(Constantes.ESTADO.inicial);
                this.listaMontacargas.add(recursoMontacargas);
            }
        }

        marcaTurno();
        terminaProcesando();
    }

    private void accionIconoOperador(int posicion){
        for( OperadorBascula operador : this.listaOperadores ){
            if( operador.getIdEstacion() == posicion ){
                if( operador.getEstado() == Constantes.ESTADO.inicial ){
                    setOperadorSeleccionado(operador);
                    deshabilitaRecursos();
                    getIconoOperador( operador.getIdEstacion() )
                            .setImageResource(R.drawable.ic_operador1);
                    getIconoOperador( operador.getIdEstacion() )
                            .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                    operador.setEstado( Constantes.ESTADO.seleccionado );
                    this.boton1.setText(R.string.liberarUsuario);
                    this.boton1.setEnabled(false);
                    this.boton1.setOnClickListener(null);
                    this.boton2.setText(R.string.asignarUsuario);
                    this.boton2.setEnabled(true);
                    this.boton2.setOnClickListener(this.eventoAsignarOperador);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( operador.getEstado() == Constantes.ESTADO.seleccionado ){
                        setOperadorSeleccionado(null);
                        habilitaRecursos();
                        getIconoOperador( operador.getIdEstacion() )
                                .setImageResource(R.drawable.ic_operador2);
                        if( operador.getLibre() ){
                            getIconoOperador( operador.getIdEstacion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
                            operador.setEstado( Constantes.ESTADO.inicial );
                        }else{
                            getIconoOperador( operador.getIdEstacion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                            operador.setEstado( Constantes.ESTADO.asignado );
                        }
                        this.contenedorBotones.setVisibility(View.GONE);
                    }else{
                        setOperadorSeleccionado(operador);
                        deshabilitaRecursos();
                        getIconoOperador( operador.getIdEstacion() )
                                .setImageResource(R.drawable.ic_operador1);
                        getIconoOperador( operador.getIdEstacion() )
                                .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                        operador.setEstado( Constantes.ESTADO.seleccionado );
                        this.boton1.setText(R.string.liberarUsuario);
                        this.boton1.setEnabled(true);
                        this.boton1.setOnClickListener(this.eventoLiberarOperador);
                        this.boton2.setText(R.string.asignarUsuario);
                        this.boton2.setEnabled(false);
                        this.boton2.setOnClickListener(null);
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
                    getIconoTina( tina.getIdPosicion() )
                            .setImageResource(R.drawable.ic_tina1);
                    getIconoTina( tina.getIdPosicion() )
                            .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                    tina.setEstado( Constantes.ESTADO.seleccionado );
                    this.boton1.setText(R.string.liberarTina);
                    this.boton1.setEnabled(false);
                    this.boton1.setOnClickListener(null);
                    this.boton2.setText(R.string.asignarTina);
                    this.boton2.setEnabled(true);
                    this.boton2.setOnClickListener(this.eventoAsignarTina);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( tina.getEstado() == Constantes.ESTADO.seleccionado ){
                        getIconoTina( tina.getIdPosicion() )
                                .setImageResource(R.drawable.ic_tina2);
                        if( tina.getLibre() ){
                            getIconoTina( tina.getIdPosicion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
                            tina.setEstado( Constantes.ESTADO.inicial );
                        }else{
                            getIconoTina( tina.getIdPosicion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                            tina.setEstado( Constantes.ESTADO.asignado );
                        }

                        if(esMezclar){
                            this.listaMezclarTinas.remove(tina);
                            if(this.listaMezclarTinas.size() == 0){
                                this.contenedorBotones.setVisibility(View.GONE);
                                this.contenedorMensaje.setVisibility(View.VISIBLE);
                            }
                        }else{
                            setTinaSeleccionada(null);
                            habilitaRecursos();
                            this.contenedorBotones.setVisibility(View.GONE);
                        }
                    }else{
                        getIconoTina( tina.getIdPosicion() )
                                .setImageResource(R.drawable.ic_tina1);
                        getIconoTina( tina.getIdPosicion() )
                                .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                        tina.setEstado( Constantes.ESTADO.seleccionado );

                        if(esMezclar){
                            this.listaMezclarTinas.add(tina);
                            this.contenedorMensaje.setVisibility(View.GONE);
                            this.boton1.setText(R.string.cancelar);
                            this.boton1.setEnabled(true);
                            this.boton1.setOnClickListener(this.eventoCancelarMezclar);
                            this.boton2.setText(R.string.aceptar);
                            this.boton2.setEnabled(true);
                            this.boton2.setOnClickListener(this.eventoAceptarMezclar);
                            this.contenedorBotones.setVisibility(View.VISIBLE);
                        }else{
                            setTinaSeleccionada(tina);
                            deshabilitaRecursos();
                            this.boton1.setText(R.string.liberarTina);
                            this.boton1.setEnabled(true);
                            this.boton1.setOnClickListener(this.eventoLiberarTina);
                            this.boton2.setText(R.string.mezclarTina);
                            this.boton2.setEnabled(true);
                            this.boton2.setOnClickListener(this.eventoMezclarTina);
                            this.contenedorBotones.setVisibility(View.VISIBLE);
                        }
                    }
                }
                break;
            }
        }
    }

    private void accionIconoMontacargas(int posicion){
        for( OperadorMontacargas montacargas : this.listaMontacargas ){
            if( montacargas.getIdMontacargaPreseleccion() == posicion ){
                if( montacargas.getEstado() == Constantes.ESTADO.inicial ){
                    setMontacargasSeleccionado(montacargas);
                    deshabilitaRecursos();
                    getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() )
                            .setImageResource(R.drawable.ic_montacargas1);
                    getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() )
                            .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                    montacargas.setEstado( Constantes.ESTADO.seleccionado );
                    this.boton1.setText(R.string.liberarUsuario);
                    this.boton1.setEnabled(false);
                    this.boton1.setOnClickListener(null);
                    this.boton2.setText(R.string.asignarUsuario);
                    this.boton2.setEnabled(true);
                    this.boton2.setOnClickListener(this.eventoAsignarMontacargas);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( montacargas.getEstado() == Constantes.ESTADO.seleccionado ){
                        setMontacargasSeleccionado(null);
                        habilitaRecursos();
                        getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() )
                                .setImageResource(R.drawable.ic_montacargas2);
                        if( montacargas.getLibre() ){
                            getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
                            montacargas.setEstado( Constantes.ESTADO.inicial );
                        }else{
                            getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                            montacargas.setEstado( Constantes.ESTADO.asignado );
                        }
                        this.contenedorBotones.setVisibility(View.GONE);
                    }else{
                        setMontacargasSeleccionado(montacargas);
                        deshabilitaRecursos();
                        getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() )
                                .setImageResource(R.drawable.ic_montacargas1);
                        getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() )
                                .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                        montacargas.setEstado( Constantes.ESTADO.seleccionado );
                        this.boton1.setText(R.string.liberarUsuario);
                        this.boton1.setEnabled(true);
                        this.boton1.setOnClickListener(this.eventoLiberarMontacargas);
                        this.boton2.setText(R.string.asignarUsuario);
                        this.boton2.setEnabled(false);
                        this.boton2.setOnClickListener(null);
                        this.contenedorBotones.setVisibility(View.VISIBLE);
                    }
                }
                break;
            }
        }
    }

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
            accionIconoMontacargas( getMontacargasSeleccionado().getIdMontacargaPreseleccion() );
        }

        ObtenAsignados obtenAsignados = new ObtenAsignados( getFragment() );
        obtenAsignados.execute();

        CargaCatalogos cargaCatalogos = new CargaCatalogos( getFragment() );
        cargaCatalogos.execute();
    }

    public void actualizaCatalogos(List<Talla> listaTalla, List<Subtalla> listaSubtalla, List<GrupoEspecie> listaEspecie){
        this.listaTalla = new ArrayList<>();
        this.listaSubtalla = new ArrayList<>();
        this.listaGrupoEspecie = new ArrayList<>();

        Talla talla = new Talla();
        talla.setDescripcion("Talla");
        this.listaTalla.add(talla);

        Subtalla subtalla = new Subtalla();
        subtalla.setDescripcion("Subtalla");
        this.listaSubtalla.add(subtalla);

        GrupoEspecie especie = new GrupoEspecie();
        especie.setDescripcion("Especie");
        this.listaGrupoEspecie.add(especie);

        this.listaTalla.addAll(listaTalla);
        this.listaSubtalla.addAll(listaSubtalla);
        this.listaGrupoEspecie.addAll(listaEspecie);
    }

    private void asignarTina(){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_asignar_tina, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaEmergente = builder.create();
        this.ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button botonCancelar = ventanaEmergente.findViewById(R.id.boton1);
                botonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        accionIconoTina( getTinaSeleccionada().getIdPosicion() );
                        ventanaEmergente.dismiss();
                    }
                });

                Spinner seleccionSubtalla = ventanaEmergente.findViewById(R.id.seleccionSubtalla);
                seleccionSubtalla.setAdapter( new AdaptadorSubtalla( getContext(), listaSubtalla ) );
                seleccionSubtalla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adaptadorVista, View vista, int posicion, long id) {
                        getTinaSeleccionada().setSubtalla( (Subtalla) adaptadorVista.getItemAtPosition(posicion) );
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                Spinner seleccionTalla = ventanaEmergente.findViewById(R.id.seleccionTalla);
                seleccionTalla.setAdapter( new AdaptadorTalla( getContext(), listaTalla ) );
                seleccionTalla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adaptadorVista, View vista, int posicion, long id) {
                        getTinaSeleccionada().setTalla( (Talla) adaptadorVista.getItemAtPosition(posicion) );
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                Spinner seleccionEspecie = ventanaEmergente.findViewById(R.id.seleccionEspecie);
                seleccionEspecie.setAdapter( new AdaptadorGrupoEspecie( getContext(), listaGrupoEspecie ) );
                seleccionEspecie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adaptadorVista, View vista, int posicion, long id) {
                        getTinaSeleccionada().setEspecie( (GrupoEspecie) adaptadorVista.getItemAtPosition(posicion) );
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                TextView etiquetaPosicion = ventanaEmergente.findViewById(R.id.etiquetaPosicion);
                etiquetaPosicion.setText( String.valueOf( getTinaSeleccionada().getEtiquetaMovil() ) );

                TextView etiquetaFecha = ventanaEmergente.findViewById(R.id.etiquetaFecha);
                etiquetaFecha.setText(fechaActual);

                EditText campoEscaner = ventanaEmergente.findViewById(R.id.campoEscaner);
                campoEscaner.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        validaTina( editable.toString() );
                    }
                });
            }
        });
        this.ventanaEmergente.show();
    }

    private void liberarTina(){
        System.out.println("LIBERAR TINA..............");
    }

    private void mezclarTina(){
        this.esMezclar = true;
        this.listaMezclarTinas = new ArrayList<>();
        this.contenedorBotones.setVisibility(View.GONE);
        this.contenedorMensaje.setVisibility(View.VISIBLE);

        habilitaTinas();
    }

    private void habilitaTinas(){
        for(Tina tina : this.listaTinas){
            if( !tina.getLibre() ){
                getIconoTina( tina.getIdPosicion() ).setEnabled(true);
            }
        }
        getIconoTina( getTinaSeleccionada().getIdPosicion() ).setEnabled(false);
    }

    private void asignarOperador(){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_asignar_empleado, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaEmergente = builder.create();
        this.ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button botonCancelar = ventanaEmergente.findViewById(R.id.boton1);
                botonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        accionIconoOperador( getOperadorSeleccionado().getIdEstacion() );
                        ventanaEmergente.dismiss();
                    }
                });

                TextView etiquetaFecha = ventanaEmergente.findViewById(R.id.etiquetaFecha);
                etiquetaFecha.setText(fechaActual);

                TextView etiquetaPosicion = ventanaEmergente.findViewById(R.id.etiquetaPosicion);
                etiquetaPosicion.setText( getEtiquetaOperador( getOperadorSeleccionado().getIdEstacion() ) );

                TextView etiquetaBascula = ventanaEmergente.findViewById(R.id.etiquetaBascula);
                etiquetaBascula.setText( getEtiquetaBasculaOperador( getOperadorSeleccionado().getIdEstacion() ) );

                TextView etiquetaTinaPrincipal = ventanaEmergente.findViewById(R.id.etiquetaTinaPrincipal);
                etiquetaTinaPrincipal.setText( getTinaPrincipalOperador( getOperadorSeleccionado().getIdEstacion() ) );

                TextView etiquetaSubtallaPrincipal = ventanaEmergente.findViewById(R.id.etiquetaSubtallaPrincipal);
                etiquetaSubtallaPrincipal.setText( getSubtallaPrincipalOperador( getOperadorSeleccionado().getIdEstacion() ) );

                TextView etiquetaTinaSecundaria = ventanaEmergente.findViewById(R.id.etiquetaTinaSecundaria);
                etiquetaTinaSecundaria.setText( getTinaSecundariaOperador( getOperadorSeleccionado().getIdEstacion() ) );

                TextView etiquetaSubtallaSecundaria = ventanaEmergente.findViewById(R.id.etiquetaSubtallaSecundaria);
                etiquetaSubtallaSecundaria.setText( getSubtallaSecundariaOperador( getOperadorSeleccionado().getIdEstacion() ) );

                EditText campoEscaner = ventanaEmergente.findViewById(R.id.campoEscaner);
                campoEscaner.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        esMontacargas = false;
                        validaGafete( editable.toString() );
                    }
                });
            }
        });
        this.ventanaEmergente.show();
    }

    private void liberarOperador(){
        System.out.println("LIBERAR OPERADOR...........");
    }

    private void asignarMontacargas(){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_asignar_montacargas, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaEmergente = builder.create();
        this.ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button botonCancelar = ventanaEmergente.findViewById(R.id.boton1);
                botonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        accionIconoMontacargas( getMontacargasSeleccionado().getIdMontacargaPreseleccion() );
                        ventanaEmergente.dismiss();
                    }
                });

                TextView etiquetaFecha = ventanaEmergente.findViewById(R.id.etiquetaFecha);
                etiquetaFecha.setText(fechaActual);

                TextView etiquetaPosicion = ventanaEmergente.findViewById(R.id.etiquetaPosicion);
                etiquetaPosicion.setText( getEtiquetaMontacargas( getMontacargasSeleccionado().getIdMontacargaPreseleccion() ) );

                EditText campoEscaner = ventanaEmergente.findViewById(R.id.campoEscaner);
                campoEscaner.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        esMontacargas = true;
                        validaGafete( editable.toString() );
                    }
                });
            }
        });
        this.ventanaEmergente.show();
    }

    private void liberarMontacargas(){
        System.out.println("LIBERAR MONTACARGAS...........");
    }

    private void cancelaMezclar(){
        this.esMezclar = false;
        accionIconoTina( getTinaSeleccionada().getIdPosicion() );
        for( Tina tina : this.listaMezclarTinas ){
            accionIconoTina( tina.getIdPosicion() );
        }
    }

    private void aceptaMezclar(){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_decision_mezclar, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaEmergente = builder.create();
        this.ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button botonCancelar = ventanaEmergente.findViewById(R.id.boton1);
                botonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ventanaEmergente.dismiss();
                    }
                });

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton2);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ventanaEmergente.dismiss();
                        resultadoMezclaTinas();
                    }
                });

                TextView etiquetaTinaSeleccionada = ventanaEmergente.findViewById(R.id.etiquetaTinaSeleccionada);
                etiquetaTinaSeleccionada.setText( getTinaSeleccionada().getEtiquetaMovil() );

                TextView etiquetaSubtallaSeleccionada = ventanaEmergente.findViewById(R.id.etiquetaSubtallaSeleccionada);
                etiquetaSubtallaSeleccionada.setText( getTinaSeleccionada().getSubtalla().getDescripcion() );

                AdaptadorMezclarSubtallas adaptador = new AdaptadorMezclarSubtallas(getContext(), listaMezclarTinas);
                ListView listaPosicionSubtalla = ventanaEmergente.findViewById(R.id.listaSubtallas);
                listaPosicionSubtalla.setAdapter(adaptador);
                Utilerias.setAlturaLista(listaPosicionSubtalla, 349);
            }
        });
        this.ventanaEmergente.show();
    }

    private void validaTina(String codigo){
        if( codigo.length() >= 15 ){
            iniciaProcesandoEmergente();
            ValidaTina validaTina = new ValidaTina(codigo, this);
            validaTina.execute();
        }else{
            EditText campoDescripcion = this.ventanaEmergente.findViewById(R.id.campoDescripcion);
            campoDescripcion.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoDescripcion.setTextColor( getResources().getColor(R.color.noValido) );
        }
    }

    public void resultadoEscaneoTina(TinaEscaneo resultadoTina){
        EditText campoDescripcion = this.ventanaEmergente.findViewById(R.id.campoDescripcion);

        if( resultadoTina.getIdTinaDes() != null ){
            campoDescripcion.setText( resultadoTina.getTinaDes() );
            campoDescripcion.setTextColor( getResources().getColor(R.color.siValido) );

            getTinaSeleccionada().getTina().setIdTina( Long.valueOf( resultadoTina.getIdTinaDes() ) );
            getTinaSeleccionada().getTina().setDescripcion( resultadoTina.getTinaDes() );
        } else{
            campoDescripcion.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoDescripcion.setTextColor( getResources().getColor(R.color.noValido) );
        }

        terminaProcesandoEmergente();
    }

    private void validaGafete(String codigo){
        if( codigo.length() >= 7 ){
            iniciaProcesandoEmergente();
            ValidaGafete validaGafete = new ValidaGafete(this, codigo);
            validaGafete.execute();
        }else{
            EditText campoNombre = this.ventanaEmergente.findViewById(R.id.campoNombre);
            campoNombre.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoNombre.setTextColor( getResources().getColor(R.color.noValido) );
        }
    }

    public void resultadoMezclaTinas(){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_mensaje_mezclar, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaEmergente = builder.create();
        this.ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton1);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cancelaMezclar();
                        ventanaEmergente.dismiss();
                    }
                });

                TextView etiquetaTinaSeleccionada = ventanaEmergente.findViewById(R.id.etiquetaTinaSeleccionada);
                etiquetaTinaSeleccionada.setText( getTinaSeleccionada().getEtiquetaMovil() );

                TextView etiquetaSubtallaSeleccionada = ventanaEmergente.findViewById(R.id.etiquetaSubtallaSeleccionada);
                etiquetaSubtallaSeleccionada.setText( getTinaSeleccionada().getSubtalla().getDescripcion() );

                AdaptadorMezclarSubtallas adaptador = new AdaptadorMezclarSubtallas(getContext(), listaMezclarTinas);
                ListView listaPosicionSubtalla = ventanaEmergente.findViewById(R.id.listaSubtallas);
                listaPosicionSubtalla.setAdapter(adaptador);
                Utilerias.setAlturaLista(listaPosicionSubtalla, 349);
            }
        });
        this.ventanaEmergente.show();
    }

    public void resultadoEscaneoGafete(Gafete resultadoGafete){
        EditText campoNombre = this.ventanaEmergente.findViewById(R.id.campoNombre);

        if( resultadoGafete.getResultado().equalsIgnoreCase("YES") ){
            campoNombre.setText( resultadoGafete.getEmpleado().getNom_trab() );
            campoNombre.setTextColor( getResources().getColor(R.color.siValido) );

            if(this.esMontacargas){
                //RELACIONAR EL ID DEL EMPLEADO DEL GAFETE ESCANEADO CON EL MONTACARGAS SELECCIONADO.
                //getMontacargasSeleccionado().setIdEmpleado();
            }else{
                getOperadorSeleccionado().getEmpleado()
                        .setClaveEmpleado( resultadoGafete.getEmpleado().getCla_trab() );
                getOperadorSeleccionado().getEmpleado()
                        .setNombre( resultadoGafete.getEmpleado().getNom_trab() );
                getOperadorSeleccionado().getEmpleado()
                        .setApellidoPaterno( resultadoGafete.getEmpleado().getAp_paterno() );
                getOperadorSeleccionado().getEmpleado()
                        .setApellidoMaterno( resultadoGafete.getEmpleado().getAp_materno() );
            }
        }else{
            campoNombre.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoNombre.setTextColor( getResources().getColor(R.color.noValido) );
        }

        terminaProcesandoEmergente();
    }

    private void liberaTurno(){
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
                etiquetaMensaje.setText("¿Desea terminar el turno?");

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton2);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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

    private String getTinaPrincipalOperador(int posicionOperador){
        for( Tina tina : this.listaTinas ){
            if( tina.getIdPosicion() == posicionOperador + 1 ){
                if( !tina.getLibre() ){
                    return tina.getEtiquetaMovil();
                }
                break;
            }
        }
        return "";
    }

    private String getSubtallaPrincipalOperador(int posicionOperador){
        for( Tina tina : this.listaTinas ){
            if( tina.getIdPosicion() == posicionOperador + 1 ){
                if( !tina.getLibre() ){
                    return tina.getSubtalla().getDescripcion();
                }
                break;
            }
        }
        return "";
    }

    private String getTinaSecundariaOperador(int posicionOperador){
        Tina tinaSecundaria;
        switch (posicionOperador){
            case 1:
                tinaSecundaria = getTina(1);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 2:
                tinaSecundaria = getTina(2);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 3:
                tinaSecundaria = getTina(3);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 4:
                tinaSecundaria = getTina(4);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 5:
                tinaSecundaria = getTina(5);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 6:
                tinaSecundaria = getTina(8);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 7:
                tinaSecundaria = getTina(9);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 8:
                tinaSecundaria = getTina(10);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 9:
                tinaSecundaria = getTina(11);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 10:
                tinaSecundaria = getTina(12);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
        }
        return "";
    }

    private String getSubtallaSecundariaOperador(int posicionOperador){
        Tina tinaSecundaria;
        switch (posicionOperador){
            case 1:
                tinaSecundaria = getTina(1);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 2:
                tinaSecundaria = getTina(2);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 3:
                tinaSecundaria = getTina(3);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 4:
                tinaSecundaria = getTina(4);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 5:
                tinaSecundaria = getTina(5);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 6:
                tinaSecundaria = getTina(8);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 7:
                tinaSecundaria = getTina(9);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 8:
                tinaSecundaria = getTina(10);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 9:
                tinaSecundaria = getTina(11);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 10:
                tinaSecundaria = getTina(12);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
        }
        return "";
    }

    private Tina getTina(int posicion){
        for( Tina tina : this.listaTinas ){
            if( tina.getIdPosicion() == posicion ){
                return tina;
            }
        }
        return null;
    }

    private void iniciaComponentes(){
        this.fragment = this;
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

        this.iconoTurno = this.vista.findViewById(R.id.iconoTurno);
        this.iconoTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liberaTurno();
            }
        });

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaActual = formatoFecha.format( new Date() );

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

        this.contenedorMensaje = this.vista.findViewById(R.id.contenedorMensaje);

        this.eventoCancelarMezclar = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelaMezclar();
            }
        };

        this.eventoAceptarMezclar = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aceptaMezclar();
            }
        };

        this.eventoAsignarTina = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asignarTina();
            }
        };

        this.eventoLiberarTina = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liberarTina();
            }
        };

        this.eventoMezclarTina = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mezclarTina();
            }
        };

        this.eventoAsignarOperador = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asignarOperador();
            }
        };

        this.eventoLiberarOperador = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liberarOperador();
            }
        };

        this.eventoAsignarMontacargas = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asignarMontacargas();
            }
        };

        this.eventoLiberarMontacargas = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liberarMontacargas();
            }
        };

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
                accionIconoTina(7);
            }
        });

        this.tina8 = this.vista.findViewById(R.id.tina8);
        this.tina8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(8);
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

        this.montacargas1 = this.vista.findViewById(R.id.montacargas1);
        this.montacargas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoMontacargas(1);
            }
        });

        this.montacargas2 = this.vista.findViewById(R.id.montacargas2);
        this.montacargas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoMontacargas(2);
            }
        });

        this.montacargas3 = this.vista.findViewById(R.id.montacargas3);
        this.montacargas3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoMontacargas(3);
            }
        });

        this.montacargas4 = this.vista.findViewById(R.id.montacargas4);
        this.montacargas4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoMontacargas(4);
            }
        });

        getAsignados();
    }

    public void iniciaProcesando(){
        this.barraProgreso.setVisibility(View.VISIBLE);
    }

    public void iniciaProcesandoEmergente(){
        ProgressBar barraProgreso = this.ventanaEmergente.findViewById(R.id.barraProgreso);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        this.ventanaEmergente.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesandoEmergente(){
        ProgressBar barraProgreso = this.ventanaEmergente.findViewById(R.id.barraProgreso);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        this.ventanaEmergente.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        barraProgreso.setVisibility(View.GONE);
    }

    public void terminaProcesando(){
        if( this.actualizar.isRefreshing() ){
            this.actualizar.setRefreshing(false);
        }
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
            getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() ).setEnabled(false);
        }

        if( getTinaSeleccionada() != null ){
            getIconoTina( getTinaSeleccionada().getIdPosicion() ).setEnabled(true);
        }else{
            if( getOperadorSeleccionado() != null ){
                getIconoOperador( getOperadorSeleccionado().getIdEstacion() ).setEnabled(true);
            }else{
                if( getMontacargasSeleccionado() != null ){
                    getIconoMontacargas( getMontacargasSeleccionado().getIdMontacargaPreseleccion() ).setEnabled(true);
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
            getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() ).setEnabled(true);
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
}

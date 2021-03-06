package com.grupo.pinsa.sip.views.simulador.preselecion;

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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorMezclarSubtallas;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorTinasLiberadas;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.contenedores.Contenedor_Asigna_Tina;
import com.grupo.pinsa.sip.views.simulador.utilerias.Constantes;
import com.grupo.pinsa.sip.views.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.views.simulador.modelo.OperadorBascula;
import com.grupo.pinsa.sip.views.simulador.modelo.OperadorMontacargas;
import com.grupo.pinsa.sip.views.simulador.modelo.Tina;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.MezclaTinaServicio;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Preselecion_Tinas extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    View vista;

    private LinearLayout contenedorBotones;
    private LinearLayout contenedorMensaje;
    private LinearLayout contenedorTurno;

    private Button boton1;
    private Button boton2;
    private Button botonTurno;

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

    private boolean esMezclar;

    private OnFragmentInteractionListener mListener;

    public Fragment_Preselecion_Tinas() {
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
        vista=inflater.inflate(R.layout.fragment_preselecion_tinas, container, false);

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
        if( isAdded() ){
            this.listaTinas = listaTinas;
            for( final Tina recursoTina : this.listaTinas ){
                recursoTina.setEstado(Constantes.ESTADO.seleccionado);
                accionIconoTina( recursoTina.getIdPreseleccionPosicionTina() );
            }
        }
    }

    public void ordenaOperadoresBascula(List<OperadorBascula> listaOperadores){
        if( isAdded() ){
            this.listaOperadores = listaOperadores;
            for( final OperadorBascula recursoOperador : this.listaOperadores ){
                recursoOperador.setEstado(Constantes.ESTADO.seleccionado);
                accionIconoOperador( recursoOperador.getIdPreseleccionEstacion() );
            }
        }
    }

    public void ordenaOperadoresMontacargas(List<OperadorMontacargas> listaMontacargas){
        if( isAdded() ){
            this.listaMontacargas = listaMontacargas;
            for( OperadorMontacargas recursoMontacargas : this.listaMontacargas ){
                recursoMontacargas.setEstado(Constantes.ESTADO.seleccionado);
                accionIconoMontacargas( recursoMontacargas.getIdPreseleccionMontacarga() );
            }
        }
    }

    public void marcaTurno(){
        if(UsuarioLogueado.getUsuarioLogueado().getTurno() == 1){
            this.iconoTurno.setText("T1");
            //this.iconoTurno.setVisibility(View.VISIBLE);
        }else{
            this.iconoTurno.setText("T2");
            //this.iconoTurno.setVisibility(View.VISIBLE);
        }
    }

    public void ventanaMensaje(final String mensaje){
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
                        getAsignados();
                    }
                });
            }
        });
        this.ventanaError.show();
    }

    private String getEtiquetaTina(int posicion){
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

    private void creaObjetosVacios(){
        iniciaProcesando();

        if( this.listaTinas.isEmpty() ){
            for( int posicion = 1; posicion <= 12; posicion++ ){
                final Tina recursoTina = new Tina();
                recursoTina.setIdPreseleccionPosicionTina(posicion);
                recursoTina.setLibre(true);
                recursoTina.setPosicion( getEtiquetaTina(posicion) );
                recursoTina.setEstado(Constantes.ESTADO.inicial);
                this.listaTinas.add(recursoTina);
            }
        }

        if( this.listaOperadores.isEmpty() ){
            for( int posicion = 1; posicion <= 10; posicion++ ){
                final OperadorBascula recursoOperador = new OperadorBascula();
                recursoOperador.setIdPreseleccionEstacion(posicion);
                recursoOperador.setLibre(true);
                recursoOperador.setEstacion( getEtiquetaOperador(posicion) );
                recursoOperador.setEstado(Constantes.ESTADO.inicial);
                this.listaOperadores.add(recursoOperador);
            }
        }

        if( this.listaMontacargas.isEmpty() ){
            for( int posicion = 1; posicion <= 4; posicion++ ){
                OperadorMontacargas recursoMontacargas = new OperadorMontacargas();
                recursoMontacargas.setIdPreseleccionMontacarga(posicion);
                recursoMontacargas.setLibre(true);
                recursoMontacargas.setEstado(Constantes.ESTADO.inicial);
                this.listaMontacargas.add(recursoMontacargas);
            }
        }

        marcaTurno();
        terminaProcesando();
    }

    private boolean validaMuestraTurno(){
        for( OperadorBascula operadorBascula : this.listaOperadores ){
            if( !operadorBascula.getLibre() ){
                return true;
            }
        }
        return false;
    }

    private void accionIconoOperador(int posicion){
        for( OperadorBascula operador : this.listaOperadores ){
            if( operador.getIdPreseleccionEstacion() == posicion ){
                if( operador.getEstado() == Constantes.ESTADO.inicial ){
                    setOperadorSeleccionado(operador);
                    //deshabilitaRecursos();
                    getIconoOperador( operador.getIdPreseleccionEstacion() )
                            .setImageResource(R.drawable.ic_operador_blanco);
                    getIconoOperador( operador.getIdPreseleccionEstacion() )
                            .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                    operador.setEstado( Constantes.ESTADO.seleccionado );
                    this.boton1.setText(R.string.liberarUsuario);
                    this.boton1.setEnabled(false);
                    this.boton1.setOnClickListener(null);
                    this.boton2.setText(R.string.asignarUsuario);
                    this.boton2.setEnabled(true);
                    this.boton2.setOnClickListener(this.eventoAsignarOperador);
                    this.contenedorTurno.setVisibility(View.GONE);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( operador.getEstado() == Constantes.ESTADO.seleccionado ){
                        setOperadorSeleccionado(null);
                        //habilitaRecursos();
                        if( operador.getLibre() ){
                            getIconoOperador( operador.getIdPreseleccionEstacion() )
                                    .setImageResource(R.drawable.ic_operador_gris);
                            getIconoOperador( operador.getIdPreseleccionEstacion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
                            operador.setEstado( Constantes.ESTADO.inicial );
                        }else{
                            getIconoOperador( operador.getIdPreseleccionEstacion() )
                                    .setImageResource(R.drawable.ic_operador_negro);
                            getIconoOperador( operador.getIdPreseleccionEstacion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_asignado ) );
                            operador.setEstado( Constantes.ESTADO.asignado );
                        }
                        this.contenedorBotones.setVisibility(View.GONE);
                        if( validaMuestraTurno() ){
                            this.contenedorTurno.setVisibility(View.VISIBLE);
                        }else{
                            this.contenedorTurno.setVisibility(View.GONE);
                        }
                    }else{
                        setOperadorSeleccionado(operador);
                        //deshabilitaRecursos();
                        getIconoOperador( operador.getIdPreseleccionEstacion() )
                                .setImageResource(R.drawable.ic_operador_blanco);
                        getIconoOperador( operador.getIdPreseleccionEstacion() )
                                .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                        operador.setEstado( Constantes.ESTADO.seleccionado );
                        this.boton1.setText(R.string.liberarUsuario);
                        this.boton1.setEnabled(true);
                        this.boton1.setOnClickListener(this.eventoLiberarOperador);
                        this.boton2.setText(R.string.asignarUsuario);
                        this.boton2.setEnabled(false);
                        this.boton2.setOnClickListener(null);
                        this.contenedorTurno.setVisibility(View.GONE);
                        this.contenedorBotones.setVisibility(View.VISIBLE);
                    }
                }
                break;
            }
        }
    }

    private void accionIconoTina(int posicion){
        for( Tina tina : this.listaTinas ){
            if( tina.getIdPreseleccionPosicionTina() == posicion ){
                if( tina.getEstado() == Constantes.ESTADO.inicial ){
                    setTinaSeleccionada(tina);
                    //deshabilitaRecursos();
                    getIconoTina( tina.getIdPreseleccionPosicionTina() )
                            .setImageResource(R.drawable.ic_tina_blanca);
                    getIconoTina( tina.getIdPreseleccionPosicionTina() )
                            .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                    tina.setEstado( Constantes.ESTADO.seleccionado );
                    this.boton1.setText(R.string.liberarTina);
                    this.boton1.setEnabled(false);
                    this.boton1.setOnClickListener(null);
                    this.boton2.setText(R.string.asignarTina);
                    this.boton2.setEnabled(true);
                    this.boton2.setOnClickListener(this.eventoAsignarTina);
                    this.contenedorTurno.setVisibility(View.GONE);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( tina.getEstado() == Constantes.ESTADO.seleccionado ){
                        if( tina.getLibre() ){
                            getIconoTina( tina.getIdPreseleccionPosicionTina() )
                                    .setImageResource(R.drawable.ic_tina_gris);
                            getIconoTina( tina.getIdPreseleccionPosicionTina() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
                            tina.setEstado( Constantes.ESTADO.inicial );
                        }else{
                            cambiaIconoLlenado(tina);
                            getIconoTina( tina.getIdPreseleccionPosicionTina() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_asignado ) );
                            tina.setEstado( Constantes.ESTADO.asignado );
                        }

                        if(this.esMezclar){
                            this.listaMezclarTinas.remove(tina);
                            if(this.listaMezclarTinas.size() == 0){
                                this.contenedorBotones.setVisibility(View.GONE);
                                this.contenedorMensaje.setVisibility(View.VISIBLE);
                            }
                        }else{
                            setTinaSeleccionada(null);
                            //habilitaRecursos();
                            this.contenedorBotones.setVisibility(View.GONE);
                            if( validaMuestraTurno() ){
                                this.contenedorTurno.setVisibility(View.VISIBLE);
                            }
                        }
                    }else{
                        getIconoTina( tina.getIdPreseleccionPosicionTina() )
                                .setImageResource(R.drawable.ic_tina_blanca);
                        getIconoTina( tina.getIdPreseleccionPosicionTina() )
                                .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                        tina.setEstado( Constantes.ESTADO.seleccionado );

                        if(this.esMezclar){
                            this.listaMezclarTinas.add(tina);
                            this.contenedorMensaje.setVisibility(View.GONE);
                            this.boton1.setText(R.string.cancelar);
                            this.boton1.setEnabled(true);
                            this.boton1.setOnClickListener(this.eventoCancelarMezclar);
                            this.boton2.setText(R.string.aceptar);
                            this.boton2.setEnabled(true);
                            this.boton2.setOnClickListener(this.eventoAceptarMezclar);
                        }else{
                            setTinaSeleccionada(tina);
                            //deshabilitaRecursos();
                            this.boton1.setText(R.string.liberarTina);
                            this.boton1.setEnabled(true);
                            this.boton1.setOnClickListener(this.eventoLiberarTina);
                            this.boton2.setText(R.string.mezclarTina);
                            if( validaMezcla() ){
                                this.boton2.setEnabled(true);
                            }else{
                                this.boton2.setEnabled(false);
                            }
                            this.boton2.setOnClickListener(this.eventoMezclarTina);
                            this.contenedorTurno.setVisibility(View.GONE);
                        }
                        this.contenedorBotones.setVisibility(View.VISIBLE);
                    }
                }
                break;
            }
        }
    }

    private boolean validaMezcla(){
        int cantidad = 0;
        for(Tina tina : this.listaTinas){
            if( !tina.getLibre() ){
                cantidad = cantidad+1;
            }
        }
        if(cantidad < 2){
            return false;
        }
        return true;
    }

    private void cambiaIconoLlenado(Tina tina){
        if( tina.getPorcentaje() >= 0 &&
                tina.getPorcentaje() < 20 ){
            getIconoTina( tina.getIdPreseleccionPosicionTina() )
                    .setImageResource(R.drawable.ic_tina_negra);
        }else{
            if( tina.getPorcentaje() >= 20 &&
                    tina.getPorcentaje() < 40 ){
                getIconoTina( tina.getIdPreseleccionPosicionTina() )
                        .setImageResource(R.drawable.ic_tina20);
            }else{
                if( tina.getPorcentaje() >= 40 &&
                        tina.getPorcentaje() < 80 ){
                    getIconoTina( tina.getIdPreseleccionPosicionTina() )
                            .setImageResource(R.drawable.ic_tina40);
                }else{
                    if( tina.getPorcentaje() >= 80 &&
                            tina.getPorcentaje() < 100 ){
                        getIconoTina( tina.getIdPreseleccionPosicionTina() )
                                .setImageResource(R.drawable.ic_tina80);
                    }else{
                        getIconoTina( tina.getIdPreseleccionPosicionTina() )
                                .setImageResource(R.drawable.ic_tina100);
                    }
                }
            }
        }
    }

    private void accionIconoMontacargas(int posicion){
        for( OperadorMontacargas montacargas : this.listaMontacargas ){
            if( montacargas.getIdPreseleccionMontacarga() == posicion ){
                if( montacargas.getEstado() == Constantes.ESTADO.inicial ){
                    setMontacargasSeleccionado(montacargas);
                    //deshabilitaRecursos();
                    getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() )
                            .setImageResource(R.drawable.ic_montacargas_blanco);
                    getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() )
                            .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                    montacargas.setEstado( Constantes.ESTADO.seleccionado );
                    this.boton1.setText(R.string.liberarUsuario);
                    this.boton1.setEnabled(false);
                    this.boton1.setOnClickListener(null);
                    this.boton2.setText(R.string.asignarUsuario);
                    this.boton2.setEnabled(true);
                    this.boton2.setOnClickListener(this.eventoAsignarMontacargas);
                    this.contenedorTurno.setVisibility(View.GONE);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( montacargas.getEstado() == Constantes.ESTADO.seleccionado ){
                        setMontacargasSeleccionado(null);
                        //habilitaRecursos();
                        if( montacargas.getLibre() ){
                            getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() )
                                    .setImageResource(R.drawable.ic_montacargas_gris);
                            getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
                            montacargas.setEstado( Constantes.ESTADO.inicial );
                        }else{
                            getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() )
                                    .setImageResource(R.drawable.ic_montacargas_negro);
                            getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_asignado ) );
                            montacargas.setEstado( Constantes.ESTADO.asignado );
                        }
                        this.contenedorBotones.setVisibility(View.GONE);
                        if( validaMuestraTurno() ){
                            this.contenedorTurno.setVisibility(View.VISIBLE);
                        }
                    }else{
                        setMontacargasSeleccionado(montacargas);
                        //deshabilitaRecursos();
                        getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() )
                                .setImageResource(R.drawable.ic_montacargas_blanco);
                        getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() )
                                .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                        montacargas.setEstado( Constantes.ESTADO.seleccionado );
                        this.boton1.setText(R.string.liberarUsuario);
                        this.boton1.setEnabled(true);
                        this.boton1.setOnClickListener(this.eventoLiberarMontacargas);
                        this.boton2.setText(R.string.asignarUsuario);
                        this.boton2.setEnabled(false);
                        this.boton2.setOnClickListener(null);
                        this.contenedorTurno.setVisibility(View.GONE);
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
            accionIconoTina( getTinaSeleccionada().getIdPreseleccionPosicionTina() );
        }

        if( getOperadorSeleccionado() != null ){
            accionIconoOperador( getOperadorSeleccionado().getIdPreseleccionEstacion() );
        }

        if( getMontacargasSeleccionado() != null ){
            accionIconoMontacargas( getMontacargasSeleccionado().getIdPreseleccionMontacarga() );
        }

        getTinasAsignadas();
    }

    private void limpiaSeleccionado(int posicion){
        if(!this.esMezclar){
            if( getTinaSeleccionada() != null
                    && getTinaSeleccionada().getIdPreseleccionPosicionTina() != posicion ){
                accionIconoTina( getTinaSeleccionada().getIdPreseleccionPosicionTina() );
            }

            if( getOperadorSeleccionado() != null
                    && getOperadorSeleccionado().getIdPreseleccionEstacion() != posicion ){
                accionIconoOperador( getOperadorSeleccionado().getIdPreseleccionEstacion() );
            }

            if( getMontacargasSeleccionado() != null
                    && getMontacargasSeleccionado().getIdPreseleccionMontacarga() != posicion ){
                accionIconoMontacargas( getMontacargasSeleccionado().getIdPreseleccionMontacarga() );
            }
        }
    }

    private void getTinasAsignadas(){
        Call<List<Tina>> tinasAsignadas = APIServicios.getConexion().getTinasAsignadas();
        tinasAsignadas.enqueue(new Callback<List<Tina>>() {
            @Override
            public void onResponse(Call<List<Tina>> call, Response<List<Tina>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        ordenaTinas( response.body() );
                        getMontacragasAsignados();
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener las tinas asignadas");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Tina>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void getOperadoresAsignados(){
        Call<List<OperadorBascula>> operadoresAsignados = APIServicios.getConexion().getOperadoresAsignados();
        operadoresAsignados.enqueue(new Callback<List<OperadorBascula>>() {
            @Override
            public void onResponse(Call<List<OperadorBascula>> call, Response<List<OperadorBascula>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        ordenaOperadoresBascula( response.body() );
                        marcaTurno();
                        terminaProcesando();
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener los operadores asignados");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OperadorBascula>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void getMontacragasAsignados(){
        Call<List<OperadorMontacargas>> montacargasAsignados = APIServicios.getConexion().getMontacargasAsignados();
        montacargasAsignados.enqueue(new Callback<List<OperadorMontacargas>>() {
            @Override
            public void onResponse(Call<List<OperadorMontacargas>> call, Response<List<OperadorMontacargas>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        ordenaOperadoresMontacargas( response.body() );
                        getOperadoresAsignados();
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener montacargas asignados");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OperadorMontacargas>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void asignarTina(){
        Fragment fragment = new Contenedor_Asigna_Tina().newInstance( 0, getTinaSeleccionada() );
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private void liberarTina(){
        iniciaProcesando();
        JsonObject json = new JsonObject();
        json.addProperty("idPreseleccionPosicionTina", getTinaSeleccionada().getIdPreseleccionPosicionTina() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().liberaTina(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        resultadoAsignacion("La posición fue liberada exitosamente");
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al liberar la tina" );
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

    private void mezclarTina(){
        this.esMezclar = true;
        this.listaMezclarTinas = new ArrayList<>();
        this.contenedorBotones.setVisibility(View.GONE);
        this.contenedorMensaje.setVisibility(View.VISIBLE);

        //TODO: PROBLEMA AL MEXCLAR TINAS YA QUE QUITA LA PRIMERA SELECCIONADA AL SELECCIONES LAS DEMAS

        //habilitaTinas();
        for(OperadorBascula operador : this.listaOperadores){
            getIconoOperador( operador.getIdPreseleccionEstacion() ).setEnabled(false);
        }

        for(OperadorMontacargas montacargas : this.listaMontacargas){
            getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() ).setEnabled(false);
        }

        for(Tina tina : this.listaTinas){
            if( tina.getLibre() ){
                getIconoTina( tina.getIdPreseleccionPosicionTina() ).setEnabled(false);
            }
        }

        getIconoTina( getTinaSeleccionada().getIdPreseleccionPosicionTina() ).setEnabled(false);
    }

    private void habilitaTinas(){
        for(Tina tina : this.listaTinas){
            if( !tina.getLibre() ){
                getIconoTina( tina.getIdPreseleccionPosicionTina() ).setEnabled(true);
            }
        }
        getIconoTina( getTinaSeleccionada().getIdPreseleccionPosicionTina() ).setEnabled(false);
    }

    private void asignarOperador(){
        Fragment fragment = new Fragment_Asigna_Operador().newInstance( getOperadorSeleccionado(), (Serializable) this.listaTinas );
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private void liberarOperador(){
        iniciaProcesando();
        getOperadorSeleccionado().setIdEmpleado(0);
        getOperadorSeleccionado().setLibre(true);
        guardaOperador();
    }

    private void guardaOperador(){
        JsonObject json = new JsonObject();
        json.addProperty("idPreseleccionEstacion", getOperadorSeleccionado().getIdPreseleccionEstacion() );
        json.addProperty("estacion", getOperadorSeleccionado().getEstacion() );
        json.addProperty("idPosicionPrincipal", getOperadorSeleccionado().getIdPosicionPrincipal() );
        json.addProperty("idPosicionAlterna", getOperadorSeleccionado().getIdPosicionAlterna() );
        json.addProperty("idEmpleado", getOperadorSeleccionado().getIdEmpleado() );
        json.addProperty("turno", getOperadorSeleccionado().getTurno() );
        json.addProperty("libre", getOperadorSeleccionado().getLibre() );
        //json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaOperador(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        resultadoAsignacion("El operador fue liberado exitosamente");
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al liberar al operador" );
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

    private void asignarMontacargas(){
        Fragment fragment = new Fragment_Asigna_Montacargas().newInstance( getMontacargasSeleccionado() );
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private void liberarMontacargas(){
        iniciaProcesando();
        getMontacargasSeleccionado().setIdEmpleado("0");
        getMontacargasSeleccionado().setLibre(true);
        guardaMontacargas();
    }

    private void guardaMontacargas(){
        JsonObject json = new JsonObject();
        json.addProperty("idPreseleccionMontacarga", getMontacargasSeleccionado().getIdPreseleccionMontacarga() );
        json.addProperty("idEmpleado", getMontacargasSeleccionado().getIdEmpleado() );
        json.addProperty("turno", getMontacargasSeleccionado().getTurno() );
        json.addProperty("libre", getMontacargasSeleccionado().getLibre() );
        //json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaMontacargas(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        resultadoAsignacion("El operador fue liberado exitosamente");
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al liberar al operador" );
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

    public void resultadoAsignacion(String mensaje){
        ventanaMensaje(mensaje);
    }

    private void cancelaMezclar(){
        this.esMezclar = false;
        accionIconoTina( getTinaSeleccionada().getIdPreseleccionPosicionTina() );
        for( Tina tina : this.listaMezclarTinas ){
            accionIconoTina( tina.getIdPreseleccionPosicionTina() );
        }

        for(OperadorBascula operador : this.listaOperadores){
            getIconoOperador( operador.getIdPreseleccionEstacion() ).setEnabled(true);
        }

        for(OperadorMontacargas montacargas : this.listaMontacargas){
            getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() ).setEnabled(true);
        }

        for(Tina tina : this.listaTinas){
            getIconoTina( tina.getIdPreseleccionPosicionTina() ).setEnabled(true);
        }

    }

    private String getMensajeMezclar(){
        for( Tina tinaMezcla : this.listaMezclarTinas ){
            if( tinaMezcla.getSubtalla().getIdSubtalla() != getTinaSeleccionada().getSubtalla().getIdSubtalla() ){
                return getResources().getString(R.string.mezclarSubtallasDistintas);
            }
        }
        return getResources().getString(R.string.decisionMezclar);
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
                        iniciaProcesando();
                        mezclaTinasServicio();
                        ventanaEmergente.dismiss();
                    }
                });

                TextView etiquetaMensaje = ventanaEmergente.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText( getMensajeMezclar() );

                TextView etiquetaTinaSeleccionada = ventanaEmergente.findViewById(R.id.etiquetaTinaSeleccionada);
                etiquetaTinaSeleccionada.setText(
                        getTinaSeleccionada().getPosicion().concat(" - ")
                                .concat( getTinaSeleccionada().getSubtalla().getDescripcion() )
                );

                AdaptadorMezclarSubtallas adaptador = new AdaptadorMezclarSubtallas(getContext(), listaMezclarTinas);
                ListView listaPosicionSubtalla = ventanaEmergente.findViewById(R.id.listaSubtallas);
                listaPosicionSubtalla.setAdapter(adaptador);
                Utilerias.setAlturaLista(listaPosicionSubtalla, 349);
            }
        });
        this.ventanaEmergente.show();
    }

    private void mezclaTinasServicio(){
        MezclaTinaServicio mezclaTinaServicio = new MezclaTinaServicio();
        mezclaTinaServicio.setIdTinaPrincipal( getTinaSeleccionada().getIdPreseleccionPosicionTina() );
        List<Integer> listaTinas = new ArrayList<>();
        for( Tina tina : this.listaMezclarTinas ){
            listaTinas.add( tina.getIdPreseleccionPosicionTina() );
        }
        mezclaTinaServicio.setPosicionesTina(listaTinas);
        mezclaTinaServicio.setUsuario( UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().mezclaTinas(mezclaTinaServicio);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    terminaProcesando();
                    if( response.code() == 200 ){
                        resultadoMezclaTinas();
                    }else{
                        errorServicio( "Error al mezclar tinas" );
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
                        getAsignados();
                    }
                });

                TextView etiquetaTinaSeleccionada = ventanaEmergente.findViewById(R.id.etiquetaTinaSeleccionada);
                etiquetaTinaSeleccionada.setText(
                        getTinaSeleccionada().getPosicion().concat(" - ")
                                .concat( getTinaSeleccionada().getSubtalla().getDescripcion() )
                );

                AdaptadorMezclarSubtallas adaptador = new AdaptadorMezclarSubtallas(getContext(), listaMezclarTinas);
                ListView listaPosicionSubtalla = ventanaEmergente.findViewById(R.id.listaSubtallas);
                listaPosicionSubtalla.setAdapter(adaptador);
                Utilerias.setAlturaLista(listaPosicionSubtalla, 349);

                List<Tina> tinasLiberadas = new ArrayList<>();
                tinasLiberadas.addAll(listaMezclarTinas);
                tinasLiberadas.add( getTinaSeleccionada() );
                AdaptadorTinasLiberadas adaptadorLiberadas = new AdaptadorTinasLiberadas(getContext(), tinasLiberadas);
                ListView listaLiberadas = ventanaEmergente.findViewById(R.id.listaLiberadas);
                listaLiberadas.setAdapter(adaptadorLiberadas);
                Utilerias.setAlturaLista(listaLiberadas, 349);
            }
        });
        this.ventanaEmergente.show();
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
                etiquetaMensaje.setText("¿Está seguro de liberar todos los operadores?");

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton2);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iniciaProcesando();
                        liberaTurnoServicio();
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

    private void liberaTurnoServicio(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario());
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().liberaTurno(jsonObject);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        ventanaMensaje("Los operadores fueron liberados exitosamente");
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al liberar el turno" );
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
        this.contenedorTurno = this.vista.findViewById(R.id.contenedorTurno);
        this.botonTurno = this.vista.findViewById(R.id.botonTurno);
        this.botonTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liberaTurno();
            }
        });

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
                limpiaSeleccionado(1);
                accionIconoTina(1);
            }
        });
        this.tina1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                navegaDetalleTina(1);
                return true;
            }
        });

        this.tina2 = this.vista.findViewById(R.id.tina2);
        this.tina2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(2);
                accionIconoTina(2);
            }
        });
        this.tina2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                navegaDetalleTina(2);
                return true;
            }
        });

        this.tina3 = this.vista.findViewById(R.id.tina3);
        this.tina3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(3);
                accionIconoTina(3);
            }
        });
        this.tina3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                navegaDetalleTina(3);
                return true;
            }
        });

        this.tina4 = this.vista.findViewById(R.id.tina4);
        this.tina4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(4);
                accionIconoTina(4);
            }
        });
        this.tina4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                navegaDetalleTina(4);
                return true;
            }
        });

        this.tina5 = this.vista.findViewById(R.id.tina5);
        this.tina5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(5);
                accionIconoTina(5);
            }
        });
        this.tina5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                navegaDetalleTina(5);
                return true;
            }
        });

        this.tina6 = this.vista.findViewById(R.id.tina6);
        this.tina6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(6);
                accionIconoTina(6);
            }
        });
        this.tina6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                navegaDetalleTina(6);
                return true;
            }
        });

        this.tina7 = this.vista.findViewById(R.id.tina7);
        this.tina7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(7);
                accionIconoTina(7);
            }
        });
        this.tina7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                navegaDetalleTina(7);
                return true;
            }
        });

        this.tina8 = this.vista.findViewById(R.id.tina8);
        this.tina8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(8);
                accionIconoTina(8);
            }
        });
        this.tina8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                navegaDetalleTina(8);
                return true;
            }
        });

        this.tina9 = this.vista.findViewById(R.id.tina9);
        this.tina9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(9);
                accionIconoTina(9);
            }
        });
        this.tina9.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                navegaDetalleTina(9);
                return true;
            }
        });

        this.tina10 = this.vista.findViewById(R.id.tina10);
        this.tina10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(10);
                accionIconoTina(10);
            }
        });
        this.tina10.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                navegaDetalleTina(10);
                return true;
            }
        });

        this.tina11 = this.vista.findViewById(R.id.tina11);
        this.tina11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(11);
                accionIconoTina(11);
            }
        });
        this.tina11.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                navegaDetalleTina(11);
                return true;
            }
        });

        this.tina12 = this.vista.findViewById(R.id.tina12);
        this.tina12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(12);
                accionIconoTina(12);
            }
        });
        this.tina12.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                navegaDetalleTina(12);
                return true;
            }
        });

        this.operador1 = this.vista.findViewById(R.id.operador1);
        this.operador1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(1);
                accionIconoOperador(1);
            }
        });

        this.operador2 = this.vista.findViewById(R.id.operador2);
        this.operador2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(2);
                accionIconoOperador(2);
            }
        });

        this.operador3 = this.vista.findViewById(R.id.operador3);
        this.operador3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(3);
                accionIconoOperador(3);
            }
        });

        this.operador4 = this.vista.findViewById(R.id.operador4);
        this.operador4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(4);
                accionIconoOperador(4);
            }
        });

        this.operador5 = this.vista.findViewById(R.id.operador5);
        this.operador5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(5);
                accionIconoOperador(5);
            }
        });

        this.operador6 = this.vista.findViewById(R.id.operador6);
        this.operador6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(6);
                accionIconoOperador(6);
            }
        });

        this.operador7 = this.vista.findViewById(R.id.operador7);
        this.operador7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(7);
                accionIconoOperador(7);
            }
        });

        this.operador8 = this.vista.findViewById(R.id.operador8);
        this.operador8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(8);
                accionIconoOperador(8);
            }
        });

        this.operador9 = this.vista.findViewById(R.id.operador9);
        this.operador9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(9);
                accionIconoOperador(9);
            }
        });

        this.operador10 = this.vista.findViewById(R.id.operador10);
        this.operador10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(10);
                accionIconoOperador(10);
            }
        });

        this.montacargas1 = this.vista.findViewById(R.id.montacargas1);
        this.montacargas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(1);
                accionIconoMontacargas(1);
            }
        });

        this.montacargas2 = this.vista.findViewById(R.id.montacargas2);
        this.montacargas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(2);
                accionIconoMontacargas(2);
            }
        });

        this.montacargas3 = this.vista.findViewById(R.id.montacargas3);
        this.montacargas3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(3);
                accionIconoMontacargas(3);
            }
        });

        this.montacargas4 = this.vista.findViewById(R.id.montacargas4);
        this.montacargas4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(4);
                accionIconoMontacargas(4);
            }
        });

        getAsignados();
    }

    private void navegaDetalleTina(int posicion){
        if(!this.esMezclar){
            for( Tina tina : this.listaTinas ){
                if( tina.getIdPreseleccionPosicionTina() == posicion ){
                    if( !tina.getLibre() ){
                        if( tina.getIdAsignacionCocida() > 0 ){
                            Fragment fragment = Fragment_Asigna_Tina_Cocida.newInstance( tina, false );
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
                        }else{
                            Fragment fragment = Fragment_Asigna_Tina.newInstance( tina, false );
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
                        }
                    }
                    break;
                }
            }
        }
    }

    public void iniciaProcesando(){
        this.barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        if( this.actualizar.isRefreshing() ){
            this.actualizar.setRefreshing(false);
        }
        this.barraProgreso.setVisibility(View.GONE);
    }

    private void deshabilitaRecursos(){
        for(Tina tina : this.listaTinas){
            getIconoTina( tina.getIdPreseleccionPosicionTina() ).setEnabled(false);
        }

        for(OperadorBascula operador : this.listaOperadores){
            getIconoOperador( operador.getIdPreseleccionEstacion() ).setEnabled(false);
        }

        for(OperadorMontacargas montacargas : this.listaMontacargas){
            getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() ).setEnabled(false);
        }

        if( getTinaSeleccionada() != null ){
            getIconoTina( getTinaSeleccionada().getIdPreseleccionPosicionTina() ).setEnabled(true);
        }else{
            if( getOperadorSeleccionado() != null ){
                getIconoOperador( getOperadorSeleccionado().getIdPreseleccionEstacion() ).setEnabled(true);
            }else{
                if( getMontacargasSeleccionado() != null ){
                    getIconoMontacargas( getMontacargasSeleccionado().getIdPreseleccionMontacarga() ).setEnabled(true);
                }
            }
        }
    }

    private void habilitaRecursos(){
        for(Tina tina : this.listaTinas){
            getIconoTina( tina.getIdPreseleccionPosicionTina() ).setEnabled(true);
        }

        for(OperadorBascula operador : this.listaOperadores){
            getIconoOperador( operador.getIdPreseleccionEstacion() ).setEnabled(true);
        }

        for(OperadorMontacargas montacargas : this.listaMontacargas){
            getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() ).setEnabled(true);
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

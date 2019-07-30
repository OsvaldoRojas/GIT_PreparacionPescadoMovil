package com.example.simulador_pescado.Preselecion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.Utilerias.Constantes;
import com.example.simulador_pescado.clases.Metodos_Retonables;
import com.example.simulador_pescado.clases.Utilidades;
import com.example.simulador_pescado.vista.Bascula;
import com.example.simulador_pescado.vista.OperadorBascula;
import com.example.simulador_pescado.vista.OperadorMontacargas;
import com.example.simulador_pescado.vista.Tina;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Preselecion_TiempoMuerto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Preselecion_TiempoMuerto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Preselecion_TiempoMuerto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;

    private LinearLayout contenedorBotones;

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

    private ImageView bascula1;
    private ImageView bascula2;
    private ImageView bascula3;
    private ImageView bascula4;
    private ImageView bascula5;
    private ImageView bascula6;
    private ImageView bascula7;
    private ImageView bascula8;
    private ImageView bascula9;
    private ImageView bascula10;

    private ImageView montacargas1;
    private ImageView montacargas2;
    private ImageView montacargas3;
    private ImageView montacargas4;

    private Tina tinaSeleccionada;
    private OperadorBascula operadorSeleccionado;
    private OperadorMontacargas montacargasSeleccionado;
    private Bascula basculaSeleccionada;

    private List<Tina> listaTinas = new ArrayList<>();
    private List<OperadorBascula> listaOperadores = new ArrayList<>();
    private List<OperadorMontacargas> listaMontacargas = new ArrayList<>();
    private List<Bascula> listaBasculas = new ArrayList<>();

    private AlertDialog ventanaEmergente;
    private ProgressBar barraProgreso;
    private Button botonCrear;

    private View.OnClickListener eventoCreaOrdenTina;
    private View.OnClickListener eventoCreaOrdenOperador;
    private View.OnClickListener eventoCreaOrdenMontacargas;
    private View.OnClickListener eventoCreaOrdenBascula;

    private String fechaActual;

    private OnFragmentInteractionListener mListener;

    public Fragment_Preselecion_TiempoMuerto() {
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

    public Bascula getBasculaSeleccionada() {
        return basculaSeleccionada;
    }

    public void setBasculaSeleccionada(Bascula basculaSeleccionada) {
        this.basculaSeleccionada = basculaSeleccionada;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Preselecion_TiempoMuerto.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Preselecion_TiempoMuerto newInstance(String param1, String param2) {
        Fragment_Preselecion_TiempoMuerto fragment = new Fragment_Preselecion_TiempoMuerto();
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
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_fragment__preselecion__tiempo_muerto, container, false);

        iniciaComponentes();
        return vista;
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

    private void creaOrdenTina(){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_orden_mantenimiento, null);
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

                TextView etiquetaFecha = ventanaEmergente.findViewById(R.id.etiquetaFecha);
                etiquetaFecha.setText(fechaActual);

                TextView etiquetaEquipo = ventanaEmergente.findViewById(R.id.etiquetaEquipo);
                etiquetaEquipo.setText( "Tina ".concat( getEtiquetaMovil( getTinaSeleccionada().getIdPosicion() ) ) );
            }
        });
        this.ventanaEmergente.show();
    }

    private void creaOrdenOperador(){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_orden_mantenimiento, null);
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

                TextView etiquetaEquipo = ventanaEmergente.findViewById(R.id.etiquetaEquipo);
                etiquetaEquipo.setText( "Operador ".concat( getEtiquetaOperador( getOperadorSeleccionado().getIdEstacion() ) ) );
            }
        });
        this.ventanaEmergente.show();
    }

    private void creaOrdenBascula(){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_orden_mantenimiento, null);
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
                        accionIconoBascula( getBasculaSeleccionada().getIdBascula() );
                        ventanaEmergente.dismiss();
                    }
                });

                TextView etiquetaFecha = ventanaEmergente.findViewById(R.id.etiquetaFecha);
                etiquetaFecha.setText(fechaActual);

                TextView etiquetaEquipo = ventanaEmergente.findViewById(R.id.etiquetaEquipo);
                etiquetaEquipo.setText( "Báscula ".concat( getEtiquetaBascula( getBasculaSeleccionada().getIdBascula() ) ) );
            }
        });
        this.ventanaEmergente.show();
    }

    private void creaOrdenMontacargas(){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_orden_mantenimiento, null);
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

                TextView etiquetaEquipo = ventanaEmergente.findViewById(R.id.etiquetaEquipo);
                etiquetaEquipo.setText( "Montacargas ".concat( getEtiquetaMontacargas( getMontacargasSeleccionado().getIdMontacargaPreseleccion() ) ) );
            }
        });
        this.ventanaEmergente.show();
    }

    private void iniciaComponentes(){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaActual = formatoFecha.format( new Date() );

        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);

        this.contenedorBotones = this.vista.findViewById(R.id.contenedorBotones);
        this.botonCrear = this.vista.findViewById(R.id.botonOrden);

        this.eventoCreaOrdenTina = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creaOrdenTina();
            }
        };

        this.eventoCreaOrdenOperador = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creaOrdenOperador();
            }
        };

        this.eventoCreaOrdenBascula = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creaOrdenBascula();
            }
        };

        this.eventoCreaOrdenMontacargas = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creaOrdenMontacargas();
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

        this.bascula1 = this.vista.findViewById(R.id.bascula1);
        this.bascula1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoBascula(1);
            }
        });

        this.bascula2 = this.vista.findViewById(R.id.bascula2);
        this.bascula2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoBascula(2);
            }
        });

        this.bascula3 = this.vista.findViewById(R.id.bascula3);
        this.bascula3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoBascula(3);
            }
        });

        this.bascula4 = this.vista.findViewById(R.id.bascula4);
        this.bascula4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoBascula(4);
            }
        });

        this.bascula5 = this.vista.findViewById(R.id.bascula5);
        this.bascula5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoBascula(5);
            }
        });

        this.bascula6 = this.vista.findViewById(R.id.bascula6);
        this.bascula6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoBascula(6);
            }
        });

        this.bascula7 = this.vista.findViewById(R.id.bascula7);
        this.bascula7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoBascula(7);
            }
        });

        this.bascula8 = this.vista.findViewById(R.id.bascula8);
        this.bascula8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoBascula(8);
            }
        });

        this.bascula9 = this.vista.findViewById(R.id.bascula9);
        this.bascula9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoBascula(9);
            }
        });

        this.bascula10 = this.vista.findViewById(R.id.bascula10);
        this.bascula10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoBascula(10);
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

        creaObjetosVacios();
    }

    private void creaObjetosVacios(){
        if( this.listaTinas.isEmpty() ){
            for( int posicion = 1; posicion <= 12; posicion++ ){
                final Tina recursoTina = new Tina();
                recursoTina.setIdPosicion(posicion);
                recursoTina.setEstado(Constantes.ESTADO.inicial);
                this.listaTinas.add(recursoTina);
            }
        }

        if( this.listaOperadores.isEmpty() ){
            for( int posicion = 1; posicion <= 10; posicion++ ){
                final OperadorBascula recursoOperador = new OperadorBascula();
                recursoOperador.setIdEstacion(posicion);
                recursoOperador.setEstado(Constantes.ESTADO.inicial);
                this.listaOperadores.add(recursoOperador);
            }
        }

        if( this.listaMontacargas.isEmpty() ){
            for( int posicion = 1; posicion <= 4; posicion++ ){
                OperadorMontacargas recursoMontacargas = new OperadorMontacargas();
                recursoMontacargas.setIdMontacargaPreseleccion(posicion);
                recursoMontacargas.setEstado(Constantes.ESTADO.inicial);
                this.listaMontacargas.add(recursoMontacargas);
            }
        }

        if( this.listaBasculas.isEmpty() ){
            for( int posicion = 1; posicion <=10; posicion++ ){
                Bascula recursoBascula = new Bascula();
                recursoBascula.setIdBascula(posicion);
                recursoBascula.setEstado(Constantes.ESTADO.inicial);
                this.listaBasculas.add(recursoBascula);
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

        for(Bascula bascula : this.listaBasculas){
            getIconoBascula( bascula.getIdBascula() ).setEnabled(true);
        }
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

        for(Bascula bascula : this.listaBasculas){
            getIconoBascula( bascula.getIdBascula() ).setEnabled(false);
        }

        if( getTinaSeleccionada() != null ){
            getIconoTina( getTinaSeleccionada().getIdPosicion() ).setEnabled(true);
        }else{
            if( getOperadorSeleccionado() != null ){
                getIconoOperador( getOperadorSeleccionado().getIdEstacion() ).setEnabled(true);
            }else{
                if( getMontacargasSeleccionado() != null ){
                    getIconoMontacargas( getMontacargasSeleccionado().getIdMontacargaPreseleccion() ).setEnabled(true);
                }else{
                    if( getBasculaSeleccionada() != null ){
                        getIconoBascula( getBasculaSeleccionada().getIdBascula() ).setEnabled(true);
                    }
                }
            }
        }
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

    private ImageView getIconoBascula(int posicion){
        switch (posicion){
            case 1: return this.bascula1;
            case 2: return this.bascula2;
            case 3: return this.bascula3;
            case 4: return this.bascula4;
            case 5: return this.bascula5;
            case 6: return this.bascula6;
            case 7: return this.bascula7;
            case 8: return this.bascula8;
            case 9: return this.bascula9;
            case 10: return this.bascula10;
        }
        return null;
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
                            .setBackground( getResources().getDrawable(R.drawable.contenedor_icono_seleccionado) );
                    tina.setEstado(Constantes.ESTADO.seleccionado);
                    this.botonCrear.setOnClickListener(this.eventoCreaOrdenTina);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( tina.getEstado() == Constantes.ESTADO.seleccionado ){
                        setTinaSeleccionada(null);
                        habilitaRecursos();
                        getIconoTina( tina.getIdPosicion() )
                                .setImageResource(R.drawable.ic_tina2);
                        getIconoTina( tina.getIdPosicion() )
                                .setBackground( getResources().getDrawable(R.drawable.contenedor_icono) );
                        tina.setEstado(Constantes.ESTADO.inicial);
                        this.botonCrear.setOnClickListener(null);
                        this.contenedorBotones.setVisibility(View.GONE);
                    }
                }
                break;
            }
        }
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
                            .setBackground( getResources().getDrawable(R.drawable.contenedor_icono_seleccionado) );
                    operador.setEstado(Constantes.ESTADO.seleccionado);
                    this.botonCrear.setOnClickListener(this.eventoCreaOrdenOperador);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( operador.getEstado() == Constantes.ESTADO.seleccionado ){
                        setOperadorSeleccionado(null);
                        habilitaRecursos();
                        getIconoOperador( operador.getIdEstacion() )
                                .setImageResource(R.drawable.ic_operador2);
                        getIconoOperador( operador.getIdEstacion() )
                                .setBackground( getResources().getDrawable(R.drawable.contenedor_icono) );
                        operador.setEstado(Constantes.ESTADO.inicial);
                        this.botonCrear.setOnClickListener(null);
                        this.contenedorBotones.setVisibility(View.GONE);
                    }
                }
                break;
            }
        }
    }

    private void accionIconoBascula(int posicion){
        for( Bascula bascula : this.listaBasculas ){
            if( bascula.getIdBascula() == posicion ){
                if( bascula.getEstado() == Constantes.ESTADO.inicial ){
                    setBasculaSeleccionada(bascula);
                    deshabilitaRecursos();
                    getIconoBascula( bascula.getIdBascula() )
                            .setImageResource(R.drawable.ic_bascula1);
                    getIconoBascula( bascula.getIdBascula() )
                            .setBackground( getResources().getDrawable(R.drawable.contenedor_icono_seleccionado) );
                    bascula.setEstado(Constantes.ESTADO.seleccionado);
                    this.botonCrear.setOnClickListener(this.eventoCreaOrdenBascula);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( bascula.getEstado() == Constantes.ESTADO.seleccionado ){
                        setBasculaSeleccionada(null);
                        habilitaRecursos();
                        getIconoBascula( bascula.getIdBascula() )
                                .setImageResource(R.drawable.ic_bascula2);
                        getIconoBascula( bascula.getIdBascula() )
                                .setBackground( getResources().getDrawable(R.drawable.contenedor_icono) );
                        bascula.setEstado(Constantes.ESTADO.inicial);
                        this.botonCrear.setOnClickListener(null);
                        this.contenedorBotones.setVisibility(View.GONE);
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
                            .setBackground( getResources().getDrawable(R.drawable.contenedor_icono_seleccionado) );
                    montacargas.setEstado(Constantes.ESTADO.seleccionado);
                    this.botonCrear.setOnClickListener(this.eventoCreaOrdenMontacargas);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( montacargas.getEstado() == Constantes.ESTADO.seleccionado ){
                        setMontacargasSeleccionado(null);
                        habilitaRecursos();
                        getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() )
                                .setImageResource(R.drawable.ic_montacargas2);
                        getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() )
                                .setBackground( getResources().getDrawable(R.drawable.contenedor_icono) );
                        montacargas.setEstado(Constantes.ESTADO.inicial);
                        this.botonCrear.setOnClickListener(null);
                        this.contenedorBotones.setVisibility(View.GONE);
                    }
                }
                break;
            }
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

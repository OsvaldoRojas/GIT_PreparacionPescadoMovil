package com.example.simulador_pescado.preselecion;

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
import com.example.simulador_pescado.vista.Bascula;
import com.example.simulador_pescado.vista.Maquinaria;
import com.example.simulador_pescado.vista.OperadorBascula;
import com.example.simulador_pescado.vista.OperadorMontacargas;
import com.example.simulador_pescado.vista.Tina;

import java.util.ArrayList;
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

    private AlertDialog ventanaError;
    private ProgressBar barraProgreso;
    private Button botonCrear;

    private View.OnClickListener eventoCreaOrdenTina;
    private View.OnClickListener eventoCreaOrdenOperador;
    private View.OnClickListener eventoCreaOrdenMontacargas;
    private View.OnClickListener eventoCreaOrdenBascula;

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
        vista=inflater.inflate(R.layout.fragment_preselecion_tiempo_muerto, container, false);

        iniciaComponentes();
        return vista;
    }

    private boolean validaMaquinaria(String clave){
        for( Maquinaria maquinariaLista : Catalogos.getInstancia().getCatalogoMaquinaria() ){
            if( maquinariaLista.getClave().equalsIgnoreCase(clave) ){
                return true;
            }
        }
        return false;
    }

    private void creaOrdenTina(){
        if( validaMaquinaria( getTinaSeleccionada().getClaveMaquina() ) ){
            Fragment fragment = new Fragment_CreaOrdenMantenimiento().newInstance( getTinaSeleccionada().getClaveMaquina() );
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        }else{
            errorMaquinaria();
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

    private void creaOrdenBascula(){
        if( validaMaquinaria( getBasculaSeleccionada().getClaveMaquina() ) ){
            Fragment fragment = new Fragment_CreaOrdenMantenimiento().newInstance( getBasculaSeleccionada().getClaveMaquina() );
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        }else{
            errorMaquinaria();
        }
    }

    private void creaOrdenMontacargas(){
        if( validaMaquinaria( getMontacargasSeleccionado().getClaveMaquina() ) ){
            Fragment fragment = new Fragment_CreaOrdenMantenimiento().newInstance( getMontacargasSeleccionado().getClaveMaquina() );
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        }else{
            errorMaquinaria();
        }
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

    private void iniciaComponentes(){
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

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
                Tina recursoTina = new Tina();
                recursoTina.setIdPreseleccionPosicionTina(posicion);
                recursoTina.setPosicion( getEtiquetaTina(posicion) );
                recursoTina.setClaveMaquina("MAQ-TIN-" + posicion);
                recursoTina.setEstado(Constantes.ESTADO.inicial);
                this.listaTinas.add(recursoTina);
            }
        }

        if( this.listaOperadores.isEmpty() ){
            for( int posicion = 1; posicion <= 10; posicion++ ){
                OperadorBascula recursoOperador = new OperadorBascula();
                recursoOperador.setIdPreseleccionEstacion(posicion);
                recursoOperador.setEstacion( getEtiquetaOperador(posicion) );
                recursoOperador.setClaveMaquina("MAQ-OPE-" + posicion);
                recursoOperador.setEstado(Constantes.ESTADO.inicial);
                this.listaOperadores.add(recursoOperador);
            }
        }

        if( this.listaMontacargas.isEmpty() ){
            for( int posicion = 1; posicion <= 4; posicion++ ){
                OperadorMontacargas recursoMontacargas = new OperadorMontacargas();
                recursoMontacargas.setIdPreseleccionMontacarga(posicion);
                recursoMontacargas.setClaveMaquina("MAQ-MON-" + posicion);
                recursoMontacargas.setEstado(Constantes.ESTADO.inicial);
                this.listaMontacargas.add(recursoMontacargas);
            }
        }

        if( this.listaBasculas.isEmpty() ){
            for( int posicion = 1; posicion <=10; posicion++ ){
                Bascula recursoBascula = new Bascula();
                recursoBascula.setIdBascula(posicion);
                recursoBascula.setClaveMaquina("MAQ-BAS-" + posicion);
                recursoBascula.setEstado(Constantes.ESTADO.inicial);
                this.listaBasculas.add(recursoBascula);
            }
        }

        terminaProcesando();
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

        for(Bascula bascula : this.listaBasculas){
            getIconoBascula( bascula.getIdBascula() ).setEnabled(true);
        }
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

        for(Bascula bascula : this.listaBasculas){
            getIconoBascula( bascula.getIdBascula() ).setEnabled(false);
        }

        if( getTinaSeleccionada() != null ){
            getIconoTina( getTinaSeleccionada().getIdPreseleccionPosicionTina() ).setEnabled(true);
        }else{
            if( getOperadorSeleccionado() != null ){
                getIconoOperador( getOperadorSeleccionado().getIdPreseleccionEstacion() ).setEnabled(true);
            }else{
                if( getMontacargasSeleccionado() != null ){
                    getIconoMontacargas( getMontacargasSeleccionado().getIdPreseleccionMontacarga() ).setEnabled(true);
                }else{
                    if( getBasculaSeleccionada() != null ){
                        getIconoBascula( getBasculaSeleccionada().getIdBascula() ).setEnabled(true);
                    }
                }
            }
        }
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
            if( tina.getIdPreseleccionPosicionTina() == posicion ){
                if( tina.getEstado() == Constantes.ESTADO.inicial ){
                    setTinaSeleccionada(tina);
                    deshabilitaRecursos();
                    getIconoTina( tina.getIdPreseleccionPosicionTina() )
                            .setImageResource(R.drawable.ic_tina_blanca);
                    getIconoTina( tina.getIdPreseleccionPosicionTina() )
                            .setBackground( getResources().getDrawable(R.drawable.contenedor_icono_seleccionado) );
                    tina.setEstado(Constantes.ESTADO.seleccionado);
                    this.botonCrear.setOnClickListener(this.eventoCreaOrdenTina);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( tina.getEstado() == Constantes.ESTADO.seleccionado ){
                        setTinaSeleccionada(null);
                        habilitaRecursos();
                        getIconoTina( tina.getIdPreseleccionPosicionTina() )
                                .setImageResource(R.drawable.ic_tina_gris);
                        getIconoTina( tina.getIdPreseleccionPosicionTina() )
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
            if( operador.getIdPreseleccionEstacion() == posicion ){
                if( operador.getEstado() == Constantes.ESTADO.inicial ){
                    setOperadorSeleccionado(operador);
                    deshabilitaRecursos();
                    getIconoOperador( operador.getIdPreseleccionEstacion() )
                            .setImageResource(R.drawable.ic_operador_blanco);
                    getIconoOperador( operador.getIdPreseleccionEstacion() )
                            .setBackground( getResources().getDrawable(R.drawable.contenedor_icono_seleccionado) );
                    operador.setEstado(Constantes.ESTADO.seleccionado);
                    this.botonCrear.setOnClickListener(this.eventoCreaOrdenOperador);
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
                            .setImageResource(R.drawable.ic_bascula_blanca);
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
                                .setImageResource(R.drawable.ic_bascula_gris);
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
            if( montacargas.getIdPreseleccionMontacarga() == posicion ){
                if( montacargas.getEstado() == Constantes.ESTADO.inicial ){
                    setMontacargasSeleccionado(montacargas);
                    deshabilitaRecursos();
                    getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() )
                            .setImageResource(R.drawable.ic_montacargas_blanco);
                    getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() )
                            .setBackground( getResources().getDrawable(R.drawable.contenedor_icono_seleccionado) );
                    montacargas.setEstado(Constantes.ESTADO.seleccionado);
                    this.botonCrear.setOnClickListener(this.eventoCreaOrdenMontacargas);
                    this.contenedorBotones.setVisibility(View.VISIBLE);
                }else{
                    if( montacargas.getEstado() == Constantes.ESTADO.seleccionado ){
                        setMontacargasSeleccionado(null);
                        habilitaRecursos();
                        getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() )
                                .setImageResource(R.drawable.ic_montacargas_gris);
                        getIconoMontacargas( montacargas.getIdPreseleccionMontacarga() )
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

    public void iniciaProcesando(){
        //getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        this.barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        //getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

package com.grupo.pinsa.sip.views.simulador.descongelado;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.utilerias.Constantes;
import com.grupo.pinsa.sip.views.simulador.modelo.PosicionEstibaDescongelado;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Descongelado_Plan extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View vista;

    private ImageView posicion1;
    private ImageView posicion2;
    private ImageView posicion3;
    private ImageView posicion4;
    private ImageView posicion5;
    private ImageView posicion6;
    private ImageView posicion7;
    private ImageView posicion8;
    private ImageView posicion9;
    private ImageView posicion10;
    private ImageView posicion11;
    private ImageView posicion12;
    private ImageView posicion13;
    private ImageView posicion14;
    private ImageView posicion15;
    private ImageView posicion16;
    private ImageView posicion17;
    private ImageView posicion18;
    private ImageView posicion19;
    private ImageView posicion20;
    private ImageView posicion21;
    private ImageView posicion22;
    private ImageView posicion23;
    private ImageView posicion24;
    private ImageView posicion25;
    private ImageView posicion26;
    private ImageView posicion27;
    private ImageView posicion28;
    private ImageView posicion29;
    private ImageView posicion30;
    private ImageView posicion31;
    private ImageView posicion32;
    private ImageView posicion33;
    private ImageView posicion34;
    private ImageView posicion35;
    private ImageView posicion36;
    private ImageView posicion37;
    private ImageView posicion38;
    private ImageView posicion39;
    private ImageView posicion40;
    private ImageView posicion41;
    private ImageView posicion42;
    private ImageView posicion43;
    private ImageView posicion44;
    private ImageView posicion45;
    private ImageView posicion46;
    private ImageView posicion47;
    private ImageView posicion48;
    private ImageView posicion49;
    private ImageView posicion50;
    private ImageView posicion51;
    private ImageView posicion52;
    private ImageView posicion53;
    private ImageView posicion54;
    private ImageView posicion55;
    private ImageView posicion56;
    private ImageView posicion57;
    private ImageView posicion58;
    private ImageView posicion59;
    private ImageView posicion60;
    private ImageView posicion61;
    private ImageView posicion62;
    private ImageView posicion63;
    private ImageView posicion64;
    private ImageView posicion65;
    private ImageView posicion66;
    private ImageView posicion67;
    private ImageView posicion68;
    private ImageView posicion69;
    private ImageView posicion70;
    private ImageView posicion71;
    private ImageView posicion72;
    private ImageView posicion73;
    private ImageView posicion74;
    private ImageView posicion75;
    private ImageView posicion76;
    private ImageView posicion77;
    private ImageView posicion78;
    private ImageView posicion79;
    private ImageView posicion80;

    private PosicionEstibaDescongelado posicionSeleccionada;

    private List<PosicionEstibaDescongelado> listaPosiciones = new ArrayList<>();

    private LinearLayout botonera;
    private SwipeRefreshLayout actualizar;
    private Button boton1;
    private Button boton2;
    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;


    private OnFragmentInteractionListener mListener;

    public Fragment_Descongelado_Plan() {
    }

    public PosicionEstibaDescongelado getPosicionSeleccionada() {
        return posicionSeleccionada;
    }

    public void setPosicionSeleccionada(PosicionEstibaDescongelado posicionSeleccionada) {
        this.posicionSeleccionada = posicionSeleccionada;
    }

    public static Fragment_Descongelado_Plan newInstance(String param1, String param2) {
        Fragment_Descongelado_Plan fragment = new Fragment_Descongelado_Plan();
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
        this.vista = inflater.inflate(R.layout.fragment_descongelado_plan, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        this.actualizar = this.vista.findViewById(R.id.actualizar);
        this.actualizar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actualizar.setRefreshing(true);
                if( getPosicionSeleccionada() != null ){
                    accionIconoPosicion( getPosicionSeleccionada().getIdPosicion() );
                }
                obtenPosiciones();
            }
        });

        this.botonera = this.vista.findViewById(R.id.botonera);

        this.boton1 = this.vista.findViewById(R.id.boton1);
        this.boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirma("¿Está seguro que desea liberar la estiba?");
            }
        });

        this.boton2 = this.vista.findViewById(R.id.boton2);
        this.boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                muestraDetalle();
            }
        });

        iniciaProcesando();

        this.posicion1 = this.vista.findViewById(R.id.posicion1);
        this.posicion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(1);
                accionIconoPosicion(1);
            }
        });

        this.posicion2 = this.vista.findViewById(R.id.posicion2);
        this.posicion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(2);
                accionIconoPosicion(2);
            }
        });

        this.posicion3 = this.vista.findViewById(R.id.posicion3);
        this.posicion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(3);
                accionIconoPosicion(3);
            }
        });

        this.posicion4 = this.vista.findViewById(R.id.posicion4);
        this.posicion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(4);
                accionIconoPosicion(4);
            }
        });

        this.posicion5 = this.vista.findViewById(R.id.posicion5);
        this.posicion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(5);
                accionIconoPosicion(5);
            }
        });

        this.posicion6 = this.vista.findViewById(R.id.posicion6);
        this.posicion6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(6);
                accionIconoPosicion(6);
            }
        });

        this.posicion7 = this.vista.findViewById(R.id.posicion7);
        this.posicion7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(7);
                accionIconoPosicion(7);
            }
        });

        this.posicion8 = this.vista.findViewById(R.id.posicion8);
        this.posicion8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(8);
                accionIconoPosicion(8);
            }
        });

        this.posicion9 = this.vista.findViewById(R.id.posicion9);
        this.posicion9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(9);
                accionIconoPosicion(9);
            }
        });

        this.posicion10 = this.vista.findViewById(R.id.posicion10);
        this.posicion10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(10);
                accionIconoPosicion(10);
            }
        });

        this.posicion11 = this.vista.findViewById(R.id.posicion11);
        this.posicion11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(11);
                accionIconoPosicion(11);
            }
        });

        this.posicion12 = this.vista.findViewById(R.id.posicion12);
        this.posicion12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(12);
                accionIconoPosicion(12);
            }
        });

        this.posicion13 = this.vista.findViewById(R.id.posicion13);
        this.posicion13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(13);
                accionIconoPosicion(13);
            }
        });

        this.posicion14 = this.vista.findViewById(R.id.posicion14);
        this.posicion14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(14);
                accionIconoPosicion(14);
            }
        });

        this.posicion15 = this.vista.findViewById(R.id.posicion15);
        this.posicion15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(15);
                accionIconoPosicion(15);
            }
        });

        this.posicion16 = this.vista.findViewById(R.id.posicion16);
        this.posicion16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(16);
                accionIconoPosicion(16);
            }
        });

        this.posicion17 = this.vista.findViewById(R.id.posicion17);
        this.posicion17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(17);
                accionIconoPosicion(17);
            }
        });

        this.posicion18 = this.vista.findViewById(R.id.posicion18);
        this.posicion18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(18);
                accionIconoPosicion(18);
            }
        });

        this.posicion19 = this.vista.findViewById(R.id.posicion19);
        this.posicion19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(19);
                accionIconoPosicion(19);
            }
        });

        this.posicion20 = this.vista.findViewById(R.id.posicion20);
        this.posicion20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(20);
                accionIconoPosicion(20);
            }
        });

        this.posicion21 = this.vista.findViewById(R.id.posicion21);
        this.posicion21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(21);
                accionIconoPosicion(21);
            }
        });

        this.posicion22 = this.vista.findViewById(R.id.posicion22);
        this.posicion22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(22);
                accionIconoPosicion(22);
            }
        });

        this.posicion23 = this.vista.findViewById(R.id.posicion23);
        this.posicion23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(23);
                accionIconoPosicion(23);
            }
        });

        this.posicion24 = this.vista.findViewById(R.id.posicion24);
        this.posicion24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(24);
                accionIconoPosicion(24);
            }
        });

        this.posicion25 = this.vista.findViewById(R.id.posicion25);
        this.posicion25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(25);
                accionIconoPosicion(25);
            }
        });

        this.posicion26 = this.vista.findViewById(R.id.posicion26);
        this.posicion26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(26);
                accionIconoPosicion(26);
            }
        });

        this.posicion27 = this.vista.findViewById(R.id.posicion27);
        this.posicion27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(27);
                accionIconoPosicion(27);
            }
        });

        this.posicion28 = this.vista.findViewById(R.id.posicion28);
        this.posicion28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(28);
                accionIconoPosicion(28);
            }
        });

        this.posicion29 = this.vista.findViewById(R.id.posicion29);
        this.posicion29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(29);
                accionIconoPosicion(29);
            }
        });

        this.posicion30 = this.vista.findViewById(R.id.posicion30);
        this.posicion30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(30);
                accionIconoPosicion(30);
            }
        });

        this.posicion31 = this.vista.findViewById(R.id.posicion31);
        this.posicion31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(31);
                accionIconoPosicion(31);
            }
        });

        this.posicion32 = this.vista.findViewById(R.id.posicion32);
        this.posicion32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(32);
                accionIconoPosicion(32);
            }
        });

        this.posicion33 = this.vista.findViewById(R.id.posicion33);
        this.posicion33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(33);
                accionIconoPosicion(33);
            }
        });

        this.posicion34 = this.vista.findViewById(R.id.posicion34);
        this.posicion34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(34);
                accionIconoPosicion(34);
            }
        });

        this.posicion35 = this.vista.findViewById(R.id.posicion35);
        this.posicion35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(35);
                accionIconoPosicion(35);
            }
        });

        this.posicion36 = this.vista.findViewById(R.id.posicion36);
        this.posicion36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(36);
                accionIconoPosicion(36);
            }
        });

        this.posicion37 = this.vista.findViewById(R.id.posicion37);
        this.posicion37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(37);
                accionIconoPosicion(37);
            }
        });

        this.posicion38 = this.vista.findViewById(R.id.posicion38);
        this.posicion38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(38);
                accionIconoPosicion(38);
            }
        });

        this.posicion39 = this.vista.findViewById(R.id.posicion39);
        this.posicion39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(39);
                accionIconoPosicion(39);
            }
        });

        this.posicion40 = this.vista.findViewById(R.id.posicion40);
        this.posicion40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(40);
                accionIconoPosicion(40);
            }
        });

        this.posicion41 = this.vista.findViewById(R.id.posicion41);
        this.posicion41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(41);
                accionIconoPosicion(41);
            }
        });

        this.posicion42 = this.vista.findViewById(R.id.posicion42);
        this.posicion42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(42);
                accionIconoPosicion(42);
            }
        });

        this.posicion43 = this.vista.findViewById(R.id.posicion43);
        this.posicion43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(43);
                accionIconoPosicion(43);
            }
        });

        this.posicion44 = this.vista.findViewById(R.id.posicion44);
        this.posicion44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(44);
                accionIconoPosicion(44);
            }
        });

        this.posicion45 = this.vista.findViewById(R.id.posicion45);
        this.posicion45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(45);
                accionIconoPosicion(45);
            }
        });

        this.posicion46 = this.vista.findViewById(R.id.posicion46);
        this.posicion46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(46);
                accionIconoPosicion(46);
            }
        });

        this.posicion47 = this.vista.findViewById(R.id.posicion47);
        this.posicion47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(47);
                accionIconoPosicion(47);
            }
        });

        this.posicion48 = this.vista.findViewById(R.id.posicion48);
        this.posicion48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(48);
                accionIconoPosicion(48);
            }
        });

        this.posicion49 = this.vista.findViewById(R.id.posicion49);
        this.posicion49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(49);
                accionIconoPosicion(49);
            }
        });

        this.posicion50 = this.vista.findViewById(R.id.posicion50);
        this.posicion50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(50);
                accionIconoPosicion(50);
            }
        });

        this.posicion51 = this.vista.findViewById(R.id.posicion51);
        this.posicion51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(51);
                accionIconoPosicion(51);
            }
        });

        this.posicion52 = this.vista.findViewById(R.id.posicion52);
        this.posicion52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(52);
                accionIconoPosicion(52);
            }
        });

        this.posicion53 = this.vista.findViewById(R.id.posicion53);
        this.posicion53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(53);
                accionIconoPosicion(53);
            }
        });

        this.posicion54 = this.vista.findViewById(R.id.posicion54);
        this.posicion54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(54);
                accionIconoPosicion(54);
            }
        });

        this.posicion55 = this.vista.findViewById(R.id.posicion55);
        this.posicion55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(55);
                accionIconoPosicion(55);
            }
        });

        this.posicion56 = this.vista.findViewById(R.id.posicion56);
        this.posicion56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(56);
                accionIconoPosicion(56);
            }
        });

        this.posicion57 = this.vista.findViewById(R.id.posicion57);
        this.posicion57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(57);
                accionIconoPosicion(57);
            }
        });

        this.posicion58 = this.vista.findViewById(R.id.posicion58);
        this.posicion58.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(58);
                accionIconoPosicion(58);
            }
        });

        this.posicion59 = this.vista.findViewById(R.id.posicion59);
        this.posicion59.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(59);
                accionIconoPosicion(59);
            }
        });

        this.posicion60 = this.vista.findViewById(R.id.posicion60);
        this.posicion60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(60);
                accionIconoPosicion(60);
            }
        });

        this.posicion61 = this.vista.findViewById(R.id.posicion61);
        this.posicion61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(61);
                accionIconoPosicion(61);
            }
        });

        this.posicion62 = this.vista.findViewById(R.id.posicion62);
        this.posicion62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(62);
                accionIconoPosicion(62);
            }
        });

        this.posicion63 = this.vista.findViewById(R.id.posicion63);
        this.posicion63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(63);
                accionIconoPosicion(63);
            }
        });

        this.posicion64 = this.vista.findViewById(R.id.posicion64);
        this.posicion64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(64);
                accionIconoPosicion(64);
            }
        });

        this.posicion65 = this.vista.findViewById(R.id.posicion65);
        this.posicion65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(65);
                accionIconoPosicion(65);
            }
        });

        this.posicion66 = this.vista.findViewById(R.id.posicion66);
        this.posicion66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(66);
                accionIconoPosicion(66);
            }
        });

        this.posicion67 = this.vista.findViewById(R.id.posicion67);
        this.posicion67.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(67);
                accionIconoPosicion(67);
            }
        });

        this.posicion68 = this.vista.findViewById(R.id.posicion68);
        this.posicion68.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(68);
                accionIconoPosicion(68);
            }
        });

        this.posicion69 = this.vista.findViewById(R.id.posicion69);
        this.posicion69.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(69);
                accionIconoPosicion(69);
            }
        });

        this.posicion70 = this.vista.findViewById(R.id.posicion70);
        this.posicion70.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(70);
                accionIconoPosicion(70);
            }
        });

        this.posicion71 = this.vista.findViewById(R.id.posicion71);
        this.posicion71.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(71);
                accionIconoPosicion(71);
            }
        });

        this.posicion72 = this.vista.findViewById(R.id.posicion72);
        this.posicion72.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(72);
                accionIconoPosicion(72);
            }
        });

        this.posicion73 = this.vista.findViewById(R.id.posicion73);
        this.posicion73.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(73);
                accionIconoPosicion(73);
            }
        });

        this.posicion74 = this.vista.findViewById(R.id.posicion74);
        this.posicion74.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(74);
                accionIconoPosicion(74);
            }
        });

        this.posicion75 = this.vista.findViewById(R.id.posicion75);
        this.posicion75.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(75);
                accionIconoPosicion(75);
            }
        });

        this.posicion76 = this.vista.findViewById(R.id.posicion76);
        this.posicion76.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(76);
                accionIconoPosicion(76);
            }
        });

        this.posicion77 = this.vista.findViewById(R.id.posicion77);
        this.posicion77.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(77);
                accionIconoPosicion(77);
            }
        });

        this.posicion78 = this.vista.findViewById(R.id.posicion78);
        this.posicion78.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(78);
                accionIconoPosicion(78);
            }
        });

        this.posicion79 = this.vista.findViewById(R.id.posicion79);
        this.posicion79.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(79);
                accionIconoPosicion(79);
            }
        });

        this.posicion80 = this.vista.findViewById(R.id.posicion80);
        this.posicion80.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaSeleccionado(80);
                accionIconoPosicion(80);
            }
        });

        obtenPosiciones();
    }

    private void obtenPosiciones(){
        Call<List<PosicionEstibaDescongelado>> llamadaServicio = APIServicios.getConexion().getPosicionesDescongelado();
        llamadaServicio.enqueue(new Callback<List<PosicionEstibaDescongelado>>() {
            @Override
            public void onResponse(Call<List<PosicionEstibaDescongelado>> call, Response<List<PosicionEstibaDescongelado>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        resultadoPosiciones( response.body() );
                        terminaProcesando();
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener las posiciones");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PosicionEstibaDescongelado>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    private void confirma(final String mensaje){
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
                etiquetaMensaje.setText(mensaje);

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton2);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iniciaProcesando();
                        liberaCompleta();
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

    public void errorServicio(final String mensaje){
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
                etiquetaMensaje.setText(mensaje);

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

    public void resultadoPosiciones(List<PosicionEstibaDescongelado> posiciones){
        if( isAdded() ){
            this.listaPosiciones = posiciones;
            for( PosicionEstibaDescongelado recursoEstiba : this.listaPosiciones ){
                recursoEstiba.setEstado(Constantes.ESTADO.inicial);
                muestraIcono(recursoEstiba);
                if( recursoEstiba.getConteoNivel() > 0 ){
                    getIconoPosicion( recursoEstiba.getIdPosicion() ).setEnabled(true);
                }else{
                    getIconoPosicion( recursoEstiba.getIdPosicion() ).setEnabled(false);
                }
            }
        }
    }

    private void creaObjetosVacios(){
        if( this.listaPosiciones.isEmpty() ){
            for( int posicion = 1; posicion <= 80; posicion++ ){
                PosicionEstibaDescongelado recursoPosicion = new PosicionEstibaDescongelado();
                recursoPosicion.setIdPosicion(posicion);
                recursoPosicion.setEstado(Constantes.ESTADO.inicial);
                recursoPosicion.setCompleta(false);
                recursoPosicion.setConteoNivel(0);
                this.listaPosiciones.add(recursoPosicion);
                muestraIcono(recursoPosicion);
                if( recursoPosicion.getCompleta() ){
                    getIconoPosicion( recursoPosicion.getIdPosicion() )
                            .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
                }
            }
        }
    }

    private void muestraDetalle(){
        Fragment fragment = new Fragment_Descongelado_DetalleEstiba().newInstance( getPosicionSeleccionada() );
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private void liberaCompleta(){
        JsonObject json = new JsonObject();
        json.addProperty("idDescongeladoPosicionTina", getPosicionSeleccionada().getIdPosicion() );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().liberaPosicion(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if( response.code() == 200 ){
                        accionIconoPosicion( getPosicionSeleccionada().getIdPosicion() );
                        obtenPosiciones();
                    }else{
                        Gson gson = new Gson();
                        try {
                            String error = response.errorBody().string();
                            RespuestaServicio respuesta = gson.fromJson(error, RespuestaServicio.class);
                            terminaProcesando();
                            errorServicio( respuesta.getMensaje() );
                        } catch (IOException e) {
                            terminaProcesando();
                            errorServicio( "Error al liberar estiba" );
                        }
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

    private void accionIconoPosicion(int posicion){
        for( PosicionEstibaDescongelado posicionEstiba : this.listaPosiciones ){
            if( posicionEstiba.getIdPosicion() == posicion ){
                if( posicionEstiba.getConteoNivel() > 0 ){
                    if( posicionEstiba.getEstado() == Constantes.ESTADO.inicial ){
                        setPosicionSeleccionada(posicionEstiba);
                        //deshabilitaRecursos();
                        getIconoPosicion( posicionEstiba.getIdPosicion() )
                                .setBackground( getResources().getDrawable(R.drawable.contenedor_icono_seleccionado) );
                        posicionEstiba.setEstado(Constantes.ESTADO.seleccionado);
                        muestraIcono(posicionEstiba);
                        this.botonera.setVisibility(View.VISIBLE);
                    }else{
                        if( posicionEstiba.getEstado() == Constantes.ESTADO.seleccionado ){
                            setPosicionSeleccionada(null);
                            //habilitaRecursos();
                            posicionEstiba.setEstado(Constantes.ESTADO.inicial);
                            muestraIcono(posicionEstiba);
                            this.botonera.setVisibility(View.INVISIBLE);
                        }
                    }
                }
                break;
            }
        }
    }

    private void limpiaSeleccionado(int posicion){
        if( getPosicionSeleccionada() != null
                && getPosicionSeleccionada().getIdPosicion() != posicion ){
            accionIconoPosicion( getPosicionSeleccionada().getIdPosicion() );
        }
    }

    private void muestraIcono(PosicionEstibaDescongelado posicionEstiba){
        if( posicionEstiba.getEstado() == Constantes.ESTADO.seleccionado ){
            switch ( posicionEstiba.getConteoNivel() ){
                case 0:
                    getIconoPosicion( posicionEstiba.getIdPosicion() )
                            .setImageResource(R.drawable.ic_tina_blanca);
                    break;
                case 1:
                    getIconoPosicion( posicionEstiba.getIdPosicion() )
                            .setImageResource(R.drawable.ic_uno_blanco);
                    break;
                case 2:
                    getIconoPosicion( posicionEstiba.getIdPosicion() )
                            .setImageResource(R.drawable.ic_dos_blanco);
                    break;
                case 3:
                    getIconoPosicion( posicionEstiba.getIdPosicion() )
                            .setImageResource(R.drawable.ic_tres_blanco);
                    break;
                case 4:
                    getIconoPosicion( posicionEstiba.getIdPosicion() )
                            .setImageResource(R.drawable.ic_cuatro_blanco);
                    break;
                case 5:
                    getIconoPosicion( posicionEstiba.getIdPosicion() )
                            .setImageResource(R.drawable.ic_cinco_blanco);
                    break;
            }
        }else{
            switch ( posicionEstiba.getConteoNivel() ){
                case 0:
                    getIconoPosicion( posicionEstiba.getIdPosicion() )
                            .setImageResource(R.drawable.ic_tina_gris);
                    getIconoPosicion( posicionEstiba.getIdPosicion() )
                            .setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
                    break;
                case 1:
                    if( !posicionEstiba.getCompleta() ){
                        getIconoPosicion( posicionEstiba.getIdPosicion() )
                                .setImageResource(R.drawable.ic_uno_gris);
                        getIconoPosicion( posicionEstiba.getIdPosicion() )
                                .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_asignado ) );
                    }else{
                        if( posicionEstiba.getMinutos() <= 0 ){
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setImageResource(R.drawable.ic_uno_rojo);
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_rojo ) );
                        }else{
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setImageResource(R.drawable.ic_uno_azul);
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_azul ) );
                        }
                    }
                    break;
                case 2:
                    if( !posicionEstiba.getCompleta() ){
                        getIconoPosicion( posicionEstiba.getIdPosicion() )
                                .setImageResource(R.drawable.ic_dos_gris);
                        getIconoPosicion( posicionEstiba.getIdPosicion() )
                                .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_asignado ) );
                    }else{
                        if( posicionEstiba.getMinutos() <= 0 ){
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setImageResource(R.drawable.ic_dos_rojo);
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_rojo ) );
                        }else{
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setImageResource(R.drawable.ic_dos_azul);
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_azul ) );
                        }
                    }
                    break;
                case 3:
                    if( !posicionEstiba.getCompleta() ){
                        getIconoPosicion( posicionEstiba.getIdPosicion() )
                                .setImageResource(R.drawable.ic_tres_gris);
                        getIconoPosicion( posicionEstiba.getIdPosicion() )
                                .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_asignado ) );
                    }else{
                        if( posicionEstiba.getMinutos() <= 0 ){
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setImageResource(R.drawable.ic_tres_rojo);
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_rojo ) );
                        }else{
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setImageResource(R.drawable.ic_tres_azul);
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_azul ) );
                        }
                    }
                    break;
                case 4:
                    if( !posicionEstiba.getCompleta() ){
                        getIconoPosicion( posicionEstiba.getIdPosicion() )
                                .setImageResource(R.drawable.ic_cuatro_gris);
                        getIconoPosicion( posicionEstiba.getIdPosicion() )
                                .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_asignado ) );
                    }else{
                        if( posicionEstiba.getMinutos() <= 0 ){
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setImageResource(R.drawable.ic_cuatro_rojo);
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_rojo ) );
                        }else{
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setImageResource(R.drawable.ic_cuatro_azul);
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_azul ) );
                        }
                    }
                    break;
                case 5:
                    if( !posicionEstiba.getCompleta() ){
                        getIconoPosicion( posicionEstiba.getIdPosicion() )
                                .setImageResource(R.drawable.ic_cinco_gris);
                        getIconoPosicion( posicionEstiba.getIdPosicion() )
                                .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_asignado ) );
                    }else{
                        if( posicionEstiba.getMinutos() <= 0 ){
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setImageResource(R.drawable.ic_cinco_rojo);
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_rojo ) );
                        }else{
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setImageResource(R.drawable.ic_cinco_azul);
                            getIconoPosicion( posicionEstiba.getIdPosicion() )
                                    .setBackground( getResources().getDrawable( R.drawable.contenedor_icono_azul ) );
                        }
                    }
            }
        }
    }

    private void habilitaRecursos(){
        for(PosicionEstibaDescongelado posicion : this.listaPosiciones){
            getIconoPosicion( posicion.getIdPosicion() ).setEnabled(true);
        }
    }

    private void deshabilitaRecursos(){
        for(PosicionEstibaDescongelado posicion : this.listaPosiciones){
            getIconoPosicion( posicion.getIdPosicion() ).setEnabled(false);
        }

        if( getPosicionSeleccionada() != null ){
            getIconoPosicion( getPosicionSeleccionada().getIdPosicion() ).setEnabled(true);
        }
    }

    private ImageView getIconoPosicion(int posicion){
        switch (posicion){
            case 1: return this.posicion1;
            case 2: return this.posicion2;
            case 3: return this.posicion3;
            case 4: return this.posicion4;
            case 5: return this.posicion5;
            case 6: return this.posicion6;
            case 7: return this.posicion7;
            case 8: return this.posicion8;
            case 9: return this.posicion9;
            case 10: return this.posicion10;
            case 11: return this.posicion11;
            case 12: return this.posicion12;
            case 13: return this.posicion13;
            case 14: return this.posicion14;
            case 15: return this.posicion15;
            case 16: return this.posicion16;
            case 17: return this.posicion17;
            case 18: return this.posicion18;
            case 19: return this.posicion19;
            case 20: return this.posicion20;
            case 21: return this.posicion21;
            case 22: return this.posicion22;
            case 23: return this.posicion23;
            case 24: return this.posicion24;
            case 25: return this.posicion25;
            case 26: return this.posicion26;
            case 27: return this.posicion27;
            case 28: return this.posicion28;
            case 29: return this.posicion29;
            case 30: return this.posicion30;
            case 31: return this.posicion31;
            case 32: return this.posicion32;
            case 33: return this.posicion33;
            case 34: return this.posicion34;
            case 35: return this.posicion35;
            case 36: return this.posicion36;
            case 37: return this.posicion37;
            case 38: return this.posicion38;
            case 39: return this.posicion39;
            case 40: return this.posicion40;
            case 41: return this.posicion41;
            case 42: return this.posicion42;
            case 43: return this.posicion43;
            case 44: return this.posicion44;
            case 45: return this.posicion45;
            case 46: return this.posicion46;
            case 47: return this.posicion47;
            case 48: return this.posicion48;
            case 49: return this.posicion49;
            case 50: return this.posicion50;
            case 51: return this.posicion51;
            case 52: return this.posicion52;
            case 53: return this.posicion53;
            case 54: return this.posicion54;
            case 55: return this.posicion55;
            case 56: return this.posicion56;
            case 57: return this.posicion57;
            case 58: return this.posicion58;
            case 59: return this.posicion59;
            case 60: return this.posicion60;
            case 61: return this.posicion61;
            case 62: return this.posicion62;
            case 63: return this.posicion63;
            case 64: return this.posicion64;
            case 65: return this.posicion65;
            case 66: return this.posicion66;
            case 67: return this.posicion67;
            case 68: return this.posicion68;
            case 69: return this.posicion69;
            case 70: return this.posicion70;
            case 71: return this.posicion71;
            case 72: return this.posicion72;
            case 73: return this.posicion73;
            case 74: return this.posicion74;
            case 75: return this.posicion75;
            case 76: return this.posicion76;
            case 77: return this.posicion77;
            case 78: return this.posicion78;
            case 79: return this.posicion79;
            case 80: return this.posicion80;
        }
        return null;
    }

    public void iniciaProcesando(){
        ProgressBar barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        if( this.actualizar.isRefreshing() ){
            this.actualizar.setRefreshing(false);
        }
        ProgressBar barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        barraProgreso.setVisibility(View.GONE);
    }

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
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

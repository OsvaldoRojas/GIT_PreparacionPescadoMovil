package com.example.simulador_pescado.Descongelado;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.Utilerias.Constantes;
import com.example.simulador_pescado.clases.Metodos_Retonables;
import com.example.simulador_pescado.clases.Utilidades;
import com.example.simulador_pescado.vista.OperadorMontacargas;
import com.example.simulador_pescado.vista.Tina;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Descongelado_TiempoMuerto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Descongelado_TiempoMuerto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Descongelado_TiempoMuerto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View vista;

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
    private ImageView tina13;
    private ImageView tina14;
    private ImageView tina15;
    private ImageView tina16;
    private ImageView tina17;
    private ImageView tina18;
    private ImageView tina19;
    private ImageView tina20;
    private ImageView tina21;
    private ImageView tina22;
    private ImageView tina23;
    private ImageView tina24;
    private ImageView tina25;
    private ImageView tina26;
    private ImageView tina27;
    private ImageView tina28;
    private ImageView tina29;
    private ImageView tina30;
    private ImageView tina31;
    private ImageView tina32;
    private ImageView tina33;
    private ImageView tina34;
    private ImageView tina35;
    private ImageView tina36;
    private ImageView tina37;
    private ImageView tina38;
    private ImageView tina39;
    private ImageView tina40;
    private ImageView tina41;
    private ImageView tina42;
    private ImageView tina43;
    private ImageView tina44;
    private ImageView tina45;
    private ImageView tina46;
    private ImageView tina47;
    private ImageView tina48;
    private ImageView tina49;
    private ImageView tina50;
    private ImageView tina51;
    private ImageView tina52;
    private ImageView tina53;
    private ImageView tina54;
    private ImageView tina55;
    private ImageView tina56;
    private ImageView tina57;
    private ImageView tina58;
    private ImageView tina59;
    private ImageView tina60;
    private ImageView tina61;
    private ImageView tina62;
    private ImageView tina63;
    private ImageView tina64;
    private ImageView tina65;
    private ImageView tina66;
    private ImageView tina67;
    private ImageView tina68;
    private ImageView tina69;
    private ImageView tina70;
    private ImageView tina71;
    private ImageView tina72;
    private ImageView tina73;
    private ImageView tina74;
    private ImageView tina75;
    private ImageView tina76;
    private ImageView tina77;
    private ImageView tina78;
    private ImageView tina79;
    private ImageView tina80;
    private ImageView tina81;
    private ImageView tina82;
    private ImageView tina83;
    private ImageView tina84;
    private ImageView tina85;
    private ImageView tina86;
    private ImageView tina87;
    private ImageView tina88;
    private ImageView tina89;
    private ImageView tina90;
    private ImageView tina91;
    private ImageView tina92;
    private ImageView tina93;
    private ImageView tina94;
    private ImageView tina95;
    private ImageView tina96;

    private ImageView montacargas;

    private Tina tinaSeleccionada;
    private OperadorMontacargas montacargasSeleccionado;

    private List<Tina> listaTinas = new ArrayList<>();
    private List<OperadorMontacargas> listaMontacargas = new ArrayList<>();

    private LinearLayout botonera;
    private ScrollView vistaIconos;
    private SwipeRefreshLayout actualizar;
    private Button botonCreaOrden;
    private AlertDialog ventanaEmergente;

    private String fechaActual;

    private OnFragmentInteractionListener mListener;

    public Fragment_Descongelado_TiempoMuerto() {
        // Required empty public constructor
    }

    public Tina getTinaSeleccionada() {
        return tinaSeleccionada;
    }

    public void setTinaSeleccionada(Tina tinaSeleccionada) {
        this.tinaSeleccionada = tinaSeleccionada;
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
     * @return A new instance of fragment Fragment_Descongelado_TiempoMuerto.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Descongelado_TiempoMuerto newInstance(String param1, String param2) {
        Fragment_Descongelado_TiempoMuerto fragment = new Fragment_Descongelado_TiempoMuerto();
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
        this.vista = inflater.inflate(R.layout.fragment_fragment__descongelado__tiempo_muerto, container, false);

        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaActual = formatoFecha.format( new Date() );

        this.actualizar = this.vista.findViewById(R.id.actualizar);
        this.vistaIconos = this.vista.findViewById(R.id.vistaIconos);
        this.botonera = this.vista.findViewById(R.id.botonera);
        this.botonCreaOrden = this.vista.findViewById(R.id.botonCrearOrden);

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

        this.tina13 = this.vista.findViewById(R.id.tina13);
        this.tina13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(13);
            }
        });

        this.tina14 = this.vista.findViewById(R.id.tina14);
        this.tina14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(14);
            }
        });

        this.tina15 = this.vista.findViewById(R.id.tina15);
        this.tina15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(15);
            }
        });

        this.tina16 = this.vista.findViewById(R.id.tina16);
        this.tina16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(16);
            }
        });

        this.tina17 = this.vista.findViewById(R.id.tina17);
        this.tina17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(17);
            }
        });

        this.tina18 = this.vista.findViewById(R.id.tina18);
        this.tina18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(18);
            }
        });

        this.tina19 = this.vista.findViewById(R.id.tina19);
        this.tina19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(19);
            }
        });

        this.tina20 = this.vista.findViewById(R.id.tina20);
        this.tina20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(20);
            }
        });

        this.tina21 = this.vista.findViewById(R.id.tina21);
        this.tina21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(21);
            }
        });

        this.tina22 = this.vista.findViewById(R.id.tina22);
        this.tina22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(22);
            }
        });

        this.tina23 = this.vista.findViewById(R.id.tina23);
        this.tina23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(23);
            }
        });

        this.tina24 = this.vista.findViewById(R.id.tina24);
        this.tina24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(24);
            }
        });

        this.tina25 = this.vista.findViewById(R.id.tina25);
        this.tina25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(25);
            }
        });

        this.tina26 = this.vista.findViewById(R.id.tina26);
        this.tina26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(26);
            }
        });

        this.tina27 = this.vista.findViewById(R.id.tina27);
        this.tina27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(27);
            }
        });

        this.tina28 = this.vista.findViewById(R.id.tina28);
        this.tina28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(28);
            }
        });

        this.tina29 = this.vista.findViewById(R.id.tina29);
        this.tina29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(29);
            }
        });

        this.tina30 = this.vista.findViewById(R.id.tina30);
        this.tina30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(30);
            }
        });

        this.tina31 = this.vista.findViewById(R.id.tina31);
        this.tina31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(31);
            }
        });

        this.tina32 = this.vista.findViewById(R.id.tina32);
        this.tina32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(32);
            }
        });

        this.tina33 = this.vista.findViewById(R.id.tina33);
        this.tina33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(33);
            }
        });

        this.tina34 = this.vista.findViewById(R.id.tina34);
        this.tina34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(34);
            }
        });

        this.tina35 = this.vista.findViewById(R.id.tina35);
        this.tina35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(35);
            }
        });

        this.tina36 = this.vista.findViewById(R.id.tina36);
        this.tina36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(36);
            }
        });

        this.tina37 = this.vista.findViewById(R.id.tina37);
        this.tina37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(37);
            }
        });

        this.tina38 = this.vista.findViewById(R.id.tina38);
        this.tina38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(38);
            }
        });

        this.tina39 = this.vista.findViewById(R.id.tina39);
        this.tina39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(39);
            }
        });

        this.tina40 = this.vista.findViewById(R.id.tina40);
        this.tina40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(40);
            }
        });

        this.tina41 = this.vista.findViewById(R.id.tina41);
        this.tina41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(41);
            }
        });

        this.tina42 = this.vista.findViewById(R.id.tina42);
        this.tina42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(42);
            }
        });

        this.tina43 = this.vista.findViewById(R.id.tina43);
        this.tina43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(43);
            }
        });

        this.tina44 = this.vista.findViewById(R.id.tina44);
        this.tina44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(44);
            }
        });

        this.tina45 = this.vista.findViewById(R.id.tina45);
        this.tina45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(45);
            }
        });

        this.tina46 = this.vista.findViewById(R.id.tina46);
        this.tina46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(46);
            }
        });

        this.tina47 = this.vista.findViewById(R.id.tina47);
        this.tina47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(47);
            }
        });

        this.tina48 = this.vista.findViewById(R.id.tina48);
        this.tina48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(48);
            }
        });

        this.tina49 = this.vista.findViewById(R.id.tina49);
        this.tina49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(49);
            }
        });

        this.tina50 = this.vista.findViewById(R.id.tina50);
        this.tina50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(50);
            }
        });

        this.tina51 = this.vista.findViewById(R.id.tina51);
        this.tina51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(51);
            }
        });

        this.tina52 = this.vista.findViewById(R.id.tina52);
        this.tina52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(52);
            }
        });

        this.tina53 = this.vista.findViewById(R.id.tina53);
        this.tina53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(53);
            }
        });

        this.tina54 = this.vista.findViewById(R.id.tina54);
        this.tina54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(54);
            }
        });

        this.tina55 = this.vista.findViewById(R.id.tina55);
        this.tina55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(55);
            }
        });

        this.tina56 = this.vista.findViewById(R.id.tina56);
        this.tina56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(56);
            }
        });

        this.tina57 = this.vista.findViewById(R.id.tina57);
        this.tina57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(57);
            }
        });

        this.tina58 = this.vista.findViewById(R.id.tina58);
        this.tina58.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(58);
            }
        });

        this.tina59 = this.vista.findViewById(R.id.tina59);
        this.tina59.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(59);
            }
        });

        this.tina60 = this.vista.findViewById(R.id.tina60);
        this.tina60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(60);
            }
        });

        this.tina61 = this.vista.findViewById(R.id.tina61);
        this.tina61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(61);
            }
        });

        this.tina62 = this.vista.findViewById(R.id.tina62);
        this.tina62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(62);
            }
        });

        this.tina63 = this.vista.findViewById(R.id.tina63);
        this.tina63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(63);
            }
        });

        this.tina64 = this.vista.findViewById(R.id.tina64);
        this.tina64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(64);
            }
        });

        this.tina65 = this.vista.findViewById(R.id.tina65);
        this.tina65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(65);
            }
        });

        this.tina66 = this.vista.findViewById(R.id.tina66);
        this.tina66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(66);
            }
        });

        this.tina67 = this.vista.findViewById(R.id.tina67);
        this.tina67.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(67);
            }
        });

        this.tina68 = this.vista.findViewById(R.id.tina68);
        this.tina68.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(68);
            }
        });

        this.tina69 = this.vista.findViewById(R.id.tina69);
        this.tina69.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(69);
            }
        });

        this.tina70 = this.vista.findViewById(R.id.tina70);
        this.tina70.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(70);
            }
        });

        this.tina71 = this.vista.findViewById(R.id.tina71);
        this.tina71.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(71);
            }
        });

        this.tina72 = this.vista.findViewById(R.id.tina72);
        this.tina72.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(72);
            }
        });

        this.tina73 = this.vista.findViewById(R.id.tina73);
        this.tina73.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(73);
            }
        });

        this.tina74 = this.vista.findViewById(R.id.tina74);
        this.tina74.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(74);
            }
        });

        this.tina75 = this.vista.findViewById(R.id.tina75);
        this.tina75.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(75);
            }
        });

        this.tina76 = this.vista.findViewById(R.id.tina76);
        this.tina76.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(76);
            }
        });

        this.tina77 = this.vista.findViewById(R.id.tina77);
        this.tina77.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(77);
            }
        });

        this.tina78 = this.vista.findViewById(R.id.tina78);
        this.tina78.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(78);
            }
        });

        this.tina79 = this.vista.findViewById(R.id.tina79);
        this.tina79.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(79);
            }
        });

        this.tina80 = this.vista.findViewById(R.id.tina80);
        this.tina80.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(80);
            }
        });

        this.tina81 = this.vista.findViewById(R.id.tina81);
        this.tina81.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(81);
            }
        });

        this.tina82 = this.vista.findViewById(R.id.tina82);
        this.tina82.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(82);
            }
        });

        this.tina83 = this.vista.findViewById(R.id.tina83);
        this.tina83.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(83);
            }
        });

        this.tina84 = this.vista.findViewById(R.id.tina84);
        this.tina84.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(84);
            }
        });

        this.tina85 = this.vista.findViewById(R.id.tina85);
        this.tina85.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(85);
            }
        });

        this.tina86 = this.vista.findViewById(R.id.tina86);
        this.tina86.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(86);
            }
        });

        this.tina87 = this.vista.findViewById(R.id.tina87);
        this.tina87.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(87);
            }
        });

        this.tina88 = this.vista.findViewById(R.id.tina88);
        this.tina88.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(88);
            }
        });

        this.tina89 = this.vista.findViewById(R.id.tina89);
        this.tina89.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(89);
            }
        });

        this.tina90 = this.vista.findViewById(R.id.tina90);
        this.tina90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(90);
            }
        });

        this.tina91 = this.vista.findViewById(R.id.tina91);
        this.tina91.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(91);
            }
        });

        this.tina92 = this.vista.findViewById(R.id.tina92);
        this.tina92.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(92);
            }
        });

        this.tina93 = this.vista.findViewById(R.id.tina93);
        this.tina93.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(93);
            }
        });

        this.tina94 = this.vista.findViewById(R.id.tina94);
        this.tina94.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(94);
            }
        });

        this.tina95 = this.vista.findViewById(R.id.tina95);
        this.tina95.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(95);
            }
        });

        this.tina96 = this.vista.findViewById(R.id.tina96);
        this.tina96.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(96);
            }
        });

        this.montacargas = this.vista.findViewById(R.id.montacargas);
        this.montacargas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoMontacargas(1);
            }
        });

        creaObjetosVacios();
    }

    private void creaObjetosVacios(){
        if( this.listaTinas.isEmpty() ){
            for( int posicion = 1; posicion <= 96; posicion++ ){
                Tina recursoTina = new Tina();
                recursoTina.setIdPosicion(posicion);
                recursoTina.setEstado(Constantes.ESTADO.inicial);
                this.listaTinas.add(recursoTina);
            }
        }

        if( this.listaMontacargas.isEmpty() ){
            for( int posicion = 1; posicion <= 1; posicion++ ){
                OperadorMontacargas recursoMontacargas = new OperadorMontacargas();
                recursoMontacargas.setIdMontacargaPreseleccion(posicion);
                recursoMontacargas.setEstado(Constantes.ESTADO.inicial);
                this.listaMontacargas.add(recursoMontacargas);
            }
        }
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
                etiquetaEquipo.setText("Tina");
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
                etiquetaEquipo.setText("Montacargas");
            }
        });
        this.ventanaEmergente.show();
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
                    ajustaTamañoVista();
                    this.botonCreaOrden.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            creaOrdenTina();
                        }
                    });
                    this.botonera.setVisibility(View.VISIBLE);
                }else{
                    if( tina.getEstado() == Constantes.ESTADO.seleccionado ){
                        setTinaSeleccionada(null);
                        habilitaRecursos();
                        getIconoTina( tina.getIdPosicion() )
                                .setImageResource(R.drawable.ic_tina2);
                        getIconoTina( tina.getIdPosicion() )
                                .setBackground( getResources().getDrawable(R.drawable.contenedor_icono) );
                        tina.setEstado(Constantes.ESTADO.inicial);
                        this.botonCreaOrden.setOnClickListener(null);
                        this.botonera.setVisibility(View.GONE);
                        ajustaTamañoVista();
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
                    ajustaTamañoVista();
                    this.botonCreaOrden.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            creaOrdenMontacargas();
                        }
                    });
                    this.botonera.setVisibility(View.VISIBLE);
                }else{
                    if( montacargas.getEstado() == Constantes.ESTADO.seleccionado ){
                        setMontacargasSeleccionado(null);
                        habilitaRecursos();
                        getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() )
                                .setImageResource(R.drawable.ic_montacargas2);
                        getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() )
                                .setBackground( getResources().getDrawable(R.drawable.contenedor_icono) );
                        montacargas.setEstado(Constantes.ESTADO.inicial);
                        this.botonCreaOrden.setOnClickListener(null);
                        this.botonera.setVisibility(View.GONE);
                        ajustaTamañoVista();
                    }
                }
                break;
            }
        }
    }

    private void ajustaTamañoVista(){
        ViewGroup.LayoutParams botonera = this.botonera.getLayoutParams();
        ViewGroup.LayoutParams vista = this.actualizar.getLayoutParams();

        if( getTinaSeleccionada() != null ){
            vista.height = vista.height - (botonera.height*5);
            if( getTinaSeleccionada().getIdPosicion() <= 24 ){
                this.vistaIconos.post(new Runnable() {
                    public void run() {
                        vistaIconos.fullScroll(vistaIconos.FOCUS_UP);
                    }
                });
            }else{
                this.vistaIconos.post(new Runnable() {
                    public void run() {
                        vistaIconos.fullScroll(vistaIconos.FOCUS_DOWN);
                    }
                });
            }
        }else{
            vista.height = vista.height + (botonera.height*5);
        }

        this.actualizar.requestLayout();
        this.actualizar.setLayoutParams(vista);
        return;
    }

    private void habilitaRecursos(){
        for(Tina tina : this.listaTinas){
            getIconoTina( tina.getIdPosicion() ).setEnabled(true);
        }

        for(OperadorMontacargas montacargas : this.listaMontacargas){
            getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() ).setEnabled(true);
        }
    }

    private void deshabilitaRecursos(){
        for(Tina tina : this.listaTinas){
            getIconoTina( tina.getIdPosicion() ).setEnabled(false);
        }

        for(OperadorMontacargas montacargas : this.listaMontacargas){
            getIconoMontacargas( montacargas.getIdMontacargaPreseleccion() ).setEnabled(false);
        }

        if( getTinaSeleccionada() != null ){
            getIconoTina( getTinaSeleccionada().getIdPosicion() ).setEnabled(true);
        }else{
            if( getMontacargasSeleccionado() != null ){
                getIconoMontacargas( getMontacargasSeleccionado().getIdMontacargaPreseleccion() ).setEnabled(true);
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
            case 13: return this.tina13;
            case 14: return this.tina14;
            case 15: return this.tina15;
            case 16: return this.tina16;
            case 17: return this.tina17;
            case 18: return this.tina18;
            case 19: return this.tina19;
            case 20: return this.tina20;
            case 21: return this.tina21;
            case 22: return this.tina22;
            case 23: return this.tina23;
            case 24: return this.tina24;
            case 25: return this.tina25;
            case 26: return this.tina26;
            case 27: return this.tina27;
            case 28: return this.tina28;
            case 29: return this.tina29;
            case 30: return this.tina30;
            case 31: return this.tina31;
            case 32: return this.tina32;
            case 33: return this.tina33;
            case 34: return this.tina34;
            case 35: return this.tina35;
            case 36: return this.tina36;
            case 37: return this.tina37;
            case 38: return this.tina38;
            case 39: return this.tina39;
            case 40: return this.tina40;
            case 41: return this.tina41;
            case 42: return this.tina42;
            case 43: return this.tina43;
            case 44: return this.tina44;
            case 45: return this.tina45;
            case 46: return this.tina46;
            case 47: return this.tina47;
            case 48: return this.tina48;
            case 49: return this.tina49;
            case 50: return this.tina50;
            case 51: return this.tina51;
            case 52: return this.tina52;
            case 53: return this.tina53;
            case 54: return this.tina54;
            case 55: return this.tina55;
            case 56: return this.tina56;
            case 57: return this.tina57;
            case 58: return this.tina58;
            case 59: return this.tina59;
            case 60: return this.tina60;
            case 61: return this.tina61;
            case 62: return this.tina62;
            case 63: return this.tina63;
            case 64: return this.tina64;
            case 65: return this.tina65;
            case 66: return this.tina66;
            case 67: return this.tina67;
            case 68: return this.tina68;
            case 69: return this.tina69;
            case 70: return this.tina70;
            case 71: return this.tina71;
            case 72: return this.tina72;
            case 73: return this.tina73;
            case 74: return this.tina74;
            case 75: return this.tina75;
            case 76: return this.tina76;
            case 77: return this.tina77;
            case 78: return this.tina78;
            case 79: return this.tina79;
            case 80: return this.tina80;
            case 81: return this.tina81;
            case 82: return this.tina82;
            case 83: return this.tina83;
            case 84: return this.tina84;
            case 85: return this.tina85;
            case 86: return this.tina86;
            case 87: return this.tina87;
            case 88: return this.tina88;
            case 89: return this.tina89;
            case 90: return this.tina90;
            case 91: return this.tina91;
            case 92: return this.tina92;
            case 93: return this.tina93;
            case 94: return this.tina94;
            case 95: return this.tina95;
            case 96: return this.tina96;
        }
        return null;
    }

    private ImageView getIconoMontacargas(int posicion){
        switch (posicion){
            case 1: return this.montacargas;
        }
        return null;
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

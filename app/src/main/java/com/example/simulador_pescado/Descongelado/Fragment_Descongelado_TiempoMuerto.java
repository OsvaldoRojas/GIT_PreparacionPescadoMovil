package com.example.simulador_pescado.Descongelado;

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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.clases.Metodos_Retonables;
import com.example.simulador_pescado.clases.Utilidades;

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
    private Button estiba1,estiba2,estiba3,estiba4,estiba5,estiba6,estiba7,estiba8,estiba9
            ,estiba10 ,estiba11
            ,estiba12,estiba13
            ,estiba14,estiba15
            ,estiba16,estiba17,
            estiba18,estiba19,estiba20
            ,estiba21,estiba22
            ,estiba23,estiba24
            ,estiba25,estiba26
            ,estiba27,estiba28
            ,estiba29,estiba30
            ,estiba31,estiba32
            ,estiba33,estiba34
            ,estiba35,estiba36
            ,estiba37,estiba38
            ,estiba39,estiba40
            ,estiba41,estiba42
            ,estiba43,estiba44
            ,estiba45,estiba46
            ,estiba47,estiba48
            ,estiba49,estiba50
            ,estiba51,estiba52
            ,estiba53,estiba54
            ,estiba55,estiba56
            ,estiba57,estiba58
            ,estiba59,estiba60
            ,estiba61,estiba62
            ,estiba63,estiba64
            ,estiba65,estiba66
            ,estiba67,estiba68
            ,estiba69,estiba70
            ,estiba71,estiba72
            ,estiba73,estiba74
            ,estiba75,estiba76
            ,estiba77,estiba78
            ,estiba79,estiba80
            ,estiba81,estiba82
            ,estiba83,estiba84
            ,estiba85,estiba86
            ,estiba87,estiba88
            ,estiba89,estiba90
            ,estiba91,estiba92
            ,estiba93,estiba94
            ,estiba95,estiba96,btn_detalle;
    private Button carrito1;
    private OnFragmentInteractionListener mListener;

    public Fragment_Descongelado_TiempoMuerto() {
        // Required empty public constructor
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
        vista=inflater.inflate(R.layout.fragment_fragment__descongelado__tiempo_muerto, container, false);
        carrito1= vista.findViewById(R.id.carrito1);
        btn_detalle= vista.findViewById(R.id.orden_mante);
        estiba1=vista.findViewById(R.id.estiba1);
        estiba2=vista.findViewById(R.id.estiba2);
        estiba3=vista.findViewById(R.id.estiba3);
        estiba4=vista.findViewById(R.id.estiba4);
        estiba5=vista.findViewById(R.id.estiba5);
        estiba6=vista.findViewById(R.id.estiba6);
        estiba7=vista.findViewById(R.id.estiba7);
        estiba8=vista.findViewById(R.id.estiba8);
        estiba9=vista.findViewById(R.id.estiba9);
        estiba10=vista.findViewById(R.id.estiba10);
        estiba11=vista.findViewById(R.id.estiba11);
        estiba12=vista.findViewById(R.id.estiba12);
        estiba13=vista.findViewById(R.id.estiba13);
        estiba14=vista.findViewById(R.id.estiba14);
        estiba15=vista.findViewById(R.id.estiba15);
        estiba16=vista.findViewById(R.id.estiba16);
        estiba17=vista.findViewById(R.id.estiba17);
        estiba18=vista.findViewById(R.id.estiba18);
        estiba19=vista.findViewById(R.id.estiba19);
        estiba20=vista.findViewById(R.id.estiba20);
        estiba21=vista.findViewById(R.id.estiba21);
        estiba22=vista.findViewById(R.id.estiba22);
        estiba23=vista.findViewById(R.id.estiba23);
        estiba24=vista.findViewById(R.id.estiba24);
        estiba25=vista.findViewById(R.id.estiba25);
        estiba26=vista.findViewById(R.id.estiba26);
        estiba27=vista.findViewById(R.id.estiba27);
        estiba28=vista.findViewById(R.id.estiba28);
        estiba29=vista.findViewById(R.id.estiba29);
        estiba30=vista.findViewById(R.id.estiba30);
        estiba31=vista.findViewById(R.id.estiba31);
        estiba32=vista.findViewById(R.id.estiba32);
        estiba33=vista.findViewById(R.id.estiba33);
        estiba34=vista.findViewById(R.id.estiba34);
        estiba35=vista.findViewById(R.id.estiba35);
        estiba36=vista.findViewById(R.id.estiba36);
        estiba37=vista.findViewById(R.id.estiba37);
        estiba38=vista.findViewById(R.id.estiba38);
        estiba39=vista.findViewById(R.id.estiba39);
        estiba40=vista.findViewById(R.id.estiba40);
        estiba41=vista.findViewById(R.id.estiba41);
        estiba42=vista.findViewById(R.id.estiba42);
        estiba43=vista.findViewById(R.id.estiba43);
        estiba44=vista.findViewById(R.id.estiba44);
        estiba45=vista.findViewById(R.id.estiba45);
        estiba46=vista.findViewById(R.id.estiba46);
        estiba47=vista.findViewById(R.id.estiba47);
        estiba48=vista.findViewById(R.id.estiba48);
        estiba49=vista.findViewById(R.id.estiba49);
        estiba50=vista.findViewById(R.id.estiba50);
        estiba51=vista.findViewById(R.id.estiba51);
        estiba52=vista.findViewById(R.id.estiba52);
        estiba53=vista.findViewById(R.id.estiba53);
        estiba54=vista.findViewById(R.id.estiba54);
        estiba55=vista.findViewById(R.id.estiba55);
        estiba56=vista.findViewById(R.id.estiba56);
        estiba57=vista.findViewById(R.id.estiba57);
        estiba58=vista.findViewById(R.id.estiba58);
        estiba59=vista.findViewById(R.id.estiba59);
        estiba60=vista.findViewById(R.id.estiba60);
        estiba61=vista.findViewById(R.id.estiba61);
        estiba62=vista.findViewById(R.id.estiba62);
        estiba63=vista.findViewById(R.id.estiba63);
        estiba64=vista.findViewById(R.id.estiba64);
        estiba65=vista.findViewById(R.id.estiba65);
        estiba66=vista.findViewById(R.id.estiba66);
        estiba67=vista.findViewById(R.id.estiba67);
        estiba68=vista.findViewById(R.id.estiba68);
        estiba69=vista.findViewById(R.id.estiba69);
        estiba70=vista.findViewById(R.id.estiba70);
        estiba71=vista.findViewById(R.id.estiba71);
        estiba72=vista.findViewById(R.id.estiba72);
        estiba73=vista.findViewById(R.id.estiba73);
        estiba74=vista.findViewById(R.id.estiba74);
        estiba75=vista.findViewById(R.id.estiba75);
        estiba76=vista.findViewById(R.id.estiba76);
        estiba77=vista.findViewById(R.id.estiba77);
        estiba78=vista.findViewById(R.id.estiba78);
        estiba79=vista.findViewById(R.id.estiba79);
        estiba79=vista.findViewById(R.id.estiba79);
        estiba80=vista.findViewById(R.id.estiba80);
        estiba81=vista.findViewById(R.id.estiba81);
        estiba82=vista.findViewById(R.id.estiba82);
        estiba83=vista.findViewById(R.id.estiba83);
        estiba84=vista.findViewById(R.id.estiba84);
        estiba85=vista.findViewById(R.id.estiba85);
        estiba86=vista.findViewById(R.id.estiba86);
        estiba87=vista.findViewById(R.id.estiba87);
        estiba88=vista.findViewById(R.id.estiba88);
        estiba89=vista.findViewById(R.id.estiba89);
        estiba90=vista.findViewById(R.id.estiba90);
        estiba91=vista.findViewById(R.id.estiba91);
        estiba92=vista.findViewById(R.id.estiba92);
        estiba93=vista.findViewById(R.id.estiba93);
        estiba94=vista.findViewById(R.id.estiba94);
        estiba95=vista.findViewById(R.id.estiba95);
        estiba96=vista.findViewById(R.id.estiba96);

        //se inicia el onclick
        btn_detalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crear_dialog_detalle(view);



            }
        });
        estiba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 1",estiba1,view);
            }
        });

        estiba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 2",estiba2,view);
            }
        });

        estiba3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 3",estiba3,view);
            }
        });
        estiba4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 4",estiba4,view);
            }
        });
        estiba5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 5",estiba5,view);
            }
        });
        estiba6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 6",estiba6,view);
            }
        });estiba7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 7",estiba7,view);
            }
        });
        estiba8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 8",estiba8,view);
            }
        });
        estiba9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 9",estiba9,view);
            }
        });
        estiba10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 10",estiba10,view);
            }
        });
        estiba11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 11",estiba11,view);
            }
        });
        estiba12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 12",estiba12,view);
            }
        });
        estiba13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 13",estiba13,view);
            }
        });
        estiba14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 14",estiba14,view);
            }
        });
        estiba15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 15",estiba15,view);
            }
        });
        estiba16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 16",estiba16,view);
            }
        });
        estiba17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 17",estiba17,view);
            }
        });
        estiba18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 18",estiba18,view);
            }
        });
        estiba19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 19",estiba19,view);
            }
        });
        estiba20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 20",estiba20,view);
            }
        });
        estiba21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 21",estiba21,view);
            }
        });
        estiba22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 22",estiba22,view);
            }
        });
        estiba23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 23",estiba23,view);
            }
        });
        estiba24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 24",estiba24,view);
            }
        });
        estiba25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 25",estiba25,view);
            }
        });
        estiba26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 26",estiba26,view);
            }
        });
        estiba27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 27",estiba27,view);
            }
        });
        estiba28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 28",estiba28,view);
            }
        });
        estiba29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 29",estiba29,view);
            }
        });
        estiba30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 30",estiba30,view);
            }
        });
        estiba31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 31",estiba31,view);
            }
        });
        estiba32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 32",estiba32,view);
            }
        });
        estiba33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 33",estiba33,view);
            }
        });
        estiba34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 34",estiba34,view);
            }
        });
        estiba35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 35",estiba35,view);
            }
        });
        estiba36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 36",estiba36,view);
            }
        });
        estiba37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 37",estiba37,view);
            }
        });
        estiba38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 38",estiba38,view);
            }
        });
        estiba39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 39",estiba39,view);
            }
        });
        estiba40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 40",estiba40,view);
            }
        });
        estiba41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 41",estiba41,view);
            }
        });
        estiba42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 42",estiba42,view);
            }
        });
        estiba43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 43",estiba43,view);
            }
        });
        estiba44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 44",estiba44,view);
            }
        });
        estiba45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 45",estiba45,view);
            }
        });
        estiba46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 46",estiba46,view);
            }
        });
        estiba47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 47",estiba47,view);
            }
        });
        estiba48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 48",estiba48,view);
            }
        });
        estiba49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 41",estiba49,view);
            }
        });
        estiba50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 50",estiba50,view);
            }
        });
        estiba51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 51",estiba51,view);
            }
        });
        estiba52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 52",estiba52,view);
            }
        });
        estiba53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 53",estiba53,view);
            }
        });
        estiba54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 54",estiba54,view);
            }
        });
        estiba55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 55",estiba55,view);
            }
        });
        estiba56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 56",estiba56,view);
            }
        });
        estiba57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 57",estiba57,view);
            }
        });
        estiba58.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 58",estiba58,view);
            }
        });
        estiba59.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 59",estiba59,view);
            }
        });
        estiba60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 60",estiba60,view);
            }
        });
        estiba61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 61",estiba61,view);
            }
        });
        estiba62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 62",estiba62,view);
            }
        });
        estiba63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 63",estiba63,view);
            }
        });
        estiba64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 64",estiba64,view);
            }
        });
        estiba65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 65",estiba65,view);
            }
        });
        estiba66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 66",estiba66,view);
            }
        });
        estiba67.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 67",estiba67,view);
            }
        });
        estiba68.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 68",estiba68,view);
            }
        });
        estiba69.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 69",estiba69,view);
            }
        });
        estiba70.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 70",estiba70,view);
            }
        });
        estiba71.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 71",estiba71,view);
            }
        });
        estiba72.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 72",estiba72,view);
            }
        });
        estiba73.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 73",estiba73,view);
            }
        });
        estiba74.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 74",estiba74,view);
            }
        });
        estiba75.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 75",estiba75,view);
            }
        });
        estiba76.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 76",estiba76,view);
            }
        });
        estiba77.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 77",estiba77,view);
            }
        });
        estiba78.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 78",estiba78,view);
            }
        });
        estiba79.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 79",estiba79,view);
            }
        });
        estiba80.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 70",estiba70,view);
            }
        });
        estiba81.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 81",estiba81,view);
            }
        });
        estiba82.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 82",estiba82,view);
            }
        });
        estiba83.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 83",estiba83,view);
            }
        });
        estiba84.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 84",estiba84,view);
            }
        });
        estiba85.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 85",estiba85,view);
            }
        });
        estiba86.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 86",estiba86,view);
            }
        });
        estiba87.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 87",estiba87,view);
            }
        });
        estiba88.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 88",estiba88,view);
            }
        });
        estiba89.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 89",estiba89,view);
            }
        });
        estiba90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 90",estiba90,view);
            }
        });
        estiba91.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 91",estiba91,view);
            }
        });
        estiba92.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 92",estiba92,view);
            }
        });
        estiba93.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 93",estiba93,view);
            }
        });
        estiba94.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 94",estiba94,view);
            }
        });
        estiba95.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 95",estiba95,view);
            }
        });
        estiba96.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecion_tina("estiba 96",estiba96,view);
            }
        });

        return vista;
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
    private void selecion_tina(String boton,Button bton,View view){


        Button aux=null;
        aux=btn_selecionado();
        Metodos_Retonables asignacion = new Metodos_Retonables();

        Boolean asignada_atual=asignacion.Tina_asignada(boton);

        //solo si no se selecionado ninguna tina la cula este seleciona entra

        Toast.makeText(view.getContext(),"si llego"+boton,Toast.LENGTH_LONG).show();

        if (Utilidades.Boton_presionado_descongelado_tiempo_muerto.equals("ninguno")) {

            bton.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.selecion));
            Utilidades.Boton_presionado_descongelado_tiempo_muerto = boton;
            boton_inferior();

        } else {
            if (Utilidades.Boton_presionado_descongelado_tiempo_muerto.equals(boton)) {
                bton.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.no_selecion));
                Utilidades.Boton_presionado_descongelado_tiempo_muerto = "ninguno";
                boton_inferior();

            } else {
                bton.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.selecion));
                aux.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.no_selecion));
                Utilidades.Boton_presionado_descongelado_tiempo_muerto = boton;
                boton_inferior();

            }
        }
    }
    private void crear_dialog_detalle(View view) {
        {

            Metodos_Retonables obtner_fecha= new Metodos_Retonables();
            AlertDialog.Builder alerta= new AlertDialog.Builder(view.getContext());
            View vista= getActivity().getLayoutInflater().inflate(R.layout.dialog_orden_mantenimiento,null);
            final TextView dialogfecha= vista.findViewById(R.id.fecha);


            String fechaActual= obtner_fecha.Obtener_fecha();
            dialogfecha.setText(fechaActual);


            alerta.setCancelable(true).setPositiveButton("Aceptar",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //no hace nada basicamente
                        }
                    });
            alerta.setView(vista);
            alerta.show();
            //return alerta.create();

        }
    }
    private Button btn_selecionado(){
        Button aux=null;

        switch (Utilidades.Boton_presionado_descongelado_tiempo_muerto){
            case "ninguno":
                break;
            case "estiba 1":
                aux=estiba1;

                break;
            case "estiba 2":
                aux=estiba2;

                break;
            case "estiba 3":
                aux=estiba3;

                break;
            case "estiba 4":
                aux=estiba4;

                break;
            case "estiba 5":
                aux=estiba5;

                break;
            case "estiba 6":
                aux=estiba6;

                break;
            case "estiba 7":
                aux=estiba7;

                break;
            case "estiba 8":
                aux=estiba8;

                break;
            case "estiba 9":
                aux=estiba9;

                break;
            case "estiba 10":
                aux=estiba10;
                break;

            case "estiba 11":
                aux=estiba11;
                break;


            case "estiba 12":
                aux=estiba12;

                break;
            case "estiba 13":
                aux=estiba13;

                break;
            case "estiba 14":
                aux=estiba14;

                break;
            case "estiba 15":
                aux=estiba15;

                break;
            case "estiba 16":
                aux=estiba16;

                break;
            case "estiba 17":
                aux=estiba17;

                break;
            case "estiba 18":
                aux=estiba18;

                break;
            case "estiba 19":
                aux=estiba19;

                break;
            case "estiba 20":
                aux=estiba20;

                break;
            case "estiba 21":
                aux=estiba21;

                break;
            case "estiba 22":
                aux=estiba22;

                break;
            case "estiba 23":
                aux=estiba23;

                break;
            case "estiba 24":
                aux=estiba24;

                break;
            case "estiba 25":
                aux=estiba25;

                break;
            case "estiba 26":
                aux=estiba26;

                break;
            case "estiba 27":
                aux=estiba27;

                break;
            case "estiba 28":
                aux=estiba28;

                break;
            case "estiba 29":
                aux=estiba29;

                break;
            case "estiba 30":
                aux=estiba30;

                break;
            case "estiba 31":
                aux=estiba31;

                break;
            case "estiba 32":
                aux=estiba32;

                break;
            case "estiba 33":
                aux=estiba33;

                break;
            case "estiba 34":
                aux=estiba34;

                break;
            case "estiba 35":
                aux=estiba35;

                break;
            case "estiba 36":
                aux=estiba36;

                break;
            case "estiba 37":
                aux=estiba37;

                break;
            case "estiba 38":
                aux=estiba38;

                break;
            case "estiba 39":
                aux=estiba39;

                break;
            case "estiba 40":
                aux=estiba40;

                break;
            case "estiba 41":
                aux=estiba41;

                break;
            case "estiba 42":
                aux=estiba42;

                break;
            case "estiba 43":
                aux=estiba43;

                break;
            case "estiba 44":
                aux=estiba44;

                break;
            case "estiba 45":
                aux=estiba45;

                break;
            case "estiba 46":
                aux=estiba46;

                break;
            case "estiba 47":
                aux=estiba47;

                break;
            case "estiba 48":
                aux=estiba48;

                break;
            case "estiba 49":
                aux=estiba49;

                break;

            case "estiba 50":
                aux=estiba50;

                break;
            case "estiba 51":
                aux=estiba51;

                break;
            case "estiba 52":
                aux=estiba52;

                break;
            case "estiba 53":
                aux=estiba53;

                break;
            case "estiba 54":
                aux=estiba54;

                break;
            case "estiba 55":
                aux=estiba55;

                break;
            case "estiba 56":
                aux=estiba56;

                break;
            case "estiba 57":
                aux=estiba57;

                break;
            case "estiba 58":
                aux=estiba58;

                break;
            case "estiba 59":
                aux=estiba59;

                break;
            case "estiba 60":
                aux=estiba60;

                break;
            case "estiba 61":
                aux=estiba61;

                break;
            case "estiba 62":
                aux=estiba62;

                break;
            case "estiba 63":
                aux=estiba63;

                break;
            case "estiba 64":
                aux=estiba64;

                break;
            case "estiba 65":
                aux=estiba65;

                break;
            case "estiba 66":
                aux=estiba66;

                break;
            case "estiba 67":
                aux=estiba67;

                break;
            case "estiba 68":
                aux=estiba68;

                break;
            case "estiba 69":
                aux=estiba69;

                break;
            case "estiba 70":
                aux=estiba70;

                break;
            case "estiba 71":
                aux=estiba71;

                break;
            case "estiba 72":
                aux=estiba72;

                break;
            case "estiba 73":
                aux=estiba73;

                break;
            case "estiba 74":
                aux=estiba74;

                break;
            case "estiba 75":
                aux=estiba75;

                break;
            case "estiba 76":
                aux=estiba76;

                break;
            case "estiba 77":
                aux=estiba77;

                break;
            case "estiba 78":
                aux=estiba78;

                break;
            case "estiba 79":
                aux=estiba79;

                break;
            case "estiba 80":
                aux=estiba80;

                break;
            case "estiba 81":
                aux=estiba81;

                break;
            case "estiba 82":
                aux=estiba82;

                break;
            case "estiba 83":
                aux=estiba83;

                break;
            case "estiba 84":
                aux=estiba84;

                break;
            case "estiba 85":
                aux=estiba85;

                break;
            case "estiba 86":
                aux=estiba86;

                break;
            case "estiba 87":
                aux=estiba87;

                break;
            case "estiba 88":
                aux=estiba88;

                break;
            case "estiba 89":
                aux=estiba89;

                break;
            case "estiba 90":
                aux=estiba90;

                break;
            case "estiba 91":
                aux=estiba91;

                break;
            case "estiba 92":
                aux=estiba92;

                break;
            case "estiba 93":
                aux=estiba93;

                break;
            case "estiba 94":
                aux=estiba94;

                break;
            case "estiba 95":
                aux=estiba95;

                break;
            case "estiba 96":
                aux=estiba96;

                break;
        }
        return aux;
    }

    private void boton_inferior(){
        if (!Utilidades.Boton_presionado_descongelado_tiempo_muerto.equals("ninguno")){

            btn_detalle.setEnabled(true);
        }else{
            btn_detalle.setEnabled(false);
        }
    }
}

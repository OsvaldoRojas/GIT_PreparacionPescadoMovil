package com.example.simulador_pescado.Preselecion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
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
 * {@link Fragment_Preselecion_Tinas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Preselecion_Tinas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Preselecion_Tinas extends Fragment  implements View.OnTouchListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    private Button t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
    private Button emple1,emple2,emple3,emple4,emple5,emple6,emple7,emple8,emple9,emple10;
    private Button bas1,bas2,bas3,bas4,bas5,bas6,bas7,bas8,bas9,bas10;
    private Button monta1,monta2,monta3,monta4;
    private Button para1,para2;
    TextView panta_humo;


    private OnFragmentInteractionListener mListener;

    public Fragment_Preselecion_Tinas() {
        // Required empty public constructor
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
        //asignacion de variables tinas
        t1= vista.findViewById(R.id.tina1);
        t2= vista.findViewById(R.id.tina2);
        t3= vista.findViewById(R.id.tina3);
        t4= vista.findViewById(R.id.tina4);
        t5= vista.findViewById(R.id.tina5);
        t6= vista.findViewById(R.id.tina6);
        t7= vista.findViewById(R.id.tina7);
        t8= vista.findViewById(R.id.tina8);
        t9= vista.findViewById(R.id.tina9);
        t10= vista.findViewById(R.id.tina10);
        t11= vista.findViewById(R.id.tina11);
        t12= vista.findViewById(R.id.tina12);

        //asignacion variables empleados

        emple1=vista.findViewById(R.id.emple1);
        emple2=vista.findViewById(R.id.emple2);
        emple3=vista.findViewById(R.id.emple3);
        emple4=vista.findViewById(R.id.emple4);
        emple5=vista.findViewById(R.id.emple5);
        emple6=vista.findViewById(R.id.emple6);
        emple7=vista.findViewById(R.id.emple7);
        emple8=vista.findViewById(R.id.emple8);
        emple9=vista.findViewById(R.id.emple9);
        emple10=vista.findViewById(R.id.emple10);
        //asignacion variables montacargas

        monta1=vista.findViewById(R.id.monta1);
        monta2=vista.findViewById(R.id.monta2);
        monta3=vista.findViewById(R.id.monta3);
        monta4=vista.findViewById(R.id.monta4);

        //asignacion variables de botones
        para1=vista.findViewById(R.id.btnPrametro1);
        para2=vista.findViewById(R.id.btnPrametro2);
        panta_humo=vista.findViewById(R.id.btnPantalla_humo);
        //asignacion de de metodo onclick en botones de tinas
        t1.setOnClickListener(Fragment_Preselecion_Tinas.this);
        t2.setOnClickListener(Fragment_Preselecion_Tinas.this);
        t3.setOnClickListener(Fragment_Preselecion_Tinas.this);
        t4.setOnClickListener(Fragment_Preselecion_Tinas.this);
        t5.setOnClickListener(Fragment_Preselecion_Tinas.this);
        t6.setOnClickListener(Fragment_Preselecion_Tinas.this);
        t7.setOnClickListener(Fragment_Preselecion_Tinas.this);
        t8.setOnClickListener(Fragment_Preselecion_Tinas.this);
        t9.setOnClickListener(Fragment_Preselecion_Tinas.this);
        t10.setOnClickListener(Fragment_Preselecion_Tinas.this);
        t11.setOnClickListener(Fragment_Preselecion_Tinas.this);
        t12.setOnClickListener(Fragment_Preselecion_Tinas.this);
        //asignacion de de metodo onclick en botones de tinas
        emple1.setOnClickListener(Fragment_Preselecion_Tinas.this);
        emple2.setOnClickListener(Fragment_Preselecion_Tinas.this);
        emple3.setOnClickListener(Fragment_Preselecion_Tinas.this);
        emple4.setOnClickListener(Fragment_Preselecion_Tinas.this);
        emple5.setOnClickListener(Fragment_Preselecion_Tinas.this);
        emple6.setOnClickListener(Fragment_Preselecion_Tinas.this);
        emple7.setOnClickListener(Fragment_Preselecion_Tinas.this);
        emple8.setOnClickListener(Fragment_Preselecion_Tinas.this);
        emple9.setOnClickListener(Fragment_Preselecion_Tinas.this);
        emple10.setOnClickListener(Fragment_Preselecion_Tinas.this);
        //asigancion del metodo onclick
        monta1.setOnClickListener(Fragment_Preselecion_Tinas.this);
        monta2.setOnClickListener(Fragment_Preselecion_Tinas.this);
        monta3.setOnClickListener(Fragment_Preselecion_Tinas.this);
        monta4.setOnClickListener(Fragment_Preselecion_Tinas.this);

        //reiniciar variable de boton precionado
        Utilidades.boton_presionado_Preselecion_plan="ninguno";

        para1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        AlertDialog.Builder alerta= new AlertDialog.Builder(getContext());
        alerta.setCancelable(true).setMessage("Desea liberar esta tina").setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Button aux=btn_selecionado();
                Metodos_Retonables retonables= new Metodos_Retonables();
                retonables.liberar(Utilidades.boton_presionado_Preselecion_plan);
                aux.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.no_selecion));
                Utilidades.boton_presionado_Preselecion_plan="ninguno";
                btn_pantalla_humo();
                botones_inferiores(2);

            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        alerta.show();




            }
        });

        para2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String parametro =para2.getText().toString();
                if (parametro.equals("asignar")){
                    //Toast.makeText(getContext(), "para 1", Toast.LENGTH_SHORT).show();
                    if (Utilidades.tipo_de_boton.equals("tina")) {
                        crear_dialog(view);
                    }else{
                        if (Utilidades.tipo_de_boton.equals("empleado")) {
                            crear_dialog_empleado(view);
                        }else{
                            crear_dialog_montacargas(view);
                        }
                    }


                } switch (parametro){

                    case "tina":
                        crear_dialog(view);
                        break;
                    case "monta":
                        break;
                    case"empleado":
                }
                if (parametro.equals("mezclar")){
                    Toast.makeText(getContext(), "a mezclar", Toast.LENGTH_SHORT).show();
                }

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
    public void revelar(){

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return false;
    }
/**------------------------------------------------------------------ Metodos propios -----------------------------------------------*/
    @Override
    public void onClick(View view) {


        panta_humo.setCursorVisible(false);
       panta_humo.setVisibility(View.GONE);
       para1.setText("liberar");
       para1.setEnabled(false);
       para2.setText("asignar");

        switch (view.getId()){
            case R.id.tina1:
                selecion_tina("tina 1",t1,"tina");
                Utilidades.posision_tina="F1";

                break;
            case R.id.tina2:
                selecion_tina("tina 2",t2,"tina");
                Utilidades.posision_tina="E1";
                break;

            case R.id.tina3:
                selecion_tina("tina 3",t3,"tina");
                Utilidades.posision_tina="D1";
                break;
            case R.id.tina4:
                selecion_tina("tina 4",t4,"tina");
                Utilidades.posision_tina="C1";
                break;
            case R.id.tina5:
                selecion_tina("tina 5",t5,"tina");
                Utilidades.posision_tina="B1";
                break;
            case R.id.tina6:
                selecion_tina("tina 6",t6,"tina");
                Utilidades.posision_tina="A1";
                break;
            case R.id.tina7:
                selecion_tina("tina 7",t7,"tina");
                Utilidades.posision_tina="F2";
                break;
            case R.id.tina8:
                selecion_tina("tina 8",t8,"tina");
                Utilidades.posision_tina="E2";
                break;
            case R.id.tina9:
                selecion_tina("tina 9",t9,"tina");
                Utilidades.posision_tina="D2";
                break;
            case R.id.tina10:
                selecion_tina("tina 10",t10,"tina");
                Utilidades.posision_tina="C2";
                break;
            case R.id.tina11:
                selecion_tina("tina 11",t11,"tina");
                Utilidades.posision_tina="B2";
                break;
            case R.id.tina12:
                selecion_tina("tina 12",t12,"tina");
                Utilidades.posision_tina="A2";
                break;

//----------------------------------------------------botones de empleados--------------------------------------------------------
            case R.id.emple1:
                selecion_tina("empleado 1",emple1,"empleado");

                break;
            case R.id.emple2:
                selecion_tina("empleado 2",emple2,"empleado");


                break;

            case R.id.emple3:
                selecion_tina("empleado 3",emple3,"empleado");


                break;
            case R.id.emple4:
                selecion_tina("empleado 4",emple4,"empleado");


                break;
            case R.id.emple5:
                selecion_tina("empleado 5",emple5,"empleado");


                break;
            case R.id.emple6:
                selecion_tina("empleado 6",emple6,"empleado");


                break;
            case R.id.emple7:
                selecion_tina("empleado 7",emple7,"empleado");


                break;
            case R.id.emple8:
                selecion_tina("empleado 8",emple8,"empleado");


                break;
            case R.id.emple9:
                selecion_tina("empleado 9",emple9,"empleado");


                break;
            case R.id.emple10:
                selecion_tina("empleado 10",emple10,"empleado");
                break;
//------------------------------------Montacargas---------------------------------------------------------------------

            case R.id.monta1:
                selecion_tina("montacargas 1",monta1,"montacargas");


                break;
            case R.id.monta2:
                selecion_tina("montacargas 2",monta2,"montacargas");


                break;
            case R.id.monta3:
                selecion_tina("montacargas 3",monta3,"montacargas");


                break;
            case R.id.monta4:
                selecion_tina("montacargas 4",monta4,"montacargas");
                break;

        }


    }
    private void selecion_tina(String tina,Button bton,String tipo_boton){
        Button aux=null;
        aux=btn_selecionado();
        Metodos_Retonables asignacion = new Metodos_Retonables();

        Boolean asignada_atual=asignacion.Tina_asignada(tina);
        Boolean asignada_anterior=asignacion.Tina_asignada((Utilidades.boton_presionado_Preselecion_plan));
        //solo si no se selecionado ninguna tina la cula este seleciona entra

        if (!asignada_atual&&!asignada_anterior) {

            if (Utilidades.boton_presionado_Preselecion_plan.equals("ninguno")) {
                bton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.selecion));
                Utilidades.boton_presionado_Preselecion_plan = tina;
                Utilidades.tipo_de_boton=tipo_boton;
                btn_pantalla_humo();

            } else {
                if (Utilidades.boton_presionado_Preselecion_plan.equals(tina)) {
                    bton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.no_selecion));
                    Utilidades.boton_presionado_Preselecion_plan = "ninguno";
                    Utilidades.posision_tina = "ninguno";
                    Utilidades.tipo_de_boton=tipo_boton;
                    btn_pantalla_humo();
                } else {
                    bton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.selecion));
                    aux.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.no_selecion));
                    Utilidades.boton_presionado_Preselecion_plan = tina;
                    Utilidades.tipo_de_boton=tipo_boton;
                    btn_pantalla_humo();

                }
            }
        }else{
            if (Utilidades.boton_presionado_Preselecion_plan.equals("ninguno")) {
                bton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Tina_asignada_presionada));
                Utilidades.boton_presionado_Preselecion_plan = tina;
                Utilidades.tipo_de_boton=tipo_boton;
                botones_inferiores(1);

            } else {
                if (Utilidades.boton_presionado_Preselecion_plan.equals(tina)) {
                    bton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Tina_asignada));
                    Utilidades.boton_presionado_Preselecion_plan = "ninguno";
                    Utilidades.posision_tina = "ninguno";
                    Utilidades.tipo_de_boton=tipo_boton;
                    botones_inferiores(1);
                    btn_pantalla_humo();
                } else {
                    if (!asignada_anterior&& asignada_atual){
                        bton.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.Tina_asignada_presionada));
                        aux.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.no_selecion));
                        Utilidades.boton_presionado_Preselecion_plan = tina;
                        Utilidades.tipo_de_boton=tipo_boton;
                        botones_inferiores(1);

                    }else{
                        if (asignada_anterior&&!asignada_atual){
                            bton.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.selecion));
                            aux.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.Tina_asignada));
                            Utilidades.boton_presionado_Preselecion_plan = tina;
                            Utilidades.tipo_de_boton=tipo_boton;
                            botones_inferiores(2);
                        }else{
                            if (asignada_anterior&&asignada_atual){
                                bton.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.Tina_asignada_presionada));
                                aux.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.Tina_asignada));
                                Utilidades.boton_presionado_Preselecion_plan = tina;
                                Utilidades.tipo_de_boton=tipo_boton;
                                botones_inferiores(1);


                            }else{
                            Toast.makeText(getContext(), "ni idea para donde fue que se fue", Toast.LENGTH_SHORT).show();
                        }
                        }
                    }

                }
            }

        }
    }

    private void btn_pantalla_humo(){

        if (Utilidades.boton_presionado_Preselecion_plan.equals("ninguno")){
            panta_humo.setVisibility(View.VISIBLE);
        }else{
            panta_humo.setVisibility(View.GONE);
        }

    }
    public void botones_inferiores(int valor){
        if (valor==1){
            if (Utilidades.tipo_de_boton.equals("tina")){
                para1.setEnabled(true);
                para2.setText("mezclar");
            }else{
                para2.setEnabled(false);
                para1.setEnabled(true);
            }
    }else {
            if (Utilidades.tipo_de_boton.equals("tina")){
            para1.setEnabled(false);
            para2.setText("asignar");
            para2.setEnabled(true);
            }else{
                para1.setEnabled(false);
                para2.setEnabled(true);
            }
        }}

    private void crear_dialog(View view){

        {
            final Button auxi=btn_selecionado();
            Metodos_Retonables obtner_fecha= new Metodos_Retonables();
            AlertDialog.Builder alerta= new AlertDialog.Builder(view.getContext());
            View vista= getActivity().getLayoutInflater().inflate(R.layout.dialog_asignar_tina,null);
            final TextView dialogfecha= vista.findViewById(R.id.fecha);
            final TextView dialogposision=vista.findViewById(R.id.posisionTina);
            String fechaActual= obtner_fecha.Obtener_fecha();
            dialogfecha.setText(fechaActual);
            dialogposision.setText(Utilidades.posision_tina);
            alerta.setCancelable(true).setPositiveButton("SI",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            auxi.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Tina_asignada));
                            tina_asignacion(Utilidades.boton_presionado_Preselecion_plan);
                            Utilidades.boton_presionado_Preselecion_plan="ninguno";
                            btn_pantalla_humo();

                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alerta.setView(vista);
            alerta.show();
            //return alerta.create();

        }
    }


    private void crear_dialog_empleado(View view){

        {
            final Button auxi=btn_selecionado();
            Metodos_Retonables obtner_fecha= new Metodos_Retonables();
            AlertDialog.Builder alerta= new AlertDialog.Builder(view.getContext());
            View vista= getActivity().getLayoutInflater().inflate(R.layout.dialog_asignar_empleado,null);
            final TextView dialogfecha= vista.findViewById(R.id.fecha);
            String fechaActual= obtner_fecha.Obtener_fecha();
            dialogfecha.setText(fechaActual);
            alerta.setCancelable(true).setPositiveButton("SI",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            auxi.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Tina_asignada));
                            Empleado_asignacion(Utilidades.boton_presionado_Preselecion_plan);
                            Utilidades.boton_presionado_Preselecion_plan="ninguno";
                            btn_pantalla_humo();

                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alerta.setView(vista);
            alerta.show();
            //return alerta.create();

        }
    }

    private void crear_dialog_montacargas(View view) {
        {
            final Button auxi=btn_selecionado();
            Metodos_Retonables obtner_fecha= new Metodos_Retonables();
            AlertDialog.Builder alerta= new AlertDialog.Builder(view.getContext());
            View vista= getActivity().getLayoutInflater().inflate(R.layout.dialog_asignar_montacargas,null);
            final TextView dialogfecha= vista.findViewById(R.id.fecha);
            String fechaActual= obtner_fecha.Obtener_fecha();
            dialogfecha.setText(fechaActual);
            alerta.setCancelable(true).setPositiveButton("SI",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            auxi.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Tina_asignada));
                            montacargas_asignacion(Utilidades.boton_presionado_Preselecion_plan);

                            Utilidades.boton_presionado_Preselecion_plan="ninguno";
                            btn_pantalla_humo();

                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alerta.setView(vista);
            alerta.show();
            //return alerta.create();

        }
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
    /** metodos con switch para asiganacion a variables */
    private void tina_asignacion(String tina){

        switch (tina){
            case "ninguno":
                break;
            case "tina 1":
                Utilidades.T_1_asignada=true;


                break;
            case "tina 2":
                Utilidades.T_2_asignada=true;

                break;
            case "tina 3":
                Utilidades.T_3_asignada=true;

                break;
            case "tina 4":
                Utilidades.T_4_asignada=true;

                break;
            case "tina 5":
                Utilidades.T_5_asignada=true;

                break;
            case "tina 6":
                Utilidades.T_6_asignada=true;

                break;
            case "tina 7":
                Utilidades.T_7_asignada=true;

                break;
            case "tina 8":
                Utilidades.T_8_asignada=true;

                break;
            case "tina 9":
                Utilidades.T_9_asignada=true;

                break;
            case "tina 10":
                Utilidades.T_10_asignada=true;

                break;
            case "tina 11":
                Utilidades.T_11_asignada=true;

                break;
            case "tina 12":
                Utilidades.T_12_asignada=true;

                break;

        }
    }
    //cambia la variable a true cuando se a asignado al empleado, en la clase utilidades

    private void Empleado_asignacion(String tina){
        switch (tina){
            case "ninguno":
                break;
            case "empleado 1":
                Utilidades.emple_1_asignado=true;


                break;
            case "empleado 2":
                Utilidades.emple_2_asignado=true;

                break;
            case "empleado 3":
                Utilidades.emple_3_asignado=true;

                break;
            case "empleado 4":
                Utilidades.emple_4_asignado=true;

                break;
            case "empleado 5":
                Utilidades.emple_5_asignado=true;

                break;
            case "empleado 6":
                Utilidades.emple_6_asignado=true;

                break;
            case "empleado 7":
                Utilidades.emple_7_asignado=true;

                break;
            case "empleado 8":
                Utilidades.emple_8_asignado=true;

                break;
            case "empleado 9":
                Utilidades.emple_9_asignado=true;

                break;
            case "empleado 10":
                Utilidades.emple_10_asignado=true;

                break;


        }
    }
    private void montacargas_asignacion(String tina){
        switch (tina){
            case "ninguno":
                break;
            case "montacargas 1":
                Utilidades.montacargas_1_asignada=true;


                break;
            case "montacargas 2":
                Utilidades.montacargas_2_asignada=true;

                break;
            case "montacargas 3":
                Utilidades.montacargas_3_asignada=true;

                break;
            case "montacargas 4":
                Utilidades.montacargas_4_asignada=true;

                break;}}
    //verifica cual es el boton selecioano en este momento
    private Button btn_selecionado(){
        Button aux=null;
        String a ="T_1_asignada";
        switch (Utilidades.boton_presionado_Preselecion_plan){
            case "ninguno":
                break;
            case "tina 1":
                aux=t1;

                break;
            case "tina 2":
                aux=t2;

                break;
            case "tina 3":
                aux=t3;

                break;
            case "tina 4":
                aux=t4;

                break;
            case "tina 5":
                aux=t5;

                break;
            case "tina 6":
                aux=t6;

                break;
            case "tina 7":
                aux=t7;

                break;
            case "tina 8":
                aux=t8;

                break;
            case "tina 9":
                aux=t9;

                break;
            case "tina 10":
                aux=t10;

                break;
            case "tina 11":
                aux=t11;

                break;
            case "tina 12":
                aux=t12;

                break;

            case "empleado 1":
                aux=emple1;


                break;
            case "empleado 2":
                aux=emple2;

                break;
            case "empleado 3":
                aux=emple3;

                break;
            case "empleado 4":
                aux=emple4;

                break;
            case "empleado 5":
                aux=emple5;

                break;
            case "empleado 6":
                aux=emple6;

                break;
            case "empleado 7":
                aux=emple7;

                break;
            case "empleado 8":
                aux=emple8;

                break;
            case "empleado 9":
                aux=emple9;

                break;
            case "empleado 10":
                aux=emple10;

                break;

            case "montacargas 1":
                aux=monta1;

                break;
            case "montacargas 2":
                aux=monta2;

                break;
            case "montacargas 3":
                aux=monta3;

                break;
            case "montacargas 4":
                aux=monta4;

                break;
        }
        return aux;
    }
}

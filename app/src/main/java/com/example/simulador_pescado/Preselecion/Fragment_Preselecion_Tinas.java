package com.example.simulador_pescado.Preselecion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.vista.OperadorBascula;
import com.example.simulador_pescado.vista.OperadorMontacargas;
import com.example.simulador_pescado.vista.Recurso;
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

    private Recurso operador1;
    private Recurso operador2;
    private Recurso operador3;
    private Recurso operador4;
    private Recurso operador5;
    private Recurso operador6;
    private Recurso operador7;
    private Recurso operador8;
    private Recurso operador9;
    private Recurso operador10;

    private Recurso tina1;
    private Recurso tina2;
    private Recurso tina3;
    private Recurso tina4;
    private Recurso tina5;
    private Recurso tina6;
    private Recurso tina7;
    private Recurso tina8;
    private Recurso tina9;
    private Recurso tina10;
    private Recurso tina11;
    private Recurso tina12;

    private Recurso montacargas1;
    private Recurso montacargas2;
    private Recurso montacargas3;
    private Recurso montacargas4;

    private Recurso recursoSeleccionado;

    private List<Recurso> listaRecursos = new ArrayList<>();

    //private Button para1,para2;
    //TextView panta_humo;

    private OnFragmentInteractionListener mListener;

    public Fragment_Preselecion_Tinas() {
        // Required empty public constructor
    }

    public Recurso getRecursoSeleccionado() {
        return recursoSeleccionado;
    }

    public void setRecursoSeleccionado(Recurso recursoSeleccionado) {
        this.recursoSeleccionado = recursoSeleccionado;
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

    private void accionIconoOperador(Recurso recurso){
        if( recurso.getEstado() == Recurso.ESTADO.inicial ){
            setRecursoSeleccionado(recurso);
            deshabilitaRecursos();
            recurso.getIcono().setImageResource(R.drawable.ic_operador1);
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
            recurso.getIcono().setImageResource(R.drawable.ic_operador2);
            recurso.getIcono().setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
            recurso.setEstado( Recurso.ESTADO.inicial );
            this.contenedorBotones.setVisibility(View.GONE);
        }
    }

    private void accionIconoTina(Recurso recurso){
        if( recurso.getEstado() == Recurso.ESTADO.inicial ){
            setRecursoSeleccionado(recurso);
            deshabilitaRecursos();
            recurso.getIcono().setImageResource(R.drawable.ic_tina1);
            recurso.getIcono().setBackground( getResources().getDrawable( R.drawable.contenedor_icono_seleccionado ) );
            recurso.setEstado( Recurso.ESTADO.seleccionado );
            this.boton1.setText(R.string.liberarTina);
            this.boton1.setEnabled(false);
            this.boton2.setText(R.string.asignarTina);
            this.boton2.setEnabled(true);
            this.contenedorBotones.setVisibility(View.VISIBLE);
        }else{
            setRecursoSeleccionado(null);
            habilitaRecursos();
            recurso.getIcono().setImageResource(R.drawable.ic_tina2);
            recurso.getIcono().setBackground( getResources().getDrawable( R.drawable.contenedor_icono ) );
            recurso.setEstado( Recurso.ESTADO.inicial );
            this.contenedorBotones.setVisibility(View.GONE);
        }
    }

    private void accionIconoMontacargas(Recurso recurso){
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
    }

    private void iniciaComponentes(){
        this.contenedorBotones = this.vista.findViewById(R.id.contenedorBotones);
        this.boton1 = this.vista.findViewById(R.id.boton1);
        this.boton2 = this.vista.findViewById(R.id.boton2);

        ImageView imagenTina1 = this.vista.findViewById(R.id.tina1);
        this.tina1 = new Tina();
        this.tina1.setEstado(Recurso.ESTADO.inicial);
        this.tina1.setIcono(imagenTina1);
        this.tina1.setPosicion("A1");
        this.tina1.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(tina1);
            }
        });

        ImageView imagenTina2 = this.vista.findViewById(R.id.tina2);
        this.tina2 = new Tina();
        this.tina2.setEstado(Recurso.ESTADO.inicial);
        this.tina2.setIcono(imagenTina2);
        this.tina2.setPosicion("A2");
        this.tina2.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(tina2);
            }
        });

        ImageView imagenTina3 = this.vista.findViewById(R.id.tina3);
        this.tina3 = new Tina();
        this.tina3.setEstado(Recurso.ESTADO.inicial);
        this.tina3.setIcono(imagenTina3);
        this.tina3.setPosicion("A3");
        this.tina3.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(tina3);
            }
        });

        ImageView imagenTina4 = this.vista.findViewById(R.id.tina4);
        this.tina4 = new Tina();
        this.tina4.setEstado(Recurso.ESTADO.inicial);
        this.tina4.setIcono(imagenTina4);
        this.tina4.setPosicion("A4");
        this.tina4.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(tina4);
            }
        });

        ImageView imagenTina5 = this.vista.findViewById(R.id.tina5);
        this.tina5 = new Tina();
        this.tina5.setEstado(Recurso.ESTADO.inicial);
        this.tina5.setIcono(imagenTina5);
        this.tina5.setPosicion("A5");
        this.tina5.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(tina5);
            }
        });

        ImageView imagenTina6 = this.vista.findViewById(R.id.tina6);
        this.tina6 = new Tina();
        this.tina6.setEstado(Recurso.ESTADO.inicial);
        this.tina6.setIcono(imagenTina6);
        this.tina6.setPosicion("A6");
        this.tina6.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(tina6);
            }
        });

        ImageView imagenTina7 = this.vista.findViewById(R.id.tina7);
        this.tina7 = new Tina();
        this.tina7.setEstado(Recurso.ESTADO.inicial);
        this.tina7.setIcono(imagenTina7);
        this.tina7.setPosicion("B6");
        this.tina7.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(tina7);
            }
        });

        ImageView imagenTina8 = this.vista.findViewById(R.id.tina8);
        this.tina8 = new Tina();
        this.tina8.setEstado(Recurso.ESTADO.inicial);
        this.tina8.setIcono(imagenTina8);
        this.tina8.setPosicion("B5");
        this.tina8.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(tina8);
            }
        });

        ImageView imagenTina9 = this.vista.findViewById(R.id.tina9);
        this.tina9 = new Tina();
        this.tina9.setEstado(Recurso.ESTADO.inicial);
        this.tina9.setIcono(imagenTina9);
        this.tina9.setPosicion("B4");
        this.tina9.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(tina9);
            }
        });

        ImageView imagenTina10 = this.vista.findViewById(R.id.tina10);
        this.tina10 = new Tina();
        this.tina10.setEstado(Recurso.ESTADO.inicial);
        this.tina10.setIcono(imagenTina10);
        this.tina10.setPosicion("B3");
        this.tina10.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(tina10);
            }
        });

        ImageView imagenTina11 = this.vista.findViewById(R.id.tina11);
        this.tina11 = new Tina();
        this.tina11.setEstado(Recurso.ESTADO.inicial);
        this.tina11.setIcono(imagenTina11);
        this.tina11.setPosicion("B2");
        this.tina11.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(tina11);
            }
        });

        ImageView imagenTina12 = this.vista.findViewById(R.id.tina12);
        this.tina12 = new Tina();
        this.tina12.setEstado(Recurso.ESTADO.inicial);
        this.tina12.setIcono(imagenTina12);
        this.tina12.setPosicion("B1");
        this.tina12.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoTina(tina12);
            }
        });

        ImageView imagenOperador1 = this.vista.findViewById(R.id.operador1);
        this.operador1 = new OperadorBascula();
        this.operador1.setEstado(Recurso.ESTADO.inicial);
        this.operador1.setIcono(imagenOperador1);
        this.operador1.setPosicion("O1");
        this.operador1.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(operador1);
            }
        });

        ImageView imagenOperador2 = this.vista.findViewById(R.id.operador2);
        this.operador2 = new OperadorBascula();
        this.operador2.setEstado(Recurso.ESTADO.inicial);
        this.operador2.setIcono(imagenOperador2);
        this.operador2.setPosicion("O2");
        this.operador2.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(operador2);
            }
        });

        ImageView imagenOperador3 = this.vista.findViewById(R.id.operador3);
        this.operador3 = new OperadorBascula();
        this.operador3.setEstado(Recurso.ESTADO.inicial);
        this.operador3.setIcono(imagenOperador3);
        this.operador3.setPosicion("O3");
        this.operador3.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(operador3);
            }
        });

        ImageView imagenOperador4 = this.vista.findViewById(R.id.operador4);
        this.operador4 = new OperadorBascula();
        this.operador4.setEstado(Recurso.ESTADO.inicial);
        this.operador4.setIcono(imagenOperador4);
        this.operador4.setPosicion("O4");
        this.operador4.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(operador4);
            }
        });

        ImageView imagenOperador5 = this.vista.findViewById(R.id.operador5);
        this.operador5 = new OperadorBascula();
        this.operador5.setEstado(Recurso.ESTADO.inicial);
        this.operador5.setIcono(imagenOperador5);
        this.operador5.setPosicion("O5");
        this.operador5.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(operador5);
            }
        });

        ImageView imagenOperador6 = this.vista.findViewById(R.id.operador6);
        this.operador6 = new OperadorBascula();
        this.operador6.setEstado(Recurso.ESTADO.inicial);
        this.operador6.setIcono(imagenOperador6);
        this.operador6.setPosicion("O6");
        this.operador6.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(operador6);
            }
        });

        ImageView imagenOperador7 = this.vista.findViewById(R.id.operador7);
        this.operador7 = new OperadorBascula();
        this.operador7.setEstado(Recurso.ESTADO.inicial);
        this.operador7.setIcono(imagenOperador7);
        this.operador7.setPosicion("O7");
        this.operador7.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(operador7);
            }
        });

        ImageView imagenOperador8 = this.vista.findViewById(R.id.operador8);
        this.operador8 = new OperadorBascula();
        this.operador8.setEstado(Recurso.ESTADO.inicial);
        this.operador8.setIcono(imagenOperador8);
        this.operador8.setPosicion("O8");
        this.operador8.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(operador8);
            }
        });

        ImageView imagenOperador9 = this.vista.findViewById(R.id.operador9);
        this.operador9 = new OperadorBascula();
        this.operador9.setEstado(Recurso.ESTADO.inicial);
        this.operador9.setIcono(imagenOperador9);
        this.operador9.setPosicion("O9");
        this.operador9.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(operador9);
            }
        });

        ImageView imagenOperador10 = this.vista.findViewById(R.id.operador10);
        this.operador10 = new OperadorBascula();
        this.operador10.setEstado(Recurso.ESTADO.inicial);
        this.operador10.setIcono(imagenOperador10);
        this.operador10.setPosicion("O10");
        this.operador10.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoOperador(operador10);
            }
        });

        ImageView imagenMontacargas1 = this.vista.findViewById(R.id.montacargas1);
        this.montacargas1 = new OperadorMontacargas();
        this.montacargas1.setEstado(Recurso.ESTADO.inicial);
        this.montacargas1.setIcono(imagenMontacargas1);
        this.montacargas1.setPosicion("M1");
        this.montacargas1.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoMontacargas(montacargas1);
            }
        });

        ImageView imagenMontacargas2 = this.vista.findViewById(R.id.montacargas2);
        this.montacargas2 = new OperadorMontacargas();
        this.montacargas2.setEstado(Recurso.ESTADO.inicial);
        this.montacargas2.setIcono(imagenMontacargas2);
        this.montacargas2.setPosicion("M2");
        this.montacargas2.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoMontacargas(montacargas2);
            }
        });

        ImageView imagenMontacargas3 = this.vista.findViewById(R.id.montacargas3);
        this.montacargas3 = new OperadorMontacargas();
        this.montacargas3.setEstado(Recurso.ESTADO.inicial);
        this.montacargas3.setIcono(imagenMontacargas3);
        this.montacargas3.setPosicion("M3");
        this.montacargas3.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoMontacargas(montacargas3);
            }
        });

        ImageView imagenMontacargas4 = this.vista.findViewById(R.id.montacargas4);
        this.montacargas4 = new OperadorMontacargas();
        this.montacargas4.setEstado(Recurso.ESTADO.inicial);
        this.montacargas4.setIcono(imagenMontacargas4);
        this.montacargas4.setPosicion("M4");
        this.montacargas4.getIcono().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionIconoMontacargas(montacargas4);
            }
        });

        llenaListaRecursos();
    }

    private void llenaListaRecursos(){
        this.listaRecursos.add(operador1);
        this.listaRecursos.add(operador2);
        this.listaRecursos.add(operador3);
        this.listaRecursos.add(operador4);
        this.listaRecursos.add(operador5);
        this.listaRecursos.add(operador6);
        this.listaRecursos.add(operador7);
        this.listaRecursos.add(operador8);
        this.listaRecursos.add(operador9);
        this.listaRecursos.add(operador10);

        this.listaRecursos.add(tina1);
        this.listaRecursos.add(tina2);
        this.listaRecursos.add(tina3);
        this.listaRecursos.add(tina4);
        this.listaRecursos.add(tina5);
        this.listaRecursos.add(tina6);
        this.listaRecursos.add(tina7);
        this.listaRecursos.add(tina8);
        this.listaRecursos.add(tina9);
        this.listaRecursos.add(tina10);
        this.listaRecursos.add(tina11);
        this.listaRecursos.add(tina12);

        this.listaRecursos.add(montacargas1);
        this.listaRecursos.add(montacargas2);
        this.listaRecursos.add(montacargas3);
        this.listaRecursos.add(montacargas4);
    }

    private void deshabilitaRecursos(){
        for(Recurso recurso : this.listaRecursos){
            if( !recurso.getPosicion().equalsIgnoreCase( getRecursoSeleccionado().getPosicion() ) ){
                recurso.getIcono().setEnabled(false);
            }
        }
    }

    private void habilitaRecursos(){
        for(Recurso recurso : this.listaRecursos){
            recurso.getIcono().setEnabled(true);
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

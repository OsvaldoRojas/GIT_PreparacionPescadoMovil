package com.grupo.pinsa.sip.simulador.cocimiento;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorCarritosCocimiento;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Cocimiento;
import com.grupo.pinsa.sip.simulador.vista.Carrito;
import com.grupo.pinsa.sip.simulador.vista.Cocedor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Carga_Carritos extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private View vista;

    private List<Carrito> listaCarritos;
    private AdaptadorCarritosCocimiento adaptador;

    int totalCarritos = 0;

    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private ProgressBar barraProgreso;
    private SwipeRefreshLayout actualizar;
    private CheckBox seleccionarTodo;
    private RecyclerView vistaLista;

    private Cocedor cocedorSeleccionado;

    private OnFragmentInteractionListener mListener;

    public Fragment_Carga_Carritos() {
    }

    public static Fragment_Carga_Carritos newInstance(Serializable param1) {
        Fragment_Carga_Carritos fragment = new Fragment_Carga_Carritos();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setCocedorSeleccionado( (Cocedor) getArguments().getSerializable(ARG_PARAM1) );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_carga_carritos, container, false);
        iniciaComponentes();
        return this.vista;
    }

    public Cocedor getCocedorSeleccionado() {
        return cocedorSeleccionado;
    }

    public void setCocedorSeleccionado(Cocedor cocedorSeleccionado) {
        this.cocedorSeleccionado = cocedorSeleccionado;
    }

    public boolean getSeleccionar(){
        return this.seleccionarTodo.isChecked();
    }

    private void iniciaComponentes(){
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        //iniciaProcesando();

        TextView etiquetaTotalCarritos = this.vista.findViewById(R.id.totalCarritos);
        etiquetaTotalCarritos.setText( String.valueOf(this.totalCarritos).concat("/")
                .concat( String.valueOf( getCocedorSeleccionado().getCapacidad() ) ) );

        this.actualizar = this.vista.findViewById(R.id.actualizar);
        this.actualizar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actualizar.setRefreshing(true);
                //getAsignados();
            }
        });

        Button completo = this.vista.findViewById(R.id.botonCompleto);
        completo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button volver = this.vista.findViewById(R.id.botonVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor_Cocimiento().newInstance(0);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        ///
        this.listaCarritos = new ArrayList<>();
        for(int i = 1; i <= getCocedorSeleccionado().getCapacidad(); i++){
            Carrito carrito = new Carrito();
            carrito.setId(String.valueOf(i));
            carrito.setDescripcion("Carrito ".concat(String.valueOf(i)));
            carrito.setEspecie("Especie ".concat(String.valueOf(i)));
            carrito.setSubtalla("Subtalla ".concat(String.valueOf(i)));
            this.listaCarritos.add(carrito);
        }

        this.vistaLista = this.vista.findViewById(R.id.listaCarritos);
        this.vistaLista.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
        this.vistaLista.setLayoutManager(layoutManager);

        this.adaptador = new AdaptadorCarritosCocimiento(this.listaCarritos, this);
        this.vistaLista.setAdapter(this.adaptador);
        ////

        this.seleccionarTodo = this.vista.findViewById(R.id.seleccionarTodo);
        this.seleccionarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < listaCarritos.size(); i++){
                    listaCarritos.get(i).setSeleccionado( seleccionarTodo.isChecked() );
                    listaCarritos.get(i).setSeleccionadoGeneral( !seleccionarTodo.isChecked() );
                    listaCarritos.get(i).setSeleccionadoSuma( seleccionarTodo.isChecked() );
                }
                adaptador.notifyDataSetChanged();
                actualizaTotal();

                if( seleccionarTodo.isChecked() ){
                    seleccionarTodo.setText("Deseleccionar todo");
                }else{
                    seleccionarTodo.setText("Seleccionar todo");
                }
            }
        });
    }

    public void actualizaTotal(){
        this.totalCarritos = 0;
        for(Carrito carrito : this.listaCarritos){
            if( carrito.isSeleccionadoSuma() ){
                this.totalCarritos = this.totalCarritos + 1;
            }
        }
        TextView etiquetaTotalCarritos = this.vista.findViewById(R.id.totalCarritos);
        etiquetaTotalCarritos.setText( String.valueOf(this.totalCarritos).concat("/")
                .concat( String.valueOf( getCocedorSeleccionado().getCapacidad() ) ) );
    }

    public void iniciaProcesando(){
        ProgressBar barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        ProgressBar barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
}

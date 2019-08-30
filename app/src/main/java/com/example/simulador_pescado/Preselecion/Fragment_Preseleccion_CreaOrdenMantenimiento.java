package com.example.simulador_pescado.Preselecion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.ActividadArtefactos;
import com.example.simulador_pescado.Contenedores.Contenedor;
import com.example.simulador_pescado.R;
import com.example.simulador_pescado.Utilerias.Catalogos;
import com.example.simulador_pescado.Utilerias.Utilerias;
import com.example.simulador_pescado.conexion.ObtenArtefactosMaquinaria;
import com.example.simulador_pescado.vista.Artefacto;
import com.example.simulador_pescado.vista.Bascula;
import com.example.simulador_pescado.vista.Maquinaria;
import com.example.simulador_pescado.vista.OperadorBascula;
import com.example.simulador_pescado.vista.OperadorMontacargas;
import com.example.simulador_pescado.vista.OrdenMantenimiento;
import com.example.simulador_pescado.vista.Tina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Fragment_Preseleccion_CreaOrdenMantenimiento extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Serializable mParam1;

    private View vista;

    private Tina tinaSeleccionada;
    private OperadorBascula operadorSeleccionado;
    private OperadorMontacargas montacargasSeleccionado;
    private Bascula basculaSeleccionada;
    private Maquinaria maquinaria;
    private OrdenMantenimiento ordenMantenimiento = new OrdenMantenimiento();

    private List<Artefacto> catalogoArtefactos = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public Fragment_Preseleccion_CreaOrdenMantenimiento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment Fragment_Preselecion_Tinas.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Preseleccion_CreaOrdenMantenimiento newInstance(Serializable param1) {
        Fragment_Preseleccion_CreaOrdenMantenimiento fragment = new Fragment_Preseleccion_CreaOrdenMantenimiento();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista=inflater.inflate(R.layout.fragment_orden_mantenimiento, container, false);

        iniciaComponentes();
        return this.vista;
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

    public Maquinaria getMaquinaria() {
        return maquinaria;
    }

    public void setMaquinaria(Maquinaria maquinaria) {
        this.maquinaria = maquinaria;
    }

    public OrdenMantenimiento getOrdenMantenimiento() {
        return ordenMantenimiento;
    }

    private void iniciaComponentes(){
        iniciaProcesando();

        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor().newInstance(1);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        Button botonAceptar = this.vista.findViewById(R.id.boton2);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciaProcesando();
                guardaOrden();
            }
        });

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        Button botonArtefactos = this.vista.findViewById(R.id.botonArtefactos);
        botonArtefactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent artefactos = new Intent(getContext(), ActividadArtefactos.class);
                artefactos.putExtra("catalogo", (Serializable) catalogoArtefactos);
                startActivityForResult(artefactos, 0);
            }
        });

        TextView etiquetaEquipo = this.vista.findViewById(R.id.etiquetaEquipo);
        if( this.mParam1 instanceof Tina ){
            setTinaSeleccionada( (Tina) this.mParam1 );
            if( obtenMaquinaria( getTinaSeleccionada().getClave() ) ){
                etiquetaEquipo.setText( getMaquinaria().getDescripcion() );
            }
        }else{
            if( this.mParam1 instanceof OperadorBascula ){
                setOperadorSeleccionado( (OperadorBascula) this.mParam1 );
                if( obtenMaquinaria( getOperadorSeleccionado().getClave() ) ){
                    etiquetaEquipo.setText( getMaquinaria().getDescripcion() );
                }
            }else{
                if( this.mParam1 instanceof OperadorMontacargas ){
                    setMontacargasSeleccionado( (OperadorMontacargas) this.mParam1 );
                    if( obtenMaquinaria( getMontacargasSeleccionado().getClave() ) ){
                        etiquetaEquipo.setText( getMaquinaria().getDescripcion() );
                    }
                }else{
                    if( this.mParam1 instanceof Bascula ){
                        setBasculaSeleccionada( (Bascula) this.mParam1 );
                        if( obtenMaquinaria( getBasculaSeleccionada().getClave() ) ){
                            etiquetaEquipo.setText( parseaMensaje( getMaquinaria().getDescripcion() ) );
                        }
                    }
                }
            }
        }

        ObtenArtefactosMaquinaria obtenArtefactosMaquinaria = new ObtenArtefactosMaquinaria(this, getMaquinaria().getIdMaquinaria() );
        obtenArtefactosMaquinaria.execute();
    }

    private boolean obtenMaquinaria(String clave){
        for( Maquinaria maquinariaLista : Catalogos.getInstancia().getCatalogoMaquinaria() ){
            if( maquinariaLista.getClave().equalsIgnoreCase(clave) ){
                setMaquinaria(maquinariaLista);
                return true;
            }
        }
        return false;
    }

    private String parseaMensaje(String mensaje){
        if( mensaje.contains("BÃ¡scula") ){
            mensaje = mensaje.replace("Ã¡", "á");
        }
        return mensaje;
    }

    public void resultadoCatalogoArtefacto(List<Artefacto> lista){
        this.catalogoArtefactos = lista;
        terminaProcesando();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( resultCode == RESULT_OK ){
            this.catalogoArtefactos = (List<Artefacto>) data.getSerializableExtra("artefactos");
            List<Artefacto> artefactos = new ArrayList<>();
            for( Artefacto artefacto : this.catalogoArtefactos ){
                if( artefacto.getSelecionado() ){
                    artefactos.add(artefacto);
                }
            }
            getOrdenMantenimiento().setListaArtefactos(artefactos);
        }
    }

    private void guardaOrden(){
        getOrdenMantenimiento().setMaquinaria( getMaquinaria() );
        TextView descripcion = this.vista.findViewById(R.id.campoDescripcion);
        getOrdenMantenimiento().setDescripcion( descripcion.getText().toString() );
        if( getOrdenMantenimiento().getListaArtefactos() == null ){
            getOrdenMantenimiento().setListaArtefactos( new ArrayList<Artefacto>() );
        }
        // CONSUMIR SERVICIO PARA GUARDAR ORDEN DE MANTENIMIENTO
        terminaProcesando();
        Fragment fragment = new Contenedor().newInstance(1);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
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
}

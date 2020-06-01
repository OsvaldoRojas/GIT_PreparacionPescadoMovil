package com.grupo.pinsa.sip.simulador.orden;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorArtefactoOrden;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorMaquinaria;
import com.grupo.pinsa.sip.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.simulador.utilerias.Catalogos;
import com.grupo.pinsa.sip.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.simulador.modelo.Artefacto;
import com.grupo.pinsa.sip.simulador.modelo.Maquinaria;
import com.grupo.pinsa.sip.simulador.modelo.OrdenMantenimiento;
import com.grupo.pinsa.sip.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.simulador.modelo.servicio.ArtefactoServicio;
import com.grupo.pinsa.sip.simulador.modelo.servicio.OrdenMantenimientoGuardar;
import com.grupo.pinsa.sip.simulador.modelo.servicio.RespuestaServicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class Fragment_CreaOrdenMantenimiento extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private View vista;

    private Maquinaria maquinaria;
    private OrdenMantenimiento ordenMantenimiento = new OrdenMantenimiento();

    private Spinner seleccionMaquinaria;
    private ListView listaArtefactos;

    private AlertDialog ventanaError;

    private List<Artefacto> catalogoArtefactos = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public Fragment_CreaOrdenMantenimiento() {
    }

    public static Fragment_CreaOrdenMantenimiento newInstance(String param1) {
        Fragment_CreaOrdenMantenimiento fragment = new Fragment_CreaOrdenMantenimiento();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista=inflater.inflate(R.layout.fragment_crea_orden_mantenimiento, container, false);
        iniciaComponentes();
        return this.vista;
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
                limpiaCampos();
            }
        });

        Button botonAceptar = this.vista.findViewById(R.id.boton2);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciaProcesando();
                validaGuardado();
            }
        });

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        Button botonArtefactos = this.vista.findViewById(R.id.botonArtefactos);
        botonArtefactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( getMaquinaria() != null ){
                    Intent artefactos = new Intent(getContext(), ActividadArtefactos.class);
                    artefactos.putExtra("catalogo", (Serializable) catalogoArtefactos);
                    startActivityForResult(artefactos, 0);
                }else{
                    errorServicio("Es necesario seleccionar una máquinaria");
                }
            }
        });

        this.seleccionMaquinaria = this.vista.findViewById(R.id.seleccionMaquinaria);
        this.seleccionMaquinaria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                iniciaProcesando();
                Maquinaria maquinaria = (Maquinaria) adapterView.getItemAtPosition(i);
                getOrdenMantenimiento().setArtefactos(new ArrayList<Artefacto>());
                listaArtefactos.setVisibility(View.GONE);
                if(maquinaria.getIdMaquinaria() > 0){
                    setMaquinaria( (Maquinaria) adapterView.getItemAtPosition(i) );
                    obtenArtefactos();
                }else{
                    resultadoCatalogoArtefacto(new ArrayList<Artefacto>());
                    setMaquinaria(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Call<List<Maquinaria>> llamadaServicio = APIServicios.getConexion().getMaquinarias( Catalogos.getInstancia().getEtapaActual() );
        llamadaServicio.enqueue(new Callback<List<Maquinaria>>() {
            @Override
            public void onResponse(Call<List<Maquinaria>> call, Response<List<Maquinaria>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        Catalogos.getInstancia().setCatalogoMaquinaria( response.body() );
                        seleccionMaquinaria.setAdapter(
                                new AdaptadorMaquinaria( Catalogos.getInstancia().getCatalogoMaquinaria(), getContext() )
                        );
                        terminaProcesando();
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener las máquinarias");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Maquinaria>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });

        this.listaArtefactos = this.vista.findViewById(R.id.listaArtefactos);

    }

    private void obtenArtefactos(){
        Call<List<Artefacto>> llamadaServicio = APIServicios.getConexion().getArtefactos( getMaquinaria().getIdMaquinaria() );
        llamadaServicio.enqueue(new Callback<List<Artefacto>>() {
            @Override
            public void onResponse(Call<List<Artefacto>> call, Response<List<Artefacto>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        resultadoCatalogoArtefacto( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener los artefactos");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Artefacto>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
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
            getOrdenMantenimiento().setArtefactos(artefactos);

            AdaptadorArtefactoOrden adaptadorArtefactos = new AdaptadorArtefactoOrden(
                    getContext(),
                    artefactos
            );
            this.listaArtefactos.setAdapter(adaptadorArtefactos);
            Utilerias.setAlturaLista(this.listaArtefactos, 0);
            this.listaArtefactos.setVisibility(View.VISIBLE);
        }
    }

    private void validaGuardado(){
        if( getOrdenMantenimiento().getArtefactos() == null
                || getOrdenMantenimiento().getArtefactos().size() == 0){
            terminaProcesando();
            errorServicio("Es necesario capturar al menos un componente");
            return;
        }

        TextView descripcion = this.vista.findViewById(R.id.campoDescripcion);
        if( descripcion.length() == 0 ){
            terminaProcesando();
            errorServicio("Es necesario capturar una descripción");
            return;
        }

        guardaOrden();
    }

    private void guardaOrden(){
        getOrdenMantenimiento().setMaquinaria( getMaquinaria() );
        TextView descripcion = this.vista.findViewById(R.id.campoDescripcion);
        getOrdenMantenimiento().setDescripcion( descripcion.getText().toString() );

        OrdenMantenimientoGuardar orden = new OrdenMantenimientoGuardar();
        orden.setIdOrdenMantenimiento(0);
        orden.setIdMaquinaria( getOrdenMantenimiento().getMaquinaria().getIdMaquinaria() );
        orden.setDescripcion( getOrdenMantenimiento().getDescripcion() );
        orden.setUsuario( UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        List<ArtefactoServicio> lista = new ArrayList<>();
        for( Artefacto artefacto : getOrdenMantenimiento().getArtefactos() ){
            ArtefactoServicio artefactoServicio = new ArtefactoServicio();
            artefactoServicio.setIdMaquinariaArtefacto( artefacto.getIdMaquinariaArtefacto() );
            lista.add(artefactoServicio);
        }
        orden.setArtefactos(lista);

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaOrdenMantenimiento(orden);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    terminaProcesando();
                    if( response.code() == 200 ){
                        for( Fragment fragment : getFragmentManager().getFragments() ){
                            if( fragment instanceof Fragment_OrdenMantenimiento ){
                                ((Fragment_OrdenMantenimiento) fragment).onStart();
                            }
                        }
                        mensajeGuardado();
                    }else{
                        errorServicio( "Error al guardar la orden de mantenimiento" );
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

    public void mensajeGuardado(){
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
                etiquetaMensaje.setText("Orden de mantenimiento guardada correctamente");

                Button botonAceptar = ventanaError.findViewById(R.id.boton1);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        limpiaCampos();
                        ventanaError.dismiss();
                    }
                });
            }
        });
        this.ventanaError.show();
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

    private void limpiaCampos(){
        this.seleccionMaquinaria.setSelection(0);
        this.ordenMantenimiento = new OrdenMantenimiento();
        TextView descripcion = this.vista.findViewById(R.id.campoDescripcion);
        descripcion.setText("");
        this.listaArtefactos.setVisibility(View.GONE);
    }

    public void iniciaProcesando(){
        ProgressBar barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        //getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        ProgressBar barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        //getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        barraProgreso.setVisibility(View.GONE);
    }
}

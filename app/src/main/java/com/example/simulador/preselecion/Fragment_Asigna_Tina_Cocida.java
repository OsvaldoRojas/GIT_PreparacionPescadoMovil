package com.example.simulador.preselecion;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simulador.R;
import com.example.simulador.adaptadores.AdaptadorCocida;
import com.example.simulador.conexion.APIServicios;
import com.example.simulador.contenedores.Contenedor;
import com.example.simulador.utilerias.Utilerias;
import com.example.simulador.vista.Cocida;
import com.example.simulador.vista.Tina;
import com.example.simulador.vista.UsuarioLogueado;
import com.example.simulador.vista.servicio.RespuestaServicio;
import com.example.simulador.vista.servicio.TinaEscaneo;
import com.example.simulador.vista.servicio.TinaServicio;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Asigna_Tina_Cocida extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Serializable mParam1;

    private View vista;

    private AlertDialog ventanaError;
    private RecyclerView vistaLista;

    private Tina tinaSeleccionada;

    private List<Cocida> cocidas;

    private OnFragmentInteractionListener mListener;

    public Fragment_Asigna_Tina_Cocida() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment Fragment_Preselecion_Tinas.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Asigna_Tina_Cocida newInstance(Serializable param1) {
        Fragment_Asigna_Tina_Cocida fragment = new Fragment_Asigna_Tina_Cocida();
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
        this.vista=inflater.inflate(R.layout.fragment_asignar_tina_cocida, container, false);

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

    private void iniciaComponentes() {
        iniciaProcesando();
        setTinaSeleccionada((Tina) mParam1);

        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor().newInstance(0);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        Button botonAceptar = this.vista.findViewById(R.id.boton2);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaAsignacion();
            }
        });

        TextView etiquetaPosicion = this.vista.findViewById(R.id.etiquetaPosicion);
        etiquetaPosicion.setText(
                etiquetaPosicion.getText().toString().concat(" ")
                        .concat( getTinaSeleccionada().getPosicion() )
        );

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        campoEscaner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validaTina( editable.toString() );
            }
        });

        getCocidas();
    }

    private void validaAsignacion(){
        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        TextView campoDescripcion = this.vista.findViewById(R.id.campoDescripcion);

        if( !campoEscaner.getText().equals("") &&
                !campoDescripcion.getText().equals( getResources().getString(R.string.mensajeErrorEscaneo) ) &&
                !campoDescripcion.getText().equals("") ){

                Cocida cocidaSeleccionada = getAsignacionCocida();
                if( cocidaSeleccionada == null ){
                    errorValidacion("Es necesario seleccionar una asignación cocida");
                }else{
                    iniciaProcesando();
                    getTinaSeleccionada().setLibre(false);
                    getTinaSeleccionada().setTurno(true);
                    if( UsuarioLogueado.getUsuarioLogueado(null).getTurno() == 1 ){
                        getTinaSeleccionada().setTurno(false);
                    }
                    getTinaSeleccionada().setSubtalla( cocidaSeleccionada.getSubtalla() );
                    getTinaSeleccionada().setTalla( cocidaSeleccionada.getTalla() );
                    getTinaSeleccionada().setGrupoEspecie( cocidaSeleccionada.getEspecie() );
                    getTinaSeleccionada().setEspecialidad( cocidaSeleccionada.getEspecialiad() );

                    guarda( cocidaSeleccionada.getId() );
                }

        }else{
            errorValidacion("Es necesario capturar una tina");
        }
    }

    private void guarda(int idCocida){
        TinaServicio tinaServicio = new TinaServicio();
        tinaServicio.setIdEspecialidad( getTinaSeleccionada().getEspecialidad().getIdEspecialidad() );
        tinaServicio.setIdPreseleccionPosicionTina( getTinaSeleccionada().getIdPreseleccionPosicionTina() );
        tinaServicio.setIdAsignacionCocida(idCocida);
        tinaServicio.setIdTina( getTinaSeleccionada().getTina().getIdTina() );
        tinaServicio.setIdEspecie( getTinaSeleccionada().getGrupoEspecie().getIdEspecie() );
        tinaServicio.setIdTalla( getTinaSeleccionada().getTalla().getIdTalla() );
        tinaServicio.setIdSubtalla( getTinaSeleccionada().getSubtalla().getIdSubtalla() );
        tinaServicio.setNpiezas( getTinaSeleccionada().getNpiezas() );
        tinaServicio.setPeso( getTinaSeleccionada().getPeso() );
        tinaServicio.setLibre( getTinaSeleccionada().getLibre() );
        tinaServicio.setTurno( getTinaSeleccionada().getTurno() );
        tinaServicio.setUsuario( UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().asignaTina(tinaServicio);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                RespuestaServicio respuesta = response.body();
                if( response.code() == 200 && respuesta.getCodigo() == 0 ){
                    resultadoAsignacion();
                }else{
                    terminaProcesando();
                    errorServicio("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<RespuestaServicio> call, Throwable t) {
                terminaProcesando();
                errorServicio("Error al conectar con el servidor");
            }
        });
    }

    public void resultadoAsignacion(){
        terminaProcesando();
        Fragment fragment = new Contenedor().newInstance(0);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private Cocida getAsignacionCocida(){
        for(Cocida cocida : this.cocidas){
            if( cocida.isSeleccionado() ){
                return cocida;
            }
        }
        return null;
    }

    public void errorValidacion(final String mensaje){
        final AlertDialog ventanaEmergente;
        AlertDialog.Builder builder = new AlertDialog.Builder( getContext() );
        View vistaAsignar = getLayoutInflater().inflate(R.layout.dialog_mensaje_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        ventanaEmergente = builder.create();
        ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaEmergente.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText(mensaje);

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton1);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ventanaEmergente.dismiss();
                    }
                });
            }
        });
        ventanaEmergente.show();
    }

    private void getCocidas(){
        Call<List<Cocida>> llamadaServicio = APIServicios.getConexion().getAsignacionCocida();
        llamadaServicio.enqueue(new Callback<List<Cocida>>() {
            @Override
            public void onResponse(Call<List<Cocida>> call, Response<List<Cocida>> response) {
                if(response.code() == 200){
                    resultadoCocidas( response.body() );
                }else{
                    terminaProcesando();
                    errorServicio("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<List<Cocida>> call, Throwable t) {
                terminaProcesando();
                errorServicio("Error al conectar con el servidor");
            }
        });
    }

    private void resultadoCocidas(List<Cocida> cocidas){
        if( isAdded() ){
            //if( !cocidas.isEmpty() ){
            this.cocidas = cocidas;
            this.vistaLista = this.vista.findViewById(R.id.listaTinas);
            this.vistaLista.setHasFixedSize(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
            this.vistaLista.setLayoutManager(layoutManager);

            AdaptadorCocida adaptador = new AdaptadorCocida(cocidas, this);
            this.vistaLista.setAdapter(adaptador);

            terminaProcesando();
            //}
        }
    }

    private void validaTina(String codigo){
        if( codigo.length() >= 15 ){
            iniciaProcesando();
            Call<TinaEscaneo> llamadaServicio = APIServicios.getConexion().getTina(codigo);
            llamadaServicio.enqueue(new Callback<TinaEscaneo>() {
                @Override
                public void onResponse(Call<TinaEscaneo> call, Response<TinaEscaneo> response) {
                    if(response.code() == 200){
                        resultadoEscaneoTina( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error interno del servidor");
                    }
                }

                @Override
                public void onFailure(Call<TinaEscaneo> call, Throwable t) {
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            });
        }else{
            TextView campoDescripcion = this.vista.findViewById(R.id.campoDescripcion);
            campoDescripcion.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoDescripcion.setTextColor( getResources().getColor(R.color.noValido) );
        }
    }

    public void resultadoEscaneoTina(TinaEscaneo resultadoTina){
        TextView campoDescripcion = this.vista.findViewById(R.id.campoDescripcion);

        if( resultadoTina.getIdTinaDes() != null ){
            campoDescripcion.setText( resultadoTina.getTinaDes() );
            campoDescripcion.setTextColor( getResources().getColor(R.color.siValido) );

            getTinaSeleccionada().getTina().setIdTina( Long.valueOf( resultadoTina.getIdTinaDes() ) );
            getTinaSeleccionada().getTina().setDescripcion( resultadoTina.getTinaDes() );
        } else{
            campoDescripcion.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoDescripcion.setTextColor( getResources().getColor(R.color.noValido) );
        }

        terminaProcesando();
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

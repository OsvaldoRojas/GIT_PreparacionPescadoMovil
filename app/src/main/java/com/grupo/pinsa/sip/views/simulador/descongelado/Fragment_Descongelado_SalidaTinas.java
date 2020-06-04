package com.grupo.pinsa.sip.views.simulador.descongelado;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorListaSalida;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.modelo.SalidaTina;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Descongelado_SalidaTinas extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View vista;

    private RecyclerView vistaLista;
    private SearchView campoBusqueda;
    private SwipeRefreshLayout actualizar;
    private ProgressBar barraProgreso;
    private AlertDialog ventanaError;

    private OnFragmentInteractionListener mListener;

    public Fragment_Descongelado_SalidaTinas() {
    }

    public static Fragment_Descongelado_SalidaTinas newInstance(String param1, String param2) {
        Fragment_Descongelado_SalidaTinas fragment = new Fragment_Descongelado_SalidaTinas();
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
        this.vista = inflater.inflate(R.layout.fragment_descongelado_salida_tina, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

        this.campoBusqueda = this.vista.findViewById(R.id.campoBusqueda);
        this.campoBusqueda.setIconifiedByDefault(false);
        this.campoBusqueda.setSubmitButtonEnabled(false);

        this.actualizar = this.vista.findViewById(R.id.actualizar);
        this.actualizar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actualizar.setRefreshing(true);
                getTinas();
            }
        });

        getTinas();
    }

    private void getTinas(){
        Call<List<SalidaTina>> llamadaServicio = APIServicios.getConexion().getSalidaTinas();
        llamadaServicio.enqueue(new Callback<List<SalidaTina>>() {
            @Override
            public void onResponse(Call<List<SalidaTina>> call, Response<List<SalidaTina>> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        resultadoTinas( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error al obtener las tinas");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SalidaTina>> call, Throwable t) {
                if( isAdded() ){
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            }
        });
    }

    public void guardaCambio(String idTina, boolean visible, boolean marcado){
        iniciaProcesando();
        JsonObject json = new JsonObject();
        json.addProperty("idTina", idTina);
        json.addProperty("visible", visible);
        json.addProperty("marcado", marcado);
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaSalidaTina(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                if( isAdded() ){
                    if(response.code() == 200){
                        getTinas();
                    }else{
                        terminaProcesando();
                        errorServicio( "Error al dar salida a la tina" );
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

    private void resultadoTinas(List<SalidaTina> listaTinas){
        if( isAdded() ){
            TextView sinResultado = this.vista.findViewById(R.id.sinResultados);
            if( !listaTinas.isEmpty() ){
                sinResultado.setVisibility(View.GONE);

                this.vistaLista = this.vista.findViewById(R.id.listaTinas);
                this.vistaLista.setHasFixedSize(true);
                this.vistaLista.setClickable(true);

                LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
                this.vistaLista.setLayoutManager(layoutManager);

                final AdaptadorListaSalida adaptadorListaSalida = new AdaptadorListaSalida(listaTinas, this);
                this.vistaLista.setAdapter(adaptadorListaSalida);

                this.campoBusqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String texto) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String texto) {
                        adaptadorListaSalida.filtro(texto);
                        return false;
                    }
                });
            }else{
                sinResultado.setVisibility(View.VISIBLE);
            }
            terminaProcesando();
        }
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
        this.barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        if( this.actualizar.isRefreshing() ){
            this.actualizar.setRefreshing(false);
        }
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

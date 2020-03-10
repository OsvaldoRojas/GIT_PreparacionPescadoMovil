package com.pinsa.simulador.descongelado;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.pinsa.simulador.Fragment_AsignaMecanico;
import com.pinsa.simulador.Fragment_DetalleOrden;
import com.pinsa.simulador.R;
import com.pinsa.simulador.adaptadores.AdaptadorOrdenMantenimiento;
import com.pinsa.simulador.conexion.APIServicios;
import com.pinsa.simulador.conexion.CerrarTiempoOrden;
import com.pinsa.simulador.utilerias.Catalogos;
import com.pinsa.simulador.utilerias.Constantes;
import com.pinsa.simulador.vista.ErrorServicio;
import com.pinsa.simulador.vista.OrdenMantenimiento;
import com.pinsa.simulador.vista.UsuarioLogueado;
import com.pinsa.simulador.vista.servicio.ListaOrdenMantenimientoServicio;
import com.pinsa.simulador.vista.servicio.RespuestaServicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Descongelado_OM.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Descongelado_OM#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Descongelado_OM extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View vista;

    private ListView listaVistaOrden;
    private SearchView campoBusqueda;
    private ProgressBar barraProgreso;
    private AlertDialog ventanaError;
    private SwipeRefreshLayout actualizar;

    private AdaptadorOrdenMantenimiento adaptadorOrden;
    private ListaOrdenMantenimientoServicio ordenSeleccionada;

    private OnFragmentInteractionListener mListener;

    public Fragment_Descongelado_OM() {
        // Required empty public constructor
    }

    public ListaOrdenMantenimientoServicio getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setOrdenSeleccionada(ListaOrdenMantenimientoServicio ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Descongelado_OM.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Descongelado_OM newInstance(String param1, String param2) {
        Fragment_Descongelado_OM fragment = new Fragment_Descongelado_OM();
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
        this.vista=inflater.inflate(R.layout.fragment_descongelado_om, container, false);

        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

        this.campoBusqueda = this.vista.findViewById(R.id.campoBusqueda);
        this.campoBusqueda.setIconifiedByDefault(false);
        this.campoBusqueda.setSubmitButtonEnabled(false);

        this.listaVistaOrden = this.vista.findViewById(R.id.listaOrden);
        this.listaVistaOrden.setTextFilterEnabled(true);

        this.actualizar = this.vista.findViewById(R.id.actualizar);
        this.actualizar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actualizar.setRefreshing(true);
                getOrdenesMantenimiento();
            }
        });

        if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.mecanico.getId() ){
            TextView tituloMecanico = this.vista.findViewById(R.id.tituloMecanico);
            tituloMecanico.setVisibility(View.GONE);
        }

        getOrdenesMantenimiento();
    }

    private void getOrdenesMantenimiento(){
        Call<List<ListaOrdenMantenimientoServicio>> llamadaServicio;
        if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.mecanico.getId() ){
            llamadaServicio = APIServicios.getConexion()
                    .getOrdenesMantenimiento(
                            Catalogos.getInstancia().getEtapaActual(),
                            (int) UsuarioLogueado.getUsuarioLogueado(null).getId_empleado()
                    );
        }else{
            llamadaServicio = APIServicios.getConexion()
                    .getOrdenesMantenimiento( Catalogos.getInstancia().getEtapaActual(), 0 );
        }

        llamadaServicio.enqueue(new Callback<List<ListaOrdenMantenimientoServicio>>() {
            @Override
            public void onResponse(Call<List<ListaOrdenMantenimientoServicio>> call, Response<List<ListaOrdenMantenimientoServicio>> response) {
                if(response.code() == 200){
                    resultadoServicioLista( response.body() );
                }else{
                    terminaProcesando();
                    errorServicio("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<List<ListaOrdenMantenimientoServicio>> call, Throwable t) {
                terminaProcesando();
                errorServicio("Error al conectar con el servidor");
            }
        });
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

    public void resultadoServicioLista(final List<ListaOrdenMantenimientoServicio> lista){
        if( isAdded() ){
            TextView sinResultado = this.vista.findViewById(R.id.sinResultados);
            if( !lista.isEmpty() ){
                sinResultado.setVisibility(View.GONE);

                this.adaptadorOrden = new AdaptadorOrdenMantenimiento( getContext(), lista );
                this.listaVistaOrden.setAdapter(this.adaptadorOrden);

                this.listaVistaOrden.setLongClickable(true);
                this.listaVistaOrden.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adaptador, View vista, int posicion, long id) {
                        setOrdenSeleccionada( lista.get(posicion) );
                        PopupMenu menu = new PopupMenu(getContext(), vista);
                        menu.getMenuInflater().inflate( R.menu.menu_lista_orden, menu.getMenu() );
                        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch ( item.getItemId() ){
                                    case R.id.asignarMecanico:
                                        asignaMecanico();
                                        return true;
                                    case R.id.cerrarTiempo:
                                        obtenDetalle(false);
                                        return true;
                                    case R.id.finalizaOrden:
                                        obtenDetalle(true);
                                        return true;
                                    case R.id.detalle:
                                        muestraDetalle();
                                        return true;
                                }
                                setOrdenSeleccionada(null);
                                return false;
                            }
                        });
                        if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() != Constantes.ROL.auxiliar.getId() ){
                            menu.getMenu().getItem(0).setVisible(false);
                        }
                        if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.mecanico.getId() ){
                            menu.getMenu().getItem(1).setVisible(false);
                        }else{
                            menu.getMenu().getItem(2).setVisible(false);
                        }
                        menu.show();
                        return false;
                    }
                });

                this.campoBusqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String texto) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String texto) {
                        adaptadorOrden.filtro(texto);
                        return false;
                    }
                });
            }else{
                sinResultado.setVisibility(View.VISIBLE);
            }

            terminaProcesando();
        }
    }

    public void errorServicio(ErrorServicio errorMensaje){
        String mensajeMostrar = errorMensaje.getMessage();
        if( errorMensaje.getMensaje() != null &&
                !errorMensaje.getMensaje().equalsIgnoreCase("") ){
            mensajeMostrar = errorMensaje.getMensaje();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_mensaje_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaError = builder.create();
        final String finalMensajeMostrar = mensajeMostrar;
        this.ventanaError.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaError.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText(finalMensajeMostrar);

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

    private void muestraDetalle(){
        Fragment fragment = new Fragment_DetalleOrden().newInstance( getOrdenSeleccionada().getIdOrdenMantenimiento() );
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private void asignaMecanico(){
        Fragment fragment = new Fragment_AsignaMecanico().newInstance( getOrdenSeleccionada().getIdOrdenMantenimiento() );
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private void cierraTiempo(OrdenMantenimiento orden){
        if( orden.getFechaFin() != null && !orden.getFechaFin().equals("") ){
            terminaProcesando();
            errorServicio("No es posible volver a cerrar la orden");
        }else{
            CerrarTiempoOrden cerrarTiempoOrden = new CerrarTiempoOrden(
                    this,
                    null,
                    getOrdenSeleccionada().getIdOrdenMantenimiento()
            );
            cerrarTiempoOrden.execute();
        }
        /*OrdenMantenimientoCerrarTiempo orden = new OrdenMantenimientoCerrarTiempo();
        orden.setIdOrden( getOrdenSeleccionada().getIdOrdenMantenimiento() );
        orden.setFecha(null);
        orden.setUsuario( UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().cierraTiempoOrdenMantenimiento(orden);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                RespuestaServicio respuesta = response.body();
                if( response.code() == 200 && respuesta.getCodigo() == 0 ){
                    getOrdenesMantenimiento();
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
        });*/
    }

    private void obtenDetalle(final boolean finaliza){
        iniciaProcesando();
        Call<OrdenMantenimiento> llamadaServicio = APIServicios.getConexion()
                .getDetalleOrden( getOrdenSeleccionada().getIdOrdenMantenimiento() );
        llamadaServicio.enqueue(new Callback<OrdenMantenimiento>() {
            @Override
            public void onResponse(Call<OrdenMantenimiento> call, Response<OrdenMantenimiento> response) {
                if(response.code() == 200){
                    if(finaliza){
                        finalizaOrden( response.body() );
                    }else{
                        cierraTiempo( response.body() );
                    }
                }else{
                    terminaProcesando();
                    errorServicio("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<OrdenMantenimiento> call, Throwable t) {
                terminaProcesando();
                errorServicio("Error al conectar con el servidor");
            }
        });
    }

    private void finalizaOrden(OrdenMantenimiento orden){
        JsonObject json = new JsonObject();
        json.addProperty("idOrdenMantenimiento", orden.getIdOrdenMantenimiento() );
        json.addProperty("idEmpleado", orden.getIdEmpleado() );
        json.addProperty("nombreEmpleado", orden.getNombreEmpleado() );
        json.addProperty("aPaternoEmpleado", orden.getaPaternoEmpleado() );
        json.addProperty("aMaternoEmpleado", orden.getaMaternoEmpleado() );
        json.addProperty("fechaInicio", orden.getFechaInicio() );
        json.addProperty("solucion", orden.getSolucion() );
        json.addProperty("finalizada", true);
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() );

        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().actualizaOrdenMantenimiento(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                RespuestaServicio respuesta = response.body();
                if( response.code() == 200 && respuesta.getCodigo() == 0 ){
                    getOrdenesMantenimiento();
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

    public void resultadoCierraTiempo(){
        getOrdenesMantenimiento();
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

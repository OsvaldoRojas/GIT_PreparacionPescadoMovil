package com.example.simulador_pescado.Atemperado;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.Utilerias.Catalogos;
import com.example.simulador_pescado.adaptadores.AdaptadorOrdenMantenimiento;
import com.example.simulador_pescado.conexion.CargaListaOrden;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.ListaOrdenMantenimientoServicio;
import com.example.simulador_pescado.vista.OrdenMantenimiento;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Atemperado_OM.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Atemperado_OM#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Atemperado_OM extends Fragment {
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

    public Fragment_Atemperado_OM() {
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
     * @return A new instance of fragment Fragment_Atemperado_OM.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Atemperado_OM newInstance(String param1, String param2) {
        Fragment_Atemperado_OM fragment = new Fragment_Atemperado_OM();
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
        this.vista = inflater.inflate(R.layout.fragment_atemperado_om, container, false);

        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        this.barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        iniciaProcesando();

        /*this.listaOrden = new ArrayList<>();
        this.listaOrden.add( new OrdenMantenimiento(1, "30/07/2019", "Montacargas", "", "Prueba descripción 1") );
        this.listaOrden.add( new OrdenMantenimiento(2, "30/07/2019", "Báscula", "", "Prueba descripción 2") );
        this.listaOrden.add( new OrdenMantenimiento(3, "30/07/2019", "Bnda", "", "Prueba descripción 3") );
        this.listaOrden.add( new OrdenMantenimiento(4, "30/07/2019", "Tina", "", "Prueba descripción 4") );
        this.listaOrden.add( new OrdenMantenimiento(5, "30/07/2019", "Recepción", "", "Prueba descripción 5") );
        this.listaOrden.add( new OrdenMantenimiento(6, "30/07/2019", "Estiba", "", "Prueba descripción 6") );
        this.listaOrden.add( new OrdenMantenimiento(7, "30/07/2019", "Tina sin talla", "", "Prueba descripción 7") );
        this.listaOrden.add( new OrdenMantenimiento(8, "30/07/2019", "Montacargas", "", "Prueba descripción 8") );
        this.listaOrden.add( new OrdenMantenimiento(9, "30/07/2019", "Recepción", "", "Prueba descripción 9") );
        this.listaOrden.add( new OrdenMantenimiento(10, "31/07/2019", "Tina", "", "Prueba descripción 10") );
        this.listaOrden.add( new OrdenMantenimiento(11, "31/07/2019", "Báscula", "", "Prueba descripción 11") );
        this.listaOrden.add( new OrdenMantenimiento(12, "31/07/2019", "Recepción", "", "Prueba descripción 12") );
        this.listaOrden.add( new OrdenMantenimiento(13, "31/07/2019", "Estiba", "", "Prueba descripción 13") );
        this.listaOrden.add( new OrdenMantenimiento(14, "31/07/2019", "Tina", "", "Prueba descripción 14") );
        this.listaOrden.add( new OrdenMantenimiento(15, "31/07/2019", "Montacargas", "", "Prueba descripción 15") );*/

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

        getOrdenesMantenimiento();
    }

    private void getOrdenesMantenimiento(){
        CargaListaOrden cargaListaOrden = new CargaListaOrden(Catalogos.getInstancia().getEtapaActual(), 0, this);
        cargaListaOrden.execute();
    }

    public void resultadoServicioLista(final List<ListaOrdenMantenimientoServicio> lista){
        if( isAdded() ){
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
                                    System.out.println("CERRAR TIEMPO....");
                                    return true;
                                case R.id.detalle:
                                    muestraDetalle();
                                    return true;
                            }
                            setOrdenSeleccionada(null);
                            return false;
                        }
                    });
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
        Fragment fragment = new Fragment_Atemperado_DetalleOrden().newInstance( getOrdenSeleccionada() );
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private void asignaMecanico(){
        Fragment fragment = new Fragment_Atemperado_AsignaMecanico().newInstance( getOrdenSeleccionada() );
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
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

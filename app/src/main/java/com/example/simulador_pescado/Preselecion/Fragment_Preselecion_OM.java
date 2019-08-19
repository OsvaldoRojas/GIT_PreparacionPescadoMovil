package com.example.simulador_pescado.Preselecion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.Utilerias.Utilerias;
import com.example.simulador_pescado.adaptadores.AdaptadorArtefacto;
import com.example.simulador_pescado.adaptadores.AdaptadorArtefactoLista;
import com.example.simulador_pescado.adaptadores.AdaptadorOrdenMantenimiento;
import com.example.simulador_pescado.conexion.ValidaGafete;
import com.example.simulador_pescado.vista.Artefacto;
import com.example.simulador_pescado.vista.ArtefactoLista;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.Gafete;
import com.example.simulador_pescado.vista.OrdenMantenimiento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Preselecion_OM.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Preselecion_OM#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Preselecion_OM extends Fragment {
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
    private AlertDialog ventanaEmergente;
    private AlertDialog ventanaError;

    private AdaptadorOrdenMantenimiento adaptadorOrden;
    private OrdenMantenimiento ordenSeleccionada;
    private List<OrdenMantenimiento> listaOrden = new ArrayList<>();
    private List<Artefacto> listaArtefactos = new ArrayList<>();
    private String[] listaCodigos = {"Código","001","002","003"};

    private Gafete gafeteEscaneado;

    private OnFragmentInteractionListener mListener;

    public Fragment_Preselecion_OM() {
        // Required empty public constructor
    }

    public Gafete getGafeteEscaneado() {
        return gafeteEscaneado;
    }

    public void setGafeteEscaneado(Gafete gafeteEscaneado) {
        this.gafeteEscaneado = gafeteEscaneado;
    }

    public OrdenMantenimiento getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setOrdenSeleccionada(OrdenMantenimiento ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Preselecion_OM.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Preselecion_OM newInstance(String param1, String param2) {
        Fragment_Preselecion_OM fragment = new Fragment_Preselecion_OM();
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
        vista=inflater.inflate(R.layout.fragment_fragment__preselecion__om, container, false);

        iniciaComponentes();
        return vista;
    }

    private void iniciaComponentes(){
        this.listaArtefactos = new ArrayList<>();
        this.listaArtefactos.add( new Artefacto(0, "Artefacto") );
        this.listaArtefactos.add( new Artefacto(1, "Artefacto 1") );
        this.listaArtefactos.add( new Artefacto(2, "Artefacto 2") );
        this.listaArtefactos.add( new Artefacto(3, "Artefacto 3") );

        this.listaOrden = new ArrayList<>();
        this.listaOrden.add( new OrdenMantenimiento(1, "30/07/2020", "Montacargas", "", "Prueba descripción 1") );
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
        this.listaOrden.add( new OrdenMantenimiento(15, "31/07/2019", "Montacargas", "", "Prueba descripción 15") );

        setGafeteEscaneado(null);

        this.campoBusqueda = this.vista.findViewById(R.id.campoBusqueda);
        this.campoBusqueda.setIconifiedByDefault(false);
        this.campoBusqueda.setSubmitButtonEnabled(false);
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

        this.adaptadorOrden = new AdaptadorOrdenMantenimiento( getContext(), this.listaOrden );
        this.listaVistaOrden = this.vista.findViewById(R.id.listaOrden);
        this.listaVistaOrden.setAdapter(this.adaptadorOrden);
        this.listaVistaOrden.setTextFilterEnabled(true);

        this.listaVistaOrden.setLongClickable(true);
        this.listaVistaOrden.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adaptador, View vista, int posicion, long id) {
                setOrdenSeleccionada( listaOrden.get(posicion) );
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
    }

    private void muestraDetalle(){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_detalle_orden, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaEmergente = builder.create();
        this.ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button botonCancelar = ventanaEmergente.findViewById(R.id.boton1);
                botonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        limpiarCampos();
                        setOrdenSeleccionada(null);
                        ventanaEmergente.dismiss();
                    }
                });

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton2);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        limpiarCampos();
                        setOrdenSeleccionada(null);
                        ventanaEmergente.dismiss();
                    }
                });

                TextView etiquetaFecha = ventanaEmergente.findViewById(R.id.etiquetaFecha);
                etiquetaFecha.setText( Utilerias.fechaActual() );

                TextView etiquetaFolio = ventanaEmergente.findViewById(R.id.etiquetaFolio);
                etiquetaFolio.setText( String.valueOf( getOrdenSeleccionada().getFolio() ) );

                TextView etiquetaEquipo = ventanaEmergente.findViewById(R.id.etiquetaEquipo);
                etiquetaEquipo.setText( getOrdenSeleccionada().getEquipo() );

                TextView etiquetaDescripcion = ventanaEmergente.findViewById(R.id.etiquetaDescripcion);
                etiquetaDescripcion.setText( getOrdenSeleccionada().getDescripcion() );

                final EditText campoCantidad = ventanaEmergente.findViewById(R.id.campoCantidad);

                final Spinner campoCodigo = ventanaEmergente.findViewById(R.id.campoCodigo);
                campoCodigo.setAdapter( new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listaCodigos) );

                final Spinner campoArtefacto = ventanaEmergente.findViewById(R.id.campoArtefacto);
                campoArtefacto.setAdapter( new AdaptadorArtefacto( getContext(), listaArtefactos ) );

                final AdaptadorArtefactoLista adaptadorArtefactoLista = new AdaptadorArtefactoLista(
                        getContext(),
                        getOrdenSeleccionada().getListaArtefactos()
                );
                final ListView listaArtefactosVista = ventanaEmergente.findViewById(R.id.listaArtefactos);
                listaArtefactosVista.setAdapter(adaptadorArtefactoLista);
                Utilerias.setAlturaLista(listaArtefactosVista, 0);

                final LinearLayout contenedorEncabezados = ventanaEmergente.findViewById(R.id.contenedorEncabezados);
                if( !getOrdenSeleccionada().getListaArtefactos().isEmpty() ){
                    contenedorEncabezados.setVisibility(View.VISIBLE);
                }

                final ScrollView vistaScroll = ventanaEmergente.findViewById(R.id.vistaGeneral);

                FloatingActionButton botonAgregar = ventanaEmergente.findViewById(R.id.botonAgregar);
                botonAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        contenedorEncabezados.setVisibility(View.VISIBLE);
                        getOrdenSeleccionada().getListaArtefactos().add(
                                new ArtefactoLista(
                                        (Artefacto) campoArtefacto.getSelectedItem(),
                                        Integer.valueOf( campoCantidad.getText().toString() ),
                                        campoCodigo.getSelectedItem().toString()
                                )
                        );
                        adaptadorArtefactoLista.notifyDataSetChanged();
                        Utilerias.setAlturaLista(listaArtefactosVista, 0);

                        vistaScroll.post(new Runnable() {
                            public void run() {
                                vistaScroll.fullScroll(vistaScroll.FOCUS_DOWN);
                            }
                        });

                        campoCantidad.setText("");
                        campoArtefacto.setSelection(0);
                        campoCodigo.setSelection(0);
                    }
                });
            }
        });
        this.ventanaEmergente.show();
    }

    private void limpiarCampos(){
        OrdenMantenimiento orden = getOrdenSeleccionada();
        List<ArtefactoLista> lista = new ArrayList<>();
        for( ArtefactoLista artefactoLista : orden.getListaArtefactos() ){
            if( artefactoLista.getArtefacto().getDescripcion().equalsIgnoreCase("Artefacto") ){
                lista.add(artefactoLista);
            }
        }

        for(ArtefactoLista artefactoLista : lista){
            orden.getListaArtefactos().remove(artefactoLista);
        }
    }

    private void asignaMecanico(){
        Fragment fragment = new AsignarMecanico().newInstance( getOrdenSeleccionada() );
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
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

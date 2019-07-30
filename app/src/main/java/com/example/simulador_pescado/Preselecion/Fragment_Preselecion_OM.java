package com.example.simulador_pescado.Preselecion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.adaptadores.AdaptadorOrdenMantenimiento;
import com.example.simulador_pescado.vista.OrdenMantenimiento;

import java.util.ArrayList;
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
    View vista;

    private ListView listaVistaOrden;
    private SearchView campoBusqueda;

    private AdaptadorOrdenMantenimiento adaptadorOrden;
    private List<OrdenMantenimiento> listaOrden = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public Fragment_Preselecion_OM() {
        // Required empty public constructor
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
        this.listaOrden = new ArrayList<>();
        this.listaOrden.add( new OrdenMantenimiento(8888, "01/05/2020", "Montacargas", "Pedro Octavio Vazquez Jalomo ") );
        this.listaOrden.add( new OrdenMantenimiento(9999, "01/05/2020", "Báscula", "Juan Lopez") );
        this.listaOrden.add( new OrdenMantenimiento(7777, "01/05/2020", "Tina", "Carlos Ramirez") );

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
        registerForContextMenu(this.listaVistaOrden);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_lista_orden, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch ( item.getItemId() ){
            case R.id.asignarMecanico:
                System.out.println("ASIGNAR MECANICO......");
                return true;
            case R.id.cerrarTiempo:
                System.out.println("CERRAR TIEMPO....");
                return true;
            case R.id.detalle:
                System.out.println("DETALLE.....");
                return true;
                default:
                    return super.onContextItemSelected(item);
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

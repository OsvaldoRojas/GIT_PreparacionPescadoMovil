package com.pinsa.simulador.descongelado;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pinsa.simulador.R;
import com.pinsa.simulador.adaptadores.AdaptadorListaSalida;
import com.pinsa.simulador.vista.SalidaTina;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Descongelado_SalidaTinas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View vista;

    private RecyclerView vistaLista;

    private OnFragmentInteractionListener mListener;

    public Fragment_Descongelado_SalidaTinas() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Descongelado_TiempoMuerto.
     */
    // TODO: Rename and change types and number of parameters
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
        this.vistaLista = this.vista.findViewById(R.id.listaTinas);
        this.vistaLista.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
        this.vistaLista.setLayoutManager(layoutManager);

        SalidaTina tina1 = new SalidaTina();
        tina1.setTina("23");
        tina1.setPosicion("A - 34");
        tina1.setHora("15:46");
        SalidaTina tina2 = new SalidaTina();
        tina2.setTina("35");
        tina2.setPosicion("D - 16");
        tina2.setHora("16:34");

        List<SalidaTina> lista = new ArrayList<>();
        lista.add(tina1);
        lista.add(tina2);
        AdaptadorListaSalida adaptadorListaSalida = new AdaptadorListaSalida(lista);
        this.vistaLista.setAdapter(adaptadorListaSalida);
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

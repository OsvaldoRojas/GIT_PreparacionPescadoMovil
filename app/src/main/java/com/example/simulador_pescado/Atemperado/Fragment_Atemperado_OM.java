package com.example.simulador_pescado.Atemperado;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simulador_pescado.R;
import com.example.simulador_pescado.adaptadores.AdaptadorRecycler;
import com.example.simulador_pescado.clases.ParametrosRecycler;

import java.util.ArrayList;

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
    View vista;
    ArrayList<ParametrosRecycler> listaPersonajes;
    RecyclerView recycler_per;
    TextView folio,fecha,artefacto,mecanico;

    private OnFragmentInteractionListener mListener;

    public Fragment_Atemperado_OM() {
        // Required empty public constructor
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
        vista=inflater.inflate(R.layout.fragment_fragment__atemperado__om, container, false);
        ordenar();
        return vista;
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
    private void llenarDatos() {

        listaPersonajes.add(new ParametrosRecycler("166","12/07/2019","maquinaria","jorge luis"));
        listaPersonajes.add(new ParametrosRecycler("2234","7/03/2019","tina","jorge "));
        listaPersonajes.add(new ParametrosRecycler("34","2/03/2019","maquinaria","jorge luis"));


    }

    public  void ordenar(){
        listaPersonajes= new ArrayList<>();
        recycler_per= vista.findViewById(R.id.recycler_Atemperado);



            //recycler_per.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_per.setLayoutManager(new GridLayoutManager(vista.getContext(),1));


        llenarDatos();
        AdaptadorRecycler adaptador22= new AdaptadorRecycler(listaPersonajes);
        adaptador22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "se seleciono  "+listaPersonajes.
                        get(recycler_per.getChildAdapterPosition(view)).getMecanico(), Toast.LENGTH_SHORT).show();
                        folio=vista.findViewById(R.id.recycler_Folio);
                        fecha=vista.findViewById(R.id.recycler_Fecha);
                        artefacto=vista.findViewById(R.id.recycler_Artefacto);
                        mecanico=vista.findViewById(R.id.recycler_Mecanico);
                        folio.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.selecion));
                        fecha.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.selecion));
                        artefacto.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.selecion));
                        mecanico.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.selecion));



            }
        });
        recycler_per.setAdapter(adaptador22);
    }

}

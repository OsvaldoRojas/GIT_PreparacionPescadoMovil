package com.example.simulador_pescado.Contenedores;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simulador_pescado.Atemperado.Fragment_Atemperado_OM;
import com.example.simulador_pescado.Atemperado.Fragment_Atemperado_Plan;
import com.example.simulador_pescado.Atemperado.Fragment_Atemperado_TiempoMuerto;
import com.example.simulador_pescado.R;
import com.example.simulador_pescado.adaptadores.SesionesAdapter;
import com.example.simulador_pescado.clases.Utilidades;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Contenedor_Atemperado.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Contenedor_Atemperado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Contenedor_Atemperado extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    private TabLayout pestañas;
    private AppBarLayout appbar;
    private ViewPager viewPager;

    private OnFragmentInteractionListener mListener;

    public Contenedor_Atemperado() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Contenedor_Atemperado.
     */
    // TODO: Rename and change types and number of parameters
    public static Contenedor_Atemperado newInstance(String param1, String param2) {
        Contenedor_Atemperado fragment = new Contenedor_Atemperado();
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
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_contenedor__atemperado, container, false);
        if(Utilidades.rotacion==0){
           View parent= (View) container.getParent();
           if (appbar==null){
               appbar=parent.findViewById(R.id.appbar);
               pestañas= new TabLayout(getActivity());
               appbar.addView(pestañas);
               viewPager=vista.findViewById(R.id.ViewPagerConte);
               llenar(viewPager);
               viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                   @Override
                   public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                       super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                   }
               });
               pestañas.setupWithViewPager(viewPager);
           }
        }else{
            Utilidades.rotacion=1;
        }
        return vista;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (Utilidades.rotacion==0){
            appbar.removeView(pestañas);
        }
    }

    private void llenar(ViewPager viewPager) {
        SesionesAdapter adapter= new SesionesAdapter(getFragmentManager());
        adapter.addfragments(new Fragment_Atemperado_Plan(),"Diseño del plan");
        adapter.addfragments(new Fragment_Atemperado_TiempoMuerto(),"Tiempo muerto");
        adapter.addfragments(new Fragment_Atemperado_OM(),"OM");
       viewPager.setAdapter(adapter);
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
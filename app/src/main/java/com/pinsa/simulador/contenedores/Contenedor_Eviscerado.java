package com.pinsa.simulador.contenedores;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.pinsa.simulador.Fragment_CreaOrdenMantenimiento;
import com.pinsa.simulador.R;
import com.pinsa.simulador.adaptadores.SesionesAdapter;
import com.pinsa.simulador.eviscerado.Fragment_Eviscerado_OM;
import com.pinsa.simulador.eviscerado.Fragment_Eviscerado_Plan;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class Contenedor_Eviscerado extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;

    private View vista;

    private TabLayout pestañas;
    private ViewPager viewPager;
    private AppBarLayout appbar;
    private OnFragmentInteractionListener mListener;

    public Contenedor_Eviscerado() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment Contenedor_Descongelado.
     */
    // TODO: Rename and change types and number of parameters
    public static Contenedor_Eviscerado newInstance(int param1) {
        Contenedor_Eviscerado fragment = new Contenedor_Eviscerado();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }else{
            mParam1 = 0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.fragment_contenedor_eviscerado, container, false);

        View parent= (View) container.getParent();
        if (appbar==null){
            appbar= parent.findViewById(R.id.appbar);
            pestañas = new TabLayout(getActivity());
            appbar.addView(pestañas);
            viewPager = vista.findViewById(R.id.ViewPagerConte);
            llenarview(viewPager);
            viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            });
            pestañas.setupWithViewPager(viewPager);
        }
        iniciaTab();
        pestañas.setTabRippleColor( getResources().getColorStateList(R.color.colorAccent) );
        pestañas.setTabMode(TabLayout.MODE_SCROLLABLE);
        pestañas.setBackgroundColor( getResources().getColor(R.color.colorPrimary) );
        pestañas.setSelectedTabIndicatorColor( getResources().getColor(R.color.blanco) );
        pestañas.getTabAt(this.mParam1).select();

        return vista;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appbar.removeView(pestañas);
    }

    private void llenarview(ViewPager viewPager) {
        SesionesAdapter adatartes2= new SesionesAdapter(getFragmentManager());
        adatartes2.addfragments(new Fragment_Eviscerado_Plan(), getResources().getString(R.string.Preselecion_tinas));
        adatartes2.addfragments(new Fragment_CreaOrdenMantenimiento(), getResources().getString(R.string.TiempoMuerto));
        adatartes2.addfragments(new Fragment_Eviscerado_OM(), getResources().getString(R.string.OM));
        viewPager.setAdapter(adatartes2);
    }

    private void iniciaTab(){
        TextView textView1 = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        textView1.setText( getResources().getString(R.string.Preselecion_tinas) );
        pestañas.getTabAt(0).setCustomView(textView1);
        TextView textView2 = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        textView2.setText( getResources().getString(R.string.TiempoMuerto) );
        pestañas.getTabAt(1).setCustomView(textView2);
        TextView textView3 = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        textView3.setText( getResources().getString(R.string.OM) );
        pestañas.getTabAt(2).setCustomView(textView3);
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

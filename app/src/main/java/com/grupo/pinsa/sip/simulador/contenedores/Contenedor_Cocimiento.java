package com.grupo.pinsa.sip.simulador.contenedores;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.grupo.pinsa.sip.simulador.orden.Fragment_CreaOrdenMantenimiento;
import com.grupo.pinsa.sip.simulador.orden.Fragment_OrdenMantenimiento;
import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.adaptadores.SesionesAdapter;
import com.grupo.pinsa.sip.simulador.cocimiento.Fragment_Cocimiento_Plan;

public class Contenedor_Cocimiento extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private int mParam1;

    private View vista;

    private TabLayout pestañas;
    private ViewPager viewPager;
    private AppBarLayout appbar;
    private OnFragmentInteractionListener mListener;

    public Contenedor_Cocimiento() {
    }

    public static Contenedor_Cocimiento newInstance(int param1) {
        Contenedor_Cocimiento fragment = new Contenedor_Cocimiento();
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
        vista=inflater.inflate(R.layout.fragment_contenedor, container, false);

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
        adatartes2.addfragments(new Fragment_Cocimiento_Plan(), getResources().getString(R.string.Preselecion_tinas));
        adatartes2.addfragments(new Fragment_CreaOrdenMantenimiento(), getResources().getString(R.string.TiempoMuerto));
        adatartes2.addfragments(new Fragment_OrdenMantenimiento(), getResources().getString(R.string.OM));
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

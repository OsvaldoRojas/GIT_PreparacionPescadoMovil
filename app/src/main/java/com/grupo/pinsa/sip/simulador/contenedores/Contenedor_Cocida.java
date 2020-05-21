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
import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.adaptadores.SesionesAdapter;
import com.grupo.pinsa.sip.simulador.estatusCocedor.Fragment_Cocida;
import com.grupo.pinsa.sip.simulador.modelo.Cocedor;

import java.io.Serializable;
import java.util.List;

public class Contenedor_Cocida extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int mParam1;
    private Serializable mParam2;

    private View vista;
    private TabLayout pestañas;
    private ViewPager viewPager;
    private AppBarLayout appbar;
    private OnFragmentInteractionListener mListener;

    public Contenedor_Cocida() {
    }

    public static Contenedor_Cocida newInstance(int param1, Serializable param2) {
        Contenedor_Cocida fragment = new Contenedor_Cocida();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getSerializable(ARG_PARAM2);
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
        SesionesAdapter adatartes = new SesionesAdapter(getFragmentManager());
        List<Cocedor> listaCocedores = (List<Cocedor>) this.mParam2;
        for(Cocedor cocedor : listaCocedores){
            if( !cocedor.getCompensacion() ){
                cocedor.setTotalCocidas( listaCocedores.size() );
                Fragment cocidaInicial = Fragment_Cocida.newInstance(cocedor);
                adatartes.addfragments(cocidaInicial, getResources().getString(R.string.cocidaInicial));
                break;
            }
        }

        for(Cocedor cocedor : listaCocedores){
            if( cocedor.getCompensacion() ){
                cocedor.setTotalCocidas( listaCocedores.size() );
                Fragment cocidaCompensacion = Fragment_Cocida.newInstance(cocedor);
                adatartes.addfragments(cocidaCompensacion, getResources().getString(R.string.compensacion));
            }
        }
        viewPager.setAdapter(adatartes);
    }

    private void iniciaTab(){
        TextView textView1 = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        textView1.setText( getResources().getString(R.string.cocidaInicial) );
        pestañas.getTabAt(0).setCustomView(textView1);

        for( int i = 1; i < pestañas.getTabCount(); i++ ){
            TextView textView2 = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
            textView2.setText( getResources().getString(R.string.compensacion) );
            pestañas.getTabAt(i).setCustomView(textView2);
        }
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

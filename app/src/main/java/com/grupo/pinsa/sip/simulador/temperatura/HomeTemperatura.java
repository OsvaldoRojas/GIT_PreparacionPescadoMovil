package com.grupo.pinsa.sip.simulador.temperatura;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.home;
import com.grupo.pinsa.sip.simulador.utilerias.Constantes;
import com.grupo.pinsa.sip.simulador.utilerias.Utilerias;

public class HomeTemperatura extends Fragment {

    private View vista;

    private OnFragmentInteractionListener mListener;

    public HomeTemperatura() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_home_temperatura, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        Button botonPreseleccion = this.vista.findViewById(R.id.botonPreseleccion);
        botonPreseleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaTemperatura(Constantes.ETAPA.preseleccion))
                        .commit();
            }
        });

        Button botonAtemperado = this.vista.findViewById(R.id.botonAtemperado);
        botonAtemperado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaTemperatura(Constantes.ETAPA.atemperado))
                        .commit();
            }
        });

        Button botonEviscerado = this.vista.findViewById(R.id.botonEviscerado);
        botonEviscerado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaTemperatura(Constantes.ETAPA.eviscerado))
                        .commit();
            }
        });

        Button botonCocimiento = this.vista.findViewById(R.id.botonCocimiento);
        botonCocimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaTemperatura(Constantes.ETAPA.cocimiento))
                        .commit();
            }
        });

        Button botonModulos = this.vista.findViewById(R.id.botonModulos);
        botonModulos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaTemperatura(Constantes.ETAPA.modulos))
                        .commit();
            }
        });

        Button botonVolver = this.vista.findViewById(R.id.botonVolver);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaTemperatura(Constantes.ETAPA.temperatura))
                        .commit();
            }
        });
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

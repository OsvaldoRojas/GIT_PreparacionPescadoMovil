package com.grupo.pinsa.sip.simulador;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.utilerias.Constantes;
import com.grupo.pinsa.sip.simulador.utilerias.Utilerias;

public class home extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View vista;

    private OnFragmentInteractionListener mListener;

    public home() {
        // Required empty public constructor
    }

    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
        this.vista = inflater.inflate(R.layout.fragment_home, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        Button botonPreseleccion = this.vista.findViewById(R.id.botonPreseleccion);
        botonPreseleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.preseleccion))
                        .commit();
            }
        });

        Button botonAtemperado = this.vista.findViewById(R.id.botonAtemperado);
        botonAtemperado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.atemperado))
                        .commit();
            }
        });

        Button botonDescongelado = this.vista.findViewById(R.id.botonDescongelado);
        botonDescongelado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.descongelado))
                        .commit();
            }
        });

        Button botonMovimiento = this.vista.findViewById(R.id.botonMovimiento);
        botonMovimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.movimiento))
                        .commit();
            }
        });

        Button botonControl = this.vista.findViewById(R.id.botonControl);
        botonControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.control))
                        .commit();
            }
        });

        Button botonEviscerado = this.vista.findViewById(R.id.botonEviscerado);
        botonEviscerado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.eviscerado))
                        .commit();
            }
        });

        Button botonEmparrillado = this.vista.findViewById(R.id.botonEmparrillado);
        botonEmparrillado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.emparrillado))
                        .commit();
            }
        });

        Button botonCocimiento = this.vista.findViewById(R.id.botonCocimiento);
        botonCocimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.cocimiento))
                        .commit();
            }
        });

        Button botonModulos = this.vista.findViewById(R.id.botonModulos);
        botonModulos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.modulos))
                        .commit();
            }
        });

        Button botonTemperatura = this.vista.findViewById(R.id.botonTemperatura);
        botonTemperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.temperatura))
                        .commit();
            }
        });

        Button botonEstatus = this.vista.findViewById(R.id.botonEstatus);
        botonEstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.estatus))
                        .commit();
            }
        });

        Button botonLavado = this.vista.findViewById(R.id.botonLavado);
        botonLavado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.lavado))
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

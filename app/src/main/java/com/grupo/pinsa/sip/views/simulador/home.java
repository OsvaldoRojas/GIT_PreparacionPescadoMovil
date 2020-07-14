package com.grupo.pinsa.sip.views.simulador;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.utilerias.Constantes;
import com.grupo.pinsa.sip.views.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;

public class home extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View vista;

    private OnFragmentInteractionListener mListener;

    public home() {
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
        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.auxiliar.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.mecanico.getId() ){
            Button botonPreseleccion = this.vista.findViewById(R.id.botonPreseleccion);
            botonPreseleccion.setVisibility(View.VISIBLE);
            botonPreseleccion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.preseleccion))
                            .commit();
                }
            });
        }

        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.auxiliar.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.mecanico.getId() ){
            Button botonAtemperado = this.vista.findViewById(R.id.botonAtemperado);
            botonAtemperado.setVisibility(View.VISIBLE);
            botonAtemperado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.atemperado))
                            .commit();
                }
            });
        }

        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.auxiliar.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.mecanico.getId() ){
            Button botonDescongelado = this.vista.findViewById(R.id.botonDescongelado);
            botonDescongelado.setVisibility(View.VISIBLE);
            botonDescongelado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.descongelado))
                            .commit();
                }
            });
        }

        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.auxiliar.getId() ){
            Button botonMovimiento = this.vista.findViewById(R.id.botonMovimiento);
            botonMovimiento.setVisibility(View.VISIBLE);
            botonMovimiento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.movimiento))
                            .commit();
                }
            });
        }

        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.auxiliar.getId() ){
            Button botonControl = this.vista.findViewById(R.id.botonControl);
            botonControl.setVisibility(View.VISIBLE);
            botonControl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.control))
                            .commit();
                }
            });
        }

        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.auxiliar.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.mecanico.getId() ){
            Button botonEviscerado = this.vista.findViewById(R.id.botonEviscerado);
            botonEviscerado.setVisibility(View.VISIBLE);
            botonEviscerado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.eviscerado))
                            .commit();
                }
            });
        }

        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.auxiliar.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.mecanico.getId() ){
            Button botonEmparrillado = this.vista.findViewById(R.id.botonEmparrillado);
            botonEmparrillado.setVisibility(View.VISIBLE);
            botonEmparrillado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.emparrillado))
                            .commit();
                }
            });
        }

        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.auxiliar.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.mecanico.getId() ){
            Button botonCocimiento = this.vista.findViewById(R.id.botonCocimiento);
            botonCocimiento.setVisibility(View.VISIBLE);
            botonCocimiento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.cocimiento))
                            .commit();
                }
            });
        }

        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.auxiliar.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.mecanico.getId() ){
            Button botonModulos = this.vista.findViewById(R.id.botonModulos);
            botonModulos.setVisibility(View.VISIBLE);
            botonModulos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.enfriamiento))
                            .commit();
                }
            });
        }

        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.auxiliar.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.mecanico.getId() ){
            Button botonTemperatura = this.vista.findViewById(R.id.botonTemperatura);
            botonTemperatura.setVisibility(View.VISIBLE);
            botonTemperatura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.temperatura))
                            .commit();
                }
            });
        }

        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.auxiliar.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.mecanico.getId() ){
            Button botonEstatus = this.vista.findViewById(R.id.botonEstatus);
            botonEstatus.setVisibility(View.VISIBLE);
            botonEstatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.estatus))
                            .commit();
                }
            });
        }

        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.auxiliar.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.mecanico.getId() ){
            Button botonLavado = this.vista.findViewById(R.id.botonLavado);
            botonLavado.setVisibility(View.VISIBLE);
            botonLavado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, Utilerias.navegaInicio(Constantes.ETAPA.lavadoCarro))
                            .commit();
                }
            });
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

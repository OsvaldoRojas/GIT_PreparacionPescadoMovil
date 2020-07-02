package com.grupo.pinsa.sip.views.simulador.modulos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.contenedores.Contenedor_Modulos;
import com.grupo.pinsa.sip.views.simulador.modelo.Modulo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class Fragment_Vista_Panoramica extends Fragment {

    private View vista;
    private OnFragmentInteractionListener mListener;

    public Fragment_Vista_Panoramica() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_vista_panoramica, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        Intent vistaPanoramica = new Intent(getContext(), ActividadVistaPanoramica.class);
        startActivityForResult(vistaPanoramica, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if( resultCode == RESULT_OK ){
           String navegacion = data.getStringExtra("navegacion");
           if( navegacion.equalsIgnoreCase("volver") ){
               Fragment fragment = new Contenedor_Modulos().newInstance(0);
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
           }else{
               if( navegacion.equalsIgnoreCase("detalle") ){
                   Modulo modulo = (Modulo) data.getSerializableExtra("modulo");
                   Fragment fragment = new Fragment_Detalle_Modulo().newInstance(modulo, true);
                   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
               }
           }
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

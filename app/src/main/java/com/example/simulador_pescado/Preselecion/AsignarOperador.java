package com.example.simulador_pescado.Preselecion;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Contenedores.Contenedor;
import com.example.simulador_pescado.R;
import com.example.simulador_pescado.conexion.ValidaGafete;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.Gafete;
import com.example.simulador_pescado.vista.OperadorBascula;
import com.example.simulador_pescado.vista.Tina;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AsignarOperador extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Serializable mParam1;
    private Serializable mParam2;

    private View vista;

    private String fechaActual;
    private AlertDialog ventanaError;

    private OperadorBascula operadorSeleccionado;
    private List<Tina> listaTinas;

    private OnFragmentInteractionListener mListener;

    public AsignarOperador() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment Fragment_Preselecion_Tinas.
     */
    // TODO: Rename and change types and number of parameters
    public static AsignarOperador newInstance(Serializable param1, Serializable param2) {
        AsignarOperador fragment = new AsignarOperador();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista=inflater.inflate(R.layout.fragment_asignar_operador, container, false);

        iniciaComponentes();
        return this.vista;
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

    public OperadorBascula getOperadorSeleccionado() {
        return operadorSeleccionado;
    }

    public void setOperadorSeleccionado(OperadorBascula operadorSeleccionado) {
        this.operadorSeleccionado = operadorSeleccionado;
    }

    private void iniciaComponentes(){
        setOperadorSeleccionado( (OperadorBascula) this.mParam1);
        this.listaTinas = (List<Tina>) this.mParam2;

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaActual = formatoFecha.format( new Date() );

        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor().newInstance(0);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText(this.fechaActual);

        TextView etiquetaPosicion = this.vista.findViewById(R.id.etiquetaPosicion);
        etiquetaPosicion.setText( getEtiquetaOperador( getOperadorSeleccionado().getIdEstacion() ) );

        TextView etiquetaBascula = this.vista.findViewById(R.id.etiquetaBascula);
        etiquetaBascula.setText( getEtiquetaBasculaOperador( getOperadorSeleccionado().getIdEstacion() ) );

        TextView etiquetaTinaPrincipal = this.vista.findViewById(R.id.etiquetaTinaPrincipal);
        etiquetaTinaPrincipal.setText( getTinaPrincipalOperador( getOperadorSeleccionado().getIdEstacion() ) );

        TextView etiquetaSubtallaPrincipal = this.vista.findViewById(R.id.etiquetaSubtallaPrincipal);
        etiquetaSubtallaPrincipal.setText( getSubtallaPrincipalOperador( getOperadorSeleccionado().getIdEstacion() ) );

        TextView etiquetaTinaSecundaria = this.vista.findViewById(R.id.etiquetaTinaSecundaria);
        etiquetaTinaSecundaria.setText( getTinaSecundariaOperador( getOperadorSeleccionado().getIdEstacion() ) );

        TextView etiquetaSubtallaSecundaria = this.vista.findViewById(R.id.etiquetaSubtallaSecundaria);
        etiquetaSubtallaSecundaria.setText( getSubtallaSecundariaOperador( getOperadorSeleccionado().getIdEstacion() ) );

        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        campoEscaner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validaGafete( editable.toString() );
            }
        });
    }

    private void validaGafete(String codigo){
        if( codigo.length() >= 7 ){
            iniciaProcesando();
            ValidaGafete validaGafete = new ValidaGafete(this, codigo);
            validaGafete.execute();
        }else{
            EditText campoNombre = this.vista.findViewById(R.id.campoNombre);
            campoNombre.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoNombre.setTextColor( getResources().getColor(R.color.noValido) );
        }
    }

    public void resultadoEscaneoGafete(Gafete resultadoGafete){
        EditText campoNombre = this.vista.findViewById(R.id.campoNombre);

        if( resultadoGafete.getResultado().equalsIgnoreCase("YES") ){
            campoNombre.setText( resultadoGafete.getEmpleado().getNom_trab() );
            campoNombre.setTextColor( getResources().getColor(R.color.siValido) );

            getOperadorSeleccionado().getEmpleado()
                    .setClaveEmpleado( resultadoGafete.getEmpleado().getCla_trab() );
            getOperadorSeleccionado().getEmpleado()
                    .setNombre( resultadoGafete.getEmpleado().getNom_trab() );
            getOperadorSeleccionado().getEmpleado()
                    .setApellidoPaterno( resultadoGafete.getEmpleado().getAp_paterno() );
            getOperadorSeleccionado().getEmpleado()
                    .setApellidoMaterno( resultadoGafete.getEmpleado().getAp_materno() );
        }else{
            campoNombre.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoNombre.setTextColor( getResources().getColor(R.color.noValido) );
        }

        terminaProcesando();
    }

    public void errorEscaneoGafete(ErrorServicio errorMensaje){
        String mensajeMostrar = errorMensaje.getMessage();
        if( errorMensaje.getMensaje() != null &&
                !errorMensaje.getMensaje().equalsIgnoreCase("") ){
            mensajeMostrar = errorMensaje.getMensaje();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_mensaje_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaError = builder.create();
        final String finalMensajeMostrar = mensajeMostrar;
        this.ventanaError.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaError.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText(finalMensajeMostrar);

                Button botonAceptar = ventanaError.findViewById(R.id.boton1);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ventanaError.dismiss();
                    }
                });
            }
        });
        this.ventanaError.show();
    }

    private String getEtiquetaOperador(int posicion){
        switch (posicion){
            case 1: return "A5";
            case 2: return "A4";
            case 3: return "A3";
            case 4: return "A2";
            case 5: return "A1";
            case 6: return "B1";
            case 7: return "B2";
            case 8: return "B3";
            case 9: return "B4";
            case 10: return "B5";
        }
        return "";
    }

    private String getEtiquetaBasculaOperador(int posicion){
        switch (posicion){
            case 1: return "A9";
            case 2: return "A7";
            case 3: return "A5";
            case 4: return "A3";
            case 5: return "A1";
            case 6: return "B2";
            case 7: return "B4";
            case 8: return "B6";
            case 9: return "B8";
            case 10: return "B10";
        }
        return "";
    }

    private String getTinaPrincipalOperador(int posicionOperador){
        for( Tina tina : this.listaTinas ){
            if( tina.getIdPosicion() == posicionOperador + 1 ){
                if( !tina.getLibre() ){
                    return tina.getEtiquetaMovil();
                }
                break;
            }
        }
        return "";
    }

    private String getSubtallaPrincipalOperador(int posicionOperador){
        for( Tina tina : this.listaTinas ){
            if( tina.getIdPosicion() == posicionOperador + 1 ){
                if( !tina.getLibre() ){
                    return tina.getSubtalla().getDescripcion();
                }
                break;
            }
        }
        return "";
    }

    private String getTinaSecundariaOperador(int posicionOperador){
        Tina tinaSecundaria;
        switch (posicionOperador){
            case 1:
                tinaSecundaria = getTina(1);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 2:
                tinaSecundaria = getTina(2);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 3:
                tinaSecundaria = getTina(3);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 4:
                tinaSecundaria = getTina(4);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 5:
                tinaSecundaria = getTina(5);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 6:
                tinaSecundaria = getTina(8);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 7:
                tinaSecundaria = getTina(9);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 8:
                tinaSecundaria = getTina(10);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 9:
                tinaSecundaria = getTina(11);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
            case 10:
                tinaSecundaria = getTina(12);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getEtiquetaMovil();
                }
        }
        return "";
    }

    private String getSubtallaSecundariaOperador(int posicionOperador){
        Tina tinaSecundaria;
        switch (posicionOperador){
            case 1:
                tinaSecundaria = getTina(1);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 2:
                tinaSecundaria = getTina(2);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 3:
                tinaSecundaria = getTina(3);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 4:
                tinaSecundaria = getTina(4);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 5:
                tinaSecundaria = getTina(5);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 6:
                tinaSecundaria = getTina(8);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 7:
                tinaSecundaria = getTina(9);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 8:
                tinaSecundaria = getTina(10);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 9:
                tinaSecundaria = getTina(11);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
            case 10:
                tinaSecundaria = getTina(12);
                if( !tinaSecundaria.getLibre() ){
                    return tinaSecundaria.getSubtalla().getDescripcion();
                }
        }
        return "";
    }

    private Tina getTina(int posicion){
        for( Tina tina : this.listaTinas ){
            if( tina.getIdPosicion() == posicion ){
                return tina;
            }
        }
        return null;
    }

    public void iniciaProcesando(){
        ProgressBar barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        ProgressBar barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        barraProgreso.setVisibility(View.GONE);
    }
}

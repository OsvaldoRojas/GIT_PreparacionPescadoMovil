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
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Contenedores.Contenedor;
import com.example.simulador_pescado.R;
import com.example.simulador_pescado.Utilerias.Catalogos;
import com.example.simulador_pescado.Utilerias.Utilerias;
import com.example.simulador_pescado.adaptadores.AdaptadorEspecialidad;
import com.example.simulador_pescado.adaptadores.AdaptadorGrupoEspecie;
import com.example.simulador_pescado.adaptadores.AdaptadorSubtalla;
import com.example.simulador_pescado.adaptadores.AdaptadorTalla;
import com.example.simulador_pescado.conexion.AsignaTina;
import com.example.simulador_pescado.conexion.ValidaTina;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.Especialidad;
import com.example.simulador_pescado.vista.GrupoEspecie;
import com.example.simulador_pescado.vista.Subtalla;
import com.example.simulador_pescado.vista.Talla;
import com.example.simulador_pescado.vista.Tina;
import com.example.simulador_pescado.vista.TinaEscaneo;
import com.example.simulador_pescado.vista.UsuarioLogueado;

import java.io.Serializable;
import java.util.List;

public class Fragment_Asigna_Tina extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Serializable mParam1;

    private View vista;

    private AlertDialog ventanaError;

    private Tina tinaSeleccionada;

    private OnFragmentInteractionListener mListener;

    public Fragment_Asigna_Tina() {
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
    public static Fragment_Asigna_Tina newInstance(Serializable param1) {
        Fragment_Asigna_Tina fragment = new Fragment_Asigna_Tina();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista=inflater.inflate(R.layout.fragment_asignar_tina, container, false);

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

    public Tina getTinaSeleccionada() {
        return tinaSeleccionada;
    }

    public void setTinaSeleccionada(Tina tinaSeleccionada) {
        this.tinaSeleccionada = tinaSeleccionada;
    }

    private void iniciaComponentes(){
        setTinaSeleccionada( (Tina) mParam1 );

        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor().newInstance(0);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        Button botonAceptar = this.vista.findViewById(R.id.boton2);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaAsignacion();
            }
        });

        Button botonPrecargar = this.vista.findViewById(R.id.botonPrecargar);
        botonPrecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargaValoresIniciales();
            }
        });

        Spinner seleccionSubtalla = this.vista.findViewById(R.id.seleccionSubtalla);
        seleccionSubtalla.setAdapter( new AdaptadorSubtalla( getContext(), Catalogos.getInstancia().getCatalogoSubtalla() ) );

        Spinner seleccionTalla = this.vista.findViewById(R.id.seleccionTalla);
        seleccionTalla.setAdapter( new AdaptadorTalla( getContext(), Catalogos.getInstancia().getCatalogoTalla() ) );

        Spinner seleccionEspecie = this.vista.findViewById(R.id.seleccionEspecie);
        seleccionEspecie.setAdapter( new AdaptadorGrupoEspecie( getContext(), Catalogos.getInstancia().getCatalogoGrupoEspecie() ) );

        Spinner seleccionEspecialidad = this.vista.findViewById(R.id.seleccionEspecialidad);
        seleccionEspecialidad.setAdapter( new AdaptadorEspecialidad( getContext(), Catalogos.getInstancia().getCatalogoEspecialidad() ) );

        TextView etiquetaPosicion = this.vista.findViewById(R.id.etiquetaPosicion);
        etiquetaPosicion.setText( String.valueOf( getTinaSeleccionada().getPosicion() ) );

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

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
                validaTina( editable.toString() );
            }
        });
    }

    private void validaAsignacion(){
        Spinner especie = this.vista.findViewById(R.id.seleccionEspecie);
        Spinner especialidad = this.vista.findViewById(R.id.seleccionEspecialidad);
        Spinner talla = this.vista.findViewById(R.id.seleccionTalla);
        Spinner subtalla = this.vista.findViewById(R.id.seleccionSubtalla);
        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        TextView campoDescripcion = this.vista.findViewById(R.id.campoDescripcion);

        if( !campoEscaner.getText().equals("") &&
                !campoDescripcion.getText().equals( getResources().getString(R.string.mensajeErrorEscaneo) ) &&
                !campoDescripcion.getText().equals("") ){

            if( ( (GrupoEspecie) especie.getSelectedItem() ).getIdEspecie() > 0 ){
                if( ( (Talla) talla.getSelectedItem() ).getIdTalla() > 0 ){
                    if( ( (Subtalla) subtalla.getSelectedItem() ).getIdSubtalla() > 0 ){
                        iniciaProcesando();
                        getTinaSeleccionada().setLibre(false);
                        getTinaSeleccionada().setTurno(true);
                        if( UsuarioLogueado.getUsuarioLogueado(null).getTurno() == 1 ){
                            getTinaSeleccionada().setTurno(false);
                        }
                        getTinaSeleccionada().setSubtalla( (Subtalla) subtalla.getSelectedItem() );
                        getTinaSeleccionada().setTalla( (Talla) talla.getSelectedItem() );
                        getTinaSeleccionada().setGrupoEspecie( (GrupoEspecie) especie.getSelectedItem() );
                        getTinaSeleccionada().setEspecialidad( (Especialidad) especialidad.getSelectedItem() );
                        AsignaTina asignaTina = new AsignaTina(this, getTinaSeleccionada() );
                        asignaTina.execute();
                    }else{
                        errorValidacion("El campo Subtalla es obligatorio");
                    }
                }else{
                    errorValidacion("El campo Talla es obligatorio");
                }
            }else {
                errorValidacion("El campo Especie es obligatorio");
            }
        }else{
            errorValidacion("Es necesario capturar una tina");
        }
    }

    public void resultadoAsignacion(){
        terminaProcesando();
        Fragment fragment = new Contenedor().newInstance(0);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private void validaTina(String codigo){
        if( codigo.length() >= 15 ){
            iniciaProcesando();
            ValidaTina validaTina = new ValidaTina(codigo, this);
            validaTina.execute();
        }else{
            TextView campoDescripcion = this.vista.findViewById(R.id.campoDescripcion);
            campoDescripcion.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoDescripcion.setTextColor( getResources().getColor(R.color.noValido) );
        }
    }

    public void resultadoEscaneoTina(TinaEscaneo resultadoTina){
        TextView campoDescripcion = this.vista.findViewById(R.id.campoDescripcion);

        if( resultadoTina.getIdTinaDes() != null ){
            campoDescripcion.setText( resultadoTina.getTinaDes() );
            campoDescripcion.setTextColor( getResources().getColor(R.color.siValido) );

            getTinaSeleccionada().getTina().setIdTina( Long.valueOf( resultadoTina.getIdTinaDes() ) );
            getTinaSeleccionada().getTina().setDescripcion( resultadoTina.getTinaDes() );
        } else{
            campoDescripcion.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoDescripcion.setTextColor( getResources().getColor(R.color.noValido) );
        }

        terminaProcesando();
    }

    public void errorServicio(ErrorServicio errorMensaje){
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

    public void errorValidacion(final String mensaje){
        final AlertDialog ventanaEmergente;
        AlertDialog.Builder builder = new AlertDialog.Builder( getContext() );
        View vistaAsignar = getLayoutInflater().inflate(R.layout.dialog_mensaje_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        ventanaEmergente = builder.create();
        ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaEmergente.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText(mensaje);

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton1);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ventanaEmergente.dismiss();
                    }
                });
            }
        });
        ventanaEmergente.show();
    }

    private void cargaValoresIniciales(){
        boolean turno = false;
        if( UsuarioLogueado.getUsuarioLogueado(null).getTurno() == 2 ){
            turno = true;
        }

        if( getTinaSeleccionada().getTurno() == turno ){
            Spinner seleccionEspecie = this.vista.findViewById(R.id.seleccionEspecie);
            Spinner seleccionTalla = this.vista.findViewById(R.id.seleccionTalla);
            Spinner seleccionSubtalla = this.vista.findViewById(R.id.seleccionSubtalla);
            Spinner seleccionEspecialidad = this.vista.findViewById(R.id.seleccionEspecialidad);

            seleccionEspecie.setSelection(
                    obtenerPosicionItem(
                            seleccionEspecie,
                            getTinaSeleccionada().getGrupoEspecie().getIdEspecie(),
                            "Especie"
                    )
            );

            seleccionTalla.setSelection(
                    obtenerPosicionItem(
                            seleccionTalla,
                            getTinaSeleccionada().getTalla().getIdTalla(),
                            "Talla"
                    )
            );

            seleccionSubtalla.setSelection(
                    obtenerPosicionItem(
                            seleccionSubtalla,
                            getTinaSeleccionada().getSubtalla().getIdSubtalla(),
                            "Subtalla"
                    )
            );

            seleccionEspecialidad.setSelection(
                    obtenerPosicionItem(
                            seleccionEspecialidad,
                            getTinaSeleccionada().getEspecialidad().getIdEspecialidad(),
                            "Especialidad"
                    )
            );
        }
    }

    public static int obtenerPosicionItem(Spinner spinner, int id, String tipo) {
        int posicion = 0;
        switch (tipo){
            case "Especie":
                for (int i = 0; i < spinner.getCount(); i++) {
                    if ( ( (GrupoEspecie) spinner.getItemAtPosition(i) ).getIdEspecie() == id ) {
                        posicion = i;
                        break;
                    }
                }
                break;
            case "Talla":
                for (int i = 0; i < spinner.getCount(); i++) {
                    if ( ( (Talla) spinner.getItemAtPosition(i) ).getIdTalla() == id ) {
                        posicion = i;
                        break;
                    }
                }
                break;
            case "Subtalla":
                for (int i = 0; i < spinner.getCount(); i++) {
                    if ( ( (Subtalla) spinner.getItemAtPosition(i) ).getIdSubtalla() == id ) {
                        posicion = i;
                        break;
                    }
                }
                break;
            case "Especialidad":
                for (int i = 0; i < spinner.getCount(); i++) {
                    if ( ( (Especialidad) spinner.getItemAtPosition(i) ).getIdEspecialidad() == id ) {
                        posicion = i;
                        break;
                    }
                }
                break;
        }
        return posicion;
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

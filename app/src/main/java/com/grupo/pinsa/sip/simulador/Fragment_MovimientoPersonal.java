package com.grupo.pinsa.sip.simulador;

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

import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorArea;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorPuesto;
import com.grupo.pinsa.sip.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.simulador.vista.Area;
import com.grupo.pinsa.sip.simulador.vista.Puesto;
import com.grupo.pinsa.sip.simulador.vista.UsuarioLogueado;
import com.grupo.pinsa.sip.simulador.vista.servicio.Gafete;
import com.grupo.pinsa.sip.simulador.vista.servicio.RespuestaServicio;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_MovimientoPersonal extends Fragment {

    private View vista;

    private AlertDialog ventanaError;

    private OnFragmentInteractionListener mListener;

    public Fragment_MovimientoPersonal() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_movimiento_personal, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        Button botonCancelar = this.vista.findViewById(R.id.boton1);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiaComponentes();
            }
        });

        Button botonAceptar = this.vista.findViewById(R.id.boton2);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valida();
            }
        });

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        Area area1 = new Area();
        area1.setId(0);
        area1.setDescripcion("Seleccionar área");
        Area area2 = new Area();
        area2.setId(2);
        area2.setDescripcion("Área 2");
        Area area3 = new Area();
        area3.setId(3);
        area3.setDescripcion("Área 3");
        List<Area> listaAreas = new ArrayList<>();
        listaAreas.add(area1);
        listaAreas.add(area2);
        listaAreas.add(area3);

        Spinner seleccionArea = this.vista.findViewById(R.id.seleccionArea);
        seleccionArea.setAdapter( new AdaptadorArea( getContext(), listaAreas ) );

        Puesto puesto1 = new Puesto();
        puesto1.setId(0);
        puesto1.setDescripcion("Seleccionar puesto");
        Puesto puesto2 = new Puesto();
        puesto2.setId(2);
        puesto2.setDescripcion("Puesto 2");
        Puesto puesto3 = new Puesto();
        puesto3.setId(3);
        puesto3.setDescripcion("Puesto 3");
        List<Puesto> listaPuestos = new ArrayList<>();
        listaPuestos.add(puesto1);
        listaPuestos.add(puesto2);
        listaPuestos.add(puesto3);

        Spinner seleccionPuesto = this.vista.findViewById(R.id.seleccionPuesto);
        seleccionPuesto.setAdapter( new AdaptadorPuesto( getContext(), listaPuestos ));

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

    private void valida(){
        TextView campoNombre = this.vista.findViewById(R.id.campoNombre);
        Spinner seleccionPuesto = this.vista.findViewById(R.id.seleccionPuesto);
        Spinner seleccionArea = this.vista.findViewById(R.id.seleccionArea);

        if( campoNombre.getText().toString().equalsIgnoreCase("") ){
            errorServicio("Es necesario ingresar un usuario");
        }else{
            if( ( (Puesto) seleccionPuesto.getSelectedItem() ).getId() == 0
                    || ( (Area) seleccionArea.getSelectedItem() ).getId() == 0 ){
                errorServicio("Es necesario seleccionar un Puesto y Área");
            }else{
                guarda(
                        campoNombre.getText().toString(),
                        (Area) seleccionArea.getSelectedItem(),
                        (Puesto) seleccionPuesto.getSelectedItem()
                );
            }
        }
    }

    private void guarda(String idEmpleado, Area area, Puesto puesto){
        JsonObject json = new JsonObject();
        json.addProperty("idEmpleado", idEmpleado);
        json.addProperty("idArea", String.valueOf( area.getId() ) );
        json.addProperty("idPuesto", String.valueOf( puesto.getId() ) );
        json.addProperty("turno", UsuarioLogueado.getUsuarioLogueado().getTurno() == 1 ? false : true );
        json.addProperty("usuario", UsuarioLogueado.getUsuarioLogueado().getClave_usuario() );
        Call<RespuestaServicio> llamadaServicio = APIServicios.getConexion().guardaPuesto(json);
        llamadaServicio.enqueue(new Callback<RespuestaServicio>() {
            @Override
            public void onResponse(Call<RespuestaServicio> call, Response<RespuestaServicio> response) {
                RespuestaServicio respuesta = response.body();
                if( response.code() == 200 && respuesta.getCodigo() == 0 ){
                    limpiaComponentes();
                }else{
                    terminaProcesando();
                    errorServicio("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<RespuestaServicio> call, Throwable t) {
                terminaProcesando();
                errorServicio("Error al conectar con el servidor");
            }
        });
    }

    private void limpiaComponentes(){
        TextView campoNombre = this.vista.findViewById(R.id.campoNombre);
        TextView campoArea = this.vista.findViewById(R.id.campoAreaPertenece);
        TextView campoPuesto = this.vista.findViewById(R.id.campoPuestoPertenece);
        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        Spinner seleccionPuesto = this.vista.findViewById(R.id.seleccionPuesto);
        Spinner seleccionArea = this.vista.findViewById(R.id.seleccionArea);

        campoEscaner.setText("");
        campoNombre.setText("");
        campoArea.setText("");
        campoPuesto.setText("");
        seleccionPuesto.setSelection(0);
        seleccionArea.setSelection(0);
    }

    private void validaGafete(String codigo){
        if( codigo.length() >= 7 ){
            iniciaProcesando();
            Call<Gafete> llamadaServicio = APIServicios.getConexionPINSA().getGafeteUsuario(codigo);
            llamadaServicio.enqueue(new Callback<Gafete>() {
                @Override
                public void onResponse(Call<Gafete> call, Response<Gafete> response) {
                    if(response.code() == 200){
                        resultadoEscaneoGafete( response.body() );
                    }else{
                        terminaProcesando();
                        errorServicio("Error interno del servidor");
                    }
                }

                @Override
                public void onFailure(Call<Gafete> call, Throwable t) {
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            });
        }else{
            TextView campoNombre = this.vista.findViewById(R.id.campoNombre);
            TextView campoArea = this.vista.findViewById(R.id.campoAreaPertenece);
            TextView campoPuesto = this.vista.findViewById(R.id.campoPuestoPertenece);
            campoNombre.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoNombre.setTextColor( getResources().getColor(R.color.noValido) );
            campoArea.setText("");
            campoPuesto.setText("");
        }
    }

    public void resultadoEscaneoGafete(Gafete resultadoGafete){
        TextView campoNombre = this.vista.findViewById(R.id.campoNombre);
        TextView campoArea = this.vista.findViewById(R.id.campoAreaPertenece);
        TextView campoPuesto = this.vista.findViewById(R.id.campoPuestoPertenece);

        if( resultadoGafete.getResultado().equalsIgnoreCase("YES") ){
            campoNombre.setText( resultadoGafete.getEmpleado().getNom_trab().concat(" ")
                    .concat( resultadoGafete.getEmpleado().getAp_paterno() ) );
            campoNombre.setTextColor( getResources().getColor(R.color.siValido) );

            campoArea.setText( resultadoGafete.getEmpleado().getAreaPertenece() );
            campoPuesto.setText( resultadoGafete.getEmpleado().getPuestoPertenece() );
        }else{
            campoNombre.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoNombre.setTextColor( getResources().getColor(R.color.noValido) );
            campoArea.setText("");
            campoPuesto.setText("");
        }

        terminaProcesando();
    }

    public void errorServicio(final String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_mensaje_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaError = builder.create();
        this.ventanaError.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaError.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText(mensaje);

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

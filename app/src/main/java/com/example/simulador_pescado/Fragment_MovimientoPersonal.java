package com.example.simulador_pescado;

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

<<<<<<< HEAD:app/src/main/java/com/example/simulador_pescado/Fragment_ControlProceso.java
import com.example.simulador_pescado.conexion.APIServicios;
import com.example.simulador_pescado.utilerias.Utilerias;
import com.example.simulador_pescado.vista.servicio.TinaEscaneo;

=======
import com.example.simulador_pescado.adaptadores.AdaptadorArea;
import com.example.simulador_pescado.adaptadores.AdaptadorPuesto;
import com.example.simulador_pescado.conexion.APIServicios;
import com.example.simulador_pescado.utilerias.Utilerias;
import com.example.simulador_pescado.vista.Area;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.Puesto;
import com.example.simulador_pescado.vista.servicio.Gafete;

import java.util.ArrayList;
import java.util.List;

>>>>>>> origin/develop:app/src/main/java/com/example/simulador_pescado/Fragment_MovimientoPersonal.java
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

<<<<<<< HEAD:app/src/main/java/com/example/simulador_pescado/Fragment_ControlProceso.java
public class Fragment_ControlProceso extends Fragment {
=======
public class Fragment_MovimientoPersonal extends Fragment {
>>>>>>> origin/develop:app/src/main/java/com/example/simulador_pescado/Fragment_MovimientoPersonal.java
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View vista;

    private AlertDialog ventanaError;

    private OnFragmentInteractionListener mListener;

<<<<<<< HEAD:app/src/main/java/com/example/simulador_pescado/Fragment_ControlProceso.java
    public Fragment_ControlProceso() {
=======
    public Fragment_MovimientoPersonal() {
>>>>>>> origin/develop:app/src/main/java/com/example/simulador_pescado/Fragment_MovimientoPersonal.java
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Descongelado_TiempoMuerto.
     */
    // TODO: Rename and change types and number of parameters
<<<<<<< HEAD:app/src/main/java/com/example/simulador_pescado/Fragment_ControlProceso.java
    public static Fragment_ControlProceso newInstance(String param1, String param2) {
        Fragment_ControlProceso fragment = new Fragment_ControlProceso();
=======
    public static Fragment_MovimientoPersonal newInstance(String param1, String param2) {
        Fragment_MovimientoPersonal fragment = new Fragment_MovimientoPersonal();
>>>>>>> origin/develop:app/src/main/java/com/example/simulador_pescado/Fragment_MovimientoPersonal.java
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
        // Inflate the layout for this fragment
<<<<<<< HEAD:app/src/main/java/com/example/simulador_pescado/Fragment_ControlProceso.java
        this.vista = inflater.inflate(R.layout.fragment_control_proceso, container, false);
        iniciaComponente();
        return this.vista;
    }

    private void iniciaComponente(){
=======
        this.vista = inflater.inflate(R.layout.fragment_movimiento_personal, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
>>>>>>> origin/develop:app/src/main/java/com/example/simulador_pescado/Fragment_MovimientoPersonal.java
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

            }
        });

        TextView etiquetaFecha = this.vista.findViewById(R.id.etiquetaFecha);
        etiquetaFecha.setText( Utilerias.fechaActual() );

        Area area1 = new Area();
        area1.setId(1);
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
        puesto1.setId(1);
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
                validaTina( editable.toString() );
            }
        });
    }

    private void limpiaComponentes(){
<<<<<<< HEAD:app/src/main/java/com/example/simulador_pescado/Fragment_ControlProceso.java
        EditText campoEscaner = this.vista.findViewById(R.id.campoEscaner);
        TextView campoDescripcion = this.vista.findViewById(R.id.campoDescripcion);
        TextView campoEtapaActual = this.vista.findViewById(R.id.campoEtapaActual);
        TextView campoEtapaSiguiente = this.vista.findViewById(R.id.campoEtapaSiguiente);
        TextView campoPeso = this.vista.findViewById(R.id.campoPeso);

        campoEscaner.setText("");
        campoDescripcion.setText("");
        campoEtapaActual.setText("");
        campoEtapaSiguiente.setText("");
        campoPeso.setText("");
    }

    private void validaTina(String codigo){
        if( codigo.length() >= 15 ){
            iniciaProcesando();
            Call<TinaEscaneo> llamadaServicio = APIServicios.getConexion().getTina(codigo);
            llamadaServicio.enqueue(new Callback<TinaEscaneo>() {
                @Override
                public void onResponse(Call<TinaEscaneo> call, Response<TinaEscaneo> response) {
                    if(response.code() == 200){
                        resultadoEscaneoTina( response.body() );
=======
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
>>>>>>> origin/develop:app/src/main/java/com/example/simulador_pescado/Fragment_MovimientoPersonal.java
                    }else{
                        terminaProcesando();
                        errorServicio("Error interno del servidor");
                    }
                }

                @Override
<<<<<<< HEAD:app/src/main/java/com/example/simulador_pescado/Fragment_ControlProceso.java
                public void onFailure(Call<TinaEscaneo> call, Throwable t) {
=======
                public void onFailure(Call<Gafete> call, Throwable t) {
>>>>>>> origin/develop:app/src/main/java/com/example/simulador_pescado/Fragment_MovimientoPersonal.java
                    terminaProcesando();
                    errorServicio("Error al conectar con el servidor");
                }
            });
        }else{
<<<<<<< HEAD:app/src/main/java/com/example/simulador_pescado/Fragment_ControlProceso.java
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


        } else{
            campoDescripcion.setText( getResources().getString(R.string.mensajeErrorEscaneo) );
            campoDescripcion.setTextColor( getResources().getColor(R.color.noValido) );
=======
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
>>>>>>> origin/develop:app/src/main/java/com/example/simulador_pescado/Fragment_MovimientoPersonal.java
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

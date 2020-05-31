package com.grupo.pinsa.sip.simulador.modulos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupo.pinsa.sip.simulador.R;
import com.grupo.pinsa.sip.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.simulador.modelo.Modulo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadVistaPanoramica extends AppCompatActivity {

    private AlertDialog ventanaError;
    private ProgressBar barraProgreso;

    private List<Modulo> modulos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_vista_panoramica);
        iniciaComponentes();
    }

    @Override
    public void onBackPressed() {
    }

    private void iniciaComponentes(){
        this.barraProgreso = findViewById(R.id.barraProgreso);
        iniciaProcesando();

        Button botonSalir = findViewById(R.id.boton1);
        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        FloatingActionButton botonActualiza = findViewById(R.id.actualiza);
        botonActualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciaProcesando();
                getModulos();
            }
        });

        getModulos();
    }

    private void getModulos(){
        Call<List<Modulo>> llamadaServicio = APIServicios.getConexion().getModulosVistaPanoramica();
        llamadaServicio.enqueue(new Callback<List<Modulo>>() {
            @Override
            public void onResponse(Call<List<Modulo>> call, Response<List<Modulo>> response) {
                if( response.code() == 200 ){
                    dibujaModulos();
                }else{
                    terminaProcesando();
                    errorServicio( "Error al obtener los módulos de vista" );
                }
            }

            @Override
            public void onFailure(Call<List<Modulo>> call, Throwable t) {
                terminaProcesando();
                errorServicio("Error al conectar con el servidor");
            }
        });
    }

    private void dibujaModulos(){
        for(Modulo modulo : this.modulos){

        }
        /*int posicionesInhabilitadas = getModuloSeleccionado().getCapacidadMaxima() - getModuloSeleccionado().getCapacidadActual();
        int posicionesDibujadas = 0;
        int posicionesTurno1 = posicionesInhabilitadas;
        int posicionesTurno2 = getModuloSeleccionado().getCarritos().size() + posicionesInhabilitadas;
        for( Carrito carrito : getModuloSeleccionado().getCarritos() ){
            if( carrito.getTurno() == 1 ){
                posicionesTurno1 = posicionesTurno1+1;
            }
        }
        LinearLayout posiciones = this.vista.findViewById(R.id.posiciones);
        posiciones.removeAllViews();
        for( int columna = 0; columna < getModuloSeleccionado().getColumna(); columna++  ){
            LinearLayout linearFila = new LinearLayout( getContext() );
            LinearLayout.LayoutParams layoutParams = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearFila.setLayoutParams(layoutParams);
            linearFila.setOrientation(LinearLayout.VERTICAL);
            posiciones.addView(linearFila);
            for( int fila = 0; fila < getModuloSeleccionado().getFila(); fila++ ){
                if( posicionesDibujadas == getModuloSeleccionado().getCapacidadMaxima() ){
                    terminaProcesando();
                    return;
                }
                ImageView imagen = new ImageView( getContext() );
                LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(70, 70);
                imagenParams.setMargins(1, 1, 1, 1 );
                imagen.setLayoutParams(imagenParams);
                imagen.setPadding(10, 10, 10, 10);

                if(posicionesDibujadas < posicionesInhabilitadas){
                    imagen.setImageResource(R.drawable.ic_block_modulo);
                    imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio ) );
                }else{
                    if(posicionesDibujadas < posicionesTurno1){
                        imagen.setImageResource(R.drawable.ic_pinsa_carritos);
                        imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_turno1 ) );
                    }else{
                        if(posicionesDibujadas < posicionesTurno2){
                            imagen.setImageResource(R.drawable.ic_pinsa_carrito_turno2);
                            imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_turno2 ) );
                        }else{
                            imagen.setImageResource(R.drawable.ic_pinsa_carrito_vacio);
                            imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio ) );
                        }
                    }
                }
                linearFila.addView(imagen);
                posicionesDibujadas = posicionesDibujadas+1;
            }
        }

        //
        float[] arregloTemperaturas = new float[]{35.6f,23.3f,40.5f};
        getModuloSeleccionado().setTemperaturas(arregloTemperaturas);
        //
        LinearLayout temperaturas = this.vista.findViewById(R.id.temperaturas);
        temperaturas.removeAllViews();
        for(int posicion = 0; posicion < getModuloSeleccionado().getTemperaturas().length; posicion++){
            LinearLayout linearFila = new LinearLayout( getContext() );
            LinearLayout.LayoutParams layoutParams = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            linearFila.setLayoutParams(layoutParams);
            linearFila.setGravity(Gravity.CENTER_VERTICAL);
            linearFila.setOrientation(LinearLayout.HORIZONTAL);
            temperaturas.addView(linearFila);

            TextView etiquetaTemperatura = new TextView( getContext() );
            etiquetaTemperatura.setText(
                    String.valueOf( getModuloSeleccionado().getTemperaturas()[posicion] ).concat("°C")
            );
            etiquetaTemperatura.setTextSize(12);
            etiquetaTemperatura.setPadding(0, 0, 5, 0);
            linearFila.addView(etiquetaTemperatura);

            ImageView imagen = new ImageView( getContext() );
            LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(50, 50);
            imagenParams.setMargins(1, 1, 1, 1 );
            imagen.setLayoutParams(imagenParams);
            imagen.setPadding(10, 10, 10, 10);
            imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio ) );
            if(getModuloSeleccionado().getTemperaturas()[posicion] <= 35){
                imagen.setImageResource(R.drawable.ic_temperatura_modulo2);
            }else{
                imagen.setImageResource(R.drawable.ic_temperatura_modulo1);
            }
            linearFila.addView(imagen);
        }*/

        terminaProcesando();
    }

    public void errorServicio(final String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        this.barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        this.barraProgreso.setVisibility(View.GONE);
    }
}

package com.grupo.pinsa.sip.views.simulador.modulos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.modelo.Carrito;
import com.grupo.pinsa.sip.views.simulador.modelo.Modulo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadVistaPanoramica extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Float scale = 1f;
    private ScaleGestureDetector SGD;
    private int currentX;
    private int currentY;

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        SGD.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                currentX = (int) event.getRawX();
                currentY = (int) event.getRawY();
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                int x2 = (int) event.getRawX();
                int y2 = (int) event.getRawY();
                linearLayout.scrollBy(currentX - x2 , currentY - y2);
                currentX = x2;
                currentY = y2;
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
        }


        return true;
    }

    private void iniciaComponentes(){
        this.barraProgreso = findViewById(R.id.barraProgreso);
        iniciaProcesando();

        this.SGD = new ScaleGestureDetector(this, new ScaleListener());
        this.linearLayout = findViewById(R.id.contenedorPrincipal);

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
                    modulos = response.body();
                    muestraModulos();
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

    private void muestraModulos(){
        Modulo modulo1 = null;
        Modulo modulo2 = null;
        Modulo modulo3 = null;
        Modulo modulo4 = null;
        Modulo modulo5 = null;
        Modulo modulo6 = null;
        Modulo modulo7 = null;
        Modulo modulo8 = null;
        Modulo modulo9 = null;
        Modulo modulo10 = null;

        for( Modulo modulo : this.modulos ){
            if( modulo.getId() == 1 ){
                modulo1 = modulo;
            }else{
                if( modulo.getId() == 2 ){
                    modulo2 = modulo;
                }else{
                    if( modulo.getId() == 3 ){
                        modulo3 = modulo;
                    }else{
                        if( modulo.getId() == 4 ){
                            modulo4 = modulo;
                        }else{
                            if( modulo.getId() == 5 ){
                                modulo5 = modulo;
                            }else{
                                if( modulo.getId() == 6 ){
                                    modulo6 = modulo;
                                }else{
                                    if( modulo.getId() == 7 ){
                                        modulo7 = modulo;
                                    }else{
                                        if( modulo.getId() == 8 ){
                                            modulo8 = modulo;
                                        }else{
                                            if( modulo.getId() == 9 ){
                                                modulo9 = modulo;
                                            }else{
                                                if( modulo.getId() == 10 ){
                                                    modulo10 = modulo;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        LinearLayout contenedorUno = findViewById(R.id.contenedorUno);
        if(modulo1 != null){
            contenedorUno.addView( dibujaModulo(modulo1) );
        }

        LinearLayout contenedorUnoVarios = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        contenedorUnoVarios.setLayoutParams(layoutParams);
        contenedorUnoVarios.setGravity(Gravity.CENTER);
        contenedorUnoVarios.setOrientation(LinearLayout.VERTICAL);
        contenedorUno.addView(contenedorUnoVarios);
        if(modulo2 != null){
            contenedorUnoVarios.addView( dibujaModulo(modulo2) );
        }

        if(modulo3 != null){
            contenedorUnoVarios.addView( dibujaModulo(modulo3) );
        }

        terminaProcesando();
    }

    private LinearLayout dibujaModulo(Modulo modulo){
        LinearLayout borde = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        layoutParams.setMargins(2, 2, 2, 2);
        borde.setLayoutParams(layoutParams);
        borde.setGravity(Gravity.CENTER);
        borde.setPadding(10, 10, 10, 10);
        borde.setBackground( getResources().getDrawable(R.drawable.borde) );
        borde.setOrientation(LinearLayout.VERTICAL);

        TextView descripcion = new TextView(this);
        LinearLayout.LayoutParams descripcionParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        descripcion.setLayoutParams(descripcionParams);
        descripcion.setText( modulo.getDescripcion() );
        descripcion.setPadding(10, 10, 10, 10);
        descripcion.setGravity(Gravity.CENTER);
        descripcion.setTextSize(9);

        borde.addView(descripcion);

        LinearLayout contenedor = new LinearLayout(this);
        LinearLayout.LayoutParams contenedorParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contenedor.setLayoutParams(contenedorParams);
        contenedor.setOrientation(LinearLayout.HORIZONTAL);

        borde.addView(contenedor);

        LinearLayout posiciones = new LinearLayout(this);
        LinearLayout.LayoutParams posicionesParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        posicionesParams.setMargins(10, 0, 7, 10);
        posiciones.setLayoutParams(posicionesParams);
        posiciones.setGravity(Gravity.CENTER);
        posiciones.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout temperaturas = new LinearLayout(this);
        LinearLayout.LayoutParams temperaturasParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        temperaturasParams.setMargins(7, 0, 10, 10);
        temperaturas.setLayoutParams(temperaturasParams);
        temperaturas.setGravity(Gravity.CENTER_VERTICAL);
        temperaturas.setOrientation(LinearLayout.VERTICAL);

        contenedor.addView(posiciones);
        contenedor.addView(temperaturas);

        int posicionesInhabilitadas = modulo.getCapacidadMaxima() - modulo.getCapacidadActual();
        int posicionesDibujadas = 0;
        int posicionesTurno1 = posicionesInhabilitadas;
        int posicionesTurno2 = modulo.getCarritos().size() + posicionesInhabilitadas;
        for( Carrito carrito : modulo.getCarritos() ){
            if( carrito.getTurno() == 1 ){
                posicionesTurno1 = posicionesTurno1+1;
            }
        }

        boolean moduloCompleto = false;
        for( int columna = 0; columna < modulo.getColumna(); columna++  ){
            if(moduloCompleto){
                break;
            }
            LinearLayout linearFila = new LinearLayout(this);
            LinearLayout.LayoutParams parametros = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearFila.setLayoutParams(parametros);
            linearFila.setOrientation(LinearLayout.VERTICAL);
            posiciones.addView(linearFila);
            for( int fila = 0; fila < modulo.getFila(); fila++ ){
                if( posicionesDibujadas == modulo.getCapacidadMaxima() ){
                    moduloCompleto = true;
                    break;
                }
                ImageView imagen = new ImageView(this);
                LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(55, 55);
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
        modulo.setTemperaturas(arregloTemperaturas);
        //

        for(int posicion = 0; posicion < modulo.getTemperaturas().length; posicion++){
            LinearLayout linearFila = new LinearLayout(this);
            LinearLayout.LayoutParams parametros = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            linearFila.setLayoutParams(parametros);
            linearFila.setGravity(Gravity.CENTER_VERTICAL);
            linearFila.setOrientation(LinearLayout.HORIZONTAL);
            temperaturas.addView(linearFila);

            TextView etiquetaTemperatura = new TextView(this);
            etiquetaTemperatura.setText(
                    String.valueOf( modulo.getTemperaturas()[posicion] ).concat("°C")
            );
            etiquetaTemperatura.setTextSize(10);
            etiquetaTemperatura.setPadding(0, 0, 5, 0);
            linearFila.addView(etiquetaTemperatura);

            ImageView imagen = new ImageView(this);
            LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(50, 50);
            imagenParams.setMargins(1, 1, 1, 1 );
            imagen.setLayoutParams(imagenParams);
            imagen.setPadding(10, 10, 10, 10);
            imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio ) );
            if(modulo.getTemperaturas()[posicion] <= 35){
                imagen.setImageResource(R.drawable.ic_temperatura_modulo2);
            }else{
                imagen.setImageResource(R.drawable.ic_temperatura_modulo1);
            }
            linearFila.addView(imagen);
        }
        return borde;
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

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        @Override
        public boolean onScale(ScaleGestureDetector detector)
        {
            scale = scale * detector.getScaleFactor();
            scale = Math.max(1f, Math.min(scale, 5f)); //0.1f und 5f  //First: Zoom in __ Second: Zoom out
            //matrix.setScale(scale, scale);
            linearLayout.setScaleX(scale);
            linearLayout.setScaleY(scale);

            linearLayout.invalidate();

            return true;
        }

    }
}

package com.grupo.pinsa.sip.views.simulador.modulos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import androidx.fragment.app.Fragment;

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
                linearLayout.scrollBy( Math.round( (currentX - x2)/scale ), Math.round( (currentY - y2)/scale ) );
                currentX = x2;
                currentY = y2;
                if(linearLayout.getScrollX() < -470){
                    linearLayout.setScrollX(-470);
                }
                if(linearLayout.getScrollX() > 475){
                    linearLayout.setScrollX(475);
                }
                if(linearLayout.getScrollY() < -705){
                    linearLayout.setScrollY(-705);
                }
                if(linearLayout.getScrollY() > 820){
                    linearLayout.setScrollY(820);
                }
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

        FloatingActionButton botonVolver = findViewById(R.id.volver);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                intent.putExtra("navegacion", "volver");
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
        LinearLayout contenedorUno = findViewById(R.id.contenedorUno);
        contenedorUno.removeAllViews();
        LinearLayout contenedorDos = findViewById(R.id.contenedorDos);
        contenedorDos.removeAllViews();
        LinearLayout contenedorTres = findViewById(R.id.contenedorTres);
        contenedorTres.removeAllViews();
        LinearLayout contenedorCuatro = findViewById(R.id.contenedorCuatro);
        contenedorCuatro.removeAllViews();

        int contador = 1;
        for(Modulo modulo : this.modulos){
            if(contador <= 3){
                if(contador == 2){
                    contenedorUno.addView( dibujaModulo(modulo, 2.9f) );
                }else{
                    contenedorUno.addView( dibujaModulo(modulo, 3.0f) );
                }
            }else{
                if(contador <= 6){
                    if(contador == 5){
                        contenedorDos.addView( dibujaModulo(modulo, 2.9f) );
                    }else{
                        contenedorDos.addView( dibujaModulo(modulo, 3.0f) );
                    }
                }else{
                    if(contador <= 9){
                        if(contador == 8){
                            contenedorTres.addView( dibujaModulo(modulo, 2.9f) );
                        }else{
                            contenedorTres.addView( dibujaModulo(modulo, 3.0f) );
                        }
                    }else{
                        contenedorCuatro.addView( dibujaModuloInvertido(modulo, 3.0f) );
                    }
                }
            }
            contador = contador + 1;
        }

        terminaProcesando();
    }

    private LinearLayout dibujaModulo(final Modulo modulo, float weight){
        LinearLayout borde = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, weight);
        layoutParams.setMargins(1, 1, 1, 1);
        borde.setLayoutParams(layoutParams);
        borde.setGravity(Gravity.CENTER_HORIZONTAL);
        borde.setPadding(3, 3, 3, 3);
        borde.setBackground( getResources().getDrawable(R.drawable.borde) );
        borde.setOrientation(LinearLayout.VERTICAL);

        borde.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                modulo.setTotalTouch( modulo.getTotalTouch() + 1 );
                if(modulo.getTotalTouch() == 2){
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    intent.putExtra("navegacion", "detalle");
                    intent.putExtra("modulo", modulo);
                    finish();
                }else{
                    new CountDownTimer(300, 300) {
                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            modulo.setTotalTouch(0);
                        }
                    }.start();
                }
                return false;
            }
        });

        TextView descripcion = new TextView(this);
        LinearLayout.LayoutParams descripcionParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40);
        descripcion.setLayoutParams(descripcionParams);
        descripcion.setText( modulo.getDescripcion() );
        descripcion.setPadding(0, 8, 0, 5);
        descripcion.setGravity(Gravity.CENTER_HORIZONTAL);
        descripcion.setTextSize(10);

        borde.addView(descripcion);

        LinearLayout contenedor = new LinearLayout(this);
        LinearLayout.LayoutParams contenedorParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contenedor.setLayoutParams(contenedorParams);
        contenedor.setGravity(Gravity.CENTER);
        contenedor.setOrientation(LinearLayout.HORIZONTAL);

        borde.addView(contenedor);

        LinearLayout posiciones = new LinearLayout(this);
        LinearLayout.LayoutParams posicionesParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        posicionesParams.setMargins(3, 0, 3, 0);
        posiciones.setLayoutParams(posicionesParams);
        posiciones.setGravity(Gravity.CENTER);
        posiciones.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout temperaturas = new LinearLayout(this);
        LinearLayout.LayoutParams temperaturasParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        temperaturasParams.setMargins(2, 0, 0, 0);
        temperaturas.setLayoutParams(temperaturasParams);
        temperaturas.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
        temperaturas.setOrientation(LinearLayout.VERTICAL);

        contenedor.addView(posiciones);

        int posicionesInhabilitadas = modulo.getCapacidadMaxima() - modulo.getCapacidadActual();
        int posicionesDibujadas = 0;
        int posicionesTurno1 = posicionesInhabilitadas;
        int posicionesTurno2 = modulo.getCarritos().size() + posicionesInhabilitadas;
        for( Carrito carrito : modulo.getCarritos() ){
            if( carrito.getTurnoMP() == 0 ){
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
                LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(22, 22);
                imagenParams.setMargins(1, 1, 1, 1 );
                imagen.setLayoutParams(imagenParams);
                imagen.setPadding(2, 2, 2, 2);

                if(posicionesDibujadas < posicionesInhabilitadas){
                    imagen.setImageResource(R.drawable.ic_block_modulo);
                    imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio_vp ) );
                }else{
                    if(posicionesDibujadas < posicionesTurno1){
                        imagen.setImageResource(R.drawable.ic_pinsa_carritos);
                        imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_turno1_vp ) );
                    }else{
                        if(posicionesDibujadas < posicionesTurno2){
                            imagen.setImageResource(R.drawable.ic_pinsa_carrito_turno2);
                            imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_turno2_vp ) );
                        }else{
                            imagen.setImageResource(R.drawable.ic_pinsa_carrito_vacio);
                            imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio_vp ) );
                        }
                    }
                }
                linearFila.addView(imagen);
                posicionesDibujadas = posicionesDibujadas+1;
            }
        }

        posiciones.addView(temperaturas);

        //
        float[] arregloTemperaturas = new float[]{35.6f,23.3f,40.5f};
        modulo.setTemperaturas(arregloTemperaturas);
        //

        if( modulo.getTemperaturas() == null ){
            modulo.setTemperaturas( new float[0] );
        }

        for(int posicion = 0; posicion < modulo.getTemperaturas().length; posicion++){
            LinearLayout linearFila = new LinearLayout(this);
            LinearLayout.LayoutParams parametros = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            linearFila.setLayoutParams(parametros);
            linearFila.setGravity(Gravity.CENTER_VERTICAL);
            linearFila.setOrientation(LinearLayout.HORIZONTAL);
            temperaturas.addView(linearFila);

            TextView etiquetaTemperatura = new TextView(this);
            etiquetaTemperatura.setText(
                    String.valueOf( modulo.getTemperaturas()[posicion] ).concat("°C")
            );
            etiquetaTemperatura.setTextSize(5);
            etiquetaTemperatura.setPadding(0, 0, 2, 0);
            linearFila.addView(etiquetaTemperatura);

            ImageView imagen = new ImageView(this);
            LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(20, 20);
            imagenParams.setMargins(1, 1, 1, 1 );
            imagen.setLayoutParams(imagenParams);
            imagen.setPadding(2, 2, 2, 2);
            imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio_vp ) );
            if(modulo.getTemperaturas()[posicion] <= 35){
                imagen.setImageResource(R.drawable.ic_temperatura_modulo2);
            }else{
                imagen.setImageResource(R.drawable.ic_temperatura_modulo1);
            }
            linearFila.addView(imagen);
        }
        return borde;
    }

    private LinearLayout dibujaModuloInvertido(final Modulo modulo, float weight){
        LinearLayout borde = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, weight);
        layoutParams.setMargins(1, 1, 1, 1);
        borde.setLayoutParams(layoutParams);
        borde.setGravity(Gravity.CENTER_HORIZONTAL);
        borde.setPadding(3, 3, 3, 3);
        borde.setBackground( getResources().getDrawable(R.drawable.borde) );
        borde.setOrientation(LinearLayout.VERTICAL);

        borde.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                modulo.setTotalTouch( modulo.getTotalTouch() + 1 );
                if(modulo.getTotalTouch() == 2){
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    intent.putExtra("navegacion", "detalle");
                    intent.putExtra("modulo", modulo);
                    finish();
                }else{
                    new CountDownTimer(300, 300) {
                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            modulo.setTotalTouch(0);
                        }
                    }.start();
                }
                return false;
            }
        });

        TextView descripcion = new TextView(this);
        LinearLayout.LayoutParams descripcionParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40);
        descripcion.setLayoutParams(descripcionParams);
        descripcion.setText( modulo.getDescripcion() );
        descripcion.setPadding(0, 8, 0, 5);
        descripcion.setGravity(Gravity.CENTER_HORIZONTAL);
        descripcion.setTextSize(10);

        borde.addView(descripcion);

        LinearLayout contenedor = new LinearLayout(this);
        LinearLayout.LayoutParams contenedorParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contenedor.setLayoutParams(contenedorParams);
        contenedor.setGravity(Gravity.CENTER);
        contenedor.setOrientation(LinearLayout.VERTICAL);

        borde.addView(contenedor);

        LinearLayout posiciones = new LinearLayout(this);
        LinearLayout.LayoutParams posicionesParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        posicionesParams.setMargins(0, 0, 0, 0);
        posiciones.setLayoutParams(posicionesParams);
        posiciones.setGravity(Gravity.CENTER);
        posiciones.setOrientation(LinearLayout.VERTICAL);

        LinearLayout temperaturas = new LinearLayout(this);
        LinearLayout.LayoutParams temperaturasParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        temperaturasParams.setMargins(0, 2, 0, 0);
        temperaturas.setLayoutParams(temperaturasParams);
        temperaturas.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
        temperaturas.setOrientation(LinearLayout.HORIZONTAL);

        contenedor.addView(posiciones);

        int posicionesInhabilitadas = modulo.getCapacidadMaxima() - modulo.getCapacidadActual();
        int posicionesDibujadas = 0;
        int posicionesTurno1 = posicionesInhabilitadas;
        int posicionesTurno2 = modulo.getCarritos().size() + posicionesInhabilitadas;
        for( Carrito carrito : modulo.getCarritos() ){
            if( carrito.getTurnoMP() == 0 ){
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
            linearFila.setOrientation(LinearLayout.HORIZONTAL);
            posiciones.addView(linearFila, 0);
            for( int fila = 0; fila < modulo.getFila(); fila++ ){
                if( posicionesDibujadas == modulo.getCapacidadMaxima() ){
                    moduloCompleto = true;
                    break;
                }
                ImageView imagen = new ImageView(this);
                LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(22, 22);
                imagenParams.setMargins(1, 1, 1, 1 );
                imagen.setLayoutParams(imagenParams);
                imagen.setPadding(2, 2, 2, 2);

                if(posicionesDibujadas < posicionesInhabilitadas){
                    imagen.setImageResource(R.drawable.ic_block_modulo);
                    imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio_vp ) );
                }else{
                    if(posicionesDibujadas < posicionesTurno1){
                        imagen.setImageResource(R.drawable.ic_pinsa_carritos);
                        imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_turno1_vp ) );
                    }else{
                        if(posicionesDibujadas < posicionesTurno2){
                            imagen.setImageResource(R.drawable.ic_pinsa_carrito_turno2);
                            imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_turno2_vp ) );
                        }else{
                            imagen.setImageResource(R.drawable.ic_pinsa_carrito_vacio);
                            imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio_vp ) );
                        }
                    }
                }
                linearFila.addView(imagen);
                posicionesDibujadas = posicionesDibujadas+1;
            }
        }

        posiciones.addView(temperaturas);

        //
        float[] arregloTemperaturas = new float[]{35.6f,23.3f,40.5f};
        modulo.setTemperaturas(arregloTemperaturas);
        //

        if( modulo.getTemperaturas() == null ){
            modulo.setTemperaturas( new float[0] );
        }

        for(int posicion = 0; posicion < modulo.getTemperaturas().length; posicion++){
            LinearLayout linearFila = new LinearLayout(this);
            LinearLayout.LayoutParams parametros = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            linearFila.setLayoutParams(parametros);
            linearFila.setGravity(Gravity.CENTER_HORIZONTAL);
            linearFila.setOrientation(LinearLayout.VERTICAL);
            temperaturas.addView(linearFila);

            TextView etiquetaTemperatura = new TextView(this);
            etiquetaTemperatura.setText(
                    String.valueOf( modulo.getTemperaturas()[posicion] ).concat("°C")
            );
            etiquetaTemperatura.setTextSize(5);
            etiquetaTemperatura.setGravity(Gravity.CENTER_HORIZONTAL);
            etiquetaTemperatura.setPadding(0, 0, 0, 2);
            linearFila.addView(etiquetaTemperatura);

            ImageView imagen = new ImageView(this);
            LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(20, 20);
            imagenParams.setMargins(1, 1, 1, 1 );
            imagen.setLayoutParams(imagenParams);
            imagen.setPadding(2, 2, 2, 2);
            imagen.setBackground( getResources().getDrawable( R.drawable.contenedor_modulo_vacio_vp ) );
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

package com.example.simulador_pescado.conexion;

import android.content.Intent;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Preselecion.AsignarTina;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.Tina;
import com.example.simulador_pescado.vista.TinaServicio;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AsignaTina extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL_TINA = "http://10.50.1.15:7080/api_simulador_movil/v1/preseleccion/posiciones/tinas";
    private ErrorServicio errorMensaje;
    private Fragment pantalla;
    private Tina tina;

    public AsignaTina(Fragment pantalla, Tina tina){
        this.pantalla = pantalla;
        this.tina = tina;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        TinaServicio tinaServicio = new TinaServicio();
        tinaServicio.setIdPreseleccionPosicionTina( this.tina.getIdPreseleccionPosicionTina() );
        tinaServicio.setPosicion( this.tina.getPosicion() );
        tinaServicio.setIdTina( String.valueOf( this.tina.getTina().getIdTina() ) );
        tinaServicio.setIdGrupoEspecie( this.tina.getGrupoEspecie().getIdGrupoEspecie() );
        tinaServicio.setIdTalla( this.tina.getTalla().getIdTalla() );
        tinaServicio.setIdSubtalla( this.tina.getSubtalla().getIdSubtalla() );
        tinaServicio.setNpiezas( this.tina.getNpiezas() );
        tinaServicio.setPeso( this.tina.getPeso() );
        tinaServicio.setActivo( this.tina.getActivo() );
        tinaServicio.setLibre( this.tina.getLibre() );
        tinaServicio.setTurno( this.tina.getTurno() );

        HttpClient conexion = new DefaultHttpClient();
        HttpPut solicitudPut = new HttpPut(this.URL_TINA);

        Gson gson = new Gson();
        solicitudPut.setHeader("content-type", "application/json");
        try {
            solicitudPut.setEntity( new StringEntity( gson.toJson(tinaServicio) ) );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            this.errorMensaje = new ErrorServicio();
            this.errorMensaje.setResultado("NO");
            this.errorMensaje.setMensaje("Error al realizar la solicitud.");
            return false;
        }

        try {
            HttpResponse respuesta = conexion.execute(solicitudPut);
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                return true;
            }else{
                String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
                this.errorMensaje = gson.fromJson(respuestaJson, ErrorServicio.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.errorMensaje = new ErrorServicio();
            this.errorMensaje.setResultado("NO");
            this.errorMensaje.setMensaje("Error al conectar con el servidor.");
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            ( (AsignarTina) this.pantalla ).resultadoAsignacion();
        }else {
            ( (AsignarTina) this.pantalla ).terminaProcesando();
            ( (AsignarTina) this.pantalla ).errorServicio(this.errorMensaje);
        }
    }
}

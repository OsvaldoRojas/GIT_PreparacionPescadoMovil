package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Preselecion.Fragment_Asigna_Montacargas;
import com.example.simulador_pescado.Preselecion.Fragment_Preselecion_Tinas;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.OperadorMontacargas;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AsignaMontacargas extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL_MONTACARGAS = "http://10.50.1.15:7080/api_simulador_movil/v1/preseleccion/montacargas";
    private ErrorServicio errorMensaje;
    private Fragment pantalla;
    private OperadorMontacargas montacargas;

    public AsignaMontacargas(Fragment pantalla, OperadorMontacargas montacargas){
        this.pantalla = pantalla;
        this.montacargas = montacargas;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        HttpClient conexion = new DefaultHttpClient();
        HttpPut solicitudPut = new HttpPut(this.URL_MONTACARGAS);

        Gson gson = new Gson();
        solicitudPut.setHeader("content-type", "application/json");
        try {
            solicitudPut.setEntity( new StringEntity( gson.toJson(this.montacargas) ) );
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
            if( this.pantalla instanceof Fragment_Asigna_Montacargas ){
                ( (Fragment_Asigna_Montacargas) this.pantalla ).resultadoAsignacion();
            }else{
                if( this.pantalla instanceof Fragment_Preselecion_Tinas )
                    ( (Fragment_Preselecion_Tinas) this.pantalla ).resultadoAsignacion();
            }
        }else{
            if( this.pantalla instanceof Fragment_Asigna_Montacargas ){
                ( (Fragment_Asigna_Montacargas) this.pantalla ).terminaProcesando();
                ( (Fragment_Asigna_Montacargas) this.pantalla ).errorServicio(this.errorMensaje);
            }else{
                if( this.pantalla instanceof Fragment_Preselecion_Tinas ){
                    ( (Fragment_Preselecion_Tinas) this.pantalla ).terminaProcesando();
                    ( (Fragment_Preselecion_Tinas) this.pantalla ).errorServicio(this.errorMensaje);
                }
            }
        }
    }
}

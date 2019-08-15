package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Preselecion.Fragment_Preselecion_Tinas;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.TinaEscaneo;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ValidaTina extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL = "http://10.50.1.15:7080/api_simulador_movil/v1/catalogos/tinas/%s";
    private String codigo;
    private TinaEscaneo resultadoTina;
    private ErrorServicio errorMensaje;
    private Fragment pantalla;

    public ValidaTina(String codigo, Fragment pantalla){
        this.codigo = codigo;
        this.pantalla = pantalla;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        String urlValores = this.URL;
        urlValores = String.format(urlValores, this.codigo);
        HttpClient conexion = new DefaultHttpClient();
        HttpGet peticion = new HttpGet(urlValores);

        peticion.setHeader("content-type", "application/json");

        try {
            HttpResponse respuesta = conexion.execute(peticion);
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
                Gson gson = new Gson();

                this.resultadoTina = gson.fromJson(respuestaJson, TinaEscaneo.class);
                return true;
            }else{
                this.errorMensaje = new ErrorServicio();
                this.errorMensaje.setResultado("NO");
                this.errorMensaje.setMensaje("Error al conectar con el servidor.");
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
            ( (Fragment_Preselecion_Tinas) this.pantalla ).resultadoEscaneoTina(this.resultadoTina);
        }else{
            ( (Fragment_Preselecion_Tinas) this.pantalla ).terminaProcesandoEmergente();
            ( (Fragment_Preselecion_Tinas) this.pantalla ).errorServicioAsignados(this.errorMensaje);
        }
    }
}

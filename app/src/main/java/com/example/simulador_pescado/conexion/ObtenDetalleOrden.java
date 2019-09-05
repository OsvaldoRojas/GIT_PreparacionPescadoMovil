package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Fragment_AsignaMecanico;
import com.example.simulador_pescado.Fragment_DetalleOrden;
import com.example.simulador_pescado.Utilerias.Constantes;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.OrdenMantenimiento;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ObtenDetalleOrden extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL = "/ordenes/mantenimientos/%s";
    private long idOrdenMantenimiento;
    private OrdenMantenimiento detalleOrden;
    private ErrorServicio errorMensaje;
    private Fragment pantalla;

    public ObtenDetalleOrden(Fragment pantalla, long idOrdenMantenimiento){
        this.pantalla = pantalla;
        this.idOrdenMantenimiento = idOrdenMantenimiento;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        String urlValores = this.URL;
        urlValores = String.format(urlValores, this.idOrdenMantenimiento);
        HttpClient conexion = new DefaultHttpClient();
        HttpGet peticion = new HttpGet( Constantes.URL_SERVICIOS.concat(urlValores) );

        peticion.setHeader("content-type", "application/json");

        try {
            HttpResponse respuesta = conexion.execute(peticion);
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
                byte resultadoByte[] = respuestaJson.getBytes("ISO-8859-1");
                String resultadoFinal = new String(resultadoByte, "UTF-8");
                Gson gson = new Gson();
                this.detalleOrden = gson.fromJson(resultadoFinal, OrdenMantenimiento.class);
                return true;
            }else{
                this.errorMensaje = new ErrorServicio();
                this.errorMensaje.setResultado("NO");
                this.errorMensaje.setMensaje("Error al conectar con el servidor");
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.errorMensaje = new ErrorServicio();
            this.errorMensaje.setResultado("NO");
            this.errorMensaje.setMensaje("Error al conectar con el servidor");
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            if( this.pantalla instanceof Fragment_DetalleOrden ){
                ( (Fragment_DetalleOrden) this.pantalla ).resultadoDetalleOrden(this.detalleOrden);
            }else{
                if( this.pantalla instanceof Fragment_AsignaMecanico ){
                    ( (Fragment_AsignaMecanico) this.pantalla ).resultadoDetalleOrden(this.detalleOrden);
                }
            }
        }else{
            if( this.pantalla instanceof Fragment_DetalleOrden ){
                ( (Fragment_DetalleOrden) this.pantalla ).terminaProcesando();
                ( (Fragment_DetalleOrden) this.pantalla ).errorServicio(this.errorMensaje);
            }else{
                if( this.pantalla instanceof Fragment_AsignaMecanico ){
                    ( (Fragment_AsignaMecanico) this.pantalla ).terminaProcesando();
                    ( (Fragment_AsignaMecanico) this.pantalla ).errorServicio(this.errorMensaje);
                }
            }
        }
    }
}

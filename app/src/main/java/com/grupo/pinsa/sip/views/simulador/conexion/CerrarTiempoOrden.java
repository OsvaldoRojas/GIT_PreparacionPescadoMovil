package com.grupo.pinsa.sip.views.simulador.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.grupo.pinsa.sip.views.simulador.orden.Fragment_OrdenMantenimiento;
import com.grupo.pinsa.sip.views.simulador.utilerias.Constantes;
import com.grupo.pinsa.sip.views.simulador.modelo.ErrorServicio;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CerrarTiempoOrden extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL = "/ordenes/mantenimientos/finalizar";
    private ErrorServicio errorMensaje;
    private Fragment pantalla;
    private String fecha;
    private long idOrden;

    public CerrarTiempoOrden(Fragment pantalla, String fecha, long idOrden){
        this.pantalla = pantalla;
        this.fecha = fecha;
        this.idOrden = idOrden;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        HttpClient conexion = new DefaultHttpClient();
        HttpPut solicitudPut = new HttpPut( Constantes.URL_SERVICIOS.concat(this.URL) );

        solicitudPut.setHeader("content-type", "application/json");

        String json;
        if( this.fecha != null ){
            json = "{\"idOrdenMantenimiento\":".concat( String.valueOf(this.idOrden) )
                    .concat(",\"fechaFin\":\"").concat(this.fecha)
                    .concat("\",\"usuario\":\"").concat( UsuarioLogueado.getUsuarioLogueado().getClave_usuario() )
                    .concat("\"}");
        }else{
            json = "{\"idOrdenMantenimiento\":".concat( String.valueOf(this.idOrden) ).concat(",\"fechaFin\":null,\"usuario\":\"")
                    .concat( UsuarioLogueado.getUsuarioLogueado().getClave_usuario() )
                    .concat("\"}");
        }
        try {
            solicitudPut.setEntity( new StringEntity(json) );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            this.errorMensaje = new ErrorServicio();
            this.errorMensaje.setResultado("NO");
            this.errorMensaje.setMensaje("Error al realizar la solicitud");
            return false;
        }

        try {
            HttpResponse respuesta = conexion.execute(solicitudPut);
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                return true;
            }else{
                Gson gson = new Gson();
                String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
                this.errorMensaje = gson.fromJson(respuestaJson, ErrorServicio.class);
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
            ( (Fragment_OrdenMantenimiento) this.pantalla ).resultadoCierraTiempo();
        }else {
            ( (Fragment_OrdenMantenimiento) this.pantalla ).terminaProcesando();
            ( (Fragment_OrdenMantenimiento) this.pantalla ).errorServicio(this.errorMensaje);
        }
    }
}

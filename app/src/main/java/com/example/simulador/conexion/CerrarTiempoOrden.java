package com.example.simulador.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador.atemperado.Fragment_Atemperado_OM;
import com.example.simulador.descongelado.Fragment_Descongelado_OM;
import com.example.simulador.preselecion.Fragment_Preselecion_OM;
import com.example.simulador.utilerias.Constantes;
import com.example.simulador.vista.ErrorServicio;
import com.example.simulador.vista.UsuarioLogueado;
import com.google.gson.Gson;

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
                    .concat("\",\"usuario\":\"").concat( UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() )
                    .concat("\"}");
        }else{
            json = "{\"idOrdenMantenimiento\":".concat( String.valueOf(this.idOrden) ).concat(",\"fechaFin\":null,\"usuario\":\"")
                    .concat( UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() )
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
            if( this.pantalla instanceof Fragment_Preselecion_OM ){
                ( (Fragment_Preselecion_OM) this.pantalla ).resultadoCierraTiempo();
            }else{
                if( this.pantalla instanceof Fragment_Atemperado_OM ){
                    ( (Fragment_Atemperado_OM) this.pantalla ).resultadoCierraTiempo();
                }else{
                    if( this.pantalla instanceof Fragment_Descongelado_OM){
                        ( (Fragment_Descongelado_OM) this.pantalla ).resultadoCierraTiempo();
                    }
                }
            }
        }else {
            if( this.pantalla instanceof Fragment_Preselecion_OM ){
                ( (Fragment_Preselecion_OM) this.pantalla ).terminaProcesando();
                ( (Fragment_Preselecion_OM) this.pantalla ).errorServicio(this.errorMensaje);
            }else{
                if( this.pantalla instanceof Fragment_Atemperado_OM ){
                    ( (Fragment_Atemperado_OM) this.pantalla ).terminaProcesando();
                    ( (Fragment_Atemperado_OM) this.pantalla ).errorServicio(this.errorMensaje);
                }else{
                    if( this.pantalla instanceof Fragment_Descongelado_OM ){
                        ( (Fragment_Descongelado_OM) this.pantalla ).terminaProcesando();
                        ( (Fragment_Descongelado_OM) this.pantalla ).errorServicio(this.errorMensaje);
                    }
                }
            }
        }
    }
}

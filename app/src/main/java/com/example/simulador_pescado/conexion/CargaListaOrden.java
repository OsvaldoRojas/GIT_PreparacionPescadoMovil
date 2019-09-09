package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Atemperado.Fragment_Atemperado_OM;
import com.example.simulador_pescado.Descongelado.Fragment_Descongelado_OM;
import com.example.simulador_pescado.Preselecion.Fragment_Preselecion_OM;
import com.example.simulador_pescado.Utilerias.Constantes;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.ListaOrdenMantenimientoServicio;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CargaListaOrden extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL = "/ordenes/mantenimientos?idEtapa=%s&idEmpleado=%s";
    private int idEtapa;
    private int idEmpleado;
    private ErrorServicio errorMensaje;
    private List<ListaOrdenMantenimientoServicio> listaOrden = new ArrayList<>();
    private Fragment pantalla;

    public CargaListaOrden(int idEtapa, int idEmpleado, Fragment pantalla){
        this.idEtapa = idEtapa;
        this.idEmpleado = idEmpleado;
        this.pantalla = pantalla;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        String urlValores = this.URL;
        urlValores = String.format(urlValores, this.idEtapa, this.idEmpleado);
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

                Type listaPlantilla = new TypeToken<List<ListaOrdenMantenimientoServicio>>(){}.getType();
                this.listaOrden = gson.fromJson(resultadoFinal, listaPlantilla);
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
            if( this.pantalla instanceof Fragment_Preselecion_OM ){
                ( (Fragment_Preselecion_OM) this.pantalla ).resultadoServicioLista(this.listaOrden);
            }else{
                if( this.pantalla instanceof Fragment_Atemperado_OM ){
                    ( (Fragment_Atemperado_OM) this.pantalla ).resultadoServicioLista(this.listaOrden);
                }else{
                    if( this.pantalla instanceof Fragment_Descongelado_OM ){
                        ( (Fragment_Descongelado_OM) this.pantalla ).resultadoServicioLista(this.listaOrden);
                    }
                }
            }
        }else{
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

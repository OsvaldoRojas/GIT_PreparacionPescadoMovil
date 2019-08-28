package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import com.example.simulador_pescado.Utilerias.Constantes;
import com.example.simulador_pescado.Utilerias.Catalogos;
import com.example.simulador_pescado.vista.Etapa;
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

public class CargaCatalogoEtapa extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL = "/catalogos/etapas";
    private List<Etapa> listaEtapas = new ArrayList<>();

    @Override
    protected Boolean doInBackground(Void... voids) {
        HttpClient conexion = new DefaultHttpClient();
        HttpGet peticion = new HttpGet( Constantes.URL_SERVICIOS.concat(this.URL) );

        peticion.setHeader("content-type", "application/json");

        try {
            HttpResponse respuesta = conexion.execute(peticion);
            String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
            Gson gson = new Gson();
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                Type listaPlantilla = new TypeToken<List<Etapa>>(){}.getType();
                this.listaEtapas = gson.fromJson(respuestaJson, listaPlantilla);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            Catalogos.getInstancia().setCatalogoEtapa(this.listaEtapas);
        }
    }
}

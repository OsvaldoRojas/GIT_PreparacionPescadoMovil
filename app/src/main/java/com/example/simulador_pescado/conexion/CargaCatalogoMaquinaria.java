package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import com.example.simulador_pescado.utilerias.Catalogos;
import com.example.simulador_pescado.utilerias.Constantes;
import com.example.simulador_pescado.vista.Maquinaria;
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

public class CargaCatalogoMaquinaria extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL = "/catalogos/maquinarias/%s";
    private List<Maquinaria> listaMaquinaria = new ArrayList<>();
    private int etapa;

    public CargaCatalogoMaquinaria(int etapa){
        this.etapa = etapa;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        String urlValores = this.URL;
        urlValores = String.format(urlValores, this.etapa);
        HttpClient conexion = new DefaultHttpClient();
        HttpGet peticion = new HttpGet( Constantes.URL_SERVICIOS.concat(urlValores) );

        peticion.setHeader("content-type", "application/json");

        try {
            HttpResponse respuesta = conexion.execute(peticion);
            String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
            byte resultadoByte[] = respuestaJson.getBytes("ISO-8859-1");
            String resultadoFinal = new String(resultadoByte, "UTF-8");
            Gson gson = new Gson();
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                Type listaPlantilla = new TypeToken<List<Maquinaria>>(){}.getType();
                this.listaMaquinaria = gson.fromJson(resultadoFinal, listaPlantilla);
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
            Catalogos.getInstancia().setCatalogoMaquinaria(this.listaMaquinaria);
        }
    }
}

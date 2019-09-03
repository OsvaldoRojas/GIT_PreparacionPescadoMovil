package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Atemperado.Fragment_Atemperado_CreaOrdenMantenimiento;
import com.example.simulador_pescado.Preselecion.Fragment_Preseleccion_CreaOrdenMantenimiento;
import com.example.simulador_pescado.Utilerias.Constantes;
import com.example.simulador_pescado.vista.Artefacto;
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

public class ObtenArtefactosMaquinaria extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL = "/catalogos/maquinarias/%s/artefactos";
    private List<Artefacto> listaArtefactos = new ArrayList<>();
    private int maquinaria;
    private Fragment pantalla;

    public ObtenArtefactosMaquinaria(Fragment pantalla, int maquinaria){
        this.maquinaria = maquinaria;
        this.pantalla = pantalla;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        String urlValores = this.URL;
        urlValores = String.format(urlValores, this.maquinaria);
        HttpClient conexion = new DefaultHttpClient();
        HttpGet peticion = new HttpGet( Constantes.URL_SERVICIOS.concat(urlValores) );

        peticion.setHeader("content-type", "application/json");

        try {
            HttpResponse respuesta = conexion.execute(peticion);
            String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
            Gson gson = new Gson();
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                Type listaPlantilla = new TypeToken<List<Artefacto>>(){}.getType();
                this.listaArtefactos = gson.fromJson(respuestaJson, listaPlantilla);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if( this.pantalla instanceof Fragment_Preseleccion_CreaOrdenMantenimiento ){
            ( (Fragment_Preseleccion_CreaOrdenMantenimiento) this.pantalla ).resultadoCatalogoArtefacto(this.listaArtefactos);
        }else{
            if( this.pantalla instanceof Fragment_Atemperado_CreaOrdenMantenimiento ){
                ( (Fragment_Atemperado_CreaOrdenMantenimiento) this.pantalla ).resultadoCatalogoArtefacto(this.listaArtefactos);
            }
        }
    }
}

package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Preselecion.Fragment_Preselecion_Tinas;
import com.example.simulador_pescado.vista.GrupoEspecie;
import com.example.simulador_pescado.vista.Subtalla;
import com.example.simulador_pescado.vista.Talla;
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

public class CargaCatalogos extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL_TALLA = "http://10.50.1.15:7080/api_simulador_movil/v1/catalogos/tallas";
    private static final String URL_SUBTALLA = "http://10.50.1.15:7080/api_simulador_movil/v1/catalogos/subtallas";
    private static final String URL_ESPECIE = "http://10.50.1.15:7080/api_simulador_movil/v1/catalogos/grupos-especies";
    private List<Talla> listaTallas = new ArrayList<>();
    private List<Subtalla> listaSubtallas = new ArrayList<>();
    private List<GrupoEspecie> listaEspecies = new ArrayList<>();
    private Fragment fragment;

    public CargaCatalogos(Fragment fragment){
        this.fragment = fragment;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        HttpClient conexion = new DefaultHttpClient();
        HttpGet peticionTalla = new HttpGet(this.URL_TALLA);
        HttpGet peticionSubtalla = new HttpGet(this.URL_SUBTALLA);
        HttpGet peticionEspecie = new HttpGet(this.URL_ESPECIE);

        peticionTalla.setHeader("content-type", "application/json");
        peticionSubtalla.setHeader("content-type", "application/json");
        peticionEspecie.setHeader("content-type", "application/json");

        try {
            HttpResponse respuesta = conexion.execute(peticionTalla);
            String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
            Gson gson = new Gson();
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                Type listaPlantilla = new TypeToken<List<Talla>>(){}.getType();
                this.listaTallas = gson.fromJson(respuestaJson, listaPlantilla);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse respuesta = conexion.execute(peticionSubtalla);
            String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
            Gson gson = new Gson();
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                Type listaPlantilla = new TypeToken<List<Subtalla>>(){}.getType();
                this.listaSubtallas = gson.fromJson(respuestaJson, listaPlantilla);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse respuesta = conexion.execute(peticionEspecie);
            String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
            Gson gson = new Gson();
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                Type listaPlantilla = new TypeToken<List<GrupoEspecie>>(){}.getType();
                this.listaEspecies = gson.fromJson(respuestaJson, listaPlantilla);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        ( (Fragment_Preselecion_Tinas) this.fragment )
                .actualizaCatalogos(this.listaTallas, this.listaSubtallas, this.listaEspecies);
    }
}

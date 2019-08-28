package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import com.example.simulador_pescado.Utilerias.Catalogos;
import com.example.simulador_pescado.Utilerias.Constantes;
import com.example.simulador_pescado.vista.Especialidad;
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

public class CargaCatalogosTina extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL_TALLA = "/catalogos/tallas";
    private static final String URL_SUBTALLA = "/catalogos/subtallas";
    private static final String URL_ESPECIE = "/catalogos/especies";
    private static final String URL_ESPECIALIDAD = "/catalogos/especialidades";
    private List<Talla> listaTallas = new ArrayList<>();
    private List<Subtalla> listaSubtallas = new ArrayList<>();
    private List<GrupoEspecie> listaEspecies = new ArrayList<>();
    private List<Especialidad> listaEspecialidad = new ArrayList<>();

    @Override
    protected Boolean doInBackground(Void... voids) {
        HttpClient conexion = new DefaultHttpClient();
        HttpGet peticionTalla = new HttpGet( Constantes.URL_SERVICIOS.concat(this.URL_TALLA) );
        HttpGet peticionSubtalla = new HttpGet( Constantes.URL_SERVICIOS.concat(this.URL_SUBTALLA) );
        HttpGet peticionEspecie = new HttpGet( Constantes.URL_SERVICIOS.concat(this.URL_ESPECIE) );
        HttpGet peticionEspecialidad = new HttpGet( Constantes.URL_SERVICIOS.concat(this.URL_ESPECIALIDAD) );

        peticionTalla.setHeader("content-type", "application/json");
        peticionSubtalla.setHeader("content-type", "application/json");
        peticionEspecie.setHeader("content-type", "application/json");
        peticionEspecialidad.setHeader("content-type", "application/json");

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
            return false;
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
            return false;
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
            return false;
        }

        try {
            HttpResponse respuesta = conexion.execute(peticionEspecialidad);
            String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
            Gson gson = new Gson();
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                Type listaPlantilla = new TypeToken<List<Especialidad>>(){}.getType();
                this.listaEspecialidad = gson.fromJson(respuestaJson, listaPlantilla);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            Catalogos.getInstancia().setCatalogoGrupoEspecie(this.listaEspecies);
            Catalogos.getInstancia().setCatalogoTalla(this.listaTallas);
            Catalogos.getInstancia().setCatalogoSubtalla(this.listaSubtallas);
            Catalogos.getInstancia().setCatalogoEspecialidad(this.listaEspecialidad);
        }
    }
}

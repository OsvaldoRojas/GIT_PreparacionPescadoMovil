package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Preselecion.Fragment_Preselecion_Tinas;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.OperadorBascula;
import com.example.simulador_pescado.vista.Tina;
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

public class ObtenAsignados extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL_POSICIONES = "http://10.50.1.15:7080/api_simulador_prep_pescado/v1/dumies/posiciones";
    private static final String URL_ESTACIONES = "http://10.50.1.15:7080/api_simulador_prep_pescado/v1/dumies/estaciones";
    private List<Tina> listaTinas = new ArrayList<>();
    private List<OperadorBascula> listaOperadores = new ArrayList<>();
    private ErrorServicio errorMensaje;
    private Fragment pantalla;

    public ObtenAsignados(Fragment pantalla){
        this.pantalla = pantalla;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        HttpClient conexion = new DefaultHttpClient();
        HttpGet peticionPosiciones = new HttpGet(this.URL_POSICIONES);
        HttpGet peticionEstaciones = new HttpGet(this.URL_ESTACIONES);

        peticionPosiciones.setHeader("content-type", "application/json");
        peticionEstaciones.setHeader("content-type", "application/json");

        try {
            HttpResponse respuesta = conexion.execute(peticionPosiciones);
            String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
            Gson gson = new Gson();
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                Type listaPlantilla = new TypeToken<List<Tina>>(){}.getType();
                this.listaTinas = gson.fromJson(respuestaJson, listaPlantilla);
            }else{
                this.errorMensaje = gson.fromJson(respuestaJson, ErrorServicio.class);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.errorMensaje = new ErrorServicio();
            this.errorMensaje.setCode(0);
            this.errorMensaje.setMessage("Error al conectar con el servidor.");
            return false;
        }

        try {
            HttpResponse respuesta = conexion.execute(peticionEstaciones);
            String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
            Gson gson = new Gson();
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                Type listaPlantilla = new TypeToken<List<OperadorBascula>>(){}.getType();
                this.listaOperadores = gson.fromJson(respuestaJson, listaPlantilla);
                return true;
            }else{
                this.errorMensaje = gson.fromJson(respuestaJson, ErrorServicio.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.errorMensaje = new ErrorServicio();
            this.errorMensaje.setCode(0);
            this.errorMensaje.setMessage("Error al conectar con el servidor.");
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            ( (Fragment_Preselecion_Tinas) this.pantalla ).ordenaTinas(this.listaTinas);
            ( (Fragment_Preselecion_Tinas) this.pantalla ).ordenaOperadoresBascula(this.listaOperadores);
            ( (Fragment_Preselecion_Tinas) this.pantalla ).terminaProcesando();
        }else{
            ( (Fragment_Preselecion_Tinas) this.pantalla ).terminaProcesando();
            ( (Fragment_Preselecion_Tinas) this.pantalla ).errorServicio(this.errorMensaje);
        }
    }
}

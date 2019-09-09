package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Atemperado.Fragment_Atemperado_Plan;
import com.example.simulador_pescado.Utilerias.Constantes;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.PosicionEstibaAtemperado;
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

public class ObtenPosiciones extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL_POSICIONES = "/atemperado/posiciones/tinas";
    private List<PosicionEstibaAtemperado> posiciones = new ArrayList<>();
    private ErrorServicio errorMensaje;
    private Fragment pantalla;

    public ObtenPosiciones(Fragment pantalla){
        this.pantalla = pantalla;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        HttpClient conexion = new DefaultHttpClient();
        HttpGet peticionPosiciones = new HttpGet( Constantes.URL_SERVICIOS.concat(this.URL_POSICIONES) );

        peticionPosiciones.setHeader("content-type", "application/json");

        Gson gson = new Gson();
        try {
            HttpResponse respuesta = conexion.execute(peticionPosiciones);
            String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                Type listaPlantilla = new TypeToken<List<PosicionEstibaAtemperado>>(){}.getType();
                this.posiciones = gson.fromJson(respuestaJson, listaPlantilla);
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
            ( (Fragment_Atemperado_Plan) this.pantalla ).resultadoPosiciones(this.posiciones);
            ( (Fragment_Atemperado_Plan) this.pantalla ).terminaProcesando();
        }else{
            ( (Fragment_Atemperado_Plan) this.pantalla ).terminaProcesando();
            ( (Fragment_Atemperado_Plan) this.pantalla ).errorServicio(this.errorMensaje);
        }
    }
}

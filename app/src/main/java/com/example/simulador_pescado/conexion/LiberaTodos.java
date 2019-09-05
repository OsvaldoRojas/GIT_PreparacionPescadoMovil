package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Preselecion.Fragment_Preselecion_Tinas;
import com.example.simulador_pescado.Utilerias.Constantes;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.UsuarioLogueado;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LiberaTodos extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL = "/preseleccion/operadores/liberar-todos";
    private ErrorServicio errorMensaje;
    private Fragment pantalla;

    public LiberaTodos(Fragment pantalla){
        this.pantalla = pantalla;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        HttpClient conexion = new DefaultHttpClient();
        HttpPut solicitudPut = new HttpPut( Constantes.URL_SERVICIOS.concat(this.URL) );

        Gson gson = new Gson();
        solicitudPut.setHeader("content-type", "application/json");

        //String json = "{ \"usuario\": \"usuarioerp\" }";
        String json = "{\"usuario\":\""
                .concat( UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() )
                .concat("\"}");
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
            ( (Fragment_Preselecion_Tinas) this.pantalla ).resultadoLiberarTodos();
        }else {
            ( (Fragment_Preselecion_Tinas) this.pantalla ).terminaProcesando();
            ( (Fragment_Preselecion_Tinas) this.pantalla ).errorServicio(this.errorMensaje);
        }
    }
}

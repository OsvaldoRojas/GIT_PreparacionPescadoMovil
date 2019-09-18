package com.example.simulador_pescado.conexion;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.simulador_pescado.LoginActivity;
import com.example.simulador_pescado.utilerias.Constantes;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.Usuario;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class InicioSesion extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL = "/movil/seguridad/acceso_sistema?clave=%s&contrasena=%s&tipo_modulo=%s";
    private String usuario;
    private String contrasena;
    private String modulo;
    private Usuario usuarioLogueado;
    private ErrorServicio errorMensaje;
    private Activity actividad;

    public InicioSesion(String usuario, String contrasena, String modulo, Activity actividad){
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.modulo = modulo;
        this.actividad = actividad;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        String urlValores = this.URL;
        urlValores = String.format(urlValores, this.usuario, this.contrasena, this.modulo);
        HttpClient conexion = new DefaultHttpClient();
        HttpGet peticion = new HttpGet( Constantes.URL_SERVICIOS_PINSA.concat(urlValores) );

        peticion.setHeader("content-type", "application/json");

        try {
            HttpResponse respuesta = conexion.execute(peticion);
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
                Gson gson = new Gson();

                if( respuestaJson.contains("\"resultado\":\"NO\"") ){
                    this.errorMensaje = gson.fromJson(respuestaJson, ErrorServicio.class);
                    return false;
                }

                this.usuarioLogueado = gson.fromJson(respuestaJson, Usuario.class);
                return true;
            }else{
                this.errorMensaje = new ErrorServicio();
                this.errorMensaje.setResultado("NO");
                this.errorMensaje.setMensaje("Error al conectar con el servidor.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.errorMensaje = new ErrorServicio();
            this.errorMensaje.setResultado("NO");
            this.errorMensaje.setMensaje("Error al conectar con el servidor.");
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            ( (LoginActivity) this.actividad ).iniciaSesion(this.usuarioLogueado);
        }else{
            ( (LoginActivity) this.actividad ).errorInicio(this.errorMensaje);
        }
    }
}

package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Atemperado.Fragment_Atemperado_AsignaMecanico;
import com.example.simulador_pescado.Descongelado.Fragment_Descongelado_AsignaMecanico;
import com.example.simulador_pescado.Preselecion.Fragment_Preseleccion_AsignaMecanico;
import com.example.simulador_pescado.Preselecion.Fragment_Asigna_Montacargas;
import com.example.simulador_pescado.Preselecion.Fragment_Asigna_Operador;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.Gafete;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ValidaGafete extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL = "http://10.50.1.15:7080/sip/reservamateriales_api/fortia/empleados/%s";
    private String codigo;
    private Gafete resultadoGafete;
    private ErrorServicio errorMensaje;
    private Fragment pantalla;

    public ValidaGafete(Fragment pantalla, String codigo){
        this.pantalla = pantalla;
        this.codigo = codigo;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        String urlValores = this.URL;
        urlValores = String.format(urlValores, this.codigo);
        HttpClient conexion = new DefaultHttpClient();
        HttpGet peticion = new HttpGet(urlValores);

        peticion.setHeader("content-type", "application/json");

        try {
            HttpResponse respuesta = conexion.execute(peticion);
            if( respuesta.getStatusLine().getStatusCode() == 200 ){
                String respuestaJson = EntityUtils.toString( respuesta.getEntity() );
                Gson gson = new Gson();

                this.resultadoGafete = gson.fromJson(respuestaJson, Gafete.class);
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
            if(this.pantalla instanceof Fragment_Asigna_Operador){
                ( (Fragment_Asigna_Operador) this.pantalla ).resultadoEscaneoGafete(this.resultadoGafete);
            }else{
                if(this.pantalla instanceof Fragment_Asigna_Montacargas){
                    ( (Fragment_Asigna_Montacargas) this.pantalla ).resultadoEscaneoGafete(this.resultadoGafete);
                }else{
                    if(this.pantalla instanceof Fragment_Preseleccion_AsignaMecanico){
                        ( (Fragment_Preseleccion_AsignaMecanico) this.pantalla ).resultadoEscaneoGafete(this.resultadoGafete);
                    }
                    else{
                        if(this.pantalla instanceof Fragment_Atemperado_AsignaMecanico){
                            ( (Fragment_Atemperado_AsignaMecanico) this.pantalla ).resultadoEscaneoGafete(this.resultadoGafete);
                        }else{
                            if(this.pantalla instanceof Fragment_Descongelado_AsignaMecanico){
                                ( (Fragment_Descongelado_AsignaMecanico) this.pantalla ).resultadoEscaneoGafete(this.resultadoGafete);
                            }
                        }
                    }
                }
            }
        }else{
            if(this.pantalla instanceof Fragment_Asigna_Operador){
                ( (Fragment_Asigna_Operador) this.pantalla ).terminaProcesando();
                ( (Fragment_Asigna_Operador) this.pantalla ).errorServicio(this.errorMensaje);
            }else{
                if(this.pantalla instanceof Fragment_Asigna_Montacargas){
                    ( (Fragment_Asigna_Montacargas) this.pantalla ).terminaProcesando();
                    ( (Fragment_Asigna_Montacargas) this.pantalla ).errorServicio(this.errorMensaje);
                }else{
                    if(this.pantalla instanceof Fragment_Preseleccion_AsignaMecanico){
                        ( (Fragment_Preseleccion_AsignaMecanico) this.pantalla ).terminaProcesando();
                        ( (Fragment_Preseleccion_AsignaMecanico) this.pantalla ).errorServicio(this.errorMensaje);
                    }else{
                        if(this.pantalla instanceof Fragment_Atemperado_AsignaMecanico){
                            ( (Fragment_Atemperado_AsignaMecanico) this.pantalla ).terminaProcesando();
                            ( (Fragment_Atemperado_AsignaMecanico) this.pantalla ).errorServicio(this.errorMensaje);
                        }else{
                            if(this.pantalla instanceof Fragment_Descongelado_AsignaMecanico){
                                ( (Fragment_Descongelado_AsignaMecanico) this.pantalla ).terminaProcesando();
                                ( (Fragment_Descongelado_AsignaMecanico) this.pantalla ).errorServicio(this.errorMensaje);
                            }
                        }
                    }
                }
            }
        }
    }
}

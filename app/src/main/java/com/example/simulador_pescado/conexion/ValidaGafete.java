package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Atemperado.AsignarMecanicoAtemperado;
import com.example.simulador_pescado.Atemperado.Fragment_Atemperado_OM;
import com.example.simulador_pescado.Descongelado.Fragment_Descongelado_OM;
import com.example.simulador_pescado.Preselecion.AsignarMecanicoPreseleccion;
import com.example.simulador_pescado.Preselecion.AsignarMontacargas;
import com.example.simulador_pescado.Preselecion.AsignarOperador;
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
            if(this.pantalla instanceof AsignarOperador){
                ( (AsignarOperador) this.pantalla ).resultadoEscaneoGafete(this.resultadoGafete);
            }else{
                if(this.pantalla instanceof AsignarMontacargas){
                    ( (AsignarMontacargas) this.pantalla ).resultadoEscaneoGafete(this.resultadoGafete);
                }else{
                    if(this.pantalla instanceof AsignarMecanicoPreseleccion){
                        ( (AsignarMecanicoPreseleccion) this.pantalla ).resultadoEscaneoGafete(this.resultadoGafete);
                    }
                    else{
                        if(this.pantalla instanceof AsignarMecanicoAtemperado){
                            ( (AsignarMecanicoAtemperado) this.pantalla ).resultadoEscaneoGafete(this.resultadoGafete);
                        }else{
                            if(this.pantalla instanceof Fragment_Descongelado_OM){
                                ( (Fragment_Descongelado_OM) this.pantalla ).resultadoEscaneoGafete(this.resultadoGafete);
                            }
                        }
                    }
                }
            }
        }else{
            if(this.pantalla instanceof AsignarOperador){
                ( (AsignarOperador) this.pantalla ).terminaProcesando();
                ( (AsignarOperador) this.pantalla ).errorServicio(this.errorMensaje);
            }else{
                if(this.pantalla instanceof AsignarMontacargas){
                    ( (AsignarMontacargas) this.pantalla ).terminaProcesando();
                    ( (AsignarMontacargas) this.pantalla ).errorServicio(this.errorMensaje);
                }else{
                    if(this.pantalla instanceof AsignarMecanicoPreseleccion){
                        ( (AsignarMecanicoPreseleccion) this.pantalla ).terminaProcesando();
                        ( (AsignarMecanicoPreseleccion) this.pantalla ).errorServicio(this.errorMensaje);
                    }else{
                        if(this.pantalla instanceof AsignarMecanicoAtemperado){
                            ( (AsignarMecanicoAtemperado) this.pantalla ).terminaProcesando();
                            ( (AsignarMecanicoAtemperado) this.pantalla ).errorServicio(this.errorMensaje);
                        }else{
                            if(this.pantalla instanceof Fragment_Descongelado_OM){
                                ( (Fragment_Descongelado_OM) this.pantalla ).terminaProcesandoEmergente();
                                ( (Fragment_Descongelado_OM) this.pantalla ).errorServicioAsignados(this.errorMensaje);
                            }
                        }
                    }
                }
            }
        }
    }
}

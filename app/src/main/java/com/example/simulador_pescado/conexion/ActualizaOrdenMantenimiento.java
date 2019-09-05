package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Fragment_AsignaMecanico;
import com.example.simulador_pescado.Fragment_DetalleOrden;
import com.example.simulador_pescado.Utilerias.Constantes;
import com.example.simulador_pescado.Utilerias.Utilerias;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.OrdenMantenimiento;
import com.example.simulador_pescado.vista.OrdenMantenimientoActualizar;
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

public class ActualizaOrdenMantenimiento extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL = "/ordenes/mantenimientos";
    private ErrorServicio errorMensaje;
    private Fragment pantalla;
    private OrdenMantenimiento ordenMantenimiento;

    public ActualizaOrdenMantenimiento(Fragment pantalla, OrdenMantenimiento ordenMantenimiento){
        this.pantalla = pantalla;
        this.ordenMantenimiento = ordenMantenimiento;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        OrdenMantenimientoActualizar orden = new OrdenMantenimientoActualizar();
        orden.setIdOrdenMantenimiento( this.ordenMantenimiento.getIdOrdenMantenimiento() );
        orden.setIdEmpleado( this.ordenMantenimiento.getIdEmpleado() );
        orden.setNombreEmpleado( this.ordenMantenimiento.getNombreEmpleado() );
        orden.setaPaternoEmpleado( this.ordenMantenimiento.getaPaternoEmpleado() );
        orden.setaMaternoEmpleado( this.ordenMantenimiento.getaMaternoEmpleado() );
        orden.setFechaInicio( this.ordenMantenimiento.getFechaInicio() );
        orden.setSolucion( Utilerias.cambiaAcentos( this.ordenMantenimiento.getSolucion() ) );
        orden.setFinalizada( this.ordenMantenimiento.getFinalizada() );
        orden.setUsuario( UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() );

        HttpClient conexion = new DefaultHttpClient();
        HttpPut solicitudPut = new HttpPut( Constantes.URL_SERVICIOS.concat(this.URL) );

        Gson gson = new Gson();
        solicitudPut.setHeader("content-type", "application/json");
        try {
            solicitudPut.setEntity( new StringEntity( gson.toJson(orden) ) );
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

    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            if( this.pantalla instanceof Fragment_AsignaMecanico ){
                ( (Fragment_AsignaMecanico) this.pantalla ).resultadoActualizaOrden();
            }else{
                if( this.pantalla instanceof Fragment_DetalleOrden ){
                    ( (Fragment_DetalleOrden) this.pantalla ).resultadoActualizaOrden();
                }
            }
        }else{
            if( this.pantalla instanceof Fragment_AsignaMecanico ){
                ( (Fragment_AsignaMecanico) this.pantalla ).terminaProcesando();
                ( (Fragment_AsignaMecanico) this.pantalla ).errorServicio(this.errorMensaje);
            }else{
                if( this.pantalla instanceof Fragment_DetalleOrden ){
                    ( (Fragment_DetalleOrden) this.pantalla ).terminaProcesando();
                    ( (Fragment_DetalleOrden) this.pantalla ).errorServicio(this.errorMensaje);
                }
            }
        }
    }
}

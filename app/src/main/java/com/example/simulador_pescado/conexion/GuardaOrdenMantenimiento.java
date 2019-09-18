package com.example.simulador_pescado.conexion;

import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Fragment_CreaOrdenMantenimiento;
import com.example.simulador_pescado.utilerias.Constantes;
import com.example.simulador_pescado.utilerias.Utilerias;
import com.example.simulador_pescado.vista.Artefacto;
import com.example.simulador_pescado.vista.ArtefactoServicio;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.OrdenMantenimiento;
import com.example.simulador_pescado.vista.OrdenMantenimientoGuardar;
import com.example.simulador_pescado.vista.UsuarioLogueado;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class GuardaOrdenMantenimiento extends AsyncTask<Void,Integer,Boolean> {

    private static final String URL = "/ordenes/mantenimientos";
    private ErrorServicio errorMensaje;
    private Fragment pantalla;
    private OrdenMantenimiento ordenMantenimiento;

    public GuardaOrdenMantenimiento(Fragment pantalla, OrdenMantenimiento ordenMantenimiento){
        this.pantalla = pantalla;
        this.ordenMantenimiento = ordenMantenimiento;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        OrdenMantenimientoGuardar orden = new OrdenMantenimientoGuardar();
        orden.setIdOrdenMantenimiento(0);
        orden.setIdMaquinaria( this.ordenMantenimiento.getMaquinaria().getIdMaquinaria() );
        orden.setDescripcion( Utilerias.cambiaAcentos( this.ordenMantenimiento.getDescripcion() ) );
        orden.setUsuario( UsuarioLogueado.getUsuarioLogueado(null).getClave_usuario() );
        List<ArtefactoServicio> lista = new ArrayList<>();
        for( Artefacto artefacto : this.ordenMantenimiento.getArtefactos() ){
            ArtefactoServicio artefactoServicio = new ArtefactoServicio();
            artefactoServicio.setIdMaquinariaArtefacto( artefacto.getIdMaquinariaArtefacto() );
            lista.add(artefactoServicio);
        }
        orden.setArtefactos(lista);

        HttpClient conexion = new DefaultHttpClient();
        HttpPost solicitudPost = new HttpPost( Constantes.URL_SERVICIOS.concat(this.URL) );

        Gson gson = new Gson();
        solicitudPost.setHeader("content-type", "application/json");
        try {
            solicitudPost.setEntity( new StringEntity( gson.toJson(orden) ) );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            this.errorMensaje = new ErrorServicio();
            this.errorMensaje.setResultado("NO");
            this.errorMensaje.setMensaje("Error al realizar la solicitud");
            return false;
        }

        try {
            HttpResponse respuesta = conexion.execute(solicitudPost);
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
            ( (Fragment_CreaOrdenMantenimiento) this.pantalla ).resultadoGuardadoOrden();
        }else{
            ( (Fragment_CreaOrdenMantenimiento) this.pantalla ).terminaProcesando();
            ( (Fragment_CreaOrdenMantenimiento) this.pantalla ).errorServicio(this.errorMensaje);
        }
    }
}

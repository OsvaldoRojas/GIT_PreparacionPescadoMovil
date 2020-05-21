package com.grupo.pinsa.sip.simulador.conexion;

import android.os.AsyncTask;

import com.grupo.pinsa.sip.simulador.utilerias.Catalogos;
import com.grupo.pinsa.sip.simulador.modelo.Especialidad;
import com.grupo.pinsa.sip.simulador.modelo.Etapa;
import com.grupo.pinsa.sip.simulador.modelo.GrupoEspecie;
import com.grupo.pinsa.sip.simulador.modelo.Refaccion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CargaCatalogos extends AsyncTask<Void,Integer,Boolean> {

    @Override
    protected Boolean doInBackground(Void... voids) {
        Call<List<Etapa>> catalogoEtapa = APIServicios.getConexion().getEtapas();
        catalogoEtapa.enqueue(new Callback<List<Etapa>>() {
            @Override
            public void onResponse(Call<List<Etapa>> call, Response<List<Etapa>> response) {
                if(response.code() == 200){
                    Catalogos.getInstancia().setCatalogoEtapa( response.body() );
                }
            }

            @Override
            public void onFailure(Call<List<Etapa>> call, Throwable t) {

            }
        });

        Call<List<Refaccion>> catalogoRefaccion = APIServicios.getConexion().getRefacciones();
        catalogoRefaccion.enqueue(new Callback<List<Refaccion>>() {
            @Override
            public void onResponse(Call<List<Refaccion>> call, Response<List<Refaccion>> response) {
                if(response.code() == 200){
                    Catalogos.getInstancia().setCatalogoRefaccion( response.body() );
                }
            }

            @Override
            public void onFailure(Call<List<Refaccion>> call, Throwable t) {

            }
        });

        /*Call<List<Talla>> catalogoTalla = APIServicios.getConexion().getTallas();
        catalogoTalla.enqueue(new Callback<List<Talla>>() {
            @Override
            public void onResponse(Call<List<Talla>> call, Response<List<Talla>> response) {
                if(response.code() == 200){
                    Catalogos.getInstancia().setCatalogoTalla( response.body() );
                }
            }

            @Override
            public void onFailure(Call<List<Talla>> call, Throwable t) {

            }
        });*/

        /*Call<List<Subtalla>> catalogoSubtalla = APIServicios.getConexion().getSubtallas();
        catalogoSubtalla.enqueue(new Callback<List<Subtalla>>() {
            @Override
            public void onResponse(Call<List<Subtalla>> call, Response<List<Subtalla>> response) {
                if(response.code() == 200){
                    Catalogos.getInstancia().setCatalogoSubtalla( response.body() );
                }
            }

            @Override
            public void onFailure(Call<List<Subtalla>> call, Throwable t) {

            }
        });*/

        Call<List<GrupoEspecie>> catalogoEspecie = APIServicios.getConexion().getEspecies();
        catalogoEspecie.enqueue(new Callback<List<GrupoEspecie>>() {
            @Override
            public void onResponse(Call<List<GrupoEspecie>> call, Response<List<GrupoEspecie>> response) {
                if(response.code() == 200){
                    Catalogos.getInstancia().setCatalogoGrupoEspecie( response.body() );
                }
            }

            @Override
            public void onFailure(Call<List<GrupoEspecie>> call, Throwable t) {

            }
        });

        Call<List<Especialidad>> catalogoEspecialidad = APIServicios.getConexion().getEspecialidades();
        catalogoEspecialidad.enqueue(new Callback<List<Especialidad>>() {
            @Override
            public void onResponse(Call<List<Especialidad>> call, Response<List<Especialidad>> response) {
                if(response.code() == 200){
                    Catalogos.getInstancia().setCatalogoEspecialidad( response.body() );
                }
            }

            @Override
            public void onFailure(Call<List<Especialidad>> call, Throwable t) {

            }
        });

        return true;
    }
}

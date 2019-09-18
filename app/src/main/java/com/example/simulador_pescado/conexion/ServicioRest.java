package com.example.simulador_pescado.conexion;

import com.example.simulador_pescado.vista.Gafete;
import com.example.simulador_pescado.vista.OperadorBascula;
import com.example.simulador_pescado.vista.OperadorMontacargas;
import com.example.simulador_pescado.vista.OrdenMantenimientoActualizar;
import com.example.simulador_pescado.vista.PosicionEstibaAtemperado;
import com.example.simulador_pescado.vista.RespuestaServicio;
import com.example.simulador_pescado.vista.Tina;
import com.example.simulador_pescado.vista.TinaEscaneo;
import com.example.simulador_pescado.vista.TinaServicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServicioRest {

    @GET("atemperado/posiciones/tinas")
    Call<List<PosicionEstibaAtemperado>> getPosicionesAtemperado();

    @GET("preseleccion/posiciones/tinas")
    Call<List<Tina>> getTinasAsignadas();

    @GET("preseleccion/estaciones")
    Call<List<OperadorBascula>> getOperadoresAsignados();

    @GET("preseleccion/montacargas")
    Call<List<OperadorMontacargas>> getMontacargasAsignados();

    @GET("reservamateriales_api/fortia/empleados/{codigo}")
    Call<Gafete> getGafeteUsuario(@Path("codigo") String codigo);

    @GET("catalogos/tinas/{codigo}")
    Call<TinaEscaneo> getTina(@Path("codigo") String codigo);

    @PUT("ordenes/mantenimientos")
    Call<RespuestaServicio> actualizaOrdenMantenimiento(@Body OrdenMantenimientoActualizar orden);

    @PUT("preseleccion/montacargas")
    Call<RespuestaServicio> guardaMontacargas(@Body OperadorMontacargas montacargas);

    @PUT("preseleccion/estaciones")
    Call<RespuestaServicio> guardaOperador(@Body OperadorBascula operador);

    @PUT("preseleccion/posiciones/tinas")
    Call<RespuestaServicio> asignaTina(@Body TinaServicio tina);

    @PUT("preseleccion/operadores/liberar-todos")
    Call<RespuestaServicio> liberaTurno(@Body String usuario);

    @POST("preseleccion/posiciones/tinas/liberar")
    Call<RespuestaServicio> liberaTina(@Body int idPreseleccionPosicionTina, @Body String usuario);
}

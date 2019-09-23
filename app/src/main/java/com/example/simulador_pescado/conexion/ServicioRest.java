package com.example.simulador_pescado.conexion;

import com.example.simulador_pescado.vista.Artefacto;
import com.example.simulador_pescado.vista.Especialidad;
import com.example.simulador_pescado.vista.Etapa;
import com.example.simulador_pescado.vista.GrupoEspecie;
import com.example.simulador_pescado.vista.Maquinaria;
import com.example.simulador_pescado.vista.OperadorBascula;
import com.example.simulador_pescado.vista.OperadorMontacargas;
import com.example.simulador_pescado.vista.OrdenMantenimiento;
import com.example.simulador_pescado.vista.PosicionEstibaAtemperado;
import com.example.simulador_pescado.vista.Refaccion;
import com.example.simulador_pescado.vista.Subtalla;
import com.example.simulador_pescado.vista.Talla;
import com.example.simulador_pescado.vista.Tina;
import com.example.simulador_pescado.vista.servicio.Gafete;
import com.example.simulador_pescado.vista.servicio.LiberarTodos;
import com.example.simulador_pescado.vista.servicio.ListaOrdenMantenimientoServicio;
import com.example.simulador_pescado.vista.servicio.OrdenMantenimientoActualizar;
import com.example.simulador_pescado.vista.servicio.OrdenMantenimientoCerrarTiempo;
import com.example.simulador_pescado.vista.servicio.OrdenMantenimientoGuardar;
import com.example.simulador_pescado.vista.servicio.RefaccionGuardar;
import com.example.simulador_pescado.vista.servicio.RespuestaServicio;
import com.example.simulador_pescado.vista.servicio.TinaEscaneo;
import com.example.simulador_pescado.vista.servicio.TinaServicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicioRest {

    @GET("catalogos/etapas")
    Call<List<Etapa>> getEtapas();

    @GET("catalogos/refacciones")
    Call<List<Refaccion>> getRefacciones();

    @GET("catalogos/maquinarias/{maquinaria}/artefactos")
    Call<List<Artefacto>> getArtefactos(@Path("maquinaria") int maquinaria);

    @GET("catalogos/tallas")
    Call<List<Talla>> getTallas();

    @GET("catalogos/subtallas")
    Call<List<Subtalla>> getSubtallas();

    @GET("catalogos/especies")
    Call<List<GrupoEspecie>> getEspecies();

    @GET("catalogos/especialidades")
    Call<List<Especialidad>> getEspecialidades();

    @GET("catalogos/maquinarias/{etapa}")
    Call<List<Maquinaria>> getMaquinarias(@Path("etapa") int etapa);

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

    @GET("ordenes/mantenimientos")
    Call<List<ListaOrdenMantenimientoServicio>> getOrdenesMantenimiento(@Query("idEtapa") int idEtapa,
                                                                        @Query("idEmpleado") int idEmpleado);

    @GET("ordenes/mantenimientos/{idOrden}")
    Call<OrdenMantenimiento> getDetalleOrden(@Path("idOrden") long idOrden);

    @GET("atemperado/posiciones/tinas")
    Call<List<PosicionEstibaAtemperado>> getPosicionesAtemperado();

    @PUT("preseleccion/montacargas")
    Call<RespuestaServicio> guardaMontacargas(@Body OperadorMontacargas montacargas);

    @PUT("preseleccion/estaciones")
    Call<RespuestaServicio> guardaOperador(@Body OperadorBascula operador);

    @PUT("preseleccion/operadores/liberar-todos")
    Call<RespuestaServicio> liberaTurno(@Body LiberarTodos liberarTodos);

    @PUT("preseleccion/posiciones/tinas")
    Call<RespuestaServicio> asignaTina(@Body TinaServicio tina);

    @POST("preseleccion/posiciones/tinas/liberar")
    Call<RespuestaServicio> liberaTina(@Body int idPreseleccionPosicionTina, @Body String usuario);

    @PUT("ordenes/mantenimientos")
    Call<RespuestaServicio> actualizaOrdenMantenimiento(@Body OrdenMantenimientoActualizar orden);

    @PUT("ordenes/mantenimientos/finalizar")
    Call<RespuestaServicio> cierraTiempoOrdenMantenimiento(@Body OrdenMantenimientoCerrarTiempo orden);

    @POST("ordenes/mantenimientos")
    Call<RespuestaServicio> guardaOrdenMantenimiento(@Body OrdenMantenimientoGuardar orden);

    @POST("ordenes/mantenimientos/refacciones")
    Call<RespuestaServicio> guardaRefaccion(@Body RefaccionGuardar refaccion);
}

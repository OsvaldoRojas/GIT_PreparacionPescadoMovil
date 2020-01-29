package com.pinsa.simulador.conexion;

import com.pinsa.simulador.vista.Artefacto;
import com.pinsa.simulador.vista.Cocida;
import com.pinsa.simulador.vista.Especialidad;
import com.pinsa.simulador.vista.Etapa;
import com.pinsa.simulador.vista.GrupoEspecie;
import com.pinsa.simulador.vista.Maquinaria;
import com.pinsa.simulador.vista.OperadorBascula;
import com.pinsa.simulador.vista.OperadorMontacargas;
import com.pinsa.simulador.vista.OrdenMantenimiento;
import com.pinsa.simulador.vista.PosicionEstibaAtemperado;
import com.pinsa.simulador.vista.PosicionEstibaDescongelado;
import com.pinsa.simulador.vista.Refaccion;
import com.pinsa.simulador.vista.Subtalla;
import com.pinsa.simulador.vista.Talla;
import com.pinsa.simulador.vista.Tina;
import com.pinsa.simulador.vista.TinaProceso;
import com.pinsa.simulador.vista.servicio.Gafete;
import com.pinsa.simulador.vista.servicio.LiberaPosicion;
import com.pinsa.simulador.vista.servicio.LiberaTinaPosicion;
import com.pinsa.simulador.vista.servicio.LiberaTinaServicio;
import com.pinsa.simulador.vista.servicio.LiberarTodos;
import com.pinsa.simulador.vista.servicio.ListaOrdenMantenimientoServicio;
import com.pinsa.simulador.vista.servicio.MezclaTinaServicio;
import com.pinsa.simulador.vista.servicio.OrdenMantenimientoActualizar;
import com.pinsa.simulador.vista.servicio.OrdenMantenimientoCerrarTiempo;
import com.pinsa.simulador.vista.servicio.OrdenMantenimientoGuardar;
import com.pinsa.simulador.vista.servicio.PosicionCompleta;
import com.pinsa.simulador.vista.servicio.RefaccionGuardar;
import com.pinsa.simulador.vista.servicio.RespuestaServicio;
import com.pinsa.simulador.vista.servicio.TinaEscaneo;
import com.pinsa.simulador.vista.servicio.TinaServicio;
import com.google.gson.JsonObject;

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
    Call<List<Etapa>> getEtapas();//FUNCIONA

    @GET("catalogos/refacciones")
    Call<List<Refaccion>> getRefacciones();//FUNCIONA

    @GET("catalogos/maquinarias/{maquinaria}/artefactos")
    Call<List<Artefacto>> getArtefactos(@Path("maquinaria") int maquinaria);//FUNCIONA

    @GET("catalogos/tallas")
    Call<List<Talla>> getTallas();//FUNCIONA

    @GET("catalogos/subtallas")
    Call<List<Subtalla>> getSubtallas();//FUNCIONA

    @GET("catalogos/especies")
    Call<List<GrupoEspecie>> getEspecies();//FUNCIONA

    @GET("catalogos/especialidades")
    Call<List<Especialidad>> getEspecialidades();//FUNCIONA

    @GET("catalogos/maquinarias/{etapa}")
    Call<List<Maquinaria>> getMaquinarias(@Path("etapa") int etapa);//FUNCIONA

    @GET("preseleccion/posiciones/tinas")
    Call<List<Tina>> getTinasAsignadas();//FUNCIONA

    @GET("preseleccion/estaciones")
    Call<List<OperadorBascula>> getOperadoresAsignados();//FUNCIONA

    @GET("preseleccion/montacargas")
    Call<List<OperadorMontacargas>> getMontacargasAsignados();//FUNCIONA

    @GET("reservamateriales_api/fortia/empleados/{codigo}")
    Call<Gafete> getGafeteUsuario(@Path("codigo") String codigo);//FUNCIONA

    @GET("catalogos/tinas/{codigo}")
    Call<TinaEscaneo> getTina(@Path("codigo") String codigo);//FUNCIONA

    @GET("ordenes/mantenimientos")
    Call<List<ListaOrdenMantenimientoServicio>> getOrdenesMantenimiento(@Query("idEtapa") int idEtapa,
                                                                        @Query("idEmpleado") int idEmpleado);//FUNCIONA

    @GET("ordenes/mantenimientos/{idOrden}")
    Call<OrdenMantenimiento> getDetalleOrden(@Path("idOrden") long idOrden);//FUNCIONA

    @GET("atemperado/posiciones/tinas")
    Call<List<PosicionEstibaAtemperado>> getPosicionesAtemperado();//FUNCIONA

    @GET("descongelado/posiciones/tinas")
    Call<List<PosicionEstibaDescongelado>> getPosicionesDescongelado();//FUNCIONA

    @GET("preseleccion/asignaciones/cocidas")
    Call<List<Cocida>> getAsignacionCocida();

    @GET("etapas/posiciones/tinas/{idTina}")
    Call<TinaProceso> getTinaProceso(@Path("idTina") long idTina);

    @PUT("preseleccion/montacargas")
    Call<RespuestaServicio> guardaMontacargas(@Body OperadorMontacargas montacargas);//FUNCIONA

    @PUT("preseleccion/estaciones")
    Call<RespuestaServicio> guardaOperador(@Body OperadorBascula operador);//FUNCIONA

    @PUT("preseleccion/operadores/liberar-todos")
    Call<RespuestaServicio> liberaTurno(@Body LiberarTodos liberarTodos);//FUNCIONA

    @PUT("preseleccion/operadores/liberar-todos")
    Call<RespuestaServicio> liberaTurnoPrueba(@Body JsonObject body);//FUNCIONA

    @PUT("preseleccion/posiciones/tinas")
    Call<RespuestaServicio> asignaTina(@Body TinaServicio tina);//FUNCIONA

    @POST("preseleccion/posiciones/tinas/liberar")
    Call<RespuestaServicio> liberaTina(@Body LiberaTinaServicio tinaLiberada);//FUNCIONA

    @PUT("ordenes/mantenimientos")
    Call<RespuestaServicio> actualizaOrdenMantenimiento(@Body OrdenMantenimientoActualizar orden);//FUNCIONA

    @PUT("ordenes/mantenimientos/finalizar")
    Call<RespuestaServicio> cierraTiempoOrdenMantenimiento(@Body OrdenMantenimientoCerrarTiempo orden);//FUNCIONA

    @POST("preseleccion/posiciones/tinas/mezclar")
    Call<RespuestaServicio> mezclaTinas(@Body MezclaTinaServicio tinasMezcladas);//FUNCIONA

    @POST("ordenes/mantenimientos")
    Call<RespuestaServicio> guardaOrdenMantenimiento(@Body OrdenMantenimientoGuardar orden);//FUNCIONA

    @POST("ordenes/mantenimientos/refacciones")
    Call<RespuestaServicio> guardaRefaccion(@Body RefaccionGuardar refaccion);//FUNCIONA

    @POST("descongelado/posiciones/tinas/liberar")
    Call<RespuestaServicio> liberaTinaPosicion(@Body LiberaTinaPosicion tinaLiberada);//FUNCIONA

    @POST("descongelado/posiciones/completa")
    Call<RespuestaServicio> completaPosicion(@Body PosicionCompleta posicionCompleta);//FUNCIONA

    @POST("descongelado/posiciones/liberar")
    Call<RespuestaServicio> liberaPosicion(@Body LiberaPosicion liberaPosicion);//FUNCIONA

    @POST("etapas/posiciones/tinas")
    Call<RespuestaServicio> guardaPeso(@Body JsonObject body);
}

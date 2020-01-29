package com.example.simulador.conexion;

import com.example.simulador.vista.Artefacto;
import com.example.simulador.vista.Cocida;
import com.example.simulador.vista.Especialidad;
import com.example.simulador.vista.Etapa;
import com.example.simulador.vista.GrupoEspecie;
import com.example.simulador.vista.Maquinaria;
import com.example.simulador.vista.OperadorBascula;
import com.example.simulador.vista.OperadorMontacargas;
import com.example.simulador.vista.OrdenMantenimiento;
import com.example.simulador.vista.PosicionEstibaAtemperado;
import com.example.simulador.vista.PosicionEstibaDescongelado;
import com.example.simulador.vista.Refaccion;
import com.example.simulador.vista.Subtalla;
import com.example.simulador.vista.Talla;
import com.example.simulador.vista.Tina;
import com.example.simulador.vista.TinaProceso;
import com.example.simulador.vista.servicio.Gafete;
import com.example.simulador.vista.servicio.LiberaPosicion;
import com.example.simulador.vista.servicio.LiberaTinaPosicion;
import com.example.simulador.vista.servicio.LiberaTinaServicio;
import com.example.simulador.vista.servicio.LiberarTodos;
import com.example.simulador.vista.servicio.ListaOrdenMantenimientoServicio;
import com.example.simulador.vista.servicio.MezclaTinaServicio;
import com.example.simulador.vista.servicio.OrdenMantenimientoActualizar;
import com.example.simulador.vista.servicio.OrdenMantenimientoCerrarTiempo;
import com.example.simulador.vista.servicio.OrdenMantenimientoGuardar;
import com.example.simulador.vista.servicio.PosicionCompleta;
import com.example.simulador.vista.servicio.RefaccionGuardar;
import com.example.simulador.vista.servicio.RespuestaServicio;
import com.example.simulador.vista.servicio.TinaEscaneo;
import com.example.simulador.vista.servicio.TinaServicio;
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
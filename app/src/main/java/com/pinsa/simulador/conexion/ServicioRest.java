package com.pinsa.simulador.conexion;

import com.google.gson.JsonObject;
import com.pinsa.simulador.vista.Artefacto;
import com.pinsa.simulador.vista.Cocida;
import com.pinsa.simulador.vista.Especialidad;
import com.pinsa.simulador.vista.Etapa;
import com.pinsa.simulador.vista.GrupoEspecie;
import com.pinsa.simulador.vista.Maquinaria;
import com.pinsa.simulador.vista.Operador;
import com.pinsa.simulador.vista.OperadorBascula;
import com.pinsa.simulador.vista.OperadorMontacargas;
import com.pinsa.simulador.vista.OrdenMantenimiento;
import com.pinsa.simulador.vista.PosicionEstibaAtemperado;
import com.pinsa.simulador.vista.PosicionEstibaDescongelado;
import com.pinsa.simulador.vista.Refaccion;
import com.pinsa.simulador.vista.SalidaTina;
import com.pinsa.simulador.vista.Subtalla;
import com.pinsa.simulador.vista.Talla;
import com.pinsa.simulador.vista.Tina;
import com.pinsa.simulador.vista.TinaProceso;
import com.pinsa.simulador.vista.servicio.Gafete;
import com.pinsa.simulador.vista.servicio.ListaOrdenMantenimientoServicio;
import com.pinsa.simulador.vista.servicio.MezclaTinaServicio;
import com.pinsa.simulador.vista.servicio.OrdenMantenimientoGuardar;
import com.pinsa.simulador.vista.servicio.RespuestaServicio;
import com.pinsa.simulador.vista.servicio.TinaEscaneo;

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

    @GET("emparrillado/estaciones")
    Call<List<Operador>> getOperadoresEmparrillado();

    @GET("eviscerado/estaciones")
    Call<List<Operador>> getOperadoresEviscerado();

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

    @GET("descongelado/posiciones/tinas")
    Call<List<PosicionEstibaDescongelado>> getPosicionesDescongelado();

    @GET("preseleccion/asignaciones/cocidas")
    Call<List<Cocida>> getAsignacionCocida();

    @GET("etapas/posiciones/tinas/{idTina}")
    Call<TinaProceso> getTinaProceso(@Path("idTina") long idTina);

    @GET("descongelado/salida")
    Call<List<SalidaTina>> getSalidaTinas();

    @PUT("preseleccion/montacargas")
    Call<RespuestaServicio> guardaMontacargas(@Body JsonObject body);

    @PUT("preseleccion/estaciones")
    Call<RespuestaServicio> guardaOperador(@Body JsonObject body);

    @PUT("emparrillado/estaciones")
    Call<RespuestaServicio> guardaOperadorEmparrillado(@Body JsonObject body);

    @PUT("eviscerado/estaciones")
    Call<RespuestaServicio> guardaOperadorEviscerado(@Body JsonObject body);

    @PUT("preseleccion/operadores/liberar-todos")
    Call<RespuestaServicio> liberaTurno(@Body JsonObject body);

    @PUT("emparrillado/estaciones/liberar-todos")
    Call<RespuestaServicio> liberarTurnoEmparrillado(@Body JsonObject body);

    @PUT("eviscerado/estaciones/liberar-todos")
    Call<RespuestaServicio> liberarTurnoEviscerado(@Body JsonObject body);

    @PUT("preseleccion/posiciones/tinas")
    Call<RespuestaServicio> asignaTina(@Body JsonObject body);

    @POST("preseleccion/posiciones/tinas/liberar")
    Call<RespuestaServicio> liberaTina(@Body JsonObject body);

    @PUT("ordenes/mantenimientos")
    Call<RespuestaServicio> actualizaOrdenMantenimiento(@Body JsonObject body);

    @PUT("ordenes/mantenimientos/finalizar")
    Call<RespuestaServicio> cierraTiempoOrdenMantenimiento(@Body JsonObject body);

    @PUT("descongelado/salida/marcadoVisible")
    Call<RespuestaServicio> guardaSalidaTina(@Body JsonObject body);

    @POST("preseleccion/posiciones/tinas/mezclar")
    Call<RespuestaServicio> mezclaTinas(@Body MezclaTinaServicio tinasMezcladas);

    @POST("ordenes/mantenimientos")
    Call<RespuestaServicio> guardaOrdenMantenimiento(@Body OrdenMantenimientoGuardar orden);

    @POST("ordenes/mantenimientos/refacciones")
    Call<RespuestaServicio> guardaRefaccion(@Body JsonObject body);

    @POST("descongelado/posiciones/tinas/liberar")
    Call<RespuestaServicio> liberaTinaPosicion(@Body JsonObject body);

    @POST("descongelado/posiciones/completa")
    Call<RespuestaServicio> completaPosicion(@Body JsonObject body);

    @POST("descongelado/posiciones/liberar")
    Call<RespuestaServicio> liberaPosicion(@Body JsonObject body);

    @POST("etapas/posiciones/tinas")
    Call<RespuestaServicio> guardaPeso(@Body JsonObject body);

    @POST("empleado/puesto")
    Call<RespuestaServicio> guardaPuesto(@Body JsonObject body);
}

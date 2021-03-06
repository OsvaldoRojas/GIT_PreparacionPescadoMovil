package com.grupo.pinsa.sip.views.simulador.conexion;

import com.google.gson.JsonObject;
import com.grupo.pinsa.sip.views.simulador.modelo.Artefacto;
import com.grupo.pinsa.sip.views.simulador.modelo.Bascula;
import com.grupo.pinsa.sip.views.simulador.modelo.Carrito;
import com.grupo.pinsa.sip.views.simulador.modelo.Cocedor;
import com.grupo.pinsa.sip.views.simulador.modelo.CocedorActualSiguiente;
import com.grupo.pinsa.sip.views.simulador.modelo.Cocida;
import com.grupo.pinsa.sip.views.simulador.modelo.ControlDescongelado;
import com.grupo.pinsa.sip.views.simulador.modelo.ControlDescongeladoDetalle;
import com.grupo.pinsa.sip.views.simulador.modelo.EmpleadoEstacion;
import com.grupo.pinsa.sip.views.simulador.modelo.Especialidad;
import com.grupo.pinsa.sip.views.simulador.modelo.Etapa;
import com.grupo.pinsa.sip.views.simulador.modelo.GrupoEspecie;
import com.grupo.pinsa.sip.views.simulador.modelo.Maquinaria;
import com.grupo.pinsa.sip.views.simulador.modelo.Modulo;
import com.grupo.pinsa.sip.views.simulador.modelo.Operador;
import com.grupo.pinsa.sip.views.simulador.modelo.OperadorBascula;
import com.grupo.pinsa.sip.views.simulador.modelo.OperadorMontacargas;
import com.grupo.pinsa.sip.views.simulador.modelo.OrdenMantenimiento;
import com.grupo.pinsa.sip.views.simulador.modelo.PosicionEstibaAtemperado;
import com.grupo.pinsa.sip.views.simulador.modelo.PosicionEstibaDescongelado;
import com.grupo.pinsa.sip.views.simulador.modelo.Refaccion;
import com.grupo.pinsa.sip.views.simulador.modelo.SalidaTina;
import com.grupo.pinsa.sip.views.simulador.modelo.Subtalla;
import com.grupo.pinsa.sip.views.simulador.modelo.Talla;
import com.grupo.pinsa.sip.views.simulador.modelo.TemperaturaCarrito;
import com.grupo.pinsa.sip.views.simulador.modelo.TemperaturaTina;
import com.grupo.pinsa.sip.views.simulador.modelo.Tina;
import com.grupo.pinsa.sip.views.simulador.modelo.TinaProceso;
import com.grupo.pinsa.sip.views.simulador.modelo.Zona;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.CocedorCarritosAsignados;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.Gafete;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.ListaOrdenMantenimientoServicio;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.MezclaTinaServicio;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.ModuloCarritosAsignados;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.ModuloSalidaCarritos;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.OrdenMantenimientoGuardar;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.RespuestaServicio;
import com.grupo.pinsa.sip.views.simulador.modelo.servicio.TinaEscaneo;

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

    @GET("catalogos/tallas/filtrado")
    Call<List<Talla>> getTallasFiltrado(@Query("tipoAgrupacion") String tipoAgrupacion,
                                        @Query("activo") boolean activo);

    @GET("catalogos/subtallas/filtrado")
    Call<List<Subtalla>> getSubtallasFiltrado(@Query("idTalla") int idTalla,
                                              @Query("activo") boolean activo);

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
    Call<TinaProceso> getTinaProceso(@Path("idTina") String idTina);

    @GET("descongelado/salida")
    Call<List<SalidaTina>> getSalidaTinas();

    @GET("empleado/estacion")
    Call<EmpleadoEstacion> getEmpleadoEstacion(@Query("idEmpleado") String idEmpleado);

    @GET("cocimiento/operadores")
    Call<List<Operador>> getOperadoresCocimiento();

    @GET("lavado/operadores")
    Call<List<Operador>> getOperadoresLavado();

    @GET("cocimiento/cocida/actualSiguiente")
    Call<CocedorActualSiguiente> getCocedorActualSiguiente();

    @GET("cocimiento/carritos/sinAsignar")
    Call<List<Carrito>> getCarritosSinAsignar();

    @GET("cocimiento/catalogos/cocedores")
    Call<List<Cocedor>> getCocedoresCocimiento();

    @GET("cocimiento/cocedores/cocida/{idCocida}")
    Call<List<Cocedor>> getDetalleCocidasCocedor(@Path("idCocida") long idCocida);

    @GET("temperaturas/tinas")
    Call<List<TemperaturaTina>> getTinasTemperatura(@Query("etapa") int idEtapa);

    @GET("modulos/catalogos/modulos")
    Call<List<Modulo>> getModulos();

    @GET("modulos/{idModulo}/carritos")
    Call<List<Carrito>> getCarritosModulo(@Path("idModulo") long idModulo);

    @GET("modulos/operador")
    Call<List<Operador>> getOperadoresModulo();

    @GET("catalogos/zonas")
    Call<List<Zona>> getZonas();

    @GET("catalogos/basculas")
    Call<List<Bascula>> getBasculas(@Query("clave") String clave);

    @GET("modulos/carritos/{idCocedor}/{idBascula}/pesados")
    Call<List<Carrito>> getCarritosSinAsignarModulo(@Path("idCocedor") long idCocedor,
                                                    @Path("idBascula") int idBascula);

    @GET("modulos/{idModulo}/{idBascula}/carritos")
    Call<List<Carrito>> getCarritosSalida(@Path("idModulo") long idModulo,
                                          @Path("idBascula") int idBascula);

    @GET("modulos/vistaPanoramica")
    Call<List<Modulo>> getModulosVistaPanoramica();

    @GET("procesos/descongelados")
    Call<List<ControlDescongelado>> getControlDescongelado(@Query("fecha") String fecha);

    @GET("procesos/descongelados/consumo")
    Call<ControlDescongeladoDetalle> getControlDetalle(@Query("fecha") String fecha);

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

    @PUT("emparrillado/estaciones/liberarTodos")
    Call<RespuestaServicio> liberaTurnoEmparrillado(@Body JsonObject body);

    @PUT("eviscerado/estaciones/liberarTodos")
    Call<RespuestaServicio> liberaTurnoEviscerado(@Body JsonObject body);

    @PUT("preseleccion/posiciones/tinas")
    Call<RespuestaServicio> asignaTina(@Body JsonObject body);

    @PUT("ordenes/mantenimientos")
    Call<RespuestaServicio> actualizaOrdenMantenimiento(@Body JsonObject body);

    @PUT("ordenes/mantenimientos/finalizar")
    Call<RespuestaServicio> cierraTiempoOrdenMantenimiento(@Body JsonObject body);

    @PUT("descongelado/salida/marcadoVisible")
    Call<RespuestaServicio> guardaSalidaTina(@Body JsonObject body);

    @PUT("cocimiento/operadores/libera-todos")
    Call<RespuestaServicio> liberaTurnoCocimiento(@Body JsonObject body);

    @PUT("cocimiento/operadores")
    Call<RespuestaServicio> guardaOperadorCocimiento(@Body JsonObject body);

    @PUT("lavado/operadores")
    Call<RespuestaServicio> guardaOperadorLavado(@Body JsonObject body);

    @PUT("lavado/operadores/libera-todos")
    Call<RespuestaServicio> liberaTurnoLavado(@Body JsonObject body);

    @PUT("temperaturas/tinas")
    Call<List<RespuestaServicio>> guardaTemperaturaTina(@Body List<TemperaturaTina> tinas);

    @PUT("temperaturas/carritos")
    Call<List<RespuestaServicio>> guardaTemperaturaCarrito(@Body List<TemperaturaCarrito> carritos);

    @PUT("modulos/operador")
    Call<RespuestaServicio> guardaOperadorModulo(@Body JsonObject body);

    @PUT("modulos/operadores/libera-todos")
    Call<RespuestaServicio> liberaTurnoModulo(@Body JsonObject body);

    @PUT("procesos/descongelados")
    Call<RespuestaServicio> guardaControlDescongelado(@Body ControlDescongelado controlDescongelado);

    @POST("preseleccion/posiciones/tinas/liberar")
    Call<RespuestaServicio> liberaTina(@Body JsonObject body);

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

    @POST("cocimiento/cocedor/carritos")
    Call<RespuestaServicio> asignaCarritosCocedor(@Body CocedorCarritosAsignados carritosAsignados);

    @POST("cocimiento/cocedor/asignarStatus")
    Call<RespuestaServicio> cambiaEstatusCocedor(@Body JsonObject body);

    @POST("cocimiento/proceso/cocida")
    Call<RespuestaServicio> iniciaCocida(@Body JsonObject body);

    @POST("cocimiento/proceso/compensacion")
    Call<RespuestaServicio> generaCompensacion(@Body JsonObject body);

    @POST("modulos/carritos")
    Call<List<RespuestaServicio>> asignaCarritosModulo(@Body ModuloCarritosAsignados carritosAsignados);

    @POST("modulos/carritos/salida")
    Call<List<RespuestaServicio>> guardaSalidaCarritos(@Body ModuloSalidaCarritos carritosSalida);

    @POST("empleado/puesto")
    Call<RespuestaServicio> guardaPuesto(@Body JsonObject body);
}

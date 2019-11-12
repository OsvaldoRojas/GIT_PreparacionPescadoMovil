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
import com.example.simulador_pescado.vista.PosicionEstibaDescongelado;
import com.example.simulador_pescado.vista.Refaccion;
import com.example.simulador_pescado.vista.Subtalla;
import com.example.simulador_pescado.vista.Talla;
import com.example.simulador_pescado.vista.Tina;
import com.example.simulador_pescado.vista.servicio.Gafete;
import com.example.simulador_pescado.vista.servicio.LiberaPosicion;
import com.example.simulador_pescado.vista.servicio.LiberaTinaPosicion;
import com.example.simulador_pescado.vista.servicio.LiberaTinaServicio;
import com.example.simulador_pescado.vista.servicio.LiberarTodos;
import com.example.simulador_pescado.vista.servicio.ListaOrdenMantenimientoServicio;
import com.example.simulador_pescado.vista.servicio.MezclaTinaServicio;
import com.example.simulador_pescado.vista.servicio.OrdenMantenimientoActualizar;
import com.example.simulador_pescado.vista.servicio.OrdenMantenimientoCerrarTiempo;
import com.example.simulador_pescado.vista.servicio.OrdenMantenimientoGuardar;
import com.example.simulador_pescado.vista.servicio.PosicionCompleta;
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

    @PUT("preseleccion/montacargas")
    Call<RespuestaServicio> guardaMontacargas(@Body OperadorMontacargas montacargas);//FUNCIONA

    @PUT("preseleccion/estaciones")
    Call<RespuestaServicio> guardaOperador(@Body OperadorBascula operador);//FUNCIONA

    @PUT("preseleccion/operadores/liberar-todos")
    Call<RespuestaServicio> liberaTurno(@Body LiberarTodos liberarTodos);//FUNCIONA

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
}

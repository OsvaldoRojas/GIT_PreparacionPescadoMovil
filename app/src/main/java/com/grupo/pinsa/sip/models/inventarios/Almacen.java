package com.grupo.pinsa.sip.models.inventarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

@SuppressWarnings("ALL")
public class Almacen {
    @SerializedName("id_almacen")
    @Expose
    private Integer idAlmacen;
    @SerializedName("id_almacen_oracle")
    @Expose
    private Integer idAlmacenOracle;
    @SerializedName("id_empresa")
    @Expose
    private Integer idEmpresa;
    @SerializedName("clave_almacen")
    @Expose
    private String claveAlmacen;
    @SerializedName("almacen")
    @Expose
    private String almacen;
    @SerializedName("nombre_almacen_oracle")
    @Expose
    private String nombreAlmacenOracle;
    @SerializedName("id_control_lote")
    @Expose
    private Integer idControlLote;
    @SerializedName("id_control_serie")
    @Expose
    private Integer idControlSerie;
    @SerializedName("control_caducidad")
    @Expose
    private Boolean controlCaducidad;
    @SerializedName("consignacion")
    @Expose
    private Boolean consignacion;
    @SerializedName("id_almacen_consignado")
    @Expose
    private Integer idAlmacenConsignado;
    @SerializedName("id_combinacion_contable")
    @Expose
    private Integer idCombinacionContable;
    @SerializedName("cuenta_contable")
    @Expose
    private String cuentaContable;
    @SerializedName("cuenta_contable_descripcion")
    @Expose
    private String cuentaContableDescripcion;
    @SerializedName("activo")
    @Expose
    private Boolean activo;
    @SerializedName("id_almacen_acceso")
    @Expose
    private Integer idAlmacenAcceso;
    @SerializedName("id_subalmacen")
    @Expose
    private Integer idSubalmacen;
    @SerializedName("id_area")
    @Expose
    private Integer idArea;
    @SerializedName("activo_acceso")
    @Expose
    private Boolean activoAcceso;

    /** Oracle Cloud **/
    @SerializedName("id_almacen_integracion")
    @Expose
    private Integer idAlmacenIntegracion;
    @SerializedName("id_almacen_cloud")
    @Expose
    private String idAlmacenCloud;
    @SerializedName("id_almacen_ebs")
    @Expose
    private Integer idAlmacenEbs;
    @SerializedName("clave_cloud")
    @Expose
    private String claveCloud;
    @SerializedName("clave_ebs")
    @Expose
    private String claveEbs;
    @SerializedName("nombre_almacen_cloud")
    @Expose
    private String nombreAlmacenCloud;
    @SerializedName("nombre_almacen_ebs")
    @Expose
    private String nombreAlmacenEbs;
    /** **/

    public Almacen() {
    }

    /**
     * Getter and Setter
     */
    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Integer getIdAlmacenOracle() {
        return idAlmacenOracle;
    }

    public void setIdAlmacenOracle(Integer idAlmacenOracle) {
        this.idAlmacenOracle = idAlmacenOracle;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getClaveAlmacen() {
        return claveAlmacen;
    }

    public void setClaveAlmacen(String claveAlmacen) {
        this.claveAlmacen = claveAlmacen;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getNombreAlmacenOracle() {
        return nombreAlmacenOracle;
    }

    public void setNombreAlmacenOracle(String nombreAlmacenOracle) {
        this.nombreAlmacenOracle = nombreAlmacenOracle;
    }

    public Integer getIdControlLote() {
        return idControlLote;
    }

    public void setIdControlLote(Integer idControlLote) {
        this.idControlLote = idControlLote;
    }

    public Integer getIdControlSerie() {
        return idControlSerie;
    }

    public void setIdControlSerie(Integer idControlSerie) {
        this.idControlSerie = idControlSerie;
    }

    public Boolean getControlCaducidad() {
        return controlCaducidad;
    }

    public void setControlCaducidad(Boolean controlCaducidad) {
        this.controlCaducidad = controlCaducidad;
    }

    public Boolean getConsignacion() {
        return consignacion;
    }

    public void setConsignacion(Boolean consignacion) {
        this.consignacion = consignacion;
    }

    public Integer getIdAlmacenConsignado() {
        return idAlmacenConsignado;
    }

    public void setIdAlmacenConsignado(Integer idAlmacenConsignado) {
        this.idAlmacenConsignado = idAlmacenConsignado;
    }

    public Integer getIdCombinacionContable() {
        return idCombinacionContable;
    }

    public void setIdCombinacionContable(Integer idCombinacionContable) {
        this.idCombinacionContable = idCombinacionContable;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public String getCuentaContableDescripcion() {
        return cuentaContableDescripcion;
    }

    public void setCuentaContableDescripcion(String cuentaContableDescripcion) {
        this.cuentaContableDescripcion = cuentaContableDescripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getIdAlmacenAcceso() {
        return idAlmacenAcceso;
    }

    public void setIdAlmacenAcceso(Integer idAlmacenAcceso) {
        this.idAlmacenAcceso = idAlmacenAcceso;
    }

    public Integer getIdSubalmacen() {
        return idSubalmacen;
    }

    public void setIdSubalmacen(Integer idSubalmacen) {
        this.idSubalmacen = idSubalmacen;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public Boolean getActivoAcceso() {
        return activoAcceso;
    }

    public void setActivoAcceso(Boolean activoAcceso) {
        this.activoAcceso = activoAcceso;
    }

    /** Oracle Cloud **/
    public Integer getIdAlmacenIntegracion() {
        return idAlmacenIntegracion;
    }

    public void setIdAlmacenIntegracion(Integer idAlmacenIntegracion) {
        this.idAlmacenIntegracion = idAlmacenIntegracion;
    }

    public String getIdAlmacenCloud() {
        return idAlmacenCloud;
    }

    public void setIdAlmacenCloud(String idAlmacenCloud) {
        this.idAlmacenCloud = idAlmacenCloud;
    }

    public Integer getIdAlmacenEbs() {
        return idAlmacenEbs;
    }

    public void setIdAlmacenEbs(Integer idAlmacenEbs) {
        this.idAlmacenEbs = idAlmacenEbs;
    }

    public String getClaveCloud() {
        return claveCloud;
    }

    public void setClaveCloud(String claveCloud) {
        this.claveCloud = claveCloud;
    }

    public String getClaveEbs() {
        return claveEbs;
    }

    public void setClaveEbs(String claveEbs) {
        this.claveEbs = claveEbs;
    }

    public String getNombreAlmacenCloud() {
        return nombreAlmacenCloud;
    }

    public void setNombreAlmacenCloud(String nombreAlmacenCloud) {
        this.nombreAlmacenCloud = nombreAlmacenCloud;
    }

    public String getNombreAlmacenEbs() {
        return nombreAlmacenEbs;
    }

    public void setNombreAlmacenEbs(String nombreAlmacenEbs) {
        this.nombreAlmacenEbs = nombreAlmacenEbs;
    }
    /** **/
}
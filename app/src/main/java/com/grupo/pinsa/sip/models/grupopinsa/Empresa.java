package com.grupo.pinsa.sip.models.grupopinsa;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.grupo.pinsa.sip.models.inventarios.Almacen;

import java.util.ArrayList;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

@SuppressWarnings("ALL")
public class Empresa {
    @SerializedName("id_usuario_empresa")
    @Expose
    private Integer idUsuarioEmpresa;
    @SerializedName("id_usuario")
    @Expose
    private Integer idUsuario;
    @SerializedName("id_empresa")
    @Expose
    private Integer idEmpresa;
    @SerializedName("id_empresa_oracle")
    @Expose
    private Integer idEmpresaOracle;
    @SerializedName("clave_empresa")
    @Expose
    private String claveEmpresa;
    @SerializedName("segmento_cuenta")
    @Expose
    private String segmentoCuenta;
    @SerializedName("libro_cuenta")
    @Expose
    private String libroCuenta;
    @SerializedName("razon_social")
    @Expose
    private String razonSocial;
    @SerializedName("maestro_inv")
    @Expose
    private Integer maestroInv;
    @SerializedName("nombre_comercial")
    @Expose
    private String nombreComercial;
    @SerializedName("rfc")
    @Expose
    private String rfc;
    @SerializedName("id_libro_cuenta_oracle")
    @Expose
    private Integer idLibroCuentaOracle;
    @SerializedName("id_catalogo_cuentas")
    @Expose
    private Integer idCatalogoCuentas;
    @SerializedName("calle_no")
    @Expose
    private String calleNo;
    @SerializedName("colonia")
    @Expose
    private String colonia;
    @SerializedName("codigo_postal")
    @Expose
    private String codigoPostal;
    @SerializedName("localidad")
    @Expose
    private String localidad;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("activo")
    @Expose
    private Boolean activo;
    @SerializedName("activo_empresa")
    @Expose
    private Boolean activoEmpresa;
    @SerializedName("almacenes")
    @Expose
    private ArrayList<Almacen> almacenes;

    /** Oracle Cloud **/
    @SerializedName("id_empresa_cloud")
    @Expose
    private String idEmpresaCloud;
    @SerializedName("id_empresa_ebs")
    @Expose
    private Integer idEmpresaEbs;
    @SerializedName("clave_cloud")
    @Expose
    private String claveCloud;
    @SerializedName("clave_ebs")
    @Expose
    private String claveEbs;
    @SerializedName("nombre_empresa_cloud")
    @Expose
    private String nombreEmpresaCloud;
    @SerializedName("nombre_empresa_ebs")
    @Expose
    private String nombreEmpresaEbs;
    @SerializedName("id_libro_cuenta_cloud")
    @Expose
    private String idLibroCuentaCloud;
    @SerializedName("nombre_libro_cuenta_cloud")
    @Expose
    private String nombreLibroCuentaCloud;
    @SerializedName("id_juego_acceso_datos_cloud")
    @Expose
    private String idJuegoAccesoDatosCloud;
    /** **/

    public Empresa() {
        idUsuarioEmpresa = 0;
        idUsuario = 0;
        idEmpresa = 0;
        idEmpresaOracle = 0;
        claveEmpresa = "";
        segmentoCuenta = "";
        libroCuenta = "";
        razonSocial  ="";
        maestroInv = 0;
        nombreComercial = "";
        rfc = "";
        idLibroCuentaOracle = 0;
        idCatalogoCuentas = 0;
        calleNo = "";
        colonia = "";
        codigoPostal = "";
        localidad = "";
        telefono = "";
        activo = false;
        activoEmpresa = false;
        almacenes = new ArrayList<>();

        idEmpresaCloud = "";
        idEmpresaEbs = 0;
        claveCloud = "";
        claveEbs = "";
        nombreEmpresaCloud = "";
        nombreEmpresaEbs = "";
        idLibroCuentaCloud = "";
        nombreLibroCuentaCloud = "";
        idJuegoAccesoDatosCloud = "";
    }

    /**
     * Getter & Setter
     */
    public Integer getIdUsuarioEmpresa() {
        return idUsuarioEmpresa;
    }

    public void setIdUsuarioEmpresa(Integer idUsuarioEmpresa) {
        this.idUsuarioEmpresa = idUsuarioEmpresa;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdEmpresaOracle() {
        return idEmpresaOracle;
    }

    public void setIdEmpresaOracle(Integer idEmpresaOracle) {
        this.idEmpresaOracle = idEmpresaOracle;
    }

    public String getClaveEmpresa() {
        return claveEmpresa;
    }

    public void setClaveEmpresa(String claveEmpresa) {
        this.claveEmpresa = claveEmpresa;
    }

    public String getSegmentoCuenta() {
        return segmentoCuenta;
    }

    public void setSegmentoCuenta(String segmentoCuenta) {
        this.segmentoCuenta = segmentoCuenta;
    }

    public String getLibroCuenta() {
        return libroCuenta;
    }

    public void setLibroCuenta(String libroCuenta) {
        this.libroCuenta = libroCuenta;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Integer getMaestroInv() {
        return maestroInv;
    }

    public void setMaestroInv(Integer maestroInv) {
        this.maestroInv = maestroInv;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Integer getIdLibroCuentaOracle() {
        return idLibroCuentaOracle;
    }

    public void setIdLibroCuentaOracle(Integer idLibroCuentaOracle) {
        this.idLibroCuentaOracle = idLibroCuentaOracle;
    }

    public Integer getIdCatalogoCuentas() {
        return idCatalogoCuentas;
    }

    public void setIdCatalogoCuentas(Integer idCatalogoCuentas) {
        this.idCatalogoCuentas = idCatalogoCuentas;
    }

    public String getCalleNo() {
        return calleNo;
    }

    public void setCalleNo(String calleNo) {
        this.calleNo = calleNo;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getActivoEmpresa() {
        return activoEmpresa;
    }

    public void setActivoEmpresa(Boolean activoEmpresa) {
        this.activoEmpresa = activoEmpresa;
    }

    public ArrayList<Almacen> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(ArrayList<Almacen> almacenes) {
        this.almacenes = almacenes;
    }

    /** Oracle Cloud **/
    public String getIdEmpresaCloud() {
        return idEmpresaCloud;
    }

    public void setIdEmpresaCloud(String idEmpresaCloud) {
        this.idEmpresaCloud = idEmpresaCloud;
    }

    public Integer getIdEmpresaEbs() {
        return idEmpresaEbs;
    }

    public void setIdEmpresaEbs(Integer idEmpresaEbs) {
        this.idEmpresaEbs = idEmpresaEbs;
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

    public String getNombreEmpresaCloud() {
        return nombreEmpresaCloud;
    }

    public void setNombreEmpresaCloud(String nombreEmpresaCloud) {
        this.nombreEmpresaCloud = nombreEmpresaCloud;
    }

    public String getNombreEmpresaEbs() {
        return nombreEmpresaEbs;
    }

    public void setNombreEmpresaEbs(String nombreEmpresaEbs) {
        this.nombreEmpresaEbs = nombreEmpresaEbs;
    }

    public String getIdLibroCuentaCloud() {
        return idLibroCuentaCloud;
    }

    public void setIdLibroCuentaCloud(String idLibroCuentaCloud) {
        this.idLibroCuentaCloud = idLibroCuentaCloud;
    }

    public String getNombreLibroCuentaCloud() {
        return nombreLibroCuentaCloud;
    }

    public void setNombreLibroCuentaCloud(String nombreLibroCuentaCloud) {
        this.nombreLibroCuentaCloud = nombreLibroCuentaCloud;
    }

    public String getIdJuegoAccesoDatosCloud() {
        return idJuegoAccesoDatosCloud;
    }

    public void setIdJuegoAccesoDatosCloud(String idJuegoAccesoDatosCloud) {
        this.idJuegoAccesoDatosCloud = idJuegoAccesoDatosCloud;
    }

    /** **/
}

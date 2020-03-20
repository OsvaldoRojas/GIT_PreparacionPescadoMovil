package com.grupo.pinsa.sip.models.grupopinsa;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 25/03/2019
 */

@SuppressWarnings("ALL")
public class Impresora {
    @SerializedName("id_impresora_empresa")
    @Expose
    private Integer idImpresoraEmpresa;
    @SerializedName("id_impresora")
    @Expose
    private Integer idImpresora;
    @SerializedName("impresora")
    @Expose
    private String impresora;
    @SerializedName("modelo")
    @Expose
    private String modelo;
    @SerializedName("clave_impresora")
    @Expose
    private String claveImpresora;
    @SerializedName("id_empresa")
    @Expose
    private Integer idEmpresa;
    @SerializedName("clave_empresa")
    @Expose
    private String claveEmpresa;
    @SerializedName("nombre_comercial")
    @Expose
    private String nombreComercial;
    @SerializedName("id_funcionalidad")
    @Expose
    private Integer idFuncionalidad;
    @SerializedName("nombre_archivo")
    @Expose
    private String nombreArchivo;
    @SerializedName("descripcion_etiqueta")
    @Expose
    private String descripcionEtiqueta;

    public Impresora() {
        idImpresoraEmpresa = 0;
        idImpresora = 0;
        impresora = "";
        modelo = "";
        claveImpresora = "";
        idEmpresa = 0;
        claveEmpresa = "";
        nombreComercial = "";
        //idFuncionalidad = 0;
        nombreArchivo = "";
        descripcionEtiqueta = "";
    }

    /**
     * Getter and Setter
     */
    public Integer getIdImpresoraEmpresa() {
        return idImpresoraEmpresa;
    }

    public void setIdImpresoraEmpresa(Integer idImpresoraEmpresa) {
        this.idImpresoraEmpresa = idImpresoraEmpresa;
    }

    public Integer getIdImpresora() {
        return idImpresora;
    }

    public void setIdImpresora(Integer idImpresora) {
        this.idImpresora = idImpresora;
    }

    public String getImpresora() {
        return impresora;
    }

    public void setImpresora(String impresora) {
        this.impresora = impresora;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getClaveImpresora() {
        return claveImpresora;
    }

    public void setClaveImpresora(String claveImpresora) {
        this.claveImpresora = claveImpresora;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getClaveEmpresa() {
        return claveEmpresa;
    }

    public void setClaveEmpresa(String claveEmpresa) {
        this.claveEmpresa = claveEmpresa;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public Integer getIdFuncionalidad() {
        return idFuncionalidad;
    }

    public void setIdFuncionalidad(Integer idFuncionalidad) {
        this.idFuncionalidad = idFuncionalidad;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getDescripcionEtiqueta() {
        return descripcionEtiqueta;
    }

    public void setDescripcionEtiqueta(String descripcionEtiqueta) {
        this.descripcionEtiqueta = descripcionEtiqueta;
    }
}

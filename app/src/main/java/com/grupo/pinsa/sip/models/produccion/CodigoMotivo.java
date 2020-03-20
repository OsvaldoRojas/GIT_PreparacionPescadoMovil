package com.grupo.pinsa.sip.models.produccion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 11/11/2019
 */

public class CodigoMotivo {
    @SerializedName("id_codigo_motivo")
    @Expose
    private Integer idCodigoMotivo;
    @SerializedName("id_empresa")
    @Expose
    private Integer idEmpresa;
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("codigo_motivo")
    @Expose
    private String codigoMotivo;
    @SerializedName("apto_producto_terminado")
    @Expose
    private Boolean aptoProductoTerminado;
    @SerializedName("activo")
    @Expose
    private Boolean activo;

    public CodigoMotivo() {
    }

    public CodigoMotivo(Integer idCodigoMotivo, Integer idEmpresa, String clave, String codigoMotivo, Boolean aptoProductoTerminado, Boolean activo) {
        this.idCodigoMotivo = idCodigoMotivo;
        this.idEmpresa = idEmpresa;
        this.clave = clave;
        this.codigoMotivo = codigoMotivo;
        this.aptoProductoTerminado = aptoProductoTerminado;
        this.activo = activo;
    }

    public Integer getIdCodigoMotivo() {
        return idCodigoMotivo;
    }

    public void setIdCodigoMotivo(Integer idCodigoMotivo) {
        this.idCodigoMotivo = idCodigoMotivo;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCodigoMotivo() {
        return codigoMotivo;
    }

    public void setCodigoMotivo(String codigoMotivo) {
        this.codigoMotivo = codigoMotivo;
    }

    public Boolean getAptoProductoTerminado() {
        return aptoProductoTerminado;
    }

    public void setAptoProductoTerminado(Boolean aptoProductoTerminado) {
        this.aptoProductoTerminado = aptoProductoTerminado;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
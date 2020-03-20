package com.grupo.pinsa.sip.models.produccion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 11/11/2019
 */

public class EstadoTarima {
    @SerializedName("id_estado_tarima")
    @Expose
    private Integer idEstadoTarima;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("activo")
    @Expose
    private Boolean activo;

    public EstadoTarima() {
    }

    public EstadoTarima(Integer idEstadoTarima, String estado, String clave, Boolean activo) {
        this.idEstadoTarima = idEstadoTarima;
        this.estado = estado;
        this.clave = clave;
        this.activo = activo;
    }

    public Integer getIdEstadoTarima() {
        return idEstadoTarima;
    }

    public void setIdEstadoTarima(Integer idEstadoTarima) {
        this.idEstadoTarima = idEstadoTarima;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

package com.grupo.pinsa.sip.models.inventarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 11/11/2019
 */

public class TipoArticulo {
    @SerializedName("id_tipo_articulo")
    @Expose
    private Integer idTipoArticulo;
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("tipo_articulo")
    @Expose
    private String tipoArticulo;
    @SerializedName("activo")
    @Expose
    private Boolean activo;

    public TipoArticulo() {
    }

    public TipoArticulo(Integer idTipoArticulo, String clave, String tipoArticulo, Boolean activo) {
        this.idTipoArticulo = idTipoArticulo;
        this.clave = clave;
        this.tipoArticulo = tipoArticulo;
        this.activo = activo;
    }

    public Integer getIdTipoArticulo() {
        return idTipoArticulo;
    }

    public void setIdTipoArticulo(Integer idTipoArticulo) {
        this.idTipoArticulo = idTipoArticulo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(String tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

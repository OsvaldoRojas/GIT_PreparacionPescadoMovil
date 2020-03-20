package com.grupo.pinsa.sip.models.inventarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 11/11/2019
 */

public class ClasificacionArticulo {
    @SerializedName("id_clasificacion_articulo")
    @Expose
    private Integer idClasificacionArticulo;
    @SerializedName("tipo_articulo")
    @Expose
    private TipoArticulo tipoArticulo;
    @SerializedName("clasificacion_articulo")
    @Expose
    private String clasificacion_articulo;
    @SerializedName("activo")
    @Expose
    private Boolean activo;

    public ClasificacionArticulo() {
        tipoArticulo = new TipoArticulo();
    }

    public ClasificacionArticulo(Integer idClasificacionArticulo, TipoArticulo tipoArticulo, String clasificacion_articulo, Boolean activo) {
        this.idClasificacionArticulo = idClasificacionArticulo;
        this.tipoArticulo = tipoArticulo;
        this.clasificacion_articulo = clasificacion_articulo;
        this.activo = activo;
    }

    public Integer getIdClasificacionArticulo() {
        return idClasificacionArticulo;
    }

    public void setIdClasificacionArticulo(Integer idClasificacionArticulo) {
        this.idClasificacionArticulo = idClasificacionArticulo;
    }

    public TipoArticulo getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(TipoArticulo tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public String getClasificacion_articulo() {
        return clasificacion_articulo;
    }

    public void setClasificacion_articulo(String clasificacion_articulo) {
        this.clasificacion_articulo = clasificacion_articulo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

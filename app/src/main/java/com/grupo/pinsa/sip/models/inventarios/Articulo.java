package com.grupo.pinsa.sip.models.inventarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 11/11/2019
 */

public class Articulo {
    @SerializedName("id_articulo")
    @Expose
    private Integer idArticulo;
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("articulo")
    @Expose
    private String articulo;
    @SerializedName("descripcion_corta")
    @Expose
    private String descripcionCorta;
    @SerializedName("unidad_medida")
    @Expose
    private UnidadMedida unidadMedida;
    @SerializedName("clasificacion_articulo")
    @Expose
    private ClasificacionArticulo clasificacionArticulo;
    @SerializedName("activo")
    @Expose
    private Boolean activo;

    public Articulo() {
        unidadMedida = new UnidadMedida();
        clasificacionArticulo = new ClasificacionArticulo();
    }

    public Articulo(Integer idArticulo, String clave, String articulo, String descripcionCorta, UnidadMedida unidadMedida, ClasificacionArticulo clasificacionArticulo, Boolean activo) {
        this.idArticulo = idArticulo;
        this.clave = clave;
        this.articulo = articulo;
        this.descripcionCorta = descripcionCorta;
        this.unidadMedida = unidadMedida;
        this.clasificacionArticulo = clasificacionArticulo;
        this.activo = activo;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public ClasificacionArticulo getClasificacionArticulo() {
        return clasificacionArticulo;
    }

    public void setClasificacionArticulo(ClasificacionArticulo clasificacionArticulo) {
        this.clasificacionArticulo = clasificacionArticulo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
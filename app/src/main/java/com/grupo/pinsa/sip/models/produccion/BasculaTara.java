package com.grupo.pinsa.sip.models.produccion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 02/12/2019
 */

public class BasculaTara {
    @SerializedName("id_bascula_tara")
    @Expose
    private Integer idBasculaTara;
    @SerializedName("id_empresa")
    @Expose
    private Integer idEmpresa;
    @SerializedName("bascula")
    @Expose
    private String bascula;
    @SerializedName("peso_tara")
    @Expose
    private Double pesoTara = 0.0;
    @SerializedName("fecha_tara")
    @Expose
    private String fechaTara;
    @SerializedName("activo")
    @Expose
    private Boolean activo;

    public BasculaTara() {
    }

    public BasculaTara(Integer idBasculaTara, Integer idEmpresa, String bascula, Double pesoTara, String fechaTara, Boolean activo) {
        this.idBasculaTara = idBasculaTara;
        this.idEmpresa = idEmpresa;
        this.bascula = bascula;
        this.pesoTara = pesoTara;
        this.fechaTara = fechaTara;
        this.activo = activo;
    }

    public Integer getIdBasculaTara() {
        return idBasculaTara;
    }

    public void setIdBasculaTara(Integer idBasculaTara) {
        this.idBasculaTara = idBasculaTara;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getBascula() {
        return bascula;
    }

    public void setBascula(String bascula) {
        this.bascula = bascula;
    }

    public Double getPesoTara() {
        return pesoTara;
    }

    public void setPesoTara(Double pesoTara) {
        this.pesoTara = pesoTara;
    }

    public String getFechaTara() {
        return fechaTara;
    }

    public void setFechaTara(String fechaTara) {
        this.fechaTara = fechaTara;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
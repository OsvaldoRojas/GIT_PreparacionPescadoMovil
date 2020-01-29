package com.pinsa.simulador.vista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GrupoEspecie implements Serializable {

    @SerializedName("idEspecie")
    @Expose
    private int idEspecie;

    @SerializedName("idGrupoEspecie")
    @Expose
    private int idEspecieInicial;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("observaciones")
    @Expose
    private String observaciones;

    @SerializedName("activo")
    @Expose
    private Boolean activo;

    @SerializedName("borrado")
    @Expose
    private Boolean borrado;

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getIdEspecieInicial() {
        return idEspecieInicial;
    }

    public void setIdEspecieInicial(int idEspecieInicial) {
        this.idEspecieInicial = idEspecieInicial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }
}

package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Talla implements Serializable {

    @SerializedName("idTalla")
    @Expose
    private int idTalla;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("activo")
    @Expose
    private Boolean activo;

    @SerializedName("simulador")
    @Expose
    private Boolean simulador;

    public int getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(int idTalla) {
        this.idTalla = idTalla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getSimulador() {
        return simulador;
    }

    public void setSimulador(Boolean simulador) {
        this.simulador = simulador;
    }
}

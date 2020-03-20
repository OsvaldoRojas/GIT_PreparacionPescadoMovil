package com.grupo.pinsa.sip.simulador.vista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Etapa implements Serializable {

    @SerializedName("idEtapa")
    @Expose
    private int idEtapa;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("borrado")
    @Expose
    private Boolean borrado;

    public int getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(int idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }
}

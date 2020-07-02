package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TinaPosicion implements Serializable {

    @SerializedName("idTina")
    @Expose
    private String idTina;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public String getIdTina() {
        return idTina;
    }

    public void setIdTina(String idTina) {
        this.idTina = idTina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

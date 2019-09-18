package com.example.simulador_pescado.vista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TinaPosicion implements Serializable {

    @SerializedName("idTina")
    @Expose
    private long idTina;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public long getIdTina() {
        return idTina;
    }

    public void setIdTina(long idTina) {
        this.idTina = idTina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

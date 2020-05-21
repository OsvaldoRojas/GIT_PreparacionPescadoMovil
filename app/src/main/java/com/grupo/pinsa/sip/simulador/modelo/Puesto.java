package com.grupo.pinsa.sip.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Puesto implements Serializable {

    @SerializedName("idPuesto")
    @Expose
    private int id;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

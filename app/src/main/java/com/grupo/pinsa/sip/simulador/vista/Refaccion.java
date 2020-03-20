package com.grupo.pinsa.sip.simulador.vista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Refaccion implements Serializable {

    @SerializedName("idRefaccion")
    @Expose
    private long idRefaccion;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("codigo")
    @Expose
    private String codigo;

    public long getIdRefaccion() {
        return idRefaccion;
    }

    public void setIdRefaccion(long idRefaccion) {
        this.idRefaccion = idRefaccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}

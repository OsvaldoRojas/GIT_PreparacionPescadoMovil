package com.grupo.pinsa.sip.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.grupo.pinsa.sip.simulador.utilerias.Constantes;

import java.io.Serializable;

public class Bascula implements Serializable {

    @SerializedName("idBascula")
    @Expose
    private int idBascula;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    private String claveMaquina;
    private Constantes.ESTADO estado;

    public String getClaveMaquina() {
        return claveMaquina;
    }

    public void setClaveMaquina(String claveMaquina) {
        this.claveMaquina = claveMaquina;
    }

    public int getIdBascula() {
        return idBascula;
    }

    public void setIdBascula(int idBascula) {
        this.idBascula = idBascula;
    }

    public Constantes.ESTADO getEstado() {
        return estado;
    }

    public void setEstado(Constantes.ESTADO estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

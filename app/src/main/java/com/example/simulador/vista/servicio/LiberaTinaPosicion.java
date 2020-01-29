package com.example.simulador.vista.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LiberaTinaPosicion implements Serializable {

    @SerializedName("idDescongeladoPosicionTina")
    @Expose
    private int idPosicion;

    @SerializedName("nivel")
    @Expose
    private int nivel;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    public int getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(int idPosicion) {
        this.idPosicion = idPosicion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

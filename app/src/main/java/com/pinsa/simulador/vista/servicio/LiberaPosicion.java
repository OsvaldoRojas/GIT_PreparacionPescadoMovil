package com.pinsa.simulador.vista.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LiberaPosicion implements Serializable {

    @SerializedName("idDescongeladoPosicionTina")
    @Expose
    private int idPosicion;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    public int getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(int idPosicion) {
        this.idPosicion = idPosicion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

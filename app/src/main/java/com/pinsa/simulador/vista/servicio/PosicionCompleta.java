package com.pinsa.simulador.vista.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PosicionCompleta implements Serializable {

    @SerializedName("idDescongeladoPosicionTina")
    @Expose
    private int idPosicion;

    @SerializedName("bloqueado")
    @Expose
    private Boolean completa;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    public int getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(int idPosicion) {
        this.idPosicion = idPosicion;
    }

    public Boolean getCompleta() {
        return completa;
    }

    public void setCompleta(Boolean completa) {
        this.completa = completa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

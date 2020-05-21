package com.grupo.pinsa.sip.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmpleadoEstacion {

    @SerializedName("idEstacion")
    @Expose
    private int idEstacion;

    @SerializedName("estacion")
    @Expose
    private String estacion;

    @SerializedName("posicionPrincipal")
    @Expose
    private String posicionPrincipal;

    @SerializedName("posicionAlterna")
    @Expose
    private String posicionAlterna;

    @SerializedName("turno")
    @Expose
    private Boolean turno;

    @SerializedName("etapa")
    @Expose
    private String etapa;

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public String getPosicionPrincipal() {
        return posicionPrincipal;
    }

    public void setPosicionPrincipal(String posicionPrincipal) {
        this.posicionPrincipal = posicionPrincipal;
    }

    public String getPosicionAlterna() {
        return posicionAlterna;
    }

    public void setPosicionAlterna(String posicionAlterna) {
        this.posicionAlterna = posicionAlterna;
    }

    public Boolean getTurno() {
        return turno;
    }

    public void setTurno(Boolean turno) {
        this.turno = turno;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }
}

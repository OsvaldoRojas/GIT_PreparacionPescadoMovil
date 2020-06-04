package com.grupo.pinsa.sip.views.simulador.modelo;

import com.grupo.pinsa.sip.views.simulador.utilerias.Constantes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OperadorBascula implements Serializable {

    @SerializedName("idPreseleccionEstacion")
    @Expose
    private int idPreseleccionEstacion;

    @SerializedName("idEmpleado")
    @Expose
    private int idEmpleado;

    @SerializedName("idPosicionPrincipal")
    @Expose
    private int idPosicionPrincipal;

    @SerializedName("idPosicionAlterna")
    @Expose
    private int idPosicionAlterna;

    @SerializedName("libre")
    @Expose
    private Boolean libre;

    @SerializedName("turno")
    @Expose
    private Boolean turno;

    @SerializedName("activo")
    @Expose
    private Boolean activo;

    @SerializedName("estacion")
    @Expose
    private String estacion;

    private String claveMaquina;
    private Constantes.ESTADO estado;

    public String getClaveMaquina() {
        return claveMaquina;
    }

    public void setClaveMaquina(String claveMaquina) {
        this.claveMaquina = claveMaquina;
    }

    public Constantes.ESTADO getEstado() {
        return estado;
    }

    public void setEstado(Constantes.ESTADO estado) {
        this.estado = estado;
    }

    public int getIdPreseleccionEstacion() {
        return idPreseleccionEstacion;
    }

    public void setIdPreseleccionEstacion(int idPreseleccionEstacion) {
        this.idPreseleccionEstacion = idPreseleccionEstacion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdPosicionPrincipal() {
        return idPosicionPrincipal;
    }

    public void setIdPosicionPrincipal(int idPosicionPrincipal) {
        this.idPosicionPrincipal = idPosicionPrincipal;
    }

    public int getIdPosicionAlterna() {
        return idPosicionAlterna;
    }

    public void setIdPosicionAlterna(int idPosicionAlterna) {
        this.idPosicionAlterna = idPosicionAlterna;
    }

    public Boolean getLibre() {
        return libre;
    }

    public void setLibre(Boolean libre) {
        this.libre = libre;
    }

    public Boolean getTurno() {
        return turno;
    }

    public void setTurno(Boolean turno) {
        this.turno = turno;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }
}

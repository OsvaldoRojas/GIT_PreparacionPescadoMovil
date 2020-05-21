package com.grupo.pinsa.sip.simulador.modelo;

import com.grupo.pinsa.sip.simulador.utilerias.Constantes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OperadorMontacargas implements Serializable {

    @SerializedName("idPreseleccionMontacarga")
    @Expose
    private int idPreseleccionMontacarga;

    @SerializedName("idEmpleado")
    @Expose
    private String idEmpleado;

    @SerializedName("turno")
    @Expose
    private Boolean turno;

    @SerializedName("libre")
    @Expose
    private Boolean libre;

    @SerializedName("activo")
    @Expose
    private Boolean activo;

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

    public int getIdPreseleccionMontacarga() {
        return idPreseleccionMontacarga;
    }

    public void setIdPreseleccionMontacarga(int idPreseleccionMontacarga) {
        this.idPreseleccionMontacarga = idPreseleccionMontacarga;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Boolean getTurno() {
        return turno;
    }

    public void setTurno(Boolean turno) {
        this.turno = turno;
    }

    public Boolean getLibre() {
        return libre;
    }

    public void setLibre(Boolean libre) {
        this.libre = libre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

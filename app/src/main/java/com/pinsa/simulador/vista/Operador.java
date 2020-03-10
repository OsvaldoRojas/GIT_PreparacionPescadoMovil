package com.pinsa.simulador.vista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pinsa.simulador.utilerias.Constantes;

import java.io.Serializable;

public class Operador implements Serializable {

    @SerializedName("idEstacion")
    @Expose
    private int idEstacion;

    @SerializedName("estacion")
    @Expose
    private String estacion;

    @SerializedName("libre")
    @Expose
    private boolean libre;

    private Constantes.ESTADO estado;
    private int idEmpleado;
    private Boolean turno;

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

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }

    public Constantes.ESTADO getEstado() {
        return estado;
    }

    public void setEstado(Constantes.ESTADO estado) {
        this.estado = estado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Boolean isTurno() {
        return turno;
    }

    public void setTurno(Boolean turno) {
        this.turno = turno;
    }
}

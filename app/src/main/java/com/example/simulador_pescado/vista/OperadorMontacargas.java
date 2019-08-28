package com.example.simulador_pescado.vista;

import com.example.simulador_pescado.Utilerias.Constantes;

import java.io.Serializable;

public class OperadorMontacargas implements Serializable {

    private int idPreseleccionMontacarga;
    private String idEmpleado;
    private Boolean turno;
    private Boolean libre;
    private Boolean activo;

    private Constantes.ESTADO estado;

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

package com.example.simulador_pescado.vista;

import com.example.simulador_pescado.Utilerias.Constantes;

import java.io.Serializable;

public class OperadorBascula implements Serializable {

    private int idPreseleccionEstacion;
    private int idEmpleado;
    private int idPosicionPrincipal;
    private int idPosicionAlterna;
    private Boolean libre;
    private Boolean turno;
    private Boolean activo;
    private String estacion;

    private Constantes.ESTADO estado;

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

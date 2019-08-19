package com.example.simulador_pescado.vista;

import com.example.simulador_pescado.Utilerias.Constantes;

import java.io.Serializable;

public class OperadorMontacargas implements Serializable {

    private int idMontacargaPreseleccion;
    private String idEmpleado;
    private String fechaCreador;
    private String usuarioCreador;
    private String fechaModifico;
    private String usuarioModifico;
    private Boolean turno;
    private Boolean libre;
    private Boolean activo;
    private Boolean borrado;

    private Constantes.ESTADO estado;

    public Constantes.ESTADO getEstado() {
        return estado;
    }

    public void setEstado(Constantes.ESTADO estado) {
        this.estado = estado;
    }

    public int getIdMontacargaPreseleccion() {
        return idMontacargaPreseleccion;
    }

    public void setIdMontacargaPreseleccion(int idMontacargaPreseleccion) {
        this.idMontacargaPreseleccion = idMontacargaPreseleccion;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getFechaCreador() {
        return fechaCreador;
    }

    public void setFechaCreador(String fechaCreador) {
        this.fechaCreador = fechaCreador;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public String getFechaModifico() {
        return fechaModifico;
    }

    public void setFechaModifico(String fechaModifico) {
        this.fechaModifico = fechaModifico;
    }

    public String getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(String usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
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

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }
}

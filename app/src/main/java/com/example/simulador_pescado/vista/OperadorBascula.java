package com.example.simulador_pescado.vista;

import com.example.simulador_pescado.Utilerias.Constantes;

public class OperadorBascula {

    private int idEstacion;
    private TinaPosicion principal;
    private TinaPosicion alterna;
    private Empleado empleado;
    private Boolean turno;
    private Boolean libre;
    private Boolean activo;
    private String fechaCreador;
    private String usuarioCreador;
    private String fechaModifico;
    private String usuarioModifico;
    private Boolean borrado;

    private Constantes.ESTADO estado;

    public Constantes.ESTADO getEstado() {
        return estado;
    }

    public void setEstado(Constantes.ESTADO estado) {
        this.estado = estado;
    }

    public TinaPosicion getPrincipal() {
        return principal;
    }

    public void setPrincipal(TinaPosicion principal) {
        this.principal = principal;
    }

    public TinaPosicion getAlterna() {
        return alterna;
    }

    public void setAlterna(TinaPosicion alterna) {
        this.alterna = alterna;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
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

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }
}

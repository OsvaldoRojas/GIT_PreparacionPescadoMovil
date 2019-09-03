package com.example.simulador_pescado.vista;

import java.util.List;

public class OrdenMantenimientoGuardar {

    private long idOrdenMantenimiento;
    private long idMaquinaria;
    private String descripcion;
    private String usuario;
    private List<ArtefactoServicio> artefactos;

    public long getIdOrdenMantenimiento() {
        return idOrdenMantenimiento;
    }

    public void setIdOrdenMantenimiento(long idOrdenMantenimiento) {
        this.idOrdenMantenimiento = idOrdenMantenimiento;
    }

    public long getIdMaquinaria() {
        return idMaquinaria;
    }

    public void setIdMaquinaria(long idMaquinaria) {
        this.idMaquinaria = idMaquinaria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<ArtefactoServicio> getArtefactos() {
        return artefactos;
    }

    public void setArtefactos(List<ArtefactoServicio> artefactos) {
        this.artefactos = artefactos;
    }
}

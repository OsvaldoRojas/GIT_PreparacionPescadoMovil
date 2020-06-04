package com.grupo.pinsa.sip.views.simulador.modelo.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrdenMantenimientoGuardar {

    @SerializedName("idOrdenMantenimiento")
    @Expose
    private long idOrdenMantenimiento;

    @SerializedName("idMaquinaria")
    @Expose
    private long idMaquinaria;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("artefactos")
    @Expose
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

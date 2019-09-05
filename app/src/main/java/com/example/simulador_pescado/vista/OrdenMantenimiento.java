package com.example.simulador_pescado.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrdenMantenimiento implements Serializable {

    private long idOrdenMantenimiento;
    private String folio;
    private String fechaInicio;
    private String fechaFin;
    private long idEmpleado;
    private String nombreEmpleado;
    private String aMaternoEmpleado;
    private String aPaternoEmpleado;
    private String descripcion;
    private String solucion;
    private Boolean finalizada;
    private Maquinaria maquinaria;
    private List<Artefacto> artefactos = new ArrayList<>();
    private List<RefaccionOrden> refacciones = new ArrayList<>();

    public long getIdOrdenMantenimiento() {
        return idOrdenMantenimiento;
    }

    public void setIdOrdenMantenimiento(long idOrdenMantenimiento) {
        this.idOrdenMantenimiento = idOrdenMantenimiento;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getaMaternoEmpleado() {
        return aMaternoEmpleado;
    }

    public void setaMaternoEmpleado(String aMaternoEmpleado) {
        this.aMaternoEmpleado = aMaternoEmpleado;
    }

    public String getaPaternoEmpleado() {
        return aPaternoEmpleado;
    }

    public void setaPaternoEmpleado(String aPaternoEmpleado) {
        this.aPaternoEmpleado = aPaternoEmpleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public Maquinaria getMaquinaria() {
        return maquinaria;
    }

    public void setMaquinaria(Maquinaria maquinaria) {
        this.maquinaria = maquinaria;
    }

    public List<Artefacto> getArtefactos() {
        return artefactos;
    }

    public void setArtefactos(List<Artefacto> artefactos) {
        this.artefactos = artefactos;
    }

    public List<RefaccionOrden> getRefacciones() {
        return refacciones;
    }

    public void setRefacciones(List<RefaccionOrden> refacciones) {
        this.refacciones = refacciones;
    }

    public Boolean getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }
}

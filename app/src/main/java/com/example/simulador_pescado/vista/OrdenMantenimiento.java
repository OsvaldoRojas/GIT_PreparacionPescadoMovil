package com.example.simulador_pescado.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrdenMantenimiento implements Serializable {

    private int folio;
    private String fechaInicio;
    private String fechaFin;
    private String equipo;
    private String mecanico;
    private String nombreEmpleado;
    private String aMaternoEmpleado;
    private String aPaternoEmpleado;
    private String descripcion;
    private List<Artefacto> listaArtefactos = new ArrayList<>();
    private List<RefaccionLista> listaRefacciones = new ArrayList<>();

    public OrdenMantenimiento(){}

    public OrdenMantenimiento(int folio, String fecha, String equipo, String mecanico, String descripcion){
        this.folio = folio;
        this.fechaInicio = fecha;
        this.equipo = equipo;
        this.mecanico = mecanico;
        this.descripcion = descripcion;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
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

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getMecanico() {
        return mecanico;
    }

    public void setMecanico(String mecanico) {
        this.mecanico = mecanico;
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

    public List<Artefacto> getListaArtefactos() {
        return listaArtefactos;
    }

    public void setListaArtefactos(List<Artefacto> listaArtefactos) {
        this.listaArtefactos = listaArtefactos;
    }

    public List<RefaccionLista> getListaRefacciones() {
        return listaRefacciones;
    }

    public void setListaRefacciones(List<RefaccionLista> listaRefacciones) {
        this.listaRefacciones = listaRefacciones;
    }
}

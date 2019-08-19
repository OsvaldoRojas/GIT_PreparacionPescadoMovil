package com.example.simulador_pescado.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrdenMantenimiento implements Serializable {

    private int folio;
    private String fecha;
    private String equipo;
    private String mecanico;
    private String descripcion;
    private List<ArtefactoLista> listaArtefactos = new ArrayList<>();

    public OrdenMantenimiento(){}

    public OrdenMantenimiento(int folio, String fecha, String equipo, String mecanico, String descripcion){
        this.folio = folio;
        this.fecha = fecha;
        this.equipo = equipo;
        this.mecanico = mecanico;
        this.descripcion = descripcion;
    }

    public List<ArtefactoLista> getListaArtefactos() {
        return listaArtefactos;
    }

    public void setListaArtefactos(List<ArtefactoLista> listaArtefactos) {
        this.listaArtefactos = listaArtefactos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
}

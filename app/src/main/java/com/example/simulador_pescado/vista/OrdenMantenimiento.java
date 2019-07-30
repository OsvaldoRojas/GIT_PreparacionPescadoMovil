package com.example.simulador_pescado.vista;

public class OrdenMantenimiento {

    private int folio;
    private String fecha;
    private String equipo;
    private String mecanico;

    public OrdenMantenimiento(){}
    public OrdenMantenimiento(int folio, String fecha, String equipo, String mecanico){
        this.folio = folio;
        this.fecha = fecha;
        this.equipo = equipo;
        this.mecanico = mecanico;
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

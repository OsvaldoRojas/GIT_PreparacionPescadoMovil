package com.example.simulador_pescado.vista;

public class Talla {

    private int idTalla;
    private String descripcion;
    private Boolean activo;
    private Boolean simulador;

    public int getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(int idTalla) {
        this.idTalla = idTalla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getSimulador() {
        return simulador;
    }

    public void setSimulador(Boolean simulador) {
        this.simulador = simulador;
    }
}

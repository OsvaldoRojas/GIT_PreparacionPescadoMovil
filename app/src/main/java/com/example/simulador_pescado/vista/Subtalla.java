package com.example.simulador_pescado.vista;

import java.io.Serializable;

public class Subtalla implements Serializable {

    private int idSubtalla;
    private String descripcion;
    private int piezas;
    private float pesoPromedioPieza;
    private Boolean activo;
    private Boolean simulador;

    public int getIdSubtalla() {
        return idSubtalla;
    }

    public void setIdSubtalla(int idSubtalla) {
        this.idSubtalla = idSubtalla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPiezas() {
        return piezas;
    }

    public void setPiezas(int piezas) {
        this.piezas = piezas;
    }

    public float getPesoPromedioPieza() {
        return pesoPromedioPieza;
    }

    public void setPesoPromedioPieza(float pesoPromedioPieza) {
        this.pesoPromedioPieza = pesoPromedioPieza;
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

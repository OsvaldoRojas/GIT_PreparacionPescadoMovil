package com.pinsa.simulador.vista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Subtalla implements Serializable {

    @SerializedName("idSubtalla")
    @Expose
    private int idSubtalla;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("piezas")
    @Expose
    private int piezas;

    @SerializedName("pesoPromedioPieza")
    @Expose
    private float pesoPromedioPieza;

    @SerializedName("activo")
    @Expose
    private Boolean activo;

    @SerializedName("simulador")
    @Expose
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

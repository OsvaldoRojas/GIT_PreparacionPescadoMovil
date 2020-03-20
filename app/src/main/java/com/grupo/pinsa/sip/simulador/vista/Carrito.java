package com.grupo.pinsa.sip.simulador.vista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Carrito implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    private String descripcion;
    private String especie;
    private String subtalla;
    private boolean seleccionado = false;
    private boolean seleccionadoGeneral = false;
    private boolean seleccionadoSuma = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getSubtalla() {
        return subtalla;
    }

    public void setSubtalla(String subtalla) {
        this.subtalla = subtalla;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public boolean isSeleccionadoGeneral() {
        return seleccionadoGeneral;
    }

    public void setSeleccionadoGeneral(boolean seleccionadoGeneral) {
        this.seleccionadoGeneral = seleccionadoGeneral;
    }

    public boolean isSeleccionadoSuma() {
        return seleccionadoSuma;
    }

    public void setSeleccionadoSuma(boolean seleccionadoSuma) {
        this.seleccionadoSuma = seleccionadoSuma;
    }
}

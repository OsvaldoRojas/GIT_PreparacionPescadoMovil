package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TinaProceso implements Serializable {

    @SerializedName("idTina")
    @Expose
    private String id;

    @SerializedName("posicion")
    @Expose
    private String posicion;

    @SerializedName("pesoActual")
    @Expose
    private long peso;

    @SerializedName("etapaActual")
    @Expose
    private String etapaActual;

    @SerializedName("etapaSiguiente")
    @Expose
    private String etapaSiguiente;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getEtapaActual() {
        return etapaActual;
    }

    public void setEtapaActual(String etapaActual) {
        this.etapaActual = etapaActual;
    }

    public String getEtapaSiguiente() {
        return etapaSiguiente;
    }

    public void setEtapaSiguiente(String etapaSiguiente) {
        this.etapaSiguiente = etapaSiguiente;
    }

    public long getPeso() {
        return peso;
    }

    public void setPeso(long peso) {
        this.peso = peso;
    }
}

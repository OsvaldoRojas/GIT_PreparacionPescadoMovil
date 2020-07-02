package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ControlDescongeladoDetalle implements Serializable {

    @SerializedName("hipocloritoSodio")
    @Expose
    private float hipocloritoSodio;

    @SerializedName("antiespumante")
    @Expose
    private float antiespumante;

    @SerializedName("consumoTotalDeAgua")
    @Expose
    private float consumoTotalAgua;

    @SerializedName("pescadoProcesado")
    @Expose
    private float pescadoProcesado;

    @SerializedName("loteHipoclorito")
    @Expose
    private int loteHipoclorito;

    @SerializedName("loteAntiespumante")
    @Expose
    private int loteAntiespumante;

    @SerializedName("controlProcesoDescongelado")
    @Expose
    private List<ControlDescongelado> controlesDescongelado;

    @SerializedName("datosPescadoProcesado")
    @Expose
    private List<PescadoProcesado> pescadosProcesados;

    private String fecha;
    private String claveProceso;

    public float getHipocloritoSodio() {
        return hipocloritoSodio;
    }

    public void setHipocloritoSodio(float hipocloritoSodio) {
        this.hipocloritoSodio = hipocloritoSodio;
    }

    public float getAntiespumante() {
        return antiespumante;
    }

    public void setAntiespumante(float antiespumante) {
        this.antiespumante = antiespumante;
    }

    public float getConsumoTotalAgua() {
        return consumoTotalAgua;
    }

    public void setConsumoTotalAgua(float consumoTotalAgua) {
        this.consumoTotalAgua = consumoTotalAgua;
    }

    public float getPescadoProcesado() {
        return pescadoProcesado;
    }

    public void setPescadoProcesado(float pescadoProcesado) {
        this.pescadoProcesado = pescadoProcesado;
    }

    public int getLoteHipoclorito() {
        return loteHipoclorito;
    }

    public void setLoteHipoclorito(int loteHipoclorito) {
        this.loteHipoclorito = loteHipoclorito;
    }

    public int getLoteAntiespumante() {
        return loteAntiespumante;
    }

    public void setLoteAntiespumante(int loteAntiespumante) {
        this.loteAntiespumante = loteAntiespumante;
    }

    public List<ControlDescongelado> getControlesDescongelado() {
        return controlesDescongelado;
    }

    public void setControlesDescongelado(List<ControlDescongelado> controlesDescongelado) {
        this.controlesDescongelado = controlesDescongelado;
    }

    public List<PescadoProcesado> getPescadosProcesados() {
        return pescadosProcesados;
    }

    public void setPescadosProcesados(List<PescadoProcesado> pescadosProcesados) {
        this.pescadosProcesados = pescadosProcesados;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getClaveProceso() {
        return claveProceso;
    }

    public void setClaveProceso(String claveProceso) {
        this.claveProceso = claveProceso;
    }
}

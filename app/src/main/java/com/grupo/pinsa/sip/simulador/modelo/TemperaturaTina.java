package com.grupo.pinsa.sip.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TemperaturaTina {

    @SerializedName("idHistoricoTina")
    @Expose
    private long idHistoricoTina;

    @SerializedName("claveTina")
    @Expose
    private String descripcion;

    @SerializedName("temperatura")
    @Expose
    private float temperatura;

    @SerializedName("especie")
    @Expose
    private String especie;

    @SerializedName("subtalla")
    @Expose
    private String subtalla;

    @SerializedName("etapa")
    @Expose
    private int idEtapa;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    private boolean seleccionado = false;
    private boolean seleccionadoGeneral = false;
    private boolean seleccionadoSuma = false;

    public long getIdHistoricoTina() {
        return idHistoricoTina;
    }

    public void setIdHistoricoTina(long idHistoricoTina) {
        this.idHistoricoTina = idHistoricoTina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
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

    public int getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(int idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ControlDescongelado implements Serializable {

    @SerializedName("idProcesoDescongelado")
    @Expose
    private long id;

    @SerializedName("claveProceso")
    @Expose
    private String clave;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    @SerializedName("hora")
    @Expose
    private String hora;

    @SerializedName("loteHipoclorito")
    @Expose
    private int loteHipoclorito;

    @SerializedName("loteAntiespumante")
    @Expose
    private int loteAntiespumante;

    @SerializedName("concentrado")
    @Expose
    private float concentrado;

    @SerializedName("temperatura")
    @Expose
    private float temperatura;

    @SerializedName("consumoCloro")
    @Expose
    private float consumoCloro;

    @SerializedName("consumoAgua")
    @Expose
    private float consumoAgua;

    @SerializedName("antiespumante")
    @Expose
    private float antiespumante;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("borrado")
    @Expose
    private boolean borrado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    public float getConcentrado() {
        return concentrado;
    }

    public void setConcentrado(float concentrado) {
        this.concentrado = concentrado;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getConsumoCloro() {
        return consumoCloro;
    }

    public void setConsumoCloro(float consumoCloro) {
        this.consumoCloro = consumoCloro;
    }

    public float getAntiespumante() {
        return antiespumante;
    }

    public void setAntiespumante(float antiespumante) {
        this.antiespumante = antiespumante;
    }

    public float getConsumoAgua() {
        return consumoAgua;
    }

    public void setConsumoAgua(float consumoAgua) {
        this.consumoAgua = consumoAgua;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
}

package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Modulo implements Serializable {

    @SerializedName("idModulo")
    @Expose
    private long id;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("capacidadActual")
    @Expose
    private int capacidadActual;

    @SerializedName("capacidadMaxima")
    @Expose
    private int capacidadMaxima;

    @SerializedName("idBascula")
    @Expose
    private long idBascula;

    @SerializedName("bascula")
    @Expose
    private Bascula bascula;

    @SerializedName("fila")
    @Expose
    private int fila;

    @SerializedName("columna")
    @Expose
    private int columna;

    @SerializedName("numeroAreas")
    @Expose
    private int numeroAreas;

    @SerializedName("temperaturas")
    @Expose
    private float[] temperaturas;

    private int totalTouch = 0;

    private List<Carrito> carritos = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public long getIdBascula() {
        return idBascula;
    }

    public void setIdBascula(long idBascula) {
        this.idBascula = idBascula;
    }

    public Bascula getBascula() {
        return bascula;
    }

    public void setBascula(Bascula bascula) {
        this.bascula = bascula;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getNumeroAreas() {
        return numeroAreas;
    }

    public void setNumeroAreas(int numeroAreas) {
        this.numeroAreas = numeroAreas;
    }

    public float[] getTemperaturas() {
        return temperaturas;
    }

    public void setTemperaturas(float[] temperaturas) {
        this.temperaturas = temperaturas;
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }

    public int getTotalTouch() {
        return totalTouch;
    }

    public void setTotalTouch(int totalTouch) {
        this.totalTouch = totalTouch;
    }
}

package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cocedor implements Serializable {

    @SerializedName("idCocedor")
    @Expose
    private long id;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("tamanio")
    @Expose
    private String tamano;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("capacidad")
    @Expose
    private int capacidad;

    @SerializedName("compensacion")
    @Expose
    private Boolean compensacion;

    @SerializedName("estatus")
    @Expose
    private String estatus;

    @SerializedName("especie")
    @Expose
    private String especie;

    @SerializedName("especialidad")
    @Expose
    private String especialidad;

    @SerializedName("tiempoRestante")
    @Expose
    private String tiempoRestante;

    @SerializedName("iniciado")
    @Expose
    private Boolean iniciado;

    private String totalCarritos;

    @SerializedName("numeroCocida")
    @Expose
    private String numeroCocida;

    @SerializedName("idCocida")
    @Expose
    private long idCocida;

    @SerializedName("numeroRegistro")
    @Expose
    private String numeroRegistro;

    @SerializedName("fechaInicio")
    @Expose
    private String fechaInicio;

    @SerializedName("horaInicio")
    @Expose
    private String horaInicio;

    @SerializedName("receta")
    @Expose
    private String receta;

    @SerializedName("bascula")
    @Expose
    private Bascula bascula;

    @SerializedName("plan")
    @Expose
    private boolean plan;

    private int totalCocidas;

    private List<Carrito> carritos = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(String tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public Boolean getIniciado() {
        return iniciado;
    }

    public void setIniciado(Boolean iniciado) {
        this.iniciado = iniciado;
    }

    public String getTotalCarritos() {
        return totalCarritos;
    }

    public void setTotalCarritos(String totalCarritos) {
        this.totalCarritos = totalCarritos;
    }

    public String getNumeroCocida() {
        return numeroCocida;
    }

    public void setNumeroCocida(String numeroCocida) {
        this.numeroCocida = numeroCocida;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }

    public Boolean getCompensacion() {
        return compensacion;
    }

    public void setCompensacion(Boolean compensacion) {
        this.compensacion = compensacion;
    }

    public long getIdCocida() {
        return idCocida;
    }

    public void setIdCocida(long idCocida) {
        this.idCocida = idCocida;
    }

    public int getTotalCocidas() {
        return totalCocidas;
    }

    public void setTotalCocidas(int totalCocidas) {
        this.totalCocidas = totalCocidas;
    }

    public Bascula getBascula() {
        return bascula;
    }

    public void setBascula(Bascula bascula) {
        this.bascula = bascula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isPlan() {
        return plan;
    }

    public void setPlan(boolean plan) {
        this.plan = plan;
    }
}

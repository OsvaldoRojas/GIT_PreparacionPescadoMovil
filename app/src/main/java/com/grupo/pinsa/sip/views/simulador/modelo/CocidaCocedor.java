package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CocidaCocedor {

    @SerializedName("idCocida")
    @Expose
    private long id;

    @SerializedName("tamanio")
    @Expose
    private String descripcion;

    @SerializedName("subtalla")
    @Expose
    private String subtalla;

    @SerializedName("especie")
    @Expose
    private String especie;

    @SerializedName("capacidad")
    @Expose
    private int capacidad;

    @SerializedName("horaInicio")
    @Expose
    private String horaInicio;

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

    public String getSubtalla() {
        return subtalla;
    }

    public void setSubtalla(String subtalla) {
        this.subtalla = subtalla;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
}

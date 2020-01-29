package com.example.simulador.vista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cocida implements Serializable {

    @SerializedName("idAsignacionCocida")
    @Expose
    private int id;

    @SerializedName("talla")
    @Expose
    private Talla talla;

    @SerializedName("subtalla")
    @Expose
    private Subtalla subtalla;

    @SerializedName("especie")
    @Expose
    private GrupoEspecie especie;

    @SerializedName("especialidad")
    @Expose
    private Especialidad especialiad;

    @SerializedName("turno")
    @Expose
    private boolean turno;

    @SerializedName("tinasPreseleccionadas")
    @Expose
    private int tinasPreseleccionadas;

    @SerializedName("tinasSimulador")
    @Expose
    private int tinasSimulador;

    private boolean seleccionado = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Talla getTalla() {
        return talla;
    }

    public void setTalla(Talla talla) {
        this.talla = talla;
    }

    public Subtalla getSubtalla() {
        return subtalla;
    }

    public void setSubtalla(Subtalla subtalla) {
        this.subtalla = subtalla;
    }

    public GrupoEspecie getEspecie() {
        return especie;
    }

    public void setEspecie(GrupoEspecie especie) {
        this.especie = especie;
    }

    public Especialidad getEspecialiad() {
        return especialiad;
    }

    public void setEspecialiad(Especialidad especialiad) {
        this.especialiad = especialiad;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public int getTinasPreseleccionadas() {
        return tinasPreseleccionadas;
    }

    public void setTinasPreseleccionadas(int tinasPreseleccionadas) {
        this.tinasPreseleccionadas = tinasPreseleccionadas;
    }

    public int getTinasSimulador() {
        return tinasSimulador;
    }

    public void setTinasSimulador(int tinasSimulador) {
        this.tinasSimulador = tinasSimulador;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}

package com.grupo.pinsa.sip.views.simulador.modelo;

import com.grupo.pinsa.sip.views.simulador.utilerias.Constantes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PosicionEstibaDescongelado implements Serializable {

    @SerializedName("idDescongeladoPosicionTina")
    @Expose
    private int idPosicion;

    @SerializedName("conteoNivel")
    @Expose
    private int conteoNivel;

    @SerializedName("especie")
    @Expose
    private String especie;

    @SerializedName("subtalla")
    @Expose
    private String subtalla;

    @SerializedName("especialidad")
    @Expose
    private String especialidad;

    @SerializedName("fila")
    @Expose
    private String fila;

    @SerializedName("columna")
    @Expose
    private String columna;

    @SerializedName("completado")
    @Expose
    private Boolean completa;

    @SerializedName("valvulaEncendida")
    @Expose
    private Boolean valvulaEncendida;

    @SerializedName("minutos")
    @Expose
    private int minutos;

    private String claveMaquina;
    private Constantes.ESTADO estado;

    public String getClaveMaquina() {
        return claveMaquina;
    }

    public void setClaveMaquina(String claveMaquina) {
        this.claveMaquina = claveMaquina;
    }

    public Constantes.ESTADO getEstado() {
        return estado;
    }

    public void setEstado(Constantes.ESTADO estado) {
        this.estado = estado;
    }

    public int getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(int idPosicion) {
        this.idPosicion = idPosicion;
    }

    public int getConteoNivel() {
        return conteoNivel;
    }

    public void setConteoNivel(int conteoNivel) {
        this.conteoNivel = conteoNivel;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public Boolean getCompleta() {
        return completa;
    }

    public void setCompleta(Boolean completa) {
        this.completa = completa;
    }

    public Boolean getValvulaEncendida() {
        return valvulaEncendida;
    }

    public void setValvulaEncendida(Boolean valvulaEncendida) {
        this.valvulaEncendida = valvulaEncendida;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }
}

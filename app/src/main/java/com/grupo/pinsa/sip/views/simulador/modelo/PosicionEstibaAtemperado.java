package com.grupo.pinsa.sip.views.simulador.modelo;

import com.grupo.pinsa.sip.views.simulador.utilerias.Constantes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PosicionEstibaAtemperado implements Serializable {

    @SerializedName("idAtemperadoPosicionTina")
    @Expose
    private int idAtemperadoPosicionTina;

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

    @SerializedName("bloqueado")
    @Expose
    private Boolean bloqueado;

    @SerializedName("fechaProceso")
    @Expose
    private String fechaProceso;

    @SerializedName("turno")
    @Expose
    private int turno;

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

    public int getIdAtemperadoPosicionTina() {
        return idAtemperadoPosicionTina;
    }

    public void setIdAtemperadoPosicionTina(int idAtemperadoPosicionTina) {
        this.idAtemperadoPosicionTina = idAtemperadoPosicionTina;
    }

    public int getConteoNivel() {
        return conteoNivel;
    }

    public void setConteoNivel(int conteoNivel) {
        this.conteoNivel = conteoNivel;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
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

    public String getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(String fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
}

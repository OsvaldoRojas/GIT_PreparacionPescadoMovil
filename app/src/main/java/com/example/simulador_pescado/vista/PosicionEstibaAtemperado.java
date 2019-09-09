package com.example.simulador_pescado.vista;

import com.example.simulador_pescado.Utilerias.Constantes;

import java.io.Serializable;

public class PosicionEstibaAtemperado implements Serializable {

    private int idAtemperadoPosicionTina;
    private int conteoNivel;
    private String especie;
    private String subtalla;
    private String especialidad;
    private Boolean bloqueado;

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
}

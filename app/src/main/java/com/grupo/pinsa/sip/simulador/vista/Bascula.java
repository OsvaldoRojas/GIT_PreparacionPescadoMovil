package com.grupo.pinsa.sip.simulador.vista;

import com.grupo.pinsa.sip.simulador.utilerias.Constantes;

import java.io.Serializable;

public class Bascula implements Serializable {

    private int idBascula;

    private String claveMaquina;
    private Constantes.ESTADO estado;

    public String getClaveMaquina() {
        return claveMaquina;
    }

    public void setClaveMaquina(String claveMaquina) {
        this.claveMaquina = claveMaquina;
    }

    public int getIdBascula() {
        return idBascula;
    }

    public void setIdBascula(int idBascula) {
        this.idBascula = idBascula;
    }

    public Constantes.ESTADO getEstado() {
        return estado;
    }

    public void setEstado(Constantes.ESTADO estado) {
        this.estado = estado;
    }
}

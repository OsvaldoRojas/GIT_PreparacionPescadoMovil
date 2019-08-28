package com.example.simulador_pescado.vista;

import com.example.simulador_pescado.Utilerias.Constantes;

import java.io.Serializable;

public class Bascula implements Serializable {

    private int idBascula;
    private Constantes.ESTADO estado;

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

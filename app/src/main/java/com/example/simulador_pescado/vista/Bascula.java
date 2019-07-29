package com.example.simulador_pescado.vista;

import com.example.simulador_pescado.Utilerias.Constantes;

public class Bascula {

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

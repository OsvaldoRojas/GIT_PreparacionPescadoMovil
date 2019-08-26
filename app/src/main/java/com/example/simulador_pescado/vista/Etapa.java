package com.example.simulador_pescado.vista;

import java.io.Serializable;

public class Etapa implements Serializable {

    private int idEtapa;
    private String descripcion;

    public int getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(int idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

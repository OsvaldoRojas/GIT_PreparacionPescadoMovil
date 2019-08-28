package com.example.simulador_pescado.vista;

import java.io.Serializable;

public class Artefacto implements Serializable {

    private int idMaquinariaArtefacto;
    private String descripcion;
    private Maquinaria maquinaria;

    public int getIdMaquinariaArtefacto() {
        return idMaquinariaArtefacto;
    }

    public void setIdMaquinariaArtefacto(int idMaquinariaArtefacto) {
        this.idMaquinariaArtefacto = idMaquinariaArtefacto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Maquinaria getMaquinaria() {
        return maquinaria;
    }

    public void setMaquinaria(Maquinaria maquinaria) {
        this.maquinaria = maquinaria;
    }
}

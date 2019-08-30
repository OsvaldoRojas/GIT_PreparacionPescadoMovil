package com.example.simulador_pescado.vista;

import java.io.Serializable;

public class Artefacto implements Serializable {

    private int idMaquinariaArtefacto;
    private String descripcion;
    private Boolean selecionado = false;

    public int getIdMaquinariaArtefacto() {
        return idMaquinariaArtefacto;
    }

    public void setIdMaquinariaArtefacto(int idMaquinariaArtefacto) {
        this.idMaquinariaArtefacto = idMaquinariaArtefacto;
    }

    public Boolean getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

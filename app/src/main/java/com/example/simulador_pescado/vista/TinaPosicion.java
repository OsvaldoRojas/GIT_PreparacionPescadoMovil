package com.example.simulador_pescado.vista;

import java.io.Serializable;

public class TinaPosicion implements Serializable {

    private long idTina;
    private String descripcion;

    public long getIdTina() {
        return idTina;
    }

    public void setIdTina(long idTina) {
        this.idTina = idTina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

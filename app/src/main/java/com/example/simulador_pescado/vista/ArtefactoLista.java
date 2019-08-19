package com.example.simulador_pescado.vista;

import java.io.Serializable;

public class ArtefactoLista implements Serializable {

    private Artefacto artefacto;
    private int cantidad;
    private String codigo;

    public ArtefactoLista(){}

    public ArtefactoLista(Artefacto artefacto, int cantidad, String codigo){
        this.artefacto = artefacto;
        this.cantidad = cantidad;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Artefacto getArtefacto() {
        return artefacto;
    }

    public void setArtefacto(Artefacto artefacto) {
        this.artefacto = artefacto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

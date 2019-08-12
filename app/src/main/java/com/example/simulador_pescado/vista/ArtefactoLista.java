package com.example.simulador_pescado.vista;

public class ArtefactoLista {

    private Artefacto artefacto;
    private int cantidad;

    public ArtefactoLista(){}

    public ArtefactoLista(Artefacto artefacto, int cantidad){
        this.artefacto = artefacto;
        this.cantidad = cantidad;
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

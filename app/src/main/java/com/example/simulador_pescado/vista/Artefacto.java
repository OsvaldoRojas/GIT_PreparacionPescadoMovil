package com.example.simulador_pescado.vista;

public class Artefacto {

    private int idArtefacto;
    private String descripcion;

    public Artefacto(){}

    public Artefacto(int idArtefacto, String descripcion){
        this.idArtefacto = idArtefacto;
        this.descripcion = descripcion;
    }

    public int getIdArtefacto() {
        return idArtefacto;
    }

    public void setIdArtefacto(int idArtefacto) {
        this.idArtefacto = idArtefacto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

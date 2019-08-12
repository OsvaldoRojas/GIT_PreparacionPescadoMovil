package com.example.simulador_pescado.vista;

public class GrupoEspecie {

    private int idGrupoEspecie;
    private String descripcion;
    private String observaciones;
    private Boolean activo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdGrupoEspecie() {
        return idGrupoEspecie;
    }

    public void setIdGrupoEspecie(int idGrupoEspecie) {
        this.idGrupoEspecie = idGrupoEspecie;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

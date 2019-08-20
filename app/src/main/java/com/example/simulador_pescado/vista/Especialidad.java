package com.example.simulador_pescado.vista;

import java.io.Serializable;

public class Especialidad implements Serializable {

    private int idEspecialidad;
    private String descripcion;

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

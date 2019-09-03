package com.example.simulador_pescado.vista;

import java.io.Serializable;

public class Refaccion implements Serializable {

    private long idRefaccion;
    private String descripcion;
    private String codigo;

    public long getIdRefaccion() {
        return idRefaccion;
    }

    public void setIdRefaccion(long idRefaccion) {
        this.idRefaccion = idRefaccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}

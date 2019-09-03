package com.example.simulador_pescado.vista;

import java.io.Serializable;

public class RefaccionLista implements Serializable {

    private Refaccion refaccion;
    private int cantidad;
    private String codigo;

    public RefaccionLista(Refaccion refaccion, int cantidad, String codigo){
        this.refaccion = refaccion;
        this.cantidad = cantidad;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Refaccion getRefaccion() {
        return refaccion;
    }

    public void setRefaccion(Refaccion refaccion) {
        this.refaccion = refaccion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

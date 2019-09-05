package com.example.simulador_pescado.vista;

import java.io.Serializable;

public class RefaccionOrden implements Serializable {

    private long idRefaccion;
    private int cantidad;
    private String codigoRefaccion;
    private String descripcionRefaccion;

    public RefaccionOrden(long idRefaccion, int cantidad, String codigoRefaccion, String descripcionRefaccion){
        this.idRefaccion = idRefaccion;
        this.cantidad = cantidad;
        this.codigoRefaccion = codigoRefaccion;
        this.descripcionRefaccion = descripcionRefaccion;
    }

    public long getIdRefaccion() {
        return idRefaccion;
    }

    public void setIdRefaccion(long idRefaccion) {
        this.idRefaccion = idRefaccion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigoRefaccion() {
        return codigoRefaccion;
    }

    public void setCodigoRefaccion(String codigoRefaccion) {
        this.codigoRefaccion = codigoRefaccion;
    }

    public String getDescripcionRefaccion() {
        return descripcionRefaccion;
    }

    public void setDescripcionRefaccion(String descripcionRefaccion) {
        this.descripcionRefaccion = descripcionRefaccion;
    }
}

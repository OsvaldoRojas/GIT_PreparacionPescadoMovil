package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RefaccionOrden implements Serializable {

    @SerializedName("idRefaccion")
    @Expose
    private long idRefaccion;

    @SerializedName("cantidad")
    @Expose
    private int cantidad;

    @SerializedName("codigoRefaccion")
    @Expose
    private String codigoRefaccion;

    @SerializedName("descripcionRefaccion")
    @Expose
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

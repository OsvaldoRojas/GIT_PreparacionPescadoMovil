package com.example.simulador_pescado.vista.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefaccionGuardar {

    @SerializedName("idOrdenMantenimiento")
    @Expose
    private long idOrden;

    @SerializedName("idRefaccion")
    @Expose
    private long idRefaccion;

    @SerializedName("cantidad")
    @Expose
    private int cantidad;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    public long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(long idOrden) {
        this.idOrden = idOrden;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

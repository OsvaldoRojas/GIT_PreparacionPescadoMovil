package com.example.simulador.vista.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrdenMantenimientoCerrarTiempo {

    @SerializedName("idOrdenMantenimiento")
    @Expose
    private long idOrden;

    @SerializedName("fechaFin")
    @Expose
    private String fecha;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    public long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(long idOrden) {
        this.idOrden = idOrden;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

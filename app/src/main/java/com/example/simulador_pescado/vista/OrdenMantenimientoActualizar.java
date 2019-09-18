package com.example.simulador_pescado.vista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrdenMantenimientoActualizar {

    @SerializedName("idOrdenMantenimiento")
    @Expose
    private long idOrdenMantenimiento;

    @SerializedName("idEmpleado")
    @Expose
    private long idEmpleado;

    @SerializedName("nombreEmpleado")
    @Expose
    private String nombreEmpleado;

    @SerializedName("aPaternoEmpleado")
    @Expose
    private String aPaternoEmpleado;

    @SerializedName("aMaternoEmpleado")
    @Expose
    private String aMaternoEmpleado;

    @SerializedName("fechaInicio")
    @Expose
    private String fechaInicio;

    @SerializedName("solucion")
    @Expose
    private String solucion;

    @SerializedName("finalizada")
    @Expose
    private Boolean finalizada;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    public long getIdOrdenMantenimiento() {
        return idOrdenMantenimiento;
    }

    public void setIdOrdenMantenimiento(long idOrdenMantenimiento) {
        this.idOrdenMantenimiento = idOrdenMantenimiento;
    }

    public long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getaPaternoEmpleado() {
        return aPaternoEmpleado;
    }

    public void setaPaternoEmpleado(String aPaternoEmpleado) {
        this.aPaternoEmpleado = aPaternoEmpleado;
    }

    public String getaMaternoEmpleado() {
        return aMaternoEmpleado;
    }

    public void setaMaternoEmpleado(String aMaternoEmpleado) {
        this.aMaternoEmpleado = aMaternoEmpleado;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public Boolean getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

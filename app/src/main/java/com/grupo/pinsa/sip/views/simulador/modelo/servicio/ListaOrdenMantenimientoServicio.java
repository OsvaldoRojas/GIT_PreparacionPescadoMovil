package com.grupo.pinsa.sip.views.simulador.modelo.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListaOrdenMantenimientoServicio implements Serializable {

    @SerializedName("idOrdenMantenimiento")
    @Expose
    private long idOrdenMantenimiento;

    @SerializedName("folio")
    @Expose
    private String folio;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    @SerializedName("idMaquinaria")
    @Expose
    private long idMaquinaria;

    @SerializedName("maquinaria")
    @Expose
    private String maquinaria;

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

    public long getIdOrdenMantenimiento() {
        return idOrdenMantenimiento;
    }

    public void setIdOrdenMantenimiento(long idOrdenMantenimiento) {
        this.idOrdenMantenimiento = idOrdenMantenimiento;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getIdMaquinaria() {
        return idMaquinaria;
    }

    public void setIdMaquinaria(long idMaquinaria) {
        this.idMaquinaria = idMaquinaria;
    }

    public String getMaquinaria() {
        return maquinaria;
    }

    public void setMaquinaria(String maquinaria) {
        this.maquinaria = maquinaria;
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
}

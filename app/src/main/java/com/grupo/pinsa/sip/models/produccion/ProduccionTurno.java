package com.grupo.pinsa.sip.models.produccion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProduccionTurno {
    @SerializedName("fecha_produccion")
    @Expose
    private String fechaProduccion;
    @SerializedName("tarimas_producto_terminado")
    @Expose
    private Integer tarimasProductoTerminado;
    @SerializedName("tarimas_producto_terminado_totales")
    @Expose
    private Integer tarimasProductoTerminadoTotales;
    @SerializedName("tarimas_produccion")
    @Expose
    private Integer tarimasProduccion;
    @SerializedName("tarimas_reprocesadas")
    @Expose
    private Integer tarimasReprocesadas;
    @SerializedName("tarimas_reproceso")
    @Expose
    private Integer tarimasReproceso;
    @SerializedName("tarimas_reproceso_totales")
    @Expose
    private Integer tarimasReprocesoTotales;
    @SerializedName("harina_producto_terminado")
    @Expose
    private Double harinaProductoTerminado;
    @SerializedName("harina_producto_terminado_total")
    @Expose
    private Double harinaProductoTerminadoTotal;
    @SerializedName("harina_produccion")
    @Expose
    private Double harinaProduccion;
    @SerializedName("harina_reprocesada")
    @Expose
    private Double harinaReprocesada;
    @SerializedName("harina_reproceso")
    @Expose
    private Double harinaReproceso;
    @SerializedName("harina_reproceso_total")
    @Expose
    private Double harinaReprocesoTotal;
    @SerializedName("aceite")
    @Expose
    private Double aceite;
    @SerializedName("soluble")
    @Expose
    private Double soluble;

    public ProduccionTurno() {
    }

    public ProduccionTurno(String fechaProduccion, Integer tarimasProductoTerminado, Integer tarimasProductoTerminadoTotales, Integer tarimasProduccion, Integer tarimasReprocesadas, Integer tarimasReproceso, Integer tarimasReprocesoTotales, Double harinaProductoTerminado, Double harinaProductoTerminadoTotal, Double harinaProduccion, Double harinaReprocesada, Double harinaReproceso, Double harinaReprocesoTotal, Double aceite, Double soluble) {
        this.fechaProduccion = fechaProduccion;
        this.tarimasProductoTerminado = tarimasProductoTerminado;
        this.tarimasProductoTerminadoTotales = tarimasProductoTerminadoTotales;
        this.tarimasProduccion = tarimasProduccion;
        this.tarimasReprocesadas = tarimasReprocesadas;
        this.tarimasReproceso = tarimasReproceso;
        this.tarimasReprocesoTotales = tarimasReprocesoTotales;
        this.harinaProductoTerminado = harinaProductoTerminado;
        this.harinaProductoTerminadoTotal = harinaProductoTerminadoTotal;
        this.harinaProduccion = harinaProduccion;
        this.harinaReprocesada = harinaReprocesada;
        this.harinaReproceso = harinaReproceso;
        this.harinaReprocesoTotal = harinaReprocesoTotal;
        this.aceite = aceite;
        this.soluble = soluble;
    }

    public String getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(String fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public Integer getTarimasProductoTerminado() {
        return tarimasProductoTerminado;
    }

    public void setTarimasProductoTerminado(Integer tarimasProductoTerminado) {
        this.tarimasProductoTerminado = tarimasProductoTerminado;
    }

    public Integer getTarimasProductoTerminadoTotales() {
        return tarimasProductoTerminadoTotales;
    }

    public void setTarimasProductoTerminadoTotales(Integer tarimasProductoTerminadoTotales) {
        this.tarimasProductoTerminadoTotales = tarimasProductoTerminadoTotales;
    }

    public Integer getTarimasProduccion() {
        return tarimasProduccion;
    }

    public void setTarimasProduccion(Integer tarimasProduccion) {
        this.tarimasProduccion = tarimasProduccion;
    }

    public Integer getTarimasReprocesadas() {
        return tarimasReprocesadas;
    }

    public void setTarimasReprocesadas(Integer tarimasReprocesadas) {
        this.tarimasReprocesadas = tarimasReprocesadas;
    }

    public Integer getTarimasReproceso() {
        return tarimasReproceso;
    }

    public void setTarimasReproceso(Integer tarimasReproceso) {
        this.tarimasReproceso = tarimasReproceso;
    }

    public Integer getTarimasReprocesoTotales() {
        return tarimasReprocesoTotales;
    }

    public void setTarimasReprocesoTotales(Integer tarimasReprocesoTotales) {
        this.tarimasReprocesoTotales = tarimasReprocesoTotales;
    }

    public Double getHarinaProductoTerminado() {
        return harinaProductoTerminado;
    }

    public void setHarinaProductoTerminado(Double harinaProductoTerminado) {
        this.harinaProductoTerminado = harinaProductoTerminado;
    }

    public Double getHarinaProductoTerminadoTotal() {
        return harinaProductoTerminadoTotal;
    }

    public void setHarinaProductoTerminadoTotal(Double harinaProductoTerminadoTotal) {
        this.harinaProductoTerminadoTotal = harinaProductoTerminadoTotal;
    }

    public Double getHarinaProduccion() {
        return harinaProduccion;
    }

    public void setHarinaProduccion(Double harinaProduccion) {
        this.harinaProduccion = harinaProduccion;
    }

    public Double getHarinaReprocesada() {
        return harinaReprocesada;
    }

    public void setHarinaReprocesada(Double harinaReprocesada) {
        this.harinaReprocesada = harinaReprocesada;
    }

    public Double getHarinaReproceso() {
        return harinaReproceso;
    }

    public void setHarinaReproceso(Double harinaReproceso) {
        this.harinaReproceso = harinaReproceso;
    }

    public Double getHarinaReprocesoTotal() {
        return harinaReprocesoTotal;
    }

    public void setHarinaReprocesoTotal(Double harinaReprocesoTotal) {
        this.harinaReprocesoTotal = harinaReprocesoTotal;
    }

    public Double getAceite() {
        return aceite;
    }

    public void setAceite(Double aceite) {
        this.aceite = aceite;
    }

    public Double getSoluble() {
        return soluble;
    }

    public void setSoluble(Double soluble) {
        this.soluble = soluble;
    }
}

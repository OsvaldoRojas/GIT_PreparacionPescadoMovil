package com.grupo.pinsa.sip.models.produccion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AjustesConsumosTurno {
    @SerializedName("sacos_cuarenta")
    @Expose
    private Integer sacosCuarenta;
    @SerializedName("sacos_veinticinco")
    @Expose
    private Integer sacosVeinticinco;
    @SerializedName("carretes_hilo")
    @Expose
    private Double carretesHilo;
    @SerializedName("maxi_bolsas")
    @Expose
    private Integer maxiBolsas;
    @SerializedName("sacos_cuarenta_reuso")
    @Expose
    private Integer sacosCuarentaReuso;
    @SerializedName("sacos_veinticinco_reuso")
    @Expose
    private Integer sacosVeinticincoReuso;
    @SerializedName("maxi_bolsas_reuso")
    @Expose
    private Integer maxiBolsasReuso;

    public AjustesConsumosTurno() {
    }

    public AjustesConsumosTurno(Integer sacosCuarenta, Integer sacosVeinticinco, Double carretesHilo, Integer maxiBolsas, Integer sacosCuarentaReuso, Integer sacosVeinticincoReuso, Integer maxiBolsasReuso) {
        this.sacosCuarenta = sacosCuarenta;
        this.sacosVeinticinco = sacosVeinticinco;
        this.carretesHilo = carretesHilo;
        this.maxiBolsas = maxiBolsas;
        this.sacosCuarentaReuso = sacosCuarentaReuso;
        this.sacosVeinticincoReuso = sacosVeinticincoReuso;
        this.maxiBolsasReuso = maxiBolsasReuso;
    }

    public Integer getSacosCuarenta() {
        return sacosCuarenta;
    }

    public void setSacosCuarenta(Integer sacosCuarenta) {
        this.sacosCuarenta = sacosCuarenta;
    }

    public Integer getSacosVeinticinco() {
        return sacosVeinticinco;
    }

    public void setSacosVeinticinco(Integer sacosVeinticinco) {
        this.sacosVeinticinco = sacosVeinticinco;
    }

    public Double getCarretesHilo() {
        return carretesHilo;
    }

    public void setCarretesHilo(Double carretesHilo) {
        this.carretesHilo = carretesHilo;
    }

    public Integer getMaxiBolsas() {
        return maxiBolsas;
    }

    public void setMaxiBolsas(Integer maxiBolsas) {
        this.maxiBolsas = maxiBolsas;
    }

    public Integer getSacosCuarentaReuso() {
        return sacosCuarentaReuso;
    }

    public void setSacosCuarentaReuso(Integer sacosCuarentaReuso) {
        this.sacosCuarentaReuso = sacosCuarentaReuso;
    }

    public Integer getSacosVeinticincoReuso() {
        return sacosVeinticincoReuso;
    }

    public void setSacosVeinticincoReuso(Integer sacosVeinticincoReuso) {
        this.sacosVeinticincoReuso = sacosVeinticincoReuso;
    }

    public Integer getMaxiBolsasReuso() {
        return maxiBolsasReuso;
    }

    public void setMaxiBolsasReuso(Integer maxiBolsasReuso) {
        this.maxiBolsasReuso = maxiBolsasReuso;
    }
}

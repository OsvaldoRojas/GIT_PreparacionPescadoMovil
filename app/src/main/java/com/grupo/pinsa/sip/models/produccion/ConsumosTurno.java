package com.grupo.pinsa.sip.models.produccion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 11/11/2019
 */

public class ConsumosTurno {
    @SerializedName("sardina")
    @Expose
    private Double sardina;
    @SerializedName("harina_reprocesada")
    @Expose
    private Double harinaReprocesada;
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
    @SerializedName("salmonicidas")
    @Expose
    private Double salmonicidas;
    @SerializedName("bactericidas")
    @Expose
    private Double bactericidas;
    @SerializedName("ajustes_consumos_produccion")
    @Expose
    private AjustesConsumosTurno ajustesConsumosTurno;

    public ConsumosTurno() {
        ajustesConsumosTurno = new AjustesConsumosTurno();
    }

    public ConsumosTurno(Double sardina, Double harinaReprocesada, Integer sacosCuarenta, Integer sacosVeinticinco, Double carretesHilo, Integer maxiBolsas, Integer sacosCuarentaReuso, Integer sacosVeinticincoReuso, Integer maxiBolsasReuso, Double salmonicidas, Double bactericidas, AjustesConsumosTurno ajustesConsumosTurno) {
        this.sardina = sardina;
        this.harinaReprocesada = harinaReprocesada;
        this.sacosCuarenta = sacosCuarenta;
        this.sacosVeinticinco = sacosVeinticinco;
        this.carretesHilo = carretesHilo;
        this.maxiBolsas = maxiBolsas;
        this.sacosCuarentaReuso = sacosCuarentaReuso;
        this.sacosVeinticincoReuso = sacosVeinticincoReuso;
        this.maxiBolsasReuso = maxiBolsasReuso;
        this.salmonicidas = salmonicidas;
        this.bactericidas = bactericidas;
        this.ajustesConsumosTurno = ajustesConsumosTurno;
    }

    public Double getSardina() {
        return sardina;
    }

    public void setSardina(Double sardina) {
        this.sardina = sardina;
    }

    public Double getHarinaReprocesada(){
        return harinaReprocesada;
    }

    public void setHarinaReprocesada(Double harinaReprocesada){
        this.harinaReprocesada = harinaReprocesada;
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

    public Double getSalmonicidas() {
        return salmonicidas;
    }

    public void setSalmonicidas(Double salmonicidas) {
        this.salmonicidas = salmonicidas;
    }

    public Double getBactericidas() {
        return bactericidas;
    }

    public void setBactericidas(Double bactericidas) {
        this.bactericidas = bactericidas;
    }

    public AjustesConsumosTurno getAjustesConsumosTurno() {
        return ajustesConsumosTurno;
    }

    public void setAjustesConsumosTurno(AjustesConsumosTurno ajustesConsumosTurno) {
        this.ajustesConsumosTurno = ajustesConsumosTurno;
    }
}

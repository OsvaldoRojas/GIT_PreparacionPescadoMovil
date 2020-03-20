package com.grupo.pinsa.sip.models.produccion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 17/11/2019
 */

public class MuestreoCalidad {
    @SerializedName("id_muestreo_tarima")
    @Expose
    private Integer idMuestreoTarima;
    @SerializedName("id_tarima")
    @Expose
    private Integer idTarima;
    @SerializedName("humedad")
    @Expose
    private Double humedad;
    @SerializedName("tvn")
    @Expose
    private Double tvn;
    @SerializedName("molienda")
    @Expose
    private Double molienda;
    @SerializedName("proteina")
    @Expose
    private Double proteina;
    @SerializedName("activo")
    @Expose
    private Boolean activo;

    public MuestreoCalidad() {
    }

    public MuestreoCalidad(Integer idMuestreoTarima, Integer idTarima, Double humedad, Double tvn, Double molienda, Double proteina, Boolean activo) {
        this.idMuestreoTarima = idMuestreoTarima;
        this.idTarima = idTarima;
        this.humedad = humedad;
        this.tvn = tvn;
        this.molienda = molienda;
        this.proteina = proteina;
        this.activo = activo;
    }

    public Integer getIdMuestreoTarima() {
        return idMuestreoTarima;
    }

    public void setIdMuestreoTarima(Integer idMuestreoTarima) {
        this.idMuestreoTarima = idMuestreoTarima;
    }

    public Integer getIdTarima() {
        return idTarima;
    }

    public void setIdTarima(Integer idTarima) {
        this.idTarima = idTarima;
    }

    public Double getHumedad() {
        return humedad;
    }

    public void setHumedad(Double humedad) {
        this.humedad = humedad;
    }

    public Double getTVN() {
        return tvn;
    }

    public void setTVN(Double tvn) {
        this.tvn = tvn;
    }

    public Double getMolienda() {
        return molienda;
    }

    public void setMolienda(Double molienda) {
        this.molienda = molienda;
    }

    public Double getProteina() {
        return proteina;
    }

    public void setProteina(Double proteina) {
        this.proteina = proteina;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

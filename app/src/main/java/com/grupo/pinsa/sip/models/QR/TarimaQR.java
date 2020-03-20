package com.grupo.pinsa.sip.models.QR;

public class TarimaQR {
    private Integer idTarima;
    private Integer numeroTarima;
    private String folio;

    public TarimaQR() {
    }

    public TarimaQR(Integer idTarima, Integer numeroTarima, String folio) {
        this.idTarima = idTarima;
        this.numeroTarima = numeroTarima;
        this.folio = folio;
    }

    public Integer getIdTarima() {
        return idTarima;
    }

    public void setIdTarima(Integer idTarima) {
        this.idTarima = idTarima;
    }

    public Integer getNumeroTarima() {
        return numeroTarima;
    }

    public void setNumeroTarima(Integer numeroTarima) {
        this.numeroTarima = numeroTarima;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
}

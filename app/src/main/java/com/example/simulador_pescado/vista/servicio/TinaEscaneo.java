package com.example.simulador_pescado.vista.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TinaEscaneo {

    @SerializedName("idTinaDes")
    @Expose
    private String idTinaDes;

    @SerializedName("tinaDes")
    @Expose
    private String tinaDes;

    @SerializedName("idEmpresa")
    @Expose
    private String idEmpresa;

    @SerializedName("estatus")
    @Expose
    private String estatus;

    public String getIdTinaDes() {
        return idTinaDes;
    }

    public void setIdTinaDes(String idTinaDes) {
        this.idTinaDes = idTinaDes;
    }

    public String getTinaDes() {
        return tinaDes;
    }

    public void setTinaDes(String tinaDes) {
        this.tinaDes = tinaDes;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}

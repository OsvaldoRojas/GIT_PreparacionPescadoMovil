package com.grupo.pinsa.sip.models.inventarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 11/11/2019
 */

public class UnidadMedida {
    @SerializedName("id_udm")
    @Expose
    private Integer idUDM;
    @SerializedName("id_oracle")
    @Expose
    private String idOracle;
    @SerializedName("abreviatura")
    @Expose
    private String abreviatura;
    @SerializedName("udm")
    @Expose
    private String udm;
    @SerializedName("activo")
    @Expose
    private Boolean activo;

    public UnidadMedida() {
    }

    public UnidadMedida(Integer idUDM, String idOracle, String abreviatura, String udm, Boolean activo) {
        this.idUDM = idUDM;
        this.idOracle = idOracle;
        this.abreviatura = abreviatura;
        this.udm = udm;
        this.activo = activo;
    }

    public Integer getIdUDM() {
        return idUDM;
    }

    public void setIdUDM(Integer idUDM) {
        this.idUDM = idUDM;
    }

    public String getIdOracle() {
        return idOracle;
    }

    public void setIdOracle(String idOracle) {
        this.idOracle = idOracle;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getUdm() {
        return udm;
    }

    public void setUdm(String udm) {
        this.udm = udm;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

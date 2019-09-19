package com.example.simulador_pescado.vista.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmpleadoGafete {

    @SerializedName("cla_trab")
    @Expose
    private int cla_trab;

    @SerializedName("nom_trab")
    @Expose
    private String nom_trab;

    @SerializedName("ap_paterno")
    @Expose
    private String ap_paterno;

    @SerializedName("ap_materno")
    @Expose
    private String ap_materno;

    public int getCla_trab() {
        return cla_trab;
    }

    public void setCla_trab(int cla_trab) {
        this.cla_trab = cla_trab;
    }

    public String getNom_trab() {
        return nom_trab;
    }

    public void setNom_trab(String nom_trab) {
        this.nom_trab = nom_trab;
    }

    public String getAp_paterno() {
        return ap_paterno;
    }

    public void setAp_paterno(String ap_paterno) {
        this.ap_paterno = ap_paterno;
    }

    public String getAp_materno() {
        return ap_materno;
    }

    public void setAp_materno(String ap_materno) {
        this.ap_materno = ap_materno;
    }
}

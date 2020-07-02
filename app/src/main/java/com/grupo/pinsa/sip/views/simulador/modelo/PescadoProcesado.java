package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PescadoProcesado implements Serializable {

    @SerializedName("talla")
    @Expose
    private String talla;

    @SerializedName("toneladas")
    @Expose
    private float toneladas;

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public float getToneladas() {
        return toneladas;
    }

    public void setToneladas(float toneladas) {
        this.toneladas = toneladas;
    }
}

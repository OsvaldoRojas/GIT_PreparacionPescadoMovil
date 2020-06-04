package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CocedorActualSiguiente {

    @SerializedName("cocidaActual")
    @Expose
    private CocidaCocedor actual;

    @SerializedName("cocidaSiguiente")
    @Expose
    private CocidaCocedor siguiente;

    public CocidaCocedor getActual() {
        return actual;
    }

    public void setActual(CocidaCocedor actual) {
        this.actual = actual;
    }

    public CocidaCocedor getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(CocidaCocedor siguiente) {
        this.siguiente = siguiente;
    }
}

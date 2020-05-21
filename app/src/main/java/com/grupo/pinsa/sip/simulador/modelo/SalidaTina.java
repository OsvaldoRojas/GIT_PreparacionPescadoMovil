package com.grupo.pinsa.sip.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalidaTina {

    @SerializedName("idTina")
    @Expose
    private String tina;

    @SerializedName("posicion")
    @Expose
    private String posicion;

    @SerializedName("subtalla")
    @Expose
    private String subtalla;

    @SerializedName("horaSalida")
    @Expose
    private String hora;

    @SerializedName("marcado")
    @Expose
    private Boolean marcado;

    public String getTina() {
        return tina;
    }

    public void setTina(String tina) {
        this.tina = tina;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSubtalla() {
        return subtalla;
    }

    public void setSubtalla(String subtalla) {
        this.subtalla = subtalla;
    }

    public Boolean getMarcado() {
        return marcado;
    }

    public void setMarcado(Boolean marcado) {
        this.marcado = marcado;
    }
}

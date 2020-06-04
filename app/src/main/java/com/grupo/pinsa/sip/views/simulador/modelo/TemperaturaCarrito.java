package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TemperaturaCarrito {

    @SerializedName("idModuloEspacio")
    @Expose
    private long idModuloEspacio;

    @SerializedName("idCocidaCarrito")
    @Expose
    private long idCocidaCarrito;

    @SerializedName("temperatura")
    @Expose
    private float temperatura;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("etapa")
    @Expose
    private int etapa;

    public long getIdModuloEspacio() {
        return idModuloEspacio;
    }

    public void setIdModuloEspacio(long idModuloEspacio) {
        this.idModuloEspacio = idModuloEspacio;
    }

    public long getIdCocidaCarrito() {
        return idCocidaCarrito;
    }

    public void setIdCocidaCarrito(long idCocidaCarrito) {
        this.idCocidaCarrito = idCocidaCarrito;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }
}

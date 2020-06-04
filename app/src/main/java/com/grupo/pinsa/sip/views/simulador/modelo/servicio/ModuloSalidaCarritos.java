package com.grupo.pinsa.sip.views.simulador.modelo.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModuloSalidaCarritos {

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("idModuloEspacio")
    @Expose
    private long[] carritos;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public long[] getCarritos() {
        return carritos;
    }

    public void setCarritos(long[] carritos) {
        this.carritos = carritos;
    }
}

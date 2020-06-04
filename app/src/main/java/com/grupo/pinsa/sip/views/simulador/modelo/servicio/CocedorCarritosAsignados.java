package com.grupo.pinsa.sip.views.simulador.modelo.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CocedorCarritosAsignados {

    @SerializedName("idCocedor")
    @Expose
    private long id;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("emparrilladoId")
    @Expose
    private long[] carritos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

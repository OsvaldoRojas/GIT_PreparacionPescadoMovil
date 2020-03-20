package com.grupo.pinsa.sip.simulador.vista.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MezclaTinaServicio {

    @SerializedName("idPreseleccionPosicionTina")
    @Expose
    private int idTinaPrincipal;

    @SerializedName("posicionesTinasMezclar")
    @Expose
    private List<Integer> posicionesTina;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    public int getIdTinaPrincipal() {
        return idTinaPrincipal;
    }

    public void setIdTinaPrincipal(int idTinaPrincipal) {
        this.idTinaPrincipal = idTinaPrincipal;
    }

    public List<Integer> getPosicionesTina() {
        return posicionesTina;
    }

    public void setPosicionesTina(List<Integer> posicionesTina) {
        this.posicionesTina = posicionesTina;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

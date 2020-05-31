package com.grupo.pinsa.sip.simulador.modelo.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.grupo.pinsa.sip.simulador.modelo.Carrito;

import java.util.List;

public class ModuloCarritosAsignados {

    @SerializedName("idModulo")
    @Expose
    private long idModulo;

    @SerializedName("turno")
    @Expose
    private int turno;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("carritos")
    @Expose
    private List<Carrito> carritos;

    public long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(long idModulo) {
        this.idModulo = idModulo;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }
}

package com.example.simulador_pescado.vista;

import com.example.simulador_pescado.Utilerias.Constantes;

import java.io.Serializable;

public class PosicionEstiba implements Serializable {

    private int idAtemperadoPosicionTina;
    private int conteoNivel;
    private Boolean bloqueado;

    private Constantes.ESTADO estado;

    public Constantes.ESTADO getEstado() {
        return estado;
    }

    public void setEstado(Constantes.ESTADO estado) {
        this.estado = estado;
    }

    public int getIdAtemperadoPosicionTina() {
        return idAtemperadoPosicionTina;
    }

    public void setIdAtemperadoPosicionTina(int idAtemperadoPosicionTina) {
        this.idAtemperadoPosicionTina = idAtemperadoPosicionTina;
    }

    public int getConteoNivel() {
        return conteoNivel;
    }

    public void setConteoNivel(int conteoNivel) {
        this.conteoNivel = conteoNivel;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
}

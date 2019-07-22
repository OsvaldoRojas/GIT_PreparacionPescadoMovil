package com.example.simulador_pescado.vista;

import android.widget.ImageView;

public class Recurso {

    private ImageView icono;
    private ESTADO estado;
    private String posicion;

    public enum ESTADO{
        inicial,
        seleccionado,
        asignado
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public ImageView getIcono() {
        return icono;
    }

    public void setIcono(ImageView icono) {
        this.icono = icono;
    }

    public ESTADO getEstado() {
        return estado;
    }

    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }
}

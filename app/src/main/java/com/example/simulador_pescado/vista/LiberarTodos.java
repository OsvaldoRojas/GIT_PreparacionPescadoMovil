package com.example.simulador_pescado.vista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiberarTodos {

    @SerializedName("usuario")
    @Expose
    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

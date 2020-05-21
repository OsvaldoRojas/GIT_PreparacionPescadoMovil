package com.grupo.pinsa.sip.simulador.modelo.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaServicio {

    @SerializedName("code")
    @Expose
    private int codigo;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("message")
    @Expose
    private String mensaje;

    @SerializedName("exception")
    @Expose
    private String error;

    @SerializedName("fila")
    @Expose
    private String fila;

    @SerializedName("columna")
    @Expose
    private String columna;

    @SerializedName("nivel")
    @Expose
    private String nivel;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}

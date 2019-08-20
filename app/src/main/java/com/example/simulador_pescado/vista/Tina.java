package com.example.simulador_pescado.vista;

import com.example.simulador_pescado.Utilerias.Constantes;

import java.io.Serializable;

public class Tina implements Serializable {

    private int idPosicion;
    private TinaPosicion tina;
    private Subtalla subtalla;
    private Talla talla;
    private GrupoEspecie especie;
    private Especialidad especialidad;
    private int npiezas;
    private int peso;
    private Boolean libre;
    private Boolean activo;
    private String etiquetaMovil;
    private String fechaCreador;
    private String usuarioCreador;
    private String fechaModifico;
    private String usuarioModifico;
    private Boolean borrado;

    private Constantes.ESTADO estado;

    public Constantes.ESTADO getEstado() {
        return estado;
    }

    public Talla getTalla() {
        return talla;
    }

    public void setTalla(Talla talla) {
        this.talla = talla;
    }

    public GrupoEspecie getEspecie() {
        return especie;
    }

    public void setEspecie(GrupoEspecie especie) {
        this.especie = especie;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public void setEstado(Constantes.ESTADO estado) {
        this.estado = estado;
    }

    public String getEtiquetaMovil() {
        return etiquetaMovil;
    }

    public void setEtiquetaMovil(String etiquetaMovil) {
        this.etiquetaMovil = etiquetaMovil;
    }

    public TinaPosicion getTina() {
        return tina;
    }

    public void setTina(TinaPosicion tina) {
        this.tina = tina;
    }

    public Subtalla getSubtalla() {
        return subtalla;
    }

    public void setSubtalla(Subtalla subtalla) {
        this.subtalla = subtalla;
    }

    public int getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(int idPosicion) {
        this.idPosicion = idPosicion;
    }

    public int getNpiezas() {
        return npiezas;
    }

    public void setNpiezas(int npiezas) {
        this.npiezas = npiezas;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Boolean getLibre() {
        return libre;
    }

    public void setLibre(Boolean libre) {
        this.libre = libre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getFechaCreador() {
        return fechaCreador;
    }

    public void setFechaCreador(String fechaCreador) {
        this.fechaCreador = fechaCreador;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public String getFechaModifico() {
        return fechaModifico;
    }

    public void setFechaModifico(String fechaModifico) {
        this.fechaModifico = fechaModifico;
    }

    public String getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(String usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }
}

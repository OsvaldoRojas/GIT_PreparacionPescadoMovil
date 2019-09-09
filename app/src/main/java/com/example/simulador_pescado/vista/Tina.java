package com.example.simulador_pescado.vista;

import com.example.simulador_pescado.Utilerias.Constantes;

import java.io.Serializable;

public class Tina implements Serializable {

    private int idPreseleccionPosicionTina;
    private String posicion;
    private TinaPosicion tina;
    private GrupoEspecie grupoEspecie;
    private Talla talla;
    private Subtalla subtalla;
    private Especialidad especialidad;
    private int npiezas;
    private int peso;
    private Boolean libre;
    private Boolean activo;
    private Boolean turno;

    private String claveMaquina;
    private Constantes.ESTADO estado;

    public String getClaveMaquina() {
        return claveMaquina;
    }

    public void setClaveMaquina(String claveMaquina) {
        this.claveMaquina = claveMaquina;
    }

    public int getIdPreseleccionPosicionTina() {
        return idPreseleccionPosicionTina;
    }

    public void setIdPreseleccionPosicionTina(int idPreseleccionPosicionTina) {
        this.idPreseleccionPosicionTina = idPreseleccionPosicionTina;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public TinaPosicion getTina() {
        return tina;
    }

    public void setTina(TinaPosicion tina) {
        this.tina = tina;
    }

    public GrupoEspecie getGrupoEspecie() {
        return grupoEspecie;
    }

    public void setGrupoEspecie(GrupoEspecie grupoEspecie) {
        this.grupoEspecie = grupoEspecie;
    }

    public Talla getTalla() {
        return talla;
    }

    public void setTalla(Talla talla) {
        this.talla = talla;
    }

    public Subtalla getSubtalla() {
        return subtalla;
    }

    public void setSubtalla(Subtalla subtalla) {
        this.subtalla = subtalla;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
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

    public Constantes.ESTADO getEstado() {
        return estado;
    }

    public void setEstado(Constantes.ESTADO estado) {
        this.estado = estado;
    }

    public Boolean getTurno() {
        return turno;
    }

    public void setTurno(Boolean turno) {
        this.turno = turno;
    }
}

package com.grupo.pinsa.sip.views.simulador.modelo;

import com.grupo.pinsa.sip.views.simulador.utilerias.Constantes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tina implements Serializable {

    @SerializedName("idPreseleccionPosicionTina")
    @Expose
    private int idPreseleccionPosicionTina;

    @SerializedName("idAsignacionCocida")
    @Expose
    private long idAsignacionCocida;

    @SerializedName("posicion")
    @Expose
    private String posicion;

    @SerializedName("tina")
    @Expose
    private TinaPosicion tina;

    @SerializedName("especie")
    @Expose
    private GrupoEspecie grupoEspecie;

    @SerializedName("talla")
    @Expose
    private Talla talla;

    @SerializedName("subtalla")
    @Expose
    private Subtalla subtalla;

    @SerializedName("especialidad")
    @Expose
    private Especialidad especialidad;

    @SerializedName("npiezas")
    @Expose
    private int npiezas;

    @SerializedName("peso")
    @Expose
    private float peso;

    @SerializedName("libre")
    @Expose
    private Boolean libre;

    @SerializedName("activo")
    @Expose
    private Boolean activo;

    @SerializedName("turno")
    @Expose
    private Boolean turno;

    @SerializedName("porcentaje")
    @Expose
    private int porcentaje;

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

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
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

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public long getIdAsignacionCocida() {
        return idAsignacionCocida;
    }

    public void setIdAsignacionCocida(long idAsignacionCocida) {
        this.idAsignacionCocida = idAsignacionCocida;
    }
}

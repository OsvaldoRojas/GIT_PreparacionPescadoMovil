package com.grupo.pinsa.sip.views.simulador.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Carrito implements Serializable {

    @SerializedName("idCarrito")
    @Expose
    private long id;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("especie")
    @Expose
    private String especie;

    @SerializedName("especialidad")
    @Expose
    private String especialidad;

    @SerializedName("subtalla")
    @Expose
    private String subtalla;

    @SerializedName("idEmparrillado")
    @Expose
    private long idEmparrillado;

    @SerializedName("idHistoricoTina")
    @Expose
    private long idHistoricoTina;

    @SerializedName("idCocidaCarrito")
    @Expose
    private long idCocidaCarrito;

    @SerializedName("idModuloEspacio")
    @Expose
    private long idModuloEspacio;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    @SerializedName("cocedor")
    @Expose
    private String cocedor;

    @SerializedName("canastillas")
    @Expose
    private int canastillas;

    @SerializedName("peso")
    @Expose
    private float peso;

    @SerializedName("temperatura")
    @Expose
    private float temperatura;

    @SerializedName("turno")
    @Expose
    private int turno;

    @SerializedName("movimientos")
    @Expose
    private List<Movimiento> movimientos;

    @SerializedName("posicion")
    @Expose
    private String posicion;

    @SerializedName("idBascula")
    @Expose
    private long idBascula;

    private boolean seleccionado = false;
    private boolean seleccionadoGeneral = false;
    private boolean seleccionadoSuma = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getSubtalla() {
        return subtalla;
    }

    public void setSubtalla(String subtalla) {
        this.subtalla = subtalla;
    }

    public long getIdEmparrillado() {
        return idEmparrillado;
    }

    public void setIdEmparrillado(long idEmparrillado) {
        this.idEmparrillado = idEmparrillado;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public boolean isSeleccionadoGeneral() {
        return seleccionadoGeneral;
    }

    public void setSeleccionadoGeneral(boolean seleccionadoGeneral) {
        this.seleccionadoGeneral = seleccionadoGeneral;
    }

    public boolean isSeleccionadoSuma() {
        return seleccionadoSuma;
    }

    public void setSeleccionadoSuma(boolean seleccionadoSuma) {
        this.seleccionadoSuma = seleccionadoSuma;
    }

    public long getIdHistoricoTina() {
        return idHistoricoTina;
    }

    public void setIdHistoricoTina(long idHistoricoTina) {
        this.idHistoricoTina = idHistoricoTina;
    }

    public long getIdCocidaCarrito() {
        return idCocidaCarrito;
    }

    public void setIdCocidaCarrito(long idCocidaCarrito) {
        this.idCocidaCarrito = idCocidaCarrito;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public long getIdModuloEspacio() {
        return idModuloEspacio;
    }

    public void setIdModuloEspacio(long idModuloEspacio) {
        this.idModuloEspacio = idModuloEspacio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCocedor() {
        return cocedor;
    }

    public void setCocedor(String cocedor) {
        this.cocedor = cocedor;
    }

    public int getCanastillas() {
        return canastillas;
    }

    public void setCanastillas(int canastillas) {
        this.canastillas = canastillas;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public long getIdBascula() {
        return idBascula;
    }

    public void setIdBascula(long idBascula) {
        this.idBascula = idBascula;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}

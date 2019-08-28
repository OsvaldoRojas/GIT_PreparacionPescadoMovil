package com.example.simulador_pescado.vista;

public class TinaServicio {

    private int idPreseleccionPosicionTina;
    private String posicion;
    private long idTina;
    private int idEspecie;
    private int idTalla;
    private int idSubtalla;
    private int idEspecialidad;
    private int npiezas;
    private int peso;
    private Boolean libre;
    private Boolean turno;

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

    public long getIdTina() {
        return idTina;
    }

    public void setIdTina(long idTina) {
        this.idTina = idTina;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(int idTalla) {
        this.idTalla = idTalla;
    }

    public int getIdSubtalla() {
        return idSubtalla;
    }

    public void setIdSubtalla(int idSubtalla) {
        this.idSubtalla = idSubtalla;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
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

    public Boolean getTurno() {
        return turno;
    }

    public void setTurno(Boolean turno) {
        this.turno = turno;
    }
}

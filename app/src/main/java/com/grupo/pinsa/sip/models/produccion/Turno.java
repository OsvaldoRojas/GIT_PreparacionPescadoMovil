package com.grupo.pinsa.sip.models.produccion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 19/11/2019
 */

public class Turno {
    @SerializedName("id_turno")
    @Expose
    private Integer idTurno;
    @SerializedName("id_produccion_turno")
    @Expose
    private Integer idProduccionTurno;
    @SerializedName("id_empresa")
    @Expose
    private Integer idEmpresa;
    @SerializedName("turno")
    @Expose
    private String turno;
    @SerializedName("hora_inicio")
    @Expose
    private String horaInicio;
    @SerializedName("hora_fin")
    @Expose
    private String horaFin;
    @SerializedName("operativo")
    @Expose
    private Boolean operativo;
    @SerializedName("operando")
    @Expose
    private Boolean operando;
    @SerializedName("fecha_produccion")
    @Expose
    private String fechaProduccion;
    @SerializedName("activo")
    @Expose
    private Boolean activo;

    public Turno() {
    }

    public Turno(Integer idTurno, Integer idProduccionTurno, Integer idEmpresa, String turno, String horaInicio, String horaFin, Boolean operativo, Boolean operando, String fechaProduccion, Boolean activo) {
        this.idTurno = idTurno;
        this.idProduccionTurno = idProduccionTurno;
        this.idEmpresa = idEmpresa;
        this.turno = turno;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.operativo = operativo;
        this.operando = operando;
        this.fechaProduccion = fechaProduccion;
        this.activo = activo;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public Integer getIdProduccionTurno() {
        return idProduccionTurno;
    }

    public void setIdProduccionTurno(Integer idProduccionTurno) {
        this.idProduccionTurno = idProduccionTurno;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Boolean getOperativo() {
        return operativo;
    }

    public void setOperativo(Boolean operativo) {
        this.operativo = operativo;
    }

    public Boolean getOperando() {
        return operando;
    }

    public void setOperando(Boolean operando) {
        this.operando = operando;
    }

    public String getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(String fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

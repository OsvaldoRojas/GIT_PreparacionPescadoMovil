package com.grupo.pinsa.sip.models.inventarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 11/11/2019
 */

public class Lote {
    @SerializedName("id_lote")
    @Expose
    private Integer idLote;
    @SerializedName("id_produccion_turno")
    @Expose
    private Integer idProduccionTurno;
    @SerializedName("id_empresa")
    @Expose
    private Integer idEmpresa;
    @SerializedName("id_almacen")
    @Expose
    private Integer idAlmacen;
    @SerializedName("id_articulo")
    @Expose
    private Integer idArticulo;
    @SerializedName("id_articulo_presentacion")
    @Expose
    private Integer idArticuloPresentacion;
    @SerializedName("lote")
    @Expose
    private String lote;
    @SerializedName("abierto")
    @Expose
    private Boolean abierto;
    @SerializedName("operando")
    @Expose
    private Boolean operando;
    @SerializedName("activo")
    @Expose
    private Boolean activo;

    public Lote() {
    }

    public Lote(Integer idLote, Integer idProduccionTurno, Integer idEmpresa, Integer idAlmacen, Integer idArticulo, Integer idArticuloPresentacion, String lote, Boolean abierto, Boolean operando, Boolean activo) {
        this.idLote = idLote;
        this.idProduccionTurno = idProduccionTurno;
        this.idEmpresa = idEmpresa;
        this.idAlmacen = idAlmacen;
        this.idArticulo = idArticulo;
        this.idArticuloPresentacion = idArticuloPresentacion;
        this.lote = lote;
        this.abierto = abierto;
        this.operando = operando;
        this.activo = activo;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
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

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getIdArticuloPresentacion() {
        return idArticuloPresentacion;
    }

    public void setIdArticuloPresentacion(Integer idArticuloPresentacion) {
        this.idArticuloPresentacion = idArticuloPresentacion;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Boolean getAbierto() {
        return abierto;
    }

    public void setAbierto(Boolean abierto) {
        this.abierto = abierto;
    }

    public Boolean getOperando() {
        return operando;
    }

    public void setOperando(Boolean operando) {
        this.operando = operando;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
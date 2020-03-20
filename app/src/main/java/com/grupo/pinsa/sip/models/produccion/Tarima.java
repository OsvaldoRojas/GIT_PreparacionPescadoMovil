package com.grupo.pinsa.sip.models.produccion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.grupo.pinsa.libraries.common.Utilerias;
import com.grupo.pinsa.sip.models.inventarios.Articulo;
import com.grupo.pinsa.sip.models.inventarios.Lote;

/**
 * Created by Juan J. Palomera on 11/11/2019
 * Modify by Juan J. Palomera on 17/11/2019 - Agrega clase Clone para realizar copias de la información sin referencia.
 */

public class Tarima {
    @SerializedName("id_tarima")
    @Expose
    private Integer idTarima;
    @SerializedName("lote")
    @Expose
    private Lote lote;
    @SerializedName("articulo")
    @Expose
    private Articulo articulo;
    @SerializedName("id_empresa")
    @Expose
    private Integer idEmpresa;
    @SerializedName("fecha_produccion")
    @Expose
    private String fechaProduccion;
    @SerializedName("fecha_retrabajo")
    @Expose
    private String fechaRetrabajo;
    @SerializedName("folio")
    @Expose
    private String folio;
    @SerializedName("numero_tarima")
    @Expose
    private Integer numeroTarima;
    @SerializedName("piezas")
    @Expose
    private Double piezas;
    @SerializedName("peso_tara")
    @Expose
    private Double pesoTara;
    @SerializedName("peso_bruto")
    @Expose
    private Double pesoBruto;
    @SerializedName("muestreada")
    @Expose
    private Boolean muestreada;
    @SerializedName("codigo_motivo")
    @Expose
    private CodigoMotivo codigoMotivo;
    @SerializedName("comentario")
    @Expose
    private String comentario;
    @SerializedName("estado_tarima")
    @Expose
    private EstadoTarima estadoTarima;
    @SerializedName("muestreo_tarima")
    @Expose
    private MuestreoCalidad muestreoCalidad;
    @SerializedName("activo")
    @Expose
    private Boolean activo;

    /**
     * Variables de Uso
     */
    private Boolean seleccionada = false;

    private Boolean retenida = false;

    public Tarima() {
        lote = new Lote();
        articulo = new Articulo();
        codigoMotivo = new CodigoMotivo();
        estadoTarima = new EstadoTarima();
        muestreoCalidad = new MuestreoCalidad();
        fechaRetrabajo = Utilerias.getShortDate();
        pesoBruto = 0.0;
        piezas = 0.0;
    }

    public Tarima(Integer idTarima, Lote lote, Articulo articulo, Integer idEmpresa, String fechaProduccion, String fechaRetrabajo, String folio, Integer numeroTarima, Double piezas, Double pesoTara, Double pesoBruto, Boolean muestreada, CodigoMotivo codigoMotivo, String comentario, EstadoTarima estadoTarima, MuestreoCalidad muestreoCalidad, Boolean activo, Boolean seleccionada, Boolean retenida) {
        this.idTarima = idTarima;
        this.lote = lote;
        this.articulo = articulo;
        this.idEmpresa = idEmpresa;
        this.fechaProduccion = fechaProduccion;
        this.fechaRetrabajo = fechaRetrabajo;
        this.folio = folio;
        this.numeroTarima = numeroTarima;
        this.piezas = piezas;
        this.pesoTara = pesoTara;
        this.pesoBruto = pesoBruto;
        this.muestreada = muestreada;
        this.codigoMotivo = codigoMotivo;
        this.comentario = comentario;
        this.estadoTarima = estadoTarima;
        this.muestreoCalidad = muestreoCalidad;
        this.activo = activo;
        this.seleccionada = seleccionada;
        this.retenida = retenida;
    }

    public void setTarima(Tarima tarima) {
        this.setIdTarima(tarima.getIdTarima());
            /* Lote */
            this.getLote().setIdLote(tarima.getLote().getIdLote());
            this.getLote().setLote(tarima.getLote().getLote());
            this.getLote().setIdAlmacen(tarima.getLote().getIdAlmacen());
            this.getLote().setIdArticulo(tarima.getLote().getIdArticulo());
            this.getLote().setIdEmpresa(tarima.getLote().getIdEmpresa());
            this.getLote().setAbierto(tarima.getLote().getAbierto());
            this.getLote().setActivo(tarima.getLote().getActivo());
            /* Articulo */
            this.getArticulo().setIdArticulo(tarima.getArticulo().getIdArticulo());
            this.getArticulo().setClave(tarima.getArticulo().getClave());
            this.getArticulo().setArticulo(tarima.getArticulo().getArticulo());
            this.getArticulo().setDescripcionCorta(tarima.getArticulo().getDescripcionCorta());
            this.getArticulo().setActivo(tarima.getArticulo().getActivo());
                /* Clasificación del Artículo */
                this.getArticulo().getClasificacionArticulo().setIdClasificacionArticulo(tarima.getArticulo().getClasificacionArticulo().getIdClasificacionArticulo());
                this.getArticulo().getClasificacionArticulo().setClasificacion_articulo(tarima.getArticulo().getClasificacionArticulo().getClasificacion_articulo());
                this.getArticulo().getClasificacionArticulo().setActivo(tarima.getArticulo().getClasificacionArticulo().getActivo());
                    /* Tipo Artículo */
                    this.getArticulo().getClasificacionArticulo().getTipoArticulo().setIdTipoArticulo(tarima.getArticulo().getClasificacionArticulo().getTipoArticulo().getIdTipoArticulo());
                    this.getArticulo().getClasificacionArticulo().getTipoArticulo().setClave(tarima.getArticulo().getClasificacionArticulo().getTipoArticulo().getClave());
                    this.getArticulo().getClasificacionArticulo().getTipoArticulo().setTipoArticulo(tarima.getArticulo().getClasificacionArticulo().getTipoArticulo().getTipoArticulo());
                    this.getArticulo().getClasificacionArticulo().getTipoArticulo().setActivo(tarima.getArticulo().getClasificacionArticulo().getTipoArticulo().getActivo());
                /* Unidad de Medida */
                this.getArticulo().getUnidadMedida().setIdUDM(tarima.getArticulo().getUnidadMedida().getIdUDM());
                this.getArticulo().getUnidadMedida().setIdOracle(tarima.getArticulo().getUnidadMedida().getIdOracle());
                this.getArticulo().getUnidadMedida().setAbreviatura(tarima.getArticulo().getUnidadMedida().getAbreviatura());
                this.getArticulo().getUnidadMedida().setUdm(tarima.getArticulo().getUnidadMedida().getUdm());
                this.getArticulo().getUnidadMedida().setActivo(tarima.getArticulo().getUnidadMedida().getActivo());
        this.setIdEmpresa(tarima.getIdEmpresa());
        this.setFechaProduccion(tarima.getFechaProduccion());
        this.setFechaRetrabajo(tarima.getFechaRetrabajo());
        this.setFolio(tarima.getFolio());
        this.setNumeroTarima(tarima.getNumeroTarima());
        this.setPiezas(tarima.getPiezas());
        this.setPesoTara(tarima.getPesoTara());
        this.setPesoBruto(tarima.getPesoBruto());
        this.setMuestreada(tarima.getMuestreada());
            /* Código Motivo */
            this.getCodigoMotivo().setIdCodigoMotivo(tarima.getCodigoMotivo().getIdCodigoMotivo());
            this.getCodigoMotivo().setIdEmpresa(tarima.getCodigoMotivo().getIdEmpresa());
            this.getCodigoMotivo().setCodigoMotivo(tarima.getCodigoMotivo().getCodigoMotivo());
            this.getCodigoMotivo().setClave(tarima.getCodigoMotivo().getClave());
            this.getCodigoMotivo().setAptoProductoTerminado(tarima.getCodigoMotivo().getAptoProductoTerminado());
            this.getCodigoMotivo().setActivo(tarima.getCodigoMotivo().getActivo());
    }

    public Integer getIdTarima() {
        return idTarima;
    }

    public void setIdTarima(Integer idTarima) {
        this.idTarima = idTarima;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(String fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public String getFechaRetrabajo() {
        return fechaRetrabajo;
    }

    public void setFechaRetrabajo(String fechaRetrabajo) {
        this.fechaRetrabajo = fechaRetrabajo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Integer getNumeroTarima() {
        return numeroTarima;
    }

    public void setNumeroTarima(Integer numeroTarima) {
        this.numeroTarima = numeroTarima;
    }

    public Double getPiezas() {
        return piezas;
    }

    public void setPiezas(Double piezas) {
        this.piezas = piezas;
    }

    public Double getPesoTara() {
        return pesoTara;
    }

    public void setPesoTara(Double pesoTara) {
        this.pesoTara = pesoTara;
    }

    public Double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(Double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public Boolean getMuestreada() {
        return muestreada;
    }

    public void setMuestreada(Boolean muestreada) {
        this.muestreada = muestreada;
    }

    public CodigoMotivo getCodigoMotivo() {
        return codigoMotivo;
    }

    public void setCodigoMotivo(CodigoMotivo codigoMotivo) {
        this.codigoMotivo = codigoMotivo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public EstadoTarima getEstadoTarima() {
        return estadoTarima;
    }

    public void setEstadoTarima(EstadoTarima estadoTarima) {
        this.estadoTarima = estadoTarima;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(Boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    public Boolean getRetenida() {
        return retenida;
    }

    public void setRetenida(Boolean retenida) {
        this.retenida = retenida;
    }

    public MuestreoCalidad getMuestreoCalidad() {
        return muestreoCalidad;
    }

    public void setMuestreoCalidad(MuestreoCalidad muestreoCalidad) {
        this.muestreoCalidad = muestreoCalidad;
    }
}
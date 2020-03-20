package com.grupo.pinsa.sip.models.seguridad;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

@SuppressWarnings("ALL")
public class Funcionalidad {
    @SerializedName("id_modulo_funcionalidad")
    @Expose
    private Integer idModuloFuncionalidad;
    @SerializedName("id_modulo")
    @Expose
    private Integer idModulo;
    @SerializedName("id_tipo_modulo")
    @Expose
    private Integer idTipoModulo;
    @SerializedName("id_funcionalidad")
    @Expose
    private Integer idFuncionalidad;
    @SerializedName("id_padre")
    @Expose
    private Integer idPadre;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("es_grupo")
    @Expose
    private Boolean esGrupo;
    @SerializedName("orden")
    @Expose
    private Integer orden;
    @SerializedName("protocolo")
    @Expose
    private String protocolo;
    @SerializedName("servidor")
    @Expose
    private String servidor;
    @SerializedName("puerto")
    @Expose
    private Integer puerto;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("es_autorizar")
    @Expose
    private Boolean esAutorizar;
    @SerializedName("es_borrar")
    @Expose
    private Boolean esBorrar;
    @SerializedName("es_consultar")
    @Expose
    private Boolean esConsultar;
    @SerializedName("es_guardar")
    @Expose
    private Boolean esGuardar;
    @SerializedName("es_revisar")
    @Expose
    private Boolean esRevisar;

    public Funcionalidad() {
        idModuloFuncionalidad = 0;
        idModulo = 0;
        idTipoModulo = 0;
        idFuncionalidad = 0;
        idPadre = 0;
        nombre = "";
        esGrupo = false;
        orden = 0;
        protocolo = "";
        servidor = "";
        puerto = 0;
        uri = "";
        esAutorizar = false;
        esBorrar = false;
        esConsultar = false;
        esGuardar = false;
        esRevisar = false;
    }

    /**
     * Getter & Setter
     */
    public Integer getIdModuloFuncionalidad() {
        return idModuloFuncionalidad;
    }

    public void setIdModuloFuncionalidad(Integer idModuloFuncionalidad) {
        this.idModuloFuncionalidad = idModuloFuncionalidad;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Integer getIdTipoModulo() {
        return idTipoModulo;
    }

    public void setIdTipoModulo(Integer idTipoModulo) {
        this.idTipoModulo = idTipoModulo;
    }

    public Integer getIdFuncionalidad() {
        return idFuncionalidad;
    }

    public void setIdFuncionalidad(Integer idFuncionalidad) {
        this.idFuncionalidad = idFuncionalidad;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEsGrupo() {
        return esGrupo;
    }

    public void setEsGrupo(Boolean esGrupo) {
        this.esGrupo = esGrupo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean getEsAutorizar() {
        return esAutorizar;
    }

    public void setEsAutorizar(Boolean esAutorizar) {
        this.esAutorizar = esAutorizar;
    }

    public Boolean getEsBorrar() {
        return esBorrar;
    }

    public void setEsBorrar(Boolean esBorrar) {
        this.esBorrar = esBorrar;
    }

    public Boolean getEsConsultar() {
        return esConsultar;
    }

    public void setEsConsultar(Boolean esConsultar) {
        this.esConsultar = esConsultar;
    }

    public Boolean getEsGuardar() {
        return esGuardar;
    }

    public void setEsGuardar(Boolean esGuardar) {
        this.esGuardar = esGuardar;
    }

    public Boolean getEsRevisar() {
        return esRevisar;
    }

    public void setEsRevisar(Boolean esRevisar) {
        this.esRevisar = esRevisar;
    }

}

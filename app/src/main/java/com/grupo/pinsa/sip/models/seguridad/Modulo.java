package com.grupo.pinsa.sip.models.seguridad;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

@SuppressWarnings("ALL")
public class Modulo {
    @SerializedName("id_modulo")
    @Expose
    private Integer idModulo;
    @SerializedName("id_tipo_modulo")
    @Expose
    private Integer idTipoModulo;
    @SerializedName("base_datos")
    @Expose
    private String baseDatos;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("servidor")
    @Expose
    private String servidor;
    @SerializedName("protocolo")
    @Expose
    private String protocolo;
    @SerializedName("puerto")
    @Expose
    private Integer puerto;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("url_imagen")
    @Expose
    private String urlImagen;
    @SerializedName("activo")
    @Expose
    private Boolean activo;
    @SerializedName("funcionalidades")
    @Expose
    private ArrayList<Funcionalidad> funcionalidades = null;

    public Modulo() {
        idModulo = 0;
        idTipoModulo = 0;
        baseDatos = "";
        nombre = "";
        descripcion = "";
        servidor = "";
        protocolo = "";
        puerto = 0;
        uri = "";
        urlImagen = "";
        activo = false;
        funcionalidades = new ArrayList<>();
    }

    /**
     * Getter & Setter
     */
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

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
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

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public ArrayList<Funcionalidad> getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(ArrayList<Funcionalidad> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }
}

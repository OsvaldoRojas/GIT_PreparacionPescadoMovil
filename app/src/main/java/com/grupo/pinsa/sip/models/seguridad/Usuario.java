package com.grupo.pinsa.sip.models.seguridad;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.grupo.pinsa.sip.models.grupopinsa.Empresa;

import java.util.ArrayList;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

@SuppressWarnings("ALL")
public class Usuario {
    @SerializedName("id_usuario")
    @Expose
    private Integer idUsuario;
    @SerializedName("clave_usuario")
    @Expose
    private String claveUsuario;
    private String contrasena;
    @SerializedName("id_empleado")
    @Expose
    private Integer idEmpleado;
    @SerializedName("id_rol")
    @Expose
    private Integer idRol;
    @SerializedName("rol")
    @Expose
    private String rol;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("apellido_paterno")
    @Expose
    private String apellidoPaterno;
    @SerializedName("apellido_materno")
    @Expose
    private String apellidoMaterno;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("activo")
    @Expose
    private Boolean activo;
    @SerializedName("empresas")
    @Expose
    private ArrayList<Empresa> empresas = null;
    @SerializedName("modulos")
    @Expose
    private ArrayList<Modulo> modulos = null;

    /** Oracle Cloud **/
    @SerializedName("id_usuario_integracion")
    @Expose
    private Integer idUsuarioIntegracion;
    @SerializedName("id_usuario_cloud")
    @Expose
    private String idUsuarioCloud;
    @SerializedName("id_usuario_ebs")
    @Expose
    private Integer idUsuarioEbs;
    @SerializedName("clave_usuario_cloud")
    @Expose
    private String claveUsuarioCloud;
    @SerializedName("clave_usuario_ebs")
    @Expose
    private String claveUsuarioEbs;
    @SerializedName("nombre_usuario_cloud")
    @Expose
    private String nombreUsuarioCloud;
    @SerializedName("nombre_usuario_ebs")
    @Expose
    private String nombreUsuarioEbs;
    /** **/

    public Usuario() {
        idUsuario = 0;
        claveUsuario = "";
        contrasena = "";
        idEmpleado = 0;
        idRol = 0;
        rol = "";
        nombre = "";
        apellidoPaterno = "";
        apellidoMaterno = "";
        email = "";
        activo = false;
        empresas = new ArrayList<>();
        modulos = new ArrayList<>();
        idUsuarioIntegracion = 0;
        idUsuarioCloud = "";
        idUsuarioEbs = 0;
        claveUsuarioCloud = "";
        claveUsuarioEbs = "";
        nombreUsuarioCloud = "";
        nombreUsuarioEbs = "";
    }

    /**
     * Getter & Setter
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(ArrayList<Empresa> empresas) {
        this.empresas = empresas;
    }

    public ArrayList<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(ArrayList<Modulo> modulos) {
        this.modulos = modulos;
    }

    /** Oracle Cloud **/
    public Integer getIdUsuarioIntegracion() {
        return idUsuarioIntegracion;
    }

    public void setIdUsuarioIntegracion(Integer idUsuarioIntegracion) {
        this.idUsuarioIntegracion = idUsuarioIntegracion;
    }

    public String getIdUsuarioCloud() {
        return idUsuarioCloud;
    }

    public void setIdUsuarioCloud(String idUsuarioCloud) {
        this.idUsuarioCloud = idUsuarioCloud;
    }

    public Integer getIdUsuarioEbs() {
        return idUsuarioEbs;
    }

    public void setIdUsuarioEbs(Integer idUsuarioEbs) {
        this.idUsuarioEbs = idUsuarioEbs;
    }

    public String getClaveUsuarioCloud() {
        return claveUsuarioCloud;
    }

    public void setClaveUsuarioCloud(String claveUsuarioCloud) {
        this.claveUsuarioCloud = claveUsuarioCloud;
    }

    public String getClaveUsuarioEbs() {
        return claveUsuarioEbs;
    }

    public void setClaveUsuarioEbs(String claveUsuarioEbs) {
        this.claveUsuarioEbs = claveUsuarioEbs;
    }

    public String getNombreUsuarioCloud() {
        return nombreUsuarioCloud;
    }

    public void setNombreUsuarioCloud(String nombreUsuarioCloud) {
        this.nombreUsuarioCloud = nombreUsuarioCloud;
    }

    public String getNombreUsuarioEbs() {
        return nombreUsuarioEbs;
    }

    public void setNombreUsuarioEbs(String nombreUsuarioEbs) {
        this.nombreUsuarioEbs = nombreUsuarioEbs;
    }
    /** **/
}
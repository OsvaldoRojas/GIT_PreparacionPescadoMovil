package com.grupo.pinsa.sip.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.grupo.pinsa.libraries.common.Utilerias;
import com.grupo.pinsa.sip.models.seguridad.Usuario;
import com.grupo.pinsa.sip.models.grupopinsa.Empresa;
import com.grupo.pinsa.sip.models.grupopinsa.Impresora;
import com.grupo.pinsa.sip.models.inventarios.Almacen;
import com.grupo.pinsa.sip.models.seguridad.Modulo;

/**
 * Created by Juan J. Palomera on 27/10/2018
 * Modified by Juan J. Palomera on 25/03/2019 - Agregado objeto Impresora
 */

@SuppressWarnings("ALL")
public class Sesion {
    private Usuario usuario;
    private Empresa empresa;
    private Modulo modulo;
    private Almacen almacen;
    private Impresora impresora;
    @SerializedName("fecha_transaccion")
    @Expose
    private String fechaTransaccion = Utilerias.getLongDate();

    /**
     * Constructores
     */
    public Sesion() {
        this(new Usuario(), new Empresa(), new Modulo(), new Almacen(), new Impresora());
    }

    public Sesion(Usuario usuario, Empresa empresa, Modulo modulo, Impresora impresora) {
        this(usuario, empresa, modulo, new Almacen(), impresora);
    }

    public Sesion(Usuario usuario, Empresa empresa, Modulo modulo, Almacen almacen, Impresora impresora) {
        this.usuario = usuario;
        this.empresa = empresa;
        this.modulo = modulo;
        this.almacen = almacen;
        this.impresora = impresora;
    }

    /**
     * Getter & Setter
     */
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public Impresora getImpresora() {
        return impresora;
    }

    public void setImpresora(Impresora impresora) {
        this.impresora = impresora;
    }
}
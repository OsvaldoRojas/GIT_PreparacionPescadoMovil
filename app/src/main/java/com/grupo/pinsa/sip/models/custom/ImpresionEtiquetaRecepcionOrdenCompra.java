package com.grupo.pinsa.sip.models.custom;

import com.grupo.pinsa.sip.models.oracle.inventarios.RecepcionOrdenCompra;
import com.grupo.pinsa.sip.models.oracle.inventarios.RecepcionOrdenCompraDetalle;
import com.grupo.pinsa.sip.models.oracle.inventarios.RecepcionOrdenCompraDetalleLote;

/**
 * Created by Juan J. Palomera on 25/03/2019
 */

public class ImpresionEtiquetaRecepcionOrdenCompra {
    private RecepcionOrdenCompra recepcionOrdenCompra;
    private RecepcionOrdenCompraDetalle recepcionOrdenCompraDetalle;
    private RecepcionOrdenCompraDetalleLote recepcionOrdenCompraDetalleLote;
    private String codigoArticulo;
    private String parentDescripcion;
    private String childDescripcion;

    public ImpresionEtiquetaRecepcionOrdenCompra() {
        this.codigoArticulo = "";
        this.parentDescripcion = "";
        this.childDescripcion = "";
        this.recepcionOrdenCompra = new RecepcionOrdenCompra();
        this.recepcionOrdenCompraDetalle = new RecepcionOrdenCompraDetalle();
        this.recepcionOrdenCompraDetalleLote = new RecepcionOrdenCompraDetalleLote();
    }

    public ImpresionEtiquetaRecepcionOrdenCompra(RecepcionOrdenCompra recepcionOrdenCompra, RecepcionOrdenCompraDetalle recepcionOrdenCompraDetalle, RecepcionOrdenCompraDetalleLote recepcionOrdenCompraDetalleLote, String codigoArticulo, String parentDescripcion, String childDescripcion) {
        this.recepcionOrdenCompra = recepcionOrdenCompra;
        this.recepcionOrdenCompraDetalle = recepcionOrdenCompraDetalle;
        this.recepcionOrdenCompraDetalleLote = recepcionOrdenCompraDetalleLote;
        this.codigoArticulo = codigoArticulo;
        this.parentDescripcion = parentDescripcion;
        this.childDescripcion = childDescripcion;
    }

    public RecepcionOrdenCompra getRecepcionOrdenCompra() {
        return recepcionOrdenCompra;
    }

    public RecepcionOrdenCompraDetalle getRecepcionOrdenCompraDetalle() {
        return recepcionOrdenCompraDetalle;
    }

    public RecepcionOrdenCompraDetalleLote getRecepcionOrdenCompraDetalleLote() {
        return recepcionOrdenCompraDetalleLote;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public String getParentDescripcion() {
        return parentDescripcion;
    }

    public String getChildDescripcion() {
        return childDescripcion;
    }

    public void setRecepcionOrdenCompra(RecepcionOrdenCompra recepcionOrdenCompra) {
        this.recepcionOrdenCompra = recepcionOrdenCompra;
    }

    public void setRecepcionOrdenCompraDetalle(RecepcionOrdenCompraDetalle recepcionOrdenCompraDetalle) {
        this.recepcionOrdenCompraDetalle = recepcionOrdenCompraDetalle;
    }

    public void setRecepcionOrdenCompraDetalleLote(RecepcionOrdenCompraDetalleLote recepcionOrdenCompraDetalleLote) {
        this.recepcionOrdenCompraDetalleLote = recepcionOrdenCompraDetalleLote;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public void setParentDescripcion(String parentDescripcion) {
        this.parentDescripcion = parentDescripcion;
    }

    public void setChildDescripcion(String childDescripcion) {
        this.childDescripcion = childDescripcion;
    }
}

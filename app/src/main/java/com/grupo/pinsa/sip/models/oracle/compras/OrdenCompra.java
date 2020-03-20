package com.grupo.pinsa.sip.models.oracle.compras;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

public class OrdenCompra {
    @SerializedName("type_lookup_code")
    @Expose
    private String typeLookupCode;
    @SerializedName("po_header_id")
    @Expose
    private String poHeaderId;
    @SerializedName("po_number")
    @Expose
    private String poNumber;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("organization_name")
    @Expose
    private String organizationName;
    @SerializedName("vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("vendor_site_id")
    @Expose
    private String vendorSiteId;
    @SerializedName("vendor_site_code")
    @Expose
    private String vendorSiteCode;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("bill_of_landing")
    @Expose
    private String billOfLading ;
    @SerializedName("orden_compra_detalles")
    @Expose
    private ArrayList<OrdenCompraDetalle> ordenCompraDetalles;

    /**
     * Constructor
     */
    public OrdenCompra() {
        this.typeLookupCode = "";
        this.poHeaderId = "";
        this.poNumber = "";
        this.comments = "";
        this.organizationName = "";
        this.vendorId = "";
        this.vendorName = "";
        this.vendorSiteId = "";
        this.vendorSiteCode = "";
        this.currencyCode = "";
        this.billOfLading = "";
        this.ordenCompraDetalles = new ArrayList<>();
    }

    /**
     * Getter & Setter
     */
    public String getTypeLookupCode() {
        return typeLookupCode;
    }

    public void setTypeLookupCode(String typeLookupCode) {
        this.typeLookupCode = typeLookupCode;
    }

    public String getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(String poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorSiteId() {
        return vendorSiteId;
    }

    public void setVendorSiteId(String vendorSiteId) {
        this.vendorSiteId = vendorSiteId;
    }

    public String getVendorSiteCode() {
        return vendorSiteCode;
    }

    public void setVendorSiteCode(String vendorSiteCode) {
        this.vendorSiteCode = vendorSiteCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getBillOfLading() {
        return billOfLading;
    }

    public void setBillOfLading(String billOfLading) {
        this.billOfLading = billOfLading;
    }

    public ArrayList<OrdenCompraDetalle> getOrdenCompraDetalles() {
        return ordenCompraDetalles;
    }

    public void setOrdenCompraDetalles(ArrayList<OrdenCompraDetalle> ordenCompraDetalles) {
        this.ordenCompraDetalles = ordenCompraDetalles;
    }
}

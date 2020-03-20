package com.grupo.pinsa.sip.models.oracle.compras;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

public class OrdenCompraDetalleLoteParaRecepcion {
    @SerializedName("transaction_interface_id")
    @Expose
    private int transactionInterfaceId;
    @SerializedName("last_update_date")
    @Expose
    private String lastUpdateDate;
    @SerializedName("last_update_by")
    @Expose
    private String lastUpdateBy;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("last_update_login")
    @Expose
    private String lastUpdateLogin;
    @SerializedName("lot_number")
    @Expose
    private String lotNumber;
    @SerializedName("transaction_quantity")
    @Expose
    private Double transaction_quantity;
    @SerializedName("primary_quantity")
    @Expose
    private Double primary_quantity;
    @SerializedName("product_code")
    @Expose
    private String productCode;
    @SerializedName("product_transaction_id")
    @Expose
    private int productTransactionId;
    @SerializedName("lot_expiration_date")
    @Expose
    private String lotExpirationDate;

    public OrdenCompraDetalleLoteParaRecepcion() {
        transactionInterfaceId = 0;
        lastUpdateDate = "";
        lastUpdateBy = "";
        creationDate = "";
        createdBy = "";
        lastUpdateLogin = "";
        lotNumber = "";
        transaction_quantity = 0.0;
        primary_quantity = 0.0;
        productCode = "";
        productTransactionId = 0;
        lotExpirationDate = (new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
    }

    public OrdenCompraDetalleLoteParaRecepcion(String lotNumber, Double transaction_quantity, Double primary_quantity, String productCode, int productTransactionId, String lotExpirationDate) {
        this.lotNumber = lotNumber;
        this.transaction_quantity = transaction_quantity;
        this.primary_quantity = primary_quantity;
        this.productCode = productCode;
        this.productTransactionId = productTransactionId;
        this.lotExpirationDate = lotExpirationDate;
    }

    /**
     * Getter & Setter
     */
    public int getTransactionInterfaceId() {
        return transactionInterfaceId;
    }

    public void setTransactionInterfaceId(int transactionInterfaceId) {
        this.transactionInterfaceId = transactionInterfaceId;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(String lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Double getTransaction_quantity() {
        return transaction_quantity;
    }

    public void setTransaction_quantity(Double transaction_quantity) {
        this.transaction_quantity = transaction_quantity;
    }

    public Double getPrimary_quantity() {
        return primary_quantity;
    }

    public void setPrimary_quantity(Double primary_quantity) {
        this.primary_quantity = primary_quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getProductTransactionId() {
        return productTransactionId;
    }

    public void setProductTransactionId(int productTransactionId) {
        this.productTransactionId = productTransactionId;
    }

    public String getLotExpirationDate() {
        return lotExpirationDate;
    }

    public void setLotExpirationDate(String lotExpirationDate) {
        this.lotExpirationDate = lotExpirationDate;
    }
}

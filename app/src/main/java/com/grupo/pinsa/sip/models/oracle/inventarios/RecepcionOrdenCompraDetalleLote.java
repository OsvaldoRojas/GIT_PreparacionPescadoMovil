package com.grupo.pinsa.sip.models.oracle.inventarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 09/04/2019
 * Modified by Juan J. Palomera on 24/05/2019
 */

public class RecepcionOrdenCompraDetalleLote {
    @SerializedName("transaction_temp_id")
    @Expose
    private Integer transactionTempId;
    @SerializedName("last_update_date")
    @Expose
    private String lastUpdateDate;
    @SerializedName("last_updated_by")
    @Expose
    private String lastUpdatedBy;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("last_update_login")
    @Expose
    private String lastUpdateLogin;
    @SerializedName("primary_quantity")
    @Expose
    private Double primaryQuantity;
    @SerializedName("transaction_quantity")
    @Expose
    private Double transactionQuantity;
    @SerializedName("lot_number")
    @Expose
    private String lotNumber;
    @SerializedName("lot_expiration_date")
    @Expose
    private String lotExpirationDate;
    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("reason_code")
    @Expose
    private String reasonCode;
    @SerializedName("product_code")
    @Expose
    private String productCode;
    @SerializedName("product_transaction_id")
    @Expose
    private Integer productTransactionId;

    private String printDescription = "";

    public RecepcionOrdenCompraDetalleLote() {
        this.transactionTempId = 0;
        this.lastUpdateDate = "";
        this.lastUpdatedBy = "";
        this.creationDate = "";
        this.createdBy = "";
        this.lastUpdateLogin = "";
        this.primaryQuantity = 0.0;
        this.transactionQuantity = 0.0;
        this.lotNumber = "";
        this.lotExpirationDate = "";
        this.errorCode = "";
        this.reasonCode = "";
        this.productCode = "";
        this.productTransactionId = 0;
    }

    public RecepcionOrdenCompraDetalleLote(Integer transactionTempId, String lastUpdateDate, String lastUpdatedBy, String creationDate, String createdBy, String lastUpdateLogin, Double primaryQuantity, Double transactionQuantity, String lotNumber, String lotExpirationDate, String errorCode, String reasonCode, String productCode, Integer productTransactionId, String printDescription) {
        this.transactionTempId = transactionTempId;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdateLogin = lastUpdateLogin;
        this.primaryQuantity = primaryQuantity;
        this.transactionQuantity = transactionQuantity;
        this.lotNumber = lotNumber;
        this.lotExpirationDate = lotExpirationDate;
        this.errorCode = errorCode;
        this.reasonCode = reasonCode;
        this.productCode = productCode;
        this.productTransactionId = productTransactionId;
        this.printDescription = printDescription;
    }

    public Integer getTransactionTempId() {
        return transactionTempId;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public Double getPrimaryQuantity() {
        return primaryQuantity;
    }

    public Double getTransactionQuantity() {
        return transactionQuantity;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public String getLotExpirationDate() {
        return lotExpirationDate;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public Integer getProductTransactionId() {
        return productTransactionId;
    }

    public String getPrintDescription() {
        return printDescription;
    }

    public void setTransactionTempId(Integer transactionTempId) {
        this.transactionTempId = transactionTempId;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastUpdateLogin(String lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public void setPrimaryQuantity(Double primaryQuantity) {
        this.primaryQuantity = primaryQuantity;
    }

    public void setTransactionQuantity(Double transactionQuantity) {
        this.transactionQuantity = transactionQuantity;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public void setLotExpirationDate(String lotExpirationDate) {
        this.lotExpirationDate = lotExpirationDate;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductTransactionId(Integer productTransactionId) {
        this.productTransactionId = productTransactionId;
    }

    public void setPrintDescription(String printDescription) {
        this.printDescription = printDescription;
    }
}

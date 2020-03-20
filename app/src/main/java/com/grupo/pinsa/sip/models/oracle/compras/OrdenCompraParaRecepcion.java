package com.grupo.pinsa.sip.models.oracle.compras;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

public class OrdenCompraParaRecepcion {
    @SerializedName("header_interface_id")
    @Expose
    private int headerInterfaceId;
    @SerializedName("group_id")
    @Expose
    private int groupId;
    @SerializedName("processing_status_code")
    @Expose
    private String processingStatusCode;
    @SerializedName("receipt_source_code")
    @Expose
    private String receiptSourceCode;
    @SerializedName("transaction_type")
    @Expose
    private String transactionType;
    @SerializedName("last_update_date")
    @Expose
    private String lastUpdateDate;
    @SerializedName("last_updated_by")
    @Expose
    private String lastUpdatedBy;
    @SerializedName("last_update_login")
    @Expose
    private String lastUpdateLogin;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("tipo_recepcion")
    @Expose
    private Integer tipoRecepcion = 0;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("vendor_site_code")
    @Expose
    private String vendorSiteCode;
    @SerializedName("expected_receipt_date")
    @Expose
    private String expectedReceiptDate;
    @SerializedName("validation_flag")
    @Expose
    private String validationFlag;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("receipt_num")
    @Expose
    private int receiptNum;
    @SerializedName("bill_of_landing")
    @Expose
    private String billOfLading ;
    @SerializedName("recepcion_orden_compra_detalles")
    @Expose
    public ArrayList<OrdenCompraDetalleParaRecepcion> ordenCompraDetalleParaRecepcionArrayList;

    /**
     * Local Variables
     */
    @SerializedName("transaction_date")
    @Expose
    private String transactionDate;
    @SerializedName("conversion_rate_date")
    @Expose
    private String conversionRateDate;

    public OrdenCompraParaRecepcion() {
        headerInterfaceId = 0;
        groupId = 0;
        processingStatusCode = "";
        receiptSourceCode = "";
        transactionType = "";
        lastUpdateDate = "";
        lastUpdatedBy = "";
        lastUpdateLogin = "";
        creationDate = "";
        createdBy = "";
        vendorId = "";
        expectedReceiptDate = "";
        validationFlag = "";
        comments = "";
        receiptNum = 0;
        billOfLading = "";
        ordenCompraDetalleParaRecepcionArrayList = new ArrayList<>();
        transactionDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        conversionRateDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
    }

    /**
     * Getter & Setter
     */
    public int getHeaderInterfaceId() {
        return headerInterfaceId;
    }

    public void setHeaderInterfaceId(Integer headerInterfaceId) {
        this.headerInterfaceId = headerInterfaceId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getProcessingStatusCode() {
        return processingStatusCode;
    }

    public void setProcessingStatusCode(String processingStatusCode) {
        this.processingStatusCode = processingStatusCode;
    }

    public String getReceiptSourceCode() {
        return receiptSourceCode;
    }

    public void setReceiptSourceCode(String receiptSourceCode) {
        this.receiptSourceCode = receiptSourceCode;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(String lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
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

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getExpectedReceiptDate() {
        return expectedReceiptDate;
    }

    public void setExpectedReceiptDate(String expectedReceiptDate) {
        this.expectedReceiptDate = expectedReceiptDate;
    }

    public String getValidationFlag() {
        return validationFlag;
    }

    public void setValidationFlag(String validationFlag) {
        this.validationFlag = validationFlag;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getConversionRateDate() {
        return conversionRateDate;
    }

    public void setConversionRateDate(String conversionRateDate) {
        this.conversionRateDate = conversionRateDate;
    }

    public int getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(int receiptNum) {
        this.receiptNum = receiptNum;
    }

    public Integer getTipoRecepcion() {
        return tipoRecepcion;
    }

    public void setTipoRecepcion(Integer tipoRecepcion) {
        this.tipoRecepcion = tipoRecepcion;
    }

    public void setHeaderInterfaceId(int headerInterfaceId) {
        this.headerInterfaceId = headerInterfaceId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorSiteCode() {
        return vendorSiteCode;
    }

    public void setVendorSiteCode(String vendorSiteCode) {
        this.vendorSiteCode = vendorSiteCode;
    }

    public String getBillOfLading() {
        return billOfLading;
    }

    public void setBillOfLading(String billOfLading) {
        this.billOfLading = billOfLading;
    }
}

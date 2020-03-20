package com.grupo.pinsa.sip.models.oracle.compras;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

public class OrdenCompraDetalleParaRecepcion {
    @SerializedName("interface_transaction_id")
    @Expose
    private int interfaceTransactionId;
    @SerializedName("group_id")
    @Expose
    private int groupId;
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
    @SerializedName("transaction_type")
    @Expose
    private String transactionType;
    @SerializedName("transaction_date")
    @Expose
    private String transactionDate;
    @SerializedName("processing_status_code")
    @Expose
    private String processingStatusCode;
    @SerializedName("processing_mode_code")
    @Expose
    private String processingModeCode;
    @SerializedName("transaction_status_code")
    @Expose
    private String transactionStatusCode;
    @SerializedName("po_header_id")
    @Expose
    private String poHeaderId;
    @SerializedName("po_line_id")
    @Expose
    private String poLineId;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("quantity")
    @Expose
    private Double quantity;
    @SerializedName("unit_of_measure")
    @Expose
    private String unitOfMeasure;
    @SerializedName("po_line_location_id")
    @Expose
    private String poLineLocationId;
    @SerializedName("auto_transact_code")
    @Expose
    private String autoTransactCode;
    @SerializedName("receipt_source_code")
    @Expose
    private String receiptSourceCode;
    @SerializedName("to_organization_code")
    @Expose
    private String toOrganizationCode;
    @SerializedName("source_document_code")
    @Expose
    private String sourceDocumentCode;
    @SerializedName("document_num")
    @Expose
    private String documentNum;
    @SerializedName("destination_type_code")
    @Expose
    private String destinationTypeCode;
    @SerializedName("subinventory")
    @Expose
    private String subinventory;
    @SerializedName("header_interface_id")
    @Expose
    private int headerInterfaceId;
    @SerializedName("validation_flag")
    @Expose
    private String validationFlag;
    @SerializedName("attribute8")
    @Expose
    private String attribute8;
    @SerializedName("expected_receipt_date")
    @Expose
    private String expectedReceiptDate;
    @SerializedName("ship_head_attribute_category")
    @Expose
    private String shipHeadAttributeCategory;
    @SerializedName("locator_id")
    @Expose
    private String locatorId;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("conversion_rate_type")
    @Expose
    private String conversionRateType;
    @SerializedName("conversion_rate_date")
    @Expose
    private String conversionRateDate;
    @SerializedName("tipo_linea")
    @Expose
    private Integer tipoLinea;
    @SerializedName("recepcion_orden_compra_detalle_lotes")
    @Expose
    private ArrayList<OrdenCompraDetalleLoteParaRecepcion> ordenCompraDetalleLoteParaRecepcions;
    @SerializedName("recepcion_orden_compra_detalle_serie")
    @Expose
    private ArrayList<OrdenCompraDetalleSeriesParaRecepcion> ordenCompraDetalleSeryParaRecepcions;

    /** Oracle Cloud */
    @SerializedName("parent_transaction_id")
    @Expose
    private String parentTransactionId;
    @SerializedName("receipt_number")
    @Expose
    private String receiptNumber;

    /**
     * Local Variables
     */
    private boolean received;
    private int selectedSubInventory;
    private int selectedUbication;
    @SerializedName("restrict_locators_code")
    @Expose
    private String restrictLocatorsCode;
    @SerializedName("restrict_sub_inventories_code")
    @Expose
    private String restrictSubInventoriesCode;
    private OrdenCompraDetalle ordenCompraDetalle;

    public OrdenCompraDetalleParaRecepcion() {
        interfaceTransactionId = 0;
        groupId = 0;
        lastUpdateDate = "";
        lastUpdateBy = "";
        creationDate = "";
        createdBy = "";
        lastUpdateLogin = "";
        transactionType = "";
        transactionDate = "";
        processingStatusCode = "";
        processingModeCode = "";
        transactionStatusCode = "";
        poHeaderId = "";
        poLineId = "";
        itemId = "";
        quantity = 0.0;
        unitOfMeasure = "";
        poLineLocationId = "";
        autoTransactCode = "";
        receiptSourceCode = "";
        toOrganizationCode = "";
        sourceDocumentCode = "";
        documentNum = "";
        destinationTypeCode = "";
        subinventory = "";
        headerInterfaceId = 0;
        validationFlag = "";
        attribute8 = "";
        expectedReceiptDate = "";
        shipHeadAttributeCategory = "";
        locatorId = "";
        comments = "";
        currencyCode = "";
        conversionRateType = "";
        conversionRateDate = "";
        ordenCompraDetalleLoteParaRecepcions = new ArrayList<>();
        ordenCompraDetalleSeryParaRecepcions = new ArrayList<>();
        received = false;
        selectedSubInventory = 0;
        selectedUbication = 0;
        restrictLocatorsCode = "";
        restrictSubInventoriesCode = "";
        tipoLinea = 0;
        ordenCompraDetalle = new OrdenCompraDetalle();
    }

    /**
     * Getter & Setter
     */
    public int getInterfaceTransactionId() {
        return interfaceTransactionId;
    }

    public void setInterfaceTransactionId(int interfaceTransactionId) {
        this.interfaceTransactionId = interfaceTransactionId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getProcessingStatusCode() {
        return processingStatusCode;
    }

    public void setProcessingStatusCode(String processingStatusCode) {
        this.processingStatusCode = processingStatusCode;
    }

    public String getProcessingModeCode() {
        return processingModeCode;
    }

    public void setProcessingModeCode(String processingModeCode) {
        this.processingModeCode = processingModeCode;
    }

    public String getTransactionStatusCode() {
        return transactionStatusCode;
    }

    public void setTransactionStatusCode(String transactionStatusCode) {
        this.transactionStatusCode = transactionStatusCode;
    }

    public String getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(String poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public String getPoLineId() {
        return poLineId;
    }

    public void setPoLineId(String poLineId) {
        this.poLineId = poLineId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getPoLineLocationId() {
        return poLineLocationId;
    }

    public void setPoLineLocationId(String poLineLocationId) {
        this.poLineLocationId = poLineLocationId;
    }

    public String getAutoTransactCode() {
        return autoTransactCode;
    }

    public void setAutoTransactCode(String autoTransactCode) {
        this.autoTransactCode = autoTransactCode;
    }

    public String getReceiptSourceCode() {
        return receiptSourceCode;
    }

    public void setReceiptSourceCode(String receiptSourceCode) {
        this.receiptSourceCode = receiptSourceCode;
    }

    public String getToOrganizationCode() {
        return toOrganizationCode;
    }

    public void setToOrganizationCode(String toOrganizationCode) {
        this.toOrganizationCode = toOrganizationCode;
    }

    public String getSourceDocumentCode() {
        return sourceDocumentCode;
    }

    public void setSourceDocumentCode(String sourceDocumentCode) {
        this.sourceDocumentCode = sourceDocumentCode;
    }

    public String getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(String documentNum) {
        this.documentNum = documentNum;
    }

    public String getDestinationTypeCode() {
        return destinationTypeCode;
    }

    public void setDestinationTypeCode(String destinationTypeCode) {
        this.destinationTypeCode = destinationTypeCode;
    }

    public String getSubinventory() {
        return subinventory;
    }

    public void setSubinventory(String subinventory) {
        this.subinventory = subinventory;
    }

    public int getHeaderInterfaceId() {
        return headerInterfaceId;
    }

    public void setHeaderInterfaceId(int headerInterfaceId) {
        this.headerInterfaceId = headerInterfaceId;
    }

    public String getValidationFlag() {
        return validationFlag;
    }

    public void setValidationFlag(String validationFlag) {
        this.validationFlag = validationFlag;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getExpectedReceiptDate() {
        return expectedReceiptDate;
    }

    public void setExpectedReceiptDate(String expectedReceiptDate) {
        this.expectedReceiptDate = expectedReceiptDate;
    }

    public String getShipHeadAttributeCategory() {
        return shipHeadAttributeCategory;
    }

    public void setShipHeadAttributeCategory(String shipHeadAttributeCategory) {
        this.shipHeadAttributeCategory = shipHeadAttributeCategory;
    }

    public String getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(String locatorId) {
        this.locatorId = locatorId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getConversionRateType() {
        return conversionRateType;
    }

    public void setConversionRateType(String conversionRateType) {
        this.conversionRateType = conversionRateType;
    }

    public String getConversionRateDate() {
        return conversionRateDate;
    }

    public void setConversionRateDate(String conversionRateDate) {
        this.conversionRateDate = conversionRateDate;
    }

    public boolean getReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public int getSelectedSubInventory() {
        return selectedSubInventory;
    }

    public void setSelectedSubInventory(int selectedSubInventory) {
        this.selectedSubInventory = selectedSubInventory;
    }

    public int getSelectedUbication() {
        return selectedUbication;
    }

    public void setSelectedUbication(int selectedUbication) {
        this.selectedUbication = selectedUbication;
    }

    public String getRestrictLocatorsCode() {
        return restrictLocatorsCode;
    }

    public void setRestrictLocatorsCode(String restrictLocatorsCode) {
        this.restrictLocatorsCode = restrictLocatorsCode;
    }

    public String getRestrictSubInventoriesCode() {
        return restrictSubInventoriesCode;
    }

    public void setRestrictSubInventoriesCode(String restrictSubInventoriesCode) {
        this.restrictSubInventoriesCode = restrictSubInventoriesCode;
    }

    public ArrayList<OrdenCompraDetalleLoteParaRecepcion> getOrdenCompraDetalleLoteParaRecepcions() {
        return ordenCompraDetalleLoteParaRecepcions;
    }

    public void setOrdenCompraDetalleLoteParaRecepcions(ArrayList<OrdenCompraDetalleLoteParaRecepcion> ordenCompraDetalleLoteParaRecepcions) {
        this.ordenCompraDetalleLoteParaRecepcions = ordenCompraDetalleLoteParaRecepcions;
    }

    public ArrayList<OrdenCompraDetalleSeriesParaRecepcion> getOrdenCompraDetalleSeryParaRecepcions() {
        return ordenCompraDetalleSeryParaRecepcions;
    }

    public void setOrdenCompraDetalleSeryParaRecepcions(ArrayList<OrdenCompraDetalleSeriesParaRecepcion> ordenCompraDetalleSeryParaRecepcions) {
        this.ordenCompraDetalleSeryParaRecepcions = ordenCompraDetalleSeryParaRecepcions;
    }

    public OrdenCompraDetalle getOrdenCompraDetalle() {
        return ordenCompraDetalle;
    }

    public void setOrdenCompraDetalle(OrdenCompraDetalle ordenCompraDetalle) {
        this.ordenCompraDetalle = ordenCompraDetalle;
    }

    public String getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(String parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Integer getTipolinea() {
        return tipoLinea;
    }

    public void setTipolinea(Integer tipoLinea) {
        this.tipoLinea = tipoLinea;
    }
}

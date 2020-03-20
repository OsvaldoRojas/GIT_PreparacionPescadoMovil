package com.grupo.pinsa.sip.models.oracle.inventarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Juan J. Palomera on 03/04/2019
 */

public class RecepcionOrdenCompraDetalle {
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("last_update_login")
    @Expose
    private String lastUpdateLogin;
    @SerializedName("last_update_date")
    @Expose
    private String lastUpdateDate;
    @SerializedName("last_updated_by")
    @Expose
    private String lastUpdatedBy;
    @SerializedName("organization_id")
    @Expose
    private String organizationId;
    @SerializedName("parent_transaction_id")
    @Expose
    private String parentTransactionId;
    @SerializedName("shipment_line_id")
    @Expose
    private String shipmentLineId;
    @SerializedName("interface_transaction_id")
    @Expose
    private String interfaceTransactionId;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("transact_qty")
    @Expose
    private Double transactQty; // DOUBLE
    @SerializedName("quantity")
    @Expose
    private Double quantity; // DOUBLE
    @SerializedName("primary_quantity")
    @Expose
    private Double primaryQuantity; // DOUBLE
    @SerializedName("transact_uom")
    @Expose
    private String transactUom;
    @SerializedName("primary_uom")
    @Expose
    private String primaryUom;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("item_number")
    @Expose
    private String itemNumber;
    @SerializedName("numero_ant")
    @Expose
    private String numeroAnt;
    @SerializedName("number_part")
    @Expose
    private String number_part;
    @SerializedName("fabricator")
    @Expose
    private String fabricator;
    @SerializedName("presentation")
    @Expose
    private String presentation;
    @SerializedName("item_desc")
    @Expose
    private String itemDesc;
    @SerializedName("vendor_item_num")
    @Expose
    private Integer vendorItemNum;
    @SerializedName("locator")
    @Expose
    private String locator;
    @SerializedName("destination_type_code")
    @Expose
    private String destinationTypeCode;
    @SerializedName("po_line_id")
    @Expose
    private String poLineId;
    @SerializedName("po_line_location_id")
    @Expose
    private String poLineLocationId;
    @SerializedName("po_distribution_id")
    @Expose
    private String poDistributionId;
    @SerializedName("subinventory")
    @Expose
    private String subinventory;
    @SerializedName("locator_id")
    @Expose
    private String locatorId;
    @SerializedName("source_doc_uom")
    @Expose
    private String sourceDocUom;
    @SerializedName("source_doc_qty")
    @Expose
    private Double sourceDocQty; // DOUBLE
    @SerializedName("wip_line_id")
    @Expose
    private String wipLineId;
    @SerializedName("wip_operation_seq_num")
    @Expose
    private Integer wipOperationSeqNum;
    @SerializedName("vendor_lot_num")
    @Expose
    private Integer vendorLotNum;
    @SerializedName("rma_reference")
    @Expose
    private String rmaReference;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("receipt_date")
    @Expose
    private String receiptDate;
    @SerializedName("po_line")
    @Expose
    private Integer poLine;
    @SerializedName("po_shipment")
    @Expose
    private Integer poShipment;
    @SerializedName("requisition_line")
    @Expose
    private Integer requisitionLine;
    @SerializedName("req_line_id")
    @Expose
    private String reqLineId;
    @SerializedName("order_line")
    @Expose
    private Integer orderLine;
    @SerializedName("unit_meas_lookup_code")
    @Expose
    private String unitMeasLookupCode;
    @SerializedName("need_by_date")
    @Expose
    private String needByDate;
    @SerializedName("promised_date")
    @Expose
    private String promisedDate;
    @SerializedName("quantity_ordered")
    @Expose
    private Double quantityOrdered; // DOUBLE
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("user_conversion_type")
    @Expose
    private String userConversionType;
    @SerializedName("currency_conversion_type")
    @Expose
    private String currencyConversionType;
    @SerializedName("currency_conversion_date")
    @Expose
    private String currencyConversionDate;
    @SerializedName("currency_conversion_rate")
    @Expose
    private Double currencyConversionRate;
    @SerializedName("secondary_quantity")
    @Expose
    private Double secondaryQuantity; // DOUBLE
    @SerializedName("secondary_unit_of_measure")
    @Expose
    private String secondaryUnitOfMeasure;
    @SerializedName("qc_grade")
    @Expose
    private String qcGrade;
    @SerializedName("pll_note_to_receive")
    @Expose
    private String pllNoteToReceive;
    @SerializedName("amount")
    @Expose
    private Double amount; // DOUBLE
    @SerializedName("currency_name")
    @Expose
    private String currencyName;
    @SerializedName("recepcion_orden_compra_detalle_lotes")
    @Expose
    private ArrayList<RecepcionOrdenCompraDetalleLote> recepcionOrdenCompraDetalleLoteArrayList;

    private ArrayList<RecepcionOrdenCompraDetalleSerie> recepcionOrdenCompraDetalleSerieArrayList;

    private String printDescription = "";

    public RecepcionOrdenCompraDetalle() {
        this.creationDate = "";
        this.createdBy = "";
        this.lastUpdateLogin = "";
        this.lastUpdateDate = "";
        this.lastUpdatedBy = "";
        this.organizationId = "";
        this.parentTransactionId = "";
        this.shipmentLineId = "";
        this.interfaceTransactionId = "";
        this.transactionId = "";
        this.transactQty = 0.0;
        this.quantity = 0.0;
        this.transactUom = "";
        this.primaryQuantity = 0.0;
        this.primaryUom = "";
        this.itemId = "";
        this.itemNumber = "";
        this.numeroAnt = "";
        this.number_part = "";
        this.fabricator = "";
        this.presentation = "";
        this.itemDesc = "";
        this.vendorItemNum = 0;
        this.locator = "";
        this.destinationTypeCode = "";
        this.poLineId = "";
        this.poLineLocationId = "";
        this.poDistributionId = "";
        this.subinventory = "";
        this.locatorId = "";
        this.sourceDocUom = "";
        this.sourceDocQty = 0.0;
        this.wipLineId = "";
        this.wipOperationSeqNum = 0;
        this.vendorLotNum = 0;
        this.rmaReference = "";
        this.categoryId = "";
        this.receiptDate = "";
        this.poLine = 0;
        this.poShipment = 0;
        this.requisitionLine = 0;
        this.reqLineId = "";
        this.orderLine = 0;
        this.unitMeasLookupCode = "";
        this.needByDate = "";
        this.promisedDate = "";
        this.quantityOrdered = 0.0;
        this.currencyCode = "";
        this.userConversionType = "";
        this.currencyConversionType = "";
        this.currencyConversionDate = "";
        this.currencyConversionRate = 0.0;
        this.secondaryQuantity = 0.0;
        this.secondaryUnitOfMeasure = "";
        this.qcGrade = "";
        this.pllNoteToReceive = "";
        this.amount = 0.0;
        this.currencyName = "";
        this.recepcionOrdenCompraDetalleLoteArrayList = new ArrayList<>();
        this.recepcionOrdenCompraDetalleSerieArrayList = new ArrayList<>();
    }

    /**
     * Getter & Setter
     */
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

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(String parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    public String getShipmentLineId() {
        return shipmentLineId;
    }

    public void setShipmentLineId(String shipmentLineId) {
        this.shipmentLineId = shipmentLineId;
    }

    public String getInterfaceTransactionId() {
        return interfaceTransactionId;
    }

    public void setInterfaceTransactionId(String interfaceTransactionId) {
        this.interfaceTransactionId = interfaceTransactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getTransactQty() {
        return transactQty;
    }

    public void setTransactQty(Double transactQty) {
        this.transactQty = transactQty;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrimaryQuantity() {
        return primaryQuantity;
    }

    public void setPrimaryQuantity(Double primaryQuantity) {
        this.primaryQuantity = primaryQuantity;
    }

    public String getTransactUom() {
        return transactUom;
    }

    public void setTransactUom(String transactUom) {
        this.transactUom = transactUom;
    }

    public String getPrimaryUom() {
        return primaryUom;
    }

    public void setPrimaryUom(String primaryUom) {
        this.primaryUom = primaryUom;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getNumeroAnt() {
        return numeroAnt;
    }

    public void setNumeroAnt(String numeroAnt) {
        this.numeroAnt = numeroAnt;
    }

    public String getNumber_part() {
        return number_part;
    }

    public void setNumber_part(String number_part) {
        this.number_part = number_part;
    }

    public String getFabricator() {
        return fabricator;
    }

    public void setFabricator(String fabricator) {
        this.fabricator = fabricator;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Integer getVendorItemNum() {
        return vendorItemNum;
    }

    public void setVendorItemNum(Integer vendorItemNum) {
        this.vendorItemNum = vendorItemNum;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public String getDestinationTypeCode() {
        return destinationTypeCode;
    }

    public void setDestinationTypeCode(String destinationTypeCode) {
        this.destinationTypeCode = destinationTypeCode;
    }

    public String getPoLineId() {
        return poLineId;
    }

    public void setPoLineId(String poLineId) {
        this.poLineId = poLineId;
    }

    public String getPoLineLocationId() {
        return poLineLocationId;
    }

    public void setPoLineLocationId(String poLineLocationId) {
        this.poLineLocationId = poLineLocationId;
    }

    public String getPoDistributionId() {
        return poDistributionId;
    }

    public void setPoDistributionId(String poDistributionId) {
        this.poDistributionId = poDistributionId;
    }

    public String getSubinventory() {
        return subinventory;
    }

    public void setSubinventory(String subinventory) {
        this.subinventory = subinventory;
    }

    public String getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(String locatorId) {
        this.locatorId = locatorId;
    }

    public String getSourceDocUom() {
        return sourceDocUom;
    }

    public void setSourceDocUom(String sourceDocUom) {
        this.sourceDocUom = sourceDocUom;
    }

    public Double getSourceDocQty() {
        return sourceDocQty;
    }

    public void setSourceDocQty(Double sourceDocQty) {
        this.sourceDocQty = sourceDocQty;
    }

    public String getWipLineId() {
        return wipLineId;
    }

    public void setWipLineId(String wipLineId) {
        this.wipLineId = wipLineId;
    }

    public Integer getWipOperationSeqNum() {
        return wipOperationSeqNum;
    }

    public void setWipOperationSeqNum(Integer wipOperationSeqNum) {
        this.wipOperationSeqNum = wipOperationSeqNum;
    }

    public Integer getVendorLotNum() {
        return vendorLotNum;
    }

    public void setVendorLotNum(Integer vendorLotNum) {
        this.vendorLotNum = vendorLotNum;
    }

    public String getRmaReference() {
        return rmaReference;
    }

    public void setRmaReference(String rmaReference) {
        this.rmaReference = rmaReference;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Integer getPoLine() {
        return poLine;
    }

    public void setPoLine(Integer poLine) {
        this.poLine = poLine;
    }

    public Integer getPoShipment() {
        return poShipment;
    }

    public void setPoShipment(Integer poShipment) {
        this.poShipment = poShipment;
    }

    public Integer getRequisitionLine() {
        return requisitionLine;
    }

    public void setRequisitionLine(Integer requisitionLine) {
        this.requisitionLine = requisitionLine;
    }

    public String getReqLineId() {
        return reqLineId;
    }

    public void setReqLineId(String reqLineId) {
        this.reqLineId = reqLineId;
    }

    public Integer getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(Integer orderLine) {
        this.orderLine = orderLine;
    }

    public String getUnitMeasLookupCode() {
        return unitMeasLookupCode;
    }

    public void setUnitMeasLookupCode(String unitMeasLookupCode) {
        this.unitMeasLookupCode = unitMeasLookupCode;
    }

    public String getNeedByDate() {
        return needByDate;
    }

    public void setNeedByDate(String needByDate) {
        this.needByDate = needByDate;
    }

    public String getPromisedDate() {
        return promisedDate;
    }

    public void setPromisedDate(String promisedDate) {
        this.promisedDate = promisedDate;
    }

    public Double getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Double quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getUserConversionType() {
        return userConversionType;
    }

    public void setUserConversionType(String userConversionType) {
        this.userConversionType = userConversionType;
    }

    public String getCurrencyConversionType() {
        return currencyConversionType;
    }

    public void setCurrencyConversionType(String currencyConversionType) {
        this.currencyConversionType = currencyConversionType;
    }

    public String getCurrencyConversionDate() {
        return currencyConversionDate;
    }

    public void setCurrencyConversionDate(String currencyConversionDate) {
        this.currencyConversionDate = currencyConversionDate;
    }

    public Double getCurrencyConversionRate() {
        return currencyConversionRate;
    }

    public void setCurrencyConversionRate(Double currencyConversionRate) {
        this.currencyConversionRate = currencyConversionRate;
    }

    public Double getSecondaryQuantity() {
        return secondaryQuantity;
    }

    public void setSecondaryQuantity(Double secondaryQuantity) {
        this.secondaryQuantity = secondaryQuantity;
    }

    public String getSecondaryUnitOfMeasure() {
        return secondaryUnitOfMeasure;
    }

    public void setSecondaryUnitOfMeasure(String secondaryUnitOfMeasure) {
        this.secondaryUnitOfMeasure = secondaryUnitOfMeasure;
    }

    public String getQcGrade() {
        return qcGrade;
    }

    public void setQcGrade(String qcGrade) {
        this.qcGrade = qcGrade;
    }

    public String getPllNoteToReceive() {
        return pllNoteToReceive;
    }

    public void setPllNoteToReceive(String pllNoteToReceive) {
        this.pllNoteToReceive = pllNoteToReceive;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public ArrayList<RecepcionOrdenCompraDetalleLote> getRecepcionOrdenCompraDetalleLoteArrayList() {
        return recepcionOrdenCompraDetalleLoteArrayList;
    }

    public void setRecepcionOrdenCompraDetalleLoteArrayList(ArrayList<RecepcionOrdenCompraDetalleLote> recepcionOrdenCompraDetalleLoteArrayList) {
        this.recepcionOrdenCompraDetalleLoteArrayList = recepcionOrdenCompraDetalleLoteArrayList;
    }

    public ArrayList<RecepcionOrdenCompraDetalleSerie> getRecepcionOrdenCompraDetalleSerieArrayList() {
        return recepcionOrdenCompraDetalleSerieArrayList;
    }

    public void setRecepcionOrdenCompraDetalleSerieArrayList(ArrayList<RecepcionOrdenCompraDetalleSerie> recepcionOrdenCompraDetalleSerieArrayList) {
        this.recepcionOrdenCompraDetalleSerieArrayList = recepcionOrdenCompraDetalleSerieArrayList;
    }

    public String getPrintDescription() {
        return printDescription;
    }

    public void setPrintDescription(String printDescription) {
        this.printDescription = printDescription;
    }
}

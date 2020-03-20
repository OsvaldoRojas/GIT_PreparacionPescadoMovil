package com.grupo.pinsa.sip.models.oracle.compras;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.grupo.pinsa.sip.models.oracle.inventarios.SubInventario;

import java.util.ArrayList;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

public class OrdenCompraDetalle {
    @SerializedName("po_line_id")
    @Expose
    private String poLineId;
    @SerializedName("line_num")
    @Expose
    private Integer lineNum;
    @SerializedName("line_location_id")
    @Expose
    private String lineLocationId;
    @SerializedName("shipment_num")
    @Expose
    private Integer shipmentNum;
    @SerializedName("po_release_id")
    @Expose
    private Integer poReleaseId;
    @SerializedName("release_num")
    @Expose
    private Integer releaseNum;
    @SerializedName("ship_to_organization_id")
    @Expose
    private String shipToOrganizationId;
    @SerializedName("organization_code")
    @Expose
    private String organizationCode;
    @SerializedName("outside_operation_flag")
    @Expose
    private String outsideOperationFlag;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("unit_meas_lookup_code")
    @Expose
    private String unitMeasLookupCode;
    @SerializedName("uom_class")
    @Expose
    private String uomClass;
    @SerializedName("location_control_code")
    @Expose
    private Integer locationControlCode;
    @SerializedName("restrict_locators_code")
    @Expose
    private String restrictLocatorsCode;
    @SerializedName("restrict_subinventories_code")
    @Expose
    private String restrictSubinventoriesCode;
    @SerializedName("shelf_life_code")
    @Expose
    private Integer shelfLifeCode;
    @SerializedName("shelf_life_days")
    @Expose
    private Double shelfLifeDays;
    @SerializedName("serial_number_control_code")
    @Expose
    private Integer serialNumberControlCode;
    @SerializedName("lot_control_code")
    @Expose
    private Integer lotControlCode;
    @SerializedName("revision_qty_control_code")
    @Expose
    private String revisionQtyControlCode;
    @SerializedName("item_number")
    @Expose
    private String itemNumber;
    @SerializedName("item_revision")
    @Expose
    private String itemRevision;
    @SerializedName("item_description")
    @Expose
    private String itemDescription;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("hazard_class")
    @Expose
    private String hazardClass;
    @SerializedName("un_number")
    @Expose
    private String unNumber;
    @SerializedName("vendor_product_num")
    @Expose
    private String vendorProductNum;
    @SerializedName("ship_to_location_id")
    @Expose
    private String shipToLocationId;
    @SerializedName("location_code")
    @Expose
    private String locationCode;
    @SerializedName("receiving_routing_id")
    @Expose
    private Integer receivingRoutingId;
    @SerializedName("routing_name")
    @Expose
    private String routingName;
    @SerializedName("need_by_date")
    @Expose
    private String needByDate;
    @SerializedName("promised_date")
    @Expose
    private String promisedDate;
    @SerializedName("enforce_ship_to_location_code")
    @Expose
    private String enforceShipToLocationCode;
    @SerializedName("qty_rcv_tolerance")
    @Expose
    private Double qtyRcvTolerance;
    @SerializedName("qty_rcv_exception_code")
    @Expose
    private String qtyRcvExceptionCode;
    @SerializedName("days_early_receipt_allowed")
    @Expose
    private Double daysEarlyReceiptAllowed;
    @SerializedName("days_late_receipt_allowed")
    @Expose
    private Double daysLateReceiptAllowed;
    @SerializedName("receipt_days_exception_code")
    @Expose
    private String receiptDaysExceptionCode;
    @SerializedName("quantity")
    @Expose
    private Double quantity;
    @SerializedName("quantity_received")
    @Expose
    private Double quantityReceived;
    @SerializedName("quantity_cancelled")
    @Expose
    private Double quantityCancelled;
    @SerializedName("government_context")
    @Expose
    private String governmentContext;
    @SerializedName("inspection_required_flag")
    @Expose
    private String inspectionRequiredFlag;
    @SerializedName("receipt_required_flag")
    @Expose
    private String receiptRequiredFlag;
    @SerializedName("unit_price")
    @Expose
    private Double unitPrice;
    @SerializedName("rate_type")
    @Expose
    private String rateType;
    @SerializedName("rate_date")
    @Expose
    private String rateDate;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("note_to_receiver")
    @Expose
    private String noteToReceiver;
    @SerializedName("attribute_category")
    @Expose
    private String attributeCategory;
    @SerializedName("attribute1")
    @Expose
    private String attribute1;
    @SerializedName("attribute2")
    @Expose
    private String attribute2;
    @SerializedName("attribute3")
    @Expose
    private String attribute3;
    @SerializedName("attribute4")
    @Expose
    private String attribute4;
    @SerializedName("attribute5")
    @Expose
    private String attribute5;
    @SerializedName("attribute6")
    @Expose
    private String attribute6;
    @SerializedName("attribute7")
    @Expose
    private String attribute7;
    @SerializedName("attribute8")
    @Expose
    private String attribute8;
    @SerializedName("attribute9")
    @Expose
    private String attribute9;
    @SerializedName("attribute10")
    @Expose
    private String attribute10;
    @SerializedName("attribute11")
    @Expose
    private String attribute11;
    @SerializedName("attribute12")
    @Expose
    private String attribute12;
    @SerializedName("attribute13")
    @Expose
    private String attribute13;
    @SerializedName("attribute14")
    @Expose
    private String attribute14;
    @SerializedName("attribute15")
    @Expose
    private String attribute15;
    @SerializedName("closed_code")
    @Expose
    private String closedCode;
    @SerializedName("user_conversion_type")
    @Expose
    private String userConversionType;
    @SerializedName("match_option")
    @Expose
    private String matchOption;
    @SerializedName("country_of_origin_code")
    @Expose
    private String countryOfOriginCode;
    @SerializedName("pll_note_to_receiver")
    @Expose
    private String pllNoteToReceiver;
    @SerializedName("destination_type_code")
    @Expose
    private String destinationTypeCode;
    @SerializedName("subinventarios")
    @Expose
    private ArrayList<SubInventario> subinventarios;
    @SerializedName("tipo_linea")
    @Expose
    private Integer tipoLinea;

    /** Oracle Cloud */
    @SerializedName("parent_transaction_id")
    @Expose
    private String parentTransactionId;
    @SerializedName("receipt_number")
    @Expose
    private String receiptNumber;

    public OrdenCompraDetalle() {
    }

    public OrdenCompraDetalle(String poLineId, Integer lineNum, String lineLocationId, Integer shipmentNum, Integer poReleaseId, Integer releaseNum, String shipToOrganizationId, String organizationCode, String outsideOperationFlag, String itemId, String unitMeasLookupCode, String uomClass, Integer locationControlCode, String restrictLocatorsCode, String restrictSubinventoriesCode, Integer shelfLifeCode, Double shelfLifeDays, Integer serialNumberControlCode, Integer lotControlCode, String revisionQtyControlCode, String itemNumber, String itemRevision, String itemDescription, String categoryId, String hazardClass, String unNumber, String vendorProductNum, String shipToLocationId, String locationCode, Integer receivingRoutingId, String routingName, String needByDate, String promisedDate, String enforceShipToLocationCode, Double qtyRcvTolerance, String qtyRcvExceptionCode, Double daysEarlyReceiptAllowed, Double daysLateReceiptAllowed, String receiptDaysExceptionCode, Double quantity, Double quantityReceived, Double quantityCancelled, String governmentContext, String inspectionRequiredFlag, String receiptRequiredFlag, Double unitPrice, String rateType, String rateDate, String rate, String noteToReceiver, String attributeCategory, String attribute1, String attribute2, String attribute3, String attribute4, String attribute5, String attribute6, String attribute7, String attribute8, String attribute9, String attribute10, String attribute11, String attribute12, String attribute13, String attribute14, String attribute15, String closedCode, String userConversionType, String matchOption, String countryOfOriginCode, String pllNoteToReceiver, String destinationTypeCode, Integer tipoLinea, String parentTransactionId, String receiptNumber, ArrayList<SubInventario> subinventarios) {
        this.poLineId = poLineId;
        this.lineNum = lineNum;
        this.lineLocationId = lineLocationId;
        this.shipmentNum = shipmentNum;
        this.poReleaseId = poReleaseId;
        this.releaseNum = releaseNum;
        this.shipToOrganizationId = shipToOrganizationId;
        this.organizationCode = organizationCode;
        this.outsideOperationFlag = outsideOperationFlag;
        this.itemId = itemId;
        this.unitMeasLookupCode = unitMeasLookupCode;
        this.uomClass = uomClass;
        this.locationControlCode = locationControlCode;
        this.restrictLocatorsCode = restrictLocatorsCode;
        this.restrictSubinventoriesCode = restrictSubinventoriesCode;
        this.shelfLifeCode = shelfLifeCode;
        this.shelfLifeDays = shelfLifeDays;
        this.serialNumberControlCode = serialNumberControlCode;
        this.lotControlCode = lotControlCode;
        this.revisionQtyControlCode = revisionQtyControlCode;
        this.itemNumber = itemNumber;
        this.itemRevision = itemRevision;
        this.itemDescription = itemDescription;
        this.categoryId = categoryId;
        this.hazardClass = hazardClass;
        this.unNumber = unNumber;
        this.vendorProductNum = vendorProductNum;
        this.shipToLocationId = shipToLocationId;
        this.locationCode = locationCode;
        this.receivingRoutingId = receivingRoutingId;
        this.routingName = routingName;
        this.needByDate = needByDate;
        this.promisedDate = promisedDate;
        this.enforceShipToLocationCode = enforceShipToLocationCode;
        this.qtyRcvTolerance = qtyRcvTolerance;
        this.qtyRcvExceptionCode = qtyRcvExceptionCode;
        this.daysEarlyReceiptAllowed = daysEarlyReceiptAllowed;
        this.daysLateReceiptAllowed = daysLateReceiptAllowed;
        this.receiptDaysExceptionCode = receiptDaysExceptionCode;
        this.quantity = quantity;
        this.quantityReceived = quantityReceived;
        this.quantityCancelled = quantityCancelled;
        this.governmentContext = governmentContext;
        this.inspectionRequiredFlag = inspectionRequiredFlag;
        this.receiptRequiredFlag = receiptRequiredFlag;
        this.unitPrice = unitPrice;
        this.rateType = rateType;
        this.rateDate = rateDate;
        this.rate = rate;
        this.noteToReceiver = noteToReceiver;
        this.attributeCategory = attributeCategory;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
        this.attribute6 = attribute6;
        this.attribute7 = attribute7;
        this.attribute8 = attribute8;
        this.attribute9 = attribute9;
        this.attribute10 = attribute10;
        this.attribute11 = attribute11;
        this.attribute12 = attribute12;
        this.attribute13 = attribute13;
        this.attribute14 = attribute14;
        this.attribute15 = attribute15;
        this.closedCode = closedCode;
        this.userConversionType = userConversionType;
        this.matchOption = matchOption;
        this.countryOfOriginCode = countryOfOriginCode;
        this.pllNoteToReceiver = pllNoteToReceiver;
        this.destinationTypeCode = destinationTypeCode;
        this.subinventarios = subinventarios;
        this.tipoLinea = tipoLinea;
        this.parentTransactionId = parentTransactionId;
        this.receiptNumber = receiptNumber;
    }

    /**
     * Getter & Setter
     */
    public String getPoLineId() {
        return poLineId;
    }

    public void setPoLineId(String poLineId) {
        this.poLineId = poLineId;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getLineLocationId() {
        return lineLocationId;
    }

    public void setLineLocationId(String lineLocationId) {
        this.lineLocationId = lineLocationId;
    }

    public Integer getShipmentNum() {
        return shipmentNum;
    }

    public void setShipmentNum(Integer shipmentNum) {
        this.shipmentNum = shipmentNum;
    }

    public Integer getPoReleaseId() {
        return poReleaseId;
    }

    public void setPoReleaseId(Integer poReleaseId) {
        this.poReleaseId = poReleaseId;
    }

    public Integer getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(Integer releaseNum) {
        this.releaseNum = releaseNum;
    }

    public String getShipToOrganizationId() {
        return shipToOrganizationId;
    }

    public void setShipToOrganizationId(String shipToOrganizationId) {
        this.shipToOrganizationId = shipToOrganizationId;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOutsideOperationFlag() {
        return outsideOperationFlag;
    }

    public void setOutsideOperationFlag(String outsideOperationFlag) {
        this.outsideOperationFlag = outsideOperationFlag;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUnitMeasLookupCode() {
        return unitMeasLookupCode;
    }

    public void setUnitMeasLookupCode(String unitMeasLookupCode) {
        this.unitMeasLookupCode = unitMeasLookupCode;
    }

    public String getUomClass() {
        return uomClass;
    }

    public void setUomClass(String uomClass) {
        this.uomClass = uomClass;
    }

    public Integer getLocationControlCode() {
        return locationControlCode;
    }

    public void setLocationControlCode(Integer locationControlCode) {
        this.locationControlCode = locationControlCode;
    }

    public String getRestrictLocatorsCode() {
        return restrictLocatorsCode;
    }

    public void setRestrictLocatorsCode(String restrictLocatorsCode) {
        this.restrictLocatorsCode = restrictLocatorsCode;
    }

    public String getRestrictSubinventoriesCode() {
        return restrictSubinventoriesCode;
    }

    public void setRestrictSubinventoriesCode(String restrictSubinventoriesCode) {
        this.restrictSubinventoriesCode = restrictSubinventoriesCode;
    }

    public Integer getShelfLifeCode() {
        return shelfLifeCode;
    }

    public void setShelfLifeCode(Integer shelfLifeCode) {
        this.shelfLifeCode = shelfLifeCode;
    }

    public Double getShelfLifeDays() {
        return shelfLifeDays;
    }

    public void setShelfLifeDays(Double shelfLifeDays) {
        this.shelfLifeDays = shelfLifeDays;
    }

    public Integer getSerialNumberControlCode() {
        return serialNumberControlCode;
    }

    public void setSerialNumberControlCode(Integer serialNumberControlCode) {
        this.serialNumberControlCode = serialNumberControlCode;
    }

    public Integer getLotControlCode() {
        return lotControlCode;
    }

    public void setLotControlCode(Integer lotControlCode) {
        this.lotControlCode = lotControlCode;
    }

    public String getRevisionQtyControlCode() {
        return revisionQtyControlCode;
    }

    public void setRevisionQtyControlCode(String revisionQtyControlCode) {
        this.revisionQtyControlCode = revisionQtyControlCode;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemRevision() {
        return itemRevision;
    }

    public void setItemRevision(String itemRevision) {
        this.itemRevision = itemRevision;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getHazardClass() {
        return hazardClass;
    }

    public void setHazardClass(String hazardClass) {
        this.hazardClass = hazardClass;
    }

    public String getUnNumber() {
        return unNumber;
    }

    public void setUnNumber(String unNumber) {
        this.unNumber = unNumber;
    }

    public String getVendorProductNum() {
        return vendorProductNum;
    }

    public void setVendorProductNum(String vendorProductNum) {
        this.vendorProductNum = vendorProductNum;
    }

    public String getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(String shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public Integer getReceivingRoutingId() {
        return receivingRoutingId;
    }

    public void setReceivingRoutingId(Integer receivingRoutingId) {
        this.receivingRoutingId = receivingRoutingId;
    }

    public String getRoutingName() {
        return routingName;
    }

    public void setRoutingName(String routingName) {
        this.routingName = routingName;
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

    public String getEnforceShipToLocationCode() {
        return enforceShipToLocationCode;
    }

    public void setEnforceShipToLocationCode(String enforceShipToLocationCode) {
        this.enforceShipToLocationCode = enforceShipToLocationCode;
    }

    public Double getQtyRcvTolerance() {
        return qtyRcvTolerance;
    }

    public void setQtyRcvTolerance(Double qtyRcvTolerance) {
        this.qtyRcvTolerance = qtyRcvTolerance;
    }

    public String getQtyRcvExceptionCode() {
        return qtyRcvExceptionCode;
    }

    public void setQtyRcvExceptionCode(String qtyRcvExceptionCode) {
        this.qtyRcvExceptionCode = qtyRcvExceptionCode;
    }

    public Double getDaysEarlyReceiptAllowed() {
        return daysEarlyReceiptAllowed;
    }

    public void setDaysEarlyReceiptAllowed(Double daysEarlyReceiptAllowed) {
        this.daysEarlyReceiptAllowed = daysEarlyReceiptAllowed;
    }

    public Double getDaysLateReceiptAllowed() {
        return daysLateReceiptAllowed;
    }

    public void setDaysLateReceiptAllowed(Double daysLateReceiptAllowed) {
        this.daysLateReceiptAllowed = daysLateReceiptAllowed;
    }

    public String getReceiptDaysExceptionCode() {
        return receiptDaysExceptionCode;
    }

    public void setReceiptDaysExceptionCode(String receiptDaysExceptionCode) {
        this.receiptDaysExceptionCode = receiptDaysExceptionCode;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQuantityReceived() {
        return quantityReceived;
    }

    public void setQuantityReceived(Double quantityReceived) {
        this.quantityReceived = quantityReceived;
    }

    public Double getQuantityCancelled() {
        return quantityCancelled;
    }

    public void setQuantityCancelled(Double quantityCancelled) {
        this.quantityCancelled = quantityCancelled;
    }

    public String getGovernmentContext() {
        return governmentContext;
    }

    public void setGovernmentContext(String governmentContext) {
        this.governmentContext = governmentContext;
    }

    public String getInspectionRequiredFlag() {
        return inspectionRequiredFlag;
    }

    public void setInspectionRequiredFlag(String inspectionRequiredFlag) {
        this.inspectionRequiredFlag = inspectionRequiredFlag;
    }

    public String getReceiptRequiredFlag() {
        return receiptRequiredFlag;
    }

    public void setReceiptRequiredFlag(String receiptRequiredFlag) {
        this.receiptRequiredFlag = receiptRequiredFlag;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getRateDate() {
        return rateDate;
    }

    public void setRateDate(String rateDate) {
        this.rateDate = rateDate;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNoteToReceiver() {
        return noteToReceiver;
    }

    public void setNoteToReceiver(String noteToReceiver) {
        this.noteToReceiver = noteToReceiver;
    }

    public String getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public String getAttribute11() {
        return attribute11;
    }

    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }

    public String getAttribute12() {
        return attribute12;
    }

    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }

    public String getAttribute13() {
        return attribute13;
    }

    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }

    public String getAttribute14() {
        return attribute14;
    }

    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }

    public String getAttribute15() {
        return attribute15;
    }

    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }

    public String getClosedCode() {
        return closedCode;
    }

    public void setClosedCode(String closedCode) {
        this.closedCode = closedCode;
    }

    public String getUserConversionType() {
        return userConversionType;
    }

    public void setUserConversionType(String userConversionType) {
        this.userConversionType = userConversionType;
    }

    public String getMatchOption() {
        return matchOption;
    }

    public void setMatchOption(String matchOption) {
        this.matchOption = matchOption;
    }

    public String getCountryOfOriginCode() {
        return countryOfOriginCode;
    }

    public void setCountryOfOriginCode(String countryOfOriginCode) {
        this.countryOfOriginCode = countryOfOriginCode;
    }

    public String getPllNoteToReceiver() {
        return pllNoteToReceiver;
    }

    public void setPllNoteToReceiver(String pllNoteToReceiver) {
        this.pllNoteToReceiver = pllNoteToReceiver;
    }

    public String getDestinationTypeCode() {
        return destinationTypeCode;
    }

    public void setDestinationTypeCode(String destinationTypeCode) {
        this.destinationTypeCode = destinationTypeCode;
    }

    public ArrayList<SubInventario> getSubinventarios() {
        return subinventarios;
    }

    public void setSubinventarios(ArrayList<SubInventario> subinventarios) {
        this.subinventarios = subinventarios;
    }

    public Integer getTipoLinea() {
        return tipoLinea;
    }

    public void setTipoLinea(Integer tipoLinea) {
        this.tipoLinea = tipoLinea;
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
}

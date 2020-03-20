package com.grupo.pinsa.sip.models.oracle.inventarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Juan J. Palomera on 03/04/2019
 */

public class RecepcionOrdenCompra {
    @SerializedName("organization_id")
    @Expose
    private String organizationId;
    @SerializedName("shipment_header_id")
    @Expose
    private String shipmentHeaderId;
    @SerializedName("transaction_type")
    @Expose
    private String transactionType;
    @SerializedName("transaction_date")
    @Expose
    private String transactionDate;
    @SerializedName("po_header_id")
    @Expose
    private String poHeaderId;
    @SerializedName("po_release_id")
    @Expose
    private String poReleaseId;
    @SerializedName("routing_header_id")
    @Expose
    private String routingHeaderId;
    @SerializedName("ship_to_location_id")
    @Expose
    private String shipToLocationId;
    @SerializedName("ship_to_location")
    @Expose
    private String shipToLocation;
    @SerializedName("ship_to_location_desc")
    @Expose
    private String shipToLocationDesc;
    @SerializedName("employee_id")
    @Expose
    private String employeeId;
    @SerializedName("receiver")
    @Expose
    private String receiver;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("deliver_to_location_id")
    @Expose
    private String deliverToLocationId;
    @SerializedName("deliver_to_location")
    @Expose
    private String deliverToLocation;
    @SerializedName("deliver_to_location_desc")
    @Expose
    private String deliverToLocationDesc;
    @SerializedName("deliver_to_person_id")
    @Expose
    private String deliverToPersonId;
    @SerializedName("deliver_to_person")
    @Expose
    private String deliverToPerson;
    @SerializedName("source_doc_code")
    @Expose
    private String sourceDocCode;
    @SerializedName("source_type")
    @Expose
    private String sourceType;
    @SerializedName("supplier_id")
    @Expose
    private String supplierId;
    @SerializedName("supplier")
    @Expose
    private String supplier;
    @SerializedName("vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("supplier_site_id")
    @Expose
    private String supplierSiteId;
    @SerializedName("supplier_site")
    @Expose
    private String supplierSite;
    @SerializedName("from_organization_id")
    @Expose
    private String fromOrganizationId;
    @SerializedName("wip_entity_name")
    @Expose
    private String wipEntityName;
    @SerializedName("wip_entity_id")
    @Expose
    private String wipEntityId;
    @SerializedName("wip_repetitive_schedule_id")
    @Expose
    private String wipRepetitiveScheduleId;
    @SerializedName("department_code")
    @Expose
    private String departmentCode;
    @SerializedName("inspection_status_code")
    @Expose
    private String inspectionStatusCode;
    @SerializedName("reason_id")
    @Expose
    private String reasonId;
    @SerializedName("rma_reference")
    @Expose
    private String rmaReference;
    @SerializedName("receipt_exception_flag")
    @Expose
    private String receiptExceptionFlag;
    @SerializedName("receipt_num")
    @Expose
    private String receiptNum;
    @SerializedName("po_num")
    @Expose
    private String poNum;
    @SerializedName("shipment_num")
    @Expose
    private String shipmentNum;
    @SerializedName("po_release")
    @Expose
    private String poRelease;
    @SerializedName("requisition_num")
    @Expose
    private String requisitionNum;
    @SerializedName("req_header_id")
    @Expose
    private String reqHeaderId;
    @SerializedName("order_num")
    @Expose
    private String orderNum;
    @SerializedName("routing")
    @Expose
    private String routing;
    @SerializedName("territory_short_name")
    @Expose
    private String territoryShortName;
    @SerializedName("note_to_receiver")
    @Expose
    private String noteToReceiver;
    @SerializedName("recepcion_orden_compra_detalles")
    @Expose
    private ArrayList<RecepcionOrdenCompraDetalle> recepcionOrdenCompraDetalleArrayList;

    public RecepcionOrdenCompra() {
        this.organizationId = "";
        this.shipmentHeaderId = "";
        this.transactionType = "";
        this.transactionDate = "";
        this.poHeaderId = "";
        this.poReleaseId = "";
        this.routingHeaderId = "";
        this.shipToLocationId = "";
        this.shipToLocation = "";
        this.shipToLocationDesc = "";
        this.employeeId = "";
        this.receiver = "";
        this.comments = "";
        this.deliverToLocationId = "";
        this.deliverToLocation = "";
        this.deliverToLocationDesc = "";
        this.deliverToPersonId = "";
        this.deliverToPerson = "";
        this.sourceDocCode = "";
        this.sourceType = "";
        this.supplierId = "";
        this.supplier = "";
        this.vendorId = "";
        this.vendorName = "";
        this.supplierSiteId = "";
        this.supplierSite = "";
        this.fromOrganizationId = "";
        this.wipEntityName = "";
        this.wipEntityId = "";
        this.wipRepetitiveScheduleId = "";
        this.departmentCode = "";
        this.inspectionStatusCode = "";
        this.reasonId = "";
        this.rmaReference = "";
        this.receiptExceptionFlag = "";
        this.receiptNum = "";
        this.poNum = "";
        this.shipmentNum = "";
        this.poRelease = "";
        this.requisitionNum = "";
        this.reqHeaderId = "";
        this.orderNum = "";
        this.routing = "";
        this.territoryShortName = "";
        this.noteToReceiver = "";
        this.recepcionOrdenCompraDetalleArrayList = new ArrayList<>();
    }

    /**
     * Getter & Setter
     */
    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getShipmentHeaderId() {
        return shipmentHeaderId;
    }

    public void setShipmentHeaderId(String shipmentHeaderId) {
        this.shipmentHeaderId = shipmentHeaderId;
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

    public String getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(String poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public String getPoReleaseId() {
        return poReleaseId;
    }

    public void setPoReleaseId(String poReleaseId) {
        this.poReleaseId = poReleaseId;
    }

    public String getRoutingHeaderId() {
        return routingHeaderId;
    }

    public void setRoutingHeaderId(String routingHeaderId) {
        this.routingHeaderId = routingHeaderId;
    }

    public String getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(String shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public String getShipToLocation() {
        return shipToLocation;
    }

    public void setShipToLocation(String shipToLocation) {
        this.shipToLocation = shipToLocation;
    }

    public String getShipToLocationDesc() {
        return shipToLocationDesc;
    }

    public void setShipToLocationDesc(String shipToLocationDesc) {
        this.shipToLocationDesc = shipToLocationDesc;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDeliverToLocationId() {
        return deliverToLocationId;
    }

    public void setDeliverToLocationId(String deliverToLocationId) {
        this.deliverToLocationId = deliverToLocationId;
    }

    public String getDeliverToLocation() {
        return deliverToLocation;
    }

    public void setDeliverToLocation(String deliverToLocation) {
        this.deliverToLocation = deliverToLocation;
    }

    public String getDeliverToLocationDesc() {
        return deliverToLocationDesc;
    }

    public void setDeliverToLocationDesc(String deliverToLocationDesc) {
        this.deliverToLocationDesc = deliverToLocationDesc;
    }

    public String getDeliverToPersonId() {
        return deliverToPersonId;
    }

    public void setDeliverToPersonId(String deliverToPersonId) {
        this.deliverToPersonId = deliverToPersonId;
    }

    public String getDeliverToPerson() {
        return deliverToPerson;
    }

    public void setDeliverToPerson(String deliverToPerson) {
        this.deliverToPerson = deliverToPerson;
    }

    public String getSourceDocCode() {
        return sourceDocCode;
    }

    public void setSourceDocCode(String sourceDocCode) {
        this.sourceDocCode = sourceDocCode;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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

    public String getSupplierSiteId() {
        return supplierSiteId;
    }

    public void setSupplierSiteId(String supplierSiteId) {
        this.supplierSiteId = supplierSiteId;
    }

    public String getSupplierSite() {
        return supplierSite;
    }

    public void setSupplierSite(String supplierSite) {
        this.supplierSite = supplierSite;
    }

    public String getFromOrganizationId() {
        return fromOrganizationId;
    }

    public void setFromOrganizationId(String fromOrganizationId) {
        this.fromOrganizationId = fromOrganizationId;
    }

    public String getWipEntityName() {
        return wipEntityName;
    }

    public void setWipEntityName(String wipEntityName) {
        this.wipEntityName = wipEntityName;
    }

    public String getWipEntityId() {
        return wipEntityId;
    }

    public void setWipEntityId(String wipEntityId) {
        this.wipEntityId = wipEntityId;
    }

    public String getWipRepetitiveScheduleId() {
        return wipRepetitiveScheduleId;
    }

    public void setWipRepetitiveScheduleId(String wipRepetitiveScheduleId) {
        this.wipRepetitiveScheduleId = wipRepetitiveScheduleId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getInspectionStatusCode() {
        return inspectionStatusCode;
    }

    public void setInspectionStatusCode(String inspectionStatusCode) {
        this.inspectionStatusCode = inspectionStatusCode;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getRmaReference() {
        return rmaReference;
    }

    public void setRmaReference(String rmaReference) {
        this.rmaReference = rmaReference;
    }

    public String getReceiptExceptionFlag() {
        return receiptExceptionFlag;
    }

    public void setReceiptExceptionFlag(String receiptExceptionFlag) {
        this.receiptExceptionFlag = receiptExceptionFlag;
    }

    public String getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    public String getPoNum() {
        return poNum;
    }

    public void setPoNum(String poNum) {
        this.poNum = poNum;
    }

    public String getShipmentNum() {
        return shipmentNum;
    }

    public void setShipmentNum(String shipmentNum) {
        this.shipmentNum = shipmentNum;
    }

    public String getPoRelease() {
        return poRelease;
    }

    public void setPoRelease(String poRelease) {
        this.poRelease = poRelease;
    }

    public String getRequisitionNum() {
        return requisitionNum;
    }

    public void setRequisitionNum(String requisitionNum) {
        this.requisitionNum = requisitionNum;
    }

    public String getReqHeaderId() {
        return reqHeaderId;
    }

    public void setReqHeaderId(String reqHeaderId) {
        this.reqHeaderId = reqHeaderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    public String getTerritoryShortName() {
        return territoryShortName;
    }

    public void setTerritoryShortName(String territoryShortName) {
        this.territoryShortName = territoryShortName;
    }

    public String getNoteToReceiver() {
        return noteToReceiver;
    }

    public void setNoteToReceiver(String noteToReceiver) {
        this.noteToReceiver = noteToReceiver;
    }

    public ArrayList<RecepcionOrdenCompraDetalle> getRecepcionOrdenCompraDetalleArrayList() {
        return recepcionOrdenCompraDetalleArrayList;
    }

    public void setRecepcionOrdenCompraDetalleArrayList(ArrayList<RecepcionOrdenCompraDetalle> recepcionOrdenCompraDetalleArrayList) {
        this.recepcionOrdenCompraDetalleArrayList = recepcionOrdenCompraDetalleArrayList;
    }
}
package com.workorder.app.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetWorkorderPOJO implements Serializable {

    @SerializedName("WorkOrderId")
    @Expose
    private Integer workOrderId;
    @SerializedName("WorkOrderNo")
    @Expose
    private String workOrderNo;
    @SerializedName("ClientWorkOrder")
    @Expose
    private String clientWorkOrder;
    @SerializedName("ClientName")
    @Expose
    private String clientName;
    @SerializedName("ClientContact")
    @Expose
    private String clientContact;
    @SerializedName("StatusId")
    @Expose
    private Integer statusId;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("OrderTypeId")
    @Expose
    private Integer orderTypeId;
    @SerializedName("OrderType")
    @Expose
    private String orderType;
    @SerializedName("Priority")
    @Expose
    private String priority;
    @SerializedName("WorkScope")
    @Expose
    private String workScope;
    @SerializedName("WorkOrderDate")
    @Expose
    private String workOrderDate;
    @SerializedName("DueDate")
    @Expose
    private String dueDate;
    @SerializedName("ProcessType")
    @Expose
    private String processType;
    @SerializedName("Contractor")
    @Expose
    private String contractor;
    @SerializedName("ContractorContact")
    @Expose
    private String contractorContact;
    @SerializedName("SiteName")
    @Expose
    private String siteName;
    @SerializedName("BuildingName")
    @Expose
    private String buildingName;
    @SerializedName("Floor")
    @Expose
    private Object floor;
    @SerializedName("Room")
    @Expose
    private Object room;
    @SerializedName("Address1")
    @Expose
    private String address1;
    @SerializedName("Address2")
    @Expose
    private String address2;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("PostCode")
    @Expose
    private String postCode;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Lat")
    @Expose
    private Double lat;
    @SerializedName("Lon")
    @Expose
    private Double lon;
    @SerializedName("WorkOrderStatus")
    @Expose
    private String workOrderStatus;
    @SerializedName("OrderWarningLevel")
    @Expose
    private String orderWarningLevel;

    public Integer getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Integer workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getWorkOrderNo() {
        return workOrderNo;
    }

    public void setWorkOrderNo(String workOrderNo) {
        this.workOrderNo = workOrderNo;
    }

    public String getClientWorkOrder() {
        return clientWorkOrder;
    }

    public void setClientWorkOrder(String clientWorkOrder) {
        this.clientWorkOrder = clientWorkOrder;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(Integer orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getWorkScope() {
        return workScope;
    }

    public void setWorkScope(String workScope) {
        this.workScope = workScope;
    }

    public String getWorkOrderDate() {
        return workOrderDate;
    }

    public void setWorkOrderDate(String workOrderDate) {
        this.workOrderDate = workOrderDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getContractorContact() {
        return contractorContact;
    }

    public void setContractorContact(String contractorContact) {
        this.contractorContact = contractorContact;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Object getFloor() {
        return floor;
    }

    public void setFloor(Object floor) {
        this.floor = floor;
    }

    public Object getRoom() {
        return room;
    }

    public void setRoom(Object room) {
        this.room = room;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getWorkOrderStatus() {
        return workOrderStatus;
    }

    public void setWorkOrderStatus(String workOrderStatus) {
        this.workOrderStatus = workOrderStatus;
    }

    public String getOrderWarningLevel() {
        return orderWarningLevel;
    }

    public void setOrderWarningLevel(String orderWarningLevel) {
        this.orderWarningLevel = orderWarningLevel;
    }

}

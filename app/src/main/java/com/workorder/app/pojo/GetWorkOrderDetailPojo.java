package com.workorder.app.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetWorkOrderDetailPojo implements Serializable {

    @SerializedName("AssesmentId")
    @Expose
    private Integer assesmentId;
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
    @SerializedName("TraderCategoryId")
    @Expose
    private Integer traderCategoryId;
    @SerializedName("TradeCategory")
    @Expose
    private String tradeCategory;
    @SerializedName("AssesmentType")
    @Expose
    private String assesmentType;
    @SerializedName("WorkScope")
    @Expose
    private String workScope;
    @SerializedName("AssesmentDate")
    @Expose
    private String assesmentDate;
    @SerializedName("ProjectName")
    @Expose
    private String projectName;
    @SerializedName("IsSurveyAttached")
    @Expose
    private String isSurveyAttached;
    @SerializedName("SurveyId")
    @Expose
    private Integer surveyId;
    @SerializedName("AssesmentNo")
    @Expose
    private String assesmentNo;
    @SerializedName("SiteName")
    @Expose
    private String siteName;
    @SerializedName("BuildingName")
    @Expose
    private String buildingName;
    @SerializedName("Floor")
    @Expose
    private String floor;
    @SerializedName("Room")
    @Expose
    private String room;
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

    public Integer getAssesmentId() {
        return assesmentId;
    }

    public void setAssesmentId(Integer assesmentId) {
        this.assesmentId = assesmentId;
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

    public Integer getTraderCategoryId() {
        return traderCategoryId;
    }

    public void setTraderCategoryId(Integer traderCategoryId) {
        this.traderCategoryId = traderCategoryId;
    }

    public String getTradeCategory() {
        return tradeCategory;
    }

    public void setTradeCategory(String tradeCategory) {
        this.tradeCategory = tradeCategory;
    }

    public String getAssesmentType() {
        return assesmentType;
    }

    public void setAssesmentType(String assesmentType) {
        this.assesmentType = assesmentType;
    }

    public String getWorkScope() {
        return workScope;
    }

    public void setWorkScope(String workScope) {
        this.workScope = workScope;
    }

    public String getAssesmentDate() {
        return assesmentDate;
    }

    public void setAssesmentDate(String assesmentDate) {
        this.assesmentDate = assesmentDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getIsSurveyAttached() {
        return isSurveyAttached;
    }

    public void setIsSurveyAttached(String isSurveyAttached) {
        this.isSurveyAttached = isSurveyAttached;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getAssesmentNo() {
        return assesmentNo;
    }

    public void setAssesmentNo(String assesmentNo) {
        this.assesmentNo = assesmentNo;
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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
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

}

package com.workorder.app.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeStatusPOJO implements Serializable {
    @SerializedName("SITE_ACTIVITY_ID")
    @Expose
    private Integer sITEACTIVITYID;
    @SerializedName("ASSESMENT_ID")
    @Expose
    private Integer aSSESMENTID;
    @SerializedName("LATITUDE")
    @Expose
    private Double lATITUDE;
    @SerializedName("LONGITUDE")
    @Expose
    private Double lONGITUDE;
    @SerializedName("START_DATE")
    @Expose
    private String sTARTDATE;
    @SerializedName("END_DATE")
    @Expose
    private String eNDDATE;
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("CREATED_DATE")
    @Expose
    private String cREATEDDATE;
    @SerializedName("CREATOR_ID")
    @Expose
    private Integer cREATORID;
    @SerializedName("UPDATED_DATE")
    @Expose
    private String uPDATEDDATE;
    @SerializedName("UPDATER_ID")
    @Expose
    private Integer uPDATERID;
    @SerializedName("DELETED_DATE")
    @Expose
    private Object dELETEDDATE;
    @SerializedName("DELETER_ID")
    @Expose
    private Object dELETERID;
    @SerializedName("IS_DELETED")
    @Expose
    private Boolean iSDELETED;
    @SerializedName("ESCALATION_LEVEL_ID")
    @Expose
    private Integer eSCALATIONLEVELID;
    @SerializedName("WARNING_LEVEL_ID")
    @Expose
    private Integer wARNINGLEVELID;
    @SerializedName("STATUS_LEVEL_ID")
    @Expose
    private Integer sTATUSLEVELID;
    @SerializedName("EMPLOYEE_ID")
    @Expose
    private Integer eMPLOYEEID;
    @SerializedName("Assesment")
    @Expose
    private Object assesment;
    @SerializedName("Worker")
    @Expose
    private Object worker;

    public Integer getSITEACTIVITYID() {
        return sITEACTIVITYID;
    }

    public void setSITEACTIVITYID(Integer sITEACTIVITYID) {
        this.sITEACTIVITYID = sITEACTIVITYID;
    }

    public Integer getASSESMENTID() {
        return aSSESMENTID;
    }

    public void setASSESMENTID(Integer aSSESMENTID) {
        this.aSSESMENTID = aSSESMENTID;
    }

    public Double getLATITUDE() {
        return lATITUDE;
    }

    public void setLATITUDE(Double lATITUDE) {
        this.lATITUDE = lATITUDE;
    }

    public Double getLONGITUDE() {
        return lONGITUDE;
    }

    public void setLONGITUDE(Double lONGITUDE) {
        this.lONGITUDE = lONGITUDE;
    }

    public String getSTARTDATE() {
        return sTARTDATE;
    }

    public void setSTARTDATE(String sTARTDATE) {
        this.sTARTDATE = sTARTDATE;
    }

    public String getENDDATE() {
        return eNDDATE;
    }

    public void setENDDATE(String eNDDATE) {
        this.eNDDATE = eNDDATE;
    }

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getCREATEDDATE() {
        return cREATEDDATE;
    }

    public void setCREATEDDATE(String cREATEDDATE) {
        this.cREATEDDATE = cREATEDDATE;
    }

    public Integer getCREATORID() {
        return cREATORID;
    }

    public void setCREATORID(Integer cREATORID) {
        this.cREATORID = cREATORID;
    }

    public String getUPDATEDDATE() {
        return uPDATEDDATE;
    }

    public void setUPDATEDDATE(String uPDATEDDATE) {
        this.uPDATEDDATE = uPDATEDDATE;
    }

    public Integer getUPDATERID() {
        return uPDATERID;
    }

    public void setUPDATERID(Integer uPDATERID) {
        this.uPDATERID = uPDATERID;
    }

    public Object getDELETEDDATE() {
        return dELETEDDATE;
    }

    public void setDELETEDDATE(Object dELETEDDATE) {
        this.dELETEDDATE = dELETEDDATE;
    }

    public Object getDELETERID() {
        return dELETERID;
    }

    public void setDELETERID(Object dELETERID) {
        this.dELETERID = dELETERID;
    }

    public Boolean getISDELETED() {
        return iSDELETED;
    }

    public void setISDELETED(Boolean iSDELETED) {
        this.iSDELETED = iSDELETED;
    }

    public Integer getESCALATIONLEVELID() {
        return eSCALATIONLEVELID;
    }

    public void setESCALATIONLEVELID(Integer eSCALATIONLEVELID) {
        this.eSCALATIONLEVELID = eSCALATIONLEVELID;
    }

    public Integer getWARNINGLEVELID() {
        return wARNINGLEVELID;
    }

    public void setWARNINGLEVELID(Integer wARNINGLEVELID) {
        this.wARNINGLEVELID = wARNINGLEVELID;
    }

    public Integer getSTATUSLEVELID() {
        return sTATUSLEVELID;
    }

    public void setSTATUSLEVELID(Integer sTATUSLEVELID) {
        this.sTATUSLEVELID = sTATUSLEVELID;
    }

    public Integer getEMPLOYEEID() {
        return eMPLOYEEID;
    }

    public void setEMPLOYEEID(Integer eMPLOYEEID) {
        this.eMPLOYEEID = eMPLOYEEID;
    }

    public Object getAssesment() {
        return assesment;
    }

    public void setAssesment(Object assesment) {
        this.assesment = assesment;
    }

    public Object getWorker() {
        return worker;
    }

    public void setWorker(Object worker) {
        this.worker = worker;
    }

}

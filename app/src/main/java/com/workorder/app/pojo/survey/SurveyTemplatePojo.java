package com.workorder.app.pojo.survey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveyTemplatePojo {

    @SerializedName("SURVEY_ID")
    @Expose
    private Integer sURVEYID;
    @SerializedName("Assesments")
    @Expose
    private Object assesments;
    @SerializedName("SurveyQuestions")
    @Expose
    private Object surveyQuestions;
    @SerializedName("CREATED_DATE")
    @Expose
    private Object cREATEDDATE;
    @SerializedName("CREATOR_ID")
    @Expose
    private Object cREATORID;
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
    @SerializedName("SURVEY_TYPE_ID")
    @Expose
    private Integer sURVEYTYPEID;
    @SerializedName("SurveyType")
    @Expose
    private SurveyType surveyType;
    @SerializedName("SURVEY_TITLE")
    @Expose
    private String sURVEYTITLE;
    @SerializedName("SURVEY_DESC")
    @Expose
    private String sURVEYDESC;
    @SerializedName("LAST_QUESTION_ID")
    @Expose
    private Integer lASTQUESTIONID;
    @SerializedName("TOTAL_SCORE")
    @Expose
    private Integer tOTALSCORE;
    @SerializedName("YES_NO_SCORE")
    @Expose
    private Integer yESNOSCORE;
    @SerializedName("CHOICE_SCORE")
    @Expose
    private Integer cHOICESCORE;
    @SerializedName("TEXT_SCORE")
    @Expose
    private Integer tEXTSCORE;
    @SerializedName("ESCALATION_LEVEL_ID")
    @Expose
    private Integer eSCALATIONLEVELID;
    @SerializedName("WARNING_LEVEL_ID")
    @Expose
    private Integer wARNINGLEVELID;
    @SerializedName("ENTITY_STATUS_ID")
    @Expose
    private Integer eNTITYSTATUSID;
    @SerializedName("EXTERNAL_REF")
    @Expose
    private Object eXTERNALREF;

    public Integer getSURVEYID() {
        return sURVEYID;
    }

    public void setSURVEYID(Integer sURVEYID) {
        this.sURVEYID = sURVEYID;
    }

    public Object getAssesments() {
        return assesments;
    }

    public void setAssesments(Object assesments) {
        this.assesments = assesments;
    }

    public Object getSurveyQuestions() {
        return surveyQuestions;
    }

    public void setSurveyQuestions(Object surveyQuestions) {
        this.surveyQuestions = surveyQuestions;
    }

    public Object getCREATEDDATE() {
        return cREATEDDATE;
    }

    public void setCREATEDDATE(Object cREATEDDATE) {
        this.cREATEDDATE = cREATEDDATE;
    }

    public Object getCREATORID() {
        return cREATORID;
    }

    public void setCREATORID(Object cREATORID) {
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

    public Integer getSURVEYTYPEID() {
        return sURVEYTYPEID;
    }

    public void setSURVEYTYPEID(Integer sURVEYTYPEID) {
        this.sURVEYTYPEID = sURVEYTYPEID;
    }

    public SurveyType getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(SurveyType surveyType) {
        this.surveyType = surveyType;
    }

    public String getSURVEYTITLE() {
        return sURVEYTITLE;
    }

    public void setSURVEYTITLE(String sURVEYTITLE) {
        this.sURVEYTITLE = sURVEYTITLE;
    }

    public String getSURVEYDESC() {
        return sURVEYDESC;
    }

    public void setSURVEYDESC(String sURVEYDESC) {
        this.sURVEYDESC = sURVEYDESC;
    }

    public Integer getLASTQUESTIONID() {
        return lASTQUESTIONID;
    }

    public void setLASTQUESTIONID(Integer lASTQUESTIONID) {
        this.lASTQUESTIONID = lASTQUESTIONID;
    }

    public Integer getTOTALSCORE() {
        return tOTALSCORE;
    }

    public void setTOTALSCORE(Integer tOTALSCORE) {
        this.tOTALSCORE = tOTALSCORE;
    }

    public Integer getYESNOSCORE() {
        return yESNOSCORE;
    }

    public void setYESNOSCORE(Integer yESNOSCORE) {
        this.yESNOSCORE = yESNOSCORE;
    }

    public Integer getCHOICESCORE() {
        return cHOICESCORE;
    }

    public void setCHOICESCORE(Integer cHOICESCORE) {
        this.cHOICESCORE = cHOICESCORE;
    }

    public Integer getTEXTSCORE() {
        return tEXTSCORE;
    }

    public void setTEXTSCORE(Integer tEXTSCORE) {
        this.tEXTSCORE = tEXTSCORE;
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

    public Integer getENTITYSTATUSID() {
        return eNTITYSTATUSID;
    }

    public void setENTITYSTATUSID(Integer eNTITYSTATUSID) {
        this.eNTITYSTATUSID = eNTITYSTATUSID;
    }

    public Object getEXTERNALREF() {
        return eXTERNALREF;
    }

    public void setEXTERNALREF(Object eXTERNALREF) {
        this.eXTERNALREF = eXTERNALREF;
    }

    public class SurveyType {

        @SerializedName("SURVEY_TYPE_ID")
        @Expose
        private Integer sURVEYTYPEID;
        @SerializedName("Surveys")
        @Expose
        private List<SurveyTemplatePojo> surveys = null;
        @SerializedName("NAME")
        @Expose
        private String nAME;

        public Integer getSURVEYTYPEID() {
            return sURVEYTYPEID;
        }

        public void setSURVEYTYPEID(Integer sURVEYTYPEID) {
            this.sURVEYTYPEID = sURVEYTYPEID;
        }

        public List<SurveyTemplatePojo> getSurveys() {
            return surveys;
        }

        public void setSurveys(List<SurveyTemplatePojo> surveys) {
            this.surveys = surveys;
        }

        public String getNAME() {
            return nAME;
        }

        public void setNAME(String nAME) {
            this.nAME = nAME;
        }

    }

}



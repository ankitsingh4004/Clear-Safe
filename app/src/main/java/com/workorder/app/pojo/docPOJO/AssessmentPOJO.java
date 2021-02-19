package com.workorder.app.pojo.docPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AssessmentPOJO  implements Serializable {
    @SerializedName("AssesmentTemplateId")
    @Expose
    private int AssesmentTemplateId;
    @SerializedName("AssesmentId")
    @Expose
    private   int AssesmentId;
    @SerializedName("AssignedDate")
    @Expose
    private    String AssignedDate;
    @SerializedName("Title")
    @Expose
    private   String Title;
    @SerializedName("Version")
    @Expose
    private   String Version;
    @SerializedName("TemplateNumber")
    @Expose
    private    String TemplateNumber;
    @SerializedName("SWMSStatus")
    @Expose
    private    String SWMSStatus;
    @SerializedName("SWMSWarningLevel")
    @Expose
    private    String SWMSWarningLevel;
    @SerializedName("Documents")
    @Expose
    private    List<Documents> Documents;
    @SerializedName("SignedStatus")
    @Expose
    private    boolean SignedStatus;
    @SerializedName("Attachements")
    @Expose
    private    List<Attachements> Attachements;



    public static class Documents implements Serializable
    {
        @SerializedName("DOCUMENTNAME")
        @Expose
        private String DOCUMENTNAME;
        @SerializedName("FILENAME")
        @Expose
        private String FILENAME;
        @SerializedName("DOCUMENT_URL")
        @Expose
        private String DOCUMENT_URL;
        @SerializedName("VERSION_NUMBER")
        @Expose
        private String VERSION_NUMBER;

        public void setDOCUMENTNAME(String DOCUMENTNAME){
            this.DOCUMENTNAME = DOCUMENTNAME;
        }
        public String getDOCUMENTNAME(){
            return this.DOCUMENTNAME;
        }
        public void setFILENAME(String FILENAME){
            this.FILENAME = FILENAME;
        }
        public String getFILENAME(){
            return this.FILENAME;
        }
        public void setDOCUMENT_URL(String DOCUMENT_URL){
            this.DOCUMENT_URL = DOCUMENT_URL;
        }
        public String getDOCUMENT_URL(){
            return this.DOCUMENT_URL;
        }
        public void setVERSION_NUMBER(String VERSION_NUMBER){
            this.VERSION_NUMBER = VERSION_NUMBER;
        }
        public String getVERSION_NUMBER(){
            return this.VERSION_NUMBER;
        }
    }

    public static class Attachements implements Serializable
    {
        @SerializedName("AssesmentTemplateId")
        @Expose
        private int AssesmentTemplateId;
        @SerializedName("AssignedEmployeeId")
        @Expose
        private int AssignedEmployeeId;
        @SerializedName("AssignedDate")
        @Expose
        private String AssignedDate;
        @SerializedName("Url")
        @Expose
        private String Url;
        @SerializedName("DocumentName")
        @Expose
        private String DocumentName;
        @SerializedName("Status")
        @Expose
        private String Status;

        public void setAssesmentTemplateId(int AssesmentTemplateId){
            this.AssesmentTemplateId = AssesmentTemplateId;
        }
        public int getAssesmentTemplateId(){
            return this.AssesmentTemplateId;
        }
        public void setAssignedEmployeeId(int AssignedEmployeeId){
            this.AssignedEmployeeId = AssignedEmployeeId;
        }
        public int getAssignedEmployeeId(){
            return this.AssignedEmployeeId;
        }
        public void setAssignedDate(String AssignedDate){
            this.AssignedDate = AssignedDate;
        }
        public String getAssignedDate(){
            return this.AssignedDate;
        }
        public void setUrl(String Url){
            this.Url = Url;
        }
        public String getUrl(){
            return this.Url;
        }
        public void setDocumentName(String DocumentName){
            this.DocumentName = DocumentName;
        }
        public String getDocumentName(){
            return this.DocumentName;
        }
        public void setStatus(String Status){
            this.Status = Status;
        }
        public String getStatus(){
            return this.Status;
        }
    }

    public int getAssesmentTemplateId() {
        return AssesmentTemplateId;
    }

    public void setAssesmentTemplateId(int assesmentTemplateId) {
        AssesmentTemplateId = assesmentTemplateId;
    }

    public int getAssesmentId() {
        return AssesmentId;
    }

    public void setAssesmentId(int assesmentId) {
        AssesmentId = assesmentId;
    }

    public String getAssignedDate() {
        return AssignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        AssignedDate = assignedDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getTemplateNumber() {
        return TemplateNumber;
    }

    public void setTemplateNumber(String templateNumber) {
        TemplateNumber = templateNumber;
    }

    public String getSWMSStatus() {
        return SWMSStatus;
    }

    public void setSWMSStatus(String SWMSStatus) {
        this.SWMSStatus = SWMSStatus;
    }

    public String getSWMSWarningLevel() {
        return SWMSWarningLevel;
    }

    public void setSWMSWarningLevel(String SWMSWarningLevel) {
        this.SWMSWarningLevel = SWMSWarningLevel;
    }

    public List<AssessmentPOJO.Documents> getDocuments() {
        return Documents;
    }

    public void setDocuments(List<AssessmentPOJO.Documents> documents) {
        Documents = documents;
    }

    public boolean isSignedStatus() {
        return SignedStatus;
    }

    public void setSignedStatus(boolean signedStatus) {
        SignedStatus = signedStatus;
    }

    public List<AssessmentPOJO.Attachements> getAttachements() {
        return Attachements;
    }

    public void setAttachements(List<AssessmentPOJO.Attachements> attachements) {
        Attachements = attachements;
    }
}






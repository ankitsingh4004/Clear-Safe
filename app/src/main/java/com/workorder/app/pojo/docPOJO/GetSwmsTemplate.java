package com.workorder.app.pojo.docPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetSwmsTemplate implements Serializable {
    @SerializedName("AssesmentTemplateId")
    @Expose
    private Integer assesmentTemplateId;
    @SerializedName("AssesmentId")
    @Expose
    private Integer assesmentId;
    @SerializedName("AssignedDate")
    @Expose
    private String assignedDate;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("TemplateNumber")
    @Expose
    private String templateNumber;
    @SerializedName("SWMSStatus")
    @Expose
    private String sWMSStatus;
    @SerializedName("SWMSWarningLevel")
    @Expose
    private String sWMSWarningLevel;
    @SerializedName("DOCUMENTNAME")
    @Expose
    private String dOCUMENTNAME;
    @SerializedName("FILENAME")
    @Expose
    private String fILENAME;
    @SerializedName("DOCUMENT_URL")
    @Expose
    private String dOCUMENTURL;
    @SerializedName("VERSION_NUMBER")
    @Expose
    private String vERSIONNUMBER;

    @SerializedName("SignedStatus")
    private Boolean SignedStatus;

    @SerializedName("Attachements")
    @Expose
    private List<Attachement> attachements = null;

    public Integer getAssesmentTemplateId() {
        return assesmentTemplateId;
    }

    public void setAssesmentTemplateId(Integer assesmentTemplateId) {
        this.assesmentTemplateId = assesmentTemplateId;
    }

    public Integer getAssesmentId() {
        return assesmentId;
    }

    public void setAssesmentId(Integer assesmentId) {
        this.assesmentId = assesmentId;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTemplateNumber() {
        return templateNumber;
    }

    public void setTemplateNumber(String templateNumber) {
        this.templateNumber = templateNumber;
    }

    public String getSWMSStatus() {
        return sWMSStatus;
    }

    public void setSWMSStatus(String sWMSStatus) {
        this.sWMSStatus = sWMSStatus;
    }

    public String getSWMSWarningLevel() {
        return sWMSWarningLevel;
    }

    public void setSWMSWarningLevel(String sWMSWarningLevel) {
        this.sWMSWarningLevel = sWMSWarningLevel;
    }

    public String getDOCUMENTNAME() {
        return dOCUMENTNAME;
    }

    public void setDOCUMENTNAME(String dOCUMENTNAME) {
        this.dOCUMENTNAME = dOCUMENTNAME;
    }

    public String getFILENAME() {
        return fILENAME;
    }

    public void setFILENAME(String fILENAME) {
        this.fILENAME = fILENAME;
    }

    public String getDOCUMENTURL() {
        return dOCUMENTURL;
    }

    public void setDOCUMENTURL(String dOCUMENTURL) {
        this.dOCUMENTURL = dOCUMENTURL;
    }

    public String getVERSIONNUMBER() {
        return vERSIONNUMBER;
    }

    public void setVERSIONNUMBER(String vERSIONNUMBER) {
        this.vERSIONNUMBER = vERSIONNUMBER;
    }

    public List<Attachement> getAttachements() {
        return attachements;
    }

    public void setAttachements(List<Attachement> attachements) {
        this.attachements = attachements;
    }

    public Boolean getSignedStatus() {
        return SignedStatus;
    }

    public void setSignedStatus(Boolean signedStatus) {
        SignedStatus = signedStatus;
    }

    public class Attachement {

        @SerializedName("AssesmentTemplateId")
        @Expose
        private Integer assesmentTemplateId;
        @SerializedName("AssignedEmployeeId")
        @Expose
        private Integer assignedEmployeeId;
        @SerializedName("AssignedDate")
        @Expose
        private String assignedDate;
        @SerializedName("Url")
        @Expose
        private Object url;
        @SerializedName("DocumentName")
        @Expose
        private String documentName;
        @SerializedName("Status")
        @Expose
        private String status;

        public Integer getAssesmentTemplateId() {
            return assesmentTemplateId;
        }

        public void setAssesmentTemplateId(Integer assesmentTemplateId) {
            this.assesmentTemplateId = assesmentTemplateId;
        }

        public Integer getAssignedEmployeeId() {
            return assignedEmployeeId;
        }

        public void setAssignedEmployeeId(Integer assignedEmployeeId) {
            this.assignedEmployeeId = assignedEmployeeId;
        }

        public String getAssignedDate() {
            return assignedDate;
        }

        public void setAssignedDate(String assignedDate) {
            this.assignedDate = assignedDate;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public String getDocumentName() {
            return documentName;
        }

        public void setDocumentName(String documentName) {
            this.documentName = documentName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Attachement() {
        }
    }
}

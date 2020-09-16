package com.workorder.app.pojo.survey;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveyQuestionPojo {

    @SerializedName("SURVEYQ_QUESTION_ID")
    @Expose
    private Integer sURVEYQQUESTIONID;
    @SerializedName("SurveyQuestions1")
    @Expose
    private Object surveyQuestions1;
    @SerializedName("SurveyAnswers")
    @Expose
    private List<SurveyAnswer> surveyAnswers = null;
    @SerializedName("SurveyAnswerValues")
    @Expose
    private Object surveyAnswerValues;
    @SerializedName("SURVEY_ID")
    @Expose
    private Integer sURVEYID;
    @SerializedName("Survey")
    @Expose
    private Object survey;
    @SerializedName("CREATED_DATE")
    @Expose
    private String cREATEDDATE;
    @SerializedName("CREATOR_ID")
    @Expose
    private Integer cREATORID;
    @SerializedName("UPDATED_DATE")
    @Expose
    private Object uPDATEDDATE;
    @SerializedName("UPDATER_ID")
    @Expose
    private Object uPDATERID;
    @SerializedName("DELETED_DATE")
    @Expose
    private Object dELETEDDATE;
    @SerializedName("DELETER_ID")
    @Expose
    private Object dELETERID;
    @SerializedName("IS_DELETED")
    @Expose
    private Boolean iSDELETED;
    @SerializedName("SURVEYQ_ORDER")
    @Expose
    private Integer sURVEYQORDER;
    @SerializedName("QUESTION_TYPE_ID")
    @Expose
    private Integer qUESTIONTYPEID;
    @SerializedName("QuestionType")
    @Expose
    private Object questionType;
    @SerializedName("QUESTION_TITLE")
    @Expose
    private String qUESTIONTITLE;
    @SerializedName("QUESTION_DESC")
    @Expose
    private String qUESTIONDESC;
    @SerializedName("SCORE")
    @Expose
    private Integer sCORE;
    @SerializedName("PARENT_Q_ID")
    @Expose
    private Object pARENTQID;
    @SerializedName("SurveyQuestion1")
    @Expose
    private Object surveyQuestion1;
    @SerializedName("EXTERNAL_REF")
    @Expose
    private Object eXTERNALREF;

    public Integer getSURVEYQQUESTIONID() {
        return sURVEYQQUESTIONID;
    }

    public void setSURVEYQQUESTIONID(Integer sURVEYQQUESTIONID) {
        this.sURVEYQQUESTIONID = sURVEYQQUESTIONID;
    }

    public Object getSurveyQuestions1() {
        return surveyQuestions1;
    }

    public void setSurveyQuestions1(Object surveyQuestions1) {
        this.surveyQuestions1 = surveyQuestions1;
    }

    public List<SurveyAnswer> getSurveyAnswers() {
        return surveyAnswers;
    }

    public void setSurveyAnswers(List<SurveyAnswer> surveyAnswers) {
        this.surveyAnswers = surveyAnswers;
    }

    public Object getSurveyAnswerValues() {
        return surveyAnswerValues;
    }

    public void setSurveyAnswerValues(Object surveyAnswerValues) {
        this.surveyAnswerValues = surveyAnswerValues;
    }

    public Integer getSURVEYID() {
        return sURVEYID;
    }

    public void setSURVEYID(Integer sURVEYID) {
        this.sURVEYID = sURVEYID;
    }

    public Object getSurvey() {
        return survey;
    }

    public void setSurvey(Object survey) {
        this.survey = survey;
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

    public Object getUPDATEDDATE() {
        return uPDATEDDATE;
    }

    public void setUPDATEDDATE(Object uPDATEDDATE) {
        this.uPDATEDDATE = uPDATEDDATE;
    }

    public Object getUPDATERID() {
        return uPDATERID;
    }

    public void setUPDATERID(Object uPDATERID) {
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

    public Integer getSURVEYQORDER() {
        return sURVEYQORDER;
    }

    public void setSURVEYQORDER(Integer sURVEYQORDER) {
        this.sURVEYQORDER = sURVEYQORDER;
    }

    public Integer getQUESTIONTYPEID() {
        return qUESTIONTYPEID;
    }

    public void setQUESTIONTYPEID(Integer qUESTIONTYPEID) {
        this.qUESTIONTYPEID = qUESTIONTYPEID;
    }

    public Object getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Object questionType) {
        this.questionType = questionType;
    }

    public String getQUESTIONTITLE() {
        return qUESTIONTITLE;
    }

    public void setQUESTIONTITLE(String qUESTIONTITLE) {
        this.qUESTIONTITLE = qUESTIONTITLE;
    }

    public String getQUESTIONDESC() {
        return qUESTIONDESC;
    }

    public void setQUESTIONDESC(String qUESTIONDESC) {
        this.qUESTIONDESC = qUESTIONDESC;
    }

    public Integer getSCORE() {
        return sCORE;
    }

    public void setSCORE(Integer sCORE) {
        this.sCORE = sCORE;
    }

    public Object getPARENTQID() {
        return pARENTQID;
    }

    public void setPARENTQID(Object pARENTQID) {
        this.pARENTQID = pARENTQID;
    }

    public Object getSurveyQuestion1() {
        return surveyQuestion1;
    }

    public void setSurveyQuestion1(Object surveyQuestion1) {
        this.surveyQuestion1 = surveyQuestion1;
    }

    public Object getEXTERNALREF() {
        return eXTERNALREF;
    }

    public void setEXTERNALREF(Object eXTERNALREF) {
        this.eXTERNALREF = eXTERNALREF;
    }


    public class SurveyAnswer {

        @SerializedName("SURVEY_ANSWER_ID")
        @Expose
        private Integer sURVEYANSWERID;
        @SerializedName("SurveyAnswerValues")
        @Expose
        private Object surveyAnswerValues;
        @SerializedName("SURVEY_QUESTION_ID")
        @Expose
        private Integer sURVEYQUESTIONID;
        @SerializedName("CREATED_DATE")
        @Expose
        private String cREATEDDATE;
        @SerializedName("CREATOR_ID")
        @Expose
        private Integer cREATORID;
        @SerializedName("UPDATED_DATE")
        @Expose
        private Object uPDATEDDATE;
        @SerializedName("UPDATER_ID")
        @Expose
        private Object uPDATERID;
        @SerializedName("DELETED_DATE")
        @Expose
        private Object dELETEDDATE;
        @SerializedName("DELETER_ID")
        @Expose
        private Object dELETERID;
        @SerializedName("IS_DELETED")
        @Expose
        private Boolean iSDELETED;
        @SerializedName("SURVEY_ANSWER_TITLE")
        @Expose
        private String sURVEYANSWERTITLE;
        @SerializedName("SURVEY_A_COMMENT")
        @Expose
        private Object sURVEYACOMMENT;
        @SerializedName("SURVEYA_VALUE")
        @Expose
        private Object sURVEYAVALUE;
        @SerializedName("SURVEYA_SCORE")
        @Expose
        private Integer sURVEYASCORE;
        @SerializedName("GOTO_QUESTION_ID")
        @Expose
        private Object gOTOQUESTIONID;
        @SerializedName("GoToQuestion")
        @Expose
        private Object goToQuestion;

        public Integer getSURVEYANSWERID() {
            return sURVEYANSWERID;
        }

        public void setSURVEYANSWERID(Integer sURVEYANSWERID) {
            this.sURVEYANSWERID = sURVEYANSWERID;
        }

        public Object getSurveyAnswerValues() {
            return surveyAnswerValues;
        }

        public void setSurveyAnswerValues(Object surveyAnswerValues) {
            this.surveyAnswerValues = surveyAnswerValues;
        }

        public Integer getSURVEYQUESTIONID() {
            return sURVEYQUESTIONID;
        }

        public void setSURVEYQUESTIONID(Integer sURVEYQUESTIONID) {
            this.sURVEYQUESTIONID = sURVEYQUESTIONID;
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

        public Object getUPDATEDDATE() {
            return uPDATEDDATE;
        }

        public void setUPDATEDDATE(Object uPDATEDDATE) {
            this.uPDATEDDATE = uPDATEDDATE;
        }

        public Object getUPDATERID() {
            return uPDATERID;
        }

        public void setUPDATERID(Object uPDATERID) {
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

        public String getSURVEYANSWERTITLE() {
            return sURVEYANSWERTITLE;
        }

        public void setSURVEYANSWERTITLE(String sURVEYANSWERTITLE) {
            this.sURVEYANSWERTITLE = sURVEYANSWERTITLE;
        }

        public Object getSURVEYACOMMENT() {
            return sURVEYACOMMENT;
        }

        public void setSURVEYACOMMENT(Object sURVEYACOMMENT) {
            this.sURVEYACOMMENT = sURVEYACOMMENT;
        }

        public Object getSURVEYAVALUE() {
            return sURVEYAVALUE;
        }

        public void setSURVEYAVALUE(Object sURVEYAVALUE) {
            this.sURVEYAVALUE = sURVEYAVALUE;
        }

        public Integer getSURVEYASCORE() {
            return sURVEYASCORE;
        }

        public void setSURVEYASCORE(Integer sURVEYASCORE) {
            this.sURVEYASCORE = sURVEYASCORE;
        }

        public Object getGOTOQUESTIONID() {
            return gOTOQUESTIONID;
        }

        public void setGOTOQUESTIONID(Object gOTOQUESTIONID) {
            this.gOTOQUESTIONID = gOTOQUESTIONID;
        }

        public Object getGoToQuestion() {
            return goToQuestion;
        }

        public void setGoToQuestion(Object goToQuestion) {
            this.goToQuestion = goToQuestion;
        }

    }

}

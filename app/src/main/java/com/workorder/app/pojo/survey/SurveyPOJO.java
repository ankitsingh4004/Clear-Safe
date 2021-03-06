package com.workorder.app.pojo.survey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SurveyPOJO implements Serializable {

    @SerializedName("parentQuestionID")
    @Expose
    private String parentQuestionID;


    @SerializedName("parentAnswerID")
    @Expose
    private String parentAnswerID;


    @SerializedName("questionID")
    @Expose
    private String questionID;


    @SerializedName("gotoQuestionID")
    @Expose
    private String gotoQuestionID;


    @SerializedName("ordering")
    @Expose
    private String ordering;

    @SerializedName("questionType")
    @Expose
    private String questionType;

    @SerializedName("questionTypeName")
    @Expose
    private String questionTypeName;

    @SerializedName("title")
    @Expose
    private String title;


    @SerializedName("description")
    @Expose
    private String description;


    @SerializedName("comment")
    @Expose
    private String comment;


    @SerializedName("score")
    @Expose
    private String score;


    @SerializedName("surveyID")
    @Expose
    private String surveyID;


    @SerializedName("surveyAID")
    @Expose
    private String surveyAID;

    @SerializedName("answerModels")
    public ArrayList<SurveyquestPOJO> surveyquestPOJOS;

    public String getParentQuestionID() {
        return parentQuestionID;
    }

    public void setParentQuestionID(String parentQuestionID) {
        this.parentQuestionID = parentQuestionID;
    }

    public String getParentAnswerID() {
        return parentAnswerID;
    }

    public void setParentAnswerID(String parentAnswerID) {
        this.parentAnswerID = parentAnswerID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getGotoQuestionID() {
        return gotoQuestionID;
    }

    public void setGotoQuestionID(String gotoQuestionID) {
        this.gotoQuestionID = gotoQuestionID;
    }

    public String getOrdering() {
        return ordering;
    }

    public void setOrdering(String ordering) {
        this.ordering = ordering;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }

    public String getSurveyAID() {
        return surveyAID;
    }

    public void setSurveyAID(String surveyAID) {
        this.surveyAID = surveyAID;
    }

    public ArrayList<SurveyquestPOJO> getSurveyquestPOJOS() {
        return surveyquestPOJOS;
    }

    public void setSurveyquestPOJOS(ArrayList<SurveyquestPOJO> surveyquestPOJOS) {
        this.surveyquestPOJOS = surveyquestPOJOS;
    }

    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }
}

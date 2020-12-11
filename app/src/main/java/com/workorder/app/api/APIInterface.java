package com.workorder.app.api;

import com.workorder.app.pojo.survey.SurveyQuestionPojo;
import com.workorder.app.pojo.survey.SurveyTemplatePojo;


import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("api/Survey/GetSurveyTemplates")
    Call<List<SurveyTemplatePojo>> surveytemplate(
            @Header("Authorization")
                    String Token, @Query("assesmentId") int id);

    @GET("api/Survey/GetSurveyQuestions")
    Call<List<SurveyQuestionPojo>> surveytemplatequest(
            @Header("Authorization")
                    String Token , @Query("surveyId") int id);

}

package com.workorder.app.util;

import com.workorder.app.activity.HomeActivity;
import com.workorder.app.pojo.CreateAssetPOJO;
import com.workorder.app.pojo.GetSiteByIdPOJO;
import com.workorder.app.pojo.GetWorkOrderDetailPojo;
import com.workorder.app.pojo.GetWorkerPOJO;
import com.workorder.app.pojo.GetWorkorderPOJO;
import com.workorder.app.pojo.HomeStatusPOJO;
import com.workorder.app.pojo.LoginPOJO;
import com.workorder.app.pojo.WorkOrderPOJO;
import com.workorder.app.pojo.assesment.AssesmentHomePOJO;
import com.workorder.app.pojo.docPOJO.DocListPOJO;
import com.workorder.app.pojo.docPOJO.GetSwmsTemplate;
import com.workorder.app.pojo.survey.SurveyPOJO;



import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static List<GetSiteByIdPOJO> getSiteList=new ArrayList<>();
    public static List<GetWorkerPOJO> workerPOJOList=new ArrayList<>();
    //public static LoginResponseWorkOrderModel loginResponse;

    public static List<AssesmentHomePOJO> assesmentHomePOJOList=new ArrayList<>();

    public static List<GetWorkorderPOJO> workOrderPOJOList=new ArrayList<>();
    public static List<GetWorkOrderDetailPojo> workOrderPOJOdetail=new ArrayList<>();
    public static List<GetSwmsTemplate> getSwmsTemplates=new ArrayList<>();


    public static HomeStatusPOJO homeStatusPOJO;

    public static String USER_ID="";
    public static AssesmentHomePOJO assesmentHomePOJO;

    public static GetWorkorderPOJO workOrderPOJO;
    public static GetWorkOrderDetailPojo workOrderdetail;
    public static DocListPOJO docListPOJO;
    public static GetSwmsTemplate getSwmsTemplate;


    public static SurveyPOJO surveyPOJO;


    public static LoginPOJO loginPOJO;

    public static int SEND_STATUS;
    public static String SEND_STATUS1;
    public static int CHECKED_POSITION=-1;


    public static int TIME_PERIOD=-1;


    public static double CURRENT_LAT;
    public static double CURRENT_LNG;
    public static String PROVIDER="";
    public static String DISTANCE="";

    public static String TASK_ID="67";
    public static final String HOME_ACTIVITY="home activity is opened";
    public static final String SHOW_DOCUMENT_ACTIVITY="Show Document activity is opened";
    public static final String SHOW_SURVEY_ACTIVITY="Show SURVEY activity is opened";
    public static String ACTIVITY_NAME= HOME_ACTIVITY;


    public static String FROM_CITY="";
    public static String FROM_COUNTRY="";
    public static String TO_COUNTRY="";


    public static CreateAssetPOJO createAssetModel=new CreateAssetPOJO();










}

package com.workorder.app.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.workorder.app.MyApplication;

import static android.content.Context.MODE_PRIVATE;

public class UrlClass {

    public static String BASE_URL = "BASE_URL";

    private final static String PREFS = "myUrlPrefs";

    public static String getBaseUrl (){


        SharedPreferences pref =MyApplication.getContext().getSharedPreferences(PREFS,MODE_PRIVATE);

        Log.d("fetchurl",pref.getString(BASE_URL,""));

        return MyApplication.getContext()
                .getSharedPreferences(PREFS, MODE_PRIVATE)
                .getString(BASE_URL,"https://clear-peoplesafe.com/crm/");

    }

    //public static String BASE_URL = "https://clear-peoplesafe.com/crm/";

    public static String LOGIN_URL = getBaseUrl() + "api/account/login";
    public static String LOGOUT_URL=getBaseUrl()+"api/account/Logout";
    public static String Change_Password=getBaseUrl()+"account/change-password";
    public static String Reset_Password=getBaseUrl()+"api/Account/ResetPassword";
    public static String Forgot_Password=getBaseUrl()+"api/Account/ForgotPassword?Email=";

    public static String SURVEY_URL=getBaseUrl()+"api/SurveyApi/QuestionAnswerList?SurveyID=";
    public static String SURVEY_TEMPLATE=getBaseUrl()+"api/order/workOrderSurveyList?workorderId=";
    public static String Worker_Home=getBaseUrl()+"api/Site/GetWorder";
    public static String GET_LOCATION_URL=getBaseUrl()+"api/Site/GetLocation";

    public static String GET_TASK_API_URL=getBaseUrl()+"api/taskapi/";

    public static String GET_SITE_BY_ID=getBaseUrl()+"api/api/Site/SiteById?Id=";
    public static String CHECK_ON_SITE_URL=getBaseUrl()+"api/Order/Createactivity";//"taskapi/Activity/";

    public static String ROLE_TYPE="";
    public static String COMPANYID="";//"http://109.228.49.117:8095/api/TaskApi/GetAllTaskList?rolename=Worker&companyid=2148"
    public static String GET_ALL_TASK_URL=getBaseUrl()+"api/TaskApi/GetAllTaskList?rolename=";//+ROLE_TYPE+"&companyid="+COMPANYID;//"2148";
    //http://109.228.49.117:8095/api/order/assignedorderlist?rolename=\(userRole)&companyid=\(companyID)
    public static String GET_WORKORDER_URL=getBaseUrl()+"api/order/assignedorderlist?rolename=";

    public static String UPLOAD_SIGN_URL=getBaseUrl()+"api/Order/SignedDocument"; //"TaskApi/SignedDocument";
    public static String UPLOAD_SIGN_DOCUMENT=getBaseUrl()+"api/Order/SignCompanyDocument"; //"TaskApi/SignedDocument";

    public static String GET_WORK_ORDER_NO=getBaseUrl()+"api/workorderfortenant/getworkordernumber";

    public static String UPDATE_STATUS_URL=getBaseUrl()+ "api/Order/updateactivity";//"SiteActivity/updatestatus";
    public static String SUBMIT_ANSWER=getBaseUrl()+ "api/Survey/SubmitSurvey";//"SiteActivity/updatestatus";

    public static String CREATE_ACTIVITY_URL=getBaseUrl()+"api/SiteActivity/createactivity";//http://109.228.49.117:8095/api/SiteActivity/createactivity


    public static String ASSET_PROCESS_TYPE_URL=getBaseUrl()+"api/assets/ProcessTypeAssets";

    public static String WO_PROCESS_TYPE_URL=getBaseUrl()+"api/order/ProcessTypeWOrder";


    /*   http://localhost:35853/api/Site/GetLocation

    {
        "personcompanyid":2127
    }*/
    /*http://localhost:35853/api/Site/GetWorder


    {
        "SiteId": 1
    }*/
}

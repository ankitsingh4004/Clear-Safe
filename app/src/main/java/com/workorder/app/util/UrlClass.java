package com.workorder.app.util;

import com.workorder.app.workorderapplication.activity.SearchAsset;

public class UrlClass {
   public static String BASE_URL="http://109.228.49.117:8097/";
   // public static String BASE_URL="192.168.0.14:5002/";
    public static String LOGIN_URL=BASE_URL+"api/account/login";
    public static String LOGOUT_URL=BASE_URL+"api/account/Logout";
    public static String Change_Password=BASE_URL+"account/change-password";
    public static String Reset_Password=BASE_URL+"api/Account/ResetPassword";
    public static String Forgot_Password=BASE_URL+"api/Account/ForgotPassword?Email=";

    public static String SURVEY_URL=BASE_URL+"api/SurveyApi/QuestionAnswerList?SurveyID=";
    public static String SURVEY_TEMPLATE=BASE_URL+"api/order/workOrderSurveyList?workorderId=";
    public static String Worker_Home=BASE_URL+"api/Site/GetWorder";
    public static String GET_LOCATION_URL=BASE_URL+"api/Site/GetLocation";

    public static String GET_TASK_API_URL=BASE_URL+"api/taskapi/";

    public static String GET_SITE_BY_ID=BASE_URL+"api/api/Site/SiteById?Id=";
    public static String CHECK_ON_SITE_URL=BASE_URL+"api/Order/Createactivity";//"taskapi/Activity/";

    public static String ROLE_TYPE="";
    public static String COMPANYID="";//"http://109.228.49.117:8095/api/TaskApi/GetAllTaskList?rolename=Worker&companyid=2148"
    public static String GET_ALL_TASK_URL=BASE_URL+"api/TaskApi/GetAllTaskList?rolename=";//+ROLE_TYPE+"&companyid="+COMPANYID;//"2148";
//http://109.228.49.117:8095/api/order/assignedorderlist?rolename=\(userRole)&companyid=\(companyID)
    public static String GET_WORKORDER_URL=BASE_URL+"api/order/assignedorderlist?rolename=";

    public static String UPLOAD_SIGN_URL=BASE_URL+"api/Order/SignedDocument"; //"TaskApi/SignedDocument";
    public static String UPLOAD_SIGN_DOCUMENT=BASE_URL+"api/Order/SignCompanyDocument"; //"TaskApi/SignedDocument";

    public static String GET_WORK_ORDER_NO=BASE_URL+"api/workorderfortenant/getworkordernumber";

    public static String UPDATE_STATUS_URL=BASE_URL+ "api/Order/updateactivity";//"SiteActivity/updatestatus";
    public static String SUBMIT_ANSWER=BASE_URL+ "api/Survey/SubmitSurvey";//"SiteActivity/updatestatus";

    public static String CREATE_ACTIVITY_URL=BASE_URL+"api/SiteActivity/createactivity";//http://109.228.49.117:8095/api/SiteActivity/createactivity


    public static String ASSET_PROCESS_TYPE_URL=BASE_URL+"api/assets/ProcessTypeAssets";

    public static String WO_PROCESS_TYPE_URL=BASE_URL+"api/order/ProcessTypeWOrder";


    /*   http://localhost:35853/api/Site/GetLocation

    {
        "personcompanyid":2127
    }*/
    /*http://localhost:35853/api/Site/GetWorder


    {
        "SiteId": 1
    }*/
}

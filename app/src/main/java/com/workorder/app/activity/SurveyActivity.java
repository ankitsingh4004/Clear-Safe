package com.workorder.app.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.workorder.app.ConnectivityReceiver;
import com.workorder.app.R;
import com.workorder.app.adapter.SurveyAdapter;
import com.workorder.app.api.APIHelper;
import com.workorder.app.api.APIInterface;

import com.workorder.app.pojo.survey.SurveyQuestionPojo;
import com.workorder.app.util.Constants;
import com.workorder.app.util.UrlClass;
import com.workorder.app.webservicecallback.OnTaskCompleted;
import com.workorder.app.webservicecallback.SendData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

import static com.workorder.app.adapter.SurveyAdapter.map3;

public class SurveyActivity extends FragmentActivity implements SurveyAdapter.RatingSelectionInterface {
    int surveyTemplateId;
    TextView tv_list_type;
    ImageView iv_back;
    ImageView iv_mapview;
    SurveyAdapter adapter;
    RecyclerView rv_sync_task_list;
    LinearLayout next;
    LinearLayout back;
    int i=0;
    TextView sub;
    List<SurveyQuestionPojo> lcs;
    List<SurveyQuestionPojo.SurveyAnswer> surveyAnswers;
    String requestBody;
    TextView ques;
    int a=0,b=0;
    ArrayList<String> gotoid=new ArrayList<>();
    int assessmentid;
    int workorderid;
    private LinkedHashMap<Integer,String> map=new LinkedHashMap<>();
    private LinkedHashMap<Integer,String> map1=new LinkedHashMap<>();
    Boolean yesno;
    String pqid;
    TextView tv_role;
    TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_sur);

        iv_back = findViewById(R.id.iv_site_location_back);
        ques = findViewById(R.id.ques);

        workorderid=getIntent().getIntExtra("workorderid",0);
        assessmentid=getIntent().getIntExtra("assessmentid",0);


        surveyTemplateId=getIntent().getIntExtra("surveyTemplateId",0);
        Log.v("wrk", String.valueOf(surveyTemplateId));

        tv_list_type = findViewById(R.id.unsigned_task);

        tv_list_type.setText("Survey");

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                map.clear();
               finish();
            }
        });

        tv_name = findViewById(R.id.tv_home_activity_name);
        tv_role = findViewById(R.id.tv_home_activity_role);
        tv_name.setText(Constants.loginPOJO.getProfile().getFIRSTNAME().toString()+" "+Constants.loginPOJO.getProfile().getLASTNAME().toString());
        tv_role.setText(Constants.loginPOJO.getProfile().getRoleNames().get(0));
        Log.v("wrk", String.valueOf(surveyTemplateId));
        next=findViewById(R.id.next);
        back=findViewById(R.id.back);
        sub=findViewById(R.id.sub);
        rv_sync_task_list=findViewById(R.id.rv_sync_task_list);
        rv_sync_task_list.setLayoutManager(new LinearLayoutManager(SurveyActivity.this));



        APIInterface apiInterface = APIHelper.getClient().create(APIInterface.class);

        Call<List<SurveyQuestionPojo>> listCall=apiInterface.surveytemplatequest("Bearer " + Constants.loginPOJO.getAccessToken(),surveyTemplateId);;
        listCall.enqueue(new Callback<List<SurveyQuestionPojo>>() {
            @Override
            public void onResponse(Call<List<SurveyQuestionPojo>> call, retrofit2.Response<List<SurveyQuestionPojo>> response) {
                Log.d("DataCheck",new Gson().toJson(response.body()));
                lcs = response.body();
                getlist(i);

            }
            @Override
            public void onFailure(Call<List<SurveyQuestionPojo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });

     /*   try {
            new GetApiCallback(SurveyActivity.this, UrlClass.SURVEY_URL+surveyTemplateId, new OnTaskCompleted<String>() {
                @Override
                public void onTaskCompleted(String response) {
                    Type collectionType = new TypeToken<List<SurveyPOJO>>(){}.getType();
                    lcs = (List<SurveyPOJO>) new Gson().fromJson( response , collectionType);
                    Log.v("respo", response);
                    getlist(i);

                }
            },true).execute();
        }catch (Exception e)
        {
            Log.d("ShowListExceptiion",e.toString());
        }*/


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=i-1;
                adapter.notifyDataSetChanged();
                getlist(i);
                if(i==0){
                    Log.v("size1", String.valueOf(i));
                    back.setVisibility(View.GONE);
                    i=0;
                    b=0;
                }
                if(i<lcs.size()-1){
                    Log.v("size1", String.valueOf(i));
                    sub.setTextColor(getResources().getColor(R.color.blue));
                    next.setBackground(getResources().getDrawable(R.drawable.circular_blue_desing));
                    sub.setText("Next");
                }
                if(i==lcs.size()-1){
                    Log.v("size1", String.valueOf(i));
                    sub.setTextColor(getResources().getColor(R.color.white));
                    next.setBackground(getResources().getDrawable(R.drawable.blue_desing));
                    sub.setText("Submit");
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                if(i< lcs.size()-1) {
                    if(map.size()>i){
                        i = i + 1;

                        back.setVisibility(View.VISIBLE);

                        adapter.notifyDataSetChanged();
                        getlist(i);
                        Log.v("size", String.valueOf( lcs.size()));
                        Log.v("size1", String.valueOf(i));
                        if(i== lcs.size()-1){
                            sub.setText("Submit");
                            next.setBackground(getResources().getDrawable(R.drawable.blue_desing));
                            sub.setTextColor(getResources().getColor(R.color.white));
                            Log.v("size1", String.valueOf(i));
                            Log.v("size", String.valueOf( lcs.size()));

                        }
                    }else {
                        if(lcs.get(i).getQUESTIONTYPEID()==3){
                            Toast.makeText(SurveyActivity.this, "Enter your answer", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(SurveyActivity.this, "Select one option", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else {

                    JSONObject jsonObject = new JSONObject();
                    sub.setText("Submit");
                    sub.setTextColor(getResources().getColor(R.color.white));
                    next.setBackground(getResources().getDrawable(R.drawable.blue_desing));

                    if (map.size() == lcs.size()) {

                        JSONArray jsonArray = new JSONArray();
                        for (Map.Entry<Integer, SubmitPojo> mmap : map3.entrySet()) {
                            JSONObject jsonObject1 = new JSONObject();
                            try {

                              /*  String srValue = mmap.getValue();
                                String aa = srValue.substring(mmap.getValue().indexOf(",") + 1);
                                String surveyAnswerID = Util.before(aa, ",");
                                String aaa = aa.substring(aa.indexOf(",") + 1);
                                String surveyID = Util.before(aaa, ",");
                                String aaaa = aaa.substring(aaa.indexOf(",") + 1);
                                String yes = Util.before(aaaa, ",");
                                String comment = Util.after(aaaa, ",");
*/

                                jsonObject1.put("QuestionId", mmap.getKey());
                                jsonObject1.put("ParentQuestionId", mmap.getValue().getParentQuestionId());
                                jsonObject1.put("FreeText",mmap.getValue().getFreeText());
                                jsonObject1.put("Answers",new JSONArray(mmap.getValue().getAnswer()));
                                jsonObject1.put("YesNo",mmap.getValue().getYes());
                                jsonObject1.put("AnswerComments",mmap.getValue().getComment());

                                jsonArray.put(jsonObject1);

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.v("exp",e.toString());
                            }
                        }



                        try {

                            jsonObject.put("SurveyId",lcs.get(0).getSURVEYID());
                            jsonObject.put("AssesmentId", assessmentid);
                            jsonObject.put("WorkOrerId", workorderid);
                            jsonObject.put("CompanyId",Constants.loginPOJO.getProfile().getCompanyId());
                            jsonObject.put("Comments","");
                            jsonObject.put("Questions", jsonArray);
                        }catch (Exception e){

                        }
                        Log.v("shalu1",map1.toString());

                        requestBody = jsonObject.toString();

                        Log.v("shalu", requestBody);
                        boolean isConnected = ConnectivityReceiver.isConnected();
                        if (isConnected == true) {
                            new SendData(SurveyActivity.this, jsonObject, UrlClass.SUBMIT_ANSWER, new OnTaskCompleted<String>() {
                                @Override
                                public void onTaskCompleted(String response) {
                                    opentThanksYesClickDialog("Survey Submitted Successfully");
                                }
                            }, true).execute();
                        } else {
                            SharedPreferences mPrefs = SurveyActivity.this.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
                            SharedPreferences.Editor prefsEditor = mPrefs.edit();
                            prefsEditor.putString("jsonarray", jsonArray.toString());
                            prefsEditor.commit();

                            opentThanksYesClickDialog("Network not available");
                            //   Toast.makeText(SurveyActivity.this,"Network Not Available",Toast.LENGTH_LONG).show();
                            //    startActivity(new Intent(SurveyActivity.this,HomeActivity.class));

                        }

                        Log.v("requestBody", requestBody);

                    } else {
                        if(lcs.get(i).getQUESTIONTYPEID()==3){
                            Toast.makeText(SurveyActivity.this, "Enter your answer", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(SurveyActivity.this, "Select one option", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                /*if(i<lcs.size()-1) {
                   // if (map.size() > i) {
                        String g;
                        if (lcs.get(i).getQUESTIONTYPEID() == 3) {
                            i = i + 1;
                            getlist(i);
                            back.setVisibility(View.VISIBLE);
                            adapter.notifyDataSetChanged();
                        } else {
                            if (SurveyAdapter.selected.equalsIgnoreCase("")) {

                            } else {

                                    for (int j = 0; j < lcs.get(i).getSurveyAnswers().size(); j++) {
                                        if (SurveyAdapter.selected.equalsIgnoreCase(lcs.get(i).getSurveyAnswers().get(j).getSURVEYANSWERTITLE())) {
                                            Object a = lcs.get(i).getSurveyAnswers().get(j).getGOTOQUESTIONID();
                                            if (a != null) {
                                                g = a.toString();
                                            } else {
                                                g = "";
                                            }

                                            Log.v("g", g);
                                            if (g.equalsIgnoreCase("")) {
                                                b = b + 1;
                                                getlist(b);
                                                i = b;
                                                back.setVisibility(View.VISIBLE);
                                                adapter.notifyDataSetChanged();


                                                for (int k = 0; k < gotoid.size(); k++) {
                                                    String m = gotoid.get(k);
                                                    Log.v("matchid", m);
                                                    float x = Float.parseFloat(m);
                                                    int y = (int) x;

                                                    if (y == lcs.get(i).getSURVEYQQUESTIONID()) {
                                                        Log.v("matchid1", m);
                                                        b = b + 1;
                                                        getlist(b);
                                                        i = b;
                                                        back.setVisibility(View.VISIBLE);
                                                        adapter.notifyDataSetChanged();

                                                    }
                                                }

                                            } else {
                                                gotoid.add(g);
                                                float x = Float.parseFloat(g);
                                                int y = (int) x;
                                                getlist(y - 1);
                                                i = y - 1;
                                                back.setVisibility(View.VISIBLE);
                                                adapter.notifyDataSetChanged();
                                            }
                                        }
                                    }


                            }
                        }

                        if (i == lcs.size() - 1) {
                            sub.setText("Submit");
                            next.setBackground(getResources().getDrawable(R.drawable.blue_desing));
                            sub.setTextColor(getResources().getColor(R.color.white));
                            Log.v("size1", String.valueOf(i));
                            Log.v("size", String.valueOf(lcs.size()));

                        }

//                    }else {
//                        Toast.makeText(SurveyActivity.this, "select one option" , Toast.LENGTH_SHORT).show();
//
//                    }

                }  else {
                    Log.v("size1", String.valueOf(i));

                    JSONObject jsonObject = new JSONObject();
                    sub.setText("Submit");
                    sub.setTextColor(getResources().getColor(R.color.white));
                    next.setBackground(getResources().getDrawable(R.drawable.blue_desing));

                    if (map.size() == lcs.size()) {

                        JSONArray jsonArray = new JSONArray();
                        for (Map.Entry<Integer, SubmitPojo> mmap : map3.entrySet()) {
                            JSONObject jsonObject1 = new JSONObject();
                            try {

                              *//*  String srValue = mmap.getValue();
                                String aa = srValue.substring(mmap.getValue().indexOf(",") + 1);
                                String surveyAnswerID = Util.before(aa, ",");
                                String aaa = aa.substring(aa.indexOf(",") + 1);
                                String surveyID = Util.before(aaa, ",");
                                String aaaa = aaa.substring(aaa.indexOf(",") + 1);
                                String yes = Util.before(aaaa, ",");
                                String comment = Util.after(aaaa, ",");
*//*

                                jsonObject1.put("QuestionId", mmap.getKey());
                                jsonObject1.put("ParentQuestionId", mmap.getValue().getParentQuestionId());
                                jsonObject1.put("FreeText",mmap.getValue().getFreetext());
                                jsonObject1.put("Answers",new JSONArray(mmap.getValue().getAnswers()));
                                jsonObject1.put("YesNo",mmap.getValue().getYesNo());
                                jsonObject1.put("AnswerComments",mmap.getValue().getAnswerComments());

                                jsonArray.put(jsonObject1);

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.v("exp",e.toString());
                            }
                        }



                        try {

                            jsonObject.put("SurveyId",lcs.get(0).getSURVEYID());
                            jsonObject.put("AssesmentId", assessmentid);
                            jsonObject.put("WorkOrerId", workorderid);
                            jsonObject.put("CompanyId",Constants.loginPOJO.getProfile().getCompanyId());
                            jsonObject.put("Comments","");
                            jsonObject.put("Questions", jsonArray);
                        }catch (Exception e){

                        }
                        Log.v("shalu1",map1.toString());

                        requestBody = jsonObject.toString();

                        Log.v("shalu", requestBody);
                        boolean isConnected = ConnectivityReceiver.isConnected();
                        if (isConnected == true) {
                            new SendData(SurveyActivity.this, jsonObject, UrlClass.SUBMIT_ANSWER, new OnTaskCompleted<String>() {
                                @Override
                                public void onTaskCompleted(String response) {
                                    opentThanksYesClickDialog("Survey Submitted Successfully");
                                }
                            }, true).execute();
                        } else {
                            SharedPreferences mPrefs = SurveyActivity.this.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
                            SharedPreferences.Editor prefsEditor = mPrefs.edit();
                            prefsEditor.putString("jsonarray", jsonArray.toString());
                            prefsEditor.commit();

                            opentThanksYesClickDialog("Network not available");
                            //   Toast.makeText(SurveyActivity.this,"Network Not Available",Toast.LENGTH_LONG).show();
                            //    startActivity(new Intent(SurveyActivity.this,HomeActivity.class));

                        }

                        Log.v("requestBody", requestBody);

                    } else {
                        Toast.makeText(SurveyActivity.this, "select one option" , Toast.LENGTH_SHORT).show();

                    }
                }*/
            }
        });



    }

    public void opentThanksYesClickDialog(String message)
    {
        final Dialog dialog=new Dialog(SurveyActivity.this);
        dialog.setContentView(R.layout.inflate_home_thanks_yes_click);
        TextView tv_type=dialog.findViewById(R.id.tv_alert_type);
        TextView tv_ok=dialog.findViewById(R.id.tv_ok_thanks);
        tv_type.setText("Alert");
        TextView tv_message=dialog.findViewById(R.id.tv_message_thanks);
        tv_message.setText(message);

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Constants.ACTIVITY_NAME = Constants.HOME_ACTIVITY;
                startActivity(new Intent(SurveyActivity.this,HomeActivity.class));
            }
        });
    }

    private void getlist(int p) {
        try {
            surveyAnswers = new ArrayList<>();
            for (int a = 0; a < lcs.size(); a++) {
                surveyAnswers = lcs.get(p).getSurveyAnswers();
            }
            ques.setText(lcs.get(p).getQUESTIONTITLE());
            adapter = new SurveyAdapter(SurveyActivity.this, surveyAnswers, p, this, map, lcs,map1);
            rv_sync_task_list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }catch (Exception e){

        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void itemselect(Integer questionID, Integer answerID, Object gotoid, Integer surveyID, String SurveyorIDt) {
        map.put(questionID,answerID+","+gotoid+","+surveyID+","+SurveyorIDt);
        Log.v("SELECt",map.toString());
    }

    @Override
    public void itemselect1(Integer questionID, Object ParentQuestionId, String FreeText, ArrayList<Integer> answer, String yes, String comment) {
        map1.put(questionID,ParentQuestionId+","+FreeText+","+answer+","+yes+","+comment);
        Log.v("SELECt1",map1.toString());
    }


    @Override
    public void itemUnSelect(int questionID) {

    }


}

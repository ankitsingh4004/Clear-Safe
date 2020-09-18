package com.workorder.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.workorder.app.R;
import com.workorder.app.adapter.SurveyAdapter;
import com.workorder.app.adapter.SurveyTempAdapter;
import com.workorder.app.api.APIHelper;
import com.workorder.app.api.APIInterface;
import com.workorder.app.pojo.survey.SurveyPOJO;
import com.workorder.app.pojo.survey.SurveyTempPOJO;
import com.workorder.app.pojo.survey.SurveyTemplatePojo;
import com.workorder.app.util.Constants;
import com.workorder.app.util.UrlClass;
import com.workorder.app.webservicecallback.GetApiCallback;
import com.workorder.app.webservicecallback.OnTaskCompleted;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveyTemplate extends FragmentActivity {
    int assessmentid;
    int workorderid;
    RecyclerView recyclerView;
    SurveyTempAdapter adapter;
    List<SurveyTemplatePojo> lcs;
    ImageView iv_back;
    TextView tv_list_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template);
        iv_back = findViewById(R.id.iv_site_location_back);


        workorderid=getIntent().getIntExtra("workorderid",0);
        assessmentid=getIntent().getIntExtra("assessmentid",0);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                // finish();
            }
        });
        tv_list_type = findViewById(R.id.unsigned_task);

        tv_list_type.setText("Survey List");


        recyclerView= findViewById(R.id.rv_sync_trask_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(SurveyTemplate.this));

        Daata();


    }

    public void Daata() {

        APIInterface apiInterface = APIHelper.getClient().create(APIInterface.class);

        Call<List<SurveyTemplatePojo>>listCall=apiInterface.surveytemplate("Bearer " + Constants.loginPOJO.getAccessToken());;
        listCall.enqueue(new Callback<List<SurveyTemplatePojo>>() {
            @Override
            public void onResponse(Call<List<SurveyTemplatePojo>> call, Response<List<SurveyTemplatePojo>> response) {
                Log.d("DataCheck",new Gson().toJson(response.body()));


                List<SurveyTemplatePojo> data = new ArrayList<>();
                data = response.body();

                adapter=new SurveyTempAdapter(SurveyTemplate.this,data,workorderid,assessmentid);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<SurveyTemplatePojo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SurveyTemplate.this,HomeActivity.class));
    }
}

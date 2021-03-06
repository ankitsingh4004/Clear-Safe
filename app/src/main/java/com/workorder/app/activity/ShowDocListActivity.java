package com.workorder.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.workorder.app.R;
import com.workorder.app.adapter.SWMSAdapter;
import com.workorder.app.pojo.assesment.AssesmentHomePOJO;
import com.workorder.app.pojo.docPOJO.AttachementPOJO;
import com.workorder.app.pojo.docPOJO.DocListPOJO;
import com.workorder.app.util.Constants;
import com.workorder.app.util.UrlClass;
import com.workorder.app.webservicecallback.GetApiCallback;
import com.workorder.app.webservicecallback.OnTaskCompleted;

import java.util.ArrayList;
import java.util.List;

public class ShowDocListActivity extends AppCompatActivity {
   ImageView iv_back;
   RecyclerView rv_show_list;
   SWMSAdapter adapter;
   AssesmentHomePOJO assesmentHomePOJO;
   List<AttachementPOJO> attachementPOJOS=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doc_list);

        iv_back=findViewById(R.id.iv_show_doc_list_back);
        rv_show_list=findViewById(R.id.rv_show_doc_list);
        rv_show_list.setHasFixedSize(true);
        rv_show_list.setLayoutManager(new LinearLayoutManager(this));
        try {
            assesmentHomePOJO = (AssesmentHomePOJO) getIntent().getSerializableExtra("AssesmentPOJO");
            if (assesmentHomePOJO!=null){
                try {
                    Constants.assesmentHomePOJO=assesmentHomePOJO;
                    new GetApiCallback(this, UrlClass.GET_TASK_API_URL + assesmentHomePOJO.getAssesmentID(), new OnTaskCompleted<String>() {
                        @Override
                        public void onTaskCompleted(String response) {
                            Constants.docListPOJO=new Gson().fromJson(response,DocListPOJO.class);

                            attachementPOJOS=Constants.docListPOJO.getAttachementPOJOs();
                         //   adapter=new SWMSAdapter(ShowDocListActivity.this,attachementPOJOS, Constants.workOrderPOJO.getWorkOrderNo());
                            rv_show_list.setAdapter(adapter);
                            Log.d("AttachementSize",attachementPOJOS.size()+"");
                        }
                    },true).execute();
                }catch (Exception e)
                {
                    Log.d("ShowListExceptiion",e.toString());
                }
            }


        }catch (Exception e)
        {
            Log.d("DocException",e.toString());
        }


     iv_back.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             onBackPressed();
         }
     });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

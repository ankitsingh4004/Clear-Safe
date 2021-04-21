package com.workorder.app.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.workorder.app.ConnectivityReceiver;
import com.workorder.app.R;
import com.workorder.app.pojo.docPOJO.GetSwmsTemplate;
import com.workorder.app.util.Constants;
import com.workorder.app.util.Signature;
import com.workorder.app.util.UrlClass;
import com.workorder.app.webservicecallback.OnTaskCompleted;
import com.workorder.app.webservicecallback.SendData;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignatureInstActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    Signature signature;
    Button clearButton, submitButton;
    //private List<AsigneeResponseModel> responseModel;
  //  private SignResponseModel signResponse;
    Integer taskId = 0;
    public String imageUrl;
    Toolbar toolbar;
   // AsigneeResponseModel asigneeResponseModel = null;
    ProgressDialog dialog;
    String name;
    TextView tv_title;
    ImageView iv_back;
    CheckBox checkBox;
    CheckBox ch_observer;
    CheckBox ch_worker;
    GetSwmsTemplate.Attachement attachementPOJO;
    final Context context = this;
    int assesmenttemplateid;
    String documentname;
    String versionno;
    int assesmentempid;
    int assesmentid;
    TextView sms;
    int v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        tv_title=findViewById(R.id.tv_activity_name);
        iv_back=findViewById(R.id.iv_back);
        sms=findViewById(R.id.sms);
        documentname=getIntent().getStringExtra("documentname");
        versionno=getIntent().getStringExtra("versionno");
        try{
            v=Integer.parseInt(versionno);
        }catch (Exception e){

        }

     //   tv_title.setText(documentname);

        sms.setText("Please sign to confirm that you have read the attached document(s). If you have not read the attached document(s) then please close this panel, read the document(s) and then sign this panel by selecting the option SIGN. By signing you are agreeing and verifying that you have read all the attached document(s).");


   /*     if(documentname.equalsIgnoreCase("")){
            sms.setText("Please enter your signature in the space below.");
        }else {
            sms.setText("Please sign to confirm that you have read the attached current instructions.If you have not read the attached document then please close this panel, read the file and then sign this panel by selecting the option SIGN.");
        }*/
     //   assesmenttemplateid= getIntent().getIntExtra("assesmenttemplateid",0);
      //  assesmentempid= getIntent().getIntExtra("assesmentempid",0);
        assesmentid= getIntent().getIntExtra("assesmentid",0);

     //   attachementPOJO= (GetSwmsTemplate.Attachement) getIntent().getSerializableExtra("AssesmentPOJO");
        init();

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

 /*   @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
*/
    public void init() {
        checkBox = findViewById(R.id.ch_signature);
        ch_observer=findViewById(R.id.ch_sign_as_observer);
        ch_worker=findViewById(R.id.ch_sign_as_worker);
        signature = findViewById(R.id.signature);
        clearButton = findViewById(R.id.btn_clear_signature);
        submitButton = findViewById(R.id.btn_submit_signature);
        ch_worker.setChecked(true);

        ch_worker.setOnCheckedChangeListener(this);
        ch_observer.setOnCheckedChangeListener(this);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signature.clear();
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    imageUrl =  signature.getBase64();
                    Log.v("shalu",signature.getBase64());
                    if((ch_worker.isChecked() || ch_observer.isChecked()))
                    {
                        if (imageUrl.length()>5500) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String dateTime = dateFormat.format(new Date());
                            JSONObject jsonObject=new JSONObject();

                            jsonObject.put("AssesmentId", assesmentid);
                            jsonObject.put("IsSignedStatus",1);
                            jsonObject.put("SingNatureDate",dateTime);
                            jsonObject.put("FileName",documentname);
                            jsonObject.put("VersionNo",0);
                            jsonObject.put("imageUrl",imageUrl);

                            Log.v("json",jsonObject.toString());
                            boolean isConnected = ConnectivityReceiver.isConnected();

                            if(isConnected ==true) {
                                new SendData(SignatureInstActivity.this, jsonObject, UrlClass.UPLOAD_SIGN_DOCUMENT, new OnTaskCompleted<String>() {
                                    @Override
                                    public void onTaskCompleted(String response) {
                                        Log.d("Post Response", response);
                                            try {
                                            JSONObject object = new JSONObject(response);
                                            Boolean result = object.getBoolean("status");
                                            if (result) {
                                                signature.clear();
                                                Toast.makeText(context, "Document has been signed and submitted. No additional change can now be made.", Toast.LENGTH_SHORT).show();
                                                Constants.ACTIVITY_NAME = Constants.SHOW_DOCUMENT_ACTIVITY;
                                                startActivity(new Intent(SignatureInstActivity.this, HomeActivity.class));
                                                finish();
                                            } else {
                                                //Toast.makeText(context, "Signature Uploaded Failed...", Toast.LENGTH_SHORT).show();
                                                Toast.makeText(context, "" + object.getString("msg"), Toast.LENGTH_SHORT).show();

                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(context, "Document has been signed and submitted. No additional change can now be made.", Toast.LENGTH_SHORT).show();
                                            Constants.ACTIVITY_NAME = Constants.SHOW_DOCUMENT_ACTIVITY;
                                            startActivity(new Intent(SignatureInstActivity.this, HomeActivity.class));
                                            finish();
                                            Log.d("PostSignature", e.toString());
                                        }

                                    }
                                }, true).execute();
                                Log.d("ImageUrl", imageUrl);
                            }else {
                                SharedPreferences mPrefs = getApplicationContext().getSharedPreferences("PREFS_NAME1",Context.MODE_PRIVATE);
                                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                                prefsEditor.putString("jsonobject", jsonObject.toString());
                                prefsEditor.commit();

                                Toast.makeText(getApplicationContext(),"Network Not Available",Toast.LENGTH_LONG).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(SignatureInstActivity.this, "Please sign to submit your signature.", Toast.LENGTH_SHORT).show();
                        }
                    }else if (!ch_worker.isChecked() || !ch_observer.isChecked())
                    {
                        Toast.makeText(SignatureInstActivity.this, "Please select the signature type.", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {                                                                       
                    e.printStackTrace();
                    Log.d("SignatureException",e.toString());
                }
            }
        });


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId())
        {
            case R.id.ch_sign_as_observer:
                if (ch_observer.isChecked())
                {
                    ch_worker.setChecked(false);
                }
                     break;
            case R.id.ch_sign_as_worker:
                if (ch_worker.isChecked())
                {
                    ch_observer.setChecked(false);
                }
                break;
        }

    }
  /*  public void showPopUp()
    {
        final Dialog dialog1 = new Dialog(context);
        dialog1.setContentView(R.layout.already_assignee_fragment);
        dialog1.setTitle("Alert");
        Button add=dialog1.findViewById(R.id.add_other);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AssigneeListDialogFragment fragment = new AssigneeListDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("taskId", taskId);
                fragment.setArguments(bundle);
                fragment.setListner(SignatureActivity.this);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragment.show(fragmentTransaction, "AssigneeListDialogFragment");
                dialog1.dismiss();
            }
        });
        Button back=dialog1.findViewById(R.id.back_task_list);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignatureActivity.this,HomeActivity.class));
                dialog1.dismiss();
            }
        });
        dialog1.show();
    }*/





}

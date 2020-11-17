package com.workorder.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.workorder.app.R;
import com.workorder.app.util.UrlClass;
import com.workorder.app.webservicecallback.GetApiCall;
import com.workorder.app.webservicecallback.GetApiCallback;
import com.workorder.app.webservicecallback.GetCallback;
import com.workorder.app.webservicecallback.OnTaskCompleted;
import com.workorder.app.webservicecallback.SendData;
import com.workorder.app.webservicecallback.SendData1;

import org.json.JSONObject;

public class forgotPassword extends AppCompatActivity {

    EditText et_login_user_id;
    Button submit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        et_login_user_id=findViewById(R.id.et_login_user_id);
        submit=findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_login_user_id.getText().toString().equals("")) {
                    et_login_user_id.requestFocus();
                    et_login_user_id.setError("Please enter email address");
                } else {
                    Log.v("response", UrlClass.Forgot_Password+et_login_user_id.getText().toString());
                    try {
                        new GetApiCall(forgotPassword.this,  UrlClass.Forgot_Password+et_login_user_id.getText().toString(), new OnTaskCompleted<String>() {
                            @Override
                            public void onTaskCompleted(String response) {
                                try {
                                    Log.v("response", response);
                                    JSONObject jsonObject=new JSONObject(response);
                                    Intent i=new Intent(forgotPassword.this,OtpVerify.class);
                                    i.putExtra("email",et_login_user_id.getText().toString());
                                    i.putExtra("code",jsonObject.getString("code"));
                                    i.putExtra("otp",jsonObject.getString("otp"));
                                    startActivity(i);
                                } catch (Exception e) {
                                    Log.d("ResponseException", e.toString());
                                }
                            }
                        }, true).execute();

                    } catch (Exception e) {
                        // progressDialog.dismiss();
                        Log.d("LoginExceptio", e.toString());
                    }
                }
            }
        });
    }
}

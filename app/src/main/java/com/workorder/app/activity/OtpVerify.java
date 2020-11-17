package com.workorder.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.workorder.app.R;
import com.workorder.app.util.UrlClass;
import com.workorder.app.webservicecallback.GetApiCall;
import com.workorder.app.webservicecallback.OnTaskCompleted;

public class OtpVerify extends AppCompatActivity {
    String email;
    String code1;
    EditText otp;
    Button submit;
    String otptext;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otp=findViewById(R.id.otp);
        submit=findViewById(R.id.submit);

        email=getIntent().getStringExtra("email");
        code1=getIntent().getStringExtra("code");
        otptext=getIntent().getStringExtra("otp");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otp.getText().toString().equals(otptext)) {
                    Intent i=new Intent(OtpVerify.this,ResetPassword.class);
                    i.putExtra("email",email);
                    i.putExtra("code",code1);
                    startActivity(i);
                } else {
                    Toast.makeText(OtpVerify.this,"Required Otp is wrong",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

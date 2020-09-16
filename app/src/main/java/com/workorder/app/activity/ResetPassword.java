package com.workorder.app.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.workorder.app.R;
import com.workorder.app.util.UrlClass;
import com.workorder.app.webservicecallback.GetCallback;
import com.workorder.app.webservicecallback.OnTaskCompleted;
import com.workorder.app.webservicecallback.SendData;
import com.workorder.app.webservicecallback.SendData1;

import org.json.JSONObject;

public class ResetPassword extends AppCompatActivity {
    EditText newpass;
    EditText confirmpass;
    EditText code;
    Button submit;
    String email;
    String code1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset__password);

        newpass=findViewById(R.id.newpass);
        confirmpass=findViewById(R.id.confirmpass);
        code=findViewById(R.id.code);
        submit=findViewById(R.id.submit);

        email=getIntent().getStringExtra("email");
        code1=getIntent().getStringExtra("code");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean hasNonAlpha = newpass.getText().toString().matches("^.*[^a-zA-Z0-9 ].*$");
                if (newpass.getText().toString().equals("")) {
                    newpass.requestFocus();
                    newpass.setError("Please enter new password");
                }else if (confirmpass.getText().toString().equals("")) {
                    confirmpass.requestFocus();
                    confirmpass.setError("Please enter confirm password");
                }else if (!confirmpass.getText().toString().equalsIgnoreCase(newpass.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"Please enter new password and confirm password same.",Toast.LENGTH_LONG).show();
                }else if(!hasNonAlpha){
                    Toast.makeText(getApplicationContext(),"Passwords must have at least one non alphanumeric character.",Toast.LENGTH_LONG).show();
                } else {

                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("Email", email);
                        jsonObject.put("Password", newpass.getText().toString().trim());
                        jsonObject.put("ConfirmPassword", confirmpass.getText().toString().trim());
                        jsonObject.put("Code", code1);
                        Log.v("json",jsonObject.toString());

                        Log.v("json",jsonObject.toString());
                        new SendData1(getApplicationContext(), jsonObject, UrlClass.Reset_Password, new OnTaskCompleted<String>() {
                            @Override
                            public void onTaskCompleted(String response) {
                                try {
                                    opentThanksYesClickDialog1("Successfully Password is Reset.");
                                    Log.v("response",response);
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

    public void opentThanksYesClickDialog1(String message) {
        final Dialog dialog = new Dialog(ResetPassword.this);
        dialog.setContentView(R.layout.inflate_home_thanks_yes_click);
        TextView tv_type = dialog.findViewById(R.id.tv_alert_type);
        TextView tv_ok = dialog.findViewById(R.id.tv_ok_thanks);
        tv_type.setText("Alert");
        TextView tv_message = dialog.findViewById(R.id.tv_message_thanks);
        tv_message.setText(message);

        dialog.show();
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResetPassword.this,LoginActivity.class));
                dialog.dismiss();



            }
        });
    }


}

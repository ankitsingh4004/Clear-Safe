package com.workorder.app.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.workorder.app.R;
import com.workorder.app.util.Constants;
import com.workorder.app.util.UrlClass;
import com.workorder.app.webservicecallback.OnTaskCompleted;
import com.workorder.app.webservicecallback.SendData;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePassword extends Fragment {

    EditText oldpass;
    EditText newPass;
    EditText confirmpass;
    Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_change__password, container, false);

        oldpass=rootView.findViewById(R.id.oldpass);
        newPass=rootView.findViewById(R.id.newPass);
        confirmpass=rootView.findViewById(R.id.confirmpass);
        submit=rootView.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changepass();
            }
        });

        return rootView;

    }



    private void changepass() {
        boolean hasNonAlpha = newPass.getText().toString().matches("^.*[^a-zA-Z0-9 ].*$");
        if (oldpass.getText().toString().equals("")) {
            oldpass.requestFocus();
            oldpass.setError("Please enter old password");
        } else if (newPass.getText().toString().equals("")) {
            newPass.requestFocus();
            newPass.setError("Please enter new password");
        }else if (confirmpass.getText().toString().equals("")) {
            confirmpass.requestFocus();
            confirmpass.setError("Please enter confirm password");
        }else if (!confirmpass.getText().toString().equalsIgnoreCase(newPass.getText().toString())) {
            Toast.makeText(getContext(),"Please enter new password and confirm password same.",Toast.LENGTH_LONG).show();
        }else if(!hasNonAlpha){
            Toast.makeText(getContext(),"Passwords must have atleast one non alphanumeric character.",Toast.LENGTH_LONG).show();
        } else {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("OldPassword", oldpass.getText().toString().trim());
                jsonObject.put("NewPassword", newPass.getText().toString().trim());

                Log.v("json",jsonObject.toString());
                new SendData(getContext(), jsonObject, UrlClass.Change_Password, new OnTaskCompleted<String>() {
                    @Override
                    public void onTaskCompleted(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            Boolean result = object.getBoolean("status");
                            if (result) {
                                opentThanksYesClickDialog1("Password Changed.");
                            } else {
                                //Toast.makeText(context, "Signature Uploaded Failed...", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getContext(), "" + object.getString("msg"), Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {

                        }

                    }
                }, true).execute();

            } catch (Exception e) {
                // progressDialog.dismiss();
                Log.d("LoginExceptio", e.toString());
            }

        }
    }


    public void opentThanksYesClickDialog1(String message) {
        final Dialog dialog = new Dialog(getContext());
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
                dialog.dismiss();

                startActivity(new Intent(getContext(),HomeActivity.class));

            }
        });
    }

}

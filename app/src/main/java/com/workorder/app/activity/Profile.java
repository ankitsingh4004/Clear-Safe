package com.workorder.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.workorder.app.R;
import com.workorder.app.util.Constants;

public class Profile  extends Fragment {
    EditText name;
    EditText email;
    EditText phone;
    EditText company;
    EditText city;
    EditText country;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_profile, container, false);
        name=rootView.findViewById(R.id.name);
        email=rootView.findViewById(R.id.email);
        phone=rootView.findViewById(R.id.phone);
        company=rootView.findViewById(R.id.company);
        city=rootView.findViewById(R.id.city);
        country=rootView.findViewById(R.id.country);

        name.setText(Constants.loginPOJO.getProfile().getFIRSTNAME().toString()+" "+Constants.loginPOJO.getProfile().getLASTNAME().toString());
        email.setText(Constants.loginPOJO.getProfile().getEmail().toString());
        phone.setText(Constants.loginPOJO.getProfile().getPERSONALMOBILE().toString());
        company.setText(Constants.loginPOJO.getProfile().getPERSONALADDRESS1().toString());
        city.setText(Constants.loginPOJO.getProfile().getBUSINESSADDRESS1().toString());
      //  country.setText(Constants.loginPOJO.getProfile().getPERSONALCOUNTRYID().toString());

        return  rootView;
    }
}

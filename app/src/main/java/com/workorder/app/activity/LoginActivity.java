                                                                                                                                                                                                                                             package com.workorder.app.activity;
import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.workorder.app.R;
import com.workorder.app.pojo.LoginPOJO;
import com.workorder.app.util.Constants;
import com.workorder.app.util.UrlClass;
import com.workorder.app.util.UtilityFunction;
import com.workorder.app.webservicecallback.GooglePlayStoreAppVersionNameLoader;
import com.workorder.app.webservicecallback.OnTaskCompleted;
import com.workorder.app.webservicecallback.SendData;
import com.workorder.app.webservicecallback.SendData1;
import com.workorder.app.webservicecallback.VersionCheckListner;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements VersionCheckListner {
    Button submitButton;
    EditText userNameEditText;
    EditText passwordEditText;
    ProgressDialog progressDialog;
    // PreferenceManager preferenceManager;
    //PreferenceManagerWorkOrder preferenceManagerWorkOrder;
    String username1="",userrole="",userid="";

    String user_id;
    public static int value;
    TextView forgotpassword;
    // List<LoginResponseModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //   progressDialog = new ProgressDialog(this);
        //  preferenceManager = PreferenceManager.getInstance(getApplicationContext());
        submitButton = findViewById(R.id.btn_login);
        forgotpassword = findViewById(R.id.forgotpassword);

        userNameEditText = findViewById(R.id.et_login_user_id);
        passwordEditText = findViewById(R.id.et_login_user_password);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.navigationbar));
        }
        new GooglePlayStoreAppVersionNameLoader(getApplicationContext(), this).execute();

        forgotpassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // callClearMobileLogin();
                startActivity(new Intent(LoginActivity.this,forgotPassword.class));

            }
        });

    /*    if (preferenceManager.getIsLogin()) {
            userNameEditText.setText(preferenceManager.getUsername());
        }*/
        //    "demobrisullcont@gmail.com"
        //   "P@ssword123"

       // userNameEditText.setText("demobrisullfm@gmail.com");   //"worker@gmail.com"   //********  demobrisullcont@gmail.com   ******/   //demobrisullcont@gmail.comworker
       // passwordEditText.setText("P@ssword123");  //    P@ssword123

        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // callClearMobileLogin();
                callLogin();

            }
        });
        // TextView frgtPswrd = findViewById(R.id.txt_frgt_pswrd);
     /*   frgtPswrd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgotPasswordFragment fragment = new ForgotPasswordFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, null).addToBackStack(null).commit();
            }
        });*/
     checkPermissions();
    }

    /*   public void callClearMobileLogin() {

           if (userNameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")) {
               if (userNameEditText.getText().toString().matches("")) {
                   Toast.makeText(this, "Please Enter Your User Name", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
               }
           } else {*/
       /*     progressDialog.show();
            progressDialog.setMessage("Logging in...");*/
           /* LoginRequestModel loginRequestModel = new LoginRequestModel();
            String musername = userNameEditText.getText().toString().trim();
            String mpassword = passwordEditText.getText().toString().trim();
            loginRequestModel.setUserName(musername);
            loginRequestModel.setPassword(mpassword);
            loginRequestModel.setGrant_type("password");*/
//            loginRequestModel.setUserName("jyotish01@hotmail.com");
//            loginRequestModel.setPassword("P@ssword123");
         /*   if (Utility.isNetworkAvailable(this)) {

                ApiServices apiServices = Network.getInstance().getApiServices();
               final Call<LoginResponseModel> loginResponseCall =
                 apiServices.postLoginDetails("application/x-www-form-urlencoded", userName, password,"password");

            loginResponseCall.enqueue(new Callback<LoginResponseModel>() {
                    @Override
                    public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                        try {
                            // TODO NULL CHECK OF RESPONSE
                            Log.d("StatusCode", String.valueOf(response.code()));
                            Log.d("Second Response",response.toString());
                                if (response.body() != null) {

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    ConstantsClear.responseModel=response.body();


                              //  preferenceManager.setSessionToken(response.body().access_token);
                                Log.d("Token", response.body().access_token);
                                Log.d("User_Id", response.body().userId);
                               // Log.d("Token", preferenceManager.getSessionToken());
                          //     preferenceManager.setKeyUserId(response.body().userId);
                             //   preferenceManager.setUsername(response.body().username);
                                 user_id=response.body().getUserId();
                                intent.putExtra("user_id",user_id);
                                preferenceManager.setUsername(userNameEditText.getText().toString());
                                preferenceManager.setPassword(passwordEditText.getText().toString());
                                preferenceManager.setKeyRole(response.body().roleArray.get(0));

                              //  preferenceManager.setIsLogin(true);
                                startActivity(intent);
                                finish();

                                progressDialog.dismiss();
                            } else {
                                Toast.makeText(getApplicationContext(),"Invalid Username or Password", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                            }


                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, "Invalid Username or Password"+e.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("ErrorException",e.getMessage());
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                        t.printStackTrace();
                        System.out.println(t.getMessage() + t.getLocalizedMessage());
                        Log.d("SecondError",t.getMessage());
                        Toast.makeText(getApplicationContext(), "Invalid Username or Password" , Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
           /* } else {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "NetworkWorkOrder is not available", Toast.LENGTH_SHORT).show();
            }

    }*/


    String userName = "";
    String password = "";

    public void callLogin() {
        if (UtilityFunction.isNetworkAvailable(this)) {
            if (userNameEditText.getText().toString().equals("")) {
                userNameEditText.requestFocus();
                userNameEditText.setError("Please enter Username");
            } else if (passwordEditText.getText().toString().equals("")) {
                passwordEditText.requestFocus();
                passwordEditText.setError("Please enter Password");
            } else {
                // progressDialog.setMessage("Authenticating...");
                //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                // progressDialog.setCancelable(false);
                // progressDialog.show();
                //   LoginRequestWorkOrderModel loginRequestModel=new LoginRequestWorkOrderModel();
                userName = userNameEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username", userName);
                    jsonObject.put("Password", password);
                   // jsonObject.put("username", "info@uveoustech.com");
                   //  jsonObject.put("Password", "Uveous@#$056");
                  //  jsonObject.put("username", "radhey2784@gmail.com");
                  // jsonObject.put("Password", "Test@123");
                  // jsonObject.put("username", "rk25412@gmail.com");
                  // jsonObject.put("Password", "123");
                 //   jsonObject.put("username", "bhaviktank28@gmail.com");
                   //  jsonObject.put("Password", "P@ssword123");
                  // jsonObject.put("username", "faishalkhan@gmail.com");
                   // jsonObject.put("Password", "123");
                   // jsonObject.put("username", "testemp01@abc.com");
                 //   jsonObject.put("Password", "P@ssword123");

                  //  jsonObject.put("grant_type","password");
                   // UrlClass.BASE_URL="http://109.228.49.117:8095/api/";
                    new SendData1(this, jsonObject, UrlClass.LOGIN_URL, new OnTaskCompleted<String>() {
                        @Override
                        public void onTaskCompleted(String response) {
                            try {
                                //   progressDialog.dismiss();
                                Log.d("LoginResponse", response);

                                Constants.loginPOJO = new Gson().fromJson(response, LoginPOJO.class);
                             //   PreferenceManagerWorkOrder.SaveLoginData(LoginActivity.this,Constants.loginPOJO);

                                value=0;

                            /*    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);*/
                                setConditionToLogin(response);

                              /*  if (Constants.loginPOJO.getBaseUrl()!=null)
                                {
                                 //   UrlClass.BASE_URL=Constants.loginPOJO.getBaseUrl();
                                 //   RetrofitManagerWorkOrder.BASE_URL=Constants.loginPOJO.getBaseUrl();

                                    Log.d("BaseUrlRespons2",UrlClass.BASE_URL);

                                    setConditionToLogin(response);
                                }else {
                                    setConditionToLogin(response);
                                }
                                */                              // progressDialog.dismiss();
                            } catch (Exception e) {
                                //   progressDialog.dismiss();
                                //progressDialog.dismiss();
                                Log.d("ResponseException", e.toString());
                            }
                        }
                    }, true).execute();

                } catch (Exception e) {
                    // progressDialog.dismiss();
                    Log.d("LoginException", e.toString());
                }

            }
        } else {
            //  progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "NetworkWorkOrder is not available", Toast.LENGTH_LONG).show();
        }
    }
    public static final int MULTIPLE_PERMISSIONS = 10; // code you want.
    String[] permissions= new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.VIBRATE};

    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(this,p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );
            return false;
        }
        return true;
    }


    public void setConditionToLogin(String response)
    {
        Constants.USER_ID = userName.trim();
        Log.d("Role", String.valueOf(Constants.loginPOJO.getProfile().getRoleNames()));
        Log.d("CompanyId", String.valueOf(Constants.loginPOJO.getProfile().getCompanyId()));
        UrlClass.ROLE_TYPE = String.valueOf(Constants.loginPOJO.getProfile().getRoleNames());
        UrlClass.COMPANYID = String.valueOf(Constants.loginPOJO.getProfile().getCompanyId());
     //   if (Constants.loginPOJO.getProfile().getRoleNames().get(0).equalsIgnoreCase("Employee")) {
            Constants.ACTIVITY_NAME =Constants.HOME_ACTIVITY;
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
     //   }


    }
    public void showUpdateDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);

        alertDialogBuilder.setTitle(LoginActivity.this.getString(R.string.app_name));
        alertDialogBuilder.setMessage("Your application update is available.");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Update Now", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                LoginActivity.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                dialog.cancel();
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               /* if (isForceUpdate) {
                    finish();
                }*/
                dialog.dismiss();
            }
        });
        alertDialogBuilder.show();
    }


    @Override
    public void onGetResponse(boolean isUpdateAvailable) {
        Log.d("ResultAPPMAIN", String.valueOf(isUpdateAvailable));
        if (isUpdateAvailable) {
            showUpdateDialog();
        }
    }


}

/*

  loginRequestModel.setUserName(userName);
           loginRequestModel.setPassword(password);
      /*     if (UtilityWorkOrder.isNetworkAvailable(this)) {
ApiServicesWorkOrder apiServicesWorkOrder = NetworkWorkOrder.getInstance().getApiServicesWorkOrder();
    final Call<LoginResponseWorkOrderModel> loginResponseCall =
            apiServicesWorkOrder.postLogin("application/json",loginRequestModel);
               loginResponseCall.enqueue(new Callback<LoginResponseWorkOrderModel>() {
@Override
public void onResponse(Call<LoginResponseWorkOrderModel> call, Response<LoginResponseWorkOrderModel> response) {
        try {
        int codeStatus=response.code();
        Log.d("statusCode", String.valueOf(response.code()));
        // TODO NULL CHECK OF RESPONSE
        if (codeStatus==401)
        {
        Log.d("First Response",response.toString());

        }else {
        if (response.isSuccessful()) {
        Log.d("LoginResponse1",response.toString());

        // ConstantsWorkOrder.loginResponse=response.body();
        Constants.loginResponse=response.body();
        LoginResponseWorkOrderModel responseWorkOrderModel=response.body();

        userrole=response.body().userrole;

        if(userrole!=null)
        {
        //  Log.v("Response",response.body().toString());

        Log.d("UserRole",userrole);
        username1=response.body().username;
        userid= String.valueOf(response.body().userid);

        UrlClass.ROLE_TYPE=userrole ;
        UrlClass.COMPANYID= String.valueOf(response.body().personcompanyid);
        if (userrole.equals("Client")) {
        Bundle bundle=new Bundle();
        bundle.putString("Id", String.valueOf(response.body().personcompanyid));
        Intent intent = new Intent(LoginActivity.this, MainActivityWorkOrder.class);
        // intent.putExtras(bundle);

        intent.putExtra("WorkOrderLogin",responseWorkOrderModel);
        startActivity(intent);
        progressDialog.dismiss();
        }else if (responseWorkOrderModel.getUserrole().equals("BBM"))
        {
        Constants.loginResponse.setUserrole("Finance Manager");

        Intent intent = new Intent(LoginActivity.this, MainActivityWorkOrder.class);
        // intent.putExtras(bundle);

        intent.putExtra("WorkOrderLogin",responseWorkOrderModel);
        startActivity(intent);
        progressDialog.dismiss();

        }else if (userrole.equals("Landlord")){
        Bundle bundle=new Bundle();
        //  bundle.putString("Id", preferenceManagerWorkOrder.getKey_Person_Company_Id());
        Intent intent = new Intent(LoginActivity.this, MainActivityWorkOrder.class);
        //intent.putExtras(bundle);
        startActivity(intent);
        progressDialog.dismiss();
        }else if (userrole.equals("Worker"))
        {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();


        }else
        {
        //Toast.makeText(LoginActivity.this, "UserName : "+preferenceManagerWorkOrder.getKey_User_Name(), Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        }

                                    /*  if(response.body().userrole.trim().equals("BBM")) {
                                          preferenceManagerWorkOrder.setKey_User_Role("Finance Manager");
                                      } else {
                                          preferenceManagerWorkOrder.setKey_User_Role(response.body().userrole);
                                      }*/


//  if ()


                                    /*  preferenceManagerWorkOrder.setKey_User_Name(response.body().username);
                                      preferenceManagerWorkOrder.setKey_User_Id(String.valueOf(response.body().userid));
                                      preferenceManagerWorkOrder.setKey_Person_Company_Id(String.valueOf(response.body().personcompanyid));
                                      preferenceManagerWorkOrder.setKey_Parent_Company_Id(String.valueOf(response.body().parentcompanyid));

                                      Log.d("UserName : ",preferenceManagerWorkOrder.getKey_User_Name());
                                      Log.d("UserId : ",preferenceManagerWorkOrder.getKey_User_Id());
                                      Log.d("PersonId : ",preferenceManagerWorkOrder.getKey_Person_Company_Id());
                                      Log.d("CompanyId : ",preferenceManagerWorkOrder.getKey_Parent_Company_Id());

                                      Toast.makeText(LoginActivity.this, ""+preferenceManagerWorkOrder.getKey_User_Id(), Toast.LENGTH_SHORT).show();

                                      */




                                 /*     if (userrole.equals("C"))
                                      Bundle bundle=new Bundle();
                                      bundle.putString("Id", preferenceManagerWorkOrder.getKey_Person_Company_Id());
                                      Intent intent = new Intent(LoginActivity.this, MainActivityWorkOrder.class);
                                    //  intent.putExtras(bundle);
                                      startActivity(intent);*/

//  username1=response.body().getUsername();

// userid=String.valueOf(response.body().getUserid());
//userrole=response.body().getUserrole();




                                     /* if (userrole.equals("Worker")){

                                          Intent intent = new Intent(LoginActivity.this, WorkerSearchList.class);
                                          startActivity(intent);
                                          progressDialog.dismiss();
                                      }else if (userrole.equals("Landlord")){
                                          Bundle bundle=new Bundle();
                                          bundle.putString("Id", preferenceManagerWorkOrder.getKey_Person_Company_Id());
                                          Intent intent = new Intent(LoginActivity.this, SearchWorkOrder.class);
                                          intent.putExtras(bundle);
                                          startActivity(intent);
                                          progressDialog.dismiss();
                                      }else if (userrole.equals("Worker")){

                                          Intent intent = new Intent(LoginActivity.this,WorkerSearchList.class);
                                          startActivity(intent);
                                          progressDialog.dismiss();
                                      }else if (userrole.equals("Client")) {
                                          Bundle bundle=new Bundle();
                                          bundle.putString("Id", preferenceManagerWorkOrder.getKey_Person_Company_Id());
                                          Intent intent = new Intent(LoginActivity.this, MainActivityWorkOrder.class);
                                          intent.putExtras(bundle);
                                          startActivity(intent);
                                          progressDialog.dismiss();
                                      }else {
                                          Intent intent = new Intent(LoginActivity.this, MainActivityWorkOrder.class);
                                          intent.putExtra("username", username1);
                                          intent.putExtra("userrole", userrole);
                                          startActivity(intent);
                                          progressDialog.dismiss();
                                      }*/
/*
                                      else if (userrole.equals("Client")) {
                                          Bundle bundle=new Bundle();
                                          bundle.putString("Id", preferenceManagerWorkOrder.getKey_Person_Company_Id());
                                          Intent intent = new Intent(LoginActivity.this, MainActivityWorkOrder.class);
                                          intent.putExtras(bundle);
                                          startActivity(intent);
                                      }


        }else {
        // sms.setText("Invalid Username or Password");
        // sms.setVisibility(View.VISIBLE);
        //progressDialog.dismiss();
        // callClearMobileLogin();
        }
        // progressDialog.dismiss();
        } else {
        //  sms.setText("Invalid Username or Password");
        //  sms.setVisibility(View.VISIBLE);
        }
        progressDialog.dismiss();
        }

        } catch (Exception e) {
        Log.v("Error",e.getMessage());
        }
        }

@Override
public void onFailure(Call<LoginResponseWorkOrderModel> call, Throwable t) {

        t.printStackTrace();
        System.out.println(t.getMessage() + t.getLocalizedMessage());
        // sms.setText(t.getLocalizedMessage());
        // sms.setVisibility(View.VISIBLE);
        }
        });

 */


package com.workorder.app.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.workorder.app.R;
import com.workorder.app.fragment.AssessmentHomeFragment;
import com.workorder.app.fragment.SWMSFragment;
import com.workorder.app.pojo.HomeStatusPOJO;
import com.workorder.app.util.Constants;
import com.workorder.app.util.UrlClass;
import com.workorder.app.webservicecallback.GetApiCallback;
import com.workorder.app.webservicecallback.OnTaskCompleted;
import com.workorder.app.webservicecallback.SendData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DashBoardActivity extends AppCompatActivity implements LocationListener, NavigationView.OnNavigationItemSelectedListener {
    String user_id = null;
    TextView tv_go_on_site;
    TextView tv_role;
    TextView tv_name,assessment,survey;
    TextView tv_list_type;
    NavigationView navigationView;
    ImageView iv_mapview;
    LinearLayout lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv_go_on_site = findViewById(R.id.tv_home_activity_go_on_site);
        tv_name = findViewById(R.id.tv_home_activity_name);
        tv_role = findViewById(R.id.tv_home_activity_role);
        tv_list_type = findViewById(R.id.unsigned_task);
        iv_mapview = findViewById(R.id.iv_home_activity_mapview);
        assessment = findViewById(R.id.assessment);
        survey = findViewById(R.id.survey);
        lin = findViewById(R.id.sur);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.navigationbar));
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));

        // Constants.loginPOJO= PreferenceManagerWorkOrder.GetLoginData(this);

        assessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardActivity.this, HomeActivity.class));
            }
        });
        survey.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DashBoardActivity.this, SurveyTemplate.class));
                    }
                });
        lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DashBoardActivity.this, SurveyTemplate.class));
                    }
                });

        final SharedPreferences pref = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        String json_array = pref.getString("jsonarray", null);
        try {
            if(json_array.isEmpty()){

            }else {
                JSONArray jsoArray = new JSONArray(json_array);
                Log.v("shalu", jsoArray.toString());

                final String requestBody=jsoArray.toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.SUBMIT_ANSWER,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                JSONObject jsonObject= null;
                                try {
                                    jsonObject = new JSONObject(response);
                                    String message=jsonObject.getString("msg");
                                    SharedPreferences mSharedPreferences = getApplicationContext().getSharedPreferences("PREFS_NAME", 0);
                                    if (mSharedPreferences != null)
                                        mSharedPreferences.edit().remove("jsonarray").commit();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_SHORT).show();

                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return String.format("application/json; charset=utf-8");
                    }

                    @Override

                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return requestBody == null ? null : requestBody.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            return null;
                        }
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (NullPointerException e){

        }


        final SharedPreferences pref1 = getSharedPreferences("PREFS_NAME1", MODE_PRIVATE);
        String json_obj = pref1.getString("jsonobject", null);
        try {
            if(json_obj.isEmpty()){

            }else {
                JSONObject jsonObject=new JSONObject(json_obj);
                new SendData(DashBoardActivity.this, jsonObject, UrlClass.UPLOAD_SIGN_URL, new OnTaskCompleted<String>() {
                    @Override
                    public void onTaskCompleted(String response) {
                        Log.d("Post Response", response);
                        try {
                            JSONObject object = new JSONObject(response);
                            SharedPreferences mSharedPreferences = getApplicationContext().getSharedPreferences("PREFS_NAME1", 0);
                            if (mSharedPreferences != null)
                                mSharedPreferences.edit().remove("jsonobject").commit();

//                            Boolean result = object.getBoolean("status");
//                            if (result) {
//                                Toast.makeText(getApplicationContext(), "" + object.getString("msg"), Toast.LENGTH_SHORT).show();
//                                Constants.ACTIVITY_NAME = Constants.SHOW_DOCUMENT_ACTIVITY;
//                                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
//                                finish();
//                            } else {
//                                //Toast.makeText(context, "Signature Uploaded Failed...", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getApplicationContext(), "" + object.getString("msg"), Toast.LENGTH_SHORT).show();
//
//                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("PostSignature", e.toString());
                        }

                    }
                }, true).execute();

            }
        }catch (JSONException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }



        if (Constants.ACTIVITY_NAME.equals(Constants.SHOW_DOCUMENT_ACTIVITY)) {
            iv_mapview.setVisibility(View.GONE);
            tv_list_type.setText("SWMS List");
            SWMSFragment fragment = new SWMSFragment();
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();

            }
        } else {
            iv_mapview.setVisibility(View.VISIBLE);
            tv_list_type.setText("Work Order List");
            AssessmentHomeFragment fragment = new AssessmentHomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        TaskDetailFragment fragment=new TaskDetailFragment();
            fragmentTransaction.add(R.id.container, fragment).commit();


        }
        callCheckOnSiteApi();
        iv_mapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this, ShowAllSiteInMap.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        tv_go_on_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openOnSiteDialog();
            }
        });


        fn_permission();
        statusCheck();
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    private void buildAlertMessageNoGps() {
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }


    public void init() {


       /* PreferenceManager preferenceManager = PreferenceManager.getInstance(this);
        nameText.setText(preferenceManager.getUsername());
        roleText.setText(preferenceManager.getKeyRole());*/
    }

    public void openOnSiteDialog() {
        final Dialog dialog = new Dialog(DashBoardActivity.this);
        dialog.setContentView(R.layout.inflate_open_dialog_go_on_site_home_activity);

        TextView tv_no = dialog.findViewById(R.id.tv_home_check_on_site_no);
        TextView tv_yes = dialog.findViewById(R.id.tv_home_check_on_site_yes);

        dialog.show();


        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                showAlertDialog();


            }
        });

    }

    public void showAlertDialog() {
        final Dialog dialog = new Dialog(DashBoardActivity.this);
        dialog.setContentView(R.layout.inlate_home_yes_click_dialog);

        TextView tv_no = dialog.findViewById(R.id.tv_home_alert_check_on_site_no);
        TextView tv_yes = dialog.findViewById(R.id.tv_home_alert_check_on_site_yes);

        dialog.show();


        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                callStatusUpdateApi(false);
                //opentThanksNoClickDialog();
            }
        });
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                //opentThanksYesClickDialog();
                callStatusUpdateApi(true);


            }
        });

    }


    public void opentThanksNoClickDialog() {
        final Dialog dialog = new Dialog(DashBoardActivity.this);
        dialog.setContentView(R.layout.inflate_thanks_home_activity);
        TextView tv_ok = dialog.findViewById(R.id.tv_ok_thanks_no_click);
        TextView tv_message = dialog.findViewById(R.id.tv_message_thanks);

        dialog.show();
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
              /*  tv_go_on_site.setText("Off-Site");
                tv_go_on_site.setBackgroundDrawable(getResources().getDrawable(R.drawable.go_off_site_design));
                tv_go_on_site.setEnabled(false)*/
                ;

            }
        });
    }

    public void opentThanksYesClickDialog() {
        final Dialog dialog = new Dialog(DashBoardActivity.this);
        dialog.setContentView(R.layout.inflate_home_thanks_yes_click);
        TextView tv_ok = dialog.findViewById(R.id.tv_ok_thanks);
        TextView tv_message = dialog.findViewById(R.id.tv_message_thanks);

        dialog.show();
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();


            }
        });
    }


    @Override
    public void onBackPressed() {
        iv_mapview.setVisibility(View.VISIBLE);
        tv_list_type.setText("Work Order List");
        AssessmentHomeFragment fragment = new AssessmentHomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        TaskDetailFragment fragment=new TaskDetailFragment();
        fragmentTransaction.add(R.id.container, fragment).commit();


        navigationView.setCheckedItem(R.id.nav_work_order_list);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // AlertDialog.Builder builder=new AlertDialog.Builder(this);
            //builder.setTitle("Alert").setMessage("Are you sure?\n")
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();
        if (id == R.id.nav_work_order_list) {
            iv_mapview.setVisibility(View.VISIBLE);
            tv_list_type.setText("Work Order List");
            fragment = new AssessmentHomeFragment();
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, null).addToBackStack(null).commit();
                callCheckOnSiteApi();
            }

        }
      /*  if (id == R.id.nav_covid_survey) {
            iv_mapview.setVisibility(View.GONE);
            tv_list_type.setText("(COVID-19) Awareness Survey");
            fragment = new SurveyFragment();
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, null).addToBackStack(null).commit();
            }
        }*/
        if (id == R.id.nav_logout) {
            logOutAlert();
            //isDestroyed();
        } else if (id == R.id.nav_swms_list) {
            iv_mapview.setVisibility(View.GONE);
            tv_go_on_site.setText("On-Site");
            tv_go_on_site.setBackgroundDrawable(getResources().getDrawable(R.drawable.go_on_site_bg_design));
            tv_go_on_site.setEnabled(true);
            tv_list_type.setText("SWMS List");
            fragment = new SWMSFragment();
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, null).addToBackStack(null).commit();
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logOutAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert").setMessage("Are you sure you want to Logout from app");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Constants.ACTIVITY_NAME = Constants.HOME_ACTIVITY;
                startActivity(new Intent(DashBoardActivity.this, LoginActivity.class));
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void callStatusUpdateApi(final boolean isCompleted) {
        try {
            if (Constants.homeStatusPOJO != null) {
                JSONObject jsonObject = new JSONObject();
              /*  jsonObject.put("AssesmentSiteId",Constants.assesmentHomePOJOList.get(0).getAssesmentID());
                jsonObject.put("Latitude",latitude);
                jsonObject.put("Longitude",longitude);
               // jsonObject.put("StartDate",Constants.homeStatusPOJO.getStartDate());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String dateTime = dateFormat.format(new Date());
                jsonObject.put("EndDate",dateTime);
                jsonObject.put("Status","Off-Site");
                jsonObject.put("EmployeeID",Constants.assesmentHomePOJOList.get(0).getEmployeeID());
                jsonObject.put("WorkOrderNo",Constants.assesmentHomePOJOList.get(0).getAssesment().getWorkOrderNumber());
                jsonObject.put("IsComplete",isCompleted);
                jsonObject.put("TaskId",Constants.assesmentHomePOJOList.get(0).getAssesmentTaskID());
*/

              /*  {
                    "Longitude": 11.21212,
                        "StartDate": "2019-05-20T23:12:57+05:30",
                        "Status": "ONSITE",
                        "AssesmentSiteId": 4,
                        "EmployeeID": 2148,
                        "WorkOrderNo": "WO-000060",
                        "EndDate": "",
                        "Latitude": 11.21212
                }*/


                jsonObject.put("UpdateLatitude", latitude);
                jsonObject.put("UpdateLongitude", longitude);
                jsonObject.put("Status", "Off-Site");
                jsonObject.put("AssesmentSiteId", Constants.homeStatusPOJO.getSITEACTIVITYID());
                jsonObject.put("EmployeeID",Constants.homeStatusPOJO.getEMPLOYEEID());
                jsonObject.put("WorkOrderNo", Constants.homeStatusPOJO.getASSESMENTID());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = dateFormat.format(new Date());
                jsonObject.put("EndDate", dateTime);
                jsonObject.put("WorkOrderStatus", "Complete");


                new SendData(this, jsonObject, UrlClass.UPDATE_STATUS_URL, new OnTaskCompleted<String>() {
                    @Override
                    public void onTaskCompleted(String response) {
                        Log.d("UpdateResponse", response);
                        if (Constants.SEND_STATUS == 200) {
                            tv_go_on_site.setText("Off-Site");
                            tv_go_on_site.setBackgroundDrawable(getResources().getDrawable(R.drawable.go_off_site_design));
                            tv_go_on_site.setEnabled(false);
                            callCheckOnSiteApi();
                        }
                        if (isCompleted) {
                            opentThanksYesClickDialog();
                        } else {
                            opentThanksNoClickDialog();
                        }
                    }
                }, true).execute();
            }
        } catch (Exception e) {
            Log.d("UpdateException", e.toString());
        }
    }


    public void callCheckOnSiteApi() {
        new GetApiCallback(this, UrlClass.getBaseUrl()+"api/Order/getactivity", new OnTaskCompleted<String>() {
            @Override
            public void onTaskCompleted(String response) {
                try {
                    Log.d("CheckStatusResponse", response);
                    Constants.homeStatusPOJO = new Gson().fromJson(response, HomeStatusPOJO.class);
                    if (Constants.homeStatusPOJO.getSTATUS().equals("On-Site")) {
                        tv_go_on_site.setText(Constants.homeStatusPOJO.getSTATUS());
                        tv_go_on_site.setBackgroundDrawable(getResources().getDrawable(R.drawable.go_on_site_bg_design));
                        tv_go_on_site.setEnabled(true);
                    } else if (Constants.homeStatusPOJO.getSTATUS().equals("Off-Site")) {
                        tv_go_on_site.setText("Off-Site");
                        tv_go_on_site.setBackgroundDrawable(getResources().getDrawable(R.drawable.go_off_site_design));
                        tv_go_on_site.setEnabled(false);
                    }
                } catch (Exception e) {
                    Log.d("Exception", e.toString());
                }

            }
        }, true).execute();
    }


    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))) {


            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION

                        },
                        REQUEST_PERMISSIONS);

            }
        } else {
            boolean_permission = true;
            fn_getlocation();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    boolean_permission = true;
                    fn_getlocation();
                    statusCheck();

                } else {
                    statusCheck();
                    //Toast.makeText(HomeActivity.this, "Please allow the permission", Toast.LENGTH_LONG).show();

                }
            }
        }
    }


    boolean isGPSEnable = false;
    boolean isNetworkEnable = false;
    double latitude, longitude;
    LocationManager locationManager;
    Location location;
    private static final int REQUEST_PERMISSIONS = 100;

    private static final int MY_REQUEST = 1001;
    boolean boolean_permission;

    private void fn_getlocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnable && !isNetworkEnable) {

        } else {

            if (isNetworkEnable) {
                location = null;
                //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {

                        Log.e("latitude", location.getLatitude() + "");
                        Log.e("longitude", location.getLongitude() + "");

                        latitude = location.getLatitude();
                        longitude = location.getLongitude();

                        Constants.PROVIDER = LocationManager.NETWORK_PROVIDER;
                        ///String distance=  UtilityFunction.calculateDistance(latitude,longitude,Double.parseDouble(locationPOJO.getLat()),Double.parseDouble(locationPOJO.getLong()),LocationManager.NETWORK_PROVIDER);

                        Constants.CURRENT_LAT = latitude;
                        Constants.CURRENT_LNG = longitude;
                        // Constants.DISTANCE=distance;
//                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                                Uri.parse("http://maps.google.com/maps?saddr="+latitude+","+longitude+"&daddr="+locationPOJO.getLat()+","+locationPOJO.getLong()));///*28.7893,79.0250*/
//                        //28.5355,77.3910
//                        startActivity(intent);
                        Log.d("Latitude", Constants.CURRENT_LAT + "");
                        Log.d("Longitude", Constants.CURRENT_LNG + "");
                        // fn_update(location);
                        // getNearPlaces();
                    }
                }

            } else if (isGPSEnable) {
                location = null;
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        Log.e("latitude", location.getLatitude() + "");
                        Log.e("longitude", location.getLongitude() + "");
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        // String distance=  UtilityFunction.calculateDistance(latitude,longitude,Double.parseDouble(locationPOJO.getLat()),Double.parseDouble(locationPOJO.getLong()),LocationManager.GPS_PROVIDER);

                        Constants.PROVIDER= LocationManager.GPS_PROVIDER;
                        Constants.CURRENT_LAT=latitude;
                        Constants.CURRENT_LNG=longitude;
                      /*  Constants.DISTANCE=distance;
                        Log.d("Distance",distance);*/
                        Log.d("Latitude", Constants.CURRENT_LAT+"");
                        Log.d("Longitude", Constants.CURRENT_LNG+"");

                        //   fn_update(location);
                        // getNearPlaces();
                    }
                }
            }


        }



    }

    @Override
    public void onLocationChanged(Location location) {

        Constants.CURRENT_LAT=location.getLatitude();
        Constants.CURRENT_LNG=location.getLongitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}

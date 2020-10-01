package com.workorder.app.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.workorder.app.GetLatLngServices;
import com.workorder.app.R;
import com.workorder.app.adapter.SyncronizedHomeAdapter;
import com.workorder.app.pojo.CreateActivityPOJO;
import com.workorder.app.pojo.GetWorkOrderDetailPojo;
import com.workorder.app.pojo.GetWorkorderPOJO;
import com.workorder.app.pojo.HomeStatusPOJO;
import com.workorder.app.pojo.WorkOrderPOJO;
import com.workorder.app.pojo.assesment.SiteLocationPOJO;
import com.workorder.app.util.Constants;
import com.workorder.app.util.UrlClass;
import com.workorder.app.util.UtilityFunction;
import com.workorder.app.webservicecallback.GetApiCallback;
import com.workorder.app.webservicecallback.OnTaskCompleted;
import com.workorder.app.webservicecallback.SendData;
import com.workorder.app.webservicecallback.directionhelpers.FetchURL;
import com.workorder.app.webservicecallback.directionhelpers.TaskLoadedCallback;
import com.workorder.app.workorderapplication.remote.ApiServicesWorkOrder;
import com.workorder.app.workorderapplication.remote.NetworkWorkOrder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {
    ImageView iv_back;
    TextView tv_mark_on_site;
    TextView tv_wo_no;
    TextView tv_wo_start_dt;
    TextView tv_wo_end_dt;
    int workorderid;
    TextView tv_status;
    TextView tv_address;
    RecyclerView recyclerView;
    // SiteLocationAdapter adapter;
    GetWorkorderPOJO assesmentHomePOJO;
    LinearLayout ll_bottom_sheet;
    BottomSheetBehavior bottomSheetBehavior;
    private static final int REQUEST_PERMISSIONS = 100;
    boolean boolean_permission;
    Double latitude, longitude;
    Geocoder geocoder;
    private GoogleMap mMap;
    LocationManager locationManager;
    LatLng destLatLng;
    LatLng startLatlng;
    private MarkerOptions place1, place2;
    private Polyline currentPolyline;
    Marker marker;
    boolean isGPSEnable = false;
    boolean isNetworkEnable = false;
    SiteLocationPOJO siteLocationPOJO;
    double distance = 0;
    public static final double DISTANCE =20;
    // Bottom Sheet
    ListView listView;
    TextView tv_suspended;
    TextView tv_bottom_cancel;
    TextView warning;
    TextView description;
    TextView template;
    TextView tv_go_on_site;
    public static int onsite;
    ProgressDialog dialog;
 String workorderstatus;
 String warninglevel;
 TextView workOrderStatus;
 TextView warningtext;
 ImageView warning1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        UtilityFunction.statusCheck(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        iv_back = findViewById(R.id.iv_site_location_back);
        template = findViewById(R.id.template);
        warning = findViewById(R.id.warning);
        description = findViewById(R.id.descrption);
        tv_go_on_site = findViewById(R.id.tv_home_activity_go_on_site);
        tv_mark_on_site = findViewById(R.id.tv_mark_on_site);
        tv_wo_no = findViewById(R.id.tv_site_location_site_work_no);
        tv_wo_start_dt = findViewById(R.id.tv_site_location_start_date);
        tv_wo_end_dt = findViewById(R.id.tv_site_location_end_date);
        // tv_asesmnt_title = findViewById(R.id.tv_site_location_asses_title);
        tv_status = findViewById(R.id.tv_site_location_status);
        tv_address = findViewById(R.id.tv_site_location_address);

        ll_bottom_sheet = findViewById(R.id.ll_bottom_dialog);
        listView = findViewById(R.id.rv_bottom_sheet);
        tv_suspended = findViewById(R.id.tv_suspend_minute);
        tv_bottom_cancel = findViewById(R.id.tv_bottom_cancel);
        workOrderStatus = findViewById(R.id.workOrderStatus);
        warningtext = findViewById(R.id.warningtext);
        warning1 = findViewById(R.id.warning1);


        ll_bottom_sheet.setVisibility(View.GONE);
        //   tv_trade_category = findViewById(R.id.tv_site_location_trade_category);

        //recyclerView=findViewById(R.id.rv_site_location_list);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.navigationbar));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                // finish();
            }
        });

        workorderid=getIntent().getIntExtra("WorkOrderid",0);
        workorderstatus=getIntent().getStringExtra("workorderstatus");
        warninglevel=getIntent().getStringExtra("warninglevel");
        Log.v("workorderid", String.valueOf(workorderid));

        workOrderStatus.setText(workorderstatus);
        warningtext.setText(warninglevel);
        if(warninglevel.equalsIgnoreCase("Ok")){
            warning1.setImageResource(R.drawable.ic_warning_black2_24dp);
        }else if(warninglevel.equalsIgnoreCase("Failure")){
            warning1.setImageResource(R.drawable.ic_warning_black3_24dp);
        }else {
            warning1.setImageResource(R.drawable.ic_warning_black1_24dp);
        }

        new GetApiCallback(MapsActivity.this, UrlClass.BASE_URL+"api/Order/GetOrderAssesments?orderId="+workorderid, new OnTaskCompleted<String>() {
            @Override
            public void onTaskCompleted(String response) {
                Log.d("ResponseWorkOrder", response);
                try {

                    Constants.workOrderPOJOdetail = Arrays.asList(new Gson().fromJson(response, GetWorkOrderDetailPojo[].class));
                    Constants.workOrderdetail=Constants.workOrderPOJOdetail.get(0);
                    if(Constants.workOrderdetail.getIsSurveyAttached().equalsIgnoreCase("Yes")){
                        template.setVisibility(View.VISIBLE);
                    }else {
                        template.setVisibility(View.GONE);
                    }
                } catch (Exception e) {

                }

            }
        }, true).execute();

        template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_go_on_site.getText().toString().equalsIgnoreCase("On-Site")) {
                    assesmentHomePOJO = (GetWorkorderPOJO) getIntent().getSerializableExtra("WorkOrderPOJO");
                    Intent i = new Intent(MapsActivity.this, SurveyTemplate.class);
                    i.putExtra("assessmentid", Constants.workOrderdetail.getAssesmentId());
                    i.putExtra("workorderid", assesmentHomePOJO.getWorkOrderId());
                    startActivity(i);
                } else {
                    opentThanksYesClickDialog1("Work Order has to be Activated and you have to be On-Site before the Survey can be processed");
                }
            }
        });

        for (int i = 1; i <= 480; i++) {
            timeList.add(String.valueOf(i));
        }

        assesmentHomePOJO = (GetWorkorderPOJO) getIntent().getSerializableExtra("WorkOrderPOJO");

        callCheckOnSiteApi();


        tv_mark_on_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (distance == 0) {
                    //  Toast.makeText(MapsActivity.this, "Please try again...", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MapsActivity.this, "Oops! something went wrong. Please try again after some time", Toast.LENGTH_SHORT).show();

                } else {*/
                Log.d("ButtonClickDistance", distance + "");
//               if (Constants.homeStatusPOJO.getStatus().equals("On-Site"))
//               {
//                   Toast.makeText(MapsActivity.this, "You are On-Site", Toast.LENGTH_SHORT).show();
//               }

                if (distance <= DISTANCE && distance > 0) {
                    //tv_mark_on_site.setEnabled(true);
                    // callPostApi();
                    // callCreateActivity();
                } else {
                    Toast.makeText(MapsActivity.this, "Your current location doesn't match with the site location. You cannot sign the SWMS for this Work Order until you are at the Site location.", Toast.LENGTH_LONG).show();
                }
                // }

            }
        });
    }

    public void openOnSiteDialog() {
        final Dialog dialog = new Dialog(MapsActivity.this);
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
        final Dialog dialog = new Dialog(MapsActivity.this);
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
                jsonObject.put("AssesmentSiteId",Constants.homeStatusPOJO.getSITEACTIVITYID());
                jsonObject.put("EmployeeID", Constants.homeStatusPOJO.getEMPLOYEEID());
                jsonObject.put("WorkOrderNo", Constants.workOrderdetail.getWorkOrderNo());
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
                            opentThanksYesClickDialog2();
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

    public void opentThanksNoClickDialog() {
        final Dialog dialog = new Dialog(MapsActivity.this);
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

    public void opentThanksYesClickDialog2() {
        final Dialog dialog = new Dialog(MapsActivity.this);
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


    public void opentThanksYesClickDialog1(String message) {
        final Dialog dialog = new Dialog(MapsActivity.this);
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


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DIALOG_STATUS = FIRST_TIME_DIALOG;
        if (timer != null) {
            timer.cancel();
        }
        Intent intent1 = new Intent(getApplicationContext(), GetLatLngServices.class);
        stopService(intent1);

        Constants.ACTIVITY_NAME =Constants.HOME_ACTIVITY;
        startActivity(new Intent(MapsActivity.this,HomeActivity.class));
     //   finish();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //  geocoder = new Geocoder(this, Locale.getDefault());


        //locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);


        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
        //    marker=mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        callApi();

    }

    public void opentThanksYesClickDialog(String message) {
        final Dialog dialog = new Dialog(MapsActivity.this);
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
                callTimer();

            }
        });
    }



    int alert_time = 5000;
    ArrayList<String> timeList = new ArrayList<>();
    CountDownTimer timer;
    ArrayAdapter<String> arrayAdapter;


    boolean is_dialog_show = false;
    boolean is_suspended_click = false;
    boolean is_time_selected;

    public void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.inflate_dialog_check_on_site);
        TextView tv_no = dialog.findViewById(R.id.tv_dialog_check_on_site_no);
        TextView tv_yes = dialog.findViewById(R.id.tv_dialog_check_on_site_yes);
        dialog.setTitle("Alert");
        dialog.setCancelable(false);
        dialog.show();
        is_dialog_show = true;

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                is_dialog_show = false;
                //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                showBottomSheet();
            }
        });
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Constants.TIME_PERIOD=-1;

                if (timer != null) {
                    timer.cancel();
                }
                is_dialog_show = false;

                if (distance <= DISTANCE) {
                    Log.v("text", tv_go_on_site.getText().toString());
                    if (tv_go_on_site.getText().toString().equalsIgnoreCase("Off-Site")) {
                        callCreateActivity("On-Site");

                        SharedPreferences mPrefs = MapsActivity.this.getSharedPreferences("TASK_ID", Context.MODE_PRIVATE);
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        prefsEditor.putInt("assess",Constants.workOrderdetail.getAssesmentId()  );
                        prefsEditor.commit();
                        Log.v("assess", String.valueOf(Constants.workOrderdetail.getAssesmentId()));


                    } else {
                        if (tv_go_on_site.getText().toString().equalsIgnoreCase("On-Site")) {
                            opentThanksYesClickDialog("It seems like you are already On-Site on another work Order & it is not possible to go On-Site more then one Work Order simultaneously.");
                        } else {
                            callCreateActivity("On-Site");
                            SharedPreferences mPrefs = MapsActivity.this.getSharedPreferences("TASK_ID", Context.MODE_PRIVATE);
                            SharedPreferences.Editor prefsEditor = mPrefs.edit();
                            prefsEditor.putInt("assess",Constants.workOrderdetail.getAssesmentId() );
                            prefsEditor.commit();
                            Log.v("assess", String.valueOf(Constants.workOrderdetail.getAssesmentId()));

                        }
                    }
                }
                dialog.dismiss();
            }
        });

    }


    public void callCheckOnSiteApi() {
        new GetApiCallback(this,UrlClass.BASE_URL+"api/Order/getactivity", new OnTaskCompleted<String>() {
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


    public void callCreateActivity(String status) {

        if (UtilityFunction.isNetworkAvailable(this)) {
            dialog = new ProgressDialog(this);
            try {

                dialog.setMessage("Please wait...");
                dialog.setCancelable(false);
                dialog.show();
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("Longitude", latitude);
                jsonObject.put("Latitude", longitude);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = dateFormat.format(new Date());
                jsonObject.put("StartDate", dateTime);
                jsonObject.put("Status", status);
                jsonObject.put("AssesmentSiteId", Constants.workOrderdetail.getAssesmentId());
                jsonObject.put("EmployeeID", 0);
                jsonObject.put("WorkOrderNo", Constants.workOrderdetail.getWorkOrderNo());
                jsonObject.put("EndDate", dateTime);

                Log.v("json",jsonObject.toString());

                new SendData(this, jsonObject, UrlClass.CHECK_ON_SITE_URL, new OnTaskCompleted<String>() {
                    @Override
                    public void onTaskCompleted(String response) {
                        Log.d("UpdateResponse", response);
                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            Toast.makeText(MapsActivity.this, "" + jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
                            DIALOG_STATUS = FIRST_TIME_DIALOG;
                            tv_go_on_site.setText("On-Site");
                            tv_go_on_site.setBackgroundDrawable(getResources().getDrawable(R.drawable.go_off_site_design));
                            tv_go_on_site.setEnabled(false);
                            Intent intent = new Intent(MapsActivity.this, HomeActivity.class);
                            intent.putExtra("AssesmentPOJO", assesmentHomePOJO);
                            Constants.ACTIVITY_NAME = Constants.SHOW_DOCUMENT_ACTIVITY;
                            startActivity(intent);
                            dialog.dismiss();
                        }catch (Exception e){

                        }



                    }
                }, true).execute();
               /* final String mRequestBody = jsonObject.toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://109.228.49.117:8097/api/Order/Createactivity", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("LOG_VOLLEY", response);
                        Toast.makeText(MapsActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                        DIALOG_STATUS = FIRST_TIME_DIALOG;
                        Intent intent = new Intent(MapsActivity.this, HomeActivity.class);
                        intent.putExtra("AssesmentPOJO", assesmentHomePOJO);
                        Constants.ACTIVITY_NAME = Constants.SHOW_DOCUMENT_ACTIVITY;
                        startActivity(intent);
                        dialog.dismiss();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("LOG_VOLLEY", error.toString());
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                            return null;
                        }
                    }

                };

                RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                requestQueue1.add(stringRequest);*/


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this, "Network is not available.", Toast.LENGTH_SHORT).show();
        }
    }





     /*  if (UtilityFunction.isNetworkAvailable(this))
       {
           dialog=new ProgressDialog(this);
           dialog.setMessage("Please wait...");
           dialog.setCancelable(false);
           dialog.show();
           try {
               ApiServicesWorkOrder apiServicesWorkOrder = NetworkWorkOrder.getInstance().getApiServicesWorkOrder();
               CreateActivityPOJO createActivityPOJO = new CreateActivityPOJO();
               createActivityPOJO.setLatitude(latitude);
               createActivityPOJO.setLongitude(longitude);
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String dateTime = dateFormat.format(new Date());
               createActivityPOJO.setStartDate(dateTime);
               createActivityPOJO.setStatus(status);
               createActivityPOJO.setAssesmentSiteId(assesmentHomePOJO.getSiteNameId());
               createActivityPOJO.setEmployeeID(Integer.parseInt(Constants.loginPOJO.getPersonCompanyId()));
               createActivityPOJO.setWorkOrderNo(assesmentHomePOJO.getWorkOrderNumber());
               createActivityPOJO.setEndDate("");

               Log.v("url", UrlClass.BASE_URL+"api/SiteActivity/createactivity");
               Log.v("pojo",createActivityPOJO.toString());

               final Call<String> createActivityRequest = apiServicesWorkOrder.createActivity("application/json", createActivityPOJO);
               createActivityRequest.enqueue(new Callback<String>() {
                   @Override
                   public void onResponse(Call<String> call, Response<String> response) {
                       if (response.body().equalsIgnoreCase("Site Activity Created Successfully")) {
                           Toast.makeText(MapsActivity.this, "" + response.body(), Toast.LENGTH_SHORT).show();
                           DIALOG_STATUS = FIRST_TIME_DIALOG;
                         *//*  Intent intent = new Intent(MapsActivity.this, HomeActivity.class);

                           intent.putExtra("AssesmentPOJO", assesmentHomePOJO);
                           Constants.ACTIVITY_NAME = Constants.SHOW_DOCUMENT_ACTIVITY;
                           startActivity(intent);*//*
                           dialog.dismiss();
                       } else {
                           Toast.makeText(MapsActivity.this, "" + response.body(), Toast.LENGTH_SHORT).show();
                           dialog.dismiss();
                       }
                   }

                   @Override
                   public void onFailure(Call<String> call, Throwable t) {
                       Log.d("Error",t.getMessage());
                       dialog.dismiss();
                   }
               });
           }catch (Exception e)
           {
               Log.d("CreateActivity", e.toString());
               dialog.dismiss();
           }
       }else {
           Toast.makeText(this, "Network is not available.", Toast.LENGTH_SHORT).show();
       }*/
/*
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Longitude", latitude);
            jsonObject.put("Latitude", longitude);
            //"StartDate", "2019-05-20T23,12:57+05:30",
            Log.d("Current date",UtilityFunction.getCalculatedDate("dd-MM-yyyy",0));
            jsonObject.put("StartDate", "05-24-2019");
            jsonObject.put("Status", "On-Site");
            jsonObject.put("AssesmentSiteId", Integer.parseInt(assesmentHomePOJO.getSiteNameId()));
            jsonObject.put("EmployeeID", Integer.parseInt(Constants.loginPOJO.getPersonCompanyId()));
            jsonObject.put("WorkOrderNo", assesmentHomePOJO.getWorkOrderNumber());
            jsonObject.put("EndDate", "");

            new SendData(this, jsonObject, UrlClass.CREATE_ACTIVITY_URL, new OnTaskCompleted<String>() {
                @Override
                public void onTaskCompleted(String response) {
                    Log.d("Response", response);
                    if (response.equals("Site Activity Created Successfully"))
                    {
                        Toast.makeText(MapsActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        DIALOG_STATUS = FIRST_TIME_DIALOG;
                        Intent intent = new Intent(MapsActivity.this, HomeActivity.class);

                        intent.putExtra("AssesmentPOJO", assesmentHomePOJO);
                        Constants.ACTIVITY_NAME = Constants.SHOW_DOCUMENT_ACTIVITY;
                        startActivity(intent);

                    }else {
                        Toast.makeText(MapsActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, true).execute();

        } catch (Exception e) {
            Log.d("CreateActivity", e.toString());
        }*/





    public void showBottomSheet() {
        ll_bottom_sheet.setVisibility(View.VISIBLE);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.inflate_bottom_listview, timeList);
        //  dialogAdapter=new DialogAdapter(this,timeList);
        // recyclerView.setAdapter(dialogAdapter);
        listView.setAdapter(arrayAdapter);
        //int x = (listView.getAdapter().getCount())/2;

        // listView.setSelection(x);
        //listView.getSelectedView().setSelected(true);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        is_suspended_click = false;


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alert_time = Integer.parseInt(timeList.get(position)) * 60000;
                is_time_selected = true;


            }
        });

        tv_bottom_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                ll_bottom_sheet.setVisibility(View.GONE);
                if(HomeActivity.tv_go_on_site.getText().toString().equalsIgnoreCase("On-Site")){

                }else
                { showDialog();
                }
            }
        });

        tv_suspended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  alert_time = 60000;
                if (is_time_selected) {
                    //alert_time = Constants.TIME_PERIOD* 60000;
                    //  is_time_selected=true;
                    is_dialog_show = false;
                    is_suspended_click = true;
                    ll_bottom_sheet.setVisibility(View.GONE);
                    callTimer();
                } else {
                    Toast.makeText(MapsActivity.this, "Please Select A Time.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void callTimer() {
        timer = new CountDownTimer(alert_time, 3000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                if (distance <= DISTANCE) {
                    if(HomeActivity.tv_go_on_site.getText().toString().equalsIgnoreCase("On-Site")){

                    }else
                    {
                        showDialog();
                        if (is_dialog_show) {
                            timer.cancel();
                        } else {
                            timer.start();
                        }
                    }
                }

            }
        };

    }


    public void callApi() {
        try {
           assesmentHomePOJO = (GetWorkorderPOJO) getIntent().getSerializableExtra("WorkOrderPOJO");
          //  Log.d("SiteId", assesmentHomePOJO.getSiteNameId()+"");
           if (assesmentHomePOJO != null) {

              tv_wo_no.setText(assesmentHomePOJO.getWorkOrderNo());
              tv_wo_start_dt.setText(UtilityFunction.changeDateTime(assesmentHomePOJO.getWorkOrderDate()));
                 tv_wo_end_dt.setText(UtilityFunction.changeDateTime(assesmentHomePOJO.getDueDate()));


                tv_address.setText(assesmentHomePOJO.getAddress1());
                tv_status.setText(assesmentHomePOJO.getStatus());

              //  warning.setText(assesmentHomePOJO.getWarningText());
              description.setText(assesmentHomePOJO.getWorkScope());
                destLatLng = new LatLng(assesmentHomePOJO.getLat(), assesmentHomePOJO.getLon());
              //  destLatLng=new LatLng(28.6119,77.3762);

                // if (UtilityFunction.gpsstatusCheck(MapsActivity.this)) {
                fn_permission();


            } else {
                Toast.makeText(this, "Id not found", Toast.LENGTH_SHORT).show();
            }
            //   destLatLng=new LatLng(28.6119,77.3762);

        } catch (Exception e) {
            Log.d("Map", e.getMessage());
        }

    }

    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION))) {


            } else {
                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION

                        },
                        REQUEST_PERMISSIONS);
            }
        } else {
            boolean_permission = true;
            //turnGPSOn();
            Log.d("Start Service", "Service is start");
            Intent intent1 = new Intent(getApplicationContext(), GetLatLngServices.class);
            startService(intent1);

            if (latitude==null && longitude==null)
            {
               latitude=Constants.CURRENT_LAT;
               longitude=Constants.CURRENT_LNG;
               setMarkerOnMap();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    boolean_permission = true;

                } else {
                    Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    public void setMarkerOnMap()
    {
        try {
            Log.d("Latitude", "" + latitude);
            Log.d("Longitude", "" + longitude);
          //  latitude=28.6119;
          //  longitude=77.3762;
            startLatlng = new LatLng(latitude,longitude);
            if (marker == null && marker1 == null) {
                place1 = new MarkerOptions().position(startLatlng).title("Your Location");//new LatLng(27.658143, 85.3199503)
                place2 = new MarkerOptions().position(destLatLng).title("Site Location");//new LatLng(27.667491, 85.3208583)
                marker = mMap.addMarker(place1);
                marker1 = mMap.addMarker(place2);
                markers.add(marker);
                markers.add(marker1);
                marker1.showInfoWindow();
                marker.showInfoWindow();
              /*  int padding = 50;
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (Marker marker : markers) {
                    builder.include(marker.getPosition());
                }
                LatLngBounds bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);*/
               // mMap.moveCamera(cu);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatlng,16));
            } else {
                marker.setPosition(startLatlng);
            }
            String dist = UtilityFunction.calculateDistance(latitude, longitude, destLatLng.latitude, destLatLng.longitude, Constants.PROVIDER);
            distance = Double.parseDouble(dist);
            Log.d("DISTANCE",""+distance);


            new FetchURL(MapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");

            if (DIALOG_STATUS.equals(FIRST_TIME_DIALOG)) {

                if (distance <= DISTANCE) {
                  /*  if (Constants.homeStatusPOJO.getStatus().equals("On-Site"))
                    {
                        Log.d("Status","You are On Site.");
                    }else {*/
                    Log.d("text",HomeActivity.tv_go_on_site.getText().toString());

                    if(HomeActivity.tv_go_on_site.getText().toString().equalsIgnoreCase("On-Site")){
                    }else
                    {
                        showDialog();
                        DIALOG_STATUS = REPEATED_DIALOG;

                    }
                 //   }
                }
            }
        } catch (Exception e) {
            Log.d("MapException", e.toString());
        }
    }


    Marker marker1;
    List<Marker> markers = new ArrayList<>();

    public static final String FIRST_TIME_DIALOG = "First time open dialog";
    public static final String REPEATED_DIALOG = "Repeated dialog";
    public static String DIALOG_STATUS = FIRST_TIME_DIALOG;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            latitude = Double.valueOf(intent.getStringExtra("latutide"));
            longitude = Double.valueOf(intent.getStringExtra("longitude"));


            try {
                Log.d("Latitude", "" + latitude);
                Log.d("Longitude", "" + longitude);
              //  latitude=28.6119;
              //  longitude=77.3762;
                CameraPosition camPos = new CameraPosition.Builder()
                        .target(new LatLng(latitude, longitude))
                        .zoom(18)
                        .tilt(70)
                        .build();
                CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
                mMap.animateCamera(camUpd3);
                startLatlng = new LatLng(latitude, longitude);


                if (marker == null && marker1 == null) {
                    place1 = new MarkerOptions().position(startLatlng).title("Your Location");//new LatLng(27.658143, 85.3199503)
                    place2 = new MarkerOptions().position(destLatLng).title("Site Location");//new LatLng(27.667491, 85.3208583)
                    marker = mMap.addMarker(place1);
                    marker1 = mMap.addMarker(place2);
                    markers.add(marker);
                    markers.add(marker1);
                    marker1.showInfoWindow();
                    marker.showInfoWindow();
                    int padding = 50;
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    for (Marker marker : markers) {
                        builder.include(marker.getPosition());
                    }
                    LatLngBounds bounds = builder.build();
                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                    mMap.moveCamera(cu);

                } else {
                    marker.setPosition(startLatlng);

                }
                // Polylines are useful to show a route or some other connection between points.


                // Position the map's camera near Alice Springs in the center of Australia,
                // and set the zoom factor so most of Australia shows on the screen.


                String dist = UtilityFunction.calculateDistance(latitude, longitude, destLatLng.latitude, destLatLng.longitude, Constants.PROVIDER);
                distance = Double.parseDouble(dist);

                new FetchURL(MapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");


                if (DIALOG_STATUS.equals(FIRST_TIME_DIALOG)) {
                    if (distance <= DISTANCE && distance>0) {

                        Log.d("text1",HomeActivity.tv_go_on_site.getText().toString());
                        if(HomeActivity.tv_go_on_site.getText().toString().equalsIgnoreCase("On-Site")){

                        }else
                        { showDialog();
                            DIALOG_STATUS = REPEATED_DIALOG;

                        }

                     /* if (Constants.homeStatusPOJO.getStatus().equals("On-Site"))
                      {
                          showDialog();
                      }else {
                          showDialog();
                      }*/
                    }
                }
            } catch (Exception e) {
                Log.d("BroadCastException", e.toString());
            }
            // addMarkers();


            // tv_latitude.setText(latitude+"");
            /// tv_longitude.setText(longitude+"");
            //   tv_address.getText();
        }
    };

    @Override
    protected void onPostResume() {
        super.onPostResume();
        registerReceiver(broadcastReceiver, new IntentFilter(GetLatLngServices.str_receiver));

    }

    @Override
    protected void onStop() {
        super.onStop();
        // medit.clear();
        unregisterReceiver(broadcastReceiver);
        stopService(new Intent(this, GetLatLngServices.class));
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }


    private static LatLng LOWER_MANHATTAN;
    // private static LatLng BROOKLYN_BRIDGE = new LatLng(40.7057, -73.9964);
    private static LatLng WALL_STREET;

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }


}

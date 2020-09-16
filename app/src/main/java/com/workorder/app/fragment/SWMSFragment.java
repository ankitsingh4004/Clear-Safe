package com.workorder.app.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.workorder.app.ConnectivityReceiver;
import com.workorder.app.R;
import com.workorder.app.Util;
import com.workorder.app.activity.HomeActivity;
import com.workorder.app.activity.MapsActivity;
import com.workorder.app.activity.ShowDocumentActivity;
import com.workorder.app.adapter.SWMSAdapter;
import com.workorder.app.adapter.SyncronizedHomeAdapter;
import com.workorder.app.pojo.GetLocationPOJO;
import com.workorder.app.pojo.GetWorkorderPOJO;
import com.workorder.app.pojo.HomeStatusPOJO;
import com.workorder.app.pojo.assesment.AssesmentHomePOJO;
import com.workorder.app.pojo.docPOJO.AttachementPOJO;
import com.workorder.app.pojo.docPOJO.DocListPOJO;
import com.workorder.app.pojo.docPOJO.GetSwmsTemplate;
import com.workorder.app.util.Constants;
import com.workorder.app.util.UrlClass;
import com.workorder.app.webservicecallback.GetApiCallback;
import com.workorder.app.webservicecallback.OnTaskCompleted;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static com.workorder.app.activity.HomeActivity.iv_mapview;
import static com.workorder.app.activity.HomeActivity.tv_go_on_site;

public class SWMSFragment extends Fragment implements LocationListener {
    Dialog dialog;
    Button yesButton;
    TextView titleText;
    ImageView img;
    ProgressBar progressbar;
    TextView tv_wo_no;
    TextView show;
    //HomeFragmentAdapter adapter;
  //  private List<SearchTaskListResponseModel> list = new ArrayList<>();
  //  private ArrayList<SearchTaskListResponseModel> data;
   /**/RecyclerView rv_home;
      String task = "";
  //  private PreferenceManager preferenceManager;
  //  private ProgressDialog progressDialog;
   // private List<SearchTaskListResponseModel> responseModel;


    GetLocationPOJO locationPOJO;
    int id;
    SWMSAdapter adapter;
    AssesmentHomePOJO assesmentHomePOJO;
    List<AttachementPOJO> attachementPOJOS=new ArrayList<>();

    Integer srValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        init(rootView);
      //  tv_wo_no.setText(Constants.workOrderdetail.getWorkOrderNo());


        final SharedPreferences pref1 = getContext().getSharedPreferences("TASK_ID", MODE_PRIVATE);
         id = pref1.getInt("assess", 0);
        if(id==0){
            tv_wo_no.setVisibility(View.GONE);
            show.setVisibility(View.GONE);
            opentThanksYesClickDialog1("SWMS are only available once the Work Order has been started and you are On-Site.");
            tv_go_on_site.setText("Off-Site");
            tv_go_on_site.setBackgroundDrawable(getResources().getDrawable(R.drawable.go_off_site_design));
            tv_go_on_site.setEnabled(false);
        }else {
            Log.v("id", String.valueOf(id));
            boolean isConnected = ConnectivityReceiver.isConnected();
            if (isConnected == true) {
                try {
                    new GetApiCallback(getActivity(), UrlClass.BASE_URL + "api/Order/GetAssesmentTemplates?assesmentId=" + id, new OnTaskCompleted<String>() {
                        @Override
                        public void onTaskCompleted(String response) {
                            Log.d("response", response);
                            Constants.getSwmsTemplates = Arrays.asList(new Gson().fromJson(response, GetSwmsTemplate[].class));
                            //Constants.docListPOJO = new Gson().fromJson(response, DocListPOJO.class);
                            //  attachementPOJOS = Constants.docListPOJO.getAttachementPOJOs();
                            //tv_wo_no.setText(attachementPOJOS.get());
                            adapter = new SWMSAdapter(getActivity(), Constants.getSwmsTemplates, id);
                            rv_home.setAdapter(adapter);
                            Log.d("AttachementSize", attachementPOJOS.size() + "");
                        }
                    }, true).execute();
                } catch (Exception e) {
                    Log.d("ShowListExceptiion", e.toString());
                }
            }/*else {
                for (int i=0;i< Constants.getSwmsTemplates.size();i++) {
                    adapter = new SWMSAdapter(getActivity(),  Constants.getSwmsTemplates, id);
                    rv_home.setAdapter(adapter);
                }
            }*/
        }




        return rootView;
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
                Constants.ACTIVITY_NAME =Constants.HOME_ACTIVITY;

                startActivity(new Intent(getContext(),HomeActivity.class));

            }
        });
    }

    public void init(View rootView) {
       rv_home = rootView.findViewById(R.id.rv_task_list);
        tv_wo_no=rootView.findViewById(R.id.tv_swms_work_order_no);
        show=rootView.findViewById(R.id.show);
        rv_home.setLayoutManager(new LinearLayoutManager(getActivity()));
    //    progressDialog = new ProgressDialog(getActivity());
    }

    public Toolbar setToolbar(View view) {
        Toolbar tb = (Toolbar) view.findViewById(R.id.toolbar);
        return tb;
    }
  /*  public void callAfterResponse(List<SearchTaskListResponseModel> response) {
        adapter = new RVTaskListAdapter(getActivity(), response);
        rv_home.setAdapter(adapter);
        adapter.setClicklistner(this);
    }*/
/*
    @Override
    public void taskClick(Integer position) {
        Bundle bundle = new Bundle();
//        bundle.putInt("taskId", responseModel.get(position).getTaskID());
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, null).addToBackStack(null).commit();
    }*/


    public void callCheckOnSiteApi() {
        new GetApiCallback(getContext(), UrlClass.BASE_URL+"api/Order/getactivity" , new OnTaskCompleted<String>() {
            @Override
            public void onTaskCompleted(String response) {
                try {
                    Log.d("CheckStatusResponse", response);

                    Constants.homeStatusPOJO = new Gson().fromJson(response, HomeStatusPOJO.class);
                    if (Constants.homeStatusPOJO.getSTATUS().equals("On-Site")) {
                        try {

                        }catch (Exception e)
                        {
                            Log.d("Exception",e.getMessage());
                        }

                      //  tv_wo_no.setText(Constants.homeStatusPOJO.g());

                        tv_go_on_site.setText(Constants.homeStatusPOJO.getSTATUS());
                        tv_go_on_site.setBackgroundDrawable(getResources().getDrawable(R.drawable.go_on_site_bg_design));
                        tv_go_on_site.setEnabled(true);
                    } else if (Constants.homeStatusPOJO.getSTATUS().equals("Off-Site")) {
                        tv_wo_no.setVisibility(View.GONE);
                        show.setVisibility(View.GONE);
                        //     opentThanksYesClickDialog1("SWMS are only available once the Work Order has been started and you are On-Site.");
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

    public void showProgressPopup() {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.prog_dialog_layout);
        yesButton = dialog.findViewById(R.id.btn_ok);
        titleText = dialog.findViewById(R.id.txt_title);
        img = dialog.findViewById(R.id.img_ok);
        progressbar = dialog.findViewById(R.id.progressbar);
        yesButton.setText("OK");
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

   /* public void fetchData() {
        showProgressPopup();
//        progressDialog.show();
//        progressDialog.setMessage("Loading...");
        SearchRequestModel searchRequestModel = new SearchRequestModel();
        searchRequestModel.setTitle("");
        searchRequestModel.setAssigenTo("");
        searchRequestModel.setFromDate("");
        searchRequestModel.setToDate("");

        preferenceManager = PreferenceManager.getInstance(getActivity());
        searchRequestModel.setUserName(preferenceManager.getUsername());
        if (Utility.isNetworkAvailable(getActivity())) {
                    ApiServices apiServices = Network.getInstance().getApiServices();
                    final Call<List<SearchTaskListResponseModel>> loginResponseCall =
                            apiServices.postSearcTask( "Bearer " + Constants.loginResponse,"application/json", searchRequestModel, UrlClass.Worker_Home);
                    loginResponseCall.enqueue(new Callback<List<SearchTaskListResponseModel>>() {
                        @Override
                        public void onResponse(Call<List<SearchTaskListResponseModel>> call, Response<List<SearchTaskListResponseModel>> response) {
                            try {
                                // TODO NULL CHECK OF RESPONSE
                                if (response.body() != null) {
                                    responseModel = response.body();
                                    //callAfterResponse(responseModel);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            yesButton.setVisibility(View.VISIBLE);
                                            img.setVisibility(View.VISIBLE);
                                            progressbar.setVisibility(View.GONE);
                                            titleText.setText("Download Completed");
                                        }
                                    }, 1000);
                                } else {
                                    Toast.makeText(getActivity(), response.errorBody().string().toString(), Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {

                            }
                        }

                @Override
                public void onFailure(Call<List<SearchTaskListResponseModel>> call, Throwable t) {

                    t.printStackTrace();
                    System.out.println(t.getMessage() + t.getLocalizedMessage());
                    Toast.makeText(getActivity(), "failure" + t.getMessage() + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();

                }
            });
        } else {
//            progressDialog.dismiss();
            Toast.makeText(getActivity(), "NetworkWorkOrder is not available", Toast.LENGTH_SHORT).show();
        }
    }*/


    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION))) {


            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION

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

                } else {
                    Toast.makeText(getActivity(), "Please allow the permission", Toast.LENGTH_LONG).show();

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
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
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

                        Constants.PROVIDER= LocationManager.NETWORK_PROVIDER;
                        ///String distance=  UtilityFunction.calculateDistance(latitude,longitude,Double.parseDouble(locationPOJO.getLat()),Double.parseDouble(locationPOJO.getLong()),LocationManager.NETWORK_PROVIDER);

                        Constants.CURRENT_LAT=latitude;
                        Constants.CURRENT_LNG=longitude;
                       // Constants.DISTANCE=distance;
//                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                                Uri.parse("http://maps.google.com/maps?saddr="+latitude+","+longitude+"&daddr="+locationPOJO.getLat()+","+locationPOJO.getLong()));///*28.7893,79.0250*/
//                        //28.5355,77.3910
//                        startActivity(intent);
                        Log.d("Latitude",Constants.CURRENT_LAT+"");
                        Log.d("Longitude",Constants.CURRENT_LNG+"");
                        // fn_update(location);
                        // getNearPlaces();
                    }
                }
            }else
            if (isGPSEnable) {
                location = null;
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000, 0, this);
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
                        Log.d("Latitude",Constants.CURRENT_LAT+"");
                        Log.d("Longitude",Constants.CURRENT_LNG+"");

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

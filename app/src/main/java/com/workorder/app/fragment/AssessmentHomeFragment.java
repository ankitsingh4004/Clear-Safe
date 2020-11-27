
package com.workorder.app.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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


import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.workorder.app.R;
import com.workorder.app.activity.LoginActivity;
import com.workorder.app.adapter.SyncronizedHomeAdapter;
import com.workorder.app.pojo.GetWorkOrderDetailPojo;
import com.workorder.app.pojo.GetWorkorderPOJO;
import com.workorder.app.pojo.HomeStatusPOJO;
import com.workorder.app.pojo.WorkOrderPOJO;
import com.workorder.app.pojo.assesment.AssesmentHomePOJO;
import com.workorder.app.util.Constants;
import com.workorder.app.util.UrlClass;
import com.workorder.app.util.UtilityFunction;
import com.workorder.app.webservicecallback.GetApiCallback;
import com.workorder.app.webservicecallback.OnTaskCompleted;
import com.workorder.app.webservicecallback.SendData;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.workorder.app.activity.HomeActivity.iv_mapview;
import static com.workorder.app.activity.HomeActivity.tv_go_on_site;

public class AssessmentHomeFragment extends Fragment{
    Dialog dialog;
    Button yesButton;
    TextView titleText;
    ImageView img;
    ProgressBar progressbar;
    //RVSyncTaskListAdapter adapter;
    Integer taskId ;
    //private List<SearchTaskListResponseModel> list = new ArrayList<>();
    //private ArrayList<SearchTaskListResponseModel> data;
    RecyclerView mrecyclerView;
    String task = "";
    ImageView attchment;
   // private PreferenceManager preferenceManager;
    TextView taskList;
    ProgressDialog progressDialog;
   // private List<SearchTaskListResponseModel> responseModel;
   double distance = 0;
    public static final double DISTANCE = 20;
    SyncronizedHomeAdapter syncronizedHomeAdapter;

    String role="",companyId="";
    String url="";
    String workorderno;
    int workno;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView=inflater.inflate(R.layout.fragment_sync,container,false);
       // attchment=rootView.findViewById(R.id.img_attachment);
        if(getArguments()!=null)
        {
            taskId = getArguments().getInt("taskId");
        }

        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        init(rootView);

         //UrlClass.ROLE_TYPE=Constants.loginPOJO.getUserRole();
         //UrlClass.COMPANYID=Constants.loginPOJO.getPersonCompanyId();
        url=UrlClass.GET_ALL_TASK_URL+UrlClass.ROLE_TYPE+"&companyid="+UrlClass.COMPANYID;
       // Log.d("SyncUrl",url);

       /* role=Constants.loginPOJO.getUserRole();
        companyId=Constants.loginPOJO.getPersonCompanyId();*/
       // Log.d("URL",UrlClass.GET_WORKORDER_URL+role+"&companyid="+companyId);


      //callCheckOnSiteApi();

        if(LoginActivity.value==0) {
     // if (Constants.workerPOJOList.size()==0) {

          showProgressPopup();
            callCheckOnSiteApi();
          new GetApiCallback(getActivity(), UrlClass.BASE_URL+"api/Order/GetActiveWorkOrders", new OnTaskCompleted<String>() {
              @Override
              public void onTaskCompleted(String response) {
                  Log.d("ResponseWorkOrder", response);
                  try {

                      yesButton.setVisibility(View.VISIBLE);
                      img.setVisibility(View.VISIBLE);
                      progressbar.setVisibility(View.GONE);
                      titleText.setText("Download Completed");
                      Log.d("Url", UrlClass.GET_ALL_TASK_URL);
                /*    JSONArray jsonArray=new JSONArray(response);
                    Log.d("SingleResponse",jsonArray.get(0).toString());*/
                      Constants.workOrderPOJOList = Arrays.asList(new Gson().fromJson(response, GetWorkorderPOJO[].class));
                      syncronizedHomeAdapter = new SyncronizedHomeAdapter(getActivity(), Constants.workOrderPOJOList,workorderno,workno);
                      mrecyclerView.setAdapter(syncronizedHomeAdapter);
                     // Constants.TASK_ID = "" + Constants.workOrderPOJOList.get(0).get();
                      // assesmentHomePOJO=Constants.assesmentHomePOJOList.get(0);
                         LoginActivity.value=1;

                  } catch (Exception e) {

                  }

              }
          }, true).execute();


       /*   for (int i=0;i< Constants.workOrderPOJOList.size();i++) {
              if ((Constants.workOrderPOJOList.get(i).getLat() == null) || Constants.workOrderPOJOList.get(i).getLon() == null) {

              } else {
                  String dist = UtilityFunction.calculateDistance( Constants.CURRENT_LAT , Constants.CURRENT_LNG, Constants.workOrderPOJOList.get(i).getLat(), Constants.workOrderPOJOList.get(i).getLon(), Constants.PROVIDER);
                  distance = Double.parseDouble(dist);
                  if (distance <= DISTANCE) {

                  } else {
                      SharedPreferences mSharedPreferences =getContext().getSharedPreferences("TASK_ID", 0);
                      if (mSharedPreferences != null)
                          mSharedPreferences.edit().remove("assess").commit();
                      callStatusUpdateApi("Completed", true);
                  }
              }
          }*/
     /* }
      else {
              new GetApiCallback(getActivity(), UrlClass.BASE_URL+"api/Order/GetActiveWorkOrders",new OnTaskCompleted<String>() {
                  @Override
                  public void onTaskCompleted(String response) {
                      Log.d("ResponseWorkOrder", response);
                      try {

                          yesButton.setVisibility(View.VISIBLE);
                          img.setVisibility(View.VISIBLE);
                      //    titleText.setText("Download Completed");
                          Log.d("Url", UrlClass.GET_ALL_TASK_URL);
                *//*    JSONArray jsonArray=new JSONArray(response);
                    Log.d("SingleResponse",jsonArray.get(0).toString());*//*
                          Constants.workOrderPOJOList = Arrays.asList(new Gson().fromJson(response, GetWorkorderPOJO[].class));
                          syncronizedHomeAdapter = new SyncronizedHomeAdapter(getActivity(), Constants.workOrderPOJOList,Constants.homeStatusPOJO.getSTATUS(),workno);
                          mrecyclerView.setAdapter(syncronizedHomeAdapter);
                         // Constants.TASK_ID = "" + Constants.workOrderPOJOList.get(0).getAssessmentTaskId();
                          // assesmentHomePOJO=Constants.assesmentHomePOJOList.get(0);

                      } catch (Exception e) {

                      }

                  }
              }, true).execute();

          callCheckOnSiteApi();*/
          }

/*          new GetApiCallback(getActivity(), url, new OnTaskCompleted<String>() {
              @Override
              public void onTaskCompleted(String response) {
                  Log.d("SyncResponse",response);
                  try {
                      yesButton.setVisibility(View.VISIBLE);
                      img.setVisibility(View.VISIBLE);
                      progressbar.setVisibility(View.GONE);
                      titleText.setText("Download Completed");
                      Log.d("Url",UrlClass.GET_ALL_TASK_URL);
                *//*    JSONArray jsonArray=new JSONArray(response);
                    Log.d("SingleResponse",jsonArray.get(0).toString());*//*
                      Constants.assesmentHomePOJOList= Arrays.asList(new Gson().fromJson(response, AssesmentHomePOJO[].class));
                      syncronizedHomeAdapter=new SyncronizedHomeAdapter(getActivity(), Constants.assesmentHomePOJOList);
                      mrecyclerView.setAdapter(syncronizedHomeAdapter);
                      Constants.TASK_ID=Constants.assesmentHomePOJOList.get(0).getAssesmentTaskID();
                      // assesmentHomePOJO=Constants.assesmentHomePOJOList.get(0);


                  } catch (Exception e) {
                      e.printStackTrace();
                      Log.d("SyncException",e.toString());
                  }

              }
          },false).execute();*/
      else {
            syncronizedHomeAdapter = new SyncronizedHomeAdapter(getActivity(), Constants.workOrderPOJOList,workorderno,workno);
            mrecyclerView.setAdapter(syncronizedHomeAdapter);

            /*for (int i=0;i< Constants.workOrderPOJOList.size();i++) {
                if ((Constants.workOrderPOJOList.get(i).getLat() == null) || Constants.workOrderPOJOList.get(i).getLon() == null) {

                } else {
                    String dist = UtilityFunction.calculateDistance( Constants.CURRENT_LAT , Constants.CURRENT_LNG, Constants.workOrderPOJOList.get(i).getLat(), Constants.workOrderPOJOList.get(i).getLon(), Constants.PROVIDER);
                    distance = Double.parseDouble(dist);
                    if (distance <= DISTANCE) {

                    } else {
                        SharedPreferences mSharedPreferences =getContext().getSharedPreferences("TASK_ID", 0);
                        if (mSharedPreferences != null)
                            mSharedPreferences.edit().remove("assess").commit();
                        callStatusUpdateApi("Completed", true);
                    }
                }
            }*/

        }
      //  fetchData();


        return rootView;
    }
    public void init(View rootView) {

        mrecyclerView = rootView.findViewById(R.id.rv_sync_task_list);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       // progressDialog = new ProgressDialog(getActivity(),R.style.AppCompatAlertDialogStyle);
    }
    AssesmentHomePOJO assesmentHomePOJO;


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
   /* public void fetchData()
    {
        showProgressPopup();
        SearchRequestModel searchRequestModel = new SearchRequestModel();
        searchRequestModel.setTitle("");
        searchRequestModel.setAssigenTo("");
        searchRequestModel.setFromDate("");
        searchRequestModel.setToDate("");
        preferenceManager = PreferenceManager.getInstance(getActivity());
        searchRequestModel.setUserName(preferenceManager.getUsername());
        ApiServices apiServices = Network.getInstance().getApiServices();
        final Call<List<SearchTaskListResponseModel>> loginResponseCall =
                apiServices. postAllTask( "Bearer " + preferenceManager.getSessionToken(),"application/json", searchRequestModel);
        loginResponseCall.enqueue(new Callback<List<SearchTaskListResponseModel>>() {
            @Override
            public void onResponse(Call<List<SearchTaskListResponseModel>> call, Response<List<SearchTaskListResponseModel>> response) {
                try {
                    // TODO NULL CHECK OF RESPONSE
                    if (response.body() != null) {
                        responseModel = response.body();
                        callAfterResponse(responseModel);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                yesButton.setVisibility(View.VISIBLE);
                                img.setVisibility(View.VISIBLE);
                                progressbar.setVisibility(View.GONE);
                                titleText.setText("Download Completed");
                            }
                        }, 1000);
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(getActivity(), response.errorBody().string().toString(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<List<SearchTaskListResponseModel>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage() + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "failure" + t.getMessage() + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });


    }
    public void callAfterResponse(List<SearchTaskListResponseModel> response) {
        adapter = new RVSyncTaskListAdapter(getActivity(), response);
        mrecyclerView.setAdapter(adapter);
        adapter.setClicklistner(this);




    }
    @Override
    public void taskClick(Integer position) {
        Bundle bundle = new Bundle();
        bundle.putInt("taskId", responseModel.get(position).getTaskID());
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(bundle);
        Toast.makeText(getContext(), ""+responseModel.get(position).getStatus(), Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, null).addToBackStack(null).commit();
    }*/
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Sync Data");

    }

    public void callStatusUpdateApi(final String isCompleted,final boolean workstatus) {
        try {
            if (Constants.homeStatusPOJO != null) {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("UpdateLatitude",  Constants.CURRENT_LAT);
                jsonObject.put("UpdateLongitude", Constants.CURRENT_LNG);
                jsonObject.put("Status", "Off-Site");
                jsonObject.put("AssesmentSiteId", Constants.homeStatusPOJO.getASSESMENTID());
                jsonObject.put("EmployeeID", 0);
                jsonObject.put("WorkOrderNo", Constants.homeStatusPOJO.getASSESMENTID());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = dateFormat.format(new Date());
                jsonObject.put("EndDate", dateTime);
                jsonObject.put("WorkOrderStatus", isCompleted);

                Log.v("json",jsonObject.toString());

                new SendData(getContext(), jsonObject, UrlClass.UPDATE_STATUS_URL, new OnTaskCompleted<String>() {
                    @Override
                    public void onTaskCompleted(String response) {
                        Log.d("UpdateResponse", response);
                        if (Constants.SEND_STATUS == 200) {
                            tv_go_on_site.setText("Off-Site");
                            tv_go_on_site.setBackgroundDrawable(getResources().getDrawable(R.drawable.go_off_site_design));
                            tv_go_on_site.setEnabled(false);
                          //  callCheckOnSiteApi();
                        }

                    }
                }, true).execute();
            }
        } catch (Exception e) {
            Log.d("UpdateException", e.toString());
        }
    }


    public void
    callCheckOnSiteApi() {
        new GetApiCallback(getContext(), UrlClass.BASE_URL+"api/Order/getactivity" , new OnTaskCompleted<String>() {
            @Override
            public void onTaskCompleted(String response) {
                try {
                    Log.d("CheckStatusResponse", response);
                    Constants.homeStatusPOJO = new Gson().fromJson(response, HomeStatusPOJO.class);
                    if (Constants.homeStatusPOJO.getSTATUS().equals("On-Site")) {
                        tv_go_on_site.setText(Constants.homeStatusPOJO.getSTATUS());
                        tv_go_on_site.setBackgroundDrawable(getResources().getDrawable(R.drawable.go_on_site_bg_design));
                        tv_go_on_site.setEnabled(true);
                        workno=Constants.homeStatusPOJO.getWORK_ORDER_ID();
                     //   workorderno=Constants.homeStatusPOJO.getASSESMENTID();

                        String dist = UtilityFunction.calculateDistance( Constants.CURRENT_LAT , Constants.CURRENT_LNG, Constants.homeStatusPOJO.getLATITUDE(), Constants.homeStatusPOJO.getLONGITUDE(), Constants.PROVIDER);
                        distance = Double.parseDouble(dist);
                        if (distance <= DISTANCE) {

                        } else {
                            SharedPreferences mSharedPreferences =getContext().getSharedPreferences("TASK_ID", 0);
                            if (mSharedPreferences != null)
                                mSharedPreferences.edit().remove("assess").commit();
                            callStatusUpdateApi("Completed", true);
                        }



                        
                    } else if (Constants.homeStatusPOJO.getSTATUS().equals("Off-Site")) {
                        tv_go_on_site.setText("Off-Site");
                        tv_go_on_site.setBackgroundDrawable(getResources().getDrawable(R.drawable.go_off_site_design));
                        tv_go_on_site.setEnabled(false);
                        workno=0;
                        workorderno="";
                    }
                } catch (Exception e) {
                    Log.d("Exception", e.toString());
                }

            }
        }, true).execute();
    }



}

package com.workorder.app.webservicecallback;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.workorder.app.ConnectivityReceiver;
import com.workorder.app.util.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.params.SyncBasicHttpParams;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;

public class SendData extends AsyncTask<Object, Void, Object> {
    public OnTaskCompleted<String> listener;
    public ProgressDialog dialog;
    Context context;
    String url="";
    JSONObject jsonObject;
    public String response="";
    int status_code = 0;

    boolean isDialog;

    public SendData(Context context, JSONObject jsonObject, String url, OnTaskCompleted<String> listener, boolean isDialog)
    {
        this.url=url;
        this.jsonObject=jsonObject;
        this.context=context;
        this.isDialog=isDialog;
        this.listener=listener;

    }


    @Override
    protected Object doInBackground(Object... objects) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            //  HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000); //Timeout Limit
            HttpResponse httpResponse;

            Log.d("URL",url);
            HttpPost httpPost = new HttpPost(url);
            StringEntity se = null;

            se = new StringEntity(jsonObject.toString());

            Log.d("JSONOBJECT", jsonObject.toString());
            httpPost.setEntity(se);
            httpPost.setHeader(new BasicHeader("Content-type", "application/json"));
           httpPost.setHeader(new BasicHeader("Authorization", "Bearer " + Constants.loginPOJO.getAccessToken()));

            //  response = httpClient.execute(httpPost);

            HttpParams httpParams = new SyncBasicHttpParams();
            int timeoutConnection = 5000;
            HttpConnectionParams.setConnectionTimeout(httpParams, timeoutConnection);
            int timeoutSocket = 5000;
            HttpConnectionParams.setSoTimeout(httpParams, timeoutSocket);
            HttpContext localContext = new BasicHttpContext();

            httpResponse = httpClient.execute(httpPost, localContext);
            status_code = httpResponse.getStatusLine().getStatusCode();
            //Constants.status_code=status_code;
            Log.d("Senddata_StatusCode", String.valueOf(status_code));
           // Log.d("Send_Response", httpResponse.getEntity().getContent().toString());

            // Log.d("Response",);
            /*Checking response */
            if (httpResponse != null) {
                InputStream in = httpResponse.getEntity().getContent(); //Get the data in the entity
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder total = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    total.append(line).append('\n');
                }

                Log.d("Total",total.toString());

                Constants.SEND_STATUS=status_code;
                response=total.toString();
              //  Constants.getLocationPOJOList= Arrays.asList(new Gson().fromJson(total.toString(), GetLocationPOJO[].class));

            }

        } catch (Exception e) {
            e.printStackTrace();
            dialog.dismiss();
            Log.d("SendException",e.toString());
        }

        return null;
    }



    @Override
    protected void onPreExecute() {
        dialog=new ProgressDialog(context);
        dialog.setCancelable(false);
        if (isDialog)
        {
            dialog.setMessage("Please wait");
            dialog.show();

        }
    }

    @Override
    protected void onPostExecute(Object o) {
        if (status_code==200)
        {
           /* for (int i=0;i<Constants.getLocationPOJOList.size();i++) {
                Log.d("SiteId ("+String.valueOf(i)+")", Constants.getLocationPOJOList.get(i).getLocationID());
            }*/

            try {
                JSONObject jsonObject1=new JSONObject(response);

            } catch (JSONException e) {
               // dialog.dismiss();
               // listener.onTaskCompleted(response);
               Log.d("Exception",e.toString());
                e.printStackTrace();
            }

            dialog.dismiss();
            listener.onTaskCompleted(response);

        }else   if (status_code==401)
        {
            Toast.makeText(context, "Invalid Username or Password", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }
        else{
            Toast.makeText(context, "Oops! something went wrong. Please try again after some time", Toast.LENGTH_SHORT).show();

            dialog.dismiss();
        }



    }
}

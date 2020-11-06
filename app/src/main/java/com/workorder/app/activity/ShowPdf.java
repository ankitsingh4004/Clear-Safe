package com.workorder.app.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.workorder.app.R;

public class ShowPdf extends AppCompatActivity {
    private WebView webView;
    String documentname;
    String documenturl;
    String workorderno;
    String status;
    String versionno;
    String pdfName;
    int assesmenttemplateid;
    int assesmentempid;
    int assesmentid;
    FloatingActionButton signFab;
    ImageView iv_back;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pdf);

        signFab = findViewById(R.id.fab_sign);
        documentname=getIntent().getStringExtra("documentname");
        status=getIntent().getStringExtra("status");
        documenturl=getIntent().getStringExtra("documenturl");
        versionno=getIntent().getStringExtra("versionno");
       // pdfName= getIntent().getStringExtra("pdf");
       // status= getIntent().getStringExtra("status");
     //   assesmenttemplateid= getIntent().getIntExtra("assesmenttemplateid",0);
        assesmentid= getIntent().getIntExtra("assesmentid",0);
      //  assesmentempid= getIntent().getIntExtra("assesmentempid",0);
        iv_back=findViewById(R.id.iv_back);
        tv_title=findViewById(R.id.tv_activity_name);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tv_title.setText(documentname);

        webView = (WebView) findViewById(R.id.webView);
        signFab.show();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        signFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status.equalsIgnoreCase("Signed")){
                    opentThanksYesClickDialog1("Document File already signed");
                }else {
                    Intent intent=new Intent(ShowPdf.this,SignatureInstActivity.class);
                    //    intent.putExtra("assesmenttemplateid",assesmenttemplateid);
                    intent.putExtra("documentname",documentname);
                    intent.putExtra("versionno",versionno);
                    intent.putExtra("assesmentid",assesmentid);
                    //   intent.putExtra("assesmentempid",assesmentempid);
                    startActivity(intent);
                }

            }
        });

        String url1 = Uri.encode("http://109.228.49.117:8097/"+documenturl+"/"+documentname);
        Log.v("url","http://109.228.49.117:8097/"+documenturl+"/"+documentname);
        String finalUrl = "http://docs.google.com/viewer?url=" + url1 + "&embedded=true";
        final WebView webView = findViewById(R.id.webView);
        Log.v("url",finalUrl);
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        // webView.getSettings().setUseWideViewPort(true);
        //  webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(finalUrl);
        webView.loadUrl("javascript:(function() { " +
                "document.querySelector('[role=\"toolbar\"]').remove();})()");
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                view.getSettings().setLoadsImagesAutomatically(true);
                webView.setVisibility(View.VISIBLE);
                //progressView.setVisibility(View.VISIBLE);

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                webView.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), description, Toast.LENGTH_SHORT).show();
                Log.e("error", description);

            }
        });


    }

    public void opentThanksYesClickDialog1(String message) {
        final Dialog dialog = new Dialog(ShowPdf.this);
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
}

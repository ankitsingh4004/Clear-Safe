package com.workorder.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.workorder.app.R;
import com.workorder.app.Util;


public class SplashActivity extends AppCompatActivity {
    boolean isInstalled;

    SharedPreferences preferences;
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //  getWindow().setNavigationBarColor(getResources().getColor(R.color.navigationbar));
        prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);


        // isInstalled = Util.isPackageInstalled(getApplicationContext().getPackageName(), this.getPackageManager());

        //  Log.d("appInstall", String.valueOf(isInstalled));

    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                finish();

                if (prefs.getBoolean("firstrun", true)) {
                    // Do first run stuff here then set 'firstrun' as false
                    //strat  DataActivity beacuase its your app first run
                    // using the following line to edit/commit prefs


                    prefs.edit().putBoolean("firstrun", false).commit();
                    startActivity(new Intent(SplashActivity.this, SelectCountry.class));
                    finish();


                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }

        }, 3000);



    }

}


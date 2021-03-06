package com.workorder.app.activity;

import android.location.Geocoder;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.workorder.app.R;
import com.workorder.app.util.Constants;
import com.workorder.app.util.UtilityFunction;

import java.util.ArrayList;
import java.util.List;

public class ShowAllSiteInMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Marker> markers=new ArrayList<>();
    List<LatLng> latLngList=new ArrayList<>();
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_site_in_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        iv_back=findViewById(R.id.iv_show_all_site_back);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapShowAllMarker);
        mapFragment.getMapAsync(this);

        for (int i=0;i< Constants.workOrderPOJOList.size();i++)
        {
            if((Constants.workOrderPOJOList.get(i).getLat()== null) || Constants.workOrderPOJOList.get(i).getLon()==null){

            }else {
                latLngList.add(new LatLng(Constants.workOrderPOJOList.get(i).getLat(),Constants.workOrderPOJOList.get(i).getLon()));

            }
        }
     /* latLngList.add(new LatLng(28.9845,77.7064));
        latLngList.add(new LatLng(28.6692,77.4538));
        latLngList.add(new LatLng(28.4595,77.0266));
        latLngList.add(new LatLng(28.7041,77.1025));
        latLngList.add(new LatLng(51.452880,-0.975820));
        latLngList.add(new LatLng(51.452641, -0.976510));
        latLngList.add(new LatLng(51.452530, -0.976920));*/

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
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
    Geocoder geocoder;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            for (int i = 0; i < latLngList.size(); i++) {
                Marker marker = mMap.addMarker(new MarkerOptions().position(latLngList.get(i)).title(UtilityFunction.getAddressFromLatlng(this, latLngList.get(i))));
                markers.add(marker);
            }
        }catch (Exception e){

        }


        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        new CountDownTimer(1500, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
             showAllMarker();
            }
        }.start();
    }

    public void showAllMarker()
    {
        if (markers.size()>0 && mMap!=null)
        {
            int padding=50;
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Marker marker : markers) {
                builder.include(marker.getPosition());
            }
            LatLngBounds bounds = builder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            mMap.moveCamera(cu);
        }
    }


}

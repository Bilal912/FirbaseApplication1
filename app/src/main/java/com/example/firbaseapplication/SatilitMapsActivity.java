package com.example.firbaseapplication;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class SatilitMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double lat, lng;
    private String address;
    private ArrayList<LatLng> differentLoc;
    private Location mylocation;
    private LatLng mylatLng;
    double distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satilit_maps);

        lat = getIntent().getDoubleExtra("Latitude", 0.0);
        lng = getIntent().getDoubleExtra("Longitude", 0.0);
        address = getIntent().getStringExtra("Address");
        differentLoc = new ArrayList<>();

        differentLoc.add(new LatLng(31.471930, 74.355126));
        differentLoc.add(new LatLng(31.467242, 74.315697));
        differentLoc.add(new LatLng(31.531592, 74.352767));
        differentLoc.add(new LatLng(31.556841, 74.326347));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);
        mMap.setTrafficEnabled(true);
//        mMap.getUiSettings().setAllGesturesEnabled(true);
//        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
////        mMap.getUiSettings().setZoomControlsEnabled(true);
//        mMap.getUiSettings().setCompassEnabled(true);


        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
//                LocationManager locationManager = (LocationManager)
//                        getSystemService(Context.LOCATION_SERVICE);
//                Criteria criteria = new Criteria();
//
//                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    Activity#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for Activity#requestPermissions for more details.
//                    return true;
//                }
//                Location mlocation = locationManager.getLastKnownLocation(locationManager
//                        .getBestProvider(criteria, false));

                mylatLng = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(mylatLng) );
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mylatLng,16.5f));

                LatLng zxc = new LatLng(31.471930, 74.355126);
                mMap.addMarker(new MarkerOptions().position(zxc) );
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(zxc,16.5f));

                mMap.addPolyline(new PolylineOptions().add(mylatLng).add(zxc).width(5f).color(Color.RED));



//                Location locationA = new Location("");
//                locationA.setLatitude(31.576010);
//                locationA.setLongitude(74.306610);
//                Location locationB = new Location("");
//                locationB.setLatitude(31.471930);
//                locationB.setLongitude(74.355126);
//                distance = locationA.distanceTo(locationB)/1000;
//             //   kmeter.setText(String.valueOf(distance));
//
//                Toast.makeText(getApplicationContext(), ""+distance, Toast.LENGTH_LONG).show();

                return false;
            }
        });

        // Add a marker in Sydney and move the camera
//        LatLng  xyz = new LatLng(lat, lng);
//        mMap.addMarker(new MarkerOptions().position(xyz).title(address));
//         mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(xyz,6f));


//         for (LatLng w:differentLoc){
//
//             mMap.addMarker(new MarkerOptions().position(w));
//         }
   //     mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}

package com.example.firbaseapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class CityLocationActivity extends AppCompatActivity {
    private EditText clname,clcity,clcountry,claddress;
    private LocationManager manager;
    private LocationListener listener;
    private double lat,lng;
    private PlaceAddress addr;
    private Button map_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_location);

        clname = findViewById(R.id.clname);
        clcity = findViewById(R.id.clcity);
        clcountry = findViewById(R.id.clcountry);
        claddress = findViewById(R.id.claddress);
        map_btn = findViewById(R.id.btn_map);
        map_btn.setEnabled(false);

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                lat = location.getLatitude();
                lng = location.getLongitude();
                addr = getCompleteAddressString(lat,lng);



                claddress.setText(addr.getAddress());
                clcity.setText(addr.getCity());
                clcountry.setText(addr.getCountry());

                if (manager != null){
                    manager.removeUpdates(listener);
                    map_btn.setEnabled(true);
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(i,1);

            }
        };
        abc();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                abc();
                break;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
        }

    }

    public void abc(){
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
        &&
        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                         Manifest.permission.ACCESS_COARSE_LOCATION},1);
                return;
            }
        }
        manager.requestLocationUpdates("gps",5000,0,listener);
    }



    private PlaceAddress getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        PlaceAddress pa = null;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
               String address  = addresses.get(0).getAddressLine(0);
               String country =  addresses.get(0).getCountryName();
               String city =  addresses.get(0).getLocality();
               pa = new PlaceAddress(address,country,city);

            } else {

                Toast.makeText(this, "No Address returned!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Canont get Address!", Toast.LENGTH_SHORT).show();

        }
        return pa;
    }

    public void googlemap(View view) {
        Intent intent = new Intent(getApplicationContext(),SatilitMapsActivity.class);
        intent.putExtra("Latitude",lat);
        intent.putExtra("Longitude",lng);
        intent.putExtra("Address",addr.getAddress());
        startActivity(intent);
    }
}

package com.example.touristguide.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.touristguide.R;
import com.example.touristguide.Storage.LocalStorage;
import com.example.touristguide.Utils.GpsUtils;
import com.example.touristguide.Utils.NetworkCheck;
import com.example.touristguide.Utils.SharedPreferenceData;
import com.example.touristguide.databinding.ActivitySplashBinding;

import java.util.List;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    public  static  int TIME_OUT = 3000;

    private boolean isFirstTime = true;
    private  boolean isGPS= false;
    protected LocationManager locationManager;
    private LocationListener mLocationListener;
    ProgressDialog progressDialog;
    public static final long LOCATION_REFRESH_TIME = 5000;
    public static final float LOCATION_REFRESH_DISTANCE = 5;
    public static String latitude;
    public static String longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


            }
        },TIME_OUT);
    }

    @Override
    public void onResume(){
        super.onResume();
        if (isFirstTime)
            fetchLocation();
    }

    private void fetchLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }else {
            CheckGPSStatus();
        }
    }

    private void CheckGPSStatus(){
        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                if (isGPSEnable){
                    isGPS = true;
                    if (NetworkCheck.isConnected(SplashActivity.this)){
                        getCurrentLocation();
                    }else {
                        Toast.makeText(SplashActivity.this, getResources().getString(R.string.please_enable_your_mobile_data), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else {
                    isGPS = false;
                    Toast.makeText(SplashActivity.this, getResources().getString(R.string.please_enable_your_gps), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getCurrentLocation() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching device location");
        progressDialog.setCancelable(false);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        progressDialog.show();
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                if (location != null) {
                        isFirstTime = false;
                    progressDialog.dismiss();
                    latitude = ""+location.getLatitude();
                    longitude = ""+location.getLongitude();
                    LocalStorage.setLatitude(SplashActivity.this, latitude);
                    LocalStorage.setLongitude(SplashActivity.this, longitude);
                    getCompleteAddressString(location.getLatitude(), location.getLongitude());
                    goFurther();
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
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE, mLocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            fetchLocation();
        }
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        String pinCode = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                pinCode = returnedAddress.getPostalCode();
                LocalStorage.setPincode(SplashActivity.this,pinCode);
                LocalStorage.setDeliveryAddress(SplashActivity.this,strAdd);
                Log.e("CurrentLocation","pinCode "+pinCode);
                Log.e("CurrentLocation","adrs "+ strReturnedAddress.toString());
            } else {
                Log.w("Myloctionaddress", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("Myloctionaddress", "Canont get Address!");
        }
        return strAdd;
    }

    private void goFurther(){
        locationManager.removeUpdates(mLocationListener);
        if(new SharedPreferenceData(SplashActivity.this).IsLogin()){
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(SplashActivity.this,Login_Activity.class);
            startActivity(intent);
            finish();
        }
    }


}
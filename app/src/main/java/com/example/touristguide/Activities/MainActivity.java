package com.example.touristguide.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.example.touristguide.Fragments.HomeFragment;
import com.example.touristguide.Fragments.ProfileFragment;
import com.example.touristguide.R;
import com.example.touristguide.Utils.AppUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity  implements LocationListener {

    BottomNavigationView BottomNevigation;
    public boolean isNeedToOpenHomeFragment = false;
    private boolean doubleBackToExitPressedOnce = false;
    //String address="null";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //address = getIntent().getStringExtra("address");


        BottomNevigation = findViewById(R.id.BottomBar);
        Fragment fragment = new HomeFragment();
        AppUtils.goNextFragmentAdd(this, fragment);
        BottomNevigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){

                    case R.id.ic_Home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.ic_User:
                        fragment = new ProfileFragment();
                        break;

                    default:
                        fragment = new HomeFragment();
                        break;

                }
                AppUtils.goNextFragmentReplace(MainActivity.this, fragment);
                return true;
            }
        });
    }



    @Override
    public void onBackPressed(){
        //Fragment frag = getSupportFragmentManager().findFragmentById(R.id.SlideCons);
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.SlideCons);
            if (!(fragment instanceof HomeFragment)) {
                isNeedToOpenHomeFragment = true;
            } else {
                isNeedToOpenHomeFragment = false;
            }
            if (isNeedToOpenHomeFragment) {
                isNeedToOpenHomeFragment = false;
                BottomNevigation.setSelectedItemId(R.id.ic_Home);
                AppUtils.goNextFragmentReplace(this,new HomeFragment());
            } else {
                if (doubleBackToExitPressedOnce) {

                    new AlertDialog.Builder(this)
                            .setIcon(R.drawable.splash_logo)
                            .setTitle("exit")
                            .setCancelable(false)
                            .setMessage("Are you sure want to exit")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //turnGPSOff();
                                    finishAffinity();
                                    MainActivity.this.finish();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }

                this.doubleBackToExitPressedOnce = true;
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}
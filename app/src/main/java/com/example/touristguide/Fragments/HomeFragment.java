package com.example.touristguide.Fragments;

import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touristguide.Activities.MainActivity;
import com.example.touristguide.Adapters.ViewPagerAdapter;
import com.example.touristguide.ApiCall.Models.CategoryResBean;
import com.example.touristguide.ApiCall.Presenters.CategoryPresenter;
import com.example.touristguide.ApiCall.View.ICategoryView;
import com.example.touristguide.R;
import com.example.touristguide.Storage.LocalStorage;
import com.example.touristguide.Utils.AppUtils;
import com.example.touristguide.Utils.NetworkCheck;
import com.example.touristguide.Utils.SharedPreferenceData;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment implements ICategoryView, LocationListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    TextView txtSearch,txtLocation;
    ImageView imageView;
    ViewPagerAdapter viewPagerAdapter;
    List<CategoryResBean.DataItem>list = new ArrayList<>();
    CategoryPresenter categoryPresenter;
    SharedPreferenceData profileData;

    LocationManager locationManager;
    


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryPresenter = new CategoryPresenter();
        categoryPresenter.setView(this);
        profileData = new SharedPreferenceData(getActivity());

        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        
        getlocation();


        if(NetworkCheck.isConnected(getContext())){
            categoryPresenter.CategoryCall(getActivity(),profileData.getACCESS_TOKEN());
        }else{
            NetworkCheck.showNetworkFailureAlert(getActivity());
        }

        tabLayout= view.findViewById(R.id.tabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        txtSearch = view.findViewById(R.id.txt_search);
        txtLocation = view.findViewById(R.id.text_Location);
        imageView = view.findViewById(R.id.ic_Search);

       txtLocation.setText(LocalStorage.getDeliveryAddress(getContext()));
        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SearchFragment();
                AppUtils.goNextFragmentAdd(getContext(),fragment);
            }
        });

        return view;
    }


    @SuppressLint("MissingPermission")
    private void getlocation() {
        try{
            locationManager = (LocationManager) getActivity().getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5 , (LocationListener) getContext());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onCategorySucess(CategoryResBean item) {
        list.clear();
        if(item.isStatus()){
            list.addAll(item.getData());
            for (int i = 0 ; i<list.size(); i++){
                tabLayout.addTab(tabLayout.newTab().setText(list.get(i).getName()));
            }
            viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),list);
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.setOffscreenPageLimit(1);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            viewPager.setCurrentItem(0);
        }

    }

    @Override
    public void enableLoadingBar(Context context, boolean enable) {

    }

    @Override
    public void onError(String reason) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        Toast.makeText(getContext(), ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();

        try {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getCountryName();
            txtLocation.setText(address);

        }catch (Exception e){
            e.printStackTrace();
        }

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
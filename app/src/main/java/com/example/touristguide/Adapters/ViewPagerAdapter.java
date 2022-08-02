package com.example.touristguide.Adapters;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.touristguide.ApiCall.Models.CategoryResBean;
import com.example.touristguide.Fragments.Hotels_Fragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    Hotels_Fragment fragment = null;
    private List<CategoryResBean.DataItem>list = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm, List<CategoryResBean.DataItem> list) {
        super(fm);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        for(int i = 0; i <list.size(); i++ ){
            if(i == position){
                fragment = new Hotels_Fragment(list.get(position));
            }
        }
        return fragment;

    }

    @Override
    public CharSequence getPageTitle(int position){
        return super.getPageTitle(position);
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}

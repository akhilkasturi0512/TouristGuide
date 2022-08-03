package com.example.touristguide.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.touristguide.Adapters.SearchItemListApdapter;
import com.example.touristguide.ApiCall.Models.HotelsPropertyResBean;
import com.example.touristguide.ApiCall.Models.SearchResBean;
import com.example.touristguide.ApiCall.Presenters.SearchPresenter;
import com.example.touristguide.ApiCall.View.ISearchView;
import com.example.touristguide.R;
import com.example.touristguide.Utils.AppUtils;
import com.example.touristguide.Utils.NetworkCheck;
import com.example.touristguide.Utils.SharedPreferenceData;
import com.example.touristguide.databinding.FragmentSearchBinding;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements ISearchView,SearchItemListApdapter.ItemClickListner {

    FragmentSearchBinding binding;
    SearchItemListApdapter searchItemListApdapter;
    ArrayList<SearchResBean.DataItem> list= new ArrayList<>();
    SharedPreferenceData profileData;
    SearchPresenter presenter;
    SearchResBean.DataItem dataItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         binding = DataBindingUtil .inflate(inflater,R.layout.fragment_search, container, false);
         presenter = new SearchPresenter();
         presenter.setView(this);
         profileData = new SharedPreferenceData(getContext());


         searchItemListApdapter = new SearchItemListApdapter(getContext(),list,this);
         binding.ListView.setAdapter(searchItemListApdapter);

         binding.edtSearch.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void afterTextChanged(Editable editable) {

                 if(NetworkCheck.isConnected(getContext())){
                     presenter.SearchCall(getActivity(),profileData.getACCESS_TOKEN(),editable.toString());
                 }else{
                     NetworkCheck.showNetworkFailureAlert(getContext());
                 }

             }
         });


         return binding.getRoot();
    }

    @Override
    public void onSearchSucess(SearchResBean item) {
        list.clear();
        if(item.isStatus()){
            list.addAll(item.getData());
        }
        searchItemListApdapter.notifyDataSetChanged();
    }

    @Override
    public void enableLoadingBar(Context context, boolean enable) {

    }

    @Override
    public void onError(String reason) {

    }

    @Override
    public void onItemClick(int position) {
        Fragment fragment = new Hotel_Detail_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("property_id",""+list.get(position).getId());
        fragment.setArguments(bundle);
        AppUtils.goNextFragmentAdd(getActivity(),fragment);

    }
}
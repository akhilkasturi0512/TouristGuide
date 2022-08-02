package com.example.touristguide.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.touristguide.Adapters.Hotels_Adapter;
import com.example.touristguide.ApiCall.Models.CategoryResBean;
import com.example.touristguide.ApiCall.Models.HotelsPropertyResBean;
import com.example.touristguide.ApiCall.Presenters.HotelPropertyListPresenter;
import com.example.touristguide.ApiCall.View.IHotelsPropertyView;
import com.example.touristguide.R;
import com.example.touristguide.Utils.AppUtils;
import com.example.touristguide.Utils.NetworkCheck;
import com.example.touristguide.Utils.SharedPreferenceData;

import java.util.ArrayList;


public class Hotels_Fragment extends Fragment implements Hotels_Adapter.ItemClickListner, IHotelsPropertyView {

    Hotels_Adapter hostels_adapter;
    RecyclerView rvHostel;
    SharedPreferenceData profileData;
    ArrayList<HotelsPropertyResBean.DataItem>list = new ArrayList<>();
    HotelPropertyListPresenter hotelPropertyListPresenter;
    CategoryResBean.DataItem dataItem;
    HotelsPropertyResBean.DataItem dataItem1;

    public Hotels_Fragment(CategoryResBean.DataItem dataItem){
        this.dataItem = dataItem;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater,container,savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_hostels_, container, false);

        //dataItem = (CategoryResBean.DataItem)getArguments().getSerializable("data");

        hotelPropertyListPresenter = new HotelPropertyListPresenter();
        hotelPropertyListPresenter.setView(this);

        profileData = new SharedPreferenceData(getActivity());

        rvHostel = view.findViewById(R.id.rv_Hostels);

        if(NetworkCheck.isConnected(getContext())){
            hotelPropertyListPresenter.PropertyListCall(getActivity(), profileData.getACCESS_TOKEN(),""+dataItem.getId());

        }else{
            NetworkCheck.showNetworkFailureAlert(getContext());
        }

        return view;
    }

    @Override
    public void OnItemClicked(int position) {
        /* Intent intent = new Intent(getActivity(),new Hotel_Detail_Activity().getClass());
         intent.putExtra("property_id",list.get(position));
         startActivity(intent);*/
        Fragment fragment = new Hotel_Detail_Fragment();
        Bundle bundle = new Bundle();
        //bundle.putSerializable("property_id",list.get(position));
        bundle.putString("property_id",""+list.get(position).getId());
        fragment.setArguments(bundle);
        AppUtils.goFragmentAddWithoutBackStack(getContext(),fragment);

    }

    @Override
    public void onPropertyListSuccess(HotelsPropertyResBean item) {
        list.clear();
        if(item.isStatus() && item.getData().size()>0){
            list.addAll(item.getData());
        }
        hostels_adapter = new Hotels_Adapter(list,getContext(),this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3,RecyclerView.VERTICAL,false);
        rvHostel.setLayoutManager(gridLayoutManager);
        rvHostel.setAdapter(hostels_adapter);


    }

    @Override
    public void enableLoadingBar(Context context, boolean enable) {

    }

    @Override
    public void onError(String reason) {

    }
}
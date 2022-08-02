package com.example.touristguide.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.touristguide.Adapters.Detail_Hostel_Adapter;
import com.example.touristguide.ApiCall.Models.HotelsPropertyResBean;
import com.example.touristguide.ApiCall.Models.PropertyDetailResBean;
import com.example.touristguide.ApiCall.Models.SaveBookingResBean;
import com.example.touristguide.ApiCall.Presenters.PropertyDetailPresenter;
import com.example.touristguide.ApiCall.View.IPropertyDetailView;
import com.example.touristguide.R;
import com.example.touristguide.Utils.ApiConstants;
import com.example.touristguide.Utils.AppUtils;
import com.example.touristguide.Utils.NetworkCheck;
import com.example.touristguide.Utils.SharedPreferenceData;
import com.example.touristguide.databinding.FragmentHotelDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Hotel_Detail_Fragment extends Fragment implements Detail_Hostel_Adapter.ItemClickListner,IPropertyDetailView{

    Detail_Hostel_Adapter detail_hostel_adapter;
    List<PropertyDetailResBean.Data.PropertyImagesItem> list = new ArrayList<>();
    PropertyDetailPresenter presenter;
    PropertyDetailResBean propertyDetailResBean;
    HotelsPropertyResBean.DataItem dataItem;
    SharedPreferenceData profileData;
    FragmentHotelDetailBinding binding;
    String property;

    Dialog dialog;

    int year;
    int month;
    int day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil .inflate(inflater,R.layout.fragment_hotel__detail_, container, false);

        //dataItem = (HotelsPropertyResBean.DataItem)getArguments().getSerializable("property_id")
        property = getArguments().getString("property_id");
        presenter = new PropertyDetailPresenter();
        presenter.setView(this);
        profileData = new SharedPreferenceData(getContext());

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.booking_dialog_box);
        //dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_bg_solid_corner_20));
        dialog.setCancelable(false);

        ImageView imgCross = dialog.findViewById(R.id.img_Cross);
        imgCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeFragment();
                AppUtils.goNextFragmentAdd(getContext(),fragment);
                dialog.dismiss();
            }
        });

        if(NetworkCheck.isConnected(getContext())){
            presenter.PropertyDetailCall(getActivity(),profileData.getACCESS_TOKEN(),property);
        }else{
            NetworkCheck.showNetworkFailureAlert(getActivity());
        }

        binding.btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.txtSelectDate.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please select date", Toast.LENGTH_SHORT).show();
                }
                else if(binding.edtDescription.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please write some description", Toast.LENGTH_SHORT).show();
                }
                else if(NetworkCheck.isConnected(getActivity())){
                    presenter.SaveBookingCall(getActivity(),profileData.getACCESS_TOKEN(),""+dataItem.getId(),
                            binding.txtSelectDate.getText().toString(),binding.edtDescription.getText().toString());

                }

            }
        });

        detail_hostel_adapter = new Detail_Hostel_Adapter(list, getContext(),this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false);
        binding.rvDetailHostels.setLayoutManager(gridLayoutManager);
        binding.rvDetailHostels.setAdapter(detail_hostel_adapter);


        final Calendar calendar = Calendar.getInstance();
        binding.imgDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        //txtSelectDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                        binding.txtSelectDate.setText(year+"/" + (month+1)+"/" +day);
                    }
                },year,month,day);

                datePickerDialog.show();
            }
        });


        return binding.getRoot();
    }

    @Override
    public void OnItemClicked(int position) {

    }

    @Override
    public void onPropertyDetailSuccess(PropertyDetailResBean item) {
        list.clear();
        if(item.isStatus()){
            //PROPERTY_ID = ""+item.getData().getId();
            Picasso.get().load(ApiConstants.BASE_IMAGE_URL + item.getData().getImage()).into(binding.imgHostel);
            list.addAll(item.getData().getPropertyImages());
            propertyDetailResBean = item;
            binding.txtAddress.setText(item.getData().getAddress());
            binding.txtPrice.setText(item.getData().getPrice());
            binding.txtHotelName.setText(item.getData().getBusinessName());

        }
        detail_hostel_adapter.notifyDataSetChanged();

    }

    @Override
    public void onSaveBookingSuccess(SaveBookingResBean item) {
        if(item.isStatus()){
            Toast.makeText(getActivity(), item.getMessage(), Toast.LENGTH_SHORT).show();
            dialog.show();


        }
    }

    @Override
    public void enableLoadingBar(Context context, boolean enable) {

    }

    @Override
    public void onError(String reason) {

    }
}
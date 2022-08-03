package com.example.touristguide.ApiCall.Presenters;

import android.app.Activity;
import android.widget.Toast;

import com.example.touristguide.ApiCall.Models.PropertyDetailResBean;
import com.example.touristguide.ApiCall.Models.SaveBookingResBean;
import com.example.touristguide.ApiCall.View.IPropertyDetailView;
import com.example.touristguide.TopperApp;
import com.example.touristguide.Utils.GoogleProgressDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyDetailPresenter extends BasePresenter<IPropertyDetailView>{

    GoogleProgressDialog googleProgressDialog;

    public void PropertyDetailCall(final Activity context,String accessToken,String property_id){

        googleProgressDialog = new GoogleProgressDialog(context);
        googleProgressDialog.showDialog();

        TopperApp.getInstance().getApiService().PropertyDetail("Bearer "+accessToken,property_id)
                .enqueue(new Callback<PropertyDetailResBean>() {
            @Override
            public void onResponse(Call<PropertyDetailResBean> call, Response<PropertyDetailResBean> response) {
                googleProgressDialog.dismiss();
                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onPropertyDetailSuccess(response.body());

                        }else {
                            Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        getView().onError(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    getView().onError(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<PropertyDetailResBean> call, Throwable t){

                try {
                    googleProgressDialog.dismiss();
                    t.printStackTrace();
                    getView().onError(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public  void SaveBookingCall(final Activity context,String accessToken,String property_id,String booking_date,
                                 String description ){
        googleProgressDialog = new GoogleProgressDialog(context);
        googleProgressDialog.showDialog();

        TopperApp.getInstance().getApiService().SaveBooking("Bearer "+accessToken,property_id,booking_date,description)
                .enqueue(new Callback<SaveBookingResBean>() {
            @Override
            public void onResponse(Call<SaveBookingResBean> call, Response<SaveBookingResBean> response) {
                googleProgressDialog.dismiss();
                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onSaveBookingSuccess(response.body());

                        }else {
                            Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        getView().onError(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    getView().onError(e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<SaveBookingResBean> call, Throwable t) {

                try {
                    googleProgressDialog.dismiss();
                    t.printStackTrace();
                    getView().onError(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}

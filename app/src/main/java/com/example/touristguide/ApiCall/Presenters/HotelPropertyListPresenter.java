package com.example.touristguide.ApiCall.Presenters;

import android.app.Activity;
import android.widget.Toast;

import com.example.touristguide.ApiCall.Models.HotelsPropertyResBean;
import com.example.touristguide.ApiCall.View.IHotelsPropertyView;
import com.example.touristguide.TopperApp;
import com.example.touristguide.Utils.GoogleProgressDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelPropertyListPresenter extends BasePresenter<IHotelsPropertyView>{


    GoogleProgressDialog googleProgressDialog;

    public void PropertyListCall(final Activity context,String accessToken,String cat_id){
       // getView().enableLoadingBar(context,true);
        googleProgressDialog = new GoogleProgressDialog(context);
        googleProgressDialog.showDialog();

        TopperApp.getInstance().getApiService().PropertyList("Bearer "+accessToken, cat_id).enqueue(new Callback<HotelsPropertyResBean>() {
            @Override
            public void onResponse(Call<HotelsPropertyResBean> call, Response<HotelsPropertyResBean> response) {
                //getView().enableLoadingBar(context,false);
                googleProgressDialog.dismiss();
                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onPropertyListSuccess(response.body());

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
            public void onFailure(Call<HotelsPropertyResBean> call, Throwable t) {
                try {
                    //getView().enableLoadingBar(context, false);
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

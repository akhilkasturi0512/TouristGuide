package com.example.touristguide.ApiCall.Presenters;

import android.app.Activity;
import android.widget.Toast;

import com.example.touristguide.ApiCall.Models.OtpResBean;
import com.example.touristguide.ApiCall.View.IOtpView;
import com.example.touristguide.TopperApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpPresenter extends BasePresenter<IOtpView>{
    public void OtpCall(final Activity context,String email,String otp){
        getView().enableLoadingBar(context,true);

        TopperApp.getInstance().getApiService().Otp(email,otp).enqueue(new Callback<OtpResBean>() {
            @Override
            public void onResponse(Call<OtpResBean> call, Response<OtpResBean> response) {
                getView().enableLoadingBar(context,false);

                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onOtpSucess(response.body());

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
            public void onFailure(Call<OtpResBean> call, Throwable t) {

                try {
                    getView().enableLoadingBar(context, false);
                    t.printStackTrace();
                    getView().onError(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}

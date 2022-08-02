package com.example.touristguide.ApiCall.Presenters;

import android.app.Activity;
import android.widget.Toast;

import com.example.touristguide.ApiCall.Models.RegisterResBean;
import com.example.touristguide.ApiCall.View.IRegisterView;
import com.example.touristguide.TopperApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter extends BasePresenter<IRegisterView>{

    public void RegisterCall(final Activity context,String name,String email,String mobile){

        getView().enableLoadingBar(context,true);

        TopperApp.getInstance().getApiService().Register(name,email,mobile).enqueue(new Callback<RegisterResBean>() {
            @Override
            public void onResponse(Call<RegisterResBean> call, Response<RegisterResBean> response) {
                getView().enableLoadingBar(context,false);

                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onRegisterSuccess(response.body());

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
            public void onFailure(Call<RegisterResBean> call, Throwable t) {

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

package com.example.touristguide.ApiCall.Presenters;

import android.app.Activity;
import android.widget.Toast;

import com.example.touristguide.ApiCall.Models.LoginResBean;
import com.example.touristguide.ApiCall.View.ILoginView;
import com.example.touristguide.TopperApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends BasePresenter<ILoginView>{

    public void LoginCall(final Activity context,String email_mobile){
        getView().enableLoadingBar(context,true);

        TopperApp.getInstance().getApiService().login(email_mobile).enqueue(new Callback<LoginResBean>() {
            @Override
            public void onResponse(Call<LoginResBean> call, Response<LoginResBean> response) {
                getView().enableLoadingBar(context,false);

                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onLoginSucess(response.body());

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
            public void onFailure(Call<LoginResBean> call, Throwable t) {

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

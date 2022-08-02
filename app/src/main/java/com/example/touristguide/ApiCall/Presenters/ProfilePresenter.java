package com.example.touristguide.ApiCall.Presenters;

import android.app.Activity;
import android.widget.Toast;

import com.example.touristguide.ApiCall.Models.ProfileResBean;
import com.example.touristguide.ApiCall.Models.UpdateProfileResBean;
import com.example.touristguide.ApiCall.View.IProfileView;
import com.example.touristguide.TopperApp;
import com.example.touristguide.Utils.GoogleProgressDialog;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter extends BasePresenter<IProfileView>{

    GoogleProgressDialog googleProgressDialog;

    public void ProfileCall(final Activity context,String accessToken){
        googleProgressDialog = new GoogleProgressDialog(context);
        googleProgressDialog.showDialog();

        TopperApp.getInstance().getApiService().Profile("Bearer "+accessToken).enqueue(new Callback<ProfileResBean>() {
            @Override
            public void onResponse(Call<ProfileResBean> call, Response<ProfileResBean> response) {
                googleProgressDialog.dismiss();

                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onProfileSuccess(response.body());

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
            public void onFailure(Call<ProfileResBean> call, Throwable t) {
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

    public void UpdateProfileCall(final Activity context, String accessToken,String name, String mobile, String email,
                                  MultipartBody.Part captureMediaFileProfile){
        googleProgressDialog = new GoogleProgressDialog(context);
        googleProgressDialog.showDialog();

        RequestBody reqUserName = RequestBody.create(MediaType.parse("multipart/form-data"), name);
        RequestBody reqMobile = RequestBody.create(MediaType.parse("multipart/form-data"), mobile);
        RequestBody reqEmail = RequestBody.create(MediaType.parse("multipart/form-data"), email);

        TopperApp.getInstance().getApiService().UpdateProfile("Bearer "+accessToken,reqUserName,reqMobile,reqEmail,captureMediaFileProfile)
                .enqueue(new Callback<UpdateProfileResBean>() {
                    @Override
                    public void onResponse(Call<UpdateProfileResBean> call, Response<UpdateProfileResBean> response) {
                        googleProgressDialog.dismiss();

                        try {
                            if (!handleError(response, context)) {
                                if (response.isSuccessful() && response.code() == 200){
                                    assert response.body() != null;
                                    getView().onUpdateProfileSuccess(response.body());

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
                    public void onFailure(Call<UpdateProfileResBean> call, Throwable t) {

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

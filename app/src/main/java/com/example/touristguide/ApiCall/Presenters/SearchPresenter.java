package com.example.touristguide.ApiCall.Presenters;

import android.app.Activity;
import android.widget.Toast;

import com.example.touristguide.ApiCall.Models.SearchResBean;
import com.example.touristguide.ApiCall.View.ISearchView;
import com.example.touristguide.TopperApp;
import com.example.touristguide.Utils.GoogleProgressDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter extends BasePresenter<ISearchView>{

    GoogleProgressDialog googleProgressDialog;

    public void SearchCall(final Activity context,String accessToken,String bri){


        TopperApp.getInstance().getApiService().Search("Bearer "+accessToken,bri).enqueue(new Callback<SearchResBean>() {
            @Override
            public void onResponse(Call<SearchResBean> call, Response<SearchResBean> response) {

                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onSearchSucess(response.body());

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
            public void onFailure(Call<SearchResBean> call, Throwable t) {

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

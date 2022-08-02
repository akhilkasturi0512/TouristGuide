package com.example.touristguide.ApiCall.Presenters;

import android.app.Activity;
import android.widget.Toast;

import com.example.touristguide.ApiCall.Models.CategoryResBean;
import com.example.touristguide.ApiCall.View.ICategoryView;
import com.example.touristguide.TopperApp;
import com.example.touristguide.Utils.GoogleProgressDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter extends BasePresenter<ICategoryView>{

    GoogleProgressDialog googleProgressDialog;

    public void CategoryCall(final Activity context,String accessToken){
        googleProgressDialog = new GoogleProgressDialog(context);
        googleProgressDialog.showDialog();

        TopperApp.getInstance().getApiService().Category("Bearer "+accessToken).enqueue(new Callback<CategoryResBean>() {
            @Override
            public void onResponse(Call<CategoryResBean> call, Response<CategoryResBean> response) {
                googleProgressDialog.dismiss();
                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onCategorySucess(response.body());

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
            public void onFailure(Call<CategoryResBean> call, Throwable t) {

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

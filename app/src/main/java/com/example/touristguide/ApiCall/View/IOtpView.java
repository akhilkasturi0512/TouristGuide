package com.example.touristguide.ApiCall.View;

import android.content.Context;

import com.example.touristguide.ApiCall.Models.OtpResBean;

public interface IOtpView extends IUtopperView{
    void onOtpSucess(OtpResBean  item);
}

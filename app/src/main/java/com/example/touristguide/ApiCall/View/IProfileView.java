package com.example.touristguide.ApiCall.View;

import com.example.touristguide.ApiCall.Models.ProfileResBean;
import com.example.touristguide.ApiCall.Models.UpdateProfileResBean;

public interface IProfileView extends IUtopperView{
    void onProfileSuccess(ProfileResBean item);
    void onUpdateProfileSuccess(UpdateProfileResBean item);
}

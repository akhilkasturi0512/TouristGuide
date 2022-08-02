package com.example.touristguide.ApiCall.View;

import com.example.touristguide.ApiCall.Models.PropertyDetailResBean;
import com.example.touristguide.ApiCall.Models.SaveBookingResBean;

public interface IPropertyDetailView extends IUtopperView{
    void onPropertyDetailSuccess(PropertyDetailResBean item);
    void onSaveBookingSuccess(SaveBookingResBean item);
}

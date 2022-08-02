package com.example.touristguide.ApiCall.View;

import com.example.touristguide.ApiCall.Models.HotelsPropertyResBean;

public interface IHotelsPropertyView extends IUtopperView{

    void onPropertyListSuccess(HotelsPropertyResBean item);
}

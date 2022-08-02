package com.example.touristguide.ApiCall.View;

import com.example.touristguide.ApiCall.Models.CategoryResBean;

public interface ICategoryView extends IUtopperView{
    void onCategorySucess(CategoryResBean item);
}

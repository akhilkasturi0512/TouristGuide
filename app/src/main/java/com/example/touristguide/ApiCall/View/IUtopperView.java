package com.example.touristguide.ApiCall.View;

import android.content.Context;

public interface IUtopperView {

    Context getContext();
    void enableLoadingBar(Context context,boolean enable);
    void onError(String reason);
}

package com.example.touristguide.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.touristguide.Utils.GoogleProgressDialog;
import com.example.touristguide.Utils.SharedPreferenceData;


public class BaseActivity extends AppCompatActivity {

    GoogleProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstantState){
        super.onCreate(savedInstantState);
    }

    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public static void hideSoftKeyboard(Activity activity) {
        final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public void enableLoadingBar(Context context, boolean enable) {
        if (enable) {
            loadProgressBar(context);
        } else {
            dismissProgressBar(context);
        }
    }

    public void loadProgressBar(Context context) {

        try {
            if (mProgressDialog == null)
                mProgressDialog = new GoogleProgressDialog(this);
            if ((context != null && !((Activity) context).isFinishing()))
                mProgressDialog.showDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void dismissProgressBar(Context context) {
        try {
            if (mProgressDialog != null) {
                if ((context != null && !((Activity) context).isFinishing())) {
                    mProgressDialog.dismiss();
                }
                mProgressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

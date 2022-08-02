package com.example.touristguide.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.touristguide.ApiCall.Models.OtpResBean;
import com.example.touristguide.ApiCall.Presenters.OtpPresenter;
import com.example.touristguide.ApiCall.View.IOtpView;
import com.example.touristguide.R;
import com.example.touristguide.Utils.NetworkCheck;
import com.example.touristguide.Utils.SharedPreferenceData;
import com.example.touristguide.Utils.SystemUtility;
import com.example.touristguide.Utils.Utils;
import com.example.touristguide.databinding.ActivityOtpBinding;

import okhttp3.internal.Util;

public class OtpActivity extends BaseActivity implements View.OnClickListener, IOtpView {

    ActivityOtpBinding binding;
    String email,Otp;
    OtpPresenter otpPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_otp);


        email=getIntent().getStringExtra("email");
        otpPresenter = new OtpPresenter();
        otpPresenter.setView(this);

        binding.btnVerify.setOnClickListener(this);
          Otp = binding.txtOtp1.getText().toString()+""+
                binding.txtOtp2.getText().toString()+""+
                binding.txtOtp3.getText().toString()+""+
                binding.txtOtp4.getText().toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_Verify:

                if(binding.txtOtp1.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter all field", Toast.LENGTH_SHORT).show();
                }
                else if(binding.txtOtp2.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter all field", Toast.LENGTH_SHORT).show();
                }
                else if(binding.txtOtp3.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter all field", Toast.LENGTH_SHORT).show();
                }
                else if(binding.txtOtp4.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter all field", Toast.LENGTH_SHORT).show();
                }
                else if(NetworkCheck.isConnected(this)){

                    otpPresenter.OtpCall(this,email,Otp);
                }
                else{
                    NetworkCheck.showNetworkFailureAlert(this);
                }
               /* SystemUtility.hideVirtualKeyboard(OtpActivity.this);
                if(Utils.stringNotEmpty(binding.txtOtp1.getText().toString().trim()) && Utils.stringNotEmpty(binding.txtOtp2.getText().toString().trim())
                && Utils.stringNotEmpty(binding.txtOtp3.getText().toString().trim() )&& Utils.stringNotEmpty(binding.txtOtp4.getText().toString().trim())){

                    if (NetworkCheck.isConnected(this)) {
                        otpPresenter.OtpCall(this,email,Otp);
                    }else{
                        NetworkCheck.showNetworkFailureAlert(this);
                    }

                }else{
                    Toast.makeText(this, "Please enter valid otp", Toast.LENGTH_SHORT).show();
                }
                break;*/
        }

    }

    @Override
    public void onOtpSucess(OtpResBean item){
        if(item.isStatus()){
            new SharedPreferenceData(this).SavedLoginData(item);
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
         toast(item.getMessage());

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onError(String reason) {

    }
}
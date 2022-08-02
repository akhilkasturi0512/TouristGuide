package com.example.touristguide.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touristguide.ApiCall.Models.LoginResBean;
import com.example.touristguide.ApiCall.Presenters.LoginPresenter;
import com.example.touristguide.ApiCall.View.ILoginView;
import com.example.touristguide.R;
import com.example.touristguide.Utils.NetworkCheck;
import com.example.touristguide.Utils.SharedPreferenceData;
import com.example.touristguide.Utils.Utils;
import com.example.touristguide.databinding.ActivityLoginBinding;

public class Login_Activity extends BaseActivity implements View.OnClickListener, ILoginView {


    LoginPresenter loginPresenter;
    ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginPresenter = new LoginPresenter();
        loginPresenter.setView(this);
        binding.btnLogin.setOnClickListener(this);
        binding.txtSignUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_Login:
                if(binding.edtEmail.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                }
                else if(!Utils.isEmailValid(binding.edtEmail.getText().toString())){
                    Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                }

                else if(NetworkCheck.isConnected(this)){
                    loginPresenter.LoginCall(this,binding.edtEmail.getText().toString());
                }
                else {
                    NetworkCheck.showNetworkFailureAlert(this);
                }
                break;

            case R.id.txt_SignUp:
                startActivity(new Intent(this,Register_Activity.class));
                finish();
                break;

            default:
                break;

        }

    }

    @Override
    public void onLoginSucess(LoginResBean item) {
        if(item.isStatus()){
            Intent intent = new Intent(this,OtpActivity.class);
            intent.putExtra("email",binding.edtEmail.getText().toString());
            finish();
            startActivity(intent);
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
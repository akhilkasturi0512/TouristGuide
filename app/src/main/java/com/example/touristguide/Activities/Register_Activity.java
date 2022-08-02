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

import com.example.touristguide.ApiCall.Models.RegisterResBean;
import com.example.touristguide.ApiCall.Presenters.RegisterPresenter;
import com.example.touristguide.ApiCall.View.IRegisterView;
import com.example.touristguide.R;
import com.example.touristguide.Utils.NetworkCheck;
import com.example.touristguide.Utils.Utils;
import com.example.touristguide.databinding.ActivityRegisterBinding;

public class Register_Activity extends BaseActivity implements View.OnClickListener, IRegisterView {

    ActivityRegisterBinding binding;

    RegisterPresenter registerPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        registerPresenter = new RegisterPresenter();
        registerPresenter.setView(this);

        binding.btnRegister.setOnClickListener(this);
        binding.txtSignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String name = binding.edtName.getText().toString();
        String email = binding.edtEmail.getText().toString();
        String mobile = binding.edtMobile.getText().toString();
        String password = binding.edtPassword.getText().toString();
        switch (view.getId()){
            case R.id.btn_Register:
                if(binding.edtName.getText().toString().isEmpty()){
                    Toast.makeText(Register_Activity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                }
                else if(binding.edtEmail.getText().toString().isEmpty()){
                    Toast.makeText(Register_Activity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                }
                else if(!Utils.isEmailValid(binding.edtEmail.getText().toString())){
                    Toast.makeText(Register_Activity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(binding.edtMobile.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(binding.edtMobile.getText().toString().length()!=10){
                    Toast.makeText(this, "Please enter valid number", Toast.LENGTH_SHORT).show();
                }
                else if(binding.edtPassword.getText().toString().isEmpty()){
                    Toast.makeText(Register_Activity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }
                else if(NetworkCheck.isConnected(this)){
                    registerPresenter.RegisterCall(this,name,email,mobile);
                }
                else{

                    NetworkCheck.showNetworkFailureAlert(Register_Activity.this);
                }
                break;

            case R.id.txt_SignIn:
                startActivity(new Intent(this,Login_Activity.class));
                finish();
                break;

            default:
                break;
        }

    }

    @Override
    public void onRegisterSuccess(RegisterResBean item) {
        if(item.isStatus()){
            Intent intent = new Intent(Register_Activity.this,Login_Activity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onError(String reason) {

    }


}
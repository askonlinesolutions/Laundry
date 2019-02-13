package com.laundry.ui.forgotPassword;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityForgotPasswordBinding;
import com.laundry.ui.DryCleaner.DryCleanerActivity;
import com.laundry.ui.LoginScreen.MainActivity;
import com.laundry.ui.changePassword.ChangePaawordActivity;
import com.laundry.ui.changePassword.vo.ChangePwdResponse;
import com.laundry.ui.forgotPassword.vo.ForgotPasswordResponse;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {

    ActivityForgotPasswordBinding binding;
    private static String TAG = ForgotPasswordActivity.class.getName();
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);

        init();

    }

    private void init() {
        binding.loginTitle.setOnClickListener(this);
        binding.forgotPasswordTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_title:
                onBackPressed();
                break;
            case R.id.forgot_password_tv:


                if (isNetworkConnected(this)) {
                    callForgotPasswordApi();
                } else {
                    Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
                }


//                Intent i = new Intent(ForgotPasswordActivity.this, DryCleanerActivity.class);
//                startActivity(i);

                break;
        }

    }

    private void callForgotPasswordApi() {

        email = binding.forgotEmailEt.getText().toString().trim();

        new Utility().showProgressDialog(this);
        Call<ForgotPasswordResponse> call = APIClient.getInstance().getApiInterface().forgotPassword(email);
        new ResponseListner(this).getResponse(call);


    }

    @Override
    public void onApiResponse(Object response) {

        if (response != null) {
            new Utility().hideDialog();
            try {
                if (response instanceof ForgotPasswordResponse) {
                    ForgotPasswordResponse forgotPasswordResponse = (ForgotPasswordResponse) response;
                    new Utility().hideDialog();
                    if (forgotPasswordResponse.isStatus()) {

                        Toast.makeText(this, forgotPasswordResponse.getMsg(), Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(this, forgotPasswordResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (Exception e) {
                Log.d("TAG", "onApiResponse: " + e.getMessage());
            }
        } else {
            new Utility().hideDialog();
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onApiFailure(String message) {

        new Utility().hideDialog();
        Log.d(TAG, "onApiFailure: " + message);

    }
}

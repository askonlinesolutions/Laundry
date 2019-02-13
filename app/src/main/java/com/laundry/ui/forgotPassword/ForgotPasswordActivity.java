package com.laundry.ui.forgotPassword;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.databinding.ActivityForgotPasswordBinding;
import com.laundry.ui.DryCleaner.DryCleanerActivity;
import com.laundry.ui.LoginScreen.MainActivity;
import com.laundry.ui.changePassword.ChangePaawordActivity;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityForgotPasswordBinding binding;
    private static String TAG = ForgotPasswordActivity.class.getName();


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


                Intent i = new Intent(ForgotPasswordActivity.this, DryCleanerActivity.class);
                startActivity(i);

                break;
        }

    }
}

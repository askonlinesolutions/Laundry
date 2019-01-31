package com.laundry.ui.forgotPassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.ui.LoginScreen.MainActivity;

public class ForgotPasswordActivity extends AppCompatActivity {
    TextView login_title,forgot_password_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        login_title=findViewById(R.id.login_title);
        forgot_password_btn=findViewById(R.id.activity_forgot_password);
        login_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        forgot_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForgotPasswordActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}

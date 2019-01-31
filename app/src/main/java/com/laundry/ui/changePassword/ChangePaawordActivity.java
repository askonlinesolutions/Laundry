package com.laundry.ui.changePassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.ui.LoginScreen.MainActivity;

public class ChangePaawordActivity extends AppCompatActivity {
TextView login_title,activity_forgot_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_paaword);
        login_title =findViewById(R.id.login_title);
        activity_forgot_password=findViewById(R.id.activity_forgot_password);
        forgetpassword();
        backbtn();
    }

    private void forgetpassword() {
        activity_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChangePaawordActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void backbtn() {
        login_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

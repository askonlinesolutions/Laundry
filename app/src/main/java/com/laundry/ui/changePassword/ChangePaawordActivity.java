package com.laundry.ui.changePassword;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.databinding.ActivityChangePaawordBinding;
import com.laundry.ui.LoginScreen.MainActivity;

public class ChangePaawordActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityChangePaawordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_paaword);

        init();

    }

    private void init() {
        binding.loginTitle.setOnClickListener(this);
        binding.updatePwd.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_title:
                onBackPressed();
                break;

            case R.id.update_pwd:
                Intent i = new Intent(ChangePaawordActivity.this, MainActivity.class);
                startActivity(i);
                break;

        }
    }
}

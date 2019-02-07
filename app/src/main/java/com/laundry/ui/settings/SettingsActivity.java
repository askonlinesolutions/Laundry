package com.laundry.ui.settings;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.laundry.R;
import com.laundry.databinding.ActivitySettingsBinding;
import com.laundry.ui.changePassword.ChangePaawordActivity;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        init();
    }

    private void init() {

        binding.settingBackIv.setOnClickListener(this);
        binding.passwordTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_back_iv:
                onBackPressed();
                break;

            case R.id.password_tv:
                startActivity(new Intent(SettingsActivity.this, ChangePaawordActivity.class));
                break;
        }
    }
}

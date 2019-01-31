package com.laundry.ui.editProfile;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.laundry.R;
import com.laundry.databinding.ActivityEditProfileBinding;
import com.laundry.ui.profile.ProfileActivity;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityEditProfileBinding binding;
    private boolean isVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);

        init();

    }

    private void init() {
        binding.editBackIv.setOnClickListener(this);
        binding.saveBtnTv.setOnClickListener(this);
        binding.eyeImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_back_iv:
                onBackPressed();
                break;

            case R.id.eye_image:
                if (isVisible) {
                    binding.passwordEt.setTransformationMethod(null);
                    isVisible = false;
                } else {
                    binding.passwordEt.setTransformationMethod(new PasswordTransformationMethod());
                    isVisible = true;
                }

                break;
            case R.id.save_btn_tv:
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
        }
    }
}

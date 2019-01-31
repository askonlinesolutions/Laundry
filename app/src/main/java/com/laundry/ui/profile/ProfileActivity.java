package com.laundry.ui.profile;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.laundry.R;
import com.laundry.databinding.ActivityProfileBinding;
import com.laundry.ui.MyPayment.PaymentMethodActivity;
import com.laundry.ui.editProfile.EditProfileActivity;
import com.laundry.ui.offer.OfferActivity;
import com.laundry.ui.settings.SettingsActivity;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        init();
    }

    private void init() {

        binding.backIv.setOnClickListener(this);
        binding.editIv.setOnClickListener(this);
        binding.defaultTv.setOnClickListener(this);
        binding.paymentMethodLayout.setOnClickListener(this);
        binding.offerLayout.setOnClickListener(this);
        binding.settingLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.edit_iv:
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
                this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                break;

            case R.id.payment_method_layout:
                Intent intentPay = new Intent(ProfileActivity.this, PaymentMethodActivity.class);
                startActivity(intentPay);
                this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                break;
            case R.id.setting_layout:
                Intent intentSetting = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(intentSetting);
                this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                break;

            case R.id.offer_layout:
                startActivity(new Intent(ProfileActivity.this, OfferActivity.class));
                this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                break;

        }

    }
}

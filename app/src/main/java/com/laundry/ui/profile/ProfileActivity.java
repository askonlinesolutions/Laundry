package com.laundry.ui.profile;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.Utils.Constant;
import com.laundry.Utils.MySharedPreference;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityProfileBinding;
import com.laundry.ui.DryCleaner.DryCleanerActivity;
import com.laundry.ui.MyPayment.PaymentMethodActivity;
import com.laundry.ui.changePassword.ChangePaawordActivity;
import com.laundry.ui.changePassword.vo.ChangePwdResponse;
import com.laundry.ui.editProfile.EditProfileActivity;
import com.laundry.ui.manageAddress.ManageAddressActivity;
import com.laundry.ui.offer.OfferActivity;
import com.laundry.ui.profile.vo.ProfileResponse;
import com.laundry.ui.settings.SettingsActivity;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {

    private ActivityProfileBinding binding;
    private String userId;
    private static String TAG = ProfileActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        getUser_Id();
        init();
    }

    private void init() {

        binding.backIv.setOnClickListener(this);
        binding.editIv.setOnClickListener(this);
        binding.defaultTv.setOnClickListener(this);
        binding.paymentMethodLayout.setOnClickListener(this);
        binding.offerLayout.setOnClickListener(this);
        binding.settingLayout.setOnClickListener(this);
//        binding.cameraIv.setOnClickListener(this);

        if (isNetworkConnected(this)) {
            callGetProfileApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }

    }

    private void getUser_Id() {
        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        userId = mySharedPreference.getUserId();
        Log.e("MyUserId", userId);
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

            case R.id.default_tv:
                startActivity(new Intent(ProfileActivity.this, ManageAddressActivity.class));
                this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                break;
        }

    }

    private void callGetProfileApi() {

        new Utility().showProgressDialog(this);
        Call<ProfileResponse> call = APIClient.getInstance().getApiInterface().getProgileDetails(userId);
        new ResponseListner(this).getResponse(call);

    }


    @Override
    public void onApiResponse(Object response) {
        if (response != null) {
            new Utility().hideDialog();
            try {
                if (response instanceof ProfileResponse) {
                    ProfileResponse profileResponse = (ProfileResponse) response;
                    new Utility().hideDialog();
                    if (profileResponse.isStatus()) {

                        Toast.makeText(this, " Record found Successfully..", Toast.LENGTH_SHORT).show();

                        binding.headUserNameTv.setText(profileResponse.getData().getUsermanage_first_name());
                        binding.userNameTv.setText(profileResponse.getData().getUsermanage_first_name());
                        binding.userEmailTv.setText(profileResponse.getData().getUsermanage_email());
                        binding.phoneNoTv.setText(profileResponse.getData().getUsermanage_contact());
//                        binding.userAddressTv.setText(profileResponse.getData().get);
//                        if (profileResponse.getData().getUsermanage_image() != null) {
//                            Picasso.with(this).
//                                    load(Constant.IMAGE_BASE_URL + profileResponse.getData().getUsermanage_image()) // URL or file
//                                    .into(binding.userImageIv);
//
//                        }


                    } else {
                        Toast.makeText(this, "No Record found !", Toast.LENGTH_SHORT).show();
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

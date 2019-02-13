package com.laundry.ui.settings;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivitySettingsBinding;
import com.laundry.ui.DryCleaner.DryCleanerActivity;
import com.laundry.ui.changePassword.ChangePaawordActivity;
import com.laundry.ui.changePassword.vo.ChangePwdResponse;
import com.laundry.ui.settings.vo.SettingResponse;

import retrofit2.Call;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {

    private ActivitySettingsBinding binding;
    private static String TAG = SettingsActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        init();
        callSettingapi();
    }

    private void callSettingapi() {

        new Utility().showProgressDialog(this);
        Call<SettingResponse> call = APIClient.getInstance().getApiInterface().setting("user_id","notification_order_status","notification_messageg_statu");
        new ResponseListner(this).getResponse(call);




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

    @Override
    public void onApiResponse(Object response) {

        if (response != null) {
            new Utility().hideDialog();
            try {
                if (response instanceof SettingResponse) {
                    SettingResponse settingResponse = (SettingResponse) response;
                    new Utility().hideDialog();
                    if (settingResponse.isStatus()) {

                        Toast.makeText(this, settingResponse.getMsg(), Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(ChangePaawordActivity.this, DryCleanerActivity.class);
//                        startActivity(i);


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

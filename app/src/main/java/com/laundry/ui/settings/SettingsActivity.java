package com.laundry.ui.settings;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.Utils.MySharedPreference;
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

import static com.laundry.Utils.Utility.isNetworkConnected;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {

    private ActivitySettingsBinding binding;
    private static String TAG = SettingsActivity.class.getName();
    private int orderStatus, msgStatus;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        getUser_Id();
        init();

    }


    private void getUser_Id() {
        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        userId = mySharedPreference.getUserId();
        Log.e("MyUserId", userId);
    }

    private void init() {

        binding.settingBackIv.setOnClickListener(this);
//        binding.saveSettingBtn.setOnClickListener(this);
        binding.orderStatusSwitch.setOnClickListener(this);
        binding.clientMsgSwitch.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_back_iv:
                onBackPressed();
                break;

        /*    case R.id.password_tv:
                startActivity(new Intent(SettingsActivity.this, ChangePaawordActivity.class));
                break;
*/
            case R.id.order_status_switch:

                if (binding.orderStatusSwitch.isChecked()) {
                    orderStatus = 1;
                } else {
                    orderStatus = 0;
                }

                if (isNetworkConnected(this)) {
                    callSettingApi();
                } else {
                    Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.client_msg_switch:


                if (binding.clientMsgSwitch.isChecked()) {
                    msgStatus = 1;
                } else {
                    msgStatus = 0;
                }

                if (isNetworkConnected(this)) {
                    callSettingApi();
                } else {
                    Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
                }

                break;





        }
    }


    private void callSettingApi() {

        new Utility().showProgressDialog(this);
        Call<SettingResponse> call = APIClient.getInstance().getApiInterface().updateSetting(userId, orderStatus, msgStatus);
        new ResponseListner(this).getResponse(call);


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

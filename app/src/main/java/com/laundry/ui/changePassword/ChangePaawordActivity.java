package com.laundry.ui.changePassword;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.maps.internal.ApiResponse;
import com.laundry.R;
import com.laundry.Utils.MySharedPreference;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityChangePaawordBinding;
import com.laundry.ui.DryCleaner.DryCleanerActivity;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.LoginScreen.MainActivity;
import com.laundry.ui.changePassword.vo.ChangePwdResponse;
import com.laundry.ui.myOrder.vo.MyOrderResponse;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class ChangePaawordActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {

    ActivityChangePaawordBinding binding;
    private String userId, oldPwd, newPwd, confirmPwd;
    private static String TAG = ChangePaawordActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_paaword);

        getUser_Id();
        init();

    }

    private void getUser_Id() {
        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        userId = mySharedPreference.getUserId();
        Log.e("MyUserId", userId);
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
                if (isAllFieldValide()) {
                    if (isNetworkConnected(this)) {
                        callChangePwdApi();
                    } else {
                        Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
                    }
                }


//                Intent i = new Intent(ChangePaawordActivity.this, MainActivity.class);
//                startActivity(i);
                break;

        }
    }

    private void callChangePwdApi() {
        oldPwd = binding.currentPassword.getText().toString().trim();
        newPwd = binding.newPassword.getText().toString().trim();
        confirmPwd = binding.confirmPassword.getText().toString().trim();

        new Utility().showProgressDialog(this);
        Call<ChangePwdResponse> call = APIClient.getInstance().getApiInterface().changePwd(userId, oldPwd, newPwd);
        new ResponseListner(this).getResponse(call);

    }

    @Override
    public void onApiResponse(Object response) {

        if (response != null) {
            new Utility().hideDialog();
            try {
                if (response instanceof ChangePwdResponse) {
                    ChangePwdResponse changePwdResponse = (ChangePwdResponse) response;
                    new Utility().hideDialog();
                    if (changePwdResponse.isStatus()) {

                        Toast.makeText(this, changePwdResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ChangePaawordActivity.this, DryCleanerActivity.class);
                        startActivity(i);


                    } else {
                        Toast.makeText(this, changePwdResponse.getMsg(), Toast.LENGTH_SHORT).show();
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


    private boolean isAllFieldValide() {

        if (binding.currentPassword.length() == 0 ||
                binding.newPassword.length() == 0 || binding.confirmPassword.length() == 0) {
            Toast.makeText(this, "please fill all blank  fiels!", Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (name.length() == 0) {
//            Toast.makeText(this, "Name can't be blank", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (!validateFirstName(name.getText().toString())) {
//            name.setError("Not a valid name!");
//            Toast.makeText(this, "Please enter valid name !", Toast.LENGTH_SHORT).show();
//            return false;
//        }


        if (!validatePassword(binding.currentPassword.getText().toString())) {
            binding.currentPassword.setError("Not a valid password!");
            Toast.makeText(this, "Please enter valid password !", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!validatePassword(binding.newPassword.getText().toString())) {
            binding.newPassword.setError("Not a valid confirm password!");
            Toast.makeText(this, "Please enter valid password !", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!binding.newPassword.getText().toString().equals(binding.confirmPassword.getText().toString())) {
            Toast.makeText(this, "password mismatch !", Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (!checkbox.isChecked())

//        {
//            Toast.makeText(this, "Term and cndition !", Toast.LENGTH_SHORT).show();
//            return false;
////
//        }
        return true;
    }

    public boolean validatePassword(String password) {
        return password.length() >= 6;
    }


}

package com.laundry.ui.LoginScreen;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.laundry.R;
import com.laundry.Utils.MySharedPreference;
import com.laundry.Utils.SharedPreference;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityMainBinding;
import com.laundry.ui.DryCleaner.DryCleanerActivity;
import com.laundry.ui.LoginScreen.vo.AccessTokenResponse;
import com.laundry.ui.LoginScreen.vo.LoginResponse;
import com.laundry.ui.LoginScreen.vo.SignUpResponse;
import com.laundry.ui.forgotPassword.ForgotPasswordActivity;
import com.laundry.ui.termsConditions.TermConditionActivity;


import java.util.regex.Pattern;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {

    ActivityMainBinding binding;
    private RadioButton sign_up, login;
    private LinearLayout loginbtn, signupbtn;
    private RadioGroup radio;
    private ImageView eye_image;
    private boolean isVisible = true;
    private static String TAG = MainActivity.class.getName();
    private boolean isShow = true;
    private MySharedPreference mySharedPreference;
    private String password, emailId, userName, confirmPwd, phoneNo, accessToken, tokenType, loginEmailId, loginPassword, userId;
    private TextView activity_login_btn, activity_sign_btn, login_title, forgot_password, termsAndCondition;
    private CheckBox checkbox;
    private EditText activity_login_edt_password, activity_login_edt_email, email, activity_password, confrim_password, name, phone_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mySharedPreference = MySharedPreference.getInstance(this);
        init();

//        callAccessTokenApi();

    }


    private void init() {

        sign_up = findViewById(R.id.sign_up);
        login = findViewById(R.id.login);
        name = findViewById(R.id.name);
        loginbtn = findViewById(R.id.loginbtn);
        signupbtn = findViewById(R.id.signupbtn);
        login_title = findViewById(R.id.login_title);
        eye_image = findViewById(R.id.eye_image);
        phone_no = findViewById(R.id.phone_no_et);
        termsAndCondition = findViewById(R.id.checkbox_termandcondition);
        forgot_password = findViewById(R.id.activity_forgot_password);
        //   password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        activity_sign_btn = findViewById(R.id.activity_sign_btn);
        activity_password = findViewById(R.id.activity_password);
        confrim_password = findViewById(R.id.confrim_password);
//        String emailId = activity_login_edt_email.getText().toString().trim();
//        String password1 = activity_login_edt_password.getText().toString();
        activity_login_edt_password = findViewById(R.id.activity_login_edt_password);
        activity_login_edt_email = findViewById(R.id.activity_login_edt_email);
        activity_login_btn = findViewById(R.id.activity_login_btn);
        radio = findViewById(R.id.radio);


        setupCheckout();
        activity_login_btn.setOnClickListener(this);
        activity_sign_btn.setOnClickListener(this);
        login_title.setOnClickListener(this);
        termsAndCondition.setOnClickListener(this);
        eye_image.setOnClickListener(this);
        binding.skipTv.setOnClickListener(this);
        forgot_password.setOnClickListener(this);

    }


    private void setupCheckout() {

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (sign_up.isChecked()) {
                    loginbtn.setVisibility(View.VISIBLE);
                    signupbtn.setVisibility(View.GONE);
                    sign_up.setTextColor(Color.WHITE);
                    sign_up.setBackgroundColor(getResources().getColor(R.color.sky_blue));
                    login.setBackgroundColor(getResources().getColor(R.color.white_color));
                    login.setTextColor(getResources().getColor(R.color.gray));

                }
                if (login.isChecked()) {
                    loginbtn.setVisibility(View.GONE);
                    signupbtn.setVisibility(View.VISIBLE);
                    login.setTextColor(Color.WHITE);
                    login.setBackgroundColor(getResources().getColor(R.color.sky_blue));
                    sign_up.setBackgroundColor(getResources().getColor(R.color.white_color));
                    sign_up.setTextColor(getResources().getColor(R.color.gray));
//
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.checkbox_termandcondition:
                Intent i = new Intent(MainActivity.this, TermConditionActivity.class);
                startActivity(i);
                break;
            case R.id.skip_tv:
                Intent skipIntent = new Intent(MainActivity.this, DryCleanerActivity.class);
                skipIntent.putExtra("Login", "Skip");
                startActivity(skipIntent);
                break;

            case R.id.login_title:
                onBackPressed();
                break;

            case R.id.activity_forgot_password:
                Intent forgotIntent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotIntent);
                break;

            case R.id.eye_image:
                if (isVisible) {
                    activity_password.setTransformationMethod(null);
                    binding.eyeImage.setImageResource(R.drawable.ic_hide);
                    isVisible = false;
                } else {
                    activity_password.setTransformationMethod(new PasswordTransformationMethod());
                    binding.eyeImage.setImageResource(R.drawable.ic_eye);
                    isVisible = true;

                }
                break;
            case R.id.activity_login_btn:
                if (isAllLoginFieldValid()) {
                    if (isNetworkConnected(this)) {
                        callLoginApi();
                    } else {
                        Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
                    }
                }

                break;


            case R.id.activity_sign_btn:
                if (isAllFieldValide()) {
                    if (isNetworkConnected(this)) {
                        callSignUpApi();
                    } else {
                        Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
                    }
                }

                break;


        }

    }

    private boolean isAllLoginFieldValid() {
        if (activity_login_edt_email.length() == 0 || activity_login_edt_password.length() == 0) {
            Toast.makeText(this, "field can not blank !", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isValidEmailId(activity_login_edt_email.getText().toString())) {
            activity_login_edt_email.setError("Not a valid email!");
            Toast.makeText(this, "Please enter valid email id !", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!validatePassword(activity_login_edt_password.getText().toString())) {
            activity_login_edt_password.setError("Not a valid password!");
            Toast.makeText(this, "Please enter valid password !", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private boolean isAllFieldValide() {

        if (name.length() == 0 || email.length() == 0 || activity_password.length() == 0 ||
                confrim_password.length() == 0 || phone_no.length() == 0
//                || checkbox.length() == 0
                ) {
            Toast.makeText(this, "please fill all detail!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (name.length() == 0) {
            Toast.makeText(this, "Name can't be blank", Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (!validateFirstName(name.getText().toString())) {
//            name.setError("Not a valid name!");
//            Toast.makeText(this, "Please enter valid name !", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        if (!isValidEmailId(email.getText().toString())) {
            email.setError("Not a valid email!");
            Toast.makeText(this, "Please enter valid email id !", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (!validatePassword(activity_password.getText().toString())) {
            activity_password.setError(" Max length is 6!");
            Toast.makeText(this, "Please enter valid password !", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!validatePassword(confrim_password.getText().toString())) {
            confrim_password.setError("Not a valid confirm password!");
            Toast.makeText(this, "Please enter valid password !", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!activity_password.getText().toString().equals(confrim_password.getText().toString())) {
            Toast.makeText(this, "password mismatch !", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!validatePhoneNumber(phone_no.getText().toString())) {
            phone_no.setError("Not a valid phone number!");
            Toast.makeText(this, "Please enter valid number !", Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (!checkbox.isChecked())
//
//        {
//            Toast.makeText(this, "Term and cndition !", Toast.LENGTH_SHORT).show();
//            return false;
////
//        }
        return true;
    }


    boolean isValidEmailId(String param) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(param).matches();
    }

    public boolean validatePassword(String password) {
        return password.length() >= 4;
    }

    public boolean validatePhoneNumber(String password) {
        return password.length() >= 10;
    }


//    public static boolean validateFirstName(String firstName) {
//        return firstName.matches("[A-Z][a-zA-Z]*");
//
//    }

    private void callAccessTokenApi() {

        Ion.with(this)
                .load("http://webdevelopmentreviews.net/laundry/token/index")
                .setBodyParameter("grant_type", "client_credentials"/*"807085"*/)
                .setBodyParameter("client_id", "developer")
                .setBodyParameter("client_secret", "5a633cf4392e8")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (result != null) {
                            AccessTokenResponse accessTokenResponse = new Gson().fromJson(result, AccessTokenResponse.class);
                            accessToken = accessTokenResponse.getAccess_token();
                            tokenType = accessTokenResponse.getToken_type();
//                            Toast.makeText(MainActivity.this, result + "", Toast.LENGTH_SHORT).show();
                        }

                    }

                });
    }


    private void callSignUpApi() {

        userName = name.getText().toString().trim();
        emailId = email.getText().toString().trim();
        password = activity_password.getText().toString().trim();
        confirmPwd = confrim_password.getText().toString().trim();
        phoneNo = phone_no.getText().toString().trim();

        new Utility().showProgressDialog(this);
        Call<SignUpResponse> call = APIClient.getInstance().getApiInterface().doSignUp(userName, phoneNo, emailId, password);
        new ResponseListner(this).getResponse(call);
    }


    private void callLoginApi() {

        loginEmailId = activity_login_edt_email.getText().toString().trim();
        loginPassword = activity_login_edt_password.getText().toString().trim();

        new Utility().showProgressDialog(this);
        Call<LoginResponse> call = APIClient.getInstance().getApiInterface().actionLogin(loginEmailId, loginPassword, "shjdjs", "jskjsfsf");
        new ResponseListner(this).getResponse(call);

    }


    @Override
    public void onApiResponse(Object response) {
        new Utility().hideDialog();
        if (response != null) {
            Log.e("MyResponse", new Gson().toJson(response));
            try {
                if (response instanceof SignUpResponse) {
                    SignUpResponse signUpResponse = (SignUpResponse) response;
//                    new Utility().hideDialog();
                    if (signUpResponse.isStatus()) {

                        Toast.makeText(this, signUpResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        name.setText("");
                        email.setText("");
                        confrim_password.setText("");
                        activity_password.setText("");
                        phone_no.setText("");
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                } else if (response instanceof LoginResponse) {
                    LoginResponse loginResponse = (LoginResponse) response;
                    if (loginResponse.isStatus()) {
                        SharedPreference sharedPreference = SharedPreference.getInstance(getApplicationContext());
                        sharedPreference.putString("IsLogin", "1");
                        userId = loginResponse.getUser_detail().getUser_id();

                        mySharedPreference.saveUserData(new Gson().toJson(loginResponse));
                        mySharedPreference.saveUserId(loginResponse.getUser_detail().getUser_id());
                        mySharedPreference.savePhoneNubmber(loginResponse.getUser_detail().getPhone());
                        mySharedPreference.saveUserName(loginResponse.getUser_detail().getName());
                        mySharedPreference.saveUserImage(loginResponse.getUser_detail().getImage());


                        //  Toast.makeText(this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, DryCleanerActivity.class);
                        i.putExtra("Login", "Login");
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                } else {

                }

            } catch (Exception e)

            {
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

package com.laundry.ui.LoginScreen;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.ui.DryCleaner.DryCleanerActivity;


import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RadioButton sign_up, login;
    LinearLayout loginbtn, signupbtn;
    RadioGroup radio;
    ImageView eye_image;
    EditText password;
    private boolean isVisible = true;
    private boolean isShow = true;
    String emailId;
    String password1;
    TextView activity_login_btn, activity_sign_btn, login_title,forgot_password;
    CheckBox checkbox;
    EditText activity_login_edt_password, activity_login_edt_email, email, activity_password, confrim_password, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign_up = findViewById(R.id.sign_up);
        login = findViewById(R.id.login);
        name = findViewById(R.id.name);
        loginbtn = findViewById(R.id.loginbtn);
        signupbtn = findViewById(R.id.signupbtn);
        login_title = findViewById(R.id.login_title);
        eye_image = findViewById(R.id.eye_image);
        checkbox = findViewById(R.id.checkbox);
        forgot_password=findViewById(R.id.activity_forgot_password);
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
        init();
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });


    }

    private void init() {
        setupCheckout();
        validation();
        activity_login_btn.setOnClickListener(this);
        activity_sign_btn.setOnClickListener(this);
        login_title.setOnClickListener(this);
        eye_image.setOnClickListener(this);

    }


    private void setupCheckout() {

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (sign_up.isChecked()) {
                    loginbtn.setVisibility(View.GONE);
                    signupbtn.setVisibility(View.VISIBLE);
                    sign_up.setTextColor(Color.WHITE);
                    sign_up.setBackgroundColor(getResources().getColor(R.color.sky_blue));
                    login.setBackgroundColor(getResources().getColor(R.color.white_color));
                    login.setTextColor(getResources().getColor(R.color.gray));

                }
                if (login.isChecked()) {
                    loginbtn.setVisibility(View.VISIBLE);
                    signupbtn.setVisibility(View.GONE);
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
            case R.id.eye_image:
                if (isVisible) {
                    activity_password.setTransformationMethod(null);
                    isVisible = false;
                } else {
                    activity_password.setTransformationMethod(new PasswordTransformationMethod());
                    isVisible = true;

                }
                break;
            case R.id.activity_login_btn:

                String emailId = activity_login_edt_email.getText().toString().trim();
                if (!isValidEmailId(emailId)) {
                    activity_login_edt_email.setError("Not a valid email address!");
                }
                String password1 = activity_login_edt_password.getText().toString();
                if (!validatePassword(password1)) {


//                       activity_login_edt_email.setErrorEnabled(false);
                    activity_login_edt_password.setError("Not a valid password!");
                    doLogin();
                }
                break;

//
            case R.id.activity_sign_btn:
                if (name.length() == 0 || email.length() == 0 || activity_password.length() == 0 ||
                        confrim_password.length() == 0 || checkbox.length() == 0) {
                    Toast.makeText(this, "field can not blank !", Toast.LENGTH_SHORT).show();
                    return;
                }
                String validateFirstName = name.getText().toString();

                if (!validateFirstName(validateFirstName)) {
                    return;
                }
                if (isValidEmailId(email.getText().toString())==false){
                    Toast.makeText(this, "Please enter valid email id !",Toast.LENGTH_SHORT).show();
                    return;
                }
                String passwordLogin = activity_password.getText().toString();
                if (!validatePassword(passwordLogin)) {


//                    activity_login_edt_email.setE
                    activity_password.setError("Not a valid password!");
                    return;

                }

                String confirm_passwordLogin = confrim_password.getText().toString();
                if (!validatePassword(confirm_passwordLogin)) {


                    // activity_login_edt_email.setErrorEnabled(false);
                    confrim_password.setError("Not a valid password!");
                    return;
                }
                if (!confirm_passwordLogin.equals(passwordLogin)) {
                    Toast.makeText(MainActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    return;


                }

                if (checkbox.isChecked())

                {
                    return;
//                } else {
//                    Toast.makeText(MainActivity.this, "condition", Toast.LENGTH_SHORT).show();
                }
                 else {
//                    Toast.makeText(MainActivity.this, "Password  match", Toast.LENGTH_SHORT).show();
                    doLogin();
                }

                break;

            case R.id.login_title:
                onBackPressed();

        }

    }


    private void validation() {
//       String emailId = activity_login_edt_email.getText().toString().trim();
//        if (!isValidEmailId(emailId)) {
//            activity_login_edt_email.setError("Not a valid email address!");
//        } String password1 = activity_login_edt_password.getText().toString();
//         if (!validatePassword(password1)) {
//
//
//            // activity_login_edt_email.setErrorEnabled(false);
//            activity_login_edt_password.setError("Not a valid password!");
//        } else {
//            //  activity_login_edt_email.setErrorEnabled(false);
//            //activity_login_edt_password.setErrorEnabled(false);
//            doLogin();
//        }
    }

    private void doLogin() {

        Intent i = new Intent(MainActivity.this, DryCleanerActivity.class);
        startActivity(i);
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
        return password.length() > 6;
    }

    public static boolean validateFirstName(String firstName) {
        return firstName.matches("[A-Z][a-zA-Z]*");

    }

}

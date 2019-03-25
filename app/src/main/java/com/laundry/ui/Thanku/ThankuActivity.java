package com.laundry.ui.Thanku;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.ui.DryCleaner.DryCleanerActivity;
import com.laundry.ui.Splash.SplashActivity;

public class ThankuActivity extends AppCompatActivity {
    TextView login_title;
    Button thank_you;
    private static final long TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanku);
        login_title=findViewById(R.id.login_title);
        thank_you =findViewById(R.id.thank_you);
        thank_you.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(ThankuActivity.this,DryCleanerActivity.class));
                Intent i = new Intent(ThankuActivity.this, DryCleanerActivity.class);
                i.putExtra("Login", "Login");
                startActivity(i);
                finish();


            }
        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(ThankuActivity.this,DryCleanerActivity.class));
//            }
//        },TIME_OUT);
//
//        login_title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
    }
}

package com.laundry.ui.Splash;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.laundry.R;
import com.laundry.Utils.SharedPreference;
import com.laundry.ui.DryCleaner.DryCleanerActivity;
import com.laundry.ui.LoginScreen.MainActivity;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView imgSplash = findViewById(R.id.splas);
        Glide.with(this).load(R.drawable.splash).into(imgSplash);
        new Handler().postDelayed(() -> {

            SharedPreference sharedPreference = SharedPreference.getInstance(getApplicationContext());
            if (sharedPreference.getString("IsLogin", "").equals("1")) {
                Intent i = new Intent(SplashActivity.this, DryCleanerActivity.class);
                i.putExtra("Login", "Login");
                startActivity(i);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}


//package com.laundry.ui.Splash;
//
//import android.content.Intent;
//import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.WindowManager;
//import android.widget.ImageView;
//
//import com.laundry.R;
//import com.laundry.Utils.SharedPreference;
//import com.laundry.ui.DryCleaner.DryCleanerActivity;
//import com.laundry.ui.LoginScreen.MainActivity;
//
//public class SplashActivity extends AppCompatActivity {
//    private static final long SPLASH_TIME_OUT = 1000;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//
//        ImageView imgSplash = findViewById(R.id.splas);
////        imgSplash.setImageResource(R.drawable.splash);
//
////                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.splash);
////        imgSplash.setImageBitmap(bitmap);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//
//                SharedPreference sharedPreference=SharedPreference.getInstance(getApplicationContext());
//                if (sharedPreference.getString("IsLogin","").equals("1")){
//                    startActivity(new Intent(getApplicationContext(), DryCleanerActivity.class));
//
//                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
//                    finish();
//                }else {
//                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
////                    intent.putExtra("finish", true);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
//                    finish();
//                }
//
//
//
////                Intent i = new Intent(SplashActivity.this, MainActivity.class);
////                startActivity(i);
////                setupWindowAnimations();
//
//            }
//        }, SPLASH_TIME_OUT);
//    }
//
//}
//

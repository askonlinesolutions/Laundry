package com.laundry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.laundry.ui.LoginScreen.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imgSplash = findViewById(R.id.splas);
        imgSplash.setImageResource(R.drawable.splash);

//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.splash);
//        imgSplash.setImageBitmap(bitmap);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                setupWindowAnimations();

            }
        }, SPLASH_TIME_OUT);
    }

    private void setupWindowAnimations() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

}


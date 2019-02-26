package com.laundry.ui.MyCart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.laundry.R;
import com.laundry.ui.MyPayment.PaymentActivity;

public class MyCartActivity extends AppCompatActivity {
    RecyclerView mycart_menu;
    TextView txt_apply, activity_text_discount, text_discount, text_money,login_title,activity_cart_btn_proced;
    private boolean isVisible = true;
    private boolean isShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        mycart_menu = findViewById(R.id.mycart_menu);
        text_money = findViewById(R.id.text_money);
        text_discount = findViewById(R.id.text_discount);
        login_title=findViewById(R.id.login_title);
        activity_cart_btn_proced=findViewById(R.id.activity_cart_btn_proced);
        nextactivity();
        back_btn();

        activity_text_discount = findViewById(R.id.activity_text_discount);

        txt_apply = findViewById(R.id.activity_cart_btn_apply);
        mycartmenu();
        applyDiscount();
    }

    private void nextactivity() {
        activity_cart_btn_proced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyCartActivity.this, PaymentActivity.class);
                startActivity(i);
//                finish();
            }
        });
    }

    private void back_btn() {
        login_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void applyDiscount() {
        txt_apply.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    activity_text_discount.setVisibility(View.VISIBLE);
                    text_discount.setTextColor(R.color.dark_grey);
                    text_money.setTextColor(R.color.sky_blue);
                    isVisible = true;
                }else
                    activity_text_discount.setVisibility(View.VISIBLE);
//                text_discount.setBackgroundColor(R.color.bg_color_2);
                isVisible = false;

            }
        });
    }

    private void mycartmenu() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mycart_menu.setLayoutManager(linearLayoutManager);
        MyCartAdapter myCartAdapter = new MyCartAdapter(MyCartActivity.this);
        mycart_menu.setAdapter(myCartAdapter);

    }

}

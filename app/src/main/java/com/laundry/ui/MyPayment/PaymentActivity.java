package com.laundry.ui.MyPayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.laundry.R;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {
 TextView login_title,placeOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        login_title =findViewById(R.id.login_title);
        placeOrder=findViewById(R.id.activity_place_order_btn);
        login_title.setOnClickListener(this);
        placeOrder.setOnClickListener(this);
        placeorder();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_title:
                onBackPressed();
                break;
            case R.id.activity_place_order_btn:
                Intent i = new Intent(PaymentActivity.this, PaymentMethodActivity.class);
                startActivity(i);
        }

    }
    private void placeorder() {


    }
}

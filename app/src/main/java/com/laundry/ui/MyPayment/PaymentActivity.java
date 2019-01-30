package com.laundry.ui.MyPayment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.laundry.R;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {
 TextView login_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        login_title =findViewById(R.id.login_title);
        login_title.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}

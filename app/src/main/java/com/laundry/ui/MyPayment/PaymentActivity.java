package com.laundry.ui.MyPayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.ui.Thanku.ThankuActivity;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {
    TextView login_title, placeOrder;
    RadioButton pay_pal, credit, cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        pay_pal = findViewById(R.id.pay_pal);
        credit = findViewById(R.id.credit);
        cod = findViewById(R.id.cod);
        login_title = findViewById(R.id.login_title);
        placeOrder = findViewById(R.id.activity_place_order_btn);
        pay_pal.setChecked(true);
        //  String selectedType = cod.getText().toString();

        login_title.setOnClickListener(this);
        placeOrder.setOnClickListener(this);
        //placeorder();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_title:
                onBackPressed();
                break;
            case R.id.activity_place_order_btn:
                if (pay_pal.isChecked() || credit.isChecked() || cod.isChecked()) {


                    Intent i = new Intent(PaymentActivity.this, ThankuActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(this, "Please choose 1 answer",
                            Toast.LENGTH_SHORT).show();
                }

        }
    }
}

//    private void placeorder() {
//
//
//    }


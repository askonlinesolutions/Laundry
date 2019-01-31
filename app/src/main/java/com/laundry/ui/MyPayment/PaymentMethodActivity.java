package com.laundry.ui.MyPayment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.ui.Thanku.ThankuActivity;


public class PaymentMethodActivity extends AppCompatActivity {
    TextView login_title,add_new_card,cancel_btn,playnowbtn;
    RecyclerView pament_recycler;
    ImageView btncross;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        login_title=findViewById(R.id.login_title);
        add_new_card=findViewById(R.id.activity_add_new_card);
        btncross=findViewById(R.id.close_img);

        pament_recycler=findViewById(R.id.pament_recycler);
        paymentmethod();
        back_btn();
        paymentDialog();



    }

    private void paymentDialog() {
        add_new_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogpayment();
            }


        });



    }

    private void paymentmethod() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        pament_recycler.setLayoutManager(linearLayoutManager);
        PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(this);
        pament_recycler.setAdapter(paymentMethodAdapter);
    }

    private void back_btn() {
        login_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void showDialogpayment() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_pament_dialog);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                btncross = (ImageView) dialog.findViewById(R.id.close_img);
        cancel_btn=dialog.findViewById(R.id.cancel_btn);
        playnowbtn=dialog.findViewById(R.id.playnowbtn);
                btncross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        dialog.dismiss();
                    }
                });
                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                playnowbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(PaymentMethodActivity.this, ThankuActivity.class);
                        startActivity(i);
                    }
                });

    }
}

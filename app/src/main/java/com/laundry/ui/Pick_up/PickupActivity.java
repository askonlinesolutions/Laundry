package com.laundry.ui.Pick_up;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.laundry.R;
import com.laundry.clickListener.OnItemClickLisner;
import com.laundry.ui.LoginScreen.MainActivity;
import com.laundry.ui.MyCart.MyCartActivity;
import com.laundry.ui.currentLocation.CurrentLocationMapActivity;
import com.laundry.ui.forgotPassword.ForgotPasswordActivity;


public class PickupActivity extends AppCompatActivity implements OnItemClickLisner, View.OnClickListener {
    RecyclerView rv;
    ImageView back_iv,my_cart_iv;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);
        back_iv=findViewById(R.id.back_iv);
        rv=findViewById(R.id.rv_pickup);
        my_cart_iv=findViewById(R.id.my_cart_iv);
        confirm=findViewById(R.id.schedule_pickup_tv);
        init();
        pickupDrop();
        backpress();
    }

    private void init() {
        my_cart_iv.setOnClickListener(this);
        confirm.setOnClickListener(this);
//

    }

    private void backpress() {
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void pickupDrop() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(linearLayoutManager);
        PickDropAdapter pickDropAdapter = new PickDropAdapter(this,this);
        rv.setAdapter(pickDropAdapter);


    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(PickupActivity.this,CurrentLocationMapActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_cart_iv:
                Intent i = new Intent(PickupActivity.this, MyCartActivity.class);
                startActivity(i);
                break;
            case R.id.schedule_pickup_tv:
                Intent intent = new Intent(PickupActivity.this, MyCartActivity.class);
                startActivity(intent);
                break;
        }
    }
}

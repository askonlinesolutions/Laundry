package com.laundry.ui.Pick_up;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.laundry.R;


public class PickupActivity extends AppCompatActivity {
    RecyclerView rv;
    ImageView back_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);
        back_iv=findViewById(R.id.back_iv);
        rv=findViewById(R.id.rv_pickup);
        pickupDrop();
        backpress();
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
        PickDropAdapter pickDropAdapter = new PickDropAdapter(this);
        rv.setAdapter(pickDropAdapter);


    }
}

package com.laundry.ui.Pick_up;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.laundry.R;


public class PickupActivity extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);
        rv=findViewById(R.id.rv_pickup);
        pickupDrop();
    }

    private void pickupDrop() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(linearLayoutManager);
        PickDropAdapter pickDropAdapter = new PickDropAdapter(this);
        rv.setAdapter(pickDropAdapter);


    }
}

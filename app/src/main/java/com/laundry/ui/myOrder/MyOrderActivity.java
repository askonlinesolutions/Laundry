package com.laundry.ui.myOrder;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.laundry.R;
import com.laundry.databinding.ActivityMyOrderBinding;

public class MyOrderActivity extends AppCompatActivity implements MyOrderAdapter.MessageAdapterInterface {

    ActivityMyOrderBinding binding;
    RecyclerView myOrderRv;
    MyOrderAdapter myOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_order);

        init();

    }

    private void init() {

        myOrderRv = findViewById(R.id.myOrder_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        myOrderRv.setLayoutManager(linearLayoutManager);
        myOrderAdapter = new MyOrderAdapter(this, /*name,image,*/this);
        myOrderRv.setAdapter(myOrderAdapter);
    }

    @Override
    public void click(int position) {

    }
}

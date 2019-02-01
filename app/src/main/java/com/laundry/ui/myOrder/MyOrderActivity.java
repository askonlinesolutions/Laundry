package com.laundry.ui.myOrder;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.laundry.R;
import com.laundry.databinding.ActivityMyOrderBinding;
import com.laundry.ui.MyCart.MyCartActivity;
import com.laundry.ui.myOrderDetails.MyOrderDetailsActivity;

public class MyOrderActivity extends AppCompatActivity implements MyOrderAdapter.MessageAdapterInterface, View.OnClickListener {

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
        binding.myCartIv.setOnClickListener(this);
        binding.backIv.setOnClickListener(this);
    }

    @Override
    public void click(int position) {
        Intent intent = new Intent(MyOrderActivity.this, MyOrderDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;

            case R.id.my_cart_iv:
                startActivity(new Intent(MyOrderActivity.this, MyCartActivity.class));
                break;
        }

    }
}

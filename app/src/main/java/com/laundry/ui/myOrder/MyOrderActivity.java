package com.laundry.ui.myOrder;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.laundry.R;
import com.laundry.Utils.MySharedPreference;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityMyOrderBinding;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.LoginScreen.vo.LoginResponse;
import com.laundry.ui.MyCart.MyCartActivity;
import com.laundry.ui.Services.vo.CartCountResponse;
import com.laundry.ui.myOrder.vo.MyOrderResponse;
import com.laundry.ui.myOrderDetails.MyOrderDetailsActivity;

import java.util.ArrayList;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class MyOrderActivity extends AppCompatActivity implements MyOrderAdapter.MessageAdapterInterface, View.OnClickListener, OnResponseInterface {

    private ActivityMyOrderBinding binding;
    RecyclerView myOrderRv;
    MyOrderAdapter myOrderAdapter;
    private ArrayList<MyOrderResponse.DataEntity> orderList = new ArrayList<>();
    private static String TAG = MyOrderActivity.class.getName();
    private String cust_Id;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_order);

        getCustomerId();
        init();

    }

    private void getCustomerId() {
        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        cust_Id = mySharedPreference.getUserId();
        Log.e("MyUserId", cust_Id);
    }


    private void init() {

        myOrderRv = findViewById(R.id.myOrder_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        myOrderRv.setLayoutManager(linearLayoutManager);

//        setAdapter();

        binding.myCartIv.setOnClickListener(this);
        binding.backIv.setOnClickListener(this);

        if (isNetworkConnected(this)) {
            callMyOrderApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }

        if (isNetworkConnected(this)) {
            callCartCountApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }

    }


    private void setAdapter() {
        myOrderAdapter = new MyOrderAdapter(this, orderList, this);
        myOrderRv.setAdapter(myOrderAdapter);

    }

    @Override
    public void click(int position, String orderId) {
        Intent intent = new Intent(MyOrderActivity.this, MyOrderDetailsActivity.class);
        intent.putExtra("orderId", orderId);
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

    private void callCartCountApi() {
//        new Utility().showProgressDialog(this);
        Call<CartCountResponse> call = APIClient.getInstance().getApiInterface().getCartCount(cust_Id);
        new ResponseListner(this).getResponse(call);
    }

    private void callMyOrderApi() {
        new Utility().showProgressDialog(this);
        Call<MyOrderResponse> call = APIClient.getInstance().getApiInterface().getOrdersList(cust_Id/*"26"*/);
        Log.e("MyOrderUrl", call.request().url().toString());
        new ResponseListner(this).getResponse(call);

    }


    @Override
    public void onApiResponse(Object response) {


        if (response != null) {
            new Utility().hideDialog();
            try {
                if (response instanceof MyOrderResponse) {
                    MyOrderResponse myOrderResponse = (MyOrderResponse) response;
                    new Utility().hideDialog();
                    if (myOrderResponse.isStatus()) {
                        binding.myOrderRv.setVisibility(View.VISIBLE);
                        binding.noDataTv.setVisibility(View.GONE);
                        orderList.clear();
                        if (myOrderResponse.getData() != null /*&& messageDataList.size() != 0*/) {
                            orderList.addAll(myOrderResponse.getData());

                            setAdapter();
                        }
                    } else {
                        binding.myOrderRv.setVisibility(View.GONE);
                        binding.noDataTv.setVisibility(View.VISIBLE);
                    }
                } else if (response instanceof CartCountResponse) {
//                    new Utility().hideDialog();
                    CartCountResponse cartCountResponse = (CartCountResponse) response;
                    if (cartCountResponse.isStatus()) {
//                        Toast.makeText(this, "count...24", Toast.LENGTH_SHORT).show();
                        if (count > 0) {
                            count = 0;
                        }
                        count = cartCountResponse.getCart_count();

                        binding.cartCountTv.setText(String.valueOf(count/*cartCountResponse.getCart_count()*/));
                    }
                }


            } catch (Exception e) {
                Log.d("TAG", "onApiResponse: " + e.getMessage());
            }
        } else {
            new Utility().hideDialog();
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onApiFailure(String message) {
        new Utility().hideDialog();
        Log.d(TAG, "onApiFailure: " + message);
    }
}
